/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.sena.modelo.dto;

import java.io.Serializable;

/**
 *
 * @author PCOPEN
 */
public class CuentaPk implements Serializable{
   
    private String numeroDocumento;
    private String tipoDocumento;

    public CuentaPk() {
    }

    
    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    @Override
    public String toString() {
        return "CuentaPk{" + "numeroDocumento=" + numeroDocumento 
                           + ", tipoDocumento=" + tipoDocumento + '}';
    }

    
   
    
}
