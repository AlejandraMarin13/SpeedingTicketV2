/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.sena.modelo.dto;

import java.sql.Timestamp;

/**
 *
 * @author Adrian
 */
public class RegistroEquipo {

    private int registroIdRegistro;
    private String propietarioEquipoIdEquipo;
    private String propietarioCuentaNumeroDocumento;
    private String propietariocuentaTipoDocumento;
    private Timestamp fechaSalida;
    private Timestamp fechaEntrada;

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

    public String getPropietarioCuentaNumeroDocumento() {
        return propietarioCuentaNumeroDocumento;
    }

    public void setPropietarioCuentaNumeroDocumento(String propietarioCuentaNumeroDocumento) {
        this.propietarioCuentaNumeroDocumento = propietarioCuentaNumeroDocumento;
    }

    public String getPropietariocuentaTipoDocumento() {
        return propietariocuentaTipoDocumento;
    }

    public void setPropietariocuentaTipoDocumento(String propietariocuentaTipoDocumento) {
        this.propietariocuentaTipoDocumento = propietariocuentaTipoDocumento;
    }

    public Timestamp getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Timestamp fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Timestamp getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(Timestamp fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    @Override
    public String toString() {
        return "RegistroEquipo{" + "registroIdRegistro="
                + registroIdRegistro + ", propietarioEquipoIdEquipo="
                + propietarioEquipoIdEquipo
                + ", propietarioCuentaNumeroDocumento="
                + propietarioCuentaNumeroDocumento
                + ", propietariocuentaTipoDocumento="
                + propietariocuentaTipoDocumento + ", fechaSalida="
                + fechaSalida + ", fechaEntrada=" + fechaEntrada + '}';
    }

}
