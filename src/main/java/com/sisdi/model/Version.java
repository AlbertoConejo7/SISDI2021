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
public class Version implements Serializable {
   
    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

   
    private String version_id;
    private int version_number;
    private Date version_date;
    private Time version_time;
    private String version_description;
    private String reason;
    private String observations;

public Version(int id, String id_version, int version_number, Date version_date, Time version_time, String version_description, String reason, String observations) {
    this.id = id;
    this.version_id = id_version;
    this.version_number = version_number;
    this.version_date = version_date;
    this.version_time=version_time;
    this.version_description = version_description;
    this.reason = reason;
    this.observations = observations;
}
    
}
