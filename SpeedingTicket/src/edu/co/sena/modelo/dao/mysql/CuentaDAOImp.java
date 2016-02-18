/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.sena.modelo.dao.mysql;

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
public class CuentaDAOImp {
    
      private Connection conexion;

    private final String SQL_SELECT = "SELECT * FROM " + getTableName() + "";

    private final String SQL_INSERT = "INSERT INTO " + getTableName() + "\n"
            + "(NUMERO_DOCUMENTO,\n"
            + "TIPO_DOCUMENTO,\n"
            + "PRIMER_NOMBRE,\n"
            + "SEGUNDO_NOMBRE\n"
            + "PRIMER_APELLIDO\n"
            + "SEGUNDO_APELLIDO\n"
            + "PERFIL\n"
            + "FOTO\n"
            + "VALUES\n"
            + "(?,?,?,?,?,?,?,?)";

    private final String SQL_UPDATE = "UPDATE " + getTableName() + "\n"
            + "SET\n"
            + "PRIMER_NOMBRE = ?,\n"
            + "SEGUNDO_NOMBRE= ?,\n"
            + "PRIMER_APELLIDO= ?,\n"
            + "SEGUNDO_APELLIDO = ?,\n"
            + "PERFIL = ?,\n"
            + "FOTO = ?\n"
            + "WHERE NUMERO_DOCUMENTO = ? "
            + "AND TIPO_DOCUMENTO = ?;";

    private final String SQL_UPDATEPK = "UPDATE " + getTableName()+ "\n"
            + "SET\n"
            + "NUMERO_DOCUMENTO = ?,\n"
            + "TIPO_DOCUMENTO= ?\n"
            + "WHERE NUMERO_DOCUMENTO = ? "
            + "AND TIPO_DOCUMENTO = ?;";
    private final String SQL_DELETE = "DELETE FROM " + getTableName() + "\n"
            + "WHERE NUMERO_DOCUMENTO = ? and TIPO_DOCUMENTO = ?";

    private final String SQL_SELECT_COUNT = "SELECT count(*) FROM " + getTableName() + " AS COUNT";

    public String getTableName() {
        return "PRO.CUENTA";
    }

