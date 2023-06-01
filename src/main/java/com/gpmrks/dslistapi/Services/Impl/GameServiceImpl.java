package com.gpmrks.dslistapi.Services.Impl;

import com.gpmrks.dslistapi.Dto.GameDTO;
import com.gpmrks.dslistapi.Dto.MinimalGameInfoDTO;
import com.gpmrks.dslistapi.Entities.Game;
import com.gpmrks.dslistapi.Exceptions.GameNotFoundException;
import com.gpmrks.dslistapi.Repositories.GameRepository;
import com.gpmrks.dslistapi.Services.BelongingService;
import com.gpmrks.dslistapi.Services.GameService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {

    private GameRepository gameRepository;
    private BelongingService belongingService;
    private ModelMapper modelMapper;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository, BelongingService belongingService, ModelMapper modelMapper) {
        this.gameRepository = gameRepository;
        this.modelMapper = modelMapper;
        this.belongingService = belongingService;
    }

    @Override
    @Transactional(readOnly = true)
    public List<MinimalGameInfoDTO> getAllGames() {
        List<Game> gameList = gameRepository.findAll();
        return gameList.stream().map(game -> new MinimalGameInfoDTO(
                game.getId(),
                game.getTitle(),
                game.getYear(),
                game.getImageUrl(),
                game.getShortDescription()
        )).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public GameDTO getGameById(Long gameId) {
        Game game = checkIfGameExists(gameId);
        return new GameDTO(game);
    }

    @Override
    @Transactional
    public GameDTO createGame(GameDTO gameDTO) {
        Game game = new Game(gameDTO);
        Game gameCreated = gameRepository.save(game);
        gameDTO.game().setId(gameCreated.getId());
        return new GameDTO(gameCreated);
    }

    @Override
    @Transactional
    public GameDTO updateGame(Long gameId, GameDTO gameDTO) {
        Game game = checkIfGameExists(gameId);
        game.setTitle(gameDTO.game().getTitle());
        game.setYear(gameDTO.game().getYear());
        game.setGenre(gameDTO.game().getGenre());
        game.setScore(gameDTO.game().getScore());
        game.setImageUrl(gameDTO.game().getImageUrl());
        game.setPlatforms(gameDTO.game().getPlatforms());
        game.setShortDescription(gameDTO.game().getShortDescription());
        game.setLongDescription(gameDTO.game().getLongDescription());
        Game gameUpdated = gameRepository.save(game);
        return new GameDTO(gameUpdated);
    }

    @Override
    @Transactional
    public GameDTO updatePartiallyGame(Long gameId, GameDTO gameDTO) {
        Game game = checkIfGameExists(gameId);
        modelMapper.map(gameDTO.game(), game);
        Game gameUpdatedPartially = gameRepository.save(game);
        return new GameDTO(gameUpdatedPartially);
    }

    @Override
    @Transactional
    public void deleteGame(Long gameId) {
        checkIfGameExists(gameId);
        belongingService.removeGameFromList(gameId);
        gameRepository.deleteById(gameId);
    }

    private Game checkIfGameExists(Long gameId) {
        Optional<Game> optionalGame = gameRepository.findById(gameId);
        final Game game;

        if (optionalGame.isPresent()) {
            game = optionalGame.get();
        } else {
            throw new GameNotFoundException(gameId);
        }
        return game;
    }
}
