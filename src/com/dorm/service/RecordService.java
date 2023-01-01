package com.dorm.service;

import com.dorm.pojo.Record;
import com.dorm.pojo.User;

import java.util.List;

public interface RecordService {
    List<Record> getAllRecords();

    List<Record> findRecordsBySearchType(String startDate, String endDate, String dormBuildId, String searchType, String keyword);

    Record findRecordById(String id);

    int updateRecord(String id, String stuCode, String date, String remark);

    int addRecord(String stuCode, String date, String remark);

    Record getRecordById(int id);

    void updateRecordDisabled(Record record);
}
