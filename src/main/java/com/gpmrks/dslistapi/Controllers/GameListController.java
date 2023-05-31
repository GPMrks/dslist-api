package com.gpmrks.dslistapi.Controllers;

import com.gpmrks.dslistapi.Dto.*;
import com.gpmrks.dslistapi.Services.BelongingService;
import com.gpmrks.dslistapi.Services.GameListService;
import com.gpmrks.dslistapi.Services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/lists")
public class GameListController {

    private GameListService gameListService;
    private BelongingService belongingService;
    private GameService gameService;

    @Autowired
    public GameListController(GameListService gameListService, BelongingService belongingService, GameService gameService) {
        this.gameListService = gameListService;
        this.belongingService = belongingService;
        this.gameService = gameService;
    }

    @GetMapping
    public ResponseEntity<List<GameListDTO>> getAllGameLists() {
        List<GameListDTO> gameListDTO = gameListService.getAllGameList();
        return ResponseEntity.ok(gameListDTO);
    }

    @GetMapping("{listId}")
    public ResponseEntity<List<MinimalGameInfoDTO>> searchByList(@PathVariable Long listId) {
        List<MinimalGameInfoDTO> minimalGameInfoDTOS = gameListService.searchByList(listId);
        return ResponseEntity.ok(minimalGameInfoDTOS);
    }

    @PostMapping
    public ResponseEntity<GameListDTO> createList(@RequestBody GameListForm gameListForm, UriComponentsBuilder uriComponentsBuilder) {
        GameListDTO gameListSaved = gameListService.createList(gameListForm);
        URI uri = uriComponentsBuilder.path("lists/{listId}").buildAndExpand(gameListSaved.id()).toUri();
        return ResponseEntity.created(uri).body(gameListSaved);
    }

    @PostMapping("order-game")
    public ResponseEntity<Void> orderGame(@RequestBody OrderForm orderForm) {
        belongingService.orderGameList(orderForm.listId(), orderForm.gameId(), orderForm.destinationIndex());
        return ResponseEntity.ok().build();
    }

    @PostMapping("belongs")
    public ResponseEntity<BelongingDTO> registerGameToList(@RequestBody BelongingForm belongingForm) {
        BelongingDTO belongingDTORegistered = belongingService.registerGameToList(belongingForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(belongingDTORegistered);
    }
}
