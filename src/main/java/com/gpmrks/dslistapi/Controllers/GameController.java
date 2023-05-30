package com.gpmrks.dslistapi.Controllers;

import com.gpmrks.dslistapi.Dto.GameDTO;
import com.gpmrks.dslistapi.Dto.MinimalGameInfoDTO;
import com.gpmrks.dslistapi.Entities.Game;
import com.gpmrks.dslistapi.Services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {

    private GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public ResponseEntity<List<MinimalGameInfoDTO>> getAllGames() {
        List<MinimalGameInfoDTO> gameDTOList = gameService.getAllGames();
        return ResponseEntity.ok(gameDTOList);
    }

    @GetMapping("{gameId}")
    public ResponseEntity<GameDTO> getGameById(@PathVariable Long gameId) {
        GameDTO gameDTO = gameService.getGameById(gameId);
        return ResponseEntity.ok(gameDTO);
    }

    @PostMapping
    public ResponseEntity<GameDTO> createGame(@RequestBody GameDTO gameDTO, UriComponentsBuilder uriComponentsBuilder) {
        GameDTO game = gameService.createGame(gameDTO);
        URI uri = uriComponentsBuilder.path("/games/{gameId}").buildAndExpand(game.getId()).toUri();
        return ResponseEntity.created(uri).body(game);
    }

    @PutMapping("{gameId}")
    public ResponseEntity<GameDTO> updateGame(@PathVariable Long gameId, @RequestBody GameDTO gameDTO) {
        GameDTO gameUpdated = gameService.updateGame(gameId, gameDTO);
        return ResponseEntity.ok(gameUpdated);
    }

    @PatchMapping ("{gameId}")
    public ResponseEntity<GameDTO> updatePartiallyGame(@PathVariable Long gameId, @RequestBody GameDTO gameDTO) {
        GameDTO gameUpdatedPartially = gameService.updatePartiallyGame(gameId, gameDTO);
        return ResponseEntity.ok(gameUpdatedPartially);
    }

    @DeleteMapping("{gameId}")
    public ResponseEntity<Void> deleteGame(@PathVariable Long gameId) {
        gameService.deleteGame(gameId);
        return ResponseEntity.noContent().build();
    }
}
