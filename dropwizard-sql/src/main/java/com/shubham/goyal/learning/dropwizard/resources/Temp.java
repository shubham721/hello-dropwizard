package com.shubham.goyal.learning.dropwizard.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shubham.goyal.learning.dropwizard.dal.entities.EmployeeEntity;

public class Temp  {

    public static void main(String[] args) throws JsonProcessingException {

        String temp = "{\n" +
                "\t\"email\": \"demo-user@mail.com\",\n" +
                "\t\"firstName\": \"demo\",\n" +
                "\t\"lastName\": \"user\",\n" +
                "\t\"accounts\": [\n" +
                "\t\t{\n" +
                "\t\t\t\"accountNumber\": \"123-345-65454\"\t\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"accountNumber\": \"123-345-65455\"\n" +
                "\t\t}\n" +
                "\t]\n" +
                "}";
        EmployeeEntity entity = new ObjectMapper().readValue(temp, EmployeeEntity.class);
        System.out.println(entity);
    }

}
