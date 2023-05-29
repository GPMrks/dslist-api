package com.gpmrks.dslistapi.Services;

import com.gpmrks.dslistapi.Dto.GameDTO;
import com.gpmrks.dslistapi.Dto.MinimalGameInfoDTO;
import com.gpmrks.dslistapi.Entities.Game;

import java.util.List;
import java.util.Optional;

public interface GameService {

    List<MinimalGameInfoDTO> getAllGames();

    GameDTO getGameById(Long gameId);

}
