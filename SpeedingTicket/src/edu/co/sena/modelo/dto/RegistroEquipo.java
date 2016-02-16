/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.sena.modelo.dto;

/**
 *
 * @author Adrian
 */
public class RegistroEquipo {
    
    private int registroIdRegistro;
    private String propietarioEquipoIdEquipo;
    private String propietarioNumeroDocumento;
    private String propietarioTipoDocumento;

    public RegistroEquipo() {
    }

    public int getRegistroIdRegistro() {
        return registroIdRegistro;
    }

    public void setRegistroIdRegistro(int registroIdRegistro) {
        this.registroIdRegistro = registroIdRegistro;
    }

    public String getPropietarioEquipoIdEquipo() {
        return propietarioEquipoIdEquipo;
    }

    public void setPropietarioEquipoIdEquipo(String propietarioEquipoIdEquipo) {
        this.propietarioEquipoIdEquipo = propietarioEquipoIdEquipo;
    }

    public String getPropietarioNumeroDocumento() {
        return propietarioNumeroDocumento;
    }

    public void setPropietarioNumeroDocumento(String propietarioNumeroDocumento) {
        this.propietarioNumeroDocumento = propietarioNumeroDocumento;
    }

    public String getPropietarioTipoDocumento() {
        return propietarioTipoDocumento;
    }

    public void setPropietarioTipoDocumento(String propietarioTipoDocumento) {
        this.propietarioTipoDocumento = propietarioTipoDocumento;
    }

    @Override
    public String toString() {
        return "RegistroEquipo{" + "registroIdRegistro=" + registroIdRegistro 
                + ", propietarioEquipoIdEquipo=" + propietarioEquipoIdEquipo 
                + ", propietarioNumeroDocumento=" + propietarioNumeroDocumento 
                + ", propietarioTipoDocumento=" + propietarioTipoDocumento + '}';
    }
    
}
