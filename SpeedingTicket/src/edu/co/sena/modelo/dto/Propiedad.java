/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.sena.modelo.dto;

/**
 *
 * @author ADMIN
 */
public class Propiedad {
    
     private String idEquipo;
    private String numeroDocumento;
    private String tipoDocumento;

    public Propiedad() {
    }

    public String getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(String idEquipo) {
        this.idEquipo = idEquipo;
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
        return "Propiedad{" + "idEquipo=" + idEquipo 
                + ", numeroDocumento=" + numeroDocumento 
                + ", tipoDocumento=" + tipoDocumento + '}';
    }
    
    
}
