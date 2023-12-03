package com.sunhive.hivebuddy.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sunhive.hivebuddy.data.SensorData;

@Repository
public interface SensorDataRepository extends JpaRepository<SensorData,Long> {
//    List<SensorData> findAllLatestByHiveId(Long hive_id);
    @Query("select a from SensorsData a where a.timestamp <= :creationDateTime and a.hiveId = :hive_id ORDER BY timestamp DESC LIMIT 5")
    List<SensorData> findAllLatestByHiveId(Long hive_id,
            @Param("creationDateTime") LocalDateTime creationDateTime);
}
