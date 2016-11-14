package com.staff.personal.service;

import com.staff.personal.dto.RestMessageDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by nazar on 14.11.16.
 */
public interface MainStaffPhotoService {

     RestMessageDTO addPhoto(MultipartFile multipartFile, Long id)throws IOException;

     byte[] getPhoto(Long id) throws SQLException, IOException;
}
