package com.gerry.jnshu.bean;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;
import java.util.Date;
import java.util.List;

public class Student {

//    @NotBlank
//    @Range(min = 1, max = Integer.MAX_VALUE, message = "必须大于0", groups = {GroupA.class})
    /**用户id*/
    private int id;

    @NotBlank(message = "用户名不能为空")
//    @Length(min = 4, max = 20, message = "必须在[4,20]", groups = {GroupB.class})
    private String name;
    @NotBlank(message = "学号不能为空")
//    @Range(min = 0, max = 100, message = "学号必须在[0,100]", groups = {Default.class})
    private String onlineNum;

    @NotBlank(message = "修真类型不能为空")
    private String jnshuType;

    private String qq;
    private String joinTime;
    private String school;
    private String dailyUrl;
    private String slogan;
    private String counsellor;
    private String knownPath;
    private long createTime;
    private long updateTime;
    //    private Address address;

    @Valid
    @NotNull
    @Size(min = 1,message = "address 不能为空")
    List<Address> address;

    private Date birthday;
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getJnshuType() {
        return jnshuType;
    }

    public void setJnshuType(String jnshuType) {
        this.jnshuType = jnshuType;
    }

    public String getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(String join_time) {
        this.joinTime = join_time;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getOnlineNum() {
        return onlineNum;
    }

    public void setOnlineNum(String onlineNum) {
        this.onlineNum = onlineNum;
    }

    public String getDailyUrl() {
        return dailyUrl;
    }

    public void setDailyUrl(String dailyUrl) {
        this.dailyUrl = dailyUrl;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getCounsellor() {
        return counsellor;
    }

    public void setCounsellor(String counsellor) {
        this.counsellor = counsellor;
    }

    public String getKnownPath() {
        return knownPath;
    }

    public void setKnownPath(String known_path) {
        this.knownPath = known_path;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", qq='" + qq + '\'' +
                ", jnshuType='" + jnshuType + '\'' +
                ", joinTime='" + joinTime + '\'' +
                ", school='" + school + '\'' +
                ", onlineNum='" + onlineNum + '\'' +
                ", dailyUrl='" + dailyUrl + '\'' +
                ", slogan='" + slogan + '\'' +
                ", counsellor='" + counsellor + '\'' +
                ", knownPath='" + knownPath + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }

    public interface GroupA{}

    public interface GroupB{}




}