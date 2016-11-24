package com.staff.personal.service;

import com.staff.personal.dto.RestMessageDTO;
import com.staff.personal.dto.StaffDocumentDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Created by nazar on 24.11.16.
 */
public interface StaffDocumentsService {

    RestMessageDTO addDocument(MultipartFile multipartFile, Long id) throws IOException;

    List<StaffDocumentDTO> getDocumentsNames(Long id);

    byte[] getFile(String idStaff, String idDoc);
}
