package com.dorm.dao;

import com.dorm.pojo.Record;

import java.util.List;

public interface RecordDao {
    List<Record> findAllRecords();

    List<Record> findRecordsBySearchType(String startDate, String endDate, String dormBuildId, String searchType, String keyword);

    Record findRecordById(String id);

    int updateRecord(String id, String stuCode, String date, String remark);

    int addRecord(String stuCode, String date, String remark);

    Record findRecordByIds(int id);

    void updateRecordDisabled(Record record);
}
