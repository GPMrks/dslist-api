package com.gpmrks.dslistapi.Services;

import com.gpmrks.dslistapi.Dto.GameListDTO;
import com.gpmrks.dslistapi.Dto.MinimalGameInfoDTO;

import java.util.List;

public interface GameListService {

    List<GameListDTO> getAllGameList();

    List<MinimalGameInfoDTO> searchByList(Long listId);
}