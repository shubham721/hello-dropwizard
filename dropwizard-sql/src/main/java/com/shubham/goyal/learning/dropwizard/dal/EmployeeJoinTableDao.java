package com.shubham.goyal.learning.dropwizard.dal;

import com.google.inject.Inject;
import com.shubham.goyal.learning.dropwizard.dal.entities.EmployeeEntityJoinTable;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

public class EmployeeJoinTableDao extends AbstractDAO<EmployeeEntityJoinTable> {

    @Inject
    public EmployeeJoinTableDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public EmployeeEntityJoinTable save(EmployeeEntityJoinTable employeeEntity)
    {
        return persist(employeeEntity);
    }

    public EmployeeEntityJoinTable getEntity(int id){
        return get(id);
    }

}
