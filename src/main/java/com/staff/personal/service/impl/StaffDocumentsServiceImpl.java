package com.staff.personal.service.impl;

import com.staff.personal.domain.MainStaff;
import com.staff.personal.domain.Staff;
import com.staff.personal.domain.StuffDocuments;
import com.staff.personal.dto.RestMessageDTO;
import com.staff.personal.dto.StaffDocumentDTO;
import com.staff.personal.exception.ObjectDoNotExistException;
import com.staff.personal.repository.StaffRepository;
import com.staff.personal.repository.StuffDocumentsRepository;
import com.staff.personal.service.StaffDocumentsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nazar on 24.11.16.
 */
@Slf4j
@Service
public class StaffDocumentsServiceImpl implements StaffDocumentsService {
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private StuffDocumentsRepository stuffDocumentsRepository;

    @Override
    @Transactional
    public RestMessageDTO addDocument(MultipartFile multipartFile, Long id) throws IOException {
        Staff staff = staffRepository.findOne(id);
        if (staff == null || staff.getIsDeleted() == true) {
            throw new ObjectDoNotExistException("staff object with id = " + id + " dosen't exist or is deleted");
        }
        StuffDocuments stuffDocuments = new StuffDocuments();
        stuffDocuments.setFile(multipartFile.getBytes());
        stuffDocuments.setName(multipartFile.getOriginalFilename());
        MainStaff mainStaff = staff.getMainStaff();
        List<StuffDocuments> list = mainStaff.getDocuments();
        list.add(stuffDocuments);
        mainStaff.setDocuments(list);
        staff.setMainStaff(mainStaff);
        staffRepository.save(staff);
        log.info(staff.toString());
        return new RestMessageDTO("Succes", true);
    }

    @Override
    @Transactional
    public List<StaffDocumentDTO> getDocumentsInfo(Long id) {
        Staff staff = staffRepository.findOne(id);
        if (staff == null || staff.getIsDeleted() == true) {
            throw new ObjectDoNotExistException("staff object with id = " + id + " dosen't exist or is deleted");
        }
        List<StaffDocumentDTO> documentDTOs = new ArrayList<>();
        List<StuffDocuments> list = staff.getMainStaff().getDocuments();
        for (StuffDocuments stuffDocuments : list) {
            StaffDocumentDTO documentDTO = new StaffDocumentDTO();
            documentDTO.setName(stuffDocuments.getName());
            documentDTO.setId(stuffDocuments.getId().toString());
            documentDTOs.add(documentDTO);
        }
        return documentDTOs;
    }

    @Override
    @Transactional
    public byte[] getFile(Long idStaff, int idDoc) throws IOException, SQLException {
        Staff staff = staffRepository.findOne(idStaff);
        if (staff == null || staff.getIsDeleted() == true) {
            throw new ObjectDoNotExistException("staff object with id = " + idStaff + " dosen't exist or is deleted");
        }
        List<StuffDocuments> list = staff.getMainStaff().getDocuments();
        StuffDocuments stuffDocuments = list.stream().filter(el -> el.getId() == idDoc).findFirst().get();
        return stuffDocuments.getFile();
    }

    @Override
    @Transactional
    public RestMessageDTO delDocument(Long id, Long idDoc) {
        Staff staff = staffRepository.findOne(id);
        if (staff == null || staff.getIsDeleted() == true) {
            throw new ObjectDoNotExistException("staff object with id = " + id + " dosen't exist or is deleted");
        }
        List<StuffDocuments> list = staff.getMainStaff().getDocuments();
        for (StuffDocuments stuffDocument : list) {
            if (stuffDocument.getId() == idDoc) {
                list.remove(stuffDocument);
                stuffDocumentsRepository.delete(stuffDocument);
                break;
            }
        }
        return new RestMessageDTO("Succes", true);
    }

    @Override
    @Transactional
    public List<StaffDocumentDTO> getLustration(Long id) {
        return getDocumentByType(id, "L");
    }

    @Override
    @Transactional
    public List<StaffDocumentDTO> getSpecPerevirka(Long id) {
        return getDocumentByType(id, "S");
    }

    @Override
    @Transactional
    public List<StaffDocumentDTO> getDeklaration(Long id) {
        return getDocumentByType(id, "D");
    }

    @Override
    public RestMessageDTO addLustration(MultipartFile multipartFile, Long id) throws IOException {
        return addDocumentByType(multipartFile, id, "L");
    }

    @Override
    public RestMessageDTO addSpecPerevirka(MultipartFile multipartFile, Long id) throws IOException {
        return addDocumentByType(multipartFile, id, "S");
    }

    @Override
    public RestMessageDTO addDeklaration(MultipartFile multipartFile, Long id) throws IOException {
        return addDocumentByType(multipartFile, id, "D");
    }


    @Override
    public RestMessageDTO addEducation(MultipartFile multipartFile, Long id) throws IOException {
        return addDocumentByType(multipartFile, id, "E");
    }


    private RestMessageDTO addDocumentByType(MultipartFile multipartFile, Long id, String docType) throws IOException{
        Staff staff = staffRepository.findOne(id);
        if (staff == null || staff.getIsDeleted() == true) {
            throw new ObjectDoNotExistException("staff object with id = " + id + " dosen't exist or is deleted");
        }
        StuffDocuments stuffDocuments = new StuffDocuments();
        stuffDocuments.setFile(multipartFile.getBytes());
        stuffDocuments.setName(multipartFile.getOriginalFilename());
        stuffDocuments.setType(docType);
        MainStaff mainStaff = staff.getMainStaff();
        List<StuffDocuments> list = mainStaff.getDocuments();
        list.add(stuffDocuments);
        mainStaff.setDocuments(list);
        staff.setMainStaff(mainStaff);
        staffRepository.save(staff);
        log.info(staff.toString());
        return new RestMessageDTO("Success", true);
    }


    @Override
    @Transactional
    public List<StaffDocumentDTO> getEducation(Long id){
        return getDocumentByType(id, "E");
    }


    private List<StaffDocumentDTO> getDocumentByType(Long id, String docType){
        Staff staff = staffRepository.findOne(id);
        if (staff == null || staff.getIsDeleted() == true) {
            throw new ObjectDoNotExistException("staff object with id = " + id + " dosen't exist or is deleted");
        }
        List<StaffDocumentDTO> documentDTOs = new ArrayList<>();
        List<StuffDocuments> list = staff.getMainStaff().getDocuments();
        for (StuffDocuments stuffDocuments : list) {
            if(stuffDocuments.getType().equalsIgnoreCase(docType)) {
                StaffDocumentDTO documentDTO = new StaffDocumentDTO();
                documentDTO.setName(stuffDocuments.getName());
                documentDTO.setId(stuffDocuments.getId().toString());
                documentDTOs.add(documentDTO);
            }
        }
        return documentDTOs;
    }
}
