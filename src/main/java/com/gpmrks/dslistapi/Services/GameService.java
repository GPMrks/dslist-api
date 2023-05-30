package com.gpmrks.dslistapi.Services;

import com.gpmrks.dslistapi.Dto.GameDTO;
import com.gpmrks.dslistapi.Dto.MinimalGameInfoDTO;

import java.util.List;

public interface GameService {

    List<MinimalGameInfoDTO> getAllGames();

    GameDTO getGameById(Long gameId);

    GameDTO createGame(GameDTO gameDTO);

    GameDTO updateGame(Long gameId, GameDTO gameDTO);

    GameDTO updatePartiallyGame(Long gameId, GameDTO gameDTO);

    void deleteGame(Long gameId);

}
