package com.sisdi.service;

import com.sisdi.model.Expediente;
import java.util.Date;
import java.util.List;

public interface ExpedienteService {
     List<Expediente> listarExpedientes();
      Expediente addExpediente(Expediente expediente);
       Expediente searchExpediente(String expnumber);
    List<Expediente> listExpedienteByReceptor(String receptor);
    
}
