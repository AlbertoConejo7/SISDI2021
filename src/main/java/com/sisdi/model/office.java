package com.sisdi.model;

import java.util.Date;
import lombok.Data;

@Data
public class office {
    private int id_office;
    private String state;
    private String reason;
    private Date date;
    private String name;
    private String type;

    public office(int id_office, String state, String reason, Date date, String name, String type) {
        this.id_office = id_office;
        this.state = state;
        this.reason = reason;
        this.date = date;
        this.name = name;
        this.type = type;
    }
    
    
}
