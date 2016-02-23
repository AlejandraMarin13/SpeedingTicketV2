/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.sena.modelo.dao.mysql;

import edu.co.sena.dao.RegistroEquipoDAO;
import edu.co.sena.modelo.dto.RegistroEquipo;
import edu.co.sena.modelo.dto.RegistroEquipoPk;
import edu.co.sena.modelo.factory.DAOFactory;
import edu.co.sena.modelo.factory.MySQLFactory;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *y desp
 * @author ADMIN
 */
public class RegistroEquipoDAOImpTest {

    public RegistroEquipoDAOImpTest() {
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
     * Test of findAll method, of class RegistroEquipoDAOImp.
     */
    @Test
    public void testFindAll() {
        System.out.println("findAll");
        DAOFactory regequipo = new MySQLFactory();
        RegistroEquipoDAO instance = regequipo.crearRegistroEquipo();
        List<RegistroEquipo> result = instance.findAll();
        for (RegistroEquipo regeEquipo : result) {
            System.out.println(regequipo.toString());
        }
        System.out.println("------------------------------------");

    }

    /**
     * Test of insert method, of class RegistroEquipoDAOImp.
     */
    @Test
    public void testInsert() {

        Calendar c1 = GregorianCalendar.getInstance();
        c1.set(2016, 10, 22, 06, 05, 33);
        Timestamp fechaEntrada = new Timestamp(c1.getTimeInMillis());

        Calendar c2 = GregorianCalendar.getInstance();
        c1.set(2016, 10, 22, 03, 05, 33);
        Timestamp fechaSalida = new Timestamp(c2.getTimeInMillis());
        System.out.println("Se Insertara Registro");
        DAOFactory regequipo = new MySQLFactory();
        RegistroEquipo registroRegistroDTO = new RegistroEquipo();
        registroRegistroDTO.setRegistroIdRegistro(12);
        registroRegistroDTO.setPropietariocuentaTipoDocumento("T.I");
        registroRegistroDTO.setPropietarioCuentaNumeroDocumento("98125423140");
        registroRegistroDTO.setPropietarioEquipoIdEquipo("21");
        registroRegistroDTO.setFechaEntrada(fechaEntrada);
        registroRegistroDTO.setFechaSalida(fechaSalida);

        RegistroEquipoDAO instance = regequipo.crearRegistroEquipo();
        instance.insert(registroRegistroDTO);

    }

    /**
     * Test of update method, of class RegistroEquipoDAOImp.
     */
    @Test
    public void testUpdate() {

        Calendar c1 = GregorianCalendar.getInstance();
        c1.set(2016, 10, 22, 06, 05, 33);
        Timestamp fechaEntrada = new Timestamp(c1.getTimeInMillis());

        Calendar c2 = GregorianCalendar.getInstance();
        c1.set(2016, 10, 22, 03, 05, 33);
        Timestamp fechaSalida = new Timestamp(c2.getTimeInMillis());

        DAOFactory regequipo = new MySQLFactory();
        RegistroEquipoPk llave = new RegistroEquipoPk("12", "T.I","98125423140",  "21");
        RegistroEquipo nuevoRegistro = new RegistroEquipo();
        nuevoRegistro.setFechaEntrada(fechaEntrada);
        nuevoRegistro.setFechaSalida(fechaSalida);

        RegistroEquipoDAO instance = regequipo.crearRegistroEquipo();
        instance.update(nuevoRegistro);
        System.out.println("----------------------------------");
    }

    /**
     * Test of findByPK method, of class RegistroEquipoDAOImp.
     */
    @Test

    public void testFindByPK() {
     DAOFactory regequipo = new MySQLFactory();
     RegistroEquipoDAO instance = regequipo.crearRegistroEquipo();
     RegistroEquipoPk llaves = new RegistroEquipoPk("41", "T.I", "971452356", "52");
     List<RegistroEquipo> result = instance.findByPK(llaves);
     for(RegistroEquipo registroEquipo : result){
         System.out.println(registroEquipo.toString());
     }
        System.out.println("------------------------------------------");
    }

    /**
     * Test of updatePk method, of class RegistroEquipoDAOImp.
     */
    @Test
    public void testUpdatePk() {
          System.out.println("Update Registro Equipo");
       DAOFactory regequipo = new MySQLFactory();
        RegistroEquipoPk viejoRegistro  = new RegistroEquipoPk("12","T.I", "98125423140",  "12");
        RegistroEquipoPk nuevoRegistro = new RegistroEquipoPk("23", "C.C", "102543256", "23");
        
        RegistroEquipoDAO instance = regequipo.crearRegistroEquipo();
        instance.updatePk(viejoRegistro, nuevoRegistro, viejoRegistro, 
                nuevoRegistro, viejoRegistro, nuevoRegistro, viejoRegistro,
                nuevoRegistro, viejoRegistro);
        System.out.println("-------------------------------------------");
    }

    /**
     * Test of delete method, of class RegistroEquipoDAOImp.
     */
    @Test
    public void testDelete() {
        System.out.println("Delete");
        DAOFactory regequipo = new MySQLFactory();
        RegistroEquipoPk borrarLlaves = new RegistroEquipoPk("1", "C.C", "102487521", "1");
        RegistroEquipoDAO instance = regequipo.crearRegistroEquipo();
        instance.delete(borrarLlaves);
        System.out.println("------------------------------------------");
     
    }

    /**
     * Test of count method, of class RegistroEquipoDAOImp.
     */
    @Test
    public void testCount() {
        System.out.println("count");
        DAOFactory regequipo = new MySQLFactory();
        RegistroEquipoDAO instance = regequipo.crearRegistroEquipo();
        System.out.println(instance.count());
    }

}
