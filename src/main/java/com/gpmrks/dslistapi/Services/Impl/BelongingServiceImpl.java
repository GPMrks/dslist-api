package com.gpmrks.dslistapi.Services.Impl;

import com.gpmrks.dslistapi.Dto.BelongingDTO;
import com.gpmrks.dslistapi.Dto.BelongingForm;
import com.gpmrks.dslistapi.Entities.Belonging;
import com.gpmrks.dslistapi.Entities.BelongingId;
import com.gpmrks.dslistapi.Repositories.BelongingRepository;
import com.gpmrks.dslistapi.Repositories.GameListRepository;
import com.gpmrks.dslistapi.Repositories.GameRepository;
import com.gpmrks.dslistapi.Services.BelongingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        BelongingId belongingId = new BelongingId(gameRepository.findById(belongingForm.getGameId()).get(), gameListRepository.findById(belongingForm.getListId()).get());
        BelongingDTO belongingDTO = new BelongingDTO(belongingId, belongingForm.getPosition());
        Belonging belongingToRegister = new Belonging(belongingDTO);
        Belonging belongingRegistered = belongingRepository.save(belongingToRegister);
        return new BelongingDTO(belongingRegistered);
    }
}