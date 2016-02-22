/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.sena.modelo.dao.mysql;

import edu.co.sena.dao.RegistroEquipoDAO;
import edu.co.sena.modelo.dto.RegistroEquipo;
import edu.co.sena.modelo.dto.RegistroEquipoPk;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PCOPEN
 */
public class RegistroEquipoDAOImp implements RegistroEquipoDAO {

    private Connection conexion;

    private final String SQL_SELECT = "select * from " + getTableName() + "";

    private final String SQL_INSERT = "INSERT INTO " + getTableName() + "\n"
            + "REGISTRO_ID_REGISTRO,\n"
            + "PROPIEDAD_EQUIPO_ID_EQUIPO,\n"
            + "PROPIETARIO_CUENTA_NUMERO_DOCUMENTO\n"
            + "PROPIETARIO_CUENTA_TIPO_DOCUMENTO\n"
            + "VALUES\n"
            + "(?,?,?,?,?,?,?,?";

    private final String SQL_UPDATE = "UPDATE " + getTableName() + "\n"
            + "SET\n"
            + "REGISTRO_ID_REGISTRO= ?,\n"
            + "AND PROPIEDAD_EQUIPO_ID_EQUIPO= ? "
            + "AND AND PROPIEDAD_EQUIPO_ID_EQUIPO= ?? ? "
            + "WHERE PROPIEDAD_CUENTA_TIPO_DOCUMENTO= ?";
    
     private final String SQL_DELETE = "DELETE FROM " + getTableName() + "\n"
            + "WHERE id_registro = ?"
             + " AND PROPIEDAD_EQUIPO_ID_EQUIPO= ? "
             + "AND PROPIEDAD_EQUIPO_ID_EQUIPO= ?"
             + "AND AND PROPIEDAD_EQUIPO_ID_EQUIPO=  ? ";

    private final String SQL_UPDATEPK = "UPDATE" + getTableName() + "\n"
            + "SET\n"
            + "REGISTRO_ID_REGISTRO= ?"
            + "PROPIEDAD_EQUIPO_ID_EQUIPO = ? "
            + "PROPIEDAD CUENTA_NUMERO_DOCUMENTO = ?"
            + "WHERE REGISTRO_ID_RESGISTRO = ?"
            + "AND PROPIEDAD_EQUIPO_ID_EQUIPO = ? "
            + "AND PROPIEDAD_CUENTAIPO_DOCUMENTO_= ?";
    private final String SQL_SELECT_COUNT = "SELECT count(*) FROM " + getTableName() + "";

    public String getTableName() {
        return "proyecto.RegistroEquipo";
    }

    @Override
    public List<RegistroEquipo> findAll() {

        final boolean estaConectado = (conexion != null);
        Connection conec = null;
        PreparedStatement prstmnt = null;
        ResultSet result = null;
        List<RegistroEquipo> registritosEquipos = new ArrayList<>();

        try {

            if (estaConectado) {
                conec = conexion;
            } else {
                conec = ResourceManager.getConeccion();
            }

            final String SQL = SQL_SELECT;

            System.out.println("se ejecuto " + SQL);
            prstmnt = conec.prepareStatement(SQL);
            result = prstmnt.executeQuery();

            if (!result.wasNull()) {
                while (result.next()) {
                    RegistroEquipo registroEquipo = new RegistroEquipo();
                    registroEquipo.setRegistroIdRegistro(result.getInt(1));
                    registroEquipo.setPropietarioEquipoIdEquipo(result.getNString(2));
                    registroEquipo.setPropietarioCuentaNumeroDocumento(result.getString(3));
                    registroEquipo.setPropietariocuentaTipoDocumento(result.getString(4));

                    registritosEquipos.add(registroEquipo);
                }
            }

        } catch (Exception _e) {
            System.out.println("error en el findAll");
        } finally {
            ResourceManager.close(result);;
            ResourceManager.closePreparedStatement(prstmnt);
            if (!estaConectado) {
                ResourceManager.closeConnection(conec);
            }
        }
        return registritosEquipos;
    }

