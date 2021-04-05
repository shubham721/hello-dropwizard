package com.shubham.goyal.learning.dropwizard.dal;

import com.google.inject.Inject;
import com.shubham.goyal.learning.dropwizard.dal.entities.Student;
import com.shubham.goyal.learning.dropwizard.dal.interfaces.StudentRepostitory;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class StudentRepositoryImpl extends AbstractDAO<Student> implements StudentRepostitory {

    private SessionFactory sessionFactory;

    private ServiceToDalMapper mapper;

    @Inject
    public StudentRepositoryImpl(SessionFactory sessionFactory, ServiceToDalMapper mapper) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
        this.mapper = mapper;
    }


    @Override
    public com.shubham.goyal.learning.dropwizard.entities.Student
    registerStudent(com.shubham.goyal.learning.dropwizard.entities.Student student) {
        return mapper.mysqlToServiceStudentEntity(persist(mapper.serviceToMysqlStudentEntity(student)));
    }

    @Override
    public com.shubham.goyal.learning.dropwizard.entities.Student
    fetchStudentByNameAndNumber(String name, String phone) throws RuntimeException {
        CriteriaBuilder builder = currentSession().getCriteriaBuilder();
        CriteriaQuery<Student> criteriaQuery = builder.createQuery(Student.class);
        Root<Student> queryRoot = criteriaQuery.from(Student.class);
        criteriaQuery.select(queryRoot);
        Predicate filters = builder.and(builder.equal(queryRoot.get("name"), name),
                builder.equal(queryRoot.get("phone"), phone));
        criteriaQuery.where(filters);
        Student student = uniqueResult(currentSession().createQuery(criteriaQuery));
        if(student == null){
            throw new RuntimeException(" No student found");
        }else {
            currentSession().evict(student);
        }
        return mapper.mysqlToServiceStudentEntity(student);
    }
}
