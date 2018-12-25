package cn.fcsca.springbootcache.controller;

import cn.fcsca.springbootcache.bean.Department;
import cn.fcsca.springbootcache.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * DepartmentController
 *
 * @author Fcscanf@樊乘乘
 * @description
 * @date 下午 20:10 2018-08-24
 */
@RestController
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @GetMapping("/dept/{id}")
    public Department getDeptById(@PathVariable("id") Integer id) {
        return departmentService.getDeptById(id);
    }
}
