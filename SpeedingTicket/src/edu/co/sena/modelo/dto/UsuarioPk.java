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

    private String cuentaNumeroDocumento;
    private String cuentaTipoDocumento;
    

    public String getCuentaNumeroDocumento() {
        return cuentaNumeroDocumento;
    }

    public UsuarioPk() {
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

    @Override
    public String toString() {
        return "UsuarioPk{" + "cuentaNumeroDocumento=" + cuentaNumeroDocumento
                + ", cuentaTipoDocumento=" + cuentaTipoDocumento + '}';
    }

}
