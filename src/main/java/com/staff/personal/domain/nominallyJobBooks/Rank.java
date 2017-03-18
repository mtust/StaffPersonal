package com.staff.personal.domain.nominallyJobBooks;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Rank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column
    private String code;
    @Column
    private String comment;

}
