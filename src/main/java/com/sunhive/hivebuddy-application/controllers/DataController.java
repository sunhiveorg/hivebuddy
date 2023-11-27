package com.sunhive.hivebuddy.controllers;

import com.sunhive.hivebuddy.services.TemperatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/data")
public class DataController {
    private final TemperatureService temperatureService;

    @Autowired
    public DataController(TemperatureService temperatureService) {
        this.temperatureService = temperatureService;
    }
}
