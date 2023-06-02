package com.gpmrks.dslistapi.Controllers;

import com.gpmrks.dslistapi.Dto.*;
import com.gpmrks.dslistapi.Services.BelongingService;
import com.gpmrks.dslistapi.Services.GameService;
import com.gpmrks.dslistapi.Services.ListService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Lists Controller")
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
    @Operation(summary = "Get all Lists of Games", description = "Returns a list of all Lists of Games")
    public ResponseEntity<List<ListDTO>> getAllGameLists() {
        List<ListDTO> listDTO = listService.getAllGameList();
        return ResponseEntity.ok(listDTO);
    }

    @GetMapping("{listId}")
    @Operation(summary = "Get a specific List of Games", description = "Returns all the games of a specific list of games")
    public ResponseEntity<List<MinimalGameInfoDTO>> searchByList(@PathVariable Long listId) {
        List<MinimalGameInfoDTO> minimalGameInfoDTOS = listService.searchByList(listId);
        return ResponseEntity.ok(minimalGameInfoDTOS);
    }

    @PostMapping
    @Operation(summary = "Create a List of Games", description = "Creates a new List of Games")
    public ResponseEntity<ListDTO> createList(@RequestBody @Valid ListForm listForm, UriComponentsBuilder uriComponentsBuilder) {
        ListDTO listSaved = listService.createList(listForm);
        URI uri = uriComponentsBuilder.path("lists/{listId}").buildAndExpand(listSaved.id()).toUri();
        return ResponseEntity.created(uri).body(listSaved);
    }

    @PostMapping("{listId}/order-game")
    @Operation(summary = "Order a Game in a List of Games", description = "Set a new position to a Game in the List by Game ID and the Position")
    public ResponseEntity<Void> orderGame(@PathVariable Long listId, @RequestBody @Valid OrderForm orderForm) {
        belongingService.orderGameList(listId, orderForm.gameId(), orderForm.destinationIndex());
        return ResponseEntity.ok().build();
    }

    @PostMapping("belongs")
    @Operation(summary = "Register Game to List of Games", description = "Register a Game to a List of Games")
    public ResponseEntity<BelongingDTO> registerGameToList(@RequestBody @Valid BelongingForm belongingForm) {
        BelongingDTO belongingDTORegistered = belongingService.registerGameToList(belongingForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(belongingDTORegistered);
    }
}
