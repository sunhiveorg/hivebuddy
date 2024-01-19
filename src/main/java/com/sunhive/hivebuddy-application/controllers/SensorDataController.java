package com.sunhive.hivebuddy.controllers;

import com.sunhive.hivebuddy.data.SensorData;
import com.sunhive.hivebuddy.services.SensorDataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossyOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = "api/v1/data", method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
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
    public List<SensorData> getSensorDataLatestById(@RequestParam Long id) {
        return sensorDataServiceImpl.getSensorDatasLatestById(id);
    }

    @PostMapping
    public void registerNewSensorData(@RequestBody SensorData sensorData) {
        sensorDataServiceImpl.showRealtime(sensorData);
        sensorDataServiceImpl.addNewData(sensorData);
    }

    @GetMapping(path = "/login/{hiveId}")
//    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> login(@PathVariable Long hiveId) {
        if (sensorDataServiceImpl.checkHiveIdExists(hiveId)) {
            return ResponseEntity.ok("{\"status\":\"OK\"}");
        } else {
            return ResponseEntity.notFound().build();
        }
//        return sensorDataServiceImpl.checkHiveIdExists(hiveId);
    }
}
