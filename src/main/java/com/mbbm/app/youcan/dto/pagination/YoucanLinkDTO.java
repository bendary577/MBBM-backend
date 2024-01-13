package com.mbbm.app.youcan.dto.pagination;

public class YoucanLinkDTO {

    private String next;

    private String previous;

    public YoucanLinkDTO(){}

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }
}
