package com.mbbm.app.whatsapp.phones;

import com.mbbm.app.whatsapp.general.Paging;

import java.util.List;

public class BusinessAccountPhonesResponse {

    private List<PhoneResponseObject> data;
    private Paging paging;

    public BusinessAccountPhonesResponse(List<PhoneResponseObject> data) {
        this.data = data;
    }

    public List<PhoneResponseObject> getData() {
        return data;
    }

    public void setData(List<PhoneResponseObject> data) {
        this.data = data;
    }

    public Paging getPaging() {
        return paging;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }
}
