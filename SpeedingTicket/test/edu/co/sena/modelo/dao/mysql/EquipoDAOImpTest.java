/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.sena.modelo.dao.mysql;

import edu.co.sena.dao.EquipoDAO;
import edu.co.sena.modelo.dto.Equipo;
import edu.co.sena.modelo.dto.EquipoPk;
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
public class EquipoDAOImpTest {
    
    public EquipoDAOImpTest() {
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
     * Test of findAll method, of class EquipoDAOImp.
     */
    @Test
    public void testFindAll() {
        System.out.println("findAll");
        DAOFactory equipo = new MySQLFactory();
        EquipoDAO instance =equipo.crearEquipo();
        List<Equipo> result = instance.findAll();
        
     for(Equipo equipo1 : result){
         System.out.println(equipo1.toString());
       
    }
        System.out.println("--------------------------");
    }

    /**
     * Test of insert method, of class EquipoDAOImp.
     */
    @Test
    public void testInsert() {
        System.out.println("insert");
        DAOFactory equipo = new MySQLFactory();
       Equipo equipores = new Equipo("12", "Hp", "HP-255");
        equipores.setIdEquipo("12");
        equipores.setMarca("hp");
        equipores.setDescripcion("HP-255");
        EquipoDAO instance = equipo.crearEquipo();
        instance.insert(equipores);
        System.out.println("--------------------------------");

    }
 
    /**
     * Test of update method, of class EquipoDAOImp.
     */
    @Test
    public void testUpdate_EquipoPk_Equipo() {
       
        System.out.println("update");
        EquipoPk llaveDto = null;
        Equipo dto = null;
        EquipoDAOImp instance = new EquipoDAOImp();
        instance.update(llaveDto, dto);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }


    /**
     * Test of update method, of class EquipoDAOImp.
     */
    @Test
    public void testUpdate_Equipo() {
        System.out.println("update");
        DAOFactory equipo = new MySQLFactory();
        EquipoPk llave = new EquipoPk();
        Equipo equipoNuevo = new Equipo("16", "hp", "hp-256");
        equipoNuevo.setIdEquipo("15");
        equipoNuevo.setMarca("Dell");
        equipoNuevo.setDescripcion("Dell_12");
        EquipoDAO instance = equipo.crearEquipo();
        instance.update(equipoNuevo);
        System.out.println("-------------------------------");
    }


    /**
     * Test of updatePK method, of class EquipoDAOImp.
     */
    @Test
    public void testUpdatePK() {
        System.out.println("updatePK");
        DAOFactory equipo = new MySQLFactory();
        EquipoPk viejaLlave = new EquipoPk();
        EquipoPk nuevaLlave = new EquipoPk();
        
        EquipoDAO instance = equipo.crearEquipo();
        instance.updatePK(nuevaLlave, viejaLlave);
        System.out.println("---------------------------------------");

    }

    /**
     * Test of findByPK method, of class EquipoDAOImp.
     */
    @Test
    public void testFindByPK() {
        System.out.println("findByPK");
        DAOFactory equipo = new MySQLFactory();
        EquipoDAO instance = equipo.crearEquipo();
        EquipoPk llaves = new EquipoPk();
        List <Equipo> result = instance.findByPK(llaves);
        for(Equipo equipo1 : result){
            System.out.println(equipo1.toString());
            System.out.println("---------------------------------------");
        }

    }

    /**
     * Test of delete method, of class EquipoDAOImp.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        DAOFactory equipo = new MySQLFactory();
        EquipoPk borrarLlaves = new EquipoPk();
        EquipoDAO instance = equipo.crearEquipo();
        instance.delete(borrarLlaves);
        System.out.println("---------------------------------------");

    }

    /**
     * Test of count method, of class EquipoDAOImp.
     */
    @Test
    public void testCount() {
        System.out.println("count");
        DAOFactory equipo = new MySQLFactory();
        EquipoDAO instance = equipo.crearEquipo();
        System.out.println(instance.count());
        

    }
    
}
