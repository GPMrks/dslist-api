package com.gpmrks.dslistapi.Services.Impl;

import com.gpmrks.dslistapi.Dto.GameDTO;
import com.gpmrks.dslistapi.Dto.MinimalGameInfoDTO;
import com.gpmrks.dslistapi.Entities.Game;
import com.gpmrks.dslistapi.Repositories.GameRepository;
import com.gpmrks.dslistapi.Services.GameService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GameServiceImpl implements GameService {

    private GameRepository gameRepository;

    private ModelMapper modelMapper;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository, ModelMapper modelMapper) {
        this.gameRepository = gameRepository;
        this.modelMapper = modelMapper;
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

    @Override
    @Transactional
    public GameDTO createGame(GameDTO gameDTO) {
        Game game = new Game(gameDTO);
        Game gameCreated = gameRepository.save(game);
        gameDTO.setId(gameCreated.getId());
        return new GameDTO(gameCreated);
    }

    @Override
    @Transactional
    public GameDTO updateGame(Long gameId, GameDTO gameDTO) {
        Game game = gameRepository.findById(gameId).get();
        game.setTitle(gameDTO.getTitle());
        game.setYear(gameDTO.getYear());
        game.setGenre(gameDTO.getGenre());
        game.setScore(gameDTO.getScore());
        game.setImageUrl(gameDTO.getImageUrl());
        game.setPlatforms(gameDTO.getPlatforms());
        game.setShortDescription(gameDTO.getShortDescription());
        game.setLongDescription(gameDTO.getLongDescription());
        Game gameUpdated = gameRepository.save(game);
        return new GameDTO(gameUpdated);
    }

    @Override
    @Transactional
    public GameDTO updatePartiallyGame(Long gameId, GameDTO gameDTO) {
        Game game = gameRepository.findById(gameId).get();
        modelMapper.map(gameDTO, game);
        Game gameUpdatedPartially = gameRepository.save(game);
        return new GameDTO(gameUpdatedPartially);
    }

    @Override
    public void deleteGame(Long gameId) {
        gameRepository.deleteById(gameId);
    }
}
