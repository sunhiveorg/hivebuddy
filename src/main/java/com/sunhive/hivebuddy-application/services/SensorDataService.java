package com.sunhive.hivebuddy.services;

import com.sunhive.hivebuddy.data.SensorData;
import jakarta.xml.bind.util.JAXBSource;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface SensorDataService {
    public List<SensorData> getSensorDatas();
    public void addNewData(SensorData sensorData);
    public void showRealtime(SensorData sensorData);
//    public List<Object[]> getDataFrom(String sensorID, Date startDate);
    public void createCSVForSensorData(String sensorID, LocalDateTime startDate);
}
