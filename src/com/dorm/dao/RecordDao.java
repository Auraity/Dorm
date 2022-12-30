package com.dorm.dao;

import com.dorm.pojo.Record;

import java.util.List;

public interface RecordDao {
    List<Record> findAllRecords();
}
