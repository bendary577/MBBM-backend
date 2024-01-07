package com.mbbm.app.youcan.dto;

import java.util.HashMap;

public class YoucanProductUpdateRequestDTO {

    //TODO: keys or product attributes should be enumerated to prevent adding non-required properties in the update request
    private HashMap<String, String> updatedData;

    public YoucanProductUpdateRequestDTO(){}

    public HashMap<String, String> getUpdatedValuesMap() {
        return updatedData;
    }

    public void setUpdatedValuesMap(HashMap<String, String> updatedValuesMap) {
        this.updatedData = updatedValuesMap;
    }
}
