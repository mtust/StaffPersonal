package com.staff.personal.service.nominallyJobBook.impl;

import com.staff.personal.domain.Region;
import com.staff.personal.domain.nominallyJobBooks.NominallyJobBook;
import com.staff.personal.domain.nominallyJobBooks.NominallyJobBookParent;
import com.staff.personal.domain.nominallyJobBooks.Position;
import com.staff.personal.dto.GetStaffDTO;
import com.staff.personal.dto.RestMessageDTO;
import com.staff.personal.dto.nominallyJobBook.NominallyJobBookParentStaffDTO;
import com.staff.personal.dto.nominallyJobBook.NominallyJobBookStaffDTO;
import com.staff.personal.dto.nominallyJobBook.ParentNominallyJobBookDTO;
import com.staff.personal.dto.nominallyJobBook.PoorNominallyJobBookDTO;
import com.staff.personal.repository.NominallyJobBook.NominallyJobBookParentRepository;
import com.staff.personal.repository.NominallyJobBook.NominallyJobBookRepository;
import com.staff.personal.repository.PositionRepository;
import com.staff.personal.repository.RegionRepository;
import com.staff.personal.repository.StaffRepository;
import com.staff.personal.service.StaffService;
import com.staff.personal.service.nominallyJobBook.NominallyJobBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by mtustanovskyy on 1/15/17.
 */
@Service
@Slf4j
public class NominallyJobBookServiceImpl implements NominallyJobBookService {

    @Autowired
    private NominallyJobBookRepository nominallyJobBookRepository;

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private NominallyJobBookParentRepository nominallyJobBookParentRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private StaffService staffService;

    @Override
    public PoorNominallyJobBookDTO getPoorNominallyJobBook(Long id) {
        NominallyJobBook nominallyJobBook = nominallyJobBookRepository.getOne(id);
        return this.createPoorNominallyJobBookDTO(nominallyJobBook);
    }

    @Override
    public NominallyJobBook getNominallyJobBook(Long id) {
        return nominallyJobBookRepository.getOne(id);
    }

    @Override
    public List<NominallyJobBook> getNominallyJobBooks() {
        return nominallyJobBookRepository.findAll();
    }

    @Override
    public List<NominallyJobBook> getNominallyJobBooksByRegion(Long regionId) {
        return nominallyJobBookRepository.findByRegionId(regionId);
    }

    @Override
    public List<PoorNominallyJobBookDTO> getPoorNominallyJobBooks() {
        List<NominallyJobBook> nominallyJobBooks = nominallyJobBookRepository.findAll();
        log.info("all books: " + nominallyJobBooks);
        if(nominallyJobBooks.isEmpty()){
            return new ArrayList<>();
        }
        return nominallyJobBooks.stream().map(
                nominallyJobBook -> this.createPoorNominallyJobBookDTO(nominallyJobBook)).collect(Collectors.toList());
    }

    @Override
    public List<PoorNominallyJobBookDTO> getPoorNominallyJobBooksByRegion(Long regionId) {
        List<NominallyJobBook> nominallyJobBooks = nominallyJobBookRepository.findByRegionId(regionId);
        if(nominallyJobBooks.isEmpty()){
            return new ArrayList<>();
        }
        return nominallyJobBooks.stream().map(
                nominallyJobBook -> this.createPoorNominallyJobBookDTO(nominallyJobBook)).collect(Collectors.toList());
    }

