/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.sena.modelo.dao.mysql;

import edu.co.sena.dao.CuentaDAO;
import edu.co.sena.modelo.dto.Cuenta;
import edu.co.sena.modelo.dto.CuentaPk;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author PCOPEN
 */
public class CuentaDAOImp implements CuentaDAO {

    private Connection conexion;

    private final  String SQL_SELECT ="SELECT * FROM " + getTableName() + "";

    private final String SQL_INSERT = "INSERT INTO " + getTableName() + "\n"
            + "(numero_documento,\n"
            + "tipo_documento,\n"
            + "primer_nombre,\n"
            + "segundo_nombre,\n"
            + "primer_apellido,\n"
            + "segundo_apellido,\n"
            + "perfil,\n"
            + "foto)\n"
            + "VALUES\n"
            + "(?,?,?,?,?,?,?,?)";

    private final String SQL_UPDATE = "UPDATE " + getTableName() + "\n"
            + "SET\n"
            + "primer_nombre = ?,\n"
            + "segundo_nombre = ?,\n"
            + "primer_apellido = ?,\n"
            + "segundo_apellido = ?,\n"
            + "perfil = ?,\n"
            + "foto = ?\n"
            + "WHERE numero_documento = ? AND tipo_documento = ?;";

    private final String SQL_UPDATEPK = "UPDATE " + getTableName() + "\n"
            + "SET\n"
            + "numero_documento = ?,\n"
            + "tipo_documento = ?\n"
            + "WHERE numero_documento = ? AND tipo_documento = ?;";

    private final String SQL_DELETE = "DELETE FROM " + getTableName() + "\n"
            + "WHERE numero_documento = ? AND tipo_documento = ?;";

    private final String SQL_SELECT_COUNT = "SELECT count(*) FROM " + getTableName() + " AS COUNT";

    

    @Override
    public List<Cuenta> findAll() {

// SE DECLARAN LAS VARIABLES
        final boolean estaConectado = (conexion != null);
        Connection conex = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Cuenta> lt = new ArrayList<>();

        try {
            // obtener el la conexion 

            if (estaConectado) {
                conex = conexion;
            } else {
                conex= ResourceManager.getConeccion();
            }

            // construct the SQL statement
            final String SQL = SQL_SELECT;

            System.out.println("Se Ha Ejecutado " + SQL);
            stmt = conex.prepareStatement(SQL);
            rs = stmt.executeQuery();

            if (!rs.wasNull()) {
                while (rs.next()) {
                    Cuenta c = new Cuenta();
                    c.setNumeroDocumento(rs.getString(1));
                    c.setTipoDocumento(rs.getString(2));                           
                    c.setPrimerNombre(rs.getString(3));
                    c.setSegundoNombre(rs.getString(4));
                    c.setPrimerApellido(rs.getString(5));
                    c.setSegundoApellido(rs.getString(6));
                    c.setPerfil(rs.getString(7));
                    c.setFoto(rs.getByte(8));
                   lt.add(c);
                }
              }

        } catch (Exception e) {
            System.out.println("error en el findAll " + e.getMessage());
        } finally {
            ResourceManager.close(rs);
            ResourceManager.closePreparedStatement(stmt);
            if (!estaConectado) {
                ResourceManager.closeConnection(conex);
            }
        }
        return lt;
    }


    @Override
    public void insert(Cuenta dto) {
        //  SE DECLARAN LAS VARIABLES 
        final boolean estaConectado = (conexion != null);
        Connection conec = null;
        PreparedStatement prstmnt = null;
        int result;
        try {
            if (estaConectado) {
                conec = conexion;
            } else {
                conec = ResourceManager.getConeccion();
            }

            // SE ASOCIA CON LA BASE DE DATOS
            final String SQL = SQL_INSERT;
            int indice = 1;
            System.out.println("Se ejecuto " + SQL);

            prstmnt = conec.prepareStatement(SQL);
            prstmnt.setString(indice++, dto.getNumeroDocumento());
            prstmnt.setString(indice++, dto.getTipoDocumento());
            prstmnt.setString(indice++, dto.getPrimerNombre());
            prstmnt.setString(indice++, dto.getSegundoNombre());
            prstmnt.setString(indice++, dto.getPrimerApellido());
            prstmnt.setString(indice++, dto.getSegundoApellido());
            prstmnt.setString(indice++, dto.getPerfil());
            prstmnt.setByte(indice++, dto.getFoto());

            result = prstmnt.executeUpdate();

        } catch (Exception e) {
            System.out.println("Fallo el Insert: " + e.getMessage());
        } finally {
            ResourceManager.closePreparedStatement(prstmnt);
            if (!estaConectado) {
                ResourceManager.closeConnection(conec);
            }
        }

    }

    @Override
    public void update(CuentaPk llaveDto, Cuenta dto) {

        //  SE DECLARAN LAS VARIABLES 
        final boolean estaConectado = (conexion != null);
        Connection conec = null;
        PreparedStatement prstmnt = null;
        int result;

        // AQUI SE CONSIGUE LA CONEXION
        try {
            if (estaConectado) {
                conec = conexion;
            } else {
                conec = ResourceManager.getConeccion();
            }

            // SE ASOCIA CON LA BASE DE DATOS 
            final String SQL = SQL_UPDATE;
            int indice = 1;
            System.out.println("Se ejecuto " + SQL);
            prstmnt = conec.prepareStatement(SQL);

            prstmnt.setString(indice++, dto.getPrimerNombre());
            prstmnt.setString(indice++, dto.getSegundoNombre());
            prstmnt.setString(indice++, dto.getPrimerApellido());
            prstmnt.setString(indice++, dto.getSegundoApellido());
            prstmnt.setString(indice++, dto.getPerfil());
            prstmnt.setByte(indice++, dto.getFoto());

            prstmnt.setString(indice++, llaveDto.getNumeroDocumento());
            prstmnt.setString(indice++, llaveDto.getTipoDocumento());

            result = prstmnt.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error dentro del Update: " + e.getMessage());
        } finally {
            ResourceManager.closePreparedStatement(prstmnt);
            if (!estaConectado) {
                ResourceManager.closeConnection(conec);
            }
        }

    }

