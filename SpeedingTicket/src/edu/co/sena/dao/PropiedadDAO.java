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
public interface PropiedadDAO {
    public List<Propiedad> findAll();

    public void insert(Propiedad dto);

    public void delete(Propiedad dot);

    public List<Propiedad> findBy(Propiedad dto);

    public int count();
}
