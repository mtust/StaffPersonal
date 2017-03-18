package com.staff.personal.service.nominallyJobBook.impl;

import com.staff.personal.domain.nominallyJobBooks.Position;
import com.staff.personal.dto.RestMessageDTO;
import com.staff.personal.repository.PositionRepository;
import com.staff.personal.service.nominallyJobBook.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionServiceImpl implements PositionService{

    @Autowired
    PositionRepository positionRepository;

    @Override
    public List<Position> getPositions() {
        return positionRepository.findAll();
    }

    @Override
    public RestMessageDTO createPosition(Position position) {
        positionRepository.save(position);
        return new RestMessageDTO("Success", true);
    }

    @Override
    public Position updatePosition(Long id, Position position) {
        position.setId(id);
        position = positionRepository.save(position);
        return position;
    }

    @Override
    public Position getPosition(Long id) {
        return positionRepository.getOne(id);
    }

    @Override
    public RestMessageDTO deletePosition(Long id) {
        positionRepository.delete(id);
        return new RestMessageDTO("Success", true);
    }
}
