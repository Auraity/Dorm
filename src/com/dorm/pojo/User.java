package com.dorm.pojo;

import java.util.List;

public class User {
    private Integer id;
    private String name;//用户名
    private String password;//用户密码
    private String stuCode;//用户学号
    private String dormCode;//寝室号
    private String sex;
    private String tel;
    private Integer dormBuildId;//宿舍楼id
    private Integer roleId;//角色id，0：超级管理员；1：宿舍管理员；2：学生
    private Integer createUserId;//创建着id
    private Integer disabled;//用户状态，0：激活；1：停用

    private DormBuild dormBuild;
    private List<DormBuild> dormBuilds;

    public User() {
    }

    public User(String name, String stuCode, String sex, String dormCode,DormBuild dormBuild) {
        this.name = name;
        this.stuCode = stuCode;
        this.sex = sex;
        this.dormCode = dormCode;
        this.dormBuild = dormBuild;
    }

    public User(Integer id, String name, String password, String stuCode, String dormCode, String sex, String tel, Integer dormBuildId, Integer roleId, Integer createUserId, Integer disabled) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.stuCode = stuCode;
        this.dormCode = dormCode;
        this.sex = sex;
        this.tel = tel;
        this.dormBuildId = dormBuildId;
        this.roleId = roleId;
        this.createUserId = createUserId;
        this.disabled = disabled;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", stuCode='" + stuCode + '\'' +
                ", dormCode='" + dormCode + '\'' +
                ", sex='" + sex + '\'' +
                ", tel='" + tel + '\'' +
                ", dormBuildId=" + dormBuildId +
                ", roleId=" + roleId +
                ", createUserId=" + createUserId +
                ", disabled=" + disabled +
                ", dormBuild=" + dormBuild +
                ", dormBuilds=" + dormBuilds +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStuCode() {
        return stuCode;
    }

    public void setStuCode(String stuCode) {
        this.stuCode = stuCode;
    }

    public String getDormCode() {
        return dormCode;
    }

    public void setDormCode(String dormCode) {
        this.dormCode = dormCode;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getDormBuildId() {
        return dormBuildId;
    }

    public void setDormBuildId(Integer dormBuildId) {
        this.dormBuildId = dormBuildId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Integer getDisabled() {
        return disabled;
    }

    public void setDisabled(Integer disabled) {
        this.disabled = disabled;
    }

    public DormBuild getDormBuild() {
        return dormBuild;
    }

    public void setDormBuild(DormBuild dormBuild) {
        this.dormBuild = dormBuild;
    }

    public List<DormBuild> getDormBuilds() {
        return dormBuilds;
    }

    public void setDormBuilds(List<DormBuild> dormBuilds) {
        this.dormBuilds = dormBuilds;
    }
}
