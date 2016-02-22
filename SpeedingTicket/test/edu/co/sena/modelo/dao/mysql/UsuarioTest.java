/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.sena.modelo.dao.mysql;

import edu.co.sena.dao.UsuarioDAO;
import edu.co.sena.modelo.dto.Usuario;
import edu.co.sena.modelo.dto.UsuarioPk;

import edu.co.sena.modelo.factory.DAOFactory;
import edu.co.sena.modelo.factory.MySQLFactory;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Adrian
 */
public class UsuarioTest {

    public UsuarioTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getTableName method, of class UsuarioDAOImpl.
     */
    /**
     * Test of findAll method, of class UsuarioDAOImpl.
     */
    @Test
    public void testFindAll() {
        System.out.println("findAll");
        DAOFactory f = new MySQLFactory();
        UsuarioDAO instance = f.crearUsuario();
        List<Usuario> result = instance.findAll();
        for (Usuario usuario : result) {
            System.out.println(usuario.toString());
        }
        System.out.println("____________________");
    }

    /**
     * Test of insert method, of class UsuarioDAOImpl.
     */
    @Test
    public void testInsert() {
        System.out.println("insert");
        DAOFactory f = new MySQLFactory();
        Usuario usuario = new Usuario();
        usuario.setCorreo("fas@dasa");
        usuario.setContraseña("9876543");
        usuario.setCuentaNumeroDocumento("1012443153");
        usuario.setCuentaTipoDocumento("TI");
        usuario.setRol("administrador");

        UsuarioDAO instance = f.crearUsuario();

        instance.insert(usuario);

    }

    @Test
    public void testUpdate() {
        System.out.println("update");
        DAOFactory f = new MySQLFactory();
        UsuarioPk llave = new UsuarioPk("01");
        Usuario usuario = new Usuario();
        usuario.setCuentaNumeroDocumento("98030569261");
        usuario.setCuentaTipoDocumento("TI");
        usuario.setCorreo("fas@dasdsa");
        usuario.setContraseña("9876543wq");
        UsuarioDAO instance = f.crearUsuario();
        instance.update(llave, usuario);

    }

}
