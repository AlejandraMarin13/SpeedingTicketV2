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
public class EquipoPk implements Serializable{
    
    private String idEquipo;

    public EquipoPk() {
    }

    public String getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(String idEquipo) {
        this.idEquipo = idEquipo;
    }

    @Override
    public String toString() {
        return "EquipoPk{" + "idEquipo=" + idEquipo + '}';
    }
    
    
}
