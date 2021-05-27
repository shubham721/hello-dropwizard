package com.shubham.goyal.learning.dropwizard.dal.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "ForeignKeyAssoAccountEntity")
@Table(name = "ACCOUNT", uniqueConstraints = {
        @UniqueConstraint(columnNames = "ID")})
@Getter
@Setter
public class AccountEntity implements Serializable
{
    private static final long serialVersionUID = -6790693372846798580L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Integer accountId;

    @Column(name = "ACC_NUMBER", unique = true, nullable = false, length = 100)
    private String accountNumber;

    @ManyToOne
    private EmployeeEntity employee;

    public String toString() {
        return "AccountEntity(accountId=" + this.getAccountId() + ", accountNumber=" + this.getAccountNumber() + ", ";
    }

    //Getters and setters
}
