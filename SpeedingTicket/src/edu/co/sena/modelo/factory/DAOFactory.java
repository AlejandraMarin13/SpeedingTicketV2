/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.sena.modelo.factory;

import edu.co.sena.dao.CuentaDAO;
import edu.co.sena.dao.EquipoDAO;
import edu.co.sena.dao.PropiedadDAO;
import edu.co.sena.dao.RegistroDAO;
import edu.co.sena.dao.RegistroEquipoDAO;
import edu.co.sena.dao.UsuarioDAO;

/**
 *
 * @author PCOPEN
 */
public interface DAOFactory {

    public CuentaDAO creaCuenta();

    public EquipoDAO creaEquipo();

    public RegistroDAO creaRegistro();

    public PropiedadDAO creaPropiedad();

    public UsuarioDAO creaUsuario();

    public RegistroEquipoDAO creaRegistroEquipo();

}
