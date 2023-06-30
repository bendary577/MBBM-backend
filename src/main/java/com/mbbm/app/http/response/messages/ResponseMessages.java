package com.mbbm.app.http.response.messages;

public class ResponseMessages {

    //---------------------- GENERAL ----------------------------------

    public static final String UNKNOWN_ERROR = "Server Error ... a problem occurred while we are processing your data";

    //---------------------- LOGIN ------------------------------------
    public static final String SUCCESSFUL_LOGIN = "Logged In Successfully";

    //---------------------- SIGNUP ------------------------------------
    public static final String NO_USERNAME_PROVIDED = "Please provide a username to complete registration";
    public static final String USERNAME_EXISTS = "Username provided already exists, please choose a different username";
    public static final String NO_EMAIL_PROVIDED = "Please provide an email to complete registration";
    public static final String EMAIL_EXISTS = "Email provided already exists, please choose a different email";
    public static final String SUCCESSFUL_SIGNUP = "You Have Successfully Registered in MBBM";
}
