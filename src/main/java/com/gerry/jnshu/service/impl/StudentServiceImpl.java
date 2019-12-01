package com.gerry.jnshu.service.impl;

import com.gerry.jnshu.bean.Student;
import com.gerry.jnshu.mapper.StudentMapper;
import com.gerry.jnshu.service.StudentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Resource
    private StudentMapper studentMapper;
    @Override
    public PageInfo<Student> getStudentList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Student> studentList = studentMapper.getStudentList();
        PageInfo<Student> pageInfo = new PageInfo<Student>(studentList);
        return pageInfo;
    }

    @Override
    public int addStudent(Student student) {
        return studentMapper.addStudent(student);
    }

    @Override
    public int updateSloganById(Integer id, String slogan) {
        return studentMapper.updateSloganById(id,slogan);
    }

    @Override
    public int deleteStudentById(Integer id) {
        return studentMapper.deleteStudentById(id);
    }
}