    @Override
    public void updatePk(CuentaPk viejo, CuentaPk nuevo) {

        // SE DECLARAN LAS VARIABLES 
        final boolean estaConectado = (conexion != null);
        Connection conec = null;
        PreparedStatement prstmnt = null;
        int result;
// SE CONSIGUE LA CONEXION 
        try {
            if (estaConectado) {
                conec = conexion;
            } else {
                conec = ResourceManager.getConeccion();
            }
            final String SQL = SQL_UPDATEPK;
            int indice = 1;
//SE ASOCIA CON LA BASE DE DATOS 
            System.out.println("Se ejecuto " + SQL);
            prstmnt = conec.prepareStatement(SQL);
            prstmnt.setString(indice++, nuevo.getNumeroDocumento());
            prstmnt.setString(indice++, nuevo.getTipoDocumento());
            prstmnt.setString(indice++, viejo.getNumeroDocumento());
            prstmnt.setString(indice++, viejo.getTipoDocumento());

            result = prstmnt.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error dentro del UpdatePK: " + e.getMessage());
        } finally {
            ResourceManager.closePreparedStatement(prstmnt);
            if (!estaConectado) {
                ResourceManager.closeConnection(conec);
            }
        }

    }

    @Override
    public void delete(CuentaPk dto) {

        // SE DECLARAN LAS VARIABLES 
        final boolean estaConectado = (conexion != null);
        Connection conec = null;
        PreparedStatement prstmnt = null;
        int result;

        // SE CONSIGUE LA CONEXION 
        try {
            if (estaConectado) {
                conec = conexion;
            } else {
                conec = ResourceManager.getConeccion();
            }
            // SE ASOCIA CON LA BASE DE DATOS
            final String SQL = SQL_DELETE;
            int indice = 1;
            System.out.println("Se ejecuto " + SQL);
            prstmnt = conec.prepareStatement(SQL);
            prstmnt.setString(indice++, dto.getNumeroDocumento());
            prstmnt.setString(indice++, dto.getTipoDocumento());

            result = prstmnt.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error dentro del Delete: " + e.getMessage());
        } finally {
            ResourceManager.closePreparedStatement(prstmnt);
            if (!estaConectado) {
                ResourceManager.closeConnection(conec);
            }
        }

    }

    @Override
    public List<Cuenta> findByPK(CuentaPk dto) {

        //SE DECLARAN LAS VARIABLES HA USAR
        final boolean estaConectado = (conexion != null);
        Connection conec = null;
        PreparedStatement prstmnt = null;
        ResultSet result = null;
        List<Cuenta> lich = new ArrayList<>();

        // SE REALIZA LA CONEXION 
        try {
            if (estaConectado) {
                conec = conexion;
            } else {
                conec = ResourceManager.getConeccion();
            }

            // GUIA DE LAS TABLAS DE BASE DE DATOS
            final String SQL = SQL_SELECT + " where numero_documento = ? AND tipo_documento = ?";

            System.out.println("Se ejecuto " + SQL);
            prstmnt = conec.prepareStatement(SQL);
            int index = 1;
            prstmnt.setString(index++, dto.getNumeroDocumento());
            prstmnt.setString(index++, dto.getTipoDocumento());
            result = prstmnt.executeQuery();

            if (!result.wasNull()) {
                while (result.next()) {
                    Cuenta cuen = new Cuenta();
                    cuen.setNumeroDocumento(result.getString(1));
                    cuen.setTipoDocumento(result.getString(2));
                    cuen.setPrimerNombre(result.getString(3));
                    cuen.setSegundoNombre(result.getNString(4));
                    cuen.setPrimerApellido(result.getNString(5));
                    cuen.setSegundoApellido(result.getString(6));
                    cuen.setPerfil(result.getString(7));
                    cuen.setFoto(result.getByte(8));

                    lich.add(cuen);

                }
            }

        } catch (Exception e) {
            System.out.println("Error dentro del FindByPK: " + e.getMessage());
        } finally {
            ResourceManager.close(result);
            ResourceManager.closePreparedStatement(prstmnt);
            if (!estaConectado) {
                ResourceManager.closeConnection(conec);
            }
        }
        return lich;
    }

    @Override
    public int count() {

        //SE DETERMINA LA VARIABLE
        final boolean estaConectado = (conexion != null);
        Connection conec = null;
        PreparedStatement prstmnt = null;
        ResultSet result = null;
        Integer rowsCount = 0;

        //SE CONSIGUE LA CONEXION
        try {
            if (estaConectado) {
                conec = conexion;
            } else {
                conec = ResourceManager.getConeccion();
            }

            //
            final String SQL = SQL_SELECT_COUNT;

            System.out.println("Se ejecuto " + SQL);
            prstmnt = conec.prepareStatement(SQL);
            result = prstmnt.executeQuery();
            if (!result.wasNull()) {
                while (result.next()) {
                    rowsCount = result.getInt(1);
                }
            }

        } catch (Exception e) {
            System.out.println("Error dentro del SelectCount: " + e.getMessage());
        } finally {
            ResourceManager.close(result);
            ResourceManager.closePreparedStatement(prstmnt);
            if (!estaConectado) {
                ResourceManager.closeConnection(conec);
            }
        }
        return rowsCount;

    }
   public String getTableName() {
        return "proyecto.cuenta";
    }
}
