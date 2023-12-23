package com.sunhive.hivebuddy.data;

import com.sunhive.hivebuddy.services.SensorDataService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArduinoDataReceiver implements SerialPortDataListener {
    //private List<ArduinoData> receivedDataList = new ArrayList<>();
    private StringBuilder currentMessage = new StringBuilder();
    private ObjectMapper objectMapper = new ObjectMapper();
    private File jsonFile = new File("receivedData.json");
    private final SensorDataService sensorDataService;

    @Autowired
    public ArduinoDataReceiver(SensorDataService sensorDataService) {
        this.sensorDataService = sensorDataService;
    }

    public int getListeningEvents() {
        return SerialPort.LISTENING_EVENT_DATA_RECEIVED;
    }

    public void serialEvent(SerialPortEvent serialPortEvent) {
        if (serialPortEvent.getEventType() == SerialPort.LISTENING_EVENT_DATA_RECEIVED) {
            byte[] receivedData = serialPortEvent.getReceivedData();
            String newDataChunk = new String(receivedData);

            // Append the new data chunk to the current message
            currentMessage.append(newDataChunk);

            // Check if the chunk contains a complete message (ends with a newline)
            if (newDataChunk.contains("\r\n")) {
                // Process the complete message
                int lastIndex = currentMessage.lastIndexOf("\r\n");
                int preLastIndex = currentMessage.lastIndexOf("\r\n", lastIndex - 1);
                String completeMessage = currentMessage.toString().trim().substring(preLastIndex == -1 ? 0 : preLastIndex, lastIndex).replaceAll("\r\n", "").replaceAll("\"", "");
//                String completeMessage2 = completeMessage.substring(preLastIndex,lastIndex);
//                String completeMessage3 = completeMessage2.replace("\r\n","");
                System.out.println("Received Data: " + completeMessage);

                // Clear the current message for the next one
                currentMessage.setLength(0);

                // Parse the message and add to the list
                parseArduinoData(completeMessage);

                // Save the list to the JSON file
//                saveDataToJsonFile();
            }
        }
    }

    private void parseArduinoData(String message) {
        String[] data = message.split(";");
        String[] info = data[0].split(",");
        String[] parts = data[1].split(",");
        Long hive_id = Long.parseLong(String.valueOf(info[0]));
        List<SensorData> sensorDataList = new ArrayList<>();
        for (int i = 0; i < parts.length; i++) {
            // 1 - temperatureOut, 2 - temperatureIn, 3 - humidityIn, 4 - weight, 5 - mic
            SensorData sensorData = new SensorData((long) (i + 1), hive_id, Double.parseDouble(String.valueOf(parts[i])), LocalDateTime.now());
            saveDataToDatabase(sensorData);
            sensorDataList.add(sensorData);
//            sensorDataRepository.save(sensorData);
        }
    }

//    private void saveDataToJsonFile() {
//        try {
//            objectMapper.writeValue(jsonFile, receivedDataList);
//            System.out.println("Data saved to JSON file: " + jsonFile.getAbsolutePath());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    private void saveDataToDatabase(SensorData sensorData) {
        sensorDataService.addNewData(sensorData);
    }
}
