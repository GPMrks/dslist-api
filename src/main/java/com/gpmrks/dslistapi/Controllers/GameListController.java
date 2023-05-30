package com.gpmrks.dslistapi.Controllers;

import com.gpmrks.dslistapi.Dto.GameListDTO;
import com.gpmrks.dslistapi.Dto.GameListForm;
import com.gpmrks.dslistapi.Dto.MinimalGameInfoDTO;
import com.gpmrks.dslistapi.Services.GameListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/lists")
public class GameListController {

    private GameListService gameListService;

    @Autowired
    public GameListController(GameListService gameListService) {
        this.gameListService = gameListService;
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
        URI uri = uriComponentsBuilder.path("lists/{listId}").buildAndExpand(gameListSaved.getId()).toUri();
        return ResponseEntity.created(uri).body(gameListSaved);
    }
}
