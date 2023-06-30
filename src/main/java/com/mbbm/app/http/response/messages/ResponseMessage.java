package com.mbbm.app.http.response.messages;

public class ResponseMessage {

    protected String message = "";
    protected String data = "";

    public ResponseMessage(){}
    public ResponseMessage(String message, String data){
        this.data = data;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
