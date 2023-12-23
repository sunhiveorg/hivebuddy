package com.sunhive.hivebuddy.controllers;

import com.sunhive.hivebuddy.data.LoginDTO;
import com.sunhive.hivebuddy.data.SensorData;
import com.sunhive.hivebuddy.services.SensorDataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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

    @PostMapping
    public void registerNewSensorData(@RequestBody SensorData sensorData) {
        sensorDataServiceImpl.addNewData(sensorData);
    }
    @PostMapping(path = "/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO)
    {
        String hiveId = loginDTO.getHiveId();

        List<SensorData> hiveIds = sensorDataServiceImpl.getAllHiveIds();
        if (hiveIds.contains(Long.parseLong(hiveId))) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid  ID");
        }
    }
}
