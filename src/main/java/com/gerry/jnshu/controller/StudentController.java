package com.gerry.jnshu.controller;

import com.gerry.jnshu.response.Result;
import com.gerry.jnshu.bean.Student;
import com.gerry.jnshu.service.StudentService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.jws.WebParam;
import java.io.File;
import java.util.List;
import java.util.UUID;


@Controller
public class StudentController {

    @Resource
    StudentService studentService;

    @RequestMapping(value = "/{id}/{name}", method = RequestMethod.GET)
    @ResponseBody
    public Student getStudentInfo(@PathVariable Integer id, @PathVariable String name) {
//        Student student = new Student();
//        student.setName(name);
//        student.setId(id);

        Student student = studentService.getStudentInfo(id,name);
        return student;
    }

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String getStudentList(Model model, @RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "10") int pageSize){
        PageInfo<Student> pageInfo = studentService.getStudentList(pageNum,pageSize);
        model.addAttribute("pageInfo",pageInfo);
        return "student_list";
    }

    @RequestMapping(value = "/",method = RequestMethod.POST)
    public String addStudentInfo(@Validated Student student){
        int id = studentService.addStudent(student);
        return "redirect:/student/";
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public String updateSlogan(@PathVariable Integer id,String slogan){
        int row = studentService.updateSloganById(id,slogan);
        return "redirect:/student/";

    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public String deleteStudentInfo(@PathVariable Integer id){
        int row = studentService.deleteStudentById(id);
        return "redirect:/student/";

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