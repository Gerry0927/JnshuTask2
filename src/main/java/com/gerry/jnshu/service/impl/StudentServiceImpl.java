package com.gerry.jnshu.service.impl;

import com.gerry.jnshu.bean.Student;
import com.gerry.jnshu.mapper.StudentMapper;
import com.gerry.jnshu.service.StudentService;
import com.gerry.jnshu.util.JSONUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.logging.log4j.core.util.JsonUtils;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Resource
    private StudentMapper studentMapper;

    @Resource
    private RedisTemplate redisTemplate;

    public static final String KEY_PATTERN = "student:%d:%s";

    @Override
    public Student getStudentInfo(Integer id, String name) {
        String key = String.format(KEY_PATTERN,id,name);
        Student student = JSONUtil.parseObject((String) redisTemplate.opsForValue().get(key),Student.class);
        if(student==null){
            student = studentMapper.queryStudentInfo(id,name);
            redisTemplate.opsForValue().set(key,JSONUtil.toJSONBytes(student));
        }
        return student;
    }

    @Override
    public int addStudent(Student student) {
        int stuId = studentMapper.addStudent(student);
        String key = String.format(KEY_PATTERN,student.getId(),student.getName());
        redisTemplate.opsForValue().set(key,JSONUtil.toJSONString(student));
        return stuId;
    }


    //    private BoundValueOperations<String,String>
    @Override
    public PageInfo<Student> getStudentList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Student> studentList = studentMapper.getStudentList();
        PageInfo<Student> pageInfo = new PageInfo<Student>(studentList);
//        redisTemplate.opsForValue("");
        return pageInfo;
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
