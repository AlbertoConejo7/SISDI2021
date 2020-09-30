package com.sisdi.controller;

import com.sisdi.model.office;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/*
    El controlador de la Pagina principal de SISDI
 */

@Controller
@Slf4j
public class IndexController {

    @GetMapping("/")
    public String listOffice() {
        return "redirect:index";
    }

    @GetMapping("/index")
    public String showIndex(Model model) {
        OfficeController offC=new OfficeController();
        List<office> offices=offC.getListOffice();
        log.info("ejecutando el controlador Oficios");
        model.addAttribute("offices", offices);
        return "offices/listOffices";
    }
    
}
