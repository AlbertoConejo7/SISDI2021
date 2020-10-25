package com.sisdi.service;

import com.sisdi.model.Office;
import java.util.List;


public interface OfficeService {
    List<Office> listarOficios();
    Office addOffice(Office office);
    Office searchOffice(String offnumber);
    List<Office> listOfficeByEmisor(String emisor);
    List<Office> listOfficeByReceptor(String receptor);
//    void updateState(String offnumber, int state); 
}
