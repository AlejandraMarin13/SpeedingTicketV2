/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.sena.modelo.dto;

import java.io.Serializable;

/**
 *
 * @author LINDA
 */
public class RegistroEquipoPk implements Serializable {

    private String propietarioEquipoIdEquipo;
     private String propietarioTipoDocumento;
    private String propietarioCuentaNumeroDocumento;

    public RegistroEquipoPk() {
    }

    public String getPropietarioEquipoIdEquipo() {
        return propietarioEquipoIdEquipo;
    }

    public void setPropietarioEquipoIdEquipo(String propietarioEquipoIdEquipo) {
        this.propietarioEquipoIdEquipo = propietarioEquipoIdEquipo;
    }

    public String getPropietarioTipoDocumento() {
        return propietarioTipoDocumento;
    }

    public void setPropietarioTipoDocumento(String propietarioTipoDocumento) {
        this.propietarioTipoDocumento = propietarioTipoDocumento;
    }

    public String getPropietarioCuentaNumeroDocumento() {
        return propietarioCuentaNumeroDocumento;
    }

    public void setPropietarioCuentaNumeroDocumento(String propietarioCuentaNumeroDocumento) {
        this.propietarioCuentaNumeroDocumento = propietarioCuentaNumeroDocumento;
    }

    @Override
    public String toString() {
        return "RegistroEquipoPk{" + "propietarioEquipoIdEquipo=" + propietarioEquipoIdEquipo 
                + ", propietarioTipoDocumento=" + propietarioTipoDocumento 
                + ", propietarioCuentaNumeroDocumento=" + propietarioCuentaNumeroDocumento + '}';
    }
   

}
