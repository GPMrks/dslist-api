package com.gpmrks.dslistapi.Services.Impl;

import com.gpmrks.dslistapi.Dto.BelongingDTO;
import com.gpmrks.dslistapi.Dto.BelongingForm;
import com.gpmrks.dslistapi.Entities.Belonging;
import com.gpmrks.dslistapi.Entities.BelongingId;
import com.gpmrks.dslistapi.Entities.GameList;
import com.gpmrks.dslistapi.Projections.BelongingInfoProjection;
import com.gpmrks.dslistapi.Projections.MinimalGameInfoProjection;
import com.gpmrks.dslistapi.Repositories.BelongingRepository;
import com.gpmrks.dslistapi.Repositories.GameListRepository;
import com.gpmrks.dslistapi.Repositories.GameRepository;
import com.gpmrks.dslistapi.Services.BelongingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.IntStream;

@Service
public class BelongingServiceImpl implements BelongingService {

    private BelongingRepository belongingRepository;
    private GameRepository gameRepository;
    private GameListRepository gameListRepository;

    @Autowired
    public BelongingServiceImpl(BelongingRepository belongingRepository, GameRepository gameRepository, GameListRepository gameListRepository) {
        this.belongingRepository = belongingRepository;
        this.gameRepository = gameRepository;
        this.gameListRepository = gameListRepository;
    }

    @Override
    public BelongingDTO registerGameToList(BelongingForm belongingForm) {
        GameList gameListSearch = gameListRepository.findById(belongingForm.getListId()).get();
        BelongingId belongingId = new BelongingId(gameRepository.findById(belongingForm.getGameId()).get(), gameListSearch);
        List<MinimalGameInfoProjection> gameList = gameListRepository.searchByList(gameListSearch.getId());
        BelongingDTO belongingDTO = new BelongingDTO(belongingId, gameList.size());
        Belonging belongingToRegister = new Belonging(belongingDTO);
        Belonging belongingRegistered = belongingRepository.save(belongingToRegister);
        return new BelongingDTO(belongingRegistered);
    }

    @Override
    @Transactional
    public void orderGameList(Long listId, Long gameId, int destinationIndex) {
        List<MinimalGameInfoProjection> gameList = gameListRepository.searchByList(listId);

        BelongingInfoProjection belonging = belongingRepository.getBelongingByGameId(gameId, listId);

        int gamePosition = belonging.getPosition();

        MinimalGameInfoProjection gameToMove = gameList.remove(gamePosition);
        gameList.add(destinationIndex, gameToMove);

        int minPosition = Math.min(gamePosition, destinationIndex);
        int maxPosition = Math.max(gamePosition, destinationIndex);

        IntStream.rangeClosed(minPosition, maxPosition)
                .forEach(i -> belongingRepository.updateBelongingPosition(listId, gameList.get(i).getId(), i));
    }
}