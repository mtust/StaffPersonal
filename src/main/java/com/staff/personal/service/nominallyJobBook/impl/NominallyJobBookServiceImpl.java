package com.staff.personal.service.nominallyJobBook.impl;

import com.staff.personal.domain.Staff;
import com.staff.personal.domain.nominallyJobBooks.NominallyJobBook;
import com.staff.personal.domain.nominallyJobBooks.NominallyJobBookParent;
import com.staff.personal.domain.nominallyJobBooks.Position;
import com.staff.personal.dto.RestMessageDTO;
import com.staff.personal.dto.nominallyJobBook.PoorNominallyJobBookDTO;
import com.staff.personal.repository.NominallyJobBook.NominallyJobBookParentRepository;
import com.staff.personal.repository.NominallyJobBook.NominallyJobBookRepository;
import com.staff.personal.repository.RegionRepository;
import com.staff.personal.repository.StaffRepository;
import com.staff.personal.service.nominallyJobBook.NominallyJobBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
    public RestMessageDTO createNominalJobBook(PoorNominallyJobBookDTO poorNominallyJobBookDTO) {
        NominallyJobBook nominallyJobBook = this.createNominallyJobBook(poorNominallyJobBookDTO);
        log.info("Nominally job book before create: " + nominallyJobBook);
        nominallyJobBook = nominallyJobBookRepository.save(nominallyJobBook);
        log.info("Nominally job book after create: " + nominallyJobBook);
        log.info("region " + regionRepository.findOne(poorNominallyJobBookDTO.getRegion().getId()));
        nominallyJobBook.setRegion(regionRepository.findOne(poorNominallyJobBookDTO.getRegion().getId()));
        nominallyJobBook = nominallyJobBookRepository.save(nominallyJobBook);
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
    public RestMessageDTO deleteNominalJobBook(Long id) {
        nominallyJobBookRepository.delete(id);
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
    public List<Staff> getStaffByNominallyJobBook(Long nominallyJobBookId) {
        NominallyJobBook nominallyJobBook = nominallyJobBookRepository.getOne(nominallyJobBookId);
        List<Staff> staffs = new ArrayList<>();
        for(Position position: nominallyJobBook.getPositions()){
            staffs.addAll(staffRepository.findByMainStaffPosition(position.getCode()));
        }
        return staffs;
    }



}

