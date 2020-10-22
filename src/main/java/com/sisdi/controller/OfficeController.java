package com.sisdi.controller;

import com.sisdi.data.UserData;
import com.sisdi.model.Office;
import com.sisdi.model.OfficeSimple;
import com.sisdi.service.OfficeServiceImp;
//import com.sisdi.model.version;
import com.sisdi.model.TimeOuts;
import com.sisdi.model.Usuario;
import com.sisdi.service.TimeOutsServiceImp;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
@RequestMapping(value = "/offices")
public class OfficeController {

    private Date fecha = Date.valueOf(LocalDate.now());
    
    @Autowired
    private OfficeServiceImp officeServiceImp;
    
    @Autowired
    private TimeOutsServiceImp timeOutsServiceImp;
    
    @Autowired 
    private UserData userData;

    @GetMapping("/addOffice")
    public String addOffice(Model model, OfficeSimple officeAdd, @AuthenticationPrincipal User user) {
        model.addAttribute("date", fecha);
        Usuario u=userData.getUser(user.getUsername());
        officeAdd.setEmisor(u.getTempUser().getName());
        officeAdd.setEmisorDep(u.getDepartment().getName());
        officeAdd.setDateCreate(fecha.toString());
        model.addAttribute("officeAdd", officeAdd);
        return "offices/addOffice";
    }
     @PostMapping("/saveOffice")
     public String saveOffice(Model model, @ModelAttribute("officeAdd")OfficeSimple office) throws ParseException{
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
         o.setVERSION_ID(1);
         o.setTIMEOUTS_ID(1);
         log.info(o.toString());
         officeServiceImp.addOffice(o);
         return "redirect:/offices/addOffice";
     }

    @GetMapping("/responseOffice/{officeId}")
    public String responseOffice(@PathVariable String officeId, Model model) {
        Office officeAct = officeServiceImp.searchOffice(officeId);
        model.addAttribute("date", fecha);
        model.addAttribute("officeActual", officeAct);
        model.addAttribute("title", "Ver Oficio");
        return "offices/responseOffice";
    }

    @GetMapping("/editOffice/{officeId}")
    public String editOffice(@PathVariable String officeId, Model model) {
        Office officeAct = officeServiceImp.searchOffice(officeId);
        model.addAttribute("date", fecha);
        model.addAttribute("officeActual", officeAct);
        model.addAttribute("title", "Ver Oficio");
        return "offices/editOffice";
    }

    @GetMapping("/listOffices")
    public String listOffice(Model model, @AuthenticationPrincipal User user) {
        List<Office> offices = officeServiceImp.listOfficeByEmisor(user.getUsername());
        log.info("ejecutando el controlador Oficios");
        model.addAttribute("offices", offices);
        return "offices/listOffices";
    }

    @GetMapping("/pendingOffice")
    public String pendingOffice(Model model, @AuthenticationPrincipal User user) {
        List<Office> offices = officeServiceImp.listOfficeByReceptor(user.getUsername());
       // List<TimeOuts> time = timeOutsServiceImp.listTimeOuts();
        
         model.addAttribute("date", fecha);
        log.info("ejecutando el controlador Oficios");
        model.addAttribute("officesPending", offices);
        // model.addAttribute("timeOuts", time);
        return "offices/pendingOffice";
    }

    @PostMapping("/anular")
    public String anularOficio(Model model, @AuthenticationPrincipal User user) {
        List<Office> offices = officeServiceImp.listOfficeByEmisor(user.getUsername());
        log.info("ejecutando el controlador Oficios");
        model.addAttribute("offices", offices);
        log.info("Oficio Anulado");
        return "offices/listOffices";
    }
}
