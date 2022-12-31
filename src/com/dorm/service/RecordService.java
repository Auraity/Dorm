package com.dorm.service;

import com.dorm.pojo.Record;

import java.util.List;

public interface RecordService {
    List<Record> getAllRecords();

    List<Record> findRecordsBySearchType(String startDate, String endDate, String dormBuildId, String searchType, String keyword);
}
