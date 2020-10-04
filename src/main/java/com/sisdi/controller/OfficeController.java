package com.sisdi.controller;

import com.sisdi.model.office;
//import com.sisdi.model.version;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
@RequestMapping(value = "/offices")
public class OfficeController {
    private Date fecha=Date.valueOf(LocalDate.now());
    
    @GetMapping("/addOffice")
    public String addOffice(Model model) {
        model.addAttribute("date", fecha);
        return "offices/addOffice";
    }
    @GetMapping("/editOffice/{officeId}")
    public String editOffice(@PathVariable String officeId,Model model) {
        office officeAct=this.getOffice(officeId);
        model.addAttribute("date", fecha);
        model.addAttribute("officeActual", officeAct);
        model.addAttribute("title", "Ver Oficio");
        return "offices/editOffice";
    }
    
    @GetMapping("/listOffices")
    public String listOffice(Model model) {
        List<office> offices = this.getListOffice();
        log.info("ejecutando el controlador Oficios");
        model.addAttribute("offices", offices);
        return "offices/listOffices";
    }
   
//    @PostMapping("/anularOficio")
//    public String anularOficio(@RequestParam("officeActual") office offi, Model model){
//        model.addAttribute("offi", offi);
//        model.addAttribute("anulado", "Algun oficio fue anulado");
//        return "offices/anular";
//    }
    
    public List<office> getListOffice(){
         List<office> list=new ArrayList();         
         office o1= new office(1, "Esperando Respuesta", "Probar que funciona",fecha,"Oficio-MPSP-1-Prueba","Interno");
         office o2= new office(2, "Respondido", "Probar que funciona 2",fecha,"Oficio-MPSP-2-Prueba","Externo");
         office o3= new office(3, "Esperando Respuesta", "Probar que funciona 3",fecha,"Oficio-MPSP-3-Prueba","Externo");
         office o4= new office(4, "Esperando Respuesta", "Probar que funciona 4",fecha,"Oficio-MPSP-4-Prueba","Interno");
         office o5= new office(5, "Respondido", "Probar que funciona 5",fecha,"Oficio-MPSP-5-Prueba","Externo");
        
         list.add(o1);
         list.add(o2);
         list.add(o3);
         list.add(o4);
         list.add(o5);
         
         return list;
    }
     public office getOffice(String officeId){
         List<office> list= this.getListOffice();
         office aux =null;
         for(office o:list){
             if(o.getName().equals(officeId)){
                 aux=o;
             }
         }
         return aux;
    }
}
