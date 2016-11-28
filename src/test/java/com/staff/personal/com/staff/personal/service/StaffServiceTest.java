package com.staff.personal.com.staff.personal.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.staff.personal.config.HibernateProxyTypeAdapter;
import com.staff.personal.domain.Staff;
import com.staff.personal.dto.AllStaffDTO;
import com.staff.personal.dto.MainStaffDTO;
import com.staff.personal.repository.StaffRepository;
import com.staff.personal.service.StaffService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;
import java.util.Set;

/**
 * Created by mtustanovskyy on 11/28/16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class StaffServiceTest {


    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private StaffService staffService;

    @Test
    public void updateWholeStuffFieldByIdTest(){
        GsonBuilder b = new GsonBuilder();
        b.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
        Gson gson = b.create();
        AllStaffDTO allStaffDTO = new AllStaffDTO();
        MainStaffDTO mainStaffDTO = new MainStaffDTO();
        mainStaffDTO.setFullName("pezda");
        Staff staff = staffRepository.getOne(new Long(2));
        log.info("mainStaffDTO:" + mainStaffDTO);
        allStaffDTO.setMainStaff(mainStaffDTO);
        JsonElement jsonElement = gson.toJsonTree(allStaffDTO);
        Set<Map.Entry<String, JsonElement>> set = jsonElement.getAsJsonObject().entrySet();
        JsonElement jsonElement1 = gson.toJsonTree(staff);
        Set<Map.Entry<String, JsonElement>> set1 = jsonElement1.getAsJsonObject().entrySet();
        log.info("set:" + set);
        log.info("set1:" + set1);
        for (Map.Entry<String, JsonElement> entry: set
                ) {
            log.info("entry:" + entry);
            set1.add(entry);

        }

        log.info("set2:" + set1);
    }



}
