package com.gpmrks.dslistapi.Dto;

import com.gpmrks.dslistapi.Entities.Belonging;
import com.gpmrks.dslistapi.Entities.BelongingId;

public class BelongingDTO {

    private BelongingId belongingId = new BelongingId();
    private Integer position;

    public BelongingDTO() {
    }

    public BelongingDTO(BelongingId belongingId, Integer position) {
        this.belongingId = belongingId;
        this.position = position;
    }

    public BelongingDTO(Belonging belonging) {
        this.belongingId = belonging.getBelongingId();
        this.position = belonging.getPosition();
    }

    public BelongingId getBelongingId() {
        return belongingId;
    }

    public void setBelongingId(BelongingId belongingId) {
        this.belongingId = belongingId;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}
