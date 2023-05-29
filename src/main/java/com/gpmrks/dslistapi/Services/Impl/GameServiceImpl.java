package com.gpmrks.dslistapi.Services.Impl;

import com.gpmrks.dslistapi.Dto.GameDTO;
import com.gpmrks.dslistapi.Dto.MinimalGameInfoDTO;
import com.gpmrks.dslistapi.Entities.Game;
import com.gpmrks.dslistapi.Repositories.GameRepository;
import com.gpmrks.dslistapi.Services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GameServiceImpl implements GameService {

    private GameRepository gameRepository;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<MinimalGameInfoDTO> getAllGames() {
        List<Game> gameList = gameRepository.findAll();
        return gameList.stream().map(MinimalGameInfoDTO::new).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public GameDTO getGameById(Long gameId) {
        Game game = gameRepository.findById(gameId).get();
        return new GameDTO(game);
    }
}
