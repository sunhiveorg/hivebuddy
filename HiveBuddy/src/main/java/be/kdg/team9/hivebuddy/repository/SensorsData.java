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

@RestController
//@RequestMapping("/data")
public class SensorsData {
    private String data;
    private InputStream inputStream = null;
//    public List<String> readData() {
//        return getData2();
//    }

    public Resource loadDataWithClassPathResource() {
        return new ClassPathResource("C:\\Users\\adein\\OneDrive\\Documents\\sunhive\\HiveBuddy\\src\\main\\resources\\templates\\dataFile.txt");
    }
    @GetMapping("/")
    public String sayHello() {
        return "Hello World!";
    }

    @GetMapping("/id")
    public String[] getData2(){
        Resource resource = loadDataWithClassPathResource();
        File file = null;
        String[] array = null;
        try {
            file = resource.getFile();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            array = bufferedReader.lines().collect(Collectors.joining()).split(",");
            System.out.println(Arrays.asList(array));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return array;
    }


//    private String readFromInputStream(InputStream inputStream)
//            throws IOException {
//        StringBuilder resultStringBuilder = new StringBuilder();
//        try (BufferedReader br
//                     = new BufferedReader(new InputStreamReader(inputStream))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                resultStringBuilder.append(line).append("\n");
//            }
//        }
//        return resultStringBuilder.toString();
//    }
//
//    public String getData() {
//        try {
//            File file = new File(classLoader.getResource("fileTest.txt").getFile());
//            inputStream = new FileInputStream(file);
//            data = readFromInputStream(inputStream);
//        }
//        finally {
//            if (inputStream != null) {
//                try {
//                    inputStream.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return data;
//    }
}
