/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.sena.dao;


import edu.co.sena.modelo.dto.Equipo;
import edu.co.sena.modelo.dto.EquipoPk;
import java.util.List;

/**
 *
 * @author PCOPEN
 */
public interface EquipoDAO {
   
    public abstract String getTableName();

    public abstract List<Equipo> findAll();

    public abstract void insert(Equipo equipoDTO);

    public abstract void update(Equipo equipoDTO);
    public void delete(EquipoPk dto);
    public void updatePK(EquipoPk nuevo, EquipoPk viejo);
    public List<Equipo> findByPK(EquipoPk dto);
    public int count();
}
 

