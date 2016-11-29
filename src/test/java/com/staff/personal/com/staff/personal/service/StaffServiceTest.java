package com.staff.personal.com.staff.personal.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.staff.personal.config.HibernateProxyTypeAdapter;
import com.staff.personal.domain.Staff;
import com.staff.personal.dto.AllStaffDTO;
import com.staff.personal.dto.EducationDTO;
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
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class StaffServiceTest {


    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private StaffService staffService;

    @Test
    @Ignore
    public void updateWholeStuffFieldByIdTest(){
        GsonBuilder b = new GsonBuilder();
        b.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
        Gson gson = b.create();
        AllStaffDTO allStaffDTO = new AllStaffDTO();
        MainStaffDTO mainStaffDTO = new MainStaffDTO();
        mainStaffDTO.setFullName("з");
        Staff staff = staffRepository.getOne(new Long(2));
        log.info("mainStaffDTO:" + mainStaffDTO);
        allStaffDTO.setMainStaff(mainStaffDTO);
        EducationDTO education = new EducationDTO();
        education.setLanguage("hui");
        allStaffDTO.setEducation(education);
        log.info("allStaffDTO:" + allStaffDTO);
        JsonElement jsonElement = gson.toJsonTree(allStaffDTO);
        Set<Map.Entry<String, JsonElement>> set = jsonElement.getAsJsonObject().entrySet();
        JsonElement jsonElement1 = gson.toJsonTree(staff);
        JsonObject jsonObject1 = jsonElement1.getAsJsonObject();
        Set<Map.Entry<String, JsonElement>> set1 = jsonElement1.getAsJsonObject().entrySet();
        log.info("set:" + set);
        log.info("jsonObject1:" + jsonObject1);
        this.change(set, jsonObject1);

        log.info("jsonObject2:" + jsonObject1);
    }

    private void change(Set<Map.Entry<String, JsonElement>> set, JsonObject jsonObject1){
        for (Map.Entry<String, JsonElement> entry: set
                ) {
            JsonElement element = entry.getValue();
            if(element.isJsonPrimitive()){
                log.info("entry:" + entry);
                jsonObject1.add(entry.getKey(), entry.getValue());
            } else if(element.isJsonArray()){
                for (JsonElement jsonArrayElement:
                        element.getAsJsonArray()) {
                    this.change(element.getAsJsonObject().entrySet(), jsonObject1.get(entry.getKey()).getAsJsonObject());
                }
            } else if(element.isJsonObject()){
                this.change(element.getAsJsonObject().entrySet(), jsonObject1.get(entry.getKey()).getAsJsonObject());
            }


        }
    }



}
