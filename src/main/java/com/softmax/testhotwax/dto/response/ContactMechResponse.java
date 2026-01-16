package com.softmax.testhotwax.dto.response;

public class ContactMechResponse {
    private Integer contactMechId;
    private String streetAddress;
    private String city;
    private String state;
    private String postalCode;
    private String phoneNumber;
    private String email;

    public ContactMechResponse() {}

    public ContactMechResponse(Integer contactMechId, String streetAddress, String city, String state,
                               String postalCode, String phoneNumber, String email) {
        this.contactMechId = contactMechId;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Integer getContactMechId() { return contactMechId; }
    public void setContactMechId(Integer contactMechId) { this.contactMechId = contactMechId; }

    public String getStreetAddress() { return streetAddress; }
    public void setStreetAddress(String streetAddress) { this.streetAddress = streetAddress; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getPostalCode() { return postalCode; }
    public void setPostalCode(String postalCode) { this.postalCode = postalCode; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
