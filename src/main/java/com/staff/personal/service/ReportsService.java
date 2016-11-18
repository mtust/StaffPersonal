package com.staff.personal.service;

import com.staff.personal.domain.Reports;
import com.staff.personal.dto.RestMessageDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Created by nazar on 18.11.16.
 */
public interface ReportsService {

    RestMessageDTO addReport(Long id, MultipartFile multipartFile, String name, String text) throws IOException;

    List<Reports> getReports(Long id);
}
