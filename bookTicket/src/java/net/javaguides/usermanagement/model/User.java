package net.javaguides.usermanagement.model;

import java.io.Serializable;


public class User implements Serializable {
  
    
    private String fullName;
    private String email;
    private String password;
    private String phoneNumber;
    
   
    
    public String getfullName() {
        return fullName;
    }
    public void setfullName(String fullName) {
        this.fullName = fullName;
    }
    public String getemail() {
        return email;
    }
    public void setemail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getphoneNumber() {
        return phoneNumber;
    }
    public void setphoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}