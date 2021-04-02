package com.sisdi.model;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="t_expediente")
public class Expediente implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int INDX;
    private String FILENAME;
    private String OBSERVATIONS;
     private String OWNER_ID;
     private String RECEIVER_ID;
      private int OFFICE_AMOUNT;
      private String DATE_CREATE;
      private String DATE_RETURN;
      private int STATE; // 0 para prestado 1 para devuelto

   
    
    public Expediente(){}
     
}
