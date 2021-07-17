package com.djl.backend;
import java.io.Serializable;



public class Archivo implements Serializable {
    
    private int id;
    private String name;
    private int size;
    private String type;
    private String user;

    public Archivo(int id, String name, int size, String type, String user){
        this.id = id;
        this.name = name;
        this.size = size;
        this.type = type;
        this.user = user;
    }

    public Archivo() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public String getType() {
        return type;
    }

    public String getUser() {
        return user;
    }

    
    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUser(String user) {
        this.user = user;
    }

}