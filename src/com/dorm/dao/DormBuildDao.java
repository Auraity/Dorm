package com.dorm.dao;

import com.dorm.pojo.DormBuild;

import java.util.List;

public interface DormBuildDao {
    List<DormBuild> findDormBuildsByUserId(Integer id);

    List<DormBuild> findAllDormBuild();

    void saveManagerAndBuilds(Integer userId, String[] dormBuildIds);

    void deleteDormBuildsByUserId(Integer id);

    List<DormBuild> findDormBuilds(String id);

    void saveDormBuild(DormBuild dormBuild);

    DormBuild findDormBuildById(int id);

    void updateDormBuild(DormBuild dormBuild);

    List<DormBuild> findDormBuildsByBuildId(Integer id);

    void saveStudentAndBuilds(Integer userId, String dormBuildIds);
}
