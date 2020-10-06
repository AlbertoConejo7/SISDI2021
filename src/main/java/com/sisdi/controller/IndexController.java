package com.sisdi.controller;

import com.sisdi.data.UserService;
import com.sisdi.model.office;
import com.sisdi.model.version;
import java.util.List;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/*
    El controlador de la pagina principal de SISDI
 */

@Controller
@Slf4j
public class IndexController {
    @GetMapping("/")
    public String showIndex(Model model, @AuthenticationPrincipal User user, HttpSession session) {
        UserService uServ=new UserService();
        OfficeController offC=new OfficeController();
        List<office> offices=offC.getListOffice();
        log.info("ejecutando el controlador Oficios");
        log.info("usuario:"+user);
        session.setAttribute("user", user);
        session.setAttribute("usuarioCompleto", uServ.getUser(user.getUsername()));
        model.addAttribute("user", user);
        model.addAttribute("offices", offices);
        return "index";
    }
    
     @GetMapping("/list")
    public String listOff(Model model) {
        OfficeController offC=new OfficeController();
        List<office> offices=offC.getListOffice();
        log.info("ejecutando el controlador Oficios");
        model.addAttribute("offices", offices);
        return "offices/listOffices";
    }
    
//    @GetMapping("/index")
//    public String showVersions(Model model) {
//        VersionController verC=new VersionController();
//        List<version> versions=verC.getListVersion();
//        log.info("ejecutando el controlador Versiones");
//        model.addAttribute("offices", versions);
//        return "versions/versionOffice";
//    }
    
}
