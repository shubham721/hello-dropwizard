package com.shubham.goyal.learning.dropwizard.services;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.shubham.goyal.learning.dropwizard.dal.interfaces.StudentRepostitory;
import com.shubham.goyal.learning.dropwizard.entities.MapBinderExperimentKey;
import com.shubham.goyal.learning.dropwizard.entities.Student;
import com.shubham.goyal.learning.dropwizard.services.interfaces.HelloWorldService;
import com.shubham.goyal.learning.dropwizard.services.interfaces.MapBinderInterfaceExperiment;

import java.util.Map;

public class HelloWorldServiceImpl implements HelloWorldService {

    private StudentRepostitory studentRepostitory;

    private Map<MapBinderExperimentKey, Provider<MapBinderInterfaceExperiment> > binderMap;

    @Inject
    public HelloWorldServiceImpl(StudentRepostitory studentRepostitory,
                                 Map<MapBinderExperimentKey, Provider<MapBinderInterfaceExperiment> > binderMap){
        this.studentRepostitory = studentRepostitory;
        this.binderMap = binderMap;
    }

    @Override
    @HystrixCommand
    public int registerStudent(Student student) {
        Student student1 = studentRepostitory.registerStudent(student);
        MapBinderExperimentKey key = new MapBinderExperimentKey("shubham", "goyal");
        MapBinderInterfaceExperiment experiment = binderMap.get(key).get();
        return student1.getId();
    }

    @Override
    @HystrixCommand
    public Student fetchStudent(String name, String phone) throws RuntimeException {
        return studentRepostitory.fetchStudentByNameAndNumber(name, phone);
    }

}
