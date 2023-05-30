package com.gpmrks.dslistapi.Services;

import com.gpmrks.dslistapi.Dto.BelongingDTO;
import com.gpmrks.dslistapi.Dto.BelongingForm;

public interface BelongingService {

    BelongingDTO registerGameToList(BelongingForm belongingForm);

}
