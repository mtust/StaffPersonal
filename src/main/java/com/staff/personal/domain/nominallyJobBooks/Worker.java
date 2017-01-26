package com.staff.personal.domain.nominallyJobBooks;

import com.auth0.jwt.internal.com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by mtustanovskyy on 1/15/17.
 */

@Data
@Entity
public class Worker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "stateNumber")
    private String stateNumber;

    @Column
    private String SPK;

    @Column
    private String specialRank;

    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String fatherName;
    @Column
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date orderDate;
    @Column
    private String orderNumber;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch= FetchType.LAZY, cascade= CascadeType.MERGE)
    private Position position;


}