    @Override
    public void insert(RegistroEquipo registroEquipoDTO) {
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
            System.out.println("se ejecuto" + SQL);
            prstmnt = conec.prepareStatement(SQL);
            prstmnt.setString(indice++, registroEquipoDTO.getPropietarioEquipoIdEquipo());
            prstmnt.setString(indice++, registroEquipoDTO.getPropietarioCuentaNumeroDocumento());
            prstmnt.setString(indice++, registroEquipoDTO.getPropietariocuentaTipoDocumento());
            prstmnt.setInt(indice++, registroEquipoDTO.getRegistroIdRegistro());

            result = prstmnt.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error dentro del Insert: " + e.getMessage());
        } finally {
            ResourceManager.closePreparedStatement(prstmnt);
            if (!estaConectado) {
                ResourceManager.closeConnection(conec);
            }
        }

    }

    @Override
    public void update(RegistroEquipo registroEquipoDTO) {
        final boolean estaConectado = (conexion != null);
        Connection conn = null;
        PreparedStatement prstmnt = null;
        int result;

        try {

            if (estaConectado) {
                conn = conexion;
            } else {
                conn = ResourceManager.getConeccion();
            }

            // construct the SQL statement
            final String SQL = SQL_UPDATE;
            int indice = 1;
            System.out.println("se ejecuto " + SQL);
            prstmnt = conn.prepareStatement(SQL);
            prstmnt.setInt(indice++, registroEquipoDTO.getRegistroIdRegistro());
            prstmnt.setString(indice++, registroEquipoDTO.getPropietarioEquipoIdEquipo());
            prstmnt.setString(indice++, registroEquipoDTO.getPropietarioCuentaNumeroDocumento());
            prstmnt.setString(indice++, registroEquipoDTO.getPropietariocuentaTipoDocumento());

            result = prstmnt.executeUpdate();

        } catch (Exception _e) {
            System.out.println("error en el findAll");
        } finally {

            ResourceManager.closePreparedStatement(prstmnt);

            if (!estaConectado) {
                ResourceManager.closePreparedStatement(prstmnt);
            }
        }

    }

  

    @Override
    public List<RegistroEquipo> findByPK(RegistroEquipoPk dto) {
        final boolean estaConectado = (conexion != null);
        Connection conec = null;
        PreparedStatement prstmnt = null;
        ResultSet result = null;
        List<RegistroEquipo> lich = new ArrayList<>();

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
            prstmnt.setString(index++, dto.getPropietarioCuentaNumeroDocumento());
            prstmnt.setString(index++, dto.getPropietarioEquipoIdEquipo());
            prstmnt.setString(index++, dto.getPropietarioTipoDocumento());
            prstmnt.setString(index++, dto.getRegistroIdRegistro());
            result = prstmnt.executeQuery();

            if (!result.wasNull()) {
                while (result.next()) {
                  RegistroEquipo equipoR = new RegistroEquipo();
                    equipoR.setPropietarioCuentaNumeroDocumento(result.getString(1));
                    equipoR.setPropietarioEquipoIdEquipo(result.getString(2));
                    equipoR.setPropietariocuentaTipoDocumento(result.getString(3));
                   
                    

                    lich.add(equipoR);

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
    public void updatePk(RegistroEquipoPk viejoR,
            RegistroEquipoPk nuevoIdEquipo, 
            RegistroEquipoPk viejoIdEquipo, 
            RegistroEquipoPk nuevoNumDoc, 
            RegistroEquipoPk viejoNumDoc, 
            RegistroEquipoPk nuevoTipoDoc, 
            RegistroEquipoPk viejoTipoDoc, 
            RegistroEquipoPk nuevoRegistro, 
            RegistroEquipoPk viejoRegistro) {
       
   
        final boolean estaConectado = (conexion != null);
        Connection conex = null;
        PreparedStatement prstmnt = null;
        int result;

        try {
            if (estaConectado) {
                conex = conexion;
            } else {
                conex = ResourceManager.getConeccion();
            }
            final String SQL = SQL_UPDATEPK;
            int indice = 1;
            System.out.println("se ejecuto" + SQL);
            prstmnt = conex.prepareStatement(SQL);
            prstmnt.setString(indice++, nuevoIdEquipo.getPropietarioEquipoIdEquipo());
            prstmnt.setString(indice++, viejoIdEquipo.getPropietarioEquipoIdEquipo());
            prstmnt.setString(indice++, nuevoNumDoc.getPropietarioCuentaNumeroDocumento());
            prstmnt.setString(indice++, viejoNumDoc.getPropietarioCuentaNumeroDocumento());
            prstmnt.setString(indice++, nuevoTipoDoc.getPropietarioTipoDocumento());
            prstmnt.setString(indice++, viejoTipoDoc.getPropietarioTipoDocumento());
            prstmnt.setString(indice++, nuevoRegistro.getRegistroIdRegistro());
            prstmnt.setString(indice++, viejoRegistro.getRegistroIdRegistro());

            result = prstmnt.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error dentro del UpdatePK: " + e.getMessage());
        } finally {
            ResourceManager.closePreparedStatement(prstmnt);
            if (!estaConectado) {
                ResourceManager.closeConnection(conex);
            }
        }
    }

    @Override
    public void delete(RegistroEquipoPk dto) {
         final boolean estaConectado = (conexion != null);
        Connection conex = null;
        PreparedStatement prstmnt = null;
        int resul;
        try {
            if (estaConectado) {
                conex = conexion;
            } else {
                conex = ResourceManager.getConeccion();
            }
            final String SQL = SQL_DELETE;
            int indice = 1;
            System.out.println("se ejecuto"+ SQL);
            prstmnt = conex.prepareStatement(SQL);
            prstmnt.setString(indice++, dto.getPropietarioCuentaNumeroDocumento());
            prstmnt.setString(indice++, dto.getPropietarioEquipoIdEquipo());
            prstmnt.setString(indice++, dto.getPropietarioTipoDocumento());
            prstmnt.setString(indice++, dto.getRegistroIdRegistro());

            resul = prstmnt.executeUpdate();

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
        PreparedStatement prstmt = null;
        ResultSet rs = null;
        Integer rowsCount = 0;
        try {
            if (estaConectado) {
                conex = conexion;
            } else {
                conex = ResourceManager.getConeccion();
            }

            //SE ASOCIA CON LA BASE DE DATOS
            final String SQL = SQL_SELECT_COUNT;

            System.out.println("Se ejecuto " + SQL);
            prstmt = conex.prepareStatement(SQL);
            rs = prstmt.executeQuery(SQL_SELECT_COUNT);
            if (!rs.wasNull()) {
                while (rs.next()) {
                    rowsCount = rs.getInt(1);

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
        return rowsCount;
    }
    
    }

   
    
