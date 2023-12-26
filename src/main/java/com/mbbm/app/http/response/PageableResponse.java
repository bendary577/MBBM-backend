package com.mbbm.app.http.response;

import com.mbbm.app.http.response.messages.ResponseMessage;
import java.io.Serializable;


public class PageableResponse extends ResponseMessage implements Serializable {

    private int totalPages;
    private long totalElements;
    private int numberOfElements ;
    private int size;
    private boolean isLastPage;
    private boolean isFirstPage;

    public PageableResponse(int totalPages,
                            long totalElements,
                            int numberOfElements,
                            int size,
                            boolean isLastPage,
                            boolean isFirstPage,
                            String message,
                            String data){
        super(message, data);
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.numberOfElements = numberOfElements;
        this.size= size;
        this.isLastPage = isLastPage;
        this.isFirstPage = isFirstPage;
    }

    public PageableResponse(){
        totalPages = 0;
        totalElements = 0;
        numberOfElements = 0;
        size= 0;
        isLastPage = false;
        isFirstPage = false;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isLastPage() {
        return isLastPage;
    }

    public void setLastPage(boolean lastPage) {
        isLastPage = lastPage;
    }

    public boolean isFirstPage() {
        return isFirstPage;
    }

    public void setFirstPage(boolean firstPage) {
        isFirstPage = firstPage;
    }
}
