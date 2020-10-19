package com.sisdi.controller;

import com.sisdi.data.UserData;
import com.sisdi.model.Department;
import com.sisdi.model.TempUser;
import com.sisdi.model.UserEntity;
import com.sisdi.model.office;
import com.sisdi.model.version;
import com.sisdi.service.DepartmentServiceImp;
import com.sisdi.service.TempUserServiceImp;
import com.sisdi.service.UserServiceImp;
import java.util.List;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private UserData userData;
     
    @GetMapping("/login")
    public String login(){
       return "login";
    }
    @GetMapping("/")
    public String showIndex(Model model, @AuthenticationPrincipal User user, HttpSession session) {
        OfficeController offC=new OfficeController();
        List<office> offices=offC.getListOffice();
        log.info("ejecutando el controlador Oficios");
        session.setAttribute("user", user);
        session.setAttribute("usuarioCompleto", userData.getUser(user.getUsername()));
        model.addAttribute("user", user);
        model.addAttribute("offices", offices);
        return "index";
    }
    
}
