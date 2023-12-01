package com.sunhive.hivebuddy.data;

public class ArduinoData {
    private String time;
    private String humidity;
    private String temperature;

    public ArduinoData(String time, String humidity, String temperature) {
        this.time = time;
        this.humidity = humidity;
        this.temperature = temperature;
    }

    public String getTime() {
        return time;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }
}
