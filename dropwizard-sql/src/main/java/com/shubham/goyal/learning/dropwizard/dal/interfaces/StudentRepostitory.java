package com.shubham.goyal.learning.dropwizard.dal.interfaces;


import com.shubham.goyal.learning.dropwizard.entities.Student;

public interface StudentRepostitory {

    Student registerStudent(Student student);

    Student fetchStudentByNameAndNumber(String name, String phone) throws RuntimeException;

}
