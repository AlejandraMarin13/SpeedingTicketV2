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
    
     private String registroIdEquipo;
     private int registroIdRegistro;

    public EquipoPk() {
        
   
    }

    public String getRegistroIdEquipo() {
        return registroIdEquipo;
    }

    public void setRegistroIdEquipo(String registroIdEquipo) {
        this.registroIdEquipo = registroIdEquipo;
    }

    public int getRegistroidRegistro() {
        return registroIdRegistro;
    }

    public void setRegistroidRegistro(int registroidRegistro) {
        this.registroIdRegistro = registroidRegistro;
    }

    @Override
    public String toString() {
        return "EquipoPk{" + "registroIdEquipo=" + registroIdEquipo 
                           + ", registroidRegistro=" + registroIdRegistro + '}';
    }
    
    
    
    }

    
