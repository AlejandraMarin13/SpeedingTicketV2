/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.sena.modelo.dto;

import java.sql.Time;
import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class Registro {

    private int idRegistro;
    private String usuarioNumeroDocumento;
    private String usuarioTipoDocumento;
    private Time horaEntrada;
    private Time horaSalida;
    private Date fechaEntrada;
    private Date fechaSalida;
    private String motivoVisita;
    private String rol;

    public Registro() {
    }

    public int getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(int idRegistro) {
        this.idRegistro = idRegistro;
    }

    public String getUsuarioNumeroDocumento() {
        return usuarioNumeroDocumento;
    }

    public void setUsuarioNumeroDocumento(String usuarioNumeroDocumento) {
        this.usuarioNumeroDocumento = usuarioNumeroDocumento;
    }

    public String getUsuarioTipoDocumento() {
        return usuarioTipoDocumento;
    }

    public void setUsuarioTipoDocumento(String usuarioTipoDocumento) {
        this.usuarioTipoDocumento = usuarioTipoDocumento;
    }

    public Time getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(Time horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public Time getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(Time horaSalida) {
        this.horaSalida = horaSalida;
    }

    public Date getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getMotivoVisita() {
        return motivoVisita;
    }

    public void setMotivoVisita(String motivoVisita) {
        this.motivoVisita = motivoVisita;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Registro{" + "idRegistro=" + idRegistro
                + ", usuarioNumeroDocumento=" + usuarioNumeroDocumento
                + ", usuarioTipoDocumento=" + usuarioTipoDocumento
                + ", horaEntrada=" + horaEntrada + ", horaSalida=" + horaSalida
                + ", fechaEntrada=" + fechaEntrada + ", fechaSalida="
                + fechaSalida + ", motivoVisita=" + motivoVisita + ", rol="
                + rol + '}';
    }

}
