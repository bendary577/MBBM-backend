package com.mbbm.app.youcan.dto.products;

import java.util.List;

public class YoucanProductDTO {

    private String id;
    private String name;
    private String slug;
    private String public_url;
    private String thumbnail;
    private String description;
    private int price;

    private int cost_price;
    private boolean visibility;
    private int inventory;
    private List<YoucanProductImageDTO> images;

    public YoucanProductDTO(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getPublic_url() {
        return public_url;
    }

    public void setPublic_url(String public_url) {
        this.public_url = public_url;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCost_price() {
        return cost_price;
    }

    public void setCost_price(int cost_price) {
        this.cost_price = cost_price;
    }

    public boolean isVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public List<YoucanProductImageDTO> getImages() {
        return images;
    }

    public void setImages(List<YoucanProductImageDTO> images) {
        this.images = images;
    }
}
