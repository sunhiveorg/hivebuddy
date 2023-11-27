package com.sunhive.hivebuddy.config;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sunhive.hivebuddy.data.SensorData;
import com.sunhive.hivebuddy.repository.SensorDataRepository;

@Configuration
public class SensorDataConfig {
    // @Bean
    // CommandLineRunner commandLineRunner(SensorDataRepository repository){
    //     return  args -> {
    //         SensorData data1 = new SensorData(
    //                 1L,
    //                 1L,
    //                 3,
    //                 LocalDateTime.of(2000, Month.APRIL,4,8,32)
    //         );
    //         SensorData data2 = new SensorData(
    //                 1L,
    //                 1L,
    //                 3,
    //                 LocalDateTime.of(2001, Month.JANUARY,14,18,2)
    //         );

    //         repository.saveAll(
    //                 List.of(data1,data2)
    //         );
    //     };
    // }
}
