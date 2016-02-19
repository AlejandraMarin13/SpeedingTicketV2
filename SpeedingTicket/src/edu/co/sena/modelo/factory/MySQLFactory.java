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
public class MySQLFactory  extends DAOAbstractFactory {

    @Override
    public CuentaDAO creaCuenta() {
       return new CuentaDAOImp();
    }

    @Override
    public EquipoDAO creaEquipo() {
       return new EquipoDAOImp();
    }

    @Override
    public RegistroDAO creaRegistro() {
        return new RegistroDAOImp();
    }

    @Override
    public PropiedadDAO creaPropiedad() {
      return new PropiedadDAOImp();
    }
    @Override
    public UsuarioDAO creaUsuario() {
        return new UsuarioDAOImpl();
         }

    @Override
    public RegistroEquipoDAO creaRegistroEquipo() {
      return new RegistroEquipoDAOImp();
    }
    
}
