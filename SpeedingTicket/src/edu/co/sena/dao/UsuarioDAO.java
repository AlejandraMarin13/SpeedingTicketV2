/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.sena.dao;

import edu.co.sena.modelo.dto.Usuario;
import edu.co.sena.modelo.dto.UsuarioPk;
import java.util.List;

/**
 *
 * @author Adrian
 */
public interface UsuarioDAO {
    
     public List<Usuario> findAll();

    public void insert(Usuario dto);

    public void update(UsuarioPk llavedto, Usuario dto);

    public void updatePk(UsuarioPk viejo, UsuarioPk nuevo);

    public void delete(UsuarioPk dto);

    public List<Usuario> findByPK(UsuarioPk dto);

    public int count();
}
