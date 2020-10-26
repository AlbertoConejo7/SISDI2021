package com.sisdi.controller;

import com.sisdi.data.OfficeData;
import com.sisdi.data.UserData;
import com.sisdi.model.Office;
import com.sisdi.model.OfficeSimple;
import com.sisdi.service.OfficeServiceImp;
import com.sisdi.model.Usuario;
import com.sisdi.service.TimeOutsServiceImp;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

@Controller
@Slf4j
@RequestMapping(value = "/offices")
public class OfficeController {

    private Date fecha = new Date();
    
    @Autowired
    private OfficeServiceImp officeServiceImp;
    
    @Autowired
    private TimeOutsServiceImp timeOutsServiceImp;
    
    @Autowired 
    private UserData userData;
    
    @Autowired 
    private OfficeData officeData;

    @GetMapping("/addOffice")
    public String addOffice(Model model, OfficeSimple officeAdd, @AuthenticationPrincipal User user) {
        String fechaS = new SimpleDateFormat("dd/MM/yyyy").format(this.fecha);
        model.addAttribute("date", fecha);
        Usuario u=userData.getUser(user.getUsername());
        officeAdd.setEmisor(u.getTempUser().getName());
        officeAdd.setEmisorDep(u.getDepartment().getName());
        officeAdd.setDateCreate(fechaS);
        model.addAttribute("officeAdd", officeAdd);
        return "offices/addOffice";
    }
     @PostMapping("/saveOffice")
     public String saveOffice(Model model, @ModelAttribute("officeAdd")OfficeSimple office) throws ParseException{
         Office o=officeData.getOffice(office, 0);
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
        OfficeSimple os=officeData.getOfficeSimple(officeAct);
        model.addAttribute("date", fecha);
        model.addAttribute("officeActual", os);
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
    public String anularOficio(Model model, @AuthenticationPrincipal User user, @ModelAttribute("officeActual")OfficeSimple office) throws ParseException {
        List<Office> offices = officeServiceImp.listOfficeByEmisor(user.getUsername());
        log.info("ejecutando el controlador Oficios");
        Office o=officeData.getOffice(office, 3);
        o.setINDX(office.getId());
        model.addAttribute("offices", offices);
        officeServiceImp.addOffice(o);
        return "offices/listOffices";
    }
    
    @GetMapping("/acceptOffice/{officeId}")
    public String acceptOffice(@PathVariable String officeId, Model model,@AuthenticationPrincipal User user) {
        Office officeAct = officeServiceImp.searchOffice(officeId);
        officeAct.setSTATE(1);// cambiar estado 
        
        officeAct=officeServiceImp.addOffice(officeAct);
        List<Office> offices = officeServiceImp.listOfficeByReceptor(user.getUsername());
       // List<TimeOuts> time = timeOutsServiceImp.listTimeOuts();
        
         model.addAttribute("date", fecha);
        log.info("ejecutando el controlador Oficios");
        model.addAttribute("officesPending", offices);
        // model.addAttribute("timeOuts", time);
        return "offices/pendingOffice";
    }
    
}
