package com.djl.backend;
import java.io.Serializable;



public class Archivo implements Serializable {
    
    private int id;
    private String name;
    private float size;
    private String type;

    public Archivo(int id, String name,float size, String type){
        this.name = name;
        this.size = size;
        this.type = type;
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

    public float getSize() {
        return size;
    }

    public String getType() {
        return type;
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


}