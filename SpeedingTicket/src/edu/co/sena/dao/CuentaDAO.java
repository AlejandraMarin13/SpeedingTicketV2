/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.sena.dao;

import edu.co.sena.modelo.dto.Cuenta;
import edu.co.sena.modelo.dto.CuentaPk;
import java.util.List;

/**
 *
 * @author PCOPEN
 */
public interface CuentaDAO {
    
     public List<Cuenta> findAll();

    public void insert(Cuenta dto);

    public void update(CuentaPk llaveDto, Cuenta dto);

    public void updatePk(CuentaPk viejo, CuentaPk nuevo);

    public void delete(CuentaPk dto);

    public List<Cuenta> findByPK(CuentaPk dto);

    public int count();
}


