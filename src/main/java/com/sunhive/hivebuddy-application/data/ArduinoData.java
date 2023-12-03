package com.sunhive.hivebuddy.data;

public class ArduinoData {
    private String time;
    private String humidity;
    private String temperature;
    private String sound;
    private String weight;

    public ArduinoData(String time, String humidity, String temperature, String sound, String weight) {
        this.time = time;
        this.humidity = humidity;
        this.temperature = temperature;
        this.sound = sound;
        this.weight = weight;
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

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
