package com.shubham.goyal.learning.dropwizard.dal;

import com.shubham.goyal.learning.dropwizard.dal.entities.Student;
import org.mapstruct.Mapper;

@Mapper
public abstract class ServiceToDalMapper {

    public abstract Student serviceToMysqlStudentEntity(com.shubham.goyal.learning.dropwizard.entities.Student student);

    public abstract com.shubham.goyal.learning.dropwizard.entities.Student mysqlToServiceStudentEntity(Student student);

}
