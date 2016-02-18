/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.sena.modelo.dao.mysql;

import edu.co.sena.dao.EquipoDAO;
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
public class EquipoDAOImp implements EquipoDAO{
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

   
    
    @Override
    public String getTableName() {
        return "proyecto.Equipo";
    }

    @Override
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

    @Override
    public void insert(Equipo equipoDTO) {
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
            prstmnt.setString(indice++, equipoDTO.getIdEquipo());
            prstmnt.setString(indice++, equipoDTO.getMarca());
            prstmnt.setString(indice++, equipoDTO.getDescripcion());

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
            System.out.println("Fallo el Update: " + e.getMessage());
        } finally {
            ResourceManager.closePreparedStatement(prstmnt);
            if (!estaConectado) {
                ResourceManager.closeConnection(conec);
            }
        }


    }

    @Override
    public void update(Equipo equipoDTO) {
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
            prstmnt.setString(indice++, equipoDTO.getIdEquipo());

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
 
 

    @Override
    public void updatePK(EquipoPk nuevo, EquipoPk viejo) {
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
            prstmnt.setString(indice++, nuevo.getIdEquipo());
            prstmnt.setString(indice++, viejo.getIdEquipo());

            result = prstmnt.executeUpdate();

        } catch (Exception e) {
            System.out.println("Fallo el  UpdatePK: " + e.getMessage());
        } finally {
            ResourceManager.closePreparedStatement(prstmnt);
            if (!estaConectado) {
                ResourceManager.closeConnection(conec);
            }
        }

    }

    }   


   