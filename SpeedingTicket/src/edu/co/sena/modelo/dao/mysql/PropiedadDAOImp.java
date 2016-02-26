/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.sena.modelo.dao.mysql;

import edu.co.sena.dao.PropiedadDAO;
import edu.co.sena.modelo.dto.Propiedad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PCOPEN
 */
public class PropiedadDAOImp implements PropiedadDAO {

    private Connection conexion;

    private final String SQL_SELECT = "SELECT * FROM " + getTableName() + "";
    private final String SQL_INSERT = "INSERT INTO" + getTableName() + "\n"
            + "(Equipo_Id_Equipo,\n"
            + "Cuenta_Num_Documento,\n"
            + "Cuenta_Tipo_Documento)\n"
            + "VALUES\n"
            + "(?,\n"
            + "?,\n"
            + "?);";
    private final String SQL_UPDATE = "UPDATE" + getTableName() + "\n"
            + "SET\n"
            + "Equipo_Id_Equipo = ?\n"
            + "Cuenta_Tipo_Documento = ?,\n"
            + "Cuenta_Num_Documento = ?,\n"
            + "WHERE Cuenta_Tipo_Documento = ? "
            + "AND Equipo_Id_Equipo = ?;"
            + "AND Cuenta_Num_Documento = ? ";

    private final String SQL_DELETE = "DELETE FROM" + getTableName() + "\n"
            + "WHERE Equipo_Codigo_Barras = ?"
            + "AND Cuenta_Num_Documento = ? "
            + "AND Cuenta_Tipo_Documento = ?";
    private final String SQL_SELECT_COUNT = "SELECT count(*) FROM " + getTableName() + " AS COUNT";
    private final String SQL_SELECTPK = "SELECT * FROM " + getTableName()
            + " where EQUIPO_ID_EQUIPO = ?"
            + " and CUENTA_NUMERO_DOCUMENTO = ?"
            + " and CUENTA_TIPO_DOCUMENTO = ?;";

    public String getTableName() {
        return "proyecto.Propiedad";
    }

    public List<Propiedad> findAll() {

        // DETEMINA LA VARIABLE
        final boolean estaConectado = (conexion != null);
        Connection conex = null;
        PreparedStatement prstmt = null;
        ResultSet rs = null;
        List<Propiedad> lt = new ArrayList<>();

        //SE REALIZA LA CONEXION
        try {
            if (estaConectado) {
                conex = conexion;
            } else {
                conex = ResourceManager.getConeccion();
            }

            //SE ASOCIA CON LA BASE DE DATOS
            final String SQL = SQL_SELECT;

            System.out.println("Se ejecuto " + SQL);
            prstmt = conex.prepareStatement(SQL);
            rs = prstmt.executeQuery();

            if (!rs.wasNull()) {
                while (rs.next()) {
                    Propiedad propiedad = new Propiedad();
                    propiedad.setEquipoIdEquipo(rs.getString(1));
                    propiedad.setCuentaTipoDocumento(rs.getString(2));
                    propiedad.setCuentaNumeroDocumento(rs.getString(3));

                    lt.add(propiedad);
                }
            }

        } catch (Exception e) {
            System.out.println("Error dentro del FindAll: " + e.getMessage());
        } finally {
            ResourceManager.close(rs);
            ResourceManager.closePreparedStatement(prstmt);
            if (!estaConectado) {
                ResourceManager.closeConnection(conex);
            }
        }
        return lt;
    }

