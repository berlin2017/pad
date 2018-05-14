package com.berlin.testpad.user;

import org.litepal.crud.DataSupport;

public class User extends DataSupport{

    private int id;
    private String username;
    private String pass;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
