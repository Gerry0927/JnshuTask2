package com.gerry.jnshu.controller;

import com.gerry.jnshu.bean.Result;
import com.gerry.jnshu.bean.Student;
import com.gerry.jnshu.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;
import java.util.UUID;

@RequestMapping("student")
@Controller
public class StudentController {

    @Resource
    StudentService studentService;

    @RequestMapping(value = "/{id}/{name}",method = RequestMethod.GET)
    @ResponseBody
    public Student getStudentInfo(@PathVariable Integer id, @PathVariable String name){
        Student student = new Student();
        student.setName(name);
        student.setId(id);
        return student;
    }


    @RequestMapping(value = "/",method = RequestMethod.GET)
    @ResponseBody
    public Result<List<Student>> getStudentList(){
        List<Student> studentList = studentService.getStudentList();
        Result<List<Student>> result = new Result<>();
        result.data=studentList;
        result.message="查询成功";
        result.code=200;
        return result;
    }

    @RequestMapping(value = "/",method = RequestMethod.POST)
    @ResponseBody
    public Result<Integer> addStudentInfo(Student student){
        int id = studentService.addStudent(student);
        Result<Integer> result = new Result<>();
        result.code=200;
        if(id>0){
            result.message="插入成功";
            result.data=id;
        }
        else{
            result.message="插入失败";
        }
        return result;
    }



    @RequestMapping(value = "/queryInfo2")
    public String hello(Model model, Integer[] stid) {
        System.out.println("接收到的请求参数：" + stid[0]);
        System.out.println("接收到的请求参数：" + stid[1]);
        System.out.println("接收到的请求参数：" + stid[2]);
        // 逻辑视图名称
        return "index";
    }

    @RequestMapping(value = "/updateFile")
    public String updateItem(Model model, MultipartFile pictureFile) throws Exception {

        if (pictureFile != null) {
            System.out.println(pictureFile.getOriginalFilename());
            //原始图片名称
            String originalFilename = pictureFile.getOriginalFilename();
            //如果没有图片名称，则上传不成功
            if (originalFilename != null && originalFilename.length() > 0) {
                //存放图片的物理路径
                String picPath = "app/pictures";
                //新文件的名称
                String newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
                //新的文件
                File newFile = new File(picPath + newFileName);
                //把上传的文件保存成一个新的文件
                pictureFile.transferTo(newFile);
            }
        }
        return "index";
    }

}