    @Override
    public void insert(Propiedad dto) {

        // SE ASIGNA UNA VARIABLE
        final boolean estaConectado = (conexion != null);
        Connection conex = null;
        PreparedStatement prstm = null;
        int rs;

        //REALIZACION DE LA CONEXION
        try {
            if (estaConectado) {
                conex = conexion;
            } else {
                conex = ResourceManager.getConeccion();
            }

            // SE ASOCIA A LA BASE DE DATOS
            final String SQL = SQL_INSERT;
            int indice = 1;
            System.out.println("Se ejecuto " + SQL);
            prstm = conex.prepareStatement(SQL);
            prstm.setString(indice++, dto.getEquipoIdEquipo());
            prstm.setString(indice++, dto.getCuentaTipoDocumento());
            prstm.setString(indice++, dto.getCuentaNumeroDocumento());

            rs = prstm.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error dentro del Insert: " + e.getMessage());
        } finally {
            ResourceManager.closePreparedStatement(prstm);
            if (!estaConectado) {
                ResourceManager.closeConnection(conex);
            }
        }
    }

    @Override
    public void delete(Propiedad dto) {
        // declaracion de variables
        final boolean estaConectado = (conexion != null);
        Connection conn = null;
        PreparedStatement stmt = null;
        int rs;

        try {
            // obtener el la conexion 

            if (estaConectado) {
                conn = conexion;
            } else {
                conn = ResourceManager.getConeccion();
            }

            // construct the SQL statement
            final String SQL = SQL_DELETE;
            int indice = 1;
            System.out.println("se ejecuto " + SQL);
            stmt = conn.prepareStatement(SQL);
            stmt.setString(indice++, dto.getEquipoIdEquipo());
            stmt.setString(indice++, dto.getCuentaTipoDocumento());
            stmt.setString(indice++, dto.getCuentaNumeroDocumento());

            rs = stmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("error en el findAll" + e.getMessage());
        } finally {
            ResourceManager.close((ResultSet) stmt);
            if (!estaConectado) {
                ResourceManager.closeConnection(conn);
            }
        }

    }

    @Override
    public List<Propiedad> findBy(Propiedad dto) {
        final boolean estaConectado = (conexion != null);
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Propiedad> lt = new ArrayList<>();

        try {
            // obtener el la conexion 

            if (estaConectado) {
                conn = conexion;
            } else {
                conn = ResourceManager.getConeccion();
            }

            // construct the SQL statement
            final String SQL = SQL_SELECTPK;

            System.out.println("Se Ha Ejecutado " + SQL);
            stmt = conn.prepareStatement(SQL);

            int indice = 1;
            stmt.setString(indice++, dto.getEquipoIdEquipo());
            // estos van aca
            stmt.setString(indice++, dto.getCuentaNumeroDocumento());
            stmt.setString(indice++, dto.getCuentaTipoDocumento());
            // _______
            rs = stmt.executeQuery();
            if (!rs.wasNull()) {
                while (rs.next()) {
                    Propiedad p = new Propiedad();
                    p.setEquipoIdEquipo(rs.getString(1));
                    p.setCuentaNumeroDocumento(rs.getString(2));
                    p.setCuentaTipoDocumento(rs.getString(3));
                    lt.add(p);

                }
            }

        } catch (Exception e) {
            System.out.println("error en el findAll " + e.getMessage());
        } finally {
            ResourceManager.close(rs);
            ResourceManager.close((ResultSet) stmt);
            if (!estaConectado) {
                ResourceManager.closeConnection(conn);
            }
        }
        return lt;
    }

    @Override
    public int count() {
        final boolean estaConectado = (conexion != null);
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int r = 0;
        try {
            // obtener el la conexion 

            if (estaConectado) {
                conn = conexion;
            } else {
                conn = ResourceManager.getConeccion();
            }

            // construct the SQL statement
            final String SQL = SQL_SELECT_COUNT;
            stmt = conn.prepareStatement(SQL);
            System.out.println("se ejecuto " + SQL);
            rs = stmt.executeQuery();
            rs.next();
            r = rs.getInt(1);
        } catch (Exception e) {
            System.out.println("error en el findAll" + e.getMessage());
        } finally {
            ResourceManager.close((ResultSet) stmt);
            if (!estaConectado) {
                ResourceManager.closeConnection(conn);
            }
        }
        return r;
    }

}
