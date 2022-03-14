package com.company;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JavaObjectToJSON {
    public static void main(String[] args) throws JsonProcessingException {
        Car car = new Car();
        ObjectMapper objectMapper = new ObjectMapper();

        car.setBrand("Volkswagen");
        car.setModel("Golf");
        car.setManufacturerYear(2018);

        String carJSON = objectMapper.writeValueAsString(car);
        System.out.println("Model as JSON: " + carJSON);
    }
}
