package com.sunhive.hivebuddy.data;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity(name="SensorsData")
@Table(name="sensor_data")
public class SensorData {
    @Id
    @SequenceGenerator(
            name = "date_sequence",
            sequenceName = "data_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "data_sequence"
    )
    @Column(
        name = "id",
        updatable = false
    )
    private Long id;
    
    @Column(
        name = "sensor_type_id",
        nullable = false,
        columnDefinition = "BIGINT"
    )
    private Long sensorTypeId;

    
    @Column(
        name = "hive_id",
        nullable = false,
        columnDefinition = "BIGINT"
    )
    private Long hiveId;

    @Column(
        name = "value",
        nullable = false,
        columnDefinition = "INT"
    )
    private int value;

    @Column(
        name = "timestamp",
        nullable = false,
        columnDefinition = "TIMESTAMP"
    )
    private LocalDateTime timestamp;

    public SensorData() {
    }

    public SensorData(Long id, Long sensorTypeId, Long hiveId, int value, LocalDateTime timestamp) {
        this.id = id;
        this.sensorTypeId = sensorTypeId;
        this.hiveId = hiveId;
        this.value = value;
        this.timestamp = timestamp;
    }

    public SensorData(Long sensorTypeId, Long hiveId, int value, LocalDateTime timestamp) {
        this.sensorTypeId = sensorTypeId;
        this.hiveId = hiveId;
        this.value = value;
        this.timestamp = timestamp;
    }

    // for debug http requests
    public SensorData(Long sensorTypeId, Long hiveId, int value) {
        this.sensorTypeId = sensorTypeId;
        this.hiveId = hiveId;
        this.value = value;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getsensorTypeId() {
        return this.sensorTypeId;
    }

    public void setsensorTypeId(Long sensorTypeId) {
        this.sensorTypeId = sensorTypeId;
    }

    public Long gethiveId() {
        return this.hiveId;
    }

    public void sethiveId(Long hiveId) {
        this.hiveId = hiveId;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public LocalDateTime getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", sensorTypeId='" + getsensorTypeId() + "'" +
            ", hiveId='" + gethiveId() + "'" +
            ", value='" + getValue() + "'" +
            ", timestamp='" + getTimestamp() + "'" +
            "}";
    }

}
