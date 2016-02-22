/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.sena.modelo.factory;

import edu.co.sena.modelo.dao.mysql.CuentaDAOImp;
import edu.co.sena.modelo.dao.mysql.EquipoDAOImp;
import edu.co.sena.modelo.dao.mysql.PropiedadDAOImp;
import edu.co.sena.modelo.dao.mysql.RegistroDAOImp;
import edu.co.sena.modelo.dao.mysql.RegistroEquipoDAOImp;
import edu.co.sena.modelo.dao.mysql.UsuarioDAOImpl;

/**
 *
 * @author PCOPEN
 */
public interface DAOFactory {

    public CuentaDAOImp crearCuenta();
    public EquipoDAOImp crearEquipo();
    public PropiedadDAOImp crearPropietario();
    public RegistroDAOImp crearRegistro();
    public RegistroEquipoDAOImp crearRegistroEquipo();
    public UsuarioDAOImpl crearUsuario();

}
