package com.sisdi.controller;


import com.sisdi.dao.VersionDao;
import com.sisdi.model.office;
import com.sisdi.model.version;
import com.sisdi.service.VersionService;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
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

    private Date fecha = Date.valueOf(LocalDate.now());
    private Time hora = Time.valueOf(LocalTime.now());
       
    @GetMapping("/listVersions/{officeId}")
    public String listVersion(@PathVariable String officeId, Model model) {
        //var versions = versionService.list_versions();
        List<version> versions = this.getListVersion();
        OfficeController ofC=new OfficeController();
        office officeAct=ofC.getOffice(officeId);
        model.addAttribute("officeActual", officeAct);
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
    public String add(version v){
        
        return "versionOffice";
    }
    
    @PostMapping("/saveVersion")
    public String saveVersion(version v){
        versionService.save_version(v);
        return "redirect:/offices/versionOffice";
    }
    
    public List<version> getListVersion() {
        List<version> list = new ArrayList();
        
        version v1 = new version("Oficio-MPSP-1-Prueba", 1, 2, fecha, hora, "Modificacion de version 1");
        version v2 = new version("Oficio-MPSP-2-Prueba", 2, 2, fecha, hora, "Modificacion de version 1");

        list.add(v1);
        list.add(v2);

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
