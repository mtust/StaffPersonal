package com.staff.personal.service;

import com.staff.personal.dto.RestMessageDTO;
import com.staff.personal.dto.StaffDocumentDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface StaffDocumentsService {

    RestMessageDTO addDocument(MultipartFile multipartFile, Long id, String name) throws IOException;

    RestMessageDTO addLustration(MultipartFile multipartFile, Long id, String name) throws IOException;

    RestMessageDTO addSpecPerevirka(MultipartFile multipartFile, Long id, String name) throws IOException;

    RestMessageDTO addDeklaration(MultipartFile multipartFile, Long id, String name) throws IOException;

    List<StaffDocumentDTO> getDocumentsInfo(Long id);

    byte[] getFile(Long idStaff, int idDoc)throws IOException, SQLException;

    RestMessageDTO delDocument(Long id, Long idDoc);

    List<StaffDocumentDTO> getLustration(Long id);

    List<StaffDocumentDTO> getSpecPerevirka(Long id);

    List<StaffDocumentDTO> getDeklaration(Long id);

    RestMessageDTO addEducation(MultipartFile multipartFile, Long id, String name) throws IOException;

    List<StaffDocumentDTO> getEducation(Long id);

    RestMessageDTO changeName(String name, Long id);
}
