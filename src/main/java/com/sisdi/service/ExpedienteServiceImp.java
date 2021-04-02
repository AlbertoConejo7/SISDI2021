/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisdi.service;

import com.sisdi.dao.ExpedienteDao;
import com.sisdi.model.Expediente;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpedienteServiceImp implements ExpedienteService {

    @Autowired
    private ExpedienteDao expedienteDao;



 @Override
    public List<Expediente> listarExpedientes() {
        return (List<Expediente>) expedienteDao.findAll();
    }
    
    @Override
    public Expediente addExpediente(Expediente expediente) {
        return expedienteDao.save(expediente);
    }

@Override
    public Expediente searchExpediente(String expnumber) {
        List<Expediente> list = this.listarExpedientes();
        Expediente aux = null;
        for (Expediente o : list) {
            if (o.getFILENAME().equals(expnumber)) {
                aux = o;
            }
        }
        return aux;
    }
    @Override
    public List<Expediente> listExpedienteByEmisor(String emisor) {
        List<Expediente> list = this.listarExpedientes();
        List<Expediente> aux = new ArrayList();
        for (Expediente o : list) {
            if (o.getOWNER_ID().equals(emisor)) {
                aux.add(o);
            }
        }

        return aux;
    }
    @Override
    public List<Expediente> listExpedienteByReceptor(String receptor) {
        List<Expediente> list = this.listarExpedientes();
        List<Expediente> aux = new ArrayList();
        for (Expediente o : list) {
            if (o.getRECEIVER_ID().equals(receptor)) {
                aux.add(o);
            }
        }

        return aux;
    }
    @Override
    public List<Expediente> listExpedienteByUser(String user) {
        List<Expediente> aux = new ArrayList();
        List<Expediente> emisor = this.listExpedienteByEmisor(user);
        List<Expediente> receptor = this.listExpedienteByReceptor(user);
        aux.addAll(emisor);
        aux.addAll(receptor);
        return aux;
    }
}
