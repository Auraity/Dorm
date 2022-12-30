package com.dorm.service;

import com.dorm.pojo.DormBuild;

import java.util.List;

public interface DormBuildService {
    List<DormBuild> getAllDormBuild();

    List<DormBuild> getDormBuildBuUserId(Integer id);

    void deleteDormBuildsByUserId(Integer id);

    void addDormBuildByUserId(Integer id, String[] dormBuildIds);

    List<DormBuild> getDormBuilds(String id);

    void saveDormBuild(DormBuild dormBuild);

    DormBuild getDormBuildById(int id);

    void updateDormBuild(DormBuild dormBuild);
}
