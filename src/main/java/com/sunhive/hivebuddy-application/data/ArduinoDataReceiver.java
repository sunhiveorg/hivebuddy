package com.sunhive.hivebuddy.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
//import com.fazecast.jSerialComm.SerialPortEventListener;
import com.fazecast.jSerialComm.SerialPortEvent;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ArduinoDataReceiver implements SerialPortDataListener {
    private StringBuilder receivedDataBuffer = new StringBuilder();

    @Override
    public int getListeningEvents(){
        return SerialPort.LISTENING_EVENT_DATA_RECEIVED;
    }

    @Override
    public void serialEvent(SerialPortEvent serialPortEvent) {
        if (serialPortEvent.getEventType() == SerialPort.LISTENING_EVENT_DATA_RECEIVED) {
            byte[] receivedData = serialPortEvent.getReceivedData();
            String newDataChunk = new String(receivedData);

            // Append the new data chunk to the buffer
            receivedDataBuffer.append(newDataChunk);

            // Check if the buffer contains a complete message (ends with a newline)
            if (receivedDataBuffer.toString().endsWith("\n")) {
                // Process the complete message
                String completeMessage = receivedDataBuffer.toString().trim();
                System.out.println("Received Data: " + completeMessage);

                // Clear the buffer for the next message
                receivedDataBuffer.setLength(0);

                // TODO:
                // Process data for DB then save each entry using sensorDataService.addNewData(sensorData) like in SensorDataController
            }
        }
    }
}

//public class ArduinoDataReceiver implements SerialPortEventListener {
//    private List<ArduinoData> receivedDataList = new ArrayList<>();
//    private StringBuilder currentMessage = new StringBuilder();
//    private ObjectMapper objectMapper = new ObjectMapper();
//    private File jsonFile = new File("receivedData.json");
//
//    @Override
//    public void serialEvent(SerialPortEvent serialPortEvent) {
//        if (serialPortEvent.getEventType() == SerialPort.LISTENING_EVENT_DATA_RECEIVED) {
//            byte[] receivedData = serialPortEvent.getReceivedData();
//            String newDataChunk = new String(receivedData);
//
//            // Append the new data chunk to the current message
//            currentMessage.append(newDataChunk);
//
//            // Check if the chunk contains a complete message (ends with a newline)
//            if (newDataChunk.endsWith("\n")) {
//                // Process the complete message
//                String completeMessage = currentMessage.toString().trim();
//                System.out.println("Received Data: " + completeMessage);
//
//                // Parse the message and add to the list
//                ArduinoData arduinoData = parseArduinoData(completeMessage);
//                receivedDataList.add(arduinoData);
//
//                // Clear the current message for the next one
//                currentMessage.setLength(0);
//
//                // Save the list to the JSON file
//                saveDataToJsonFile();
//            }
//        }
//    }
//
//    private ArduinoData parseArduinoData(String message) {
//        String[] parts = message.split("\\s+\\|\\s+");
//        String time = parts[0].substring(6).trim();
//        String humidity = parts[1].substring(10).trim();
//        String temperature = parts[2].substring(13).trim();
//        return new ArduinoData(time, humidity, temperature);
//    }
//
//    private void saveDataToJsonFile() {
//        try {
//            objectMapper.writeValue(jsonFile, receivedDataList);
//            System.out.println("Data saved to JSON file: " + jsonFile.getAbsolutePath());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

//}
