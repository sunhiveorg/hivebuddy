package com.sunhive.hivebuddy.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sunhive.hivebuddy.data.SensorData;
import com.sunhive.hivebuddy.services.SensorDataServiceImpl;

@CrossyOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = "api/v1/data")
public class SensorDataController {
    private final SensorDataServiceImpl sensorDataServiceImpl;

    @Autowired
    public SensorDataController(SensorDataServiceImpl sensorDataServiceImpl) {
        this.sensorDataServiceImpl = sensorDataServiceImpl;
    }

    @GetMapping
    public List<SensorData> getSensorData() {
        return sensorDataServiceImpl.getSensorDatas();
    }

    @GetMapping("/latest")
    public List<SensorData> getSensorDataLatestById(@RequestParam Long id){
        return sensorDataServiceImpl.getSensorDatasLatestById(id);
    }

    @PostMapping(
            consumes = "application/json", produces = "application/json")
    public void registerNewSensorData(@RequestBody SensorData sensorData) {
        sensorDataServiceImpl.addNewData(sensorData);
    }
}
