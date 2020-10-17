/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisdi.data;

import com.sisdi.model.Department;
import com.sisdi.service.DepartmentServiceImp;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//Data Department
@Service
public class DepartmentData {
    @Autowired
    private DepartmentServiceImp departmentServiceImp;
    
    private  HashMap<Integer, Department> listDepartment;
     
     public void init(){
        listDepartment = new HashMap();
        crearLista();
    }
     
    public void crearLista(){ 
        List<Department> tempDepartments=departmentServiceImp.listDepartment();
        for(Department tD:tempDepartments){
              listDepartment.put(tD.getId(), tD);
          }
    }
    public Department getDepartment(int i){
        return this.listDepartment.get(i);
    }
}
