package com.staff.personal.domain.nominallyJobBooks;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Created by mtustanovskyy on 1/22/17.
 */
@Data
@Entity
public class NominallyJobBookParent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "stateNumber")
    private String stateNumber;

    @Column(name = "startAction")
    private String startAction;

    @Column(name = "address")
    private String address;

    @Column(name = "location")
    private String location;

    @OneToMany
    private List<NominallyJobBook> nominallyJobBooks;

}
