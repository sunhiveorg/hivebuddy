package com.sunhive.hivebuddy.services;

import com.sunhive.hivebuddy.data.SensorData;

import java.util.List;

public interface SensorDataService {
    public List<SensorData> getSensorDatas();
    public void addNewData(SensorData sensorData);
}
