package com.mbbm.app.whatsapp.messageTemplates;

import com.mbbm.app.whatsapp.general.Paging;

import java.util.List;

public class GetMessageTemplatesResponse {

    private List<MessageTemplatesResponse> data;
    private Paging paging;

    public List<MessageTemplatesResponse> getData() {
        return data;
    }

    public void setData(List<MessageTemplatesResponse> data) {
        this.data = data;
    }

    public Paging getPaging() {
        return paging;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }
}
