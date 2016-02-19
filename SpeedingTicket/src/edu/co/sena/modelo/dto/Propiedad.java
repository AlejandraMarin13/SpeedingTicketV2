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
    
    private String EquipoIdEquipo;
    private String CuentaNumeroDocumento;
    private String CuentaTipoDocumento;

    public Propiedad() {
    }

    
    public String getEquipoIdEquipo() {
        return EquipoIdEquipo;
    }

    public void setEquipoIdEquipo(String EquipoIdEquipo) {
        this.EquipoIdEquipo = EquipoIdEquipo;
    }

    public String getCuentaNumeroDocumento() {
        return CuentaNumeroDocumento;
    }

    public void setCuentaNumeroDocumento(String CuentaNumeroDocumento) {
        this.CuentaNumeroDocumento = CuentaNumeroDocumento;
    }

    public String getCuentaTipoDocumento() {
        return CuentaTipoDocumento;
    }

    public void setCuentaTipoDocumento(String CuentaTipoDocumento) {
        this.CuentaTipoDocumento = CuentaTipoDocumento;
    }
    
   
    @Override
    public String toString() {
        return "Propiedad{" + "EquipoIdEquipo=" + EquipoIdEquipo 
                + ", CuentaNumeroDocumento=" + CuentaNumeroDocumento 
                + ", CuentaTipoDocumento=" + CuentaTipoDocumento + '}';
    }
 
}
