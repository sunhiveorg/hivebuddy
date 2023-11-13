package com.sunhive.hivebuddy.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Notification {
    private int id;
    private int type_id;
    private LocalDateTime timestamp;
    private String message;
    private int hive_id;

    public Notification(int id, int type_id, LocalDateTime timestamp, String message, int hive_id) {
        this.id = id;
        this.type_id = type_id;
        this.timestamp = timestamp;
        this.message = message;
        this.hive_id = hive_id;
    }

    public int getId() {
        return id;
    }

    public int getType_id() {
        return type_id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public int getHive_id() {
        return hive_id;
    }
}
