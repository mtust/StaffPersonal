package com.staff.personal.service.nominallyJobBook;

import com.staff.personal.domain.nominallyJobBooks.NominallyJobBook;
import com.staff.personal.domain.nominallyJobBooks.NominallyJobBookParent;
import com.staff.personal.dto.GetStaffDTO;
import com.staff.personal.dto.RestMessageDTO;
import com.staff.personal.dto.nominallyJobBook.ParentNominallyJobBookDTO;
import com.staff.personal.dto.nominallyJobBook.PoorNominallyJobBookDTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mtustanovskyy on 1/15/17.
 */
@Service
public interface NominallyJobBookService {


    PoorNominallyJobBookDTO getPoorNominallyJobBook(Long id);

    NominallyJobBook getNominallyJobBook(Long id);

    List<NominallyJobBook> getNominallyJobBooks();

    List<NominallyJobBook> getNominallyJobBooksByRegion(Long regionId);

    List<PoorNominallyJobBookDTO> getPoorNominallyJobBooks();

    List<PoorNominallyJobBookDTO> getPoorNominallyJobBooksByRegion(Long regionId);

    RestMessageDTO createNominalJobBook(PoorNominallyJobBookDTO poorNominallyJobBookDTO, Long parentId);

    NominallyJobBook createNominalJobBook(NominallyJobBook nominallyJobBook, Long parentId);

    RestMessageDTO editNominalJobBook(PoorNominallyJobBookDTO poorNominallyJobBookDTO);

    RestMessageDTO deleteNominalJobBook(Long id);

    List<NominallyJobBookParent> getNominallyJobBookParent();

    NominallyJobBookParent getNominallyJobBookParent(Long id);

    RestMessageDTO createNominalJobBookParent(NominallyJobBookParent nominallyJobBookParent);

    List<GetStaffDTO> getStaffByNominallyJobBook(Long nominallyJobBookId);

    RestMessageDTO updateParent(Long parentId, ParentNominallyJobBookDTO name);

    RestMessageDTO addPosition(Long id, List<Long> positionIds);

    RestMessageDTO updateNominalJobBook(NominallyJobBook nominallyJobBook, Long id);

    RestMessageDTO deleteParent(Long id);
}