    public List<Cuenta> findAll() {

        final boolean estaConectado = (conexion != null);
        Connection conex = null;
        PreparedStatement prstmt = null;
        ResultSet rs = null;
        List<Cuenta> lt = new ArrayList<>();

        try {
            if (estaConectado) {
                conex = conexion;
            } else {
                conex = ResourceManager.getConeccion();
            }
            final String SQL = SQL_SELECT;

            System.out.println("Se ejecuto " + SQL);
            prstmt = conex.prepareStatement(SQL);
            rs = prstmt.executeQuery();

            if (!rs.wasNull()) {
                while (rs.next()) {
                    Cuenta cuen = new Cuenta();
                    cuen.setNumeroDocumento(rs.getString(1));
                    cuen.setTipoDocumento(rs.getString(2));
                    cuen.setPrimerNombre(rs.getString(3));
                    cuen.setSegundoNombre(rs.getString(4));
                    cuen.setPrimerApellido(rs.getString(5));
                    cuen.setSegundoApellido(rs.getString(6));
                    cuen.setPerfil(rs.getString(7));
                    cuen.setFoto(rs.getByte(8));
                    lt.add(cuen);
                }
            }

        } catch (Exception e) {
            System.out.println("Fallo el  FindAll: " + e.getMessage());
        } finally {
            ResourceManager.closeResultSet(rs);
            ResourceManager.closePreparedStatement(prstmt);
            if (!estaConectado) {
                ResourceManager.closeConnection(conex);
            }
        }
        return lt;
    }

    
    public void insert(Cuenta dto) {
        final boolean estaConectado = (conexion != null);
        Connection conex = null;
        PreparedStatement prStmnt = null;
        int rs;
        try {
            if (estaConectado) {
                conex = conexion;
            } else {
                conex = ResourceManager.getConeccion();
            }
            final String SQL = SQL_INSERT;
            int indice = 1;
            System.out.println("Se ejecuto " + SQL);
            
            prStmnt = conex.prepareStatement(SQL);
            prStmnt.setString(indice++, dto.getNumeroDocumento());
            prStmnt.setString(indice++, dto.getTipoDocumento());
            prStmnt.setString(indice++, dto.getPrimerNombre());
            prStmnt.setString(indice++, dto.getSegundoNombre());
            prStmnt.setString(indice++, dto.getPrimerApellido());
            prStmnt.setString(indice++, dto.getSegundoApellido());
            prStmnt.setString(indice++, dto.getPerfil());
            prStmnt.setByte(indice++, dto.getFoto());

            rs = prStmnt.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error dentro del Insert: " + e.getMessage());
        } finally {
            ResourceManager.closePreparedStatement(prStmnt);
            if (!estaConectado) {
                ResourceManager.closeConnection(conex);
            }
        }

    }

    
    public void update(CuentaPk llaveDto, Cuenta dto) {
        final boolean estaConectado = (conexion != null);
        Connection conex = null;
        PreparedStatement prstmnt = null;
        int rs;

        try {
            if (estaConectado) {
                conex = conexion;
            } else {
                conex = ResourceManager.getConeccion();
            }
            final String SQL = SQL_UPDATE;
            int indice = 1;
            System.out.println("Se ejecuto " + SQL);
            prstmnt = conex.prepareStatement(SQL);

           prstmnt = conex.prepareStatement(SQL);
            prstmnt.setString(indice++, dto.getNumeroDocumento());
            prstmnt.setString(indice++, dto.getTipoDocumento());
            prstmnt.setString(indice++, dto.getPrimerNombre());
            prstmnt.setString(indice++, dto.getSegundoNombre());
            prstmnt.setString(indice++, dto.getPrimerApellido());
            prstmnt.setString(indice++, dto.getSegundoApellido());

            prstmnt.setString(indice++, llaveDto.getTipoDocumento());
            prstmnt.setString(indice++, llaveDto.getNumeroDocumento());

            rs = prstmnt.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error dentro del Update: " + e.getMessage());
        } finally {
            ResourceManager.closePreparedStatement(prstmnt);
            if (!estaConectado) {
                ResourceManager.closeConnection(conex);
            }
        }

    }

    
    public void updatePk(CuentaPk llaveVieja, CuentaPk llaveNueva) {
        final boolean estaConectado = (conexion != null);
        Connection conex = null;
        PreparedStatement prstmnt = null;
        int rs;

        try {
            if (estaConectado) {
                conex = conexion;
            } else {
                conex = ResourceManager.getConeccion();
            }
            final String SQL = SQL_UPDATEPK;
            int indice = 1;
            
            System.out.println("Se ejecuto " + SQL);
            prstmnt = conex.prepareStatement(SQL);
            prstmnt.setString(indice++, llaveNueva.getTipoDocumento());
            prstmnt.setString(indice++, llaveNueva.getNumeroDocumento());
            prstmnt.setString(indice++, llaveVieja.getTipoDocumento());
            prstmnt.setString(indice++, llaveVieja.getNumeroDocumento());

            rs = prstmnt.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error dentro del UpdatePK: " + e.getMessage());
        } finally {
            ResourceManager.closePreparedStatement(prstmnt);
            if (!estaConectado) {
                ResourceManager.closeConnection(conex);
            }
        }

    }

    
    public void delete(CuentaPk dto) {
        final boolean estaConectado = (conexion != null);
        Connection conex = null;
        PreparedStatement prstmnt = null;
        int rs;
        try {
            if (estaConectado) {
                conex = conexion;
            } else {
                conex = ResourceManager.getConeccion();
            }
            final String SQL = SQL_DELETE;
            int indice = 1;
            System.out.println("Se ejecuto " + SQL);
            prstmnt = conex.prepareStatement(SQL);
            prstmnt.setString(indice++, dto.getTipoDocumento());
            prstmnt.setString(indice++, dto.getNumeroDocumento());

            rs = prstmnt.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error dentro del Delete: " + e.getMessage());
        } finally {
            ResourceManager.closePreparedStatement(prstmnt);
            if (!estaConectado) {
                ResourceManager.closeConnection(conex);
            }
        }
    }

    
    public Cuenta findByPk(CuentaPk dto) {
        final boolean estaConectado = (conexion != null);
        Connection conex = null;
        PreparedStatement prstmnt = null;
        ResultSet rs = null;
        Cuenta cuen = new Cuenta();

        try {
            if (estaConectado) {
                conex = conexion;
            } else {
                conex = ResourceManager.getConeccion();
            }
            final String SQL = SQL_SELECT;

            System.out.println("Se ejecuto " + SQL);
            prstmnt = conex.prepareStatement(SQL);
            rs = prstmnt.executeQuery();

            if (!rs.wasNull()) {
                while (rs.next()) {
                    cuen.setNumeroDocumento(rs.getString(1));
                    cuen.setTipoDocumento(rs.getString(2));
                    cuen.setPrimerNombre(rs.getString(3));
                    cuen.setSegundoNombre(rs.getString(4));
                    cuen.setPrimerApellido(rs.getString(5));
                    cuen.setSegundoApellido(rs.getString(6));
                    cuen.setPerfil(rs.getString(7));
                    cuen.setFoto(rs.getByte(8));
                }
            }

        } catch (Exception e) {
            System.out.println("Error dentro del FindByPK: " + e.getMessage());
        } finally {
            ResourceManager.closeResultSet(rs);
            ResourceManager.closePreparedStatement(prstmnt);
            if (!estaConectado) {
                ResourceManager.closeConnection(conex);
            }
        }
        return cuen;
    }

   
    public int count() {
        final boolean estaConectado = (conexion != null);
        Connection conex = null;
        PreparedStatement prstmnt = null;
        ResultSet rs = null;
        int rowsCount = 0;

        try {
            if (estaConectado) {
                conex = conexion;
            } else {
                conex = ResourceManager.getConeccion();
            }
            final String SQL = SQL_SELECT_COUNT;

            System.out.println("Se ejecuto " + SQL);
            prstmnt = conex.prepareStatement(SQL);
            rs = prstmnt.executeQuery();
            while (rs.next()) {                
                rs.getInt(rowsCount);
            }
            

        } catch (Exception e) {
            System.out.println("Error dentro del SelectCount: " + e.getMessage());
        } finally {
            ResourceManager.closeResultSet(rs);
            ResourceManager.closePreparedStatement(prstmnt);
            if (!estaConectado) {
                ResourceManager.closeConnection(conex);
            }
        }
        return rowsCount;
        
    }

    

}
   


