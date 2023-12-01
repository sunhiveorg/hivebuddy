package com.sunhive.hivebuddy;

/*
TODO: remove 4 commented lines below
 */

//<<<<<<< HEAD
////import com.sunhive.hivebuddy.data.ArduinoDataReceiver;
//=======

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

//>>>>>>> 8e603a3d869800e47a3ac041df4052ea93289670

import com.sunhive.hivebuddy.data.ArduinoDataReceiver;
import com.fazecast.jSerialComm.SerialPort;
import java.util.Timer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunhive.hivebuddy.data.SensorData;


@SpringBootApplication
public class HiveBuddyApplication {

    public static void main(String[] args) {
        SpringApplication.run(HiveBuddyApplication.class, args);
    }

}
