package com.sisdi.model;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author Vale
 */

@Data
@Entity
@Table(name="T_VERSION")
public class version implements Serializable {
   
    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id_office;

   
    private int id_version;
    private int version_number;
    private Date version_date;
    private Time version_time;
    private String version_description;

public version(String id_office, int id_version, int version_number, Date version_date, Time version_time, String version_description) {
    this.id_office = id_office;
    this.id_version = id_version;
    this.version_number = version_number;
    this.version_date = version_date;
    this.version_time=version_time;
    this.version_description = version_description;
}
    
}
