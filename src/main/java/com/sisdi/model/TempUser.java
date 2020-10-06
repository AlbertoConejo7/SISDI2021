package com.sisdi.model;

import lombok.Data;

@Data
public class TempUser {
    //TempUser Class
    private String name;
    private String email;
    
    public TempUser(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
