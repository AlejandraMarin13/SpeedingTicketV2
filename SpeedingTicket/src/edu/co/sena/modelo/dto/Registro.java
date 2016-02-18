/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.sena.modelo.dto;

import java.sql.Time;
import java.sql.Timestamp;


/**
 *
 * @author ADMIN
 */
public class Registro {

    private int idRegistro;
    private String cuentaNumeroDocumento;
    private String cuentaTipoDocumento;
    private Timestamp fechaHoraEntrada;
    private Timestamp fechaHoraSalida;
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

    public Timestamp getFechaHoraEntrada() {
        return fechaHoraEntrada;
    }

    public void setFechaHoraEntrada(Timestamp fechaHoraEntrada) {
        this.fechaHoraEntrada = fechaHoraEntrada;
    }

    public Timestamp getFechaHoraSalida() {
        return fechaHoraSalida;
    }

    public void setFechaHoraSalida(Timestamp fechaHoraSalida) {
        this.fechaHoraSalida = fechaHoraSalida;
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
                + ", cuentaNumeroDocumento=" + cuentaNumeroDocumento
                + ", cuentaTipoDocumento=" + cuentaTipoDocumento
                + ", fechaHoraEntrada=" + fechaHoraEntrada
                + ", fechaHoraSalida=" + fechaHoraSalida
                + ", motivoVisita=" + motivoVisita + ", rol=" + rol + '}';
    }

}
