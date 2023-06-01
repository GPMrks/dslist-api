package com.gpmrks.dslistapi.Services.Impl;

import com.gpmrks.dslistapi.Dto.BelongingDTO;
import com.gpmrks.dslistapi.Dto.BelongingForm;
import com.gpmrks.dslistapi.Entities.Belonging;
import com.gpmrks.dslistapi.Entities.BelongingId;
import com.gpmrks.dslistapi.Entities.ListOfGames;
import com.gpmrks.dslistapi.Projections.BelongingInfoProjection;
import com.gpmrks.dslistapi.Projections.MinimalGameInfoProjection;
import com.gpmrks.dslistapi.Repositories.BelongingRepository;
import com.gpmrks.dslistapi.Repositories.GameRepository;
import com.gpmrks.dslistapi.Repositories.ListRepository;
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
    private ListRepository listRepository;

    @Autowired
    public BelongingServiceImpl(BelongingRepository belongingRepository, GameRepository gameRepository, ListRepository listRepository) {
        this.belongingRepository = belongingRepository;
        this.gameRepository = gameRepository;
        this.listRepository = listRepository;
    }

    @Override
    @Transactional
    public BelongingDTO registerGameToList(BelongingForm belongingForm) {
        ListOfGames listOfGamesSearch = listRepository.findById(belongingForm.listId()).get();
        BelongingId belongingId = new BelongingId(gameRepository.findById(belongingForm.gameId()).get(), listOfGamesSearch);
        List<MinimalGameInfoProjection> list = listRepository.searchByList(listOfGamesSearch.getId());
        BelongingDTO belongingDTO = new BelongingDTO(belongingId, list.size());
        Belonging belongingToRegister = new Belonging(belongingDTO);
        Belonging belongingRegistered = belongingRepository.save(belongingToRegister);
        return new BelongingDTO(belongingRegistered.getBelongingId(), belongingRegistered.getPosition());
    }

    @Override
    public void removeGameFromList(Long gameId) {
        belongingRepository.deleteByGameId(gameId);
    }

    @Override
    @Transactional
    public void orderGameList(Long listId, Long gameId, int destinationIndex) {
        List<MinimalGameInfoProjection> list = listRepository.searchByList(listId);

        BelongingInfoProjection belonging = belongingRepository.getBelongingByGameId(gameId, listId);

        int gamePosition = belonging.getPosition();

        MinimalGameInfoProjection gameToMove = list.remove(gamePosition);
        list.add(destinationIndex, gameToMove);

        int minPosition = Math.min(gamePosition, destinationIndex);
        int maxPosition = Math.max(gamePosition, destinationIndex);

        IntStream.rangeClosed(minPosition, maxPosition)
                .forEach(i -> belongingRepository.updateBelongingPosition(listId, list.get(i).getId(), i));
    }
}