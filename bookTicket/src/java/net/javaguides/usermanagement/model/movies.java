
package net.javaguides.usermanagement.model;

import java.io.Serializable;

public class movies implements Serializable{
    private Integer movieID;
    private String movieName; 
    private String movieImageUrl; 
    private String likedBy; 
    // Parameterized Constructor to set Student 
    // name, age, course enrolled in. 
 
    public movies(Integer i,String n, String a, String c) 
    { 
        this.movieID = i;
        this.movieName = n; 
        this.movieImageUrl = a; 
        this.likedBy = c; 
    } 
    // Setter Methods to set table data to be 
    // displayed 
    public Integer getmovieID() { return movieID; }
    public String getmovieName() { return movieName; } 
    public String getmovieImageUrl() { return movieImageUrl; } 
    public String getlikedBy() { return likedBy; } 
}
