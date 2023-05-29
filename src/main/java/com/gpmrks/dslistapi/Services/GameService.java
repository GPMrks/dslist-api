package com.gpmrks.dslistapi.Services;

import com.gpmrks.dslistapi.Dto.MinimalGameInfoDTO;

import java.util.List;

public interface GameService {

    List<MinimalGameInfoDTO> getAllGames();

}
