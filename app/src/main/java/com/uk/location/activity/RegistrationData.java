package com.uk.location.activity;

public class RegistrationData {
    private boolean isTracking;
    private String userName;
    private String passWord;

    public RegistrationData (String login, String password) {
        isTracking = false;
        userName = login;
        passWord = password;
    }

    public void setTrackingState (boolean trackingState) {
        this.isTracking = trackingState;
    }

    public boolean getTrackingState () {
        return isTracking;
    }
}
