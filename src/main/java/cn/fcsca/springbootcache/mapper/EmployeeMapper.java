package cn.fcsca.springbootcache.mapper;

import cn.fcsca.springbootcache.bean.Employee;
import cn.fcsca.springbootcache.bean.EmployeeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface EmployeeMapper {
    long countByExample(EmployeeExample example);

    int deleteByExample(EmployeeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Employee record);

    int insertSelective(Employee record);

    List<Employee> selectByExample(EmployeeExample example);

    List<Employee> selectByExampleWithDept(EmployeeExample example);

    Employee selectByPrimaryKey(Integer id);

    Employee selectByPrimaryKeyWithDept(Integer id);

    int updateByExampleSelective(@Param("record") Employee record, @Param("example") EmployeeExample example);

    int updateByExample(@Param("record") Employee record, @Param("example") EmployeeExample example);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);

    @Select("SELECT * FROM employee WHERE last_name=#{lastName}")
    Employee selectByLastName(String lastName);
}