/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.sena.modelo.dto;

import java.io.Serializable;

/**
 *
 * @author ADMIN
 */
public class RegistroPk implements Serializable {

    private String idEqupo;
    private String cuentaTipoDocumento;
    private String cuentaNumeroDodumento;

    public RegistroPk() {
    }

    public String getIdEqupo() {
        return idEqupo;
    }

    public void setIdEqupo(String idEqupo) {
        this.idEqupo = idEqupo;
    }

    public String getCuentaTipoDocumento() {
        return cuentaTipoDocumento;
    }

    public void setCuentaTipoDocumento(String cuentaTipoDocumento) {
        this.cuentaTipoDocumento = cuentaTipoDocumento;
    }

    public String getCuentaNumeroDodumento() {
        return cuentaNumeroDodumento;
    }

    public void setCuentaNumeroDodumento(String cuentaNumeroDodumento) {
        this.cuentaNumeroDodumento = cuentaNumeroDodumento;
    }

    @Override
    public String toString() {
        return "RegistroPk{" + "idEqupo=" + idEqupo
                + ", cuentaTipoDocumento=" + cuentaTipoDocumento
                + ", cuentaNumeroDodumento=" + cuentaNumeroDodumento + '}';
    }

}
