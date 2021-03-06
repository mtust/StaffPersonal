package com.staff.personal.service;

import com.staff.personal.dto.GetReportsInfoDTO;
import com.staff.personal.dto.RestMessageDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by nazar on 18.11.16.
 */
public interface ReportsService {

    RestMessageDTO addReport(Long id, MultipartFile multipartFile, String name, String text) throws IOException;

    List<GetReportsInfoDTO> getReportsInfo(Long id);

    RestMessageDTO delReport(Long idS, Long idReports);

     byte[] getReportsFile(Long id, int idR) throws IOException, SQLException;
}
