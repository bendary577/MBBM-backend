package com.mbbm.app.whatsapp.messages;

public class Text {
    private boolean preview_url;
    private String body;

    public boolean getPreview_url() {
        return preview_url;
    }

    public void setPreview_url(boolean preview_url) {
        this.preview_url = preview_url;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
