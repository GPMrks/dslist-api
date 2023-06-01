package com.gpmrks.dslistapi.Services;

import com.gpmrks.dslistapi.Dto.ListDTO;
import com.gpmrks.dslistapi.Dto.ListForm;
import com.gpmrks.dslistapi.Dto.MinimalGameInfoDTO;

import java.util.List;

public interface ListService {

    List<ListDTO> getAllGameList();
    List<MinimalGameInfoDTO> searchByList(Long listId);
    ListDTO getListById(Long listId);
    ListDTO createList(ListForm listForm);
}
