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

        var sp = SerialPort.getCommPort("/dev/cu.usbmodem112301");
        sp.setComPortParameters(9600, Byte.SIZE, SerialPort.ONE_STOP_BIT, SerialPort.NO_PARITY);
        sp.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0);
        var hasOpened = sp.openPort();

        if (!hasOpened) {
            throw new IllegalStateException("Failed to open serial port");
        }
        Runtime.getRuntime().addShutdownHook(new Thread(sp::closePort));
        var timer = new Timer();
        var timedSchedule = new ArduinoDataReceiver();

        ArduinoDataReceiver dataReceiver = new ArduinoDataReceiver();
        sp.addDataListener(dataReceiver);
        System.out.println("Listen: " + timedSchedule.getListeningEvents());

        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
