/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisdi.data;

import com.sisdi.model.TempUser;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//Data TempUser
public class TempUserService {
    private  HashMap<String, TempUser> listTempUser;
    
    public TempUserService(){
        listTempUser = new HashMap();
        crearLista();
    }
    public void crearLista(){  
          listTempUser.put("serviciospublicos@sanpablo.go.cr",new TempUser("Mauricio González González","serviciospublicos@sanpablo.go.cr"));
          listTempUser.put("archivocentral@sanpablo.go.cr", new TempUser("José Silva Castillo","archivocentral@sanpablo.go.cr"));
          listTempUser.put("centroatencionadultomayor@sanpablo.go.cr",new TempUser("Lizbeth Rodríguez Calderón","centroatencionadultomayor@sanpablo.go.cr"));
          listTempUser.put("cementerio@sanpablo.go.cr",new TempUser("Vanessa Valverde","cementerio@sanpablo.go.cr"));
          listTempUser.put("centrodeconocimiento@sanpablo.go.cr",new TempUser("Jennifer Conejo Vásquez","centrodeconocimiento@sanpablo.go.cr"));
          listTempUser.put("administradorcentrocultural@sanpablo.go.cr",new TempUser("Sergio Salazar Rivera","administradorcentrocultural@sanpablo.go.cr"));
          listTempUser.put("centrocultural@sanpablo.go.cr",new TempUser("Ileana Rojas Hernández","centrocultural@sanpablo.go.cr"));
          listTempUser.put("desarrollosocialinclusivo@sanpablo.go.cr",new TempUser("Yamileth Monterey López","desarrollosocialinclusivo@sanpablo.go.cr"));
          listTempUser.put("gestionambiental@sanpablo.go.cr",new TempUser("David González Ovares","gestionambiental@sanpablo.go.cr"));
          listTempUser.put("plataforma@sanpablo.go.cr",new TempUser("Marcela Sancho Villalobos","plataforma@sanpablo.go.cr"));
          listTempUser.put("plataforma.a@sanpablo.go.cr",new TempUser("Maureen Gutiérrez","plataforma.a@sanpablo.go.cr"));
          listTempUser.put("plataforma.b@sanpablo.go.cr",new TempUser("Carlos García Arguedas","plataforma.b@sanpablo.go.cr"));
          listTempUser.put("plataforma.c@sanpablo.go.cr",new TempUser("Patricia Zúñiga","plataforma.c@sanpablo.go.cr"));
          listTempUser.put("policiamunicipal@sanpablo.go.cr",new TempUser("Luis Moncada Espinoza","policiamunicipal@sanpablo.go.cr"));
          listTempUser.put("cecudi@sanpablo.go.cr",new TempUser("Karla Araya Núñez","cecudi@sanpablo.go.cr"));
    }
    
    public TempUser getTempUser(String email) {
        return listTempUser.get(email);
    }
}
