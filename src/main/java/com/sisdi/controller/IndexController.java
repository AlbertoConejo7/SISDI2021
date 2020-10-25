package com.sisdi.controller;

import com.sisdi.data.UserData;
import com.sisdi.service.DepartmentServiceImp;
import com.sisdi.service.OfficeServiceImp;
import com.sisdi.service.TempUserServiceImp;
import com.sisdi.service.UserServiceImp;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired 
    private TempUserServiceImp tempUserService;
    
    @Autowired 
    private DepartmentServiceImp departmentService;
    
    @Autowired 
    private UserServiceImp userService;
    
    @Autowired 
    private OfficeServiceImp officeService;
    
    @Autowired 
    private UserData userData;
     
    @GetMapping("/login")
    public String login(){
       return "login";
    }
    @GetMapping("/")
    public String showIndex(Model model, @AuthenticationPrincipal User user, HttpSession session) {
        log.info("ejecutando el controlador Oficios");
        session.setAttribute("user", user);
        session.setAttribute("usuarioCompleto", userData.getUser(user.getUsername()));
        model.addAttribute("user", user);
        return "index";
    }
    
}
