package com.shubham.goyal.learning.dropwizard.services;

import com.google.inject.Inject;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.shubham.goyal.learning.dropwizard.dal.interfaces.StudentRepostitory;
import com.shubham.goyal.learning.dropwizard.entities.Student;
import com.shubham.goyal.learning.dropwizard.services.interfaces.HelloWorldService;

public class HelloWorldServiceImpl implements HelloWorldService {

    private StudentRepostitory studentRepostitory;

    @Inject
    public HelloWorldServiceImpl(StudentRepostitory studentRepostitory){
        this.studentRepostitory = studentRepostitory;
    }

    @Override
    @HystrixCommand
    public int registerStudent(Student student) {
        Student student1 = studentRepostitory.registerStudent(student);
        return student1.getId();
    }

    @Override
    @HystrixCommand
    public Student fetchStudent(String name, String phone) throws RuntimeException {
        return studentRepostitory.fetchStudentByNameAndNumber(name, phone);
    }

}
