package com.staff.personal.service.nominallyJobBook;

import com.staff.personal.domain.nominallyJobBooks.Position;
import com.staff.personal.dto.RestMessageDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PositionService {
    List<Position> getPositions();

    RestMessageDTO createPosition(Position position);

    Position updatePosition(Long id, Position position);

    Position getPosition(Long id);

    RestMessageDTO deletePosition(Long id);
}
