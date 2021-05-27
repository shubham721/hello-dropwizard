package com.shubham.goyal.learning.dropwizard.dal;

import com.google.inject.Inject;
import com.shubham.goyal.learning.dropwizard.dal.entities.EmployeeEntity;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

public class EmployeeDao extends AbstractDAO<EmployeeEntity> {

    @Inject
    public EmployeeDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public EmployeeEntity save(EmployeeEntity employeeEntity)
    {
       return persist(employeeEntity);
    }

    public EmployeeEntity getEntity(int id){
        return get(id);
    }

}
