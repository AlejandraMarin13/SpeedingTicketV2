/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.sena.modelo.dao.mysql;

import edu.co.sena.modelo.dto.Equipo;
import edu.co.sena.modelo.dto.EquipoPk;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author PCOPEN
 */
public class EquipoDAOImp {

    private Connection conexion;

    private final String SQL_SELECT = "select * from " + getTableName() + "";

    private final String SQL_INSERT = "INSERT INTO " + getTableName() + "\n"
            + "ID_EQUIPO,\n"
            + "MARCA,\n"
            + "DESCRIPCION\n"
            + "VALUES\n"
            + "(?,?,?)";

    private final String SQL_UPDATE = "UPDATE " + getTableName() + "\n"
            + "SET\n"
            + "ID_EQUIPO = ?,\n"
            + "WHERE MARCA = ? "
            + "AND DESCRIPCION = ?";

    private final String SQL_UPDATEPK = "UPDATE " + getTableName() + "\n"
            + "SET\n"
            + "ID_EQUIPO = ?,\n"
            + "MARCA = ?"
            + "DESCRIPCION = ?"
            + "WHERE ID_EQUIPO= ?"
            + "AND MARCA = ? "
            + "AND DESCRIPCION = ?";

    private final String SQL_DELETE = "DELETE" + getTableName() + "\n"
            + " WHERE ID_EQUIPO = ?,\n"
            + "MARCA= ? "
            + "DESCRIPCION = ?;";
    private String SQL_SELECT_COUNT;

    public String getTableName() {
        return "EQUIPO";
    }

    public List<Equipo> findAll() {

        final boolean estaConectado = (conexion != null);
        Connection conec = null;
        PreparedStatement prstmnt = null;
        ResultSet result = null;
        List<Equipo> lt = new ArrayList<>();

        try {
            if (estaConectado) {
                conec = conexion;
            } else {
                conec = ResourceManager.getConeccion();
            }
            final String SQL = SQL_SELECT;

            JOptionPane.showMessageDialog(null, "Se ejecuto " + SQL);
            prstmnt = conec.prepareStatement(SQL);
            result = prstmnt.executeQuery();

            if (!result.wasNull()) {
                while (result.next()) {
                    Equipo equi = new Equipo();
                    equi.setIdEquipo(result.getString(1));
                    equi.setMarca(result.getString(2));
                    equi.setDescripcion(result.getNString(3));
                    lt.add(equi);
                }
            }

        } catch (Exception e) {
            System.out.println("Fallo el FindAll: " + e.getMessage());
        } finally {
            ResourceManager.closeResultSet(result);
            ResourceManager.closePreparedStatement(prstmnt);
            if (!estaConectado) {
                ResourceManager.closeConnection(conec);
            }
        }
        return lt;
    }

    public void insert(Equipo dto) {
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
            final String SQL = SQL_INSERT;
            int indice = 1;
            JOptionPane.showMessageDialog(null, "Se ejecuto " + SQL);
            prstmnt = conec.prepareStatement(SQL);
            prstmnt.setString(indice++, dto.getIdEquipo());
            prstmnt.setString(indice++, dto.getMarca());
            prstmnt.setString(indice++, dto.getDescripcion());

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

    public void update(EquipoPk llaveDto, Equipo dto) {
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
            final String SQL = SQL_UPDATE;
            int indice = 1;
            JOptionPane.showMessageDialog(null, "Se ejecuto " + SQL);
            prstmnt = conec.prepareStatement(SQL);

            prstmnt.setString(indice++, dto.getMarca());
            prstmnt.setString(indice++, dto.getDescripcion());
            prstmnt.setString(indice++, llaveDto.getIdEquipo());

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

    
    public void updatePk(EquipoPk llaveVieja, EquipoPk llaveNueva) {
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
            final String SQL = SQL_UPDATEPK;
            int indice = 1;
            JOptionPane.showMessageDialog(null, "Se ejecuto " + SQL);
            prstmnt = conec.prepareStatement(SQL);
            prstmnt.setString(indice++, llaveNueva.getIdEquipo());
            prstmnt.setString(indice++, llaveVieja.getIdEquipo());

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

    
    public void delete(EquipoPk dto) {
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
            final String SQL = SQL_DELETE;
            int indice = 1;
            JOptionPane.showMessageDialog(null, "Se esta ejecutando " + SQL);
            prstmnt = conec.prepareStatement(SQL);
            prstmnt.setString(indice++, dto.getIdEquipo());

            result = prstmnt.executeUpdate();

        } catch (Exception e) {
            System.out.println("Fallo el Delete: " + e.getMessage());
        } finally {
            ResourceManager.closePreparedStatement(prstmnt);
            if (!estaConectado) {
                ResourceManager.closeConnection(conec);
            }
        }
    }

  
    public List<Equipo> findByPK(EquipoPk dto) {
        final boolean estaConectado = (conexion != null);
        Connection conec = null;
        PreparedStatement prstmnt = null;
        ResultSet result = null;
        List<Equipo> eq = new ArrayList<>();

        try {
            if (estaConectado) {
                conec = conexion;
            } else {
                conec = ResourceManager.getConeccion();
            }
            final String SQL = SQL_SELECT + " where CODIGO_BARRAS = ?";

            JOptionPane.showMessageDialog(null, "Se ejecuto " + SQL);
            prstmnt = conec.prepareStatement(SQL);
            int index = 1;
            prstmnt.setString(index++, dto.getIdEquipo());
            result = prstmnt.executeQuery();

            if (!result.wasNull()) {
                while (result.next()) {
                    Equipo equi = new Equipo();
                    equi.setIdEquipo(result.getString(1));
                    equi.setMarca(result.getString(2));
                    equi.setDescripcion(result.getString(3));
                    eq.add(equi);
                }
            }

        } catch (Exception e) {
            System.out.println("Fallo el  FindByPK: " + e.getMessage());
        } finally {
            ResourceManager.closeResultSet(result);
            ResourceManager.closePreparedStatement(prstmnt);
            if (!estaConectado) {
                ResourceManager.closeConnection(conec);
            }
        }
        return eq;

    }

  
    public int count() {
        final boolean estaConectado = (conexion != null);
        Connection conec = null;
        PreparedStatement prstmnt = null;
        ResultSet result = null;
        Integer rowsCount = 0;

        try {
            if (estaConectado) {
                conec = conexion;
            } else {
                conec = ResourceManager.getConeccion();
            }
            final String SQL = SQL_SELECT_COUNT;

            JOptionPane.showMessageDialog(null, "Se ejecuta " + SQL);
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
            ResourceManager.closeResultSet(result);
            ResourceManager.closePreparedStatement(prstmnt);
            if (!estaConectado) {
                ResourceManager.closeConnection(conec);
            }
        }
        return rowsCount;
    }

}
