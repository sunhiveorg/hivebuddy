package com.sunhive.hivebuddy.controllers;

import com.sunhive.hivebuddy.data.SensorData;
import com.sunhive.hivebuddy.data.TextMessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.List;

@RestController
public class WebSocketTextController {

    @Autowired
    SimpMessagingTemplate template;

    @PostMapping("/send")
//    public ResponseEntity<Void> sendMessage(@RequestBody TextMessageDTO textMessageDTO) {
    public ResponseEntity<Void> sendMessage(@RequestBody SensorData sensorData) {
//        TextMessageDTO textMessageDTO = new TextMessageDTO();
//        textMessageDTO.setMessage(String.valueOf(sensorDataList.getValue()));
//        String[][] realtimeData = new String[sensorDataList.size()][2];
//        for (int i = 0; i < sensorDataList.size(); i++) {
//            realtimeData[i][1] = String.valueOf(sensorDataList.get(i).getsensorTypeId());
//            realtimeData[i][2] = String.valueOf(sensorDataList.get(i).getValue());
//        }
//        template.convertAndSend("/overview/"+sensorDataList.get(0).gethiveId(), realtimeData);
//        template.convertAndSend("/overview/"+sensorDataList.get(0).gethiveId(), sensorDataList);
        template.convertAndSend("/topic/overview/1/1", sensorData);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @MessageMapping("/sendMessage")
    public void receiveMessage(@Payload TextMessageDTO textMessageDTO) {
        // receive message from client
    }


    @SendTo("/topic/overview/1/1")
//    public String[][] broadcastMessage(@Payload String[][] realtimeData) {
//        return realtimeData;
//    }
//    public SensorData broadcastMessage(@Payload SensorData sensorData) {
//        return sensorData;
//    }
    public String broadcastMessage(@Payload SensorData sensorData) {
        return sensorData.toString();
    }
}