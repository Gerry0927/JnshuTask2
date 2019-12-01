package com.gerry.jnshu.service;


import com.gerry.jnshu.bean.Student;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface StudentService {
    PageInfo<Student> getStudentList(int pageNum, int pageSize);
    int addStudent(Student student);
    int updateSloganById(Integer id,String slogan);
    int deleteStudentById(Integer id);
}
