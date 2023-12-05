package com.sunhive.hivebuddy.data;

import com.sunhive.hivebuddy.repository.SensorDataRepository;
import com.sunhive.hivebuddy.services.SensorDataService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
//import com.fazecast.jSerialComm.SerialPortEventListener;
import com.fazecast.jSerialComm.SerialPortEvent;
//import com.google.gson.Gson;
//import com.google.gson.JsonElement;
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

//public class ArduinoDataReceiver implements SerialPortDataListener {
////    Logger logger = (Logger) LoggerFactory.getLogger(ArduinoDataReceiver.class);
//    private StringBuilder receivedDataBuffer = new StringBuilder();
//
//    @Override
//    public int getListeningEvents(){
//        return SerialPort.LISTENING_EVENT_DATA_RECEIVED;
//    }
//
//    @Override
//    public void serialEvent(SerialPortEvent serialPortEvent) {
//        if (serialPortEvent.getEventType() == SerialPort.LISTENING_EVENT_DATA_RECEIVED) {
//            byte[] receivedData = serialPortEvent.getReceivedData();
//            String newDataChunk = new String(receivedData);
////            ArduinoData arduinoData =
////            stringToArduinoData(newDataChunk);
////            logger.log(logger., data);
//
//            // Append the new data chunk to the buffer
////            receivedDataBuffer.setLength(0);
//            receivedDataBuffer.append(newDataChunk);
//
//            // Check if the buffer contains a complete message (ends with a newline)
//            if (receivedDataBuffer.toString().endsWith("\n")) {
//                // Process the complete message
//                String completeMessage = receivedDataBuffer.toString().trim();
////                System.out.println("0: " + completeMessage);
////                stringToArduinoData(completeMessage);
////                stringToArduinoData(receivedDataBuffer.toString());
//                JsonObject jobj = new Gson().fromJson(completeMessage, JsonElement.class).getAsJsonObject();
//                System.out.println(jobj.toString());
//
//                // Clear the buffer for the next message
//                receivedDataBuffer.setLength(0);
//
//                // TODO:
//                // Process data for DB then save each entry using sensorDataService.addNewData(sensorData) like in SensorDataController
//            }
//        }
//    }
//
//    public void stringToArduinoData(String json){
//
////        System.out.println("1: " + json);
//        Gson gson = new Gson();
//
//        JsonParser jsonParser = new JsonParser();
//        JsonObject jo = (JsonObject)jsonParser.parse(json);
////
//        JsonElement jelem = gson.fromJson(json, JsonElement.class);
//        JsonObject jobj = jelem.getAsJsonObject();
//
////        JsonObject jobj = gson.fromJson(json, JsonElement.class).getAsJsonObject();
//
//        JsonObject jsonObj = JsonParser.parseString(json).getAsJsonObject();
//
//        System.out.println("2: " + jobj.getAsString());
////        Assert.assertNotNull(jo);
////        Assert.assertTrue(jo.get("Success").getAsString());
//
//
//        // 1. JSON file to Java object
////        Staff staff = gson.fromJson(new FileReader("C:\\projects\\staff.json"), Staff.class);
//
//        // 2. JSON string to Java object
////        json = "{\"nam\":\"mkyong\"}";
////        ArduinoData data = gson.fromJson(json, ArduinoData.class);
////        System.out.println(data);
//
////        ArduinoData jsonNew = gson.fromJson(json, ArduinoData.class);
////
////        String jsonInString = gson.toJson(jsonNew);
//
////        System.out.println("2: " + jsonInString);
//
//        // 3. JSON file to JsonElement, later String
////        JsonElement json = gson.fromJson(new FileReader("C:\\projects\\staff.json"), JsonElement.class);
////        String result = gson.toJson(json);
//        return;
//    }
//}

@Service
//public class ArduinoDataReceiver implements SerialPortEventListener {
public class ArduinoDataReceiver implements SerialPortDataListener {
    private List<ArduinoData> receivedDataList = new ArrayList<>();
    private StringBuilder currentMessage = new StringBuilder();
    private ObjectMapper objectMapper = new ObjectMapper();
    private File jsonFile = new File("receivedData.json");
    private final SensorDataService sensorDataService;
    @Autowired
    public ArduinoDataReceiver(SensorDataService sensorDataService) {
        this.sensorDataService = sensorDataService;
    }

//    @Override
    public int getListeningEvents() {
//        return 0;
        return SerialPort.LISTENING_EVENT_DATA_RECEIVED;
    }

//    @Override
    public void serialEvent(SerialPortEvent serialPortEvent) {
//        StringBuilder currentMessage = new StringBuilder();
        if (serialPortEvent.getEventType() == SerialPort.LISTENING_EVENT_DATA_RECEIVED) {
            byte[] receivedData = serialPortEvent.getReceivedData();
            String newDataChunk = new String(receivedData);

            // Append the new data chunk to the current message
            currentMessage.append(newDataChunk);

            // Check if the chunk contains a complete message (ends with a newline)
//            if (newDataChunk.contains("\n")) {
            if (newDataChunk.contains("\r\n")) {
//            if (newDataChunk.indexOf("\r\n",1) == -1) {
                // Process the complete message
                int lastIndex = currentMessage.lastIndexOf("\r\n");
                int preLastIndex = currentMessage.lastIndexOf("\r\n",lastIndex-1);
//                String completeMessage = currentMessage.toString().trim();
                String completeMessage = currentMessage.toString().trim().substring(preLastIndex == -1 ? 0 : preLastIndex,lastIndex).replaceAll("\r\n","").replaceAll("\"","");
//                String completeMessage2 = completeMessage.substring(preLastIndex,lastIndex);
//                String completeMessage3 = completeMessage2.replace("\r\n","");
                System.out.println("Received Data: " + completeMessage);

                // Clear the current message for the next one
                currentMessage.setLength(0);

                // Parse the message and add to the list
                parseArduinoData(completeMessage);
//                ArduinoData arduinoData = parseArduinoData(completeMessage);
//                receivedDataList.add(arduinoData);


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
        for (int i = 0; i < parts.length; i++){
            // 1 - temperatureOut, 2 - temperatureIn, 3 - humidityIn, 4 - weight, 5 - mic
            SensorData sensorData = new SensorData((long) (i + 1),hive_id,Double.parseDouble(String.valueOf(parts[i])),LocalDateTime.now());
//            System.out.println(i + ": " + sensorData.getValue());
            saveDataToDatabase(sensorData);
            sensorDataList.add(sensorData);
//            sensorDataRepository.save(sensorData);
        }
        showRealtime(sensorDataList);
//        String time = parts[0];
//        String humidity = parts[1];
//        String temperature = parts[2];
//        String time = parts[0].substring(6).trim();
//        String humidity = parts[1].substring(10).trim();
//        String temperature = parts[2].substring(13).trim();
//        return new ArduinoData(time, humidity, temperature);
    }

//    private void saveDataToJsonFile() {
//        try {
//            objectMapper.writeValue(jsonFile, receivedDataList);
//            System.out.println("Data saved to JSON file: " + jsonFile.getAbsolutePath());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    private void saveDataToDatabase(SensorData sensorData){
        sensorDataService.addNewData(sensorData);
    }

    private void showRealtime(List<SensorData> sensorDataList){
        sensorDataService.showRealtime(sensorDataList);
    }
}
