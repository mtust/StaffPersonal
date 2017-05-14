package com.staff.personal.domain.nominallyJobBooks;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by mtustanovskyy on 1/20/17.
 */

@Data
@Entity
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column
    private String code;
    @Column
    private String comment;



}
