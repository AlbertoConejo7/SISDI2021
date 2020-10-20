package com.sisdi.service;

import com.sisdi.dao.OfficeDao;
import com.sisdi.model.Office;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OfficeServiceImp implements OfficeService{
    @Autowired
    private OfficeDao officeDao;

    @Override
    public List<Office> listarOficios() {
        return (List<Office>) officeDao.findAll();
    }

    @Override
    public Office addOffice(Office office) {
        return officeDao.save(office);
    }

    @Override
    public Office searchOffice(String offnumber) {
        List<Office> list= this.listarOficios();
         Office aux =null;
         for(Office o:list){
             if(o.getOFFNUMBER().equals(offnumber)){
                 aux=o;
             }
         }
         return aux;
    }

    @Override
    public List<Office> listOfficeByEmisor(String emisor) {
        List<Office> list= this.listarOficios();
        List<Office> aux= new ArrayList();
        for(Office o:list){
             if(o.getUSER_ID().equals(emisor)){
                 aux.add(o);
             }
         }
        
        return aux;  
    }

    @Override
    public List<Office> listOfficeByReceptor(String receptor) {
        List<Office> list= this.listarOficios();
        List<Office> aux= new ArrayList();
        for(Office o:list){
             if(o.getRECEIVER_ID().equals(receptor)){
                 aux.add(o);
             }
         }
        
        return aux; 
    }
    
}
