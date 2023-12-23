package com.sunhive.hivebuddy.data;

public class LoginDTO {
    private String hiveId;

    public LoginDTO(String hiveId) {
        this.hiveId = hiveId;
    }

    public String getHiveId() {
        return hiveId;
    }

    public void setHiveId(String hiveId) {
        this.hiveId = hiveId;
    }
}
