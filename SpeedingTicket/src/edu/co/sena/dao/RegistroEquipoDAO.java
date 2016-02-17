/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.sena.dao;

import edu.co.sena.modelo.dto.RegistroEquipo;
import edu.co.sena.modelo.dto.RegistroEquipoPk;
import java.util.List;

/**
 *
 * @author Adrian
 */
public interface RegistroEquipoDAO {

    public List<RegistroEquipo> findAll();

    public void insert(RegistroEquipo registroEquipoDTO);

    public void update(RegistroEquipo registroEquipoDTO);

    public void updatePK(RegistroEquipoPk nuevo, RegistroEquipoPk viejo);
}
