package com.sunhive.hivebuddy.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sunhive.hivebuddy.data.SensorData;
import com.sunhive.hivebuddy.services.SensorDataServiceImpl;
import org.json.JSONObject;

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
        sensorDataServiceImpl.showRealtime(sensorData);
        sensorDataServiceImpl.addNewData(sensorData);
    }

    @PostMapping(
            value = "/realtime", consumes = "application/json", produces = "application/json")
    public void showRealtime(@RequestBody JSONObject jsonObject) {
//        JSONArray jsonArray = (JSONArray) jsonObject;
//        Gson gson = new Gson();
//        List<SensorData> sensorData = new ArrayList<>();
//        for (Object data : jsonObject.getJSONArray("result")){
//           sensorData.add(gson.fromJson(data.toString(),SensorData.class));
//        }
//        sensorDataServiceImpl.showRealtime(sensorData);
    }
}
