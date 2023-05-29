package com.gpmrks.dslistapi.Controllers;

import com.gpmrks.dslistapi.Dto.MinimalGameDTO;
import com.gpmrks.dslistapi.Services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<List<MinimalGameDTO>> getAllGames() {
        List<MinimalGameDTO> gameDTOList = gameService.getAllGames();
        return ResponseEntity.ok(gameDTOList);
    }
}
