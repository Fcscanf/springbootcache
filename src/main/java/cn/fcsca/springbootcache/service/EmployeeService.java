package cn.fcsca.springbootcache.service;

import cn.fcsca.springbootcache.bean.Employee;
import cn.fcsca.springbootcache.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

/**
 * EmployeeService
 * @CacheConfig(cacheNames = "emp") 指定该类中的公共配置，即将方法上的公共配置抽取出来
 *
 * @author Fcscanf@樊乘乘
 * @description
 * @date 上午 9:57 2018-08-23
 */
@CacheConfig(cacheNames = "emp")
@Service
public class EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    /**
     * 将方法的运行结果进行缓存：以后读取相同的数据直接从缓存读取，不调用方法 @Cacheable
     *
     * CacheManager管理多个Cache组件，对缓存的真正CRUD组件中，每一个缓存都有自己唯一的名字
     * 属性：
     *    cacheNames/value：指定缓存组件的名字
     *
     *  key和keyGenerator二选一
     *    key：缓存数据使用的KEY，可以用他指定数据，默认是使用方法参数的值，1-方法的返回值
     *         值用SpEl表达式指定：#id：参数id的值， #a0 #p0 #root.args[0]
     *       E:key = "#root.methodName+'['+#id+']'"
     *    keyGenerator：key的生成器，可以自己指定key的生成器的组件id
     *       E:keyGenerator = "keyGenerator";指定自定义key生成策略注册Bean的ID名称
     *
     *    cacheManager：指定缓存管理器；或者cacheResolver指定获取解析器
     *    condition：指定符合条件下才缓存
     *       E:condition = "#id>0"
     *    unless：否定缓存；当unless指定的条件为true时，方法的返回值就不会被缓存，可以获取结果进行判断
     *       E:unless = "#result == null"
     *    sync：是否采用异步模式
     *
     * @param id
     * @return employee
     * @author Fcscanf@樊乘乘
     * @date 上午 11:37 2018-08-23
     */
    @Cacheable(cacheNames = {"emp"})
    public Employee getEmpWithDeptById(Integer id) {
        System.out.println("查询" + id + "号员工！");
        Employee employee = employeeMapper.selectByPrimaryKeyWithDept(id);
        return employee;
    }

    /**
     * @CachePut 既调用方法，又更新缓存数据；
     *   通过设定key来更新数据：
     *     key = "#employee.id"
     *     key = "#result.id"
     *
     * @param
     * @return
     * @author Fcscanf@樊乘乘
     * @date 下午 17:20 2018-08-23
     */
    @CachePut(value = "emp",key = "#result.id")
    public Employee updateEmp(Employee employee) {
        System.out.println("更新后的员工：" + employee);
        employeeMapper.updateByPrimaryKey(employee);
        return employee;
    }

    /**
     * @CacheEvict 缓存清除,E:删除员工后就不需要保留相关的缓存数据
     *   1.key = "#id"：删除指定的id
     *   2.allEntries = true：清除这个缓存所有的数据
     *   3.beforeInvocation
     *     beforeInvocation = false:
     *       指定缓存清除在方法执行之前执行,默认在方法之后执行;如果出现异常则不会清除缓存
     *     beforeInvocation = true:
     *       代表缓存清除操作是在方法运行之前执行，无论方法出现异常，缓存都会清除
     *
     * @param
     * @return
     * @author Fcscanf@樊乘乘
     * @date 下午 19:13 2018-08-23
     */
    @CacheEvict(value = "emp")
    public void deleteEmp(Integer id) {
        System.out.println("删除"+ id + "号员工");
        employeeMapper.deleteByPrimaryKey(id);
    }

    @Caching(
        cacheable = {
            @Cacheable(value = "emp",key = "#lastName")
        },
        put = {
            @CachePut(value = "emp",key = "#result.id"),
            @CachePut(value = "emp",key = "#result.email")
        }
    )
    public Employee getEmpByLastName(String lastName) {
        return employeeMapper.selectByLastName(lastName);
    }
}
