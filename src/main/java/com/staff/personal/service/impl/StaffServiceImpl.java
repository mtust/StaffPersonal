package com.staff.personal.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.staff.personal.config.HibernateProxyTypeAdapter;
import com.staff.personal.config.NullStringToEmptyAdapterFactory;
import com.staff.personal.domain.MainStaff;
import com.staff.personal.domain.Region;
import com.staff.personal.domain.Staff;
import com.staff.personal.dto.*;
import com.staff.personal.exception.BadRequestParametersException;
import com.staff.personal.exception.ObjectDoNotExistException;
import com.staff.personal.repository.MainStaffRepository;
import com.staff.personal.repository.StaffRepository;
import com.staff.personal.service.*;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by nazar on 04.11.16.
 */
@Service
@Slf4j
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private WorkExperienceService workExperienceService;

    @Autowired
    private EducationService educationService;

    @Autowired
    private HttpServletRequest requestContext;

    @Autowired
    private UserService userService;

    @Autowired
    private RegionService regionService;

    @Autowired
    private OtherService otherService;

    @Autowired
    private BenefitsService benefitsService;

    @Autowired
    private HospitalsService hospitalsService;

    @Autowired
    private FiredService firedService;


    @Autowired
    private PromotionService promotionService;
    @Autowired
    private PremiumFineService premiumFineService;
    @Autowired
    private HolidayService holidayService;
    @Autowired
    MainStaffRepository mainStaffRepository;
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    //new SimpleDateFormat("dd.MM.yyyy");
    private static SimpleDateFormat simpleDateFormatNew = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss aaa");

    @Override
    public MainStaff getMainStaffForStuff(Long id) {
        log.info("IN getAllMainStaff Controller");
        return staffRepository.findOne(id).getMainStaff();
    }


    @Override
    public RestMessageDTO deleteMainStaffById(Long dataId) {
        Staff staff = staffRepository.findOne(dataId);
        staff.setMainStaff(null);
        staffRepository.save(staff);
        return new RestMessageDTO("Success", true);
    }

    private MainStaffDTO createMainStaffDTO(Staff staff) {
        MainStaffDTO mainStaffDTO = new MainStaffDTO();
        MainStaff mainStaff = staff.getMainStaff();
        mainStaffDTO.setFullName(mainStaff.getFullName());
        mainStaffDTO.setSpecialRank(mainStaff.getSpecialRank());
        mainStaffDTO.setPosition(mainStaff.getPosition());
        mainStaffDTO.setDateNumberPurpose(simpleDateFormat.format(mainStaff.getDateNumberPurpose()));
        mainStaffDTO.setLastCertification(simpleDateFormat.format(mainStaff.getLastCertification()));
        mainStaffDTO.setContractFromDate(simpleDateFormat.format(mainStaff.getContractFromDate()));
        mainStaffDTO.setContractToDate(simpleDateFormat.format(mainStaff.getContractToDate()));
        mainStaffDTO.setExemptionDate(simpleDateFormat.format(mainStaff.getExemptionDate()));
        mainStaffDTO.setDateSwear(simpleDateFormat.format(mainStaff.getDateSwear()));
        mainStaffDTO.setNumberConferringSpeclRanks(mainStaff.getNumberConferringSpeclRanks());

        mainStaffDTO.setExemptionNumOrder(mainStaff.getExemptionNumOrder());
        mainStaffDTO.setInCommand(mainStaff.getInCommand());

        mainStaffDTO.setRankCivilServant(mainStaff.getRankCivilServant());
        mainStaffDTO.setCategoriesCivilServants(mainStaff.getCategoriesCivilServants());
        mainStaffDTO.setGroupRemuneration(mainStaff.getGroupRemuneration());
        mainStaffDTO.setStaffOfficerCategory(mainStaff.getStaffOfficerCategory());

        mainStaffDTO.setConcludedCertification(mainStaff.getConcludedCertification());
        mainStaffDTO.setPersonnelProvisionForPost(mainStaff.getPersonnelProvisionForPost());
        mainStaffDTO.setPhoneNumber(mainStaff.getPhoneNumber());
        mainStaffDTO.setBiography(mainStaff.getBiography());
        return mainStaffDTO;
    }

    @Override
    @Transactional
    public RestMessageDTO createMainStaff(MainStaffDTO mainStaffDTO, Long id) {
        log.info("IN createMainStaff");
        log.info(mainStaffDTO.toString());
        Staff staff = staffRepository.findOne(id);
        if (staff == null) {
            throw new ObjectDoNotExistException("staff object with id = " + id + " dosen't exist");
        }
        try {
            MainStaff mainStaff = new MainStaff();
            mainStaff.setFullName(mainStaffDTO.getFullName());
            mainStaff.setSpecialRank(mainStaffDTO.getSpecialRank());
            mainStaff.setPosition(mainStaffDTO.getPosition());
            mainStaff.setNumberConferringSpeclRanks(mainStaffDTO.getNumberConferringSpeclRanks());
            try {
                if (mainStaffDTO.getDateOfBirth() != null) {
                    mainStaff.setDateOfBirth(simpleDateFormat.parse(mainStaffDTO.getDateOfBirth()));
                }

                if (mainStaffDTO.getDateConferringSpecRanks() != null) {
                    mainStaff.setDateConferringSpecRanks(simpleDateFormat.parse(mainStaffDTO.getDateConferringSpecRanks()));
                }
                if (mainStaffDTO.getDateNumberPurpose() != null) {
                    mainStaff.setDateNumberPurpose(simpleDateFormat.parse(mainStaffDTO.getDateNumberPurpose()));
                }

                if (mainStaffDTO.getContractFromDate() != null) {
                    mainStaff.setContractFromDate(simpleDateFormat.parse(mainStaffDTO.getContractFromDate()));
                }
                if (mainStaffDTO.getContractToDate() != null) {
                    mainStaff.setContractToDate(simpleDateFormat.parse(mainStaffDTO.getContractToDate()));
                }
                if (mainStaffDTO.getExemptionDate() != null) {
                    mainStaff.setExemptionDate(simpleDateFormat.parse(mainStaffDTO.getExemptionDate()));
                }
                if (mainStaffDTO.getLastCertification() != null) {
                    mainStaff.setLastCertification(simpleDateFormat.parse(mainStaffDTO.getLastCertification()));
                }
                if (mainStaffDTO.getDateSwear() != null) {
                    mainStaff.setDateSwear(simpleDateFormat.parse(mainStaffDTO.getDateSwear()));
                }
            } catch (ParseException e) {
                if (mainStaffDTO.getDateOfBirth() != null) {
                    mainStaff.setDateOfBirth(simpleDateFormatNew.parse(mainStaffDTO.getDateOfBirth()));
                }

                if (mainStaffDTO.getDateConferringSpecRanks() != null) {
                    mainStaff.setDateConferringSpecRanks(simpleDateFormatNew.parse(mainStaffDTO.getDateConferringSpecRanks()));
                }
                if (mainStaffDTO.getDateNumberPurpose() != null) {
                    mainStaff.setDateNumberPurpose(simpleDateFormatNew.parse(mainStaffDTO.getDateNumberPurpose()));
                }

                if (mainStaffDTO.getContractFromDate() != null) {
                    mainStaff.setContractFromDate(simpleDateFormatNew.parse(mainStaffDTO.getContractFromDate()));
                }
                if (mainStaffDTO.getContractToDate() != null) {
                    mainStaff.setContractToDate(simpleDateFormatNew.parse(mainStaffDTO.getContractToDate()));
                }
                if (mainStaffDTO.getExemptionDate() != null) {
                    mainStaff.setExemptionDate(simpleDateFormatNew.parse(mainStaffDTO.getExemptionDate()));
                }
                if (mainStaffDTO.getLastCertification() != null) {
                    mainStaff.setLastCertification(simpleDateFormatNew.parse(mainStaffDTO.getLastCertification()));
                }
                if (mainStaffDTO.getDateSwear() != null) {
                    mainStaff.setDateSwear(simpleDateFormatNew.parse(mainStaffDTO.getDateSwear()));
                }
            }
            mainStaff.setPhoneNumber(mainStaffDTO.getPhoneNumber());
            mainStaff.setExemptionNumOrder(mainStaffDTO.getExemptionNumOrder());
            mainStaff.setInCommand(mainStaffDTO.getInCommand());

            mainStaff.setRankCivilServant(mainStaffDTO.getRankCivilServant());
            mainStaff.setCategoriesCivilServants(mainStaffDTO.getCategoriesCivilServants());
            mainStaff.setGroupRemuneration(mainStaffDTO.getGroupRemuneration());
            mainStaff.setGroupRemuneration(mainStaffDTO.getGroupRemuneration());
            mainStaff.setStaffOfficerCategory(mainStaffDTO.getStaffOfficerCategory());

            mainStaff.setConcludedCertification(mainStaffDTO.getConcludedCertification());
            mainStaff.setPersonnelProvisionForPost(mainStaffDTO.getPersonnelProvisionForPost());
            mainStaff.setBiography(mainStaffDTO.getBiography());
            log.info(mainStaff.toString());
            staff.setMainStaff(mainStaff);
            staffRepository.save(staff);
        } catch (ParseException e) {
            log.warn(e.getMessage());
            throw new BadRequestParametersException("Дата у не вірному форматі");
        }

        return new RestMessageDTO("Success", true);
    }

    @Override
    @Transactional
    public RestMessageDTO createStaff(StaffDTO staffDTO) {
        log.info(staffDTO.toString());
        Staff staff = new Staff();
        this.createUpdateStuff(staff, staffDTO);
        return new RestMessageDTO("Success", true);
    }

    @Override
    @Transactional
    public RestMessageDTO deleteStaff(Long id) {
        Staff staff = staffRepository.findOne(id);
        if (staff == null) {
            throw new ObjectDoNotExistException("staff object with id = " + id + " dosen't exist");
        }
        staff.setIsDeleted(true);
        staffRepository.save(staff);
        return new RestMessageDTO("Success", true);
    }

    @Override
    @Transactional
    public GetStaffDTO getStaff(Long id) {
        log.info("claims in service: " + ((Claims) requestContext.getAttribute("claims")).get("id"));
        Long userId = Long.parseLong(((Claims) requestContext.getAttribute("claims")).get("id").toString());
        Set<Region> regions = userService.getUserRegions(userId);
        if (regions == null) {
            regions = new HashSet<Region>();
        }
        log.info("user regions: " + regions);
        Staff staff = staffRepository.findOne(id);
        log.info("stuff regions " + staff.getRegion());
        if (staff == null || (staff.getRegion() != null && !regions.contains(staff.getRegion())) || staff.getIsDeleted() == true) {
            throw new ObjectDoNotExistException("staff object with id = " + id + " dosen't exist or is deleted");
        }

        return this.createGetStuffDTO(staff);

    }

    @Override
    public GetAllStaffDTO getWholeStaff(Long id) {
        log.info("claims in service: " + ((Claims) requestContext.getAttribute("claims")).get("id"));
        Long userId = Long.parseLong(((Claims) requestContext.getAttribute("claims")).get("id").toString());
        Set<Region> regions = userService.getUserRegions(userId);
        if (regions == null) {
            regions = new HashSet<Region>();
        }
        log.info("user regions: " + regions);
        Staff staff = staffRepository.findOne(id);
        log.info("stuff regions " + staff.getRegion());
        if (staff == null || (staff.getRegion() != null && !regions.contains(staff.getRegion())) || staff.getIsDeleted() == true) {
            throw new ObjectDoNotExistException("staff object with id = " + id + " dosen't exist");
        }

        GetAllStaffDTO getAllStaffDTO = this.createGetAllStuffDTO(staff);

        return getAllStaffDTO;
    }

    public List<GetAllStaffDTO> getWholeStaff() {
        List<Staff> listAll = staffRepository.findByIsDeletedIsFalse();
        Long userId = Long.parseLong(((Claims) requestContext.getAttribute("claims")).get("id").toString());
        Set<Region> regions = userService.getUserRegions(userId);
        List<Staff> list = null;
        if (regions.isEmpty()) {
            list = listAll;
        } else {
            list = listAll.stream().filter(staff -> regions.contains(staff.getRegion())).collect(Collectors.toList());
        }
        List<GetAllStaffDTO> listDTO = new ArrayList<>();
        for (Staff staff : list) {
            GetAllStaffDTO getAllStaffDTO = this.createGetAllStuffDTO(staff);
            listDTO.add(getAllStaffDTO);
        }
        log.info("list after foreach \n" + listDTO.toString());

        return listDTO;
    }

    @Transactional
    @Override
    public List<GetStaffDTO> getAllStaff() {
        List<Staff> listAll = staffRepository.findByIsDeletedIsFalse();
        Long userId = Long.parseLong(((Claims) requestContext.getAttribute("claims")).get("id").toString());
        Set<Region> regions = userService.getUserRegions(userId);
        List<Staff> list = null;
        if (regions.isEmpty()) {
            list = listAll;
        } else {
            list = listAll.stream().filter(staff -> regions.contains(staff.getRegion())).collect(Collectors.toList());
        }
        List<GetStaffDTO> listDTO = new ArrayList<>();
        for (Staff staff : list) {
            GetStaffDTO getStaffDTO = this.createGetStuffDTO(staff);
            listDTO.add(getStaffDTO);
        }
        log.info("list after foreach \n" + listDTO.toString());

        return listDTO;
    }

    @Override
    @Transactional
    public RestMessageDTO updateStaffById(Long id, StaffDTO staffDTO) {
        Staff staff = staffRepository.findOne(id);
        this.createUpdateStuff(staff, staffDTO);
        return new RestMessageDTO("Success", true);
    }

    @Override
    @Transactional
    public RestMessageDTO updateAllStaffById(Long id, AllStaffDTO staffDTO) {
        Staff staff = staffRepository.findOne(id);
        Region region = null;
        if (staffDTO.getRegion() != null) {
            region = regionService.getRegionById(staffDTO.getRegion().getId());
            if (region != null) {
                staff.setRegion(region);
            }
        }
        staff = staffRepository.save(staff);

        this.createMainStaff(staffDTO.getMainStaff(), staff.getId());
        workExperienceService.crateWorkExperience(staffDTO.getWorkExperiences(), staff.getId());
        otherService.createOther(staffDTO.getOther(), staff.getId());
        educationService.createEducation(staffDTO.getEducation(), staff.getId());
        for (PremiumFineDTO premium :
                staffDTO.getPremiumFines()) {
            premiumFineService.addPremiumFine(staff.getId(), premium);
        }
        for (PromotionDTO promotion :
                staffDTO.getPromotions()) {
            promotionService.addPromotion(staff.getId(), promotion);
        }
        for (HolidayDTO holiday : staffDTO.getHolidays()
                ) {
            holidayService.addHoliday(staff.getId(), holiday);
        }
        for (HospitalsDTO hospital : staffDTO.getHospitals()
                ) {
            hospitalsService.addHospitals(staff.getId(), hospital);
        }

        firedService.addFired(staff.getId(), staffDTO.getFired());

        for (BenefitsDTO benefit : staffDTO.getBenefits()
                ) {
            benefitsService.addBenefit(benefit, staff.getId());
        }

        return new RestMessageDTO("Success", true);
    }

    @Override
    @Transactional
    public RestMessageDTO updateWholeStaffByIdPatch(Long id, AllStaffDTO allStaffDTO) {

        GsonBuilder b = new GsonBuilder();
        b.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
        Gson gson = b.create();
        Staff staff = staffRepository.getOne(id);
        GetAllStaffDTO allStaffDTO1 = this.createGetAllStuffDTO(staff);
        log.info("allStaffDTO:" + allStaffDTO);
        JsonElement jsonElement = gson.toJsonTree(allStaffDTO);
        Set<Map.Entry<String, JsonElement>> set = jsonElement.getAsJsonObject().entrySet();
        JsonElement jsonElement1 = gson.toJsonTree(allStaffDTO1);
        JsonObject jsonObject1 = jsonElement1.getAsJsonObject();
        Set<Map.Entry<String, JsonElement>> set1 = jsonElement1.getAsJsonObject().entrySet();
        log.info("set:" + set);
        log.info("jsonObject1:" + jsonObject1);
        this.change(set, jsonObject1);

        log.info("jsonObject2:" + jsonObject1);
        Gson gsonNew = new GsonBuilder().registerTypeAdapterFactory(NullStringToEmptyAdapterFactory.FACTORY).create();
        allStaffDTO = gsonNew.fromJson(jsonObject1, AllStaffDTO.class);
        log.info("allStaffDTO" + allStaffDTO);
        if (allStaffDTO.getBenefits() == null) {
            allStaffDTO.setBenefits(new ArrayList<>());
        }
        if (allStaffDTO.getHolidays() == null) {
            allStaffDTO.setHolidays(new ArrayList<>());
        }
        if (allStaffDTO.getHospitals() == null) {
            allStaffDTO.setHospitals(new ArrayList<>());
        }
        if (allStaffDTO.getPremiumFines() == null) {
            allStaffDTO.setPremiumFines(new ArrayList<>());
        }
        if (allStaffDTO.getPromotions() == null) {
            allStaffDTO.setPromotions(new ArrayList<>());
        }
        if (allStaffDTO.getWorkExperiences() == null) {
            allStaffDTO.setWorkExperiences(new ArrayList<>());
        }
        this.updateAllStaffById(id, allStaffDTO);
        return new RestMessageDTO("Success", true);
    }


    private void createUpdateStuff(Staff staff, StaffDTO staffDTO) {
        Region region = null;
        if (staffDTO.getRegionId() != null) {
            region = regionService.getRegionById(staffDTO.getRegionId());
            if (region != null) {
                staff.setRegion(region);
            }
        }
        staff = staffRepository.save(staff);
        this.createMainStaff(staffDTO.getMainStaffDTO(), staff.getId());
        workExperienceService.crateWorkExperience(staffDTO.getWorkExperienceDTOs(), staff.getId());
        educationService.createEducation(staffDTO.getEducationDTO(), staff.getId());
    }

    private GetStaffDTO createGetStuffDTO(Staff staff) {
        GetStaffDTO getStaffDTO = new GetStaffDTO();
        getStaffDTO.setId(staff.getId());
        getStaffDTO.setWorkExperiences(staff.getWorkExperiences());
        getStaffDTO.setEducation(staff.getEducation());
        getStaffDTO.setMainStaff(staff.getMainStaff());
        getStaffDTO.setRegion(staff.getRegion());
        return getStaffDTO;
    }

    private GetAllStaffDTO createGetAllStuffDTO(Staff staff) {
        GetAllStaffDTO getAllStaffDTO = new GetAllStaffDTO();
        getAllStaffDTO.setId(staff.getId());
        getAllStaffDTO.setWorkExperiences(staff.getWorkExperiences());
        getAllStaffDTO.setEducation(staff.getEducation());
        getAllStaffDTO.setMainStaff(this.createMainStaffDTO(staff));
        getAllStaffDTO.setRegion(staff.getRegion());
        getAllStaffDTO.setBenefits(staff.getBenefits());
        getAllStaffDTO.setFired(staff.getFired());
        getAllStaffDTO.setHolidays(staff.getHolidays());
        getAllStaffDTO.setIsDeleted(staff.getIsDeleted());
        getAllStaffDTO.setPremiumFines(staff.getPremiumFines());
        getAllStaffDTO.setPromotions(staff.getPromotions());
        return getAllStaffDTO;
    }

    private void change(Set<Map.Entry<String, JsonElement>> set, JsonObject jsonObject1) {
        for (Map.Entry<String, JsonElement> entry : set
                ) {
            JsonElement element = entry.getValue();
            if (element.isJsonPrimitive()) {
                log.info("entry:" + entry);
                jsonObject1.add(entry.getKey(), entry.getValue());
            } else if (element.isJsonArray()) {
                for (JsonElement jsonArrayElement :
                        element.getAsJsonArray()) {
                    this.change(element.getAsJsonObject().entrySet(), jsonObject1.get(entry.getKey()).getAsJsonObject());
                }
            } else if (element.isJsonObject()) {
                this.change(element.getAsJsonObject().entrySet(), jsonObject1.get(entry.getKey()).getAsJsonObject());
            }


        }
    }


}
