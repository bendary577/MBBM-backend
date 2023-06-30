package com.mbbm.app.whatsapp.messages;

import com.mbbm.app.whatsapp.messages.MessageLanguage;

public class MessageTemplate {

    private String name;
    private MessageLanguage language;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MessageLanguage getLanguage() {
        return language;
    }

    public void setLanguage(MessageLanguage language) {
        this.language = language;
    }
}