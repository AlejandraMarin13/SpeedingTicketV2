/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.sena.modelo.dao.mysql;

import com.sun.deploy.uitoolkit.impl.fx.ui.resources.ResourceManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ADMIN
 */
public class ResourseManagerTest {
    
    public ResourseManagerTest() {
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
     * Test of getConneccion method, of class ResourseManager.
     */
    @Test
    public void testGetConneccion() throws SQLException {
       System.out.println("getConection");
        Connection resultado = ResourseManager.getConection();
        resultado.close();
      
    }

    /**
     * Test of closeConnection method, of class ResourseManager.
     */
    @Test
    public void testCloseConnection() {
        System.out.println("closeConnection");
        
        Connection resultado  = ResourseManager.getConection(); 
        ResourseManager.closeConnection(resultado);

    }

    /**
     * Test of closeResultSet method, of class ResourseManager.
     */
    @Test
    public void testCloseResultSet() throws SQLException {
      System.out.println("getConection");
        Connection resultado = ResourseManager.getConection();
        resultado.close();
    }

    public void testCloseConection() throws SQLException {
        System.out.println("getConection");
        Connection resultado = ResourseManager.getConection();
        ResourseManager.closeConnection(resultado);
    }

    public void testCloseAll() throws SQLException {
        System.out.println("CloseConection");
        Connection conexion = ResourseManager.getConection();
        ResultSet rs = null;
        PreparedStatement sentencia = null;
        String sqlPrepared = "select * from proyecto.equipo where ID_EQUIPO = ? ;";
        sentencia = conexion.prepareStatement(sqlPrepared);
        sentencia.setInt(1, 1);
        rs = sentencia.executeQuery();

        if (!rs.wasNull()) {
            while (rs.next()) {
                System.out.println("Id Equipo : " + rs.getInt(1));
                System.out.println("........................................");
            }
        }
        ResourseManager.close(rs);
        ResourseManager.closeConnection(conexion);

    }
    
}
