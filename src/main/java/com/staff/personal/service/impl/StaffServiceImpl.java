package com.staff.personal.service.impl;

import com.google.gson.*;
import com.staff.personal.config.HibernateProxyTypeAdapter;
import com.staff.personal.config.NullStringToEmptyAdapterFactory;
import com.staff.personal.domain.*;
import com.staff.personal.dto.*;
import com.staff.personal.exception.BadRequestParametersException;
import com.staff.personal.exception.ObjectDoNotExistException;
import com.staff.personal.repository.MainStaffRepository;
import com.staff.personal.repository.StaffRepository;
import com.staff.personal.service.*;
import com.staff.personal.util.PatchUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    @Transactional
    public MainStaff getMainStaffForStuff(Long id) {
        log.info("IN getAllMainStaff Controller");
        Staff staff = staffRepository.findOne(id);
        if (staff == null || staff.getIsDeleted() == true) {
            throw new ObjectDoNotExistException("staff object with id = " + id + " dosen't exist");
        }
        return staff.getMainStaff();
    }


    @Override
    @Transactional
    public RestMessageDTO deleteMainStaffById(Long dataId) {
        Staff staff = staffRepository.findOne(dataId);
        if (staff == null || staff.getIsDeleted() == true) {
            throw new ObjectDoNotExistException("staff object with id = " + dataId + " dosen't exist");
        }
        staff.setMainStaff(null);
        staffRepository.save(staff);
        return new RestMessageDTO("Success", true);
    }


    @Override
    @Transactional
    public RestMessageDTO createMainStaff(MainStaffDTO mainStaffDTO, Long id) {
        log.info("IN createMainStaff");
        log.info(mainStaffDTO.toString());
        Staff staff = staffRepository.findOne(id);
        if (staff == null || staff.getIsDeleted() == true) {
            throw new ObjectDoNotExistException("staff object with id = " + id + " dosen't exist");
        }

        Education education = new Education();
        List<MainEducationBlock> mainEducationBlocks = new ArrayList<MainEducationBlock>();
        education.setMainEducationBlocks(mainEducationBlocks);
        staff.setEducation(education);
        staff.setMainStaff(this.createMainStaffFromDTO(mainStaffDTO));
        staffRepository.save(staff);
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
        if (staff == null || staff.getIsDeleted() == true) {
            throw new ObjectDoNotExistException("staff object with id = " + id + " dosen't exist");
        }
        staff.setIsDeleted(true);
        staff.setIsDeletedByOperator(false);
        staffRepository.save(staff);
        return new RestMessageDTO("Success", true);
    }

    public RestMessageDTO restoreDeletedStaffByAdmin(Long id) {
        log.info("claims in service: " + ((Claims) requestContext.getAttribute("claims")).get("id"));
        Long userId = Long.parseLong(((Claims) requestContext.getAttribute("claims")).get("id").toString());
        Set<Region> regions = userService.getUserRegions(userId);
        Staff staff = staffRepository.findOneByIdAndRegionIn(id, regions);
        if (staff == null) {
            throw new ObjectDoNotExistException("staff object with id = " + id + " dosen't exist");
        } else if (staff.getIsDeleted() == true) {
            staff.setIsDeleted(false);
            staff.setIsDeletedByOperator(false);
            staffRepository.save(staff);
            log.info("Staff was restored" + staff.getId() + " " + staff.getMainStaff().getFullName());
        } else {
            throw new ObjectDoNotExistException("This object is not deleted by admin");
        }
        return new RestMessageDTO("Succes", true);
    }

    public RestMessageDTO restoreDeletedStaffByOperator(Long id) {
        Long userId = Long.parseLong(((Claims) requestContext.getAttribute("claims")).get("id").toString());
        Set<Region> regions = userService.getUserRegions(userId);
        Staff staff = staffRepository.findOneByIdAndRegionIn(id, regions);
        if (staff == null) {
            throw new ObjectDoNotExistException("staff object with id = " + id + " dosen't exist");
        } else if (staff.getIsDeletedByOperator() == true) {
            staff.setIsDeletedByOperator(false);
            staffRepository.save(staff);
        } else {
            throw new ObjectDoNotExistException("This object is not deleted by operator");
        }
        return new RestMessageDTO("Succes", true);
    }

    @Override
    @Transactional
    public GetStaffDTO getStaff(Long id) {
        log.info("claims in service: " + ((Claims) requestContext.getAttribute("claims")).get("id"));
        Long userId = Long.parseLong(((Claims) requestContext.getAttribute("claims")).get("id").toString());
        Set<Region> regions = userService.getUserRegions(userId);
       /* if (regions == null) {
            regions = new HashSet<Region>();
        }
        log.info("user regions: " + regions);
        Staff staff = staffRepository.findOne(id);
        log.info("stuff regions " + staff.getRegion());
        if (staff == null || (staff.getRegion() != null && !regions.contains(staff.getRegion())) || staff.getIsDeleted() == true) {
            throw new ObjectDoNotExistException("staff object with id = " + id + " dosen't exist or is deleted");
        }*/

        Staff staff = staffRepository.findOneByIsDeletedFalseAndIsDeletedByOperatorFalseAndIdAndRegionIn(id, regions);
        if(staff == null && regions.isEmpty()){
            staff = staffRepository.findOne(id);
        }
        if (staff == null) {
            throw new ObjectDoNotExistException("staff object with id = " + id + " dosen't exist or is deleted");
        }
        return this.createGetStuffDTO(staff);

    }


    @Override
    @Transactional
    public GetAllStaffDTO getWholeStaff(Long id) {
        log.info("claims in service: " + ((Claims) requestContext.getAttribute("claims")).get("id"));
        Long userId = Long.parseLong(((Claims) requestContext.getAttribute("claims")).get("id").toString());
        Set<Region> regions = userService.getUserRegions(userId);
    /*    if (regions == null) {
            regions = new HashSet<Region>();
        }
        log.info("user regions: " + regions);
        Staff staff = staffRepository.findOne(id);
        log.info("stuff regions " + staff.getRegion());
        if (staff == null || (staff.getRegion() != null && !regions.contains(staff.getRegion())) || staff.getIsDeleted() == true) {
            throw new ObjectDoNotExistException("staff object with id = " + id + " dosen't exist");
        }
        GetAllStaffDTO getAllStaffDTO = this.createGetAllStuffDTO(staff);*/
        Staff staff = staffRepository.findOneByIsDeletedFalseAndIsDeletedByOperatorFalseAndIdAndRegionIn(id, regions);
        if(staff == null && regions.isEmpty()){
            staff = staffRepository.findOne(id);
        }
        log.info("whole staff: " + staff);
        GetAllStaffDTO getAllStaffDTO = this.createGetAllStuffDTO(staff);
        return getAllStaffDTO;
    }

    public List<GetAllStaffDTO> getWholeStaff() {
        /*List<Staff> listAll = staffRepository.findByIsDeletedFalse();
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
        log.info("list after foreach \n" + listDTO.toString());*/
        Long userId = Long.parseLong(((Claims) requestContext.getAttribute("claims")).get("id").toString());
        Set<Region> regions = userService.getUserRegions(userId);
        List<Staff> list = staffRepository.findByIsDeletedFalseAndIsDeletedByOperatorFalseAndRegionIn(regions);
        List<GetAllStaffDTO> listDTO = new ArrayList<>();
        for (Staff staff : list) {
            GetAllStaffDTO getAllStaffDTO = this.createGetAllStuffDTO(staff);
            listDTO.add(getAllStaffDTO);
        }
        return listDTO;
    }

    @Transactional
    @Override
    public List<GetStaffDTO> getAllStaff() {
       /* List<Staff> listAll = staffRepository.findByIsDeletedFalse();
        Long userId = Long.parseLong(((Claims) requestContext.getAttribute("claims")).get("id").toString());
        Set<Region> regions = userService.getUserRegions(userId);
        Set<Staff> listAll = staffRepository.findByIsDeletedFalseAndRegionIn(regions);
        Set<Staff> list = null;
        if (regions.isEmpty()) {
            list = listAll;
        } else {
            // list = listAll.stream().filter(staff -> regions.contains(staff.getRegion())).collect(Collectors.toSet());
            list = listAll;
        }
        List<GetStaffDTO> listDTO = new ArrayList<>();
        for (Staff staff : list) {
            GetStaffDTO getStaffDTO = this.createGetStuffDTO(staff);
            listDTO.add(getStaffDTO);
        }
        log.info("list after foreach \n" + listDTO.toString());*/
        Long userId = Long.parseLong(((Claims) requestContext.getAttribute("claims")).get("id").toString());
        Set<Region> regions = userService.getUserRegions(userId);
        log.info("regions: " + regions.toString());
        List<Staff> list = staffRepository.findByIsDeletedFalseAndIsDeletedByOperatorFalseAndRegionIn(regions);
        log.info("list: " + list);
        List<GetStaffDTO> listDTO = new ArrayList<>();
        for (Staff staff : list) {
            GetStaffDTO getStaffDTO = this.createGetStuffDTO(staff);
            listDTO.add(getStaffDTO);
        }
        return listDTO;
    }

    @Transactional
    @Override
    public List<GetStaffDTO> getAllDeletedStaff() {
       /* List<Staff> listAll = staffRepository.findByIsDeletedTrue();
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
        log.info("list after foreach \n" + listDTO.toString());*/
        Long userId = Long.parseLong(((Claims) requestContext.getAttribute("claims")).get("id").toString());
        Set<Region> regions = userService.getUserRegions(userId);
        List<Staff> list = staffRepository.findByIsDeletedTrueAndIsDeletedByOperatorFalseAndRegionIn(regions);
        List<GetStaffDTO> listDTO = new ArrayList<>();
        for (Staff staff : list) {
            GetStaffDTO getStaffDTO = this.createGetStuffDTO(staff);
            listDTO.add(getStaffDTO);
        }
        return listDTO;
    }

    @Override
    @Transactional
    public RestMessageDTO deleteByOperator(Long id) {
        Staff staff = staffRepository.findOne(id);
        if (staff == null || staff.getIsDeleted() == true) {
            throw new ObjectDoNotExistException("staff object with id = " + id + " dosen't exist");
        }
        staff.setIsDeletedByOperator(true);
        staffRepository.save(staff);
        return new RestMessageDTO("Succes", true);
    }

    @Override
    @Transactional
    public List<GetStaffDTO> getAllStaffDeletedByOperator() {
        Long userId = Long.parseLong(((Claims) requestContext.getAttribute("claims")).get("id").toString());
        Set<Region> regions = userService.getUserRegions(userId);
        List<Staff> list = staffRepository.findByIsDeletedByOperatorTrueAndRegionIn(regions);
        List<GetStaffDTO> listDTO = new ArrayList<>();
        if (list.isEmpty()) {
            return listDTO;
        } else {
            for (Staff staff : list) {
                GetStaffDTO getStaffDTO = this.createGetStuffDTO(staff);
                listDTO.add(getStaffDTO);
            }
        }
        return listDTO;
    }


    @Override
    @Transactional
    public RestMessageDTO updateStaffById(Long id, StaffDTO staffDTO) {
        Staff staff = staffRepository.findOne(id);
        if (staff == null || staff.getIsDeleted() == true) {
            throw new ObjectDoNotExistException("staff object with id = " + id + " dosen't exist");
        }
        if (staffDTO.getEducationDTO() != null) {
            Education education = new Education();
            EducationDTO educationDTO = staffDTO.getEducationDTO();
            education.setLanguage(educationDTO.getLanguage());
            education.setId(educationDTO.getId());
            education.setMainEducationBlocks(educationDTO.getMainEducationBlocks());
            education.setOtherStudying(educationDTO.getOtherStudying());
            staff.setEducation(education);
        }

        if (staffDTO.getMainStaffDTO() != null) {
            MainStaff mainStaff = new MainStaff();
            MainStaffDTO mainStaffDTO = staffDTO.getMainStaffDTO();
            mainStaff.setId(mainStaffDTO.getId());
            mainStaff.setBiography(mainStaffDTO.getBiography());
            mainStaff.setCategoriesCivilServants(mainStaffDTO.getCategoriesCivilServants());
            mainStaff.setConcludedCertification(mainStaffDTO.getConcludedCertification());
            try {
                mainStaff.setContractFromDate(simpleDateFormat.parse(mainStaffDTO.getContractFromDate()));
            } catch (Exception e) {
                log.info(e.getMessage());
            }
            try {
                mainStaff.setContractToDate(simpleDateFormat.parse(mainStaffDTO.getContractToDate()));
            } catch (Exception e) {
                log.info(e.getMessage());
            }
            try {
                mainStaff.setDateConferringSpecRanks(simpleDateFormat.parse(mainStaffDTO.getDateConferringSpecRanks()));
            } catch (Exception e) {
                log.info(e.getMessage());
            }
            try {
                mainStaff.setDateNumberPurpose(simpleDateFormat.parse(mainStaffDTO.getDateNumberPurpose()));
            } catch (Exception e) {
                log.info(e.getMessage());
            }
            try {
                mainStaff.setDateOfBirth(simpleDateFormat.parse(mainStaffDTO.getDateOfBirth()));
            } catch (Exception e) {
                log.info(e.getMessage());
            }
            try {
                mainStaff.setDateSwear(simpleDateFormat.parse(mainStaffDTO.getDateSwear()));
            } catch (Exception e) {
                log.info(e.getMessage());
            }
            try {
                mainStaff.setExemptionDate(simpleDateFormat.parse(mainStaffDTO.getExemptionDate()));
            } catch (Exception e) {
                log.info(e.getMessage());
            }
            mainStaff.setExemptionNumOrder(mainStaffDTO.getExemptionNumOrder());
            mainStaff.setFullName(mainStaffDTO.getFullName());
            mainStaff.setGroupRemuneration(mainStaffDTO.getGroupRemuneration());
        }
        if (staffDTO.getRegionId() != null) {
            Region region = regionService.getRegionById(staffDTO.getRegionId());
            staff.setRegion(region);
        }
        if (staffDTO.getWorkExperienceDTOs() != null) {
            List<WorkExperience> wes = new ArrayList<>();
            for (WorkExperienceDTO weDTO : staffDTO.getWorkExperienceDTOs()
                    ) {
                WorkExperience we = new WorkExperience();
                we.setId(weDTO.getId());
                try {
                    we.setFromDate(simpleDateFormat.parse(weDTO.getFromDate()));
                } catch (Exception e) {
                    log.info(e.getMessage());
                }
                try {
                    we.setToDate(simpleDateFormat.parse(weDTO.getToDate()));
                } catch (Exception e) {
                    log.info(e.getMessage());
                }
                we.setName(weDTO.getOrgName());
                wes.add(we);
            }
            staff.setWorkExperiences(wes);
        }
        staffRepository.save(staff);
        return new RestMessageDTO("Success", true);
    }

    @Override
    @Transactional
    public RestMessageDTO updateAllStaffById(Long id, AllStaffDTO staffDTO) {
        Staff staff = staffRepository.findOne(id);
        if (staff == null || staff.getIsDeleted() == true) {
            throw new ObjectDoNotExistException("staff object with id = " + id + " dosen't exist");
        }
        Region region = null;
        if (staffDTO.getRegion() != null) {
            region = regionService.getRegionById(staffDTO.getRegion().getId());
            if (region != null) {
                staff.setRegion(region);
            }
        }

        staff.setMainStaff(mainStaffRepository.save(this.createMainStaffFromDTO(staffDTO.getMainStaff())));
        staff.setWorkExperiences(workExperienceService.updateWorkExperiances(staffDTO.getWorkExperiences()));
        if(staffDTO.getOther() != null) {
            staff.setOther(otherService.createOther(staffDTO.getOther()));
        }
        staff.setEducation(educationService.updateEducation(staffDTO.getEducation()));
        staff.setPremiumFines(premiumFineService.updatePremiumFine(staffDTO.getPremiumFines()));
        staff.setPromotions(promotionService.updatePromotion(staffDTO.getPromotions()));
        staff.setHolidays(holidayService.updateHolidays(staffDTO.getHolidays()));
        staff.setHospitals(hospitalsService.updateHospitals(staffDTO.getHospitals()));
        if(staffDTO.getFired() != null) {
            staff.setFired(firedService.updateFired(staffDTO.getFired()));
        }
        staff.setBenefits(benefitsService.updateBenefits(staffDTO.getBenefits()));
        staff = staffRepository.save(staff);
//        otherService.createOther(staffDTO.getOther(), staff.getId());
//        educationService.createEducation(staffDTO.getEducation(), staff.getId());
//        if (staffDTO.getPremiumFines() != null) {
//            for (PremiumFineDTO premium :
//                    staffDTO.getPremiumFines()) {
//                premiumFineService.addPremiumFine(staff.getId(), premium);
//            }
//        }
//        if (staffDTO.getPromotions() != null) {
//            for (PromotionDTO promotion :
//                    staffDTO.getPromotions()) {
//                promotionService.addPromotion(staff.getId(), promotion);
//            }
//        }
//        if (staffDTO.getHolidays() != null) {
//            for (HolidayDTO holiday : staffDTO.getHolidays()
//                    ) {
//                holidayService.addHoliday(staff.getId(), holiday);
//            }
//        }
//        if (staffDTO.getHospitals() != null) {
//            for (HospitalsDTO hospital : staffDTO.getHospitals()
//                    ) {
//                hospitalsService.addHospitals(staff.getId(), hospital);
//            }
//        }
//        if (staffDTO.getFired() != null) {
//
//            firedService.addFired(staff.getId(), staffDTO.getFired());
//
//        }
//
//        if (staffDTO.getBenefits() != null) {
//            for (BenefitsDTO benefit : staffDTO.getBenefits()
//                    ) {
//                benefitsService.addBenefit(benefit, staff.getId());
//            }
//        }

        return new RestMessageDTO("Success", true);
    }

    @Override
    @Transactional
    public RestMessageDTO updateWholeStaffByIdPatch(Long id, AllStaffDTO allStaffDTO) {

        GsonBuilder b = new GsonBuilder();
        b.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
        Gson gson = b.create();
        Staff staff = staffRepository.findOne(id);
        if (staff == null || staff.getIsDeleted() == true) {
            throw new ObjectDoNotExistException("staff object with id = " + id + " dosen't exist");
        }
        GetAllStaffDTO allStaffDTO1 = this.createGetAllStuffDTO(staff);
        JsonElement jsonElement = gson.toJsonTree(allStaffDTO);
        Set<Map.Entry<String, JsonElement>> set = jsonElement.getAsJsonObject().entrySet();
        JsonElement jsonElement1 = gson.toJsonTree(allStaffDTO1);
        JsonObject jsonObject1 = jsonElement1.getAsJsonObject();
        Set<Map.Entry<String, JsonElement>> set1 = jsonElement1.getAsJsonObject().entrySet();
        PatchUtil.getInstance().change(set, jsonObject1);

        Gson gsonNew = new GsonBuilder().registerTypeAdapterFactory(NullStringToEmptyAdapterFactory.FACTORY).create();
        allStaffDTO = gsonNew.fromJson(jsonObject1, AllStaffDTO.class);
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

    public MainStaffDTO createMainStaffDTO(MainStaff mainStaff) {
        MainStaffDTO mainStaffDTO = new MainStaffDTO();
        mainStaffDTO.setId(mainStaff.getId());
        mainStaffDTO.setFullName(mainStaff.getFullName());
        mainStaffDTO.setSpecialRank(mainStaff.getSpecialRank());
        if (mainStaff.getDateOfBirth() != null) {
            mainStaffDTO.setDateOfBirth(simpleDateFormat.format(mainStaff.getDateOfBirth()));
        }
        if (mainStaff.getDateConferringSpecRanks() != null) {
            mainStaffDTO.setDateConferringSpecRanks(simpleDateFormat.format(mainStaff.getDateConferringSpecRanks()));
        }
        if (mainStaff.getDateNumberPurpose() != null) {
            mainStaffDTO.setDateNumberPurpose(simpleDateFormat.format(mainStaff.getDateNumberPurpose()));
        }
        if (mainStaff.getContractFromDate() != null) {
            mainStaffDTO.setContractFromDate(simpleDateFormat.format(mainStaff.getContractFromDate()));
        }
        if (mainStaff.getContractToDate() != null) {

            mainStaffDTO.setContractToDate(simpleDateFormat.format(mainStaff.getContractToDate()));

        }
        if (mainStaff.getExemptionDate() != null) {
            mainStaffDTO.setExemptionDate(simpleDateFormat.format(mainStaff.getExemptionDate()));
        }
        if (mainStaff.getLastCertification() != null) {
            mainStaffDTO.setLastCertification(simpleDateFormat.format(mainStaff.getLastCertification()));
        }
        if (mainStaff.getDateSwear() != null) {
            mainStaffDTO.setDateSwear(simpleDateFormat.format(mainStaff.getDateSwear()));
        }
        if(mainStaff.getInCommandDate() != null){
            mainStaffDTO.setInCommandDate(simpleDateFormat.format(mainStaff.getInCommandDate()));
        }
        mainStaffDTO.setRankCivilServant(mainStaff.getRankCivilServant());
        mainStaffDTO.setCategoriesCivilServants(mainStaff.getCategoriesCivilServants());
        mainStaffDTO.setGroupRemuneration(mainStaff.getGroupRemuneration());
        mainStaffDTO.setStaffOfficerCategory(mainStaff.getStaffOfficerCategory());

        mainStaffDTO.setPhoneNumber(mainStaff.getPhoneNumber());
        mainStaffDTO.setExemptionNumOrder(mainStaff.getExemptionNumOrder());
        mainStaffDTO.setInCommandNumber(mainStaff.getInCommandNumber());
        mainStaffDTO.setConcludedCertification(mainStaff.getConcludedCertification());
        mainStaffDTO.setPersonnelProvisionForPost(mainStaff.getPersonnelProvisionForPost());
        mainStaffDTO.setBiography(mainStaff.getBiography());
        mainStaffDTO.setPosition(mainStaff.getPosition());
        mainStaffDTO.setNumberConferringSpeclRanks(mainStaff.getNumberConferringSpeclRanks());
        mainStaffDTO.setStructureGroupName(mainStaff.getStructureGroupName());
        mainStaffDTO.setStudy(mainStaff.getStudy());
        mainStaffDTO.setContractNumber(mainStaff.getContractNumber());
        mainStaffDTO.setNumberPurpose(mainStaff.getNumberPurpose());


        return mainStaffDTO;
    }


    @Transactional
    private void createUpdateStuff(Staff staff, StaffDTO staffDTO) {
        log.info("create staff: " + staff);
        log.info("crate staffDTO" + staffDTO);
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
        if(staffDTO.getEducationDTO() != null) {
            educationService.createEducation(staffDTO.getEducationDTO(), staff.getId());
        }
    }

    private GetStaffDTO createGetStuffDTO(Staff staff) {
        GetStaffDTO getStaffDTO = new GetStaffDTO();
        getStaffDTO.setId(staff.getId());
        getStaffDTO.setWorkExperiences(workExperienceService.createWorkExperienceDTO(staff.getWorkExperiences()));
        getStaffDTO.setEducation(staff.getEducation());
        getStaffDTO.setMainStaffDTO(this.createMainStaffDTO(staff.getMainStaff()));
        getStaffDTO.setRegion(staff.getRegion());
        return getStaffDTO;
    }


    private GetAllStaffDTO createGetAllStuffDTO(Staff staff) {
        GetAllStaffDTO getAllStaffDTO = new GetAllStaffDTO();
        getAllStaffDTO.setId(staff.getId());
        getAllStaffDTO.setWorkExperiences(workExperienceService.createWorkExperienceDTO(staff.getWorkExperiences()));
        getAllStaffDTO.setEducation(staff.getEducation());
        getAllStaffDTO.setMainStaff(this.createMainStaffDTO(staff.getMainStaff()));
        getAllStaffDTO.setRegion(staff.getRegion());
        getAllStaffDTO.setBenefits(benefitsService.createBenefitsDTO(staff.getBenefits()));
        getAllStaffDTO.setFired(firedService.createFiredDTO(staff.getFired()));
        getAllStaffDTO.setHolidays(holidayService.createHolidayDTO(staff.getHolidays()));
        getAllStaffDTO.setIsDeleted(staff.getIsDeleted());
        getAllStaffDTO.setPremiumFines(premiumFineService.createPremiumFineDTO(staff.getPremiumFines()));
        getAllStaffDTO.setPromotions(promotionService.createPromotionDTO(staff.getPromotions()));
        getAllStaffDTO.setOther(staff.getOther());
        getAllStaffDTO.setHospitals(hospitalsService.createHospitalsDTO(staff.getHospitals()));
        return getAllStaffDTO;
    }

    private MainStaff createMainStaffFromDTO(MainStaffDTO mainStaffDTO) {
        MainStaff mainStaff = new MainStaff();
        try {

            mainStaff.setId(mainStaffDTO.getId());
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
                if (mainStaffDTO.getInCommandDate() != null) {
                    mainStaff.setInCommandDate(simpleDateFormat.parse(mainStaffDTO.getInCommandDate()));
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
                if (mainStaffDTO.getInCommandDate() != null) {
                    mainStaff.setInCommandDate(simpleDateFormatNew.parse(mainStaffDTO.getInCommandDate()));
                }
            }
            mainStaff.setPhoneNumber(mainStaffDTO.getPhoneNumber());
            mainStaff.setExemptionNumOrder(mainStaffDTO.getExemptionNumOrder());
            mainStaff.setInCommandNumber(mainStaffDTO.getInCommandNumber());

            mainStaff.setRankCivilServant(mainStaffDTO.getRankCivilServant());
            mainStaff.setCategoriesCivilServants(mainStaffDTO.getCategoriesCivilServants());
            mainStaff.setGroupRemuneration(mainStaffDTO.getGroupRemuneration());
            mainStaff.setGroupRemuneration(mainStaffDTO.getGroupRemuneration());
            mainStaff.setStaffOfficerCategory(mainStaffDTO.getStaffOfficerCategory());

            mainStaff.setConcludedCertification(mainStaffDTO.getConcludedCertification());
            mainStaff.setPersonnelProvisionForPost(mainStaffDTO.getPersonnelProvisionForPost());
            mainStaff.setBiography(mainStaffDTO.getBiography());
            mainStaff.setStructureGroupName(mainStaffDTO.getStructureGroupName());

            mainStaff.setContractNumber(mainStaffDTO.getContractNumber());
            mainStaff.setStudy(mainStaffDTO.getStudy());
            mainStaff.setNumberPurpose(mainStaffDTO.getNumberPurpose());

        } catch (ParseException e) {
            log.warn(e.getMessage());
            throw new BadRequestParametersException("Дата у не вірному форматі");
        }
        return mainStaff;
    }

    @Override
    public List<GetStaffDTO> getStaffByPositionCode(String code) {
        List<Staff> staffList = staffRepository.findByMainStaffPosition(code);
        List<GetStaffDTO> listDTO = new ArrayList<>();
        for (Staff staff : staffList) {
            GetStaffDTO getStaffDTO = this.createGetStuffDTO(staff);
            listDTO.add(getStaffDTO);
        }
        return listDTO;
    }
}
