package com.sunhive.hivebuddy.utils;

import com.sunhive.hivebuddy.data.ArduinoData;
import com.google.gson.Gson;

public class JsonParser {
    public ArduinoData stringToArduinoData(String json){

        Gson gson = new Gson();

        // 1. JSON file to Java object
//        Staff staff = gson.fromJson(new FileReader("C:\\projects\\staff.json"), Staff.class);

        // 2. JSON string to Java object
//        String json = "{'name' : 'mkyong'}";
        ArduinoData data = gson.fromJson(json, ArduinoData.class);

        // 3. JSON file to JsonElement, later String
//        JsonElement json = gson.fromJson(new FileReader("C:\\projects\\staff.json"), JsonElement.class);
//        String result = gson.toJson(json);
        return data;
    }
}
