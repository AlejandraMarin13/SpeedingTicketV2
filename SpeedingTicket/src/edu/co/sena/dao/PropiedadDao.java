/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.sena.dao;

import edu.co.sena.modelo.dto.Propiedad;
import java.util.List;

/**
 *
 * @author LINDA
 */
public interface PropiedadDao {

    public List<Propiedad> findAll();

    public void insert(Propiedad dto);

    public void update(PropiedadPk llaveDto, Propiedad dto);

    public void updatePk(PropiedadPk viejo, PropiedadPk nuevo);

    public void delete(PropiedadPk dot);

    public List<Propiedad> findByPK(PropiedadPk dto);

    public int count();

    public static class PropiedadPk {

        public PropiedadPk() {
        }
    }
}
