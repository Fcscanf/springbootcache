package cn.fcsca.springbootcache.controller;

import cn.fcsca.springbootcache.bean.Employee;
import cn.fcsca.springbootcache.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * EmployeeController
 *
 * 接口开启基于注解的缓存@EnableCaching
 * 方法相关的缓存设置：
 * @Cacheable 针对方法请求参数和结果进行缓存
 * @CacheEvict 清空缓存
 * @CachePut 保证方法被调用，对结果进行更新缓存
 *
 * @author Fcscanf@樊乘乘
 * @description
 * @date 上午 10:04 2018-08-23
 */
@EnableCaching
@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/emp/{id}")
    public Employee getEmployeeWithDeptById(@PathVariable("id") Integer id) {
        Employee employee = employeeService.getEmpWithDeptById(id);
        return employee;
    }

    @GetMapping("/emp")
    public Employee updateEmp(Employee employee) {
        Employee emp = employeeService.updateEmp(employee);
        return emp;
    }

    @GetMapping("/delemp")
    public String deleteEmp(Integer id) {
        employeeService.deleteEmp(id);
        return "success";
    }

    @GetMapping("/emp/lastname/{lastName}")
    public Employee getEmpByLastName(@PathVariable("lastName") String lastName) {
        return employeeService.getEmpByLastName(lastName);
    }
}
