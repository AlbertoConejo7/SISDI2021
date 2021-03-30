package com.sisdi.controller;

import com.sisdi.data.UserData;
import com.sisdi.service.DepartmentServiceImp;
import com.sisdi.service.OfficeServiceImp;
import com.sisdi.service.TempUserServiceImp;
import com.sisdi.service.UserServiceImp;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.util.List;
import javax.naming.InvalidNameException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import signature.PDFSignatureInfo;
import signature.PDFSignatureInfoParser;

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
    @GetMapping("/error")
    public String showError(Model model, @AuthenticationPrincipal User user, HttpSession session) {
        log.info("ejecutando el controlador Oficios");
        session.setAttribute("user", user);
        session.setAttribute("usuarioCompleto", userData.getUser(user.getUsername()));
        model.addAttribute("user", user);
        return "error";
    }
    @PostMapping("/singleFileUpload")
    public ResponseEntity<?> singleFileUpload(Model model, @RequestParam("adjunto") MultipartFile file,HttpServletResponse response ) {
        JSONObject obj = new JSONObject();
        boolean signed=false;
        if (file.isEmpty()) {
            return new ResponseEntity("please select a file!", HttpStatus.OK);
        }
        try {
            byte[] bytes = file.getBytes();
            List<PDFSignatureInfo> info = PDFSignatureInfoParser.getPDFSignatureInfo(bytes);
            if(info.size()> 0){
                signed=true;
            }else{
                signed=false;
            }          
        } catch (IOException | InvalidNameException | CertificateException| NoSuchAlgorithmException | InvalidKeyException |SignatureException | NoSuchProviderException e) {
            model.addAttribute("message", "Cannot open file: " + e.getMessage());
            e.printStackTrace();
        }
         obj.put("name", file.getOriginalFilename());
         obj.put("size", file.getSize());
         obj.put("resource", file.getResource());
         obj.put("signed", signed);       
        return new ResponseEntity(obj.toString(), new HttpHeaders(), HttpStatus.OK);
    }
     
}