    @Override
    public RestMessageDTO createNominalJobBook(PoorNominallyJobBookDTO poorNominallyJobBookDTO, Long parentId) {
        NominallyJobBook nominallyJobBook = this.createNominallyJobBook(poorNominallyJobBookDTO);
        log.info("Nominally job book before create: " + nominallyJobBook);
        nominallyJobBook = nominallyJobBookRepository.save(nominallyJobBook);
        log.info("Nominally job book after create: " + nominallyJobBook);
        log.info("region " + regionRepository.findOne(poorNominallyJobBookDTO.getRegion().getId()));
        nominallyJobBook.setRegion(regionRepository.findOne(poorNominallyJobBookDTO.getRegion().getId()));
        nominallyJobBook = nominallyJobBookRepository.save(nominallyJobBook);
        NominallyJobBookParent nominallyJobBookParent  = nominallyJobBookParentRepository.getOne(parentId);
        if(nominallyJobBookParent == null){
            throw new RuntimeException("Категорії для створення посадової книги не знайдено");
        }
        List<NominallyJobBook> nominallyJobBookList = nominallyJobBookParent.getNominallyJobBooks();
        nominallyJobBookList.add(nominallyJobBook);
        nominallyJobBookParent.setNominallyJobBooks(nominallyJobBookList);
        nominallyJobBookParentRepository.save(nominallyJobBookParent);
        log.info("Nominally job book after create with region: " + nominallyJobBook);
        return new RestMessageDTO("Success", true);
    }

    @Override
    public RestMessageDTO editNominalJobBook(PoorNominallyJobBookDTO poorNominallyJobBookDTO) {
        NominallyJobBook nominallyJobBook = nominallyJobBookRepository.getOne(poorNominallyJobBookDTO.getId());
        nominallyJobBook = this.createNominallyJobBook(poorNominallyJobBookDTO, nominallyJobBook);
        nominallyJobBookRepository.save(nominallyJobBook);
        return new RestMessageDTO("Success", true);
    }

    @Override
    public RestMessageDTO updateNominalJobBook(NominallyJobBook nominallyJobBook, Long id) {
        log.info("nominallyJobBook: " + nominallyJobBook);
        nominallyJobBook.setId(id);
        Region region =  regionRepository.findOne(nominallyJobBook.getRegion().getId());
        nominallyJobBook.setRegion(region);
        List<Position> positions = new ArrayList<>();
        for(Position position: nominallyJobBook.getPositions()) {
            positions.add(positionRepository.findOne(position.getId()));
        }
        nominallyJobBook.setPositions(positions);
        nominallyJobBook = nominallyJobBookRepository.save(nominallyJobBook);
        return new RestMessageDTO("Success", true);
    }


    private PoorNominallyJobBookDTO createPoorNominallyJobBookDTO(NominallyJobBook nominallyJobBook){
        PoorNominallyJobBookDTO poorNominallyJobBookDTO = new PoorNominallyJobBookDTO();
        poorNominallyJobBookDTO.setRegion(nominallyJobBook.getRegion());
        poorNominallyJobBookDTO.setCode(nominallyJobBook.getCode());
        poorNominallyJobBookDTO.setId(nominallyJobBook.getId());
        poorNominallyJobBookDTO.setName(nominallyJobBook.getName());
        log.info("poor book: " + poorNominallyJobBookDTO);
        return poorNominallyJobBookDTO;
    }

    @Override
    public RestMessageDTO deleteNominalJobBook(Long id) {
        this.removeFromParentList(this.setPositionNull(id));
        this.delete(id);
        return new RestMessageDTO("Success", true);
    }


    private void  deleteChildFromParent(List<NominallyJobBook> nominallyJobBooks){
        for (NominallyJobBook njb: nominallyJobBooks) {
            this.setPositionNull(njb.getId());
        }
        for(int i = 0; i < nominallyJobBooks.size(); i++){
            this.delete(nominallyJobBooks.get(i).getId());
        }

    }


    @Transactional
    private NominallyJobBook setPositionNull(Long id){
        NominallyJobBook nominallyJobBook = nominallyJobBookRepository.findOne(id);
        nominallyJobBook.setPositions(new ArrayList<>());
        return nominallyJobBookRepository.save(nominallyJobBook);

    }

    @Transactional
    private void removeFromParentList(NominallyJobBook nominallyJobBook){
        NominallyJobBookParent nominallyJobBookParent = nominallyJobBookParentRepository.findOneByNominallyJobBooksContains(nominallyJobBook);
        log.info(nominallyJobBookParent.toString());
        List<NominallyJobBook> nominallyJobBooks = nominallyJobBookParent.getNominallyJobBooks();
        nominallyJobBooks.remove(nominallyJobBook);
        nominallyJobBookParent.setNominallyJobBooks(nominallyJobBooks);
        nominallyJobBookParentRepository.save(nominallyJobBookParent);
    }

