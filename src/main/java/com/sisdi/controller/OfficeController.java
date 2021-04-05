package com.sisdi.controller;

import com.sisdi.data.FileData;
import com.sisdi.data.OfficeData;
import com.sisdi.data.UserData;
import com.sisdi.model.Expediente;
import com.sisdi.model.FileSimple;
import com.sisdi.model.Office;
import com.sisdi.model.OfficeSimple;
import com.sisdi.service.OfficeServiceImp;
import com.sisdi.model.Usuario;
import com.sisdi.model.searchOffice;
import com.sisdi.service.ExpedienteServiceImp;
import com.sisdi.service.TimeOutsServiceImp;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.http.HttpSession;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    
    @Autowired
    private FileData fileData;
    
    @Autowired
    private ExpedienteServiceImp expedienteServiceImp;

   @GetMapping("/addOffice")
    public String addOffice(Model model, OfficeSimple officeAdd, @AuthenticationPrincipal User user) {
        String fechaS = new SimpleDateFormat("dd/MM/yyyy").format(this.fecha);
        String year = new SimpleDateFormat("yyyy").format(this.fecha);
        model.addAttribute("date", fecha);
        List<Usuario> usuarios = userData.listUsers();
        Usuario u = userData.getUser(user.getUsername());
          List<Office> offices = officeServiceImp.listarOficios();
          Office of=offices.get(offices.size()-1);
       int INDX= of.getINDX();
        
        String offNumber= "OFICIO"+"-"+"MSPH"+"-"+"AM"+"-"+u.getDepartment().getCod()+"-"+"NE"
                +"-"+(INDX+1)+"-"+year;
        officeAdd.setOffnumber(offNumber);
        officeAdd.setEmisor(u.getTempUser().getName());
        officeAdd.setEmisorDep(u.getDepartment().getName());
        officeAdd.setDateCreate(fechaS);
        log.info(usuarios.toString());
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("officeAdd", officeAdd);

        return "offices/addOffice";
    }
    
    @GetMapping("/addFile")
    public String addFile(Model model, FileSimple fileAdd, @AuthenticationPrincipal User user) {
        String fechaS = new SimpleDateFormat("dd/MM/yyyy").format(this.fecha);
        model.addAttribute("date", fecha);
        List<Usuario> usuarios = userData.listUsers();
        Usuario u = userData.getUser(user.getUsername());
        fileAdd.setOwner(u.getTempUser().getName());
        fileAdd.setDepartment(u.getDepartment().getName());
        fileAdd.setDateCreateFile(fechaS);
        log.info(usuarios.toString());
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("fileAdd", fileAdd);

        return "offices/addFile";
    }
    
    @GetMapping("/sendFile")
    public String sendFile(Model model, FileSimple fileSend, @AuthenticationPrincipal User user) {
        String fechaS = new SimpleDateFormat("dd/MM/yyyy").format(this.fecha);
        model.addAttribute("date", fecha);
        List<Expediente> expedientes = expedienteServiceImp.listExpedienteByEmisor(user.getUsername());       
        List<Usuario> usuarios = userData.listUsers();
        Usuario u = userData.getUser(user.getUsername());
        fileSend.setOwner(u.getTempUser().getName());
        fileSend.setDepartment(u.getDepartment().getName());
        fileSend.setDateReturn(fechaS);
        
        model.addAttribute("expedientesPending", expedientes);
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("fileSend", fileSend);

        return "offices/sendFile";
    }
    @PostMapping("/saveFile")
    public String saveFile(Model model, @ModelAttribute("fileSend") FileSimple fileSend, RedirectAttributes redirectAttrs,  @AuthenticationPrincipal User user) throws ParseException {
        try {
            fileSend.setOwner("archivocentral@sanpablo.go.cr");
            fileSend.setReceiver("archivocentral@sanpablo.go.cr");
            Expediente exp=fileData.getFile(fileSend);
            log.info("El expediente"+exp.toString());
            redirectAttrs
                    .addFlashAttribute("mensaje", "Expediente trasladado correctamente")
                    .addFlashAttribute("clase", "success");
                    expedienteServiceImp.addExpediente(exp);
        } catch (Exception e) {
            redirectAttrs
                    .addFlashAttribute("mensaje", "Error al trasladar oficio")
                    .addFlashAttribute("clase", "alert alert-danger");
                    log.info(e.toString());
        }
        return "redirect:/offices/sendFile";
    }

    @PostMapping("/saveOffice")
    public String saveOffice(Model model, @ModelAttribute("officeAdd") OfficeSimple office, RedirectAttributes redirectAttrs) throws ParseException {
        try {
            Office o = officeData.getOffice(office, 0);
            log.info(o.toString());
            officeServiceImp.addOffice(o);
            redirectAttrs
                    .addFlashAttribute("mensaje", "Oficio agregado correctamente")
                    .addFlashAttribute("clase", "success");
        } catch (Exception e) {
            redirectAttrs
                    .addFlashAttribute("mensaje", "Error al agregar oficio")
                    .addFlashAttribute("clase", "alert alert-danger");
        }
        return "redirect:/offices/addOffice";
    }
    
    @PostMapping("/saveResponseOffice")
    public String saveResponseOffice(Model model, @ModelAttribute("officeActual") OfficeSimple office, HttpSession session, RedirectAttributes redirectAttrs) throws ParseException {
        Office o = officeData.getOffice(office, 1);
        log.info(o.toString());
        try {
            officeServiceImp.addOffice(o);
            String officeId = (String) session.getAttribute("officeResponse");
            log.info(officeId);
            Office of = officeServiceImp.searchOffice(officeId);
            of.setSTATE(2);
            log.info(of.toString());
            officeServiceImp.addOffice(of);
            redirectAttrs
                    .addFlashAttribute("mensaje", "Respuesta enviada correctamente")
                    .addFlashAttribute("clase", "success");
        } catch (Exception e) {
            redirectAttrs
                    .addFlashAttribute("mensaje", "Error al responder oficio")
                    .addFlashAttribute("clase", "alert alert-danger");
        }
        return "redirect:/offices/pendingOffice";
    }

    @GetMapping("/responseOffice/{officeId}")
    public String responseOffice(@PathVariable String officeId, Model model, HttpSession session) {
        Office officeAct = officeServiceImp.searchOffice(officeId);
        OfficeSimple office_aux = new OfficeSimple();
        String fecha_ = new SimpleDateFormat("dd/MM/yyyy").format(this.fecha);
        OfficeSimple office = officeData.getOfficeSimple(officeAct);
        office_aux.setEmisor(office.getReceptor());
        office_aux.setEmisorDep(office.getReceptorDep());
        office_aux.setReceptor(office.getEmisor());
        office_aux.setReceptorDep(office.getEmisorDep());
        office_aux.setReason("Responder a " + office.getOffnumber());
        office_aux.setDateCreate(fecha_);
        office_aux.setDateLimit(office.getDateLimit());
        office_aux.setType_id(office.getType_id());
        session.setAttribute("officeResponse", officeAct.getOFFNUMBER());
        model.addAttribute("officeActual", office_aux);
        return "offices/responseOffice";
    }

    @GetMapping("/editOffice/{officeId}")
    public String editOffice(@PathVariable String officeId, Model model) {
        Office officeAct = officeServiceImp.searchOffice(officeId);
        OfficeSimple os = officeData.getOfficeSimple(officeAct);
        model.addAttribute("date", fecha);
        model.addAttribute("officeActual", os);
        model.addAttribute("title", "Ver Oficio");
        model.addAttribute("url", "https://ucu.edu.uy/sites/default/files/facultad/dcsp/Concurso_2015/038_Tecno2015_tecnologia_un_beneficio_o_una_adicci%C3%B3n.pdf");
        return "offices/editOffice";
    }

    @GetMapping("/listOffices")
    public String listOffice(Model model, @AuthenticationPrincipal User user) {
        List<Office> offices = officeServiceImp.listOfficeByEmisor(user.getUsername());
        searchOffice search = new searchOffice();
        log.info("ejecutando el controlador Oficios");
        model.addAttribute("offices", offices);
        model.addAttribute("search", search);
        return "offices/listOffices";
    }

    @GetMapping("/searchedOffices")
    public String searchedOffices(Model model, @AuthenticationPrincipal User user) {
        searchOffice search = new searchOffice();
        log.info("ejecutando el controlador Oficios");
        model.addAttribute("search", search);
        return "offices/listOffices";
    }

    @PostMapping("/searchOffice")
    public String searchOffice(Model model, @ModelAttribute("search") searchOffice search, @AuthenticationPrincipal User user) throws ParseException {
        List<Office> offices = officeData.searchOffice(search, user.getUsername());
        log.info(search.toString());
        log.info("ejecutando el controlador Oficios");
        model.addAttribute("offices", offices);
        return "offices/searchOffices";
    }

    @GetMapping("/pendingOffice")
    public String pendingOffice(Model model, @AuthenticationPrincipal User user) {
        List<Office> offices = officeServiceImp.listOfficeByReceptor(user.getUsername());
        model.addAttribute("date", fecha);
        log.info("ejecutando el controlador Oficios");
        model.addAttribute("officesPending", offices);
       
        // model.addAttribute("timeOuts", time);
        return "offices/pendingOffice";
    }
    
     @GetMapping("/pendingExpediente")
    public String pendingExpediente(Model model, @AuthenticationPrincipal User user) {
        List<Expediente> expedientes = expedienteServiceImp.listExpedienteByUser(user.getUsername());       
        log.info(expedientes.toString());
        model.addAttribute("expedientesPending", expedientes);
        return "offices/pendingExpediente";
    }

    @PostMapping("/anular")
    public String anularOficio(Model model, @AuthenticationPrincipal User user, @ModelAttribute("officeActual") OfficeSimple office) throws ParseException {
        List<Office> offices = officeServiceImp.listOfficeByEmisor(user.getUsername());
        log.info("ejecutando el controlador Oficios");
        Office o = officeData.getOffice(office, 3);
        o.setINDX(office.getId());
        model.addAttribute("offices", offices);
        officeServiceImp.addOffice(o);
        model.addAttribute("search", new searchOffice());
        return "redirect:/offices/listOffices";
    }

    @GetMapping("/acceptOffice/{officeId}")
    public String acceptOffice(@PathVariable String officeId, Model model, @AuthenticationPrincipal User user) {
        Office officeAct = officeServiceImp.searchOffice(officeId);
        officeAct.setSTATE(1);// cambiar estado 

        officeAct = officeServiceImp.addOffice(officeAct);
        List<Office> offices = officeServiceImp.listOfficeByReceptor(user.getUsername());
        // List<TimeOuts> time = timeOutsServiceImp.listTimeOuts();

        model.addAttribute("date", fecha);
        log.info("ejecutando el controlador Oficios");
        model.addAttribute("officesPending", offices);
        // model.addAttribute("timeOuts", time);
        return "offices/pendingOffice";
    }
     @GetMapping("/acceptExpediente/{expedienteId}")
    public String acceptExpediente(@PathVariable String expedienteId, Model model, @AuthenticationPrincipal User user) {
        Expediente expedienteAct = expedienteServiceImp.searchExpediente(expedienteId);
        expedienteAct.setSTATE(1);// cambiar estado 

        expedienteAct = expedienteServiceImp.addExpediente(expedienteAct);
        List<Expediente> expedientes = expedienteServiceImp.listExpedienteByReceptor(user.getUsername());
        // List<TimeOuts> time = timeOutsServiceImp.listTimeOuts();
       //String fechaS = new SimpleDateFormat("dd/MM/yyyy").format(this.fecha);
      //  model.addAttribute("date", fecha);
        expedienteAct.setDATE_RETURN(fecha);
        
         
        log.info("ejecutando el controlador expedientes");
        model.addAttribute("expedientesPending", expedientes);
        // expedienteAct = expedienteServiceImp.addExpediente(expedienteAct); arreglar guardar date
        // model.addAttribute("timeOuts", time);
        return "offices/pendingExpediente";
    }

    @GetMapping("/viewOffice/{officeId}")
    public String viewOffice(@PathVariable String officeId, Model model) {
        Office officeAct = officeServiceImp.searchOffice(officeId);
        OfficeSimple os = officeData.getOfficeSimple(officeAct);
        model.addAttribute("date", fecha);
        model.addAttribute("officeActual", os);
        model.addAttribute("title", "no");
         model.addAttribute("url", "https://ucu.edu.uy/sites/default/files/facultad/dcsp/Concurso_2015/038_Tecno2015_tecnologia_un_beneficio_o_una_adicci%C3%B3n.pdf");
        return "offices/editOffice";
    }
    
    @GetMapping("/viewOfficeS/{officeId}")
    public String viewOfficeS(@PathVariable String officeId, Model model) {
        Office officeAct = officeServiceImp.searchOffice(officeId);
        OfficeSimple os = officeData.getOfficeSimple(officeAct);
        model.addAttribute("date", fecha);
        model.addAttribute("officeActual", os);
        model.addAttribute("title", "no-r");
         model.addAttribute("url", "https://ucu.edu.uy/sites/default/files/facultad/dcsp/Concurso_2015/038_Tecno2015_tecnologia_un_beneficio_o_una_adicci%C3%B3n.pdf");
        return "offices/editOffice";
    }
    
    @GetMapping("/authSignature")
    public String authSignature(Model model, OfficeSimple officeAdd, @AuthenticationPrincipal User user) {
        return "offices/authSignature";
    }

}
