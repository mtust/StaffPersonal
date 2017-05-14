package com.staff.personal.domain.nominallyJobBooks;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.staff.personal.domain.Region;
import lombok.Data;
import lombok.Generated;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.List;

/**
 * Created by mtustanovskyy on 1/15/17.
 */
@Data
@Slf4j
@Entity
public class NominallyJobBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch= FetchType.LAZY, cascade= CascadeType.MERGE)
    private Region region;

    @Column(name = "code")
    private String code;


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    List<Position> positions;


}
