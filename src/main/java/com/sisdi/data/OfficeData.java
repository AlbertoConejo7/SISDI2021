package com.sisdi.data;

import com.sisdi.model.Office;
import com.sisdi.model.OfficeSimple;
import com.sisdi.model.Usuario;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfficeData {
    @Autowired
    private UserData userData;
     private Date fecha = Date.valueOf(LocalDate.now());
     
    public Office getOffice(OfficeSimple office) throws ParseException{
        Office o=new Office();
         Usuario emisor=userData.getUserByName(office.getEmisor());
         Usuario receptor=userData.getUserByName(office.getReceptor());
         java.util.Date dateLimit=new SimpleDateFormat("dd/MM/yyyy").parse(office.getDateLimit());
         o.setOFFNUMBER(office.getOffnumber());
         o.setREASON(office.getReason());
         o.setINCORDATE(fecha);
         o.setUSER_ID(emisor.getTempUser().getEmail());
         o.setRECEIVER_ID(receptor.getTempUser().getEmail());
         o.setTYPE_ID(office.getType_id());
         o.setSTATE(0);
         o.setOBSERVATIONS(office.getObservations());
         o.setDEADLINE(dateLimit);
         o.setTIMEOUTS_ID(1);
        return o;
    }
    public OfficeSimple getOfficeSimple(Office office){
       OfficeSimple o = new OfficeSimple();
        Usuario emisor=userData.getUser(office.getUSER_ID());
        Usuario receptor=userData.getUser(office.getRECEIVER_ID());
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/YYYY");
        String incordate = format.format(office.getINCORDATE());
        o.setOffnumber(office.getOFFNUMBER());
        o.setDateCreate(incordate);
        o.setEmisor(emisor.getTempUser().getName());
        o.setEmisorDep(emisor.getDepartment().getName());
        o.setReceptor(receptor.getTempUser().getName());
        o.setReceptorDep(receptor.getDepartment().getName());
        o.setReason(office.getREASON());
        o.setType_id(office.getTYPE_ID());
        o.setObservations(office.getOBSERVATIONS());
        if(office.getDEADLINE()!= null){
            o.setDateLimit(office.getDEADLINE().toString());
        }
        return o; 
    }
    
}
