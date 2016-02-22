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

/**
 *
 * @author PCOPEN
 */
public class EquipoDAOImp implements EquipoDAO {

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

        //DETERMINAMOS LAS VARIABLES
        final boolean estaConectado = (conexion != null);
        Connection conec = null;
        PreparedStatement prstmnt = null;
        ResultSet result = null;
        List<Equipo> lt = new ArrayList<>();

        //SE REALIZA LA CONEXION
        try {
            if (estaConectado) {
                conec = conexion;
            } else {
                conec = ResourceManager.getConeccion();
            }

            //SE MUESTRAN LOS ATRIBUTOS DE LA CLASE
            final String SQL = SQL_SELECT;

            System.out.println("se ejecuto" + SQL);
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
            ResourceManager.close(result);
            ResourceManager.closePreparedStatement(prstmnt);
            if (!estaConectado) {
                ResourceManager.closeConnection(conec);
            }
        }
        return lt;
    }

    @Override
    public void insert(Equipo equipoDTO) {

        //SE DECLARAN LAS VARIABLES
        final boolean estaConectado = (conexion != null);
        Connection conec = null;
        PreparedStatement prstmnt = null;
        int result;

        //REALIZACION DE LA CONEXION
        try {

            if (estaConectado) {
                conec = conexion;
            } else {
                conec = ResourceManager.getConeccion();
            }

            // SE ASOCIA CON LA BASE DE DATOS
            final String SQL = SQL_INSERT;
            int indice = 1;
            System.out.println("se ejecuto" + SQL);
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

        // DETERMINACION DE LA VARIBLE
        final boolean estaConectado = (conexion != null);
        Connection conec = null;
        PreparedStatement prstmnt = null;
        int result;

        //REALIZACION DE LA CONEXION
        try {
            if (estaConectado) {
                conec = conexion;
            } else {
                conec = ResourceManager.getConeccion();
            }

            // SE ASOCIA CON LA BASE DE DATOS
            final String SQL = SQL_UPDATE;
            int indice = 1;
            System.out.println("se ejecuto" + SQL);
            prstmnt = conec.prepareStatement(SQL);

            prstmnt.setString(indice++, dto.getMarca());
            prstmnt.setString(indice++, dto.getDescripcion());
            prstmnt.setString(indice++, dto.getIdEquipo());

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

        //SE DETERMINA LA VARIABLE
        final boolean estaConectado = (conexion != null);
        Connection conec = null;
        PreparedStatement prstmnt = null;
        int result;

        //PROCEDE HACER LA CONEXION
        try {
            if (estaConectado) {
                conec = conexion;
            } else {
                conec = ResourceManager.getConeccion();
            }

            //SE ASOCIA CON LA BASE DE DATOS
            final String SQL = SQL_DELETE;
            int indice = 1;
            System.out.println("se ejecuto" + SQL);
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

        //SE DETERMINA LA VARIABLE
        final boolean estaConectado = (conexion != null);
        Connection conec = null;
        PreparedStatement prstmnt = null;
        int result;

        //SE REALIZA LA CONEXION
        try {
            if (estaConectado) {
                conec = conexion;
            } else {
                conec = ResourceManager.getConeccion();
            }

            //SE ASOCIA CON LÑA BASE DE DATOS
            final String SQL = SQL_UPDATEPK;
            int indice = 1;
            System.out.println("se ejecuto" + SQL);
            prstmnt = conec.prepareStatement(SQL);
            prstmnt.setString(indice++, nuevo.getRegistroIdEquipo());
            prstmnt.setInt(indice++, viejo.getRegistroidRegistro());

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

    @Override
    public List<Equipo> findByPK(EquipoPk dto) {

        final boolean estaConectado = (conexion != null);
        Connection conex = null;
        PreparedStatement prstmnt = null;
        ResultSet result = null;
        List<Equipo> lich = new ArrayList<>();

        // SE REALIZA LA CONEXION 
        try {
            if (estaConectado) {
                conex = conexion;
            } else {
                conex = ResourceManager.getConeccion();
            }

            // GUIA DE LAS TABLAS DE BASE DE DATOS
            final String SQL = SQL_SELECT + " where numero_documento = ? AND tipo_documento = ?";

            System.out.println("Se ejecuto " + SQL);
            prstmnt = conex.prepareStatement(SQL);
            int index = 1;
            prstmnt.setString(index++, dto.getRegistroIdEquipo());

            result = prstmnt.executeQuery();

            if (!result.wasNull()) {
                while (result.next()) {
                    Equipo equipo = new Equipo();
                    equipo.setDescripcion(result.getString(1));
                    equipo.setIdEquipo(result.getString(2));
                    equipo.setMarca(result.getString(3));

                    lich.add(equipo);

                }
            }

        } catch (Exception e) {
            System.out.println("Error dentro del FindByPK: " + e.getMessage());
        } finally {
            ResourceManager.close(result);
            ResourceManager.closePreparedStatement(prstmnt);
            if (!estaConectado) {
                ResourceManager.closeConnection(conex);
            }
        }
        return lich;
    }

    @Override
    public void delete(EquipoPk dto) {

        final boolean estaConectado = (conexion != null);
        Connection conex = null;
        PreparedStatement prstmnt = null;
        int result;

        // SE CONSIGUE LA CONEXION 
        try {
            if (estaConectado) {
                conex = conexion;
            } else {
                conex = ResourceManager.getConeccion();
            }
            // SE ASOCIA CON LA BASE DE DATOS
            final String SQL = SQL_DELETE;
            int indice = 1;
            System.out.println("Se ejecuto " + SQL);
            prstmnt = conex.prepareStatement(SQL);
            prstmnt.setString(indice++, dto.getRegistroIdEquipo());

            result = prstmnt.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error dentro del Delete: " + e.getMessage());
        } finally {
            ResourceManager.closePreparedStatement(prstmnt);
            if (!estaConectado) {
                ResourceManager.closeConnection(conex);
            }
        }

    }

    @Override
    public int count() {
       
        final boolean estaConectado = (conexion != null);
        Connection conex = null;
        PreparedStatement prstmnt = null;
        ResultSet result = null;
        Integer rowsCount = 0;

        //SE CONSIGUE LA CONEXION
        try {
            if (estaConectado) {
                conex = conexion;
            } else {
                conex = ResourceManager.getConeccion();
            }

            //
            final String SQL = SQL_SELECT_COUNT;

            System.out.println("Se ejecuto " + SQL);
            prstmnt = conex.prepareStatement(SQL);
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
                ResourceManager.closeConnection(conex);
            }
        }
        return rowsCount;
        
        
        
        
    }

}


        
    
