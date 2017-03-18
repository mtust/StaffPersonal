package com.staff.personal.service.nominallyJobBook;


import com.staff.personal.domain.nominallyJobBooks.Rank;
import com.staff.personal.dto.RestMessageDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RankService {
    List<Rank> getRank();

    RestMessageDTO createRank(Rank rank);

    Rank updateRank(Long id, Rank Rabj);

    Rank getRank(Long id);

    RestMessageDTO deleteRank(Long id);
}
