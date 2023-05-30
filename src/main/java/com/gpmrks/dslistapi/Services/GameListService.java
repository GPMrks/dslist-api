package com.gpmrks.dslistapi.Services;

import com.gpmrks.dslistapi.Dto.GameListDTO;
import com.gpmrks.dslistapi.Repositories.GameListRepository;

import java.util.List;

public interface GameListService {

    List<GameListDTO> getAllGameList();

}
