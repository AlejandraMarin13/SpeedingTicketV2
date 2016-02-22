/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.sena.modelo.dto;

import java.io.Serializable;

/**
 *
 * @author Adrian
 */
public class UsuarioPk implements Serializable{

 private String login;

    public UsuarioPk() {
    }

    public UsuarioPk(String login) {
        this.login = login;
    }

   

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String toString() {
        return "UsuarioPk{" + "login=" + login + '}';
    }
    
}
