package com.shubham.goyal.learning.dropwizard.dal.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity(name = "ForeignKeyAssoManagerEntityJoin")
@Table(name = "Manager", uniqueConstraints = {
        @UniqueConstraint(columnNames = "ID"),
        @UniqueConstraint(columnNames = "EMAIL") })
@Getter
@Setter
public class ManagerEntityJoinTable implements Serializable {

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

    @Column(name = "phone")
    private String number;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinTable(name="EMPLOYEE_ACCOUNT", joinColumns={@JoinColumn(name="EMPLOYEE_ID", referencedColumnName="ID")}
            , inverseJoinColumns={@JoinColumn(name="ACCOUNT_ID", referencedColumnName="ID")})
    private Set<AccountEntityJoinTable> accounts;

    public String toString() {
        return "ManagerEntityJoinTable(super=" + super.toString() +
                ", employeeId=" + this.getEmployeeId() +
                ", email=" + this.getEmail() +
                ", firstName=" + this.getFirstName() +
                ", lastName=" + this.getLastName();
    }

    //Getters and setters
}