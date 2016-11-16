package com.staff.personal.dto;

import lombok.Data;


/**
 * Created by nazar on 16.11.16.
 */
@Data
public class FiredDTO {

    private String id;

    private String dateFiring;

    private String orderNumber;
    /**
     *інформація, куди саме
     */
    private String whereFired;
    /**
     *Пункт (стаття) звільнення
     */
    private String article;

    private String lastPosition;

    /**
     *Спеціальне звання
     */
    private String specialRank;
    /**
     *Військовий облік
     */
    private String militaryAccount ;
    /**
     *Довідка ЦЛЕК (ЛЕК) (свідоцтво) (дата, номер)
     */
    private String referenceLEKCertificate;//??????????mb Document??
    /**
     *Довідка ЦЛЕК (ЛЕК) (свідоцтво) (дата, номер)
     */
    private String referenceLEKDate;
    /**
     *Довідка ЦЛЕК (ЛЕК) (свідоцтво) (дата, номер)
     */
    private String referenceLEKNumber;

    private String conclusion;
    /**
     *Вислуга років на стан звільнення: календарна; навчання у ВНЗ; пільгова; трудовий стаж
     */
    private String seniority;

    /**
     *Особову справу направлено до (куди саме)
     */
    private String personalFileForwarded;
}
