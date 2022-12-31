package com.dorm.service.impl;

import com.dorm.dao.RecordDao;
import com.dorm.dao.impl.RecordDaoImpl;
import com.dorm.pojo.Record;
import com.dorm.service.RecordService;

import java.util.List;

public class RecordServiceImpl implements RecordService {
    RecordDao recordDao=new RecordDaoImpl();
    @Override
    public List<Record> getAllRecords() {
        return recordDao.findAllRecords();
    }

    @Override
    public List<Record> findRecordsBySearchType(String startDate, String endDate, String dormBuildId, String searchType, String keyword) {
        return recordDao.findRecordsBySearchType(startDate,endDate,dormBuildId,searchType,keyword);
    }
}
