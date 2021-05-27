package com.shubham.goyal.learning.dropwizard.dal.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity(name = "ForeignKeyAssoEntity")
@Table(name = "Employee", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"ID", "EMAIL"}),
        @UniqueConstraint(columnNames = "EMAIL") })
@Getter
@Setter
public class EmployeeEntity implements Serializable {

    private static final long serialVersionUID = -1798070786993154676L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Integer employeeId;

    @Column(name = "EMAIL", unique = true, nullable = false, length = 100)
    private String email;

    @Column(name = "FIRST_NAME", unique = false, nullable = false, length = 100)
    private String firstName;

    @Column(name = "LAST_NAME", unique = false, nullable = false, length = 100)
    private String lastName;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="EMPLOYEE_ID")
    @JsonIgnore
    private Set<AccountEntity> accounts;

    public String toString() {
        return "EmployeeEntity(super=" + super.toString() +
                ", employeeId=" + this.getEmployeeId() +
                ", email=" + this.getEmail() +
                ", firstName=" + this.getFirstName() +
                ", lastName=" + this.getLastName();
    }

    //Getters and setters
}
