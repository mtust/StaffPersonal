package com.staff.personal.controller.api;

import com.auth0.jwt.internal.org.apache.commons.codec.binary.Base64;
import com.staff.personal.domain.*;
import com.staff.personal.dto.*;
import com.staff.personal.security.Secured;
import com.staff.personal.service.*;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by nazar on 04.11.16.
 */
@Slf4j
@RestController
@RequestMapping("/api/staff")
@CrossOrigin
public class StaffController {

    @Autowired
    private StaffService staffService;
    @Autowired
    private EducationService educationService;
    @Autowired
    private WorkExperienceService workExperienceService;
    @Autowired
    private OtherService otherService;
    @Autowired
    private MainStaffPhotoService mainStaffPhotoService;
    @Autowired
    private BenefitsService benefitsService;
    @Autowired
    private FiredService firedService;
    @Autowired
    private HolidayService holidayService;
    @Autowired
    private HospitalsService hospitalsService;
    @Autowired
    private PremiumFineService premiumFineService;
    @Autowired
    private PromotionService promotionService;
    @Autowired
    private ReportsService reportsService;
    @Autowired
    private StaffDocumentsService staffDocumentsService;

    @Autowired
    private HttpServletRequest requestContext;

    @RequestMapping(method = RequestMethod.POST)
    RestMessageDTO createStaff(@RequestBody StaffDTO staffDTO) {
        return staffService.createStaff(staffDTO);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    RestMessageDTO deleteStaffByAdmin(@PathVariable Long id) {
        return staffService.deleteStaff(id);
    }

    @RequestMapping(value = "{id}/operator", method = RequestMethod.DELETE)
    RestMessageDTO deleteStaffByOperator(@PathVariable Long id) {
        return staffService.deleteByOperator(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    GetStaffDTO getStaff(@PathVariable Long id) {
        log.info("user claims: " + ((Claims) requestContext.getAttribute("claims")).get("id"));
        return staffService.getStaff(id);
    }

    @RequestMapping(value = "whole/{id}", method = RequestMethod.GET)
    GetAllStaffDTO getAllStaff(@PathVariable Long id){
        return  staffService.getWholeStaff(id);
    }

    @RequestMapping(value = "all", method = RequestMethod.GET)
    List<GetStaffDTO> getAllStaff() {
        return staffService.getAllStaff();
    }

    @Secured(value = Role.ROLE_ADMIN)
    @RequestMapping(value = "allDeleted", method = RequestMethod.GET)
    List<GetStaffDTO> getAllDeletedStaff() {
        return staffService.getAllDeletedStaff();
    }

    @Secured(value = Role.ROLE_ADMIN)
    @RequestMapping(value = "allDeletedByOperator", method = RequestMethod.GET)
    List<GetStaffDTO> getAllDeletedStaffByOperator() {
        return staffService.getAllStaffDeletedByOperator();
    }


    @RequestMapping(method = RequestMethod.GET)
    List<GetAllStaffDTO> getStaff() {
        return staffService.getWholeStaff();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    RestMessageDTO updateStaffById(@PathVariable Long id, @RequestBody StaffDTO staffDTO){
        return staffService.updateStaffById(id, staffDTO);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PATCH)
    RestMessageDTO updateStaffById(@PathVariable Long id, @RequestBody AllStaffDTO allStaffDTO){
        return staffService.updateWholeStaffByIdPatch(id, allStaffDTO);
    }

    @RequestMapping(value = "whole/{id}", method = RequestMethod.PATCH)
    RestMessageDTO updateStaffWholeById(@PathVariable Long id, @RequestBody AllStaffDTO allStaffDTO){
        return staffService.updateAllStaffById(id, allStaffDTO);
    }
    @Secured(value = Role.ROLE_ADMIN)
    @RequestMapping(value = "{id}/restore/admin", method = RequestMethod.PATCH)
    RestMessageDTO restoreDeletedStaffByAdmin(@PathVariable Long id){
        return staffService.restoreDeletedStaffByAdmin(id);
    }
    @Secured(value = Role.ROLE_ADMIN)
    @RequestMapping(value = "{id}/restore/operator", method = RequestMethod.PATCH)
    RestMessageDTO restoreDeletedStaffByOperator(@PathVariable Long id){
        return staffService.restoreDeletedStaffByOperator(id);
    }





    //MAIN STAFF
    @RequestMapping(value = "{id}/mainStaff", method = RequestMethod.GET)
    MainStaff getMainStaffForStuff(@PathVariable Long id) {
        log.info("IN getAllMainStaff Controller");
        return staffService.getMainStaffForStuff(id);
    }

    @RequestMapping(value = "{id}/mainStaff", method = RequestMethod.POST)
    RestMessageDTO createMainStaff(@PathVariable Long id, @RequestBody MainStaffDTO mainStaffDTO) {
        log.info("IN createMainStaff Controller");
        log.info(mainStaffDTO.toString());
        return staffService.createMainStaff(mainStaffDTO, id);
    }
    @Secured(value = Role.ROLE_ADMIN)
    @RequestMapping(value = "{id}/mainStaff", method = RequestMethod.DELETE)
    RestMessageDTO deleteMainStaff(@PathVariable Long id) {
        return staffService.deleteMainStaffById(id);
    }


    //EDUCATION
    @RequestMapping(value = "{id}/education", method = RequestMethod.POST)
    RestMessageDTO createEducation(@PathVariable Long id, @RequestBody EducationDTO educationDTO) {
        return educationService.createEducation(educationDTO, id);
    }

    @RequestMapping(value = "{id}/education", method = RequestMethod.GET)
    Education getEducationByStaffId(@PathVariable Long id) {
        log.info("getEducationByStaffId");
        return educationService.getEducation(id);
    }
    @Secured(value = Role.ROLE_ADMIN)
    @RequestMapping(value = "{id}/education", method = RequestMethod.DELETE)
    RestMessageDTO delEducationByStaffId(@PathVariable Long id) {
        log.info("delEducationByStaffId");
        return educationService.delEducation(id);
    }
    @Secured(value = Role.ROLE_ADMIN)
    @RequestMapping(value = "{id}/education/{idMainEducation}", method = RequestMethod.DELETE)
    RestMessageDTO delMainEducationBlock(@PathVariable Long id,@PathVariable Long idMainEducation) {
        log.info("delMainEducationBlock");
        return educationService.delMainEducation(id,idMainEducation);
    }

    //WORK EXPERIENCE
    @RequestMapping(value = "{id}/workExperience", method = RequestMethod.POST)
    RestMessageDTO createEducation(@PathVariable Long id, @RequestBody List<WorkExperienceDTO> workExperienceDTOs) {
        log.info("in createEducation");
        return workExperienceService.crateWorkExperience(workExperienceDTOs, id);
    }

    @RequestMapping(value = "{id}/workExperience", method = RequestMethod.GET)
    List<WorkExperience> getWorkExperience(@PathVariable Long id) {
        log.info("in getWorkExperience");
        return workExperienceService.getWorkExperiences(id);
    }
    @Secured(value = Role.ROLE_ADMIN)
    @RequestMapping(value = "{id}/workExperience/{idExp}", method = RequestMethod.DELETE)
    RestMessageDTO delWorkExperience(@PathVariable Long id,@PathVariable Long idExp) {
        log.info("delWorkExperience by id Experience!");
        return workExperienceService.delWorkExperiences(id,idExp);
    }

    //OTHER
    @RequestMapping(value = "{id}/other", method = RequestMethod.POST)
    RestMessageDTO createOther(@PathVariable Long id, @RequestBody Other other) {
        log.info(log.getName());
        log.info(other.toString());
        return otherService.createOther(other, id);
    }

    @RequestMapping(value = "{id}/other", method = RequestMethod.GET)
    Other getOther(@PathVariable Long id) {
        log.info("in getWorkExperience");
        return otherService.getOther(id);
    }
    @Secured(value = Role.ROLE_ADMIN)
    @RequestMapping(value = "{id}/other", method = RequestMethod.DELETE)
    RestMessageDTO delOther(@PathVariable Long id) {
        log.info("in getWorkExperience");
        return otherService.delOther(id);
    }

    //MAINSTAFFPHOTO
    @RequestMapping(value = "{id}/photo", headers = "content-type=multipart/form-data", method = RequestMethod.PUT)
    RestMessageDTO changePhoto(@RequestParam("photo") MultipartFile photo, @PathVariable Long id) throws IOException {
        log.info("IN CONTROLLER changePhoto");
        return mainStaffPhotoService.addPhoto(photo, id);
    }

    @RequestMapping(value = "{id}/photo", method = RequestMethod.GET)
    @ResponseBody
    String getPhoto(@PathVariable Long id) throws IOException, SQLException {
        log.info("IN CONTROLLER getPhoto");
        return new String(Base64.encodeBase64(mainStaffPhotoService.getPhoto(id)));
    }


    //BENEFITS
    @RequestMapping(value = "{id}/benefits", method = RequestMethod.POST)
    RestMessageDTO addBenefits(@PathVariable Long id, @RequestBody BenefitsDTO benefitsDTO) {
        log.info("in addBenefits \n" + benefitsDTO.toString());
        return benefitsService.addBenefit(benefitsDTO, id);
    }

    @RequestMapping(value = "{id}/benefits", method = RequestMethod.GET)
    List<BenefitsDTO> getBenefits(@PathVariable Long id) {
        log.info("in getBenefits");
        return benefitsService.getBenefits(id);
    }
    @Secured(value = Role.ROLE_ADMIN)
    @RequestMapping(value = "{id}/benefits/{idBen}", method = RequestMethod.DELETE)
    RestMessageDTO delBenefitsByID(@PathVariable Long id, @PathVariable Long idBen) {
        log.info("in delBenefitsByID");
        return benefitsService.delBenefitsById(id, idBen);
    }

    //FIRED
    @RequestMapping(value = "{id}/fired", method = RequestMethod.POST)
    RestMessageDTO addFired(@PathVariable Long id, @RequestBody FiredDTO firedDTO) {
        log.info("in addFired \n" + firedDTO.toString());
        return firedService.addFired(id, firedDTO);
    }

    //!!!does not work
    @RequestMapping(value = "{id}/fired", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    String getFired(@PathVariable Long id) {
        log.info("in getFired \n" + firedService.getFired(id).toString());
        return firedService.getFired(id).toString();
    }
    @Secured(value = Role.ROLE_ADMIN)
    @RequestMapping(value = "{id}/fired", method = RequestMethod.DELETE)
    RestMessageDTO delFired(@PathVariable Long id) {
            return firedService.delFired(id);
    }

    //HOLIDAYS
    @RequestMapping(value = "{id}/holiday", method = RequestMethod.POST)
    RestMessageDTO addHoliday(@PathVariable Long id, @RequestBody HolidayDTO holidayDTO) {
        log.info("in addFired \n" + holidayDTO.toString());
        return holidayService.addHoliday(id, holidayDTO);
    }

    @RequestMapping(value = "{id}/holiday", method = RequestMethod.GET)
    List<Holiday> getholiday(@PathVariable Long id) {
        log.info("in getholidays");
        return holidayService.getHolidays(id);
    }

    @Secured(value = Role.ROLE_ADMIN)
    @RequestMapping(value = "{id}/holiday/{idHol}", method = RequestMethod.DELETE)
    RestMessageDTO delHoliday(@PathVariable Long id, @PathVariable Long idHol) {
        log.info("in delHoliday");
        return holidayService.delHoliday(id, idHol);
    }

    //HOSPITALS
    @RequestMapping(value = "{id}/hospitals", method = RequestMethod.POST)
    RestMessageDTO addHospitals(@PathVariable Long id, @RequestBody HospitalsDTO hospitalsDTo) {
        log.info("hHolidayospitalDTO \n" + hospitalsDTo.toString());
        return hospitalsService.addHospitals(id, hospitalsDTo);
    }

    @RequestMapping(value = "{id}/hospitals", method = RequestMethod.GET)
    List<Hospitals> getHospitals(@PathVariable Long id) {
        log.info("in hospitals");
        return hospitalsService.getHospitals(id);
    }

    @Secured(value = Role.ROLE_ADMIN)
    @RequestMapping(value = "{id}/hospitals/{idHosp}", method = RequestMethod.DELETE)
    RestMessageDTO delHospitals(@PathVariable Long id, @PathVariable Long idHosp) {
        log.info("in delHospitals");
        return hospitalsService.delHospitals(id,idHosp);
    }

    //PremiumFine
    @RequestMapping(value = "{id}/premiumFine", method = RequestMethod.POST)
    RestMessageDTO addpremiumFine(@PathVariable Long id, @RequestBody PremiumFineDTO premiumFineDTO) {
        log.info("premiumFine \n" + premiumFineDTO.toString());
        return premiumFineService.addPremiumFine(id, premiumFineDTO);
    }

    @RequestMapping(value = "{id}/premiumFine", method = RequestMethod.GET)
    List<PremiumFine> getpremiumFine(@PathVariable Long id) {
        log.info("in premiumFine");
        return premiumFineService.getPremiumFine(id);
    }

    @Secured(value = Role.ROLE_ADMIN)
    @RequestMapping(value = "{id}/premiumFine/{idPrFine}", method = RequestMethod.DELETE)
    RestMessageDTO delPremiumFine(@PathVariable Long id, @PathVariable Long idPrFine) {
        log.info("in delPremiumFine");
        return premiumFineService.delPremiumFine(id,idPrFine);
    }

    //PROMOTION
    @RequestMapping(value = "{id}/promotion", method = RequestMethod.POST)
    RestMessageDTO addpromotion(@PathVariable Long id, @RequestBody PromotionDTO promotionDTO) {
        log.info("premiumFine \n" + promotionDTO.toString());
        return promotionService.addPromotion(id, promotionDTO);
    }

    @RequestMapping(value = "{id}/promotion", method = RequestMethod.GET)
    List<Promotion> getPromotion(@PathVariable Long id) {
        log.info("in promotion");
        return promotionService.getPromotions(id);
    }

    @Secured(value = Role.ROLE_ADMIN)
    @RequestMapping(value = "{id}/promotion/{idPr}", method = RequestMethod.DELETE)
    RestMessageDTO delPromotion(@PathVariable Long id, @PathVariable Long idPr) {
        log.info("in delPromotion");
        return promotionService.delPromotions(id,idPr);
    }


    //Reports
    @RequestMapping(value = "{id}/reports", headers = "content-type=multipart/form-data", method = RequestMethod.PUT)
    RestMessageDTO setReports(@RequestParam("file") MultipartFile file, @PathVariable Long id,
                              @RequestParam("name") String name,
                              @RequestParam("text") String text) throws IOException {
        log.info("IN CONTROLLER setReports");
        return reportsService.addReport(id, file, name, text);
    }

    @RequestMapping(value = "{id}/reports", method = RequestMethod.GET)
    @ResponseBody
    List<GetReportsInfoDTO> getReportsInfo(@PathVariable Long id) {
        log.info("IN CONTROLLER getReports");
        return reportsService.getReportsInfo(id);
    }
    @RequestMapping(value = "{id}/reports/{idReport}", method = RequestMethod.GET)
    @ResponseBody
    byte[] downloadReport(@PathVariable Long id, @PathVariable int idReport)throws IOException, SQLException{
        log.info("IN CONTROLLER getReports");
        return reportsService.getReportsFile(id,idReport);
    }


    @Secured(value = Role.ROLE_ADMIN)
    @RequestMapping(value = "{id}/reports/{idReports}", method = RequestMethod.DELETE)
    RestMessageDTO delReports(@PathVariable Long id, @PathVariable Long idReports) throws IOException, SQLException  {
        log.info("in delReports");
        return reportsService.delReport(id,idReports);
    }

   /* @RequestMapping(value = "{id}/reportsF", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
    @ResponseBody
    byte[] getReportsFile(@PathVariable Long id) throws IOException, SQLException {
        log.info("IN CONTROLLER getReports");
        return reportsService.getReportsFile(id);
    }*/

   //STAFFDOCUMENTS
   @RequestMapping(value = "{id}/staffDoc", headers = "content-type=multipart/form-data", method = RequestMethod.PUT)
   RestMessageDTO setStaffDoc(@RequestParam("file") MultipartFile file,
                              @PathVariable Long id) throws IOException {
       log.info("IN CONTROLLER setStaffDoc");
       return staffDocumentsService.addDocument(file,id);
   }
    @RequestMapping(value = "{id}/staffDoc", method = RequestMethod.GET)
    @ResponseBody
    List<StaffDocumentDTO> getStaffDoc(@PathVariable Long id){
        log.info("IN CONTROLLER getStaffDoc");
        return staffDocumentsService.getDocumentsInfo(id);
    }

    @Secured(value = Role.ROLE_ADMIN)
    @RequestMapping(value = "{id}/staffDoc/{idDoc}", method = RequestMethod.DELETE)
    RestMessageDTO delStaffDoc(@PathVariable Long id, @PathVariable Long idDoc) {
        log.info("in delStaffDoc");
        return staffDocumentsService.delDocument(id,idDoc);
    }
    @RequestMapping(value = "{id}/staffDoc/{idDoc}", method = RequestMethod.GET)
    byte[] getStaffDocFile(@PathVariable Long id, @PathVariable int idDoc)throws IOException, SQLException  {
        log.info("in getStaffDocFile");
        return staffDocumentsService.getFile(id,idDoc);
    }


}
