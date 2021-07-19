/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djl.backend;

import java.util.ArrayList;

/**
 *
 * @author Kaso5-PC
 */
public class Usuario {
    private String password;
    private String userName;
    private ArrayList<Archivo> archivos;
    
    public Usuario(String password,String userName,ArrayList<Archivo> archivos){
        this.password=password;
        this.userName=userName;
        this.archivos=archivos;
    }

    public Usuario() {
    }

    public void setArchivos(ArrayList<Archivo> archivos) {
        this.archivos = archivos;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public ArrayList<Archivo> getArchivos() {
        return archivos;
    }
    
}
