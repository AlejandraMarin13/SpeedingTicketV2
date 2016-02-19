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
import edu.co.sena.modelo.factory.DAOAbstractFactory;
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
        DAOFactory factoryDAO = MySQLFactory.getDAOFactory(DAOAbstractFactory.MYSQL_FACTORY);
        RegistroDAO instance = factoryDAO.creaRegistro();

        List<Registro> resu = instance.findAll();
        for (Registro registro : resu) {
            System.out.println(registro.toString());
        }
    }

    @Test
    public void pruebaInsert() {
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

        DAOFactory regis = MySQLFactory.getDAOFactory(DAOAbstractFactory.MYSQL_FACTORY);

        RegistroDAO instance = regis.creaRegistro();
        instance.insert(registroDTO);
    }

    @Test
    public void pruebaUpdate() {
        RegistroPk registroViejo = new RegistroPk("21", "103025005544", "CEDULA");
        Registro registroNuevo = new Registro();
        Calendar calendario = Calendar.getInstance();
        Timestamp tmes = new Timestamp(calendario.getTime().getTime());
       registroNuevo.setFechaHoraEntrada(tmes);
       registroNuevo.setFechaHoraSalida(tmes);
       registroNuevo.setMotivoVisita("Tampoco se");
       registroNuevo.setRol("aprendiz");

        DAOFactory regis = MySQLFactory.getDAOFactory(DAOAbstractFactory.MYSQL_FACTORY);

        RegistroDAO instance = regis.creaRegistro();
        instance.update(registroViejo, registroNuevo);
    }
//
//    @Test
//    public void pruebaUpdatePK() {
//        CuentaPk cuentaVieja = new CuentaPk("103025005544", "CEDULA");
//        CuentaPk cuentaNueva = new CuentaPk("545645", "CC");
//
//        DAOFactory cuent = MySQLFactory.getDAOFactory(DAOAbstractFactory.MYSQL_FACTORY);
//        CuentaDAO instance = cuent.creaCuenta();
//        instance.updatePk(cuentaVieja, cuentaNueva);
//    }
//
//    @Test
//    public void pruebaDelete() {
//        CuentaPk cuenta = new CuentaPk("545645", "CC");
//
//        DAOFactory cuent = MySQLFactory.getDAOFactory(DAOAbstractFactory.MYSQL_FACTORY);
//        CuentaDAO instance = cuent.creaCuenta();
//        instance.delete(cuenta);
//    }
//
//    @Test
//    public void findByPK() {
//        DAOFactory cuent = MySQLFactory.getDAOFactory(DAOAbstractFactory.MYSQL_FACTORY);
//        CuentaDAO instance = cuent.creaCuenta();
//        CuentaPk cuenta = new CuentaPk("1016081489", "TI");
//
//        List<Cuenta> resultado = instance.findByPK(cuenta);
//
//        for (Cuenta resultado1 : resultado) {
//            System.out.println(resultado1.toString());
//
//        }
//    }
//
//    @Test
//    public void testCount() {
//        DAOFactory cuent = MySQLFactory.getDAOFactory(DAOAbstractFactory.MYSQL_FACTORY);
//        CuentaDAO instance = cuent.creaCuenta();
//
//        System.out.println(instance.count());
//
//    }
}
