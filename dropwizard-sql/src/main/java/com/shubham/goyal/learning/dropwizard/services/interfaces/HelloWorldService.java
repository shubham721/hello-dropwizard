package com.shubham.goyal.learning.dropwizard.services.interfaces;

import com.shubham.goyal.learning.dropwizard.entities.Student;

public interface HelloWorldService {

    int registerStudent(Student student);

    Student fetchStudent(String name, String phone) throws RuntimeException;
}
