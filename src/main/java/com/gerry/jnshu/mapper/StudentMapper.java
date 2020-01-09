package com.gerry.jnshu.mapper;

import com.gerry.jnshu.bean.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentMapper {
    List<Student> getStudentList();
    Integer addStudent(Student student);
    int updateSloganById(Integer id,String slogan);
    int deleteStudentById(Integer id);

    Student queryStudentInfo(@Param("id") Integer id, @Param("name") String name);
}