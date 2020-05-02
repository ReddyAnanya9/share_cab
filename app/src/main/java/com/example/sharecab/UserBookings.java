package com.example.sharecab;

import com.google.firebase.database.Exclude;

public class UserBookings {
    private String Destination,Pickup_pt,Special_Req,time;
    private int Luggage;
    private String userID;
    public UserBookings(){

    }

    @Exclude
    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public UserBookings(String Destination, String pickup_pt, String special_Req, String time, int luggage) {
        this.Destination = Destination;
        Pickup_pt = pickup_pt;
        Special_Req = special_Req;
        this.time = time;
        Luggage = luggage;
    }

    public String getDestination() {
        return Destination;
    }

    public String getPickup_pt() {
        return Pickup_pt;
    }

    public String getSpecial_Req() {
        return Special_Req;
    }

    public String getTime() {
        return time;
    }

    public int getLuggage() {
        return Luggage;
    }
}
