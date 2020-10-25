package com.sisdi.controller;

import com.sisdi.data.OfficeData;
import com.sisdi.model.Office;
import com.sisdi.model.OfficeSimple;
import com.sisdi.model.Version;
import com.sisdi.service.OfficeServiceImp;
import com.sisdi.service.VersionService;
import java.util.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping(value = "/versions")
public class VersionController {
    
    @Autowired
    private VersionService versionService;
    
    @Autowired 
    private OfficeServiceImp officeService;

    @Autowired 
    private OfficeData officeData;
     
    private Date fecha = new Date();
    private Time hora = Time.valueOf(LocalTime.now());
       
    @GetMapping("/listVersions/{officeId}")
    public String listVersion(@PathVariable String officeId, Model model) {
        //var versions = versionService.list_versions();
        List<Version> versions = this.getListVersion();
        Office officeAct=officeService.searchOffice(officeId);
        OfficeSimple os=officeData.getOfficeSimple(officeAct);
        model.addAttribute("date", fecha);
        model.addAttribute("officeActual", os);
        model.addAttribute("title", "Versiones");
        log.info("ejecutando el controlador Versiones");
        model.addAttribute("versions", versions);
        return "offices/versionOffice";
    }
    
    
//    @GetMapping("/addVersion")
//    public String addVersion(Model model) { 
//        model.addAttribute("date", fecha);
//        model.addAttribute("time",hora);
//        return "offices/addVersion";
//    }
    
    @GetMapping("/add")
    public String add(Version v){
        
        return "versionOffice";
    }
    
    @PostMapping("/saveVersion")
    public String saveVersion(Version v){
        versionService.save_version(v);
        return "redirect:/offices/versionOffice";
    }
    
    public List<Version> getListVersion() {
        List<Version> list = new ArrayList();
//        Version v1 = new Version("Oficio-MPSP-1-Prueba",1, fecha, hora, "Modificacion de version 1","Razon", "Observaciones");
//        Version v2 = new Version("Oficio-MPSP-2-Prueba", 2, fecha, hora, "Modificacion de version 1","Razon", "Observaciones ");
//        list.add(v1);
//        list.add(v2);

        return list;
    }

//         public office getVersion(String officeId){
//         List<office> list= this.getListOffice();
//         office aux =null;
//         for(office o:list){
//             if(o.getName().equals(officeId)){
//                 aux=o;
//             }
//         }
//         return aux;
//    }
}
