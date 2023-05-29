package com.gpmrks.dslistapi.Services.Impl;

import com.gpmrks.dslistapi.Dto.MinimalGameDTO;
import com.gpmrks.dslistapi.Entities.Game;
import com.gpmrks.dslistapi.Repositories.GameRepository;
import com.gpmrks.dslistapi.Services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameServiceImpl implements GameService {

    private GameRepository gameRepository;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public List<MinimalGameDTO> getAllGames() {
        List<Game> gameList = gameRepository.findAll();
        return gameList.stream().map(MinimalGameDTO::new).toList();
    }
}
