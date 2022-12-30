package com.dorm.service;

import com.dorm.pojo.User;

import java.util.List;

public interface UserService {
    User findUserByStuCodeAndPassword(String stuCode, String password);

    List<User> findDormManager();

    List<User> findDormManager(String searchType, String keyword);

    void saveManagerAndBuilds(User user, String[] dormBuildIds);

    User getDormManagerById(int id);

    void updateManager(User user);

    List<User> getStudents();

    List<User> getStudents(String searchType, String keyword);

    void saveStudentAndBuilds(User user, String dormBuildIds);

    void changePassword(String oldPassword, String newPassword, Integer id);
}
