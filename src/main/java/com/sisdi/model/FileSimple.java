/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisdi.model;

/**
 *
 * @author AZVal
 */
import lombok.Data;

@Data
public class FileSimple {
    private String fileName;
    private String dateCreateFile;
    private String owner;
    private String department;
    private int officeAmount;
    private String observations;
}
