package com.staff.personal.dto;

import com.staff.personal.domain.MainEducationBlock;
import lombok.Data;

import java.util.List;

/**
 * Created by nazar on 08.11.16.
 */
@Data
public class EducationDTO {

    List<MainEducationBlock> mainEducationBlocks;

    String otherStudying;

    String language;

}
