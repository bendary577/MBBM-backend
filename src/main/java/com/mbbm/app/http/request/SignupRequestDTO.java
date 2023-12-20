package com.mbbm.app.http.request;

import java.io.Serializable;

public class SignupRequestDTO implements Serializable {

    private static final long serialVersionUID = 5926468583005150707L;

    //user info
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private String birthDate;
    private String nationality;
    private boolean isCompany;
    private int gender;
    private int userType;

    //address info
    private String country;
    private String state;
    private String city;

    //contacts info
    private int contactType;
    private String contactValue;
    private String countryCode;

    //need default constructor for JSON Parsing
    public SignupRequestDTO() {}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public boolean isCompany() {
        return isCompany;
    }

    public void setCompany(boolean company) {
        isCompany = company;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getContactType() {
        return contactType;
    }

    public void setContactType(int type) {
        this.contactType = type;
    }

    public String getContactValue() {
        return contactValue;
    }

    public void setContactValue(String value) {
        this.contactValue = value;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
