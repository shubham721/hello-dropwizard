package com.shubham.goyal.learning.dropwizard.dal.entities;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Table(name = "students")
@Data
public class Student {

    @Id
    @GeneratedValue(generator = "sequence-generator")
    // Identity Generation disable batch updates.
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

}
