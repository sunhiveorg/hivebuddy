package com.sunhive.hivebuddy.services;

import com.sunhive.hivebuddy.data.ArduinoDataReceiver;
import com.fazecast.jSerialComm.SerialPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Timer;

@Service
public class ArduinoService {

    private final ArduinoDataReceiver dataReceiver;
    private final SensorDataService sensorDataService;

    @Autowired
    public ArduinoService(ArduinoDataReceiver dataReceiver, SensorDataService sensorDataService) {
        this.dataReceiver = dataReceiver;
        this.sensorDataService = sensorDataService;
    }

    //    @Override
    public void gatherData(){
        // SET ARDUINO PORT HERE
        // Mac Sofiia
        //var sp = SerialPort.getCommPort("/dev/cu.usbmodem112301");
        // Win Rossy
          var sp = SerialPort.getCommPort("COM6");

        sp.setComPortParameters(9600, Byte.SIZE, SerialPort.ONE_STOP_BIT, SerialPort.NO_PARITY);
        sp.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0);
//        sp.flushDataListener();
        var hasOpened = sp.openPort();

        if (!hasOpened) {
            throw new IllegalStateException("Failed to open serial port");
        }
        Runtime.getRuntime().addShutdownHook(new Thread(sp::closePort));
//        var timer = new Timer();

        sp.addDataListener(dataReceiver);
        System.out.println("Listen: " + dataReceiver.getListeningEvents());

        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
