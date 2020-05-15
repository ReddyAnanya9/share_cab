package com.example.sharecab;

public class UserBookings {
    private String destination,pickup_pt,special_Req,time;
    private int luggage;
    public UserBookings(){

    }


    public UserBookings(String Destination, String pickup_pt, String special_Req, String time, int luggage) {
        this.destination = Destination;
        this.pickup_pt = pickup_pt;
        this.special_Req = special_Req;
        this.time = time;
        this.luggage = luggage;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getPickup_pt() {
        return pickup_pt;
    }

    public void setPickup_pt(String pickup_pt) {
        this.pickup_pt = pickup_pt;
    }

    public String getSpecial_Req() {
        return special_Req;
    }

    public void setSpecial_Req(String special_Req) {
        this.special_Req = special_Req;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getLuggage() {
        return luggage;
    }

    public void setLuggage(int luggage) {
        this.luggage = luggage;
    }
}
