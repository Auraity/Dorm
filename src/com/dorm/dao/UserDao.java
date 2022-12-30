package com.dorm.dao;

import com.dorm.pojo.User;

import java.util.List;

public interface UserDao {
    User findUserByStuCodeAndPassword(String stuCode, String password);

    List<User> findDormManager();
    List<User> findDormManager(String s);

    Integer saveManager(User user);
    String findUserStuCodeMax();

    User findDormManagerById(int id);

    void updateUser(User user);

    List<User> findStudent();

    Integer saveStudent(User user);

    String findOldPassword(Integer id);

    void setNewPassword(String newPassword,Integer id);
}
