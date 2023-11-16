package com.sunhive.hivebuddy.repository;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class SensorsData {
    private String data;
    //private InputStream inputStream = null;

    public Resource loadDataWithClassPathResource() {
        return new ClassPathResource("C:\\Users\\adein\\OneDrive\\Documents\\sunhive\\HiveBuddy\\src\\main\\resources\\dataFile.txt");
    }

//    @GetMapping("/id")
//    public String[] getData2(){
//        Resource resource = loadDataWithClassPathResource();
//        File file = null;
//        String[] array = null;
//        try {
//            file = resource.getFile();
//            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
//            array = bufferedReader.lines().collect(Collectors.joining()).split(",");
//            System.out.println(Arrays.asList(array));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        return array;
//    }
}