    @Transactional
    private void delete(Long id){
        nominallyJobBookRepository.delete(id);
    }

    private NominallyJobBook createNominallyJobBook(PoorNominallyJobBookDTO poorNominallyJobBookDTO){
        NominallyJobBook nominallyJobBook = new NominallyJobBook();
        nominallyJobBook.setRegion(poorNominallyJobBookDTO.getRegion());
        nominallyJobBook.setName(poorNominallyJobBookDTO.getName());
        nominallyJobBook.setCode(poorNominallyJobBookDTO.getCode());
        return nominallyJobBook;
    }

    private NominallyJobBook createNominallyJobBook(PoorNominallyJobBookDTO poorNominallyJobBookDTO, NominallyJobBook nominallyJobBook){
        nominallyJobBook.setName(poorNominallyJobBookDTO.getName());
        nominallyJobBook.setCode(poorNominallyJobBookDTO.getCode());
        return nominallyJobBook;
    }


    @Override
    public List<NominallyJobBookParent> getNominallyJobBookParent() {
        return nominallyJobBookParentRepository.findAll();
    }

    @Override
    public NominallyJobBookParent getNominallyJobBookParent(Long id) {
        return nominallyJobBookParentRepository.getOne(id);
    }

    @Override
    public RestMessageDTO createNominalJobBookParent(NominallyJobBookParent nominallyJobBookParent) {
        log.info("create nominallyJobBookParent: " + nominallyJobBookParent);
        nominallyJobBookParentRepository.save(nominallyJobBookParent);
        return new RestMessageDTO("Success", true);
    }

    @Override
    public List<GetStaffDTO> getStaffByNominallyJobBook(Long nominallyJobBookId) {
        NominallyJobBook nominallyJobBook = nominallyJobBookRepository.getOne(nominallyJobBookId);
        List<GetStaffDTO> staffs = new ArrayList<>();
        for(Position position: nominallyJobBook.getPositions()){
            staffs.addAll(staffService.getStaffByPositionCode(position.getCode()));
        }
        return staffs;
    }


    @Override
    public RestMessageDTO updateParent(Long parentId, ParentNominallyJobBookDTO parentNominallyJobBookDTO) {
        NominallyJobBookParent oldNominallyJobBookParent = nominallyJobBookParentRepository.findOne(parentId);
        if(parentNominallyJobBookDTO.getName() != null && parentNominallyJobBookDTO.getName() != ""){
            oldNominallyJobBookParent.setName(parentNominallyJobBookDTO.getName());
        }
        if(parentNominallyJobBookDTO.getAddress() != null && parentNominallyJobBookDTO.getAddress() != ""){
            oldNominallyJobBookParent.setAddress(parentNominallyJobBookDTO.getAddress());
        }
        if(parentNominallyJobBookDTO.getLocation() != null && parentNominallyJobBookDTO.getLocation() != ""){
            oldNominallyJobBookParent.setLocation(parentNominallyJobBookDTO.getLocation());
        }
        if(parentNominallyJobBookDTO.getStartAction() != null && parentNominallyJobBookDTO.getStartAction() != ""){
            oldNominallyJobBookParent.setStartAction(parentNominallyJobBookDTO.getStartAction());
        }
        if(parentNominallyJobBookDTO.getStateNumber() != null && parentNominallyJobBookDTO.getStateNumber() != ""){
            oldNominallyJobBookParent.setStateNumber(parentNominallyJobBookDTO.getStateNumber());
        }
        nominallyJobBookParentRepository.save(oldNominallyJobBookParent);
        return new RestMessageDTO("Success", true);
    }

