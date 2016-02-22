/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.sena.modelo.dao.mysql;

import com.sun.org.apache.bcel.internal.generic.AALOAD;
import edu.co.sena.dao.RegistroDAO;
import edu.co.sena.modelo.dto.Registro;
import edu.co.sena.modelo.dto.RegistroPk;

import edu.co.sena.modelo.factory.DAOFactory;
import edu.co.sena.modelo.factory.MySQLFactory;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author PCOPEN
 */
public class RegistroTest {

    public RegistroTest() {
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void FindAll() {
        System.out.println("findAll");
        DAOFactory cuent = new MySQLFactory();
        RegistroDAO instance = cuent.crearRegistro();
        List<Registro> result = instance.findAll();
        for (Registro registro : result) {
            System.out.println(registro.toString());
        }
        System.out.println("____________________");
    }

    @Test
    public void pruebaInsert() {
        DAOFactory regis = new MySQLFactory();
        Registro registroDTO = new Registro();
        Calendar calendario = Calendar.getInstance();
        Timestamp times = new Timestamp(calendario.getTime().getTime());
        registroDTO.setIdRegistro(21);
        registroDTO.setCuentaNumeroDocumento("103025005544");
        registroDTO.setCuentaTipoDocumento("CEDULA");
        registroDTO.setFechaHoraEntrada(times);
        registroDTO.setFechaHoraSalida(times);
        registroDTO.setRol("Aprendiz");
        registroDTO.setMotivoVisita("No se");

        

        RegistroDAO instance = regis.crearRegistro();
        instance.insert(registroDTO);
    }

    @Test
    public void pruebaUpdate() {
        DAOFactory regis = new MySQLFactory();
        RegistroPk registroViejo = new RegistroPk("21", "103025005544", "CEDULA");
        Registro registroNuevo = new Registro();
        Calendar calendario = Calendar.getInstance();
        Timestamp tmes = new Timestamp(calendario.getTime().getTime());
       registroNuevo.setFechaHoraEntrada(tmes);
       registroNuevo.setFechaHoraSalida(tmes);
       registroNuevo.setMotivoVisita("Tampoco se");
       registroNuevo.setRol("aprendiz");

        

        RegistroDAO instance = regis.crearRegistro();
        instance.update(registroViejo, registroNuevo);
    }

    @Test
    public void pruebaUpdatePK() {
        DAOFactory regis = new MySQLFactory();
        RegistroPk registroViejo = new RegistroPk("15","1016081489", "TI");
        RegistroPk registroNuevo = new RegistroPk("21","97080917191", "TI");

       
        RegistroDAO instance = regis.crearRegistro();
        instance.updatePk(registroViejo, registroNuevo, registroNuevo
                , registroViejo, registroNuevo, registroViejo);
    }

    @Test
    public void pruebaDelete() {
        DAOFactory regis = new MySQLFactory();
        RegistroPk registro = new RegistroPk("17","98050752453", "TI");

        
        RegistroDAO instance = regis.crearRegistro();
        instance.delete(registro);
    }

    @Test
    public void findByPK() {
        System.out.println("findAllPk ");
        DAOFactory f = new MySQLFactory();
        RegistroDAO instance = f.crearRegistro();
        RegistroPk llaves = new RegistroPk("12","TI", "98040962328");
        List<Registro> result = instance.findByPK(llaves);
        for (Registro registro : result) {
            System.out.println(registro.toString());
        }
        System.out.println("____________________");
    }
    
    @Test
    public void testCount() {
        DAOFactory regis = new MySQLFactory();
        RegistroDAO instance = regis.crearRegistro();

        System.out.println(instance.count());

    }
}
