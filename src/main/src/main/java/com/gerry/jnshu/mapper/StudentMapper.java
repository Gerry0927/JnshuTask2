package com.gerry.jnshu.mapper;

import com.gerry.jnshu.bean.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentMapper {
    List<Student> getStudentList();
    int addStudent(Student student);

}