package com.sunhive.hivebuddy.services;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.sunhive.hivebuddy.data.SensorData;
import com.sunhive.hivebuddy.repository.SensorDataRepository;

@Service
public class SensorDataService {
    private final SensorDataRepository sensorDataRepository;

    @Autowired
    public SensorDataService(SensorDataRepository sensorDataRepository) {
        this.sensorDataRepository = sensorDataRepository;
    }

    public List<SensorData> getSensorDatas() {
        return sensorDataRepository.findAll();
    }

    public void addNewData(SensorData sensorData) {
        sensorData.setTimestamp(LocalDateTime.now());
        sensorDataRepository.save(sensorData);
        System.out.println(sensorData);
    }

    // public void addNewData(
    //         Long sensorTypeId,
    //         Long hiveId,
    //         int value) {
    //     SensorData sensorData;
    //     sensorData.setId(hiveId);
    //     // sensorDataRepository.save(sensorData);
    //     System.out.println(sensorData);
    // }
}