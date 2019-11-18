package com.gerry.jnshu.service;


import com.gerry.jnshu.bean.Student;

import java.util.List;

public interface StudentService {
    List<Student> getStudentList();
    int addStudent(Student student);
    int updateSloganById(Integer id,String slogan);
    int deleteStudentById(Integer id);
}
