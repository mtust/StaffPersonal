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

    @OneToMany
    private List<NominallyJobBook> nominallyJobBooks;

}
