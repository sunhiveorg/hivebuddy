package com.sunhive.hivebuddy.repository;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SensorsData {
    private static List<Double> numbers = new ArrayList<>();

    private final ResourceLoader resourceLoader;

    public SensorsData(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
        loadData();
    }

    private void loadData() {
        try {
            Resource resource = new ClassPathResource("resources/dataFile.txt");
            InputStream inputStream = resource.getInputStream();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    double number = Double.parseDouble(line.trim());
                    numbers.add(number);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error loading data from file", e);
        }
    }

    public static List<Double> getNumbers() {
        return numbers;
    }
}

