/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.sena.modelo.dto;

/**
 *
 * @author PCOPEN
 */
public class Usuario {
    
    private String login;
    private String correo;
    private String contraseña;
    private String cuentaNumeroDocumento;
    private String cuentaTipoDocumento;
    private String rol;

    public Usuario() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getCuentaNumeroDocumento() {
        return cuentaNumeroDocumento;
    }

    public void setCuentaNumeroDocumento(String cuentaNumeroDocumento) {
        this.cuentaNumeroDocumento = cuentaNumeroDocumento;
    }

    public String getCuentaTipoDocumento() {
        return cuentaTipoDocumento;
    }

    public void setCuentaTipoDocumento(String cuentaTipoDocumento) {
        this.cuentaTipoDocumento = cuentaTipoDocumento;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Usuario{" + "login=" + login 
                + ", correo=" + correo 
                + ", contrase\u00f1a=" + contraseña 
                + ", cuentaNumeroDocumento=" + cuentaNumeroDocumento 
                + ", cuentaTipoDocumento=" + cuentaTipoDocumento 
                + ", rol=" + rol + '}';
    }

    
    
  

    
 
    
    
    

}
