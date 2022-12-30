package com.dorm.service.impl;

import com.dorm.dao.DormBuildDao;
import com.dorm.dao.impl.DormBuildDaoImpl;
import com.dorm.pojo.DormBuild;
import com.dorm.service.DormBuildService;

import java.util.List;

public class DormBuildServiceImpl implements DormBuildService {
    DormBuildDao dormBuildDao=new DormBuildDaoImpl();
    @Override
    public List<DormBuild> getAllDormBuild() {
        return dormBuildDao.findAllDormBuild();
    }

    @Override
    public List<DormBuild> getDormBuildBuUserId(Integer id) {
        return dormBuildDao.findDormBuildsByUserId(id);
    }

    @Override
    public void deleteDormBuildsByUserId(Integer id) {
        dormBuildDao.deleteDormBuildsByUserId(id);
    }

    @Override
    public void addDormBuildByUserId(Integer id, String[] dormBuildIds) {
        dormBuildDao.saveManagerAndBuilds(id,dormBuildIds);
    }

    @Override
    public List<DormBuild> getDormBuilds(String id) {
        return dormBuildDao.findDormBuilds(id);
    }

    @Override
    public void saveDormBuild(DormBuild dormBuild) {
        dormBuildDao.saveDormBuild(dormBuild);
    }

    @Override
    public DormBuild getDormBuildById(int id) {
        return dormBuildDao.findDormBuildById(id);
    }

    @Override
    public void updateDormBuild(DormBuild dormBuild) {
        dormBuildDao.updateDormBuild(dormBuild);
    }
}
