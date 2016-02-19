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

    private String idRegistro;
    private String cuentaTipoDocumento;
    private String cuentaNumeroDocumento;

    public RegistroPk() {
    }

    public RegistroPk(String idRegistro, String cuentaTipoDocumento, String cuentaNumeroDocumento) {
        this.idRegistro = idRegistro;
        this.cuentaTipoDocumento = cuentaTipoDocumento;
        this.cuentaNumeroDocumento = cuentaNumeroDocumento;
    }

    public String getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(String idRegistro) {
        this.idRegistro = idRegistro;
    }

    public String getCuentaTipoDocumento() {
        return cuentaTipoDocumento;
    }

    public void setCuentaTipoDocumento(String cuentaTipoDocumento) {
        this.cuentaTipoDocumento = cuentaTipoDocumento;
    }

    public String getCuentaNumeroDocumento() {
        return cuentaNumeroDocumento;
    }

    public void setCuentaNumeroDocumento(String cuentaNumeroDocumento) {
        this.cuentaNumeroDocumento = cuentaNumeroDocumento;
    }

    @Override
    public String toString() {
        return "RegistroPk{" + "idEqupo=" + idRegistro
                + ", cuentaTipoDocumento=" + cuentaTipoDocumento
                + ", cuentaNumeroDodumento=" + cuentaNumeroDocumento + '}';
    }

}
