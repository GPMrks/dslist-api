package com.gpmrks.dslistapi.Services.Impl;

import com.gpmrks.dslistapi.Dto.GameListDTO;
import com.gpmrks.dslistapi.Dto.GameListForm;
import com.gpmrks.dslistapi.Dto.MinimalGameInfoDTO;
import com.gpmrks.dslistapi.Entities.GameList;
import com.gpmrks.dslistapi.Projections.MinimalGameInfoProjection;
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
        return gameList.stream().map(gameListToDto -> new GameListDTO(gameListToDto.getId(), gameListToDto.getName())).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<MinimalGameInfoDTO> searchByList(Long listId) {
        List<MinimalGameInfoProjection> minimalGameInfoProjections = gameListRepository.searchByList(listId);
        return minimalGameInfoProjections.stream()
                .map(minimalGameInfoProjection -> new MinimalGameInfoDTO(
                    minimalGameInfoProjection.getId(),
                    minimalGameInfoProjection.getTitle(),
                    minimalGameInfoProjection.getGameYear(),
                    minimalGameInfoProjection.getImageUrl(),
                    minimalGameInfoProjection.getShortDescription()))
                .toList();
    }

    @Override
    public GameListDTO getListById(Long listId) {
        GameList gameList = gameListRepository.findById(listId).get();
        return new GameListDTO(gameList.getId(), gameList.getName());
    }

    @Override
    public GameListDTO createList(GameListForm gameListForm) {
        GameList gameList = new GameList();
        gameList.setName(gameListForm.name());
        GameList gameListSaved = gameListRepository.save(gameList);
        return new GameListDTO(gameListSaved.getId(), gameListSaved.getName());
    }
}
