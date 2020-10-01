package com.sisdi.controller;

import com.sisdi.model.office;
import com.sisdi.model.version;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping(value = "/versions")
public class VersionController {

    private final Date fecha = Date.valueOf(LocalDate.now());
    private final Time hora = Time.valueOf(LocalTime.now());
        
    @GetMapping("/listVersions")
    public String listVersion(Model model) {
        List<version> versions = this.getListVersion();
        log.info("ejecutando el controlador Versiones");
        model.addAttribute("versions", versions);
        return "versions/listVersions";
    }
    
    @GetMapping("/addVersion")
    public String addVersion(Model model) { 
        model.addAttribute("date", fecha);
        model.addAttribute("time",hora);
        return "offices/addVersion";
    }

    public List<version> getListVersion() {
        List<version> list = new ArrayList();
        
        version v1 = new version("Oficio-MPSP-1-Prueba", 1, 2, fecha, hora, "Modificacion de version 1");
        version v2 = new version("Oficio-MPSP-1-Prueba", 2, 2, fecha, hora, "Modificacion de version 1");

        list.add(v1);

        list.add(v2);

        return list;
    }

}
