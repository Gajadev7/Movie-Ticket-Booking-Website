package net.javaguides.usermanagement.model;

import java.io.Serializable;

public class bookedSeats implements Serializable{
    private String bookedSeat; 
    
    public bookedSeats(String n) 
    { 
        this.bookedSeat = n;
    } 
    
    public String getbookedSeat() { return bookedSeat; }
    
}
