package com.gerry.jnshu.service.impl;

import com.gerry.jnshu.bean.Student;
import com.gerry.jnshu.mapper.StudentMapper;
import com.gerry.jnshu.service.StudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Resource
    private StudentMapper studentMapper;
    @Override
    public List<Student> getStudentList() {
        return studentMapper.getStudentList();
    }

    @Override
    public int addStudent(Student student) {
        return studentMapper.addStudent(student);
    }
}
