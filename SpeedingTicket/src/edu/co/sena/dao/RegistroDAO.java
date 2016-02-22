/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.sena.dao;

import edu.co.sena.modelo.dto.Cuenta;
import edu.co.sena.modelo.dto.CuentaPk;
import edu.co.sena.modelo.dto.Registro;
import edu.co.sena.modelo.dto.RegistroPk;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface RegistroDAO {
    
    public List<Registro> findAll();

    public void insert(Registro dto);

    public void update(RegistroPk llaveDto, Registro dto);

    public void updatePk(RegistroPk llaveVieja, RegistroPk llaveNueva ,
            RegistroPk registroNuevo , RegistroPk registroViejo ,
            RegistroPk numNuevo , RegistroPk numViejo );

    public void delete(RegistroPk dto);
    
    public List<Registro> findByPK(RegistroPk dto);

    public int count();
}

     

    

