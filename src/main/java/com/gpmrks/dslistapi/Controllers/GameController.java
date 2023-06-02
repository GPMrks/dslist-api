package com.gpmrks.dslistapi.Controllers;

import com.gpmrks.dslistapi.Dto.GameDTO;
import com.gpmrks.dslistapi.Dto.MinimalGameInfoDTO;
import com.gpmrks.dslistapi.Services.BelongingService;
import com.gpmrks.dslistapi.Services.GameService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/games")
@Tag(name = "Games Controller")
public class GameController {

    private GameService gameService;
    private BelongingService belongingService;

    @Autowired
    public GameController(GameService gameService, BelongingService belongingService) {
        this.gameService = gameService;
        this.belongingService = belongingService;
    }

    @GetMapping
    @Operation(summary = "Get All the Games", description = "Returns a list of all Games")
    public ResponseEntity<List<MinimalGameInfoDTO>> getAllGames() {
        List<MinimalGameInfoDTO> gameDTOList = gameService.getAllGames();
        return ResponseEntity.ok(gameDTOList);
    }

    @GetMapping("{gameId}")
    @Operation(summary = "Get a specific Game", description = "Returns a specific Game by its ID")
    public ResponseEntity<GameDTO> getGameById(@PathVariable Long gameId) {
        GameDTO gameDTO = gameService.getGameById(gameId);
        return ResponseEntity.ok(gameDTO);
    }

    @PostMapping
    @Operation(summary = "Create a Game", description = "Creates a Game")
    public ResponseEntity<GameDTO> createGame(@RequestBody GameDTO gameDTO, UriComponentsBuilder uriComponentsBuilder) {
        GameDTO game = gameService.createGame(gameDTO);
        URI uri = uriComponentsBuilder.path("/games/{gameId}").buildAndExpand(game.game().getId()).toUri();
        return ResponseEntity.created(uri).body(game);
    }

    @PutMapping("{gameId}")
    @Operation(summary = "Update the whole Game", description = "Updates the Game resource as a whole by its Game ID")
    public ResponseEntity<GameDTO> updateGame(@PathVariable Long gameId, @RequestBody GameDTO gameDTO) {
        GameDTO gameUpdated = gameService.updateGame(gameId, gameDTO);
        return ResponseEntity.ok(gameUpdated);
    }

    @PatchMapping("{gameId}")
    @Operation(summary = "Update a Game Attribute", description = "Updates a desired field of the Game by its ID")
    public ResponseEntity<GameDTO> updatePartiallyGame(@PathVariable Long gameId, @RequestBody GameDTO gameDTO) {
        GameDTO gameUpdatedPartially = gameService.updatePartiallyGame(gameId, gameDTO);
        return ResponseEntity.ok(gameUpdatedPartially);
    }

    @DeleteMapping("{gameId}")
    @Operation(summary = "Delete a Game", description = "Deletes a specific Game by its ID")
    public ResponseEntity<Void> deleteGame(@PathVariable Long gameId) {
        gameService.deleteGame(gameId);
        return ResponseEntity.noContent().build();
    }
}
