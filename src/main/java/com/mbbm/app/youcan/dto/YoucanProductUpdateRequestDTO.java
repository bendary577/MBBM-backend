package com.mbbm.app.youcan.dto;

import java.util.HashMap;

public class YoucanProductUpdateRequestDTO {

    //TODO: keys or product attributes should be enumerated to prevent adding non-required properties in the update request
    private HashMap<String, Object> updatedData;

    public YoucanProductUpdateRequestDTO(){}

    public HashMap<String, Object> getUpdatedData() {
        return updatedData;
    }

    public void setUpdatedData(HashMap<String, Object> updatedData) {
        this.updatedData = updatedData;
    }
}
