package cn.fcsca.springbootcache;

import cn.fcsca.springbootcache.bean.Department;
import cn.fcsca.springbootcache.bean.Employee;
import cn.fcsca.springbootcache.mapper.EmployeeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootcacheApplicationTests {

    @Autowired
    EmployeeMapper employeeMapper;

    /**
     * 操作的k-v都是字符串
     */
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    /**
     * 操作k-v都是对象
     */
    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    RedisTemplate<Object, Employee> redisEmpTemplate;

    @Autowired
    RedisTemplate<Object, Department> redisDeptTemplate;

    /**
     * 测试Redis保存String字符串键值 
     *
     * @param
     * @return 
     * @author Fcscanf@樊乘乘
     * @date 上午 8:58 2018-08-24 
     */
    @Test
    public void redisStringTest() {
        stringRedisTemplate.opsForValue().append("msg", "world");
        String msg = stringRedisTemplate.opsForValue().get("msg");
        System.out.println(msg);
    }

    /**
     * 测试Redis保存对象 对象需要序列化
     *
     * @param 
     * @return 
     * @author Fcscanf@樊乘乘
     * @date 上午 8:57 2018-08-24 
     */
    @Test
    public void redisTest() {
        Employee employee = employeeMapper.selectByPrimaryKeyWithDept(1);
        redisTemplate.opsForValue().set("emp01",employee);
    }

    /**
     * 改变默认的序列化规则
     * 通过RedisConfig配置类将对象JSON化，注入调用测试保存JSON形式的对象 
     *
     * @param
     * @return 
     * @author Fcscanf@樊乘乘
     * @date 上午 9:27 2018-08-24 
     */
    @Test
    public void redisJsonTest() {
        Employee employee = employeeMapper.selectByPrimaryKeyWithDept(1);
        redisEmpTemplate.opsForValue().set("emp01",employee);
    }

    @Test
    public void contextLoads() {
        Employee employee = employeeMapper.selectByPrimaryKeyWithDept(1);
        System.out.println("1号对象是" + employee);
    }

}
