package com.sisdi.data;

import com.sisdi.model.Usuario;
import java.util.HashMap;
import java.util.List;


public final class UserService {
    
   private  HashMap<String, Usuario> listUser; 
   
   private TempUserService tUServ;
   private DepartmentService depServ;
   
    public UserService(){
        this.tUServ=new TempUserService();
        this.depServ=new DepartmentService();
        listUser = new HashMap();
        crearLista();
    }
   
    public void crearLista(){
        listUser.put("serviciospublicos@sanpablo.go.cr", new Usuario(this.tUServ.getTempUser("serviciospublicos@sanpablo.go.cr"),
                "serviciospublicos",this.depServ.getDepartment(6),true,true));

        listUser.put("archivocentral@sanpablo.go.cr", new Usuario(this.tUServ.getTempUser("archivocentral@sanpablo.go.cr"),
                "archivocentral",this.depServ.getDepartment(6),true));

        listUser.put("centroatencionadultomayor@sanpablo.go.cr", new Usuario(this.tUServ.getTempUser("centroatencionadultomayor@sanpablo.go.cr"),
                "centroatencionadultomayor",this.depServ.getDepartment(6),true));

        listUser.put("cecudi@sanpablo.go.cr", new Usuario(this.tUServ.getTempUser("cecudi@sanpablo.go.cr"),
                "cecudi",this.depServ.getDepartment(6),true));

        listUser.put("cementerio@sanpablo.go.cr", new Usuario(this.tUServ.getTempUser("cementerio@sanpablo.go.cr"),
                "cementerio",this.depServ.getDepartment(6),true));

        listUser.put("centrodeconocimiento@sanpablo.go.cr", new Usuario(this.tUServ.getTempUser("centrodeconocimiento@sanpablo.go.cr"),
                "centrodeconocimiento",this.depServ.getDepartment(6),true));

        listUser.put("administradorcentrocultural@sanpablo.go.cr", new Usuario(this.tUServ.getTempUser("administradorcentrocultural@sanpablo.go.cr"),
                "administradorcentrocultural",this.depServ.getDepartment(6),true));

        listUser.put("desarrollosocialinclusivo@sanpablo.go.cr", new Usuario(this.tUServ.getTempUser("desarrollosocialinclusivo@sanpablo.go.cr"),
                "desarrollosocialinclusivo",this.depServ.getDepartment(6),true));

        listUser.put("gestionambiental@sanpablo.go.cr", new Usuario(this.tUServ.getTempUser("gestionambiental@sanpablo.go.cr"),
                "gestionambiental",this.depServ.getDepartment(6),true));

        listUser.put("plataforma@sanpablo.go.cr", new Usuario(this.tUServ.getTempUser("plataforma@sanpablo.go.cr"),
                "serviciospublicos",this.depServ.getDepartment(6),true));

        listUser.put("plataforma.a@sanpablo.go.cr", new Usuario(this.tUServ.getTempUser("plataforma.a@sanpablo.go.cr"),
                "plataformaa",this.depServ.getDepartment(6),true));

         listUser.put("plataforma.b@sanpablo.go.cr", new Usuario(this.tUServ.getTempUser("plataforma.b@sanpablo.go.cr"),
                "plataformab",this.depServ.getDepartment(6),true));
         
        listUser.put("plataforma.c@sanpablo.go.cr", new Usuario(this.tUServ.getTempUser("plataforma.c@sanpablo.go.cr"),
                "plataformac",this.depServ.getDepartment(6),true));
        
        listUser.put("policiamunicipal@sanpablo.go.cr", new Usuario(this.tUServ.getTempUser("policiamunicipal@sanpablo.go.cr"),
                "policiamunicipal",this.depServ.getDepartment(6),true));
    }
    
     public Usuario login(String email, String pass) throws Exception {
        if (listUser.get(email) != null && listUser.get(email).getPassword().equals(pass)) {
            return listUser.get(email);
        } else {
            throw new Exception("Usuario no existe");
        }
    }
     public Usuario getUser(String email){
         return listUser.get(email);
     }
     public  HashMap<String, Usuario> getUsuarios(){
         return listUser;
     }
    
}
