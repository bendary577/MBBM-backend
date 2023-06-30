package com.mbbm.app.whatsapp.messageTemplates;

import java.util.List;

public class MessageTemplatesResponse {
    private String name;
    private List<ComponentsTextObject> components;
    private String language;
    private String status;
    private String category;
    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ComponentsTextObject> getComponents() {
        return components;
    }

    public void setComponents(List<ComponentsTextObject> components) {
        this.components = components;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
