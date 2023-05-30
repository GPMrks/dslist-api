package com.gpmrks.dslistapi.Services.Impl;

import com.gpmrks.dslistapi.Dto.GameListDTO;
import com.gpmrks.dslistapi.Entities.GameList;
import com.gpmrks.dslistapi.Repositories.GameListRepository;
import com.gpmrks.dslistapi.Services.GameListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GameListServiceImpl implements GameListService {

    private GameListRepository gameListRepository;

    @Autowired
    public GameListServiceImpl(GameListRepository gameListRepository) {
        this.gameListRepository = gameListRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<GameListDTO> getAllGameList() {
        List<GameList> gameList = gameListRepository.findAll();
        return gameList.stream().map(GameListDTO::new).toList();
    }
}
