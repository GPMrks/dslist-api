package com.gpmrks.dslistapi.Controllers;

import com.gpmrks.dslistapi.Dto.*;
import com.gpmrks.dslistapi.Services.BelongingService;
import com.gpmrks.dslistapi.Services.GameService;
import com.gpmrks.dslistapi.Services.ListService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/lists")
public class ListController {

    private ListService listService;
    private BelongingService belongingService;
    private GameService gameService;

    @Autowired
    public ListController(ListService listService, BelongingService belongingService, GameService gameService) {
        this.listService = listService;
        this.belongingService = belongingService;
        this.gameService = gameService;
    }

    @GetMapping
    public ResponseEntity<List<ListDTO>> getAllGameLists() {
        List<ListDTO> listDTO = listService.getAllGameList();
        return ResponseEntity.ok(listDTO);
    }

    @GetMapping("{listId}")
    public ResponseEntity<List<MinimalGameInfoDTO>> searchByList(@PathVariable Long listId) {
        List<MinimalGameInfoDTO> minimalGameInfoDTOS = listService.searchByList(listId);
        return ResponseEntity.ok(minimalGameInfoDTOS);
    }

    @PostMapping
    public ResponseEntity<ListDTO> createList(@RequestBody @Valid ListForm listForm, UriComponentsBuilder uriComponentsBuilder) {
        ListDTO listSaved = listService.createList(listForm);
        URI uri = uriComponentsBuilder.path("lists/{listId}").buildAndExpand(listSaved.id()).toUri();
        return ResponseEntity.created(uri).body(listSaved);
    }

    @PostMapping("order-game")
    public ResponseEntity<Void> orderGame(@RequestBody @Valid OrderForm orderForm) {
        belongingService.orderGameList(orderForm.listId(), orderForm.gameId(), orderForm.destinationIndex());
        return ResponseEntity.ok().build();
    }

    @PostMapping("belongs")
    public ResponseEntity<BelongingDTO> registerGameToList(@RequestBody @Valid BelongingForm belongingForm) {
        BelongingDTO belongingDTORegistered = belongingService.registerGameToList(belongingForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(belongingDTORegistered);
    }
}
