/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.sena.modelo.dao.mysql;

import edu.co.sena.dao.PropiedadDAO;
import edu.co.sena.modelo.dto.Propiedad;
import edu.co.sena.modelo.dto.PropiedadPk;
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

    private final String SQL_UPDATEPK = "UPDATE" + getTableName() + "\n"
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

    public String getTableName() {
        return "PRO.PROPIETARIO";
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
            ResourceManager.closeResultSet(rs);
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
    public void update(Propiedad dto) {

        //SE DETERMINA UNA VARIABLE
        final boolean estaConectado = (conexion != null);
        Connection conex = null;
        PreparedStatement prstm = null;
        int rs;

        //REALIZA LA CONEXION
        try {
            if (estaConectado) {
                conex = conexion;
            } else {
                conex = ResourceManager.getConeccion();
            }

            //SE BASA LA INFORMACION CON LA BASE DE DATOS
            final String SQL = SQL_UPDATE;
            int indice = 1;
            System.out.println("Se ejecuto " + SQL);
            prstm = conex.prepareStatement(SQL);

            prstm.setString(indice++, dto.getEquipoIdEquipo());
            prstm.setString(indice++, dto.getCuentaTipoDocumento());
            prstm.setString(indice++, dto.getCuentaNumeroDocumento());

            rs = prstm.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error dentro del Update: " + e.getMessage());
        } finally {
            ResourceManager.closePreparedStatement(prstm);
            if (!estaConectado) {
                ResourceManager.closeConnection(conex);
            }
        }
    }

    @Override
    public void updatePk(PropiedadPk llaveVieja, PropiedadPk llaveNueva) {

        //SDE DETERMINA LA VARIABLE
        final boolean estaConectado = (conexion != null);
        Connection conex = null;
        PreparedStatement prstmt = null;
        int rs;
// SE REALIZA LA CONEXION
        try {
            if (estaConectado) {
                conex = conexion;
            } else {
                conex = ResourceManager.getConeccion();
            }
            final String SQL = SQL_UPDATEPK;
            int indice = 1;
            System.out.println("Se ejecuto " + SQL);
            prstmt = conex.prepareStatement(SQL);
            prstmt.setString(indice++, llaveVieja.getEquipoIdEquipo());
            prstmt.setString(indice++, llaveNueva.getCuentaTipoDocumento());
            prstmt.setString(indice++, llaveNueva.getCuentaNumeroDocumento());

            rs = prstmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error dentro del UpdatePK: " + e.getMessage());
        } finally {
            ResourceManager.closePreparedStatement(prstmt);
            if (!estaConectado) {
                ResourceManager.closeConnection(conex);
            }
        }
    }

    @Override
    public void delete(Propiedad dto) {
        final boolean estaConectado = (conexion != null);
        Connection conex = null;
        PreparedStatement prstm = null;
        int rs;

        //REALIZA LA CONEXION
        try {
            if (estaConectado) {
                conex = conexion;
            } else {
                conex = ResourceManager.getConeccion();
            }

            //SE BASA LA INFORMACION CON LA BASE DE DATOS
            final String SQL = SQL_DELETE;
            int indice = 1;
            System.out.println("Se ejecuto " + SQL);
            prstm = conex.prepareStatement(SQL);

            prstm.setString(indice++, dto.getEquipoIdEquipo());
            prstm.setString(indice++, dto.getCuentaTipoDocumento());
            prstm.setString(indice++, dto.getCuentaNumeroDocumento());

            rs = prstm.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error dentro del Update: " + e.getMessage());
        } finally {
            ResourceManager.closePreparedStatement(prstm);
            if (!estaConectado) {
                ResourceManager.closeConnection(conex);
            }
        }
    }

    @Override
    public int count() {
        final boolean estaConectado = (conexion != null);
        Connection conex = null;
        PreparedStatement prstmt = null;
        ResultSet rs = null;
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
            rs = prstmt.executeQuery(SQL_SELECT_COUNT);
            int i = 0;
            if (!rs.wasNull()) {
                while (rs.next()) {
                    Propiedad propiedad = new Propiedad();
                   
                    i++;
                }
            }
        } catch (Exception e) {
            System.out.println("Error dentro del FindAll: " + e.getMessage());
        } finally {
            ResourceManager.closeResultSet(rs);
            ResourceManager.closePreparedStatement(prstmt);
            if (!estaConectado) {
                ResourceManager.closeConnection(conex);
            }
        }
        return 1;
    }

}
