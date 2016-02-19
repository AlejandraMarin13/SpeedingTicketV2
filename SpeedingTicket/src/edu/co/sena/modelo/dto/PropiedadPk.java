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
public class PropiedadPk {

    private String equipoIdEquipo;
    private  String cuentaNumeroDocumento;
    private  String cuentaTipoDocumento;

    public PropiedadPk() {
    }

  
    public String getEquipoIdEquipo() {
        return equipoIdEquipo;
    }

    public void setEquipoIdEquipo(String equipoIdEquipo) {
        this.equipoIdEquipo = equipoIdEquipo;
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
 @Override
    public String toString() {
        return "PropiedadPk{" + "equipoIdEquipo=" + equipoIdEquipo
                + ", cuentaNumeroDocumento=" + cuentaNumeroDocumento
                + ", cuentaTipoDocumento=" + cuentaTipoDocumento + '}';
    }

}
