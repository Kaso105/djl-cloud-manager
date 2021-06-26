
package com.djl.backend;

public class Archivo {
    
    private int id;
    private String name;
    private int size;
    private String type;
    private String user;
    private byte[] data;


    public Archivo(int id, String name, int size, String type, String user, byte[] data){
        this.id = id;
        this.name = name;
        this.size = size;
        this.type = type;
        this.user = user;
        this.data = data;
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

    public byte[] getData() {
        return data;
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

    public void setData(byte[] data) {
        this.data = data;
    }
    

}
