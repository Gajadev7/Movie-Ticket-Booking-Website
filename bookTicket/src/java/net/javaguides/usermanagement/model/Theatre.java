/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.javaguides.usermanagement.model;

/**
 *
 * @author Gajadev
 */
public class Theatre {
    private Integer theatreID;
    private String theatreName; 
   
    // Parameterized Constructor to set Student 
    // name, age, course enrolled in. 
    public Theatre(Integer i,String n) 
    { 
        this.theatreID = i;
        this.theatreName = n;  
    } 
    // Setter Methods to set table data to be 
    // displayed 
    public Integer gettheatreID() { return theatreID; }
    public String gettheatreName() { return theatreName; } 
}
