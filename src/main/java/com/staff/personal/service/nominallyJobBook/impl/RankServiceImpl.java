package com.staff.personal.service.nominallyJobBook.impl;

import com.staff.personal.domain.nominallyJobBooks.Rank;
import com.staff.personal.dto.RestMessageDTO;
import com.staff.personal.repository.RankRepository;
import com.staff.personal.service.nominallyJobBook.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RankServiceImpl implements RankService{

    @Autowired
    RankRepository rankRepository;

    @Override
    public List<Rank> getRank() {
        return rankRepository.findAll();
    }

    @Override
    public RestMessageDTO createRank(Rank rank) {
         rankRepository.save(rank);
        return new RestMessageDTO("Success", true);
    }

    @Override
    public Rank updateRank(Long id, Rank rank) {

        rank.setId(id);
        rank = rankRepository.save(rank);
        return rank;
    }

    @Override
    public Rank getRank(Long id) {
        return rankRepository.getOne(id);
    }

    @Override
    public RestMessageDTO deleteRank(Long id) {
        rankRepository.delete(id);
        return new RestMessageDTO("Success", true);
    }
}
