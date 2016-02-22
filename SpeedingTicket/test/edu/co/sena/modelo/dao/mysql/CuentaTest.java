/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.sena.modelo.dao.mysql;

import edu.co.sena.dao.CuentaDAO;
import edu.co.sena.modelo.dto.Cuenta;
import edu.co.sena.modelo.dto.CuentaPk;

import edu.co.sena.modelo.factory.DAOFactory;
import edu.co.sena.modelo.factory.MySQLFactory;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author PCOPEN
 */
public class CuentaTest {

    public CuentaTest() {
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
        CuentaDAO instance = cuent.crearCuenta();
        List<Cuenta> result = instance.findAll();
        for (Cuenta cuenta : result) {
            System.out.println(cuenta.toString());
        }
        System.out.println("____________________");
    }
    

    @Test
    public void pruebaInsert() {
        DAOFactory cuent = new MySQLFactory();
        Byte foto = new Byte("2");
        Cuenta cuentaDTO = new Cuenta();
        cuentaDTO.setNumeroDocumento("103025005544");
        cuentaDTO.setTipoDocumento("CEDULA");
        cuentaDTO.setPrimerNombre("ALEJANDRA");
        cuentaDTO.setSegundoNombre("MARIA");
        cuentaDTO.setPrimerApellido("MARIN");
        cuentaDTO.setSegundoApellido("LOPEZ");
        cuentaDTO.setPerfil("APRENDIZ");
        cuentaDTO.setFoto(foto);

         

        CuentaDAO instance = cuent.crearCuenta();
        instance.insert(cuentaDTO);
    }

    @Test
    public void pruebaUpdate() {
        DAOFactory cuent = new MySQLFactory();
        Byte foto = new Byte("3");
        CuentaPk cuentaVieja = new CuentaPk("103025005544", "CEDULA");
        Cuenta cuentaNueva = new Cuenta();
        cuentaNueva.setPrimerNombre("JESSICA");
        cuentaNueva.setSegundoNombre("ALEJITA");
        cuentaNueva.setPrimerApellido("MARIN");
        cuentaNueva.setSegundoApellido("HERRERA");
        cuentaNueva.setPerfil("APRENDIZ");
        cuentaNueva.setFoto(foto);

        

        CuentaDAO instance = cuent.crearCuenta();
        instance.update(cuentaVieja, cuentaNueva);
    }

    @Test
    public void pruebaUpdatePK() {
        DAOFactory cuent = new MySQLFactory();
        CuentaPk cuentaVieja = new CuentaPk("103025005544", "CEDULA");
        CuentaPk cuentaNueva = new CuentaPk("545645", "CC");

        
        CuentaDAO instance = cuent.crearCuenta();
        instance.updatePk(cuentaVieja, cuentaNueva);
    }

    @Test
    public void pruebaDelete() {
        DAOFactory cuent = new MySQLFactory();
        CuentaPk cuenta = new CuentaPk("545645", "CC");

        
        CuentaDAO instance = cuent.crearCuenta();
        instance.delete(cuenta);
    }

    @Test
    public void findByPK() {
        DAOFactory cuent = new MySQLFactory();
        CuentaDAO instance = cuent.crearCuenta();
        CuentaPk cuenta = new CuentaPk("1016081489", "TI");

        List<Cuenta> resultado = instance.findByPK(cuenta);

        for (Cuenta resultado1 : resultado) {
            System.out.println(resultado1.toString());

        }
    }

    @Test
    public void testCount() {
        DAOFactory cuent = new MySQLFactory();
        CuentaDAO instance = cuent.crearCuenta();

        System.out.println(instance.count());

    }
}

