/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisdi.data;

import com.sisdi.model.Expediente;
import com.sisdi.model.FileSimple;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class FileData {
    private Date fecha = new Date();
    
    public Expediente getFile(FileSimple file) throws ParseException{
         Date create=new SimpleDateFormat("dd/MM/yyyy").parse(file.getDateCreateFile());  
         Date returnF=new SimpleDateFormat("dd/MM/yyyy").parse(file.getDateReturn());  
        Expediente fileO=new Expediente();
        fileO.setINDX(file.getIndx());
        fileO.setFILENAME(file.getFileName());
        fileO.setOWNER_ID(file.getOwner());
        fileO.setRECEIVER_ID(file.getReceiver());
        fileO.setDATE_CREATE(create);
        fileO.setOFFICE_AMOUNT(file.getOfficeAmount());
        fileO.setOBSERVATIONS(file.getObservations());
        fileO.setDATE_RETURN(returnF);
        fileO.setSTATE(1);
        return fileO;
    }
    public FileSimple getFileSimple(Expediente file){
        FileSimple fileO=new FileSimple();
        fileO.setIndx(file.getINDX());
        fileO.setFileName(file.getFILENAME());
        fileO.setOwner(file.getOWNER_ID());
        fileO.setReceiver(file.getRECEIVER_ID());
        fileO.setDateCreateFile(file.getDATE_CREATE().toString());
        fileO.setOfficeAmount(file.getOFFICE_AMOUNT());
        fileO.setObservations(file.getOBSERVATIONS());
        
        return fileO;
    }
}
