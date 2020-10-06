/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisdi.data;

import com.sisdi.model.Department;
import java.util.HashMap;

//Data Department
public class DepartmentService {
     private  HashMap<String, Department> listDepartment;
     
     public DepartmentService(){
        listDepartment = new HashMap();
        crearLista();
    }
     
    public void crearLista(){ 
        listDepartment.put("SUPERUSER", new Department(1,"SUPERUSER"));
        listDepartment.put("Concejo Municipa", new Department(2,"Concejo Municipa"));
        listDepartment.put("Alcaldia", new Department(3,"Alcaldia"));
        listDepartment.put("Desarrollo Urbano", new Department(4,"Desarrollo Urbano"));
        listDepartment.put("Hacienda Municipal", new Department(5,"Hacienda Municipal"));
        listDepartment.put("Servicios Públicos", new Department(6,"Servicios Públicos"));
        listDepartment.put("Prooveduría", new Department(7,"Prooveduría"));
        listDepartment.put("Recursos Humanos", new Department(8,"Recursos Humanos"));
    }
    public Department getDepartment(int i){
        Department aux=null;
        for(Department d:listDepartment.values()){
            if(d.getId()==i){
                aux= d;
            }
        }
        return aux;
    }
}
