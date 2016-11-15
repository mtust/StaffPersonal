package com.staff.personal.service.impl;

import com.auth0.jwt.internal.org.apache.commons.io.IOUtils;
import com.staff.personal.domain.MainStaffPhotos;
import com.staff.personal.domain.Staff;
import com.staff.personal.dto.RestMessageDTO;
import com.staff.personal.repository.StaffRepository;
import com.staff.personal.service.MainStaffPhotoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.net.URL;

/**
 * Created by nazar on 14.11.16.
 */
@Service
@Slf4j
public class MainStaffPhotoServiceImpl implements MainStaffPhotoService {

    @Autowired
    private StaffRepository staffRepository;

    @Override
    @Transactional
    public RestMessageDTO addPhoto(MultipartFile multipartFile, Long id) throws IOException {
        Staff staff = staffRepository.findOne(id);
        List<MainStaffPhotos> list = staff.getMainStaffPhotos();
        MainStaffPhotos photo = new MainStaffPhotos();
        photo.setFile(multipartFile.getBytes());
        list.add(photo);
        staff.setMainStaffPhotos(list);
        staffRepository.save(staff);
        return new RestMessageDTO("Succes", true);
    }

    @Override
    @Transactional
    public byte[] getPhoto(Long id) throws SQLException, IOException {
        Staff staff = staffRepository.findOne(id);
        List<MainStaffPhotos> list = staff.getMainStaffPhotos();
        if (list.isEmpty()) {
            return IOUtils.toByteArray(new URL("https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcQoiJVlwkYJvPNp7vjnrPPGEe3MDBvcDbaFjkBBjo5_OLlMGLrG_sMtMcCR").openStream());
        }
        log.info(String.valueOf(list.size()));
        return list.get(list.size() - 1).getFile();
    }
}
