/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.sena.modelo.dao.mysql;

import edu.co.sena.dao.PropiedadDAO;
import edu.co.sena.modelo.dto.Propiedad;
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
 * @author ADMIN
 */
public class PropiedadDAOImpTest {
    
    public PropiedadDAOImpTest() {
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
     * Test of findAll method, of class PropiedadDAOImp.
     */
    @Test
    public void testFindAll() {
        System.out.println("findAll");
        DAOFactory propiedad = new MySQLFactory();
      PropiedadDAO instance = propiedad.crearPropietario();
      List<Propiedad > result = instance.findAll();
      for(Propiedad propiedad1 : result){
          System.out.println(propiedad1.toString());
      }
     
    }

    /**
     * Test of insert method, of class PropiedadDAOImp.
     */
    @Test
    public void testInsert() {
         System.out.println("insertar propietario");
           DAOFactory propiedad = new MySQLFactory();
         Propiedad propi = new Propiedad("125", "C.C" , "1023548");
           propi.setEquipoIdEquipo("125");
           propi.setCuentaTipoDocumento("C.C");
           propi.setCuentaNumeroDocumento("1023548");
           PropiedadDAO instance = propiedad.crearPropietario();
           instance.insert(propi);
           System.out.println("--------------------------------");

    }
 

    /**
     * Test of delete method, of class PropiedadDAOImp.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        DAOFactory propiedad = new MySQLFactory();
        Propiedad borrarLlaves = new Propiedad();
        borrarLlaves.setEquipoIdEquipo("123");
        borrarLlaves.setCuentaTipoDocumento("C.C");
        borrarLlaves.setCuentaNumeroDocumento("1023548");
        PropiedadDAO instance = propiedad.crearPropietario();
        instance.delete(borrarLlaves);

    }

    /**
     * Test of count method, of class PropiedadDAOImp.
     */
    @Test
    public void testCount() {
        System.out.println("count");
          DAOFactory propiedad = new MySQLFactory();
          PropiedadDAO instance = propiedad.crearPropietario();
          System.out.println(instance.count());

    
    }

    /**
     * Test of findByPK method, of class PropiedadDAOImp.
     */
    @Test
    public void testFindByPK() {
        System.out.println("findByPK");
          DAOFactory propiedad = new MySQLFactory();
          PropiedadDAO instance = propiedad.crearPropietario();
          Propiedad llaves = new Propiedad();
          llaves.setEquipoIdEquipo("5");
          llaves.setCuentaTipoDocumento("T.I");
          llaves.setCuentaNumeroDocumento("1054785214");
          List<Propiedad> result = instance.findAll();
          for(Propiedad propiedad1 : result){
              System.out.println(propiedad1.toString());
              System.out.println("---------------------------------------");
          }
 
    }

    
}
