package com.gpmrks.dslistapi.Services;

import com.gpmrks.dslistapi.Dto.BelongingDTO;
import com.gpmrks.dslistapi.Dto.BelongingForm;
import com.gpmrks.dslistapi.Entities.Belonging;

public interface BelongingService {

    BelongingDTO registerGameToList(BelongingForm belongingForm);

//    BelongingDTO getBelonging();

    void orderGameList(Long listId, Long gameId, int destinationIndex);
}
