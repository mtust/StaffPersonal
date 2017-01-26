package com.staff.personal.dto.nominallyJobBook;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.staff.personal.domain.Region;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by mtustanovskyy on 1/15/17.
 */
@Data
@JsonSerialize
public class PoorNominallyJobBookDTO {

    private Long id;

    private String name;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Region region;

    private String code;


}
