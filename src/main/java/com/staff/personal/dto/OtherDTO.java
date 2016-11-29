package com.staff.personal.dto;

import lombok.Data;

import javax.persistence.Column;

/**
 * Created by nazar on 14.11.16.
 */
@Data
public class OtherDTO {

    private String id;

    private String birthplace;

    private String sex;

    private String citizenship;

    private String seriesAndPassportNumber;

    private String seriesAndNumberOfOfficialIdentification;

    private String IdentificationCode;    //!!!!! STRING?

    private String placeOfResidence;

    private String phoneNumbers;     //!!!!!?

    private String maritalStatus;

    private String wifeHusband;

    private String children;
}