    @Override
    @Transactional
    public NominallyJobBook createNominalJobBook(NominallyJobBook nominallyJobBook, Long parentId) {
        log.info("nominallyJobBook: " + nominallyJobBook);
        log.info("parentId: " + parentId);
        Region region = regionRepository.findOne(nominallyJobBook.getRegion().getId());
        nominallyJobBook.setRegion(region);
        nominallyJobBook.setPositions(new ArrayList<Position>());
        log.info(nominallyJobBook.toString());
        nominallyJobBook = nominallyJobBookRepository.save(nominallyJobBook);
        NominallyJobBookParent nominallyJobBookParent  = nominallyJobBookParentRepository.getOne(parentId);
        if(nominallyJobBookParent == null){
            throw new RuntimeException("Категорії для створення посадової книги не знайдено");
        }
        List<NominallyJobBook> nominallyJobBookList = nominallyJobBookParent.getNominallyJobBooks();
        log.info(nominallyJobBookList.toString());
        nominallyJobBookList.add(nominallyJobBook);
        nominallyJobBookParent.setNominallyJobBooks(nominallyJobBookList);
        nominallyJobBookParentRepository.save(nominallyJobBookParent);

        return nominallyJobBook;
    }

    @Override
    @Transactional
    public RestMessageDTO addPosition(Long id, List<Long> positionIds) {
        NominallyJobBook nominallyJobBook = nominallyJobBookRepository.findOne(id);
        List<Position> positions = nominallyJobBook.getPositions();
        log.info(nominallyJobBook.toString());
        log.info(positionRepository.findAll(positionIds).toString());
        log.info(positionIds.toString());
        positions.addAll(positionRepository.findAll(positionIds));
        nominallyJobBook.setPositions(positions);
        nominallyJobBookRepository.save(nominallyJobBook);
        return new RestMessageDTO("Success", true);
    }

    @Override
    @Transactional
    public RestMessageDTO deleteParent(Long id) {
        NominallyJobBookParent nominallyJobBookParent = nominallyJobBookParentRepository.findOne(id);
        List<NominallyJobBook> nominallyJobBookList = nominallyJobBookParent.getNominallyJobBooks();
        log.info(nominallyJobBookList.toString());
        this.deleteChildFromParent(nominallyJobBookList);
        nominallyJobBookParentRepository.delete(id);
        return new RestMessageDTO("Success", true);
    }

    @Override
    public NominallyJobBookParentStaffDTO getStaffByParentNominallyJobBook(Long parentId) {
        List<NominallyJobBookStaffDTO> nominallyJobBookStaffDTOS = new ArrayList<>();
        NominallyJobBookParent nominallyJobBookParent = nominallyJobBookParentRepository.findOne(parentId);
        List<NominallyJobBook> nominallyJobBooks = nominallyJobBookParent.getNominallyJobBooks();
        Map<NominallyJobBook, List<GetStaffDTO>> nominallyJobBookListMap = new HashMap<>();
        for(NominallyJobBook nominallyJobBook: nominallyJobBooks) {
            if (!nominallyJobBook.getPositions().isEmpty()) {
                List<GetStaffDTO> staffs = new ArrayList<>();
                for (Position position : nominallyJobBook.getPositions()) {
                    staffs.addAll(staffService.getStaffByPositionCode(position.getCode()));
                }
                NominallyJobBookStaffDTO nominallyJobBookStaffDTO = new NominallyJobBookStaffDTO();
                nominallyJobBookStaffDTO.setGetStaffDTOS(staffs);
                nominallyJobBookStaffDTO.setNominallyJobBook(nominallyJobBook);
                nominallyJobBookStaffDTOS.add(nominallyJobBookStaffDTO);
            }
        }
        NominallyJobBookParentStaffDTO nominallyJobBookParentStaffDTO =  new NominallyJobBookParentStaffDTO();
        nominallyJobBookParentStaffDTO.setNominallyJobBookStaffDTO(nominallyJobBookStaffDTOS);
        nominallyJobBookParentStaffDTO.setNominallyJobBookParent(nominallyJobBookParent);
        return nominallyJobBookParentStaffDTO;
    }
}

