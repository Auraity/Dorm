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

    @Override
    public Record findRecordById(String id) {
        return recordDao.findRecordById(id);
    }

    @Override
    public int updateRecord(String id, String stuCode, String date, String remark) {
        return recordDao.updateRecord(id, stuCode, date, remark);
    }

    @Override
    public int addRecord(String stuCode, String date, String remark) {
        return recordDao.addRecord(stuCode,date,remark);
    }

    @Override
    public Record getRecordById(int id) {
        return recordDao.findRecordByIds(id);
    }

    @Override
    public void updateRecordDisabled(Record record) {
        recordDao.updateRecordDisabled(record);
    }
}
