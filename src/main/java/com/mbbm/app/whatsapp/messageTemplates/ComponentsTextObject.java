package com.mbbm.app.whatsapp.messageTemplates;

import java.util.List;

public class ComponentsTextObject {

    private String type;
    private String text;

    private String format;

    private List<ComponentsTextObject> buttons;


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
