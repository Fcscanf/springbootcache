package cn.fcsca.springbootcache.service;

import cn.fcsca.springbootcache.bean.Department;
import cn.fcsca.springbootcache.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Service;

/**
 * DepartmentService
 * @CacheConfig(cacheManager = "") 引入使用的缓存管理，不设置默认使用主缓存管理
 *
 * @author Fcscanf@樊乘乘
 * @description
 * @date 下午 20:05 2018-08-24
 */

@Service
public class DepartmentService {

    @Autowired
    DepartmentMapper departmentMapper;

    /**
     * 注入自定义的缓存管理器
     */
    @Autowired
    RedisCacheManager deptCacheManager;

    @Cacheable(cacheNames = "dept",cacheManager = "deptCacheManager")
    public Department getDeptById(Integer id) {
        System.out.println("查询部门" + id);
        Department department = departmentMapper.selectByPrimaryKey(id);
        return department;
    }

    public Department getDeptByIdCache(Integer id) {
        System.out.println("查询部门" + id);
        Department department = departmentMapper.selectByPrimaryKey(id);

        //获取某个缓存,进行编码缓存操作调用API
        Cache dept = deptCacheManager.getCache("dept");
        dept.put("dept:1",department);

        return department;
    }
}
