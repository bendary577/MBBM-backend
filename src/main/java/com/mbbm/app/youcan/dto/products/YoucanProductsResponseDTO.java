package com.mbbm.app.youcan.dto.products;

import com.mbbm.app.youcan.dto.pagination.YoucanMetaDTO;

import java.util.List;

public class YoucanProductsResponseDTO {

    private List<YoucanProductDTO> data;
    private YoucanMetaDTO meta;

    public YoucanProductsResponseDTO(){}


    public List<YoucanProductDTO> getData() {
        return data;
    }

    public void setData(List<YoucanProductDTO> data) {
        this.data = data;
    }

    public YoucanMetaDTO getMeta() {
        return meta;
    }

    public void setMeta(YoucanMetaDTO meta) {
        this.meta = meta;
    }
}
