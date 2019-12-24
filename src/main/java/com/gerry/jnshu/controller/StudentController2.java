package com.gerry.jnshu.controller;

import com.gerry.jnshu.bean.Student;
import com.gerry.jnshu.response.Result;
import com.gerry.jnshu.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.File;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("student")
@Validated
public class StudentController2 {

    @Resource
    StudentService studentService;

    @RequestMapping(value = "/{id}/{name}",method = RequestMethod.GET)
    @ResponseBody
    public Student getStudentInfo(@PathVariable Integer id, @PathVariable @Size(min = 1,max = 3,message = "名字长度为1-3") String name){
        Student student = new Student();
        student.setName(name);
        student.setId(id);
        return student;
    }



//    @RequestMapping(value = "/",method = RequestMethod.GET)
//    @ResponseBody
//    public Result<List<Student>> getStudentList(){
//        List<Student> studentList = studentService.getStudentList();
//        String msg = "";
//        if(studentList.size()>0){
//            msg="查询成功";
//        }
//        else{
//            msg="暂无数据";
//        }
//        return Result.success(studentList,msg);
//    }

    @RequestMapping(value = "/",method = RequestMethod.POST)
    @ResponseBody
    public Result<Integer> addStudentInfo( @Validated({Student.GroupA.class, Student.GroupB.class}) Student student){
        int id = studentService.addStudent(student);
        String msg = "";
        if(id>0){
           msg="插入成功";
        }
        else{
            msg="插入失败";
        }
        return Result.success(id,msg);
    }



    @ResponseBody
    @RequestMapping(value = "/{id}/{slogan}",method = RequestMethod.PUT)
    public Result<Integer> updateSlogan(@PathVariable Integer id,@PathVariable String slogan){
        int row = studentService.updateSloganById(id,slogan);
        String msg = "";
        if(row>0){
            msg="更新成功";
        }
        else{
            msg="更新失败";
        }
        return Result.success(row,msg);

    }

    @ResponseBody
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public Result<Integer> deleteStudentInfo(@PathVariable Integer id){
        int row = studentService.deleteStudentById(id);
        String msg = "";
        if(row>0){
            msg="删除成功";
        }
        else{
            msg="删除失败";
        }
        return Result.success(row,msg);

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