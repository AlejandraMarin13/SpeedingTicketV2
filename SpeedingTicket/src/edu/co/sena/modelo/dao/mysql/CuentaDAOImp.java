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
import javax.swing.JOptionPane;

/**
 *
 * @author PCOPEN
 */
public class CuentaDAOImp implements CuentaDAO {

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

    private final String SQL_UPDATEPK = "UPDATE " + getTableName() + "\n"
            + "SET\n"
            + "NUMERO_DOCUMENTO = ?,\n"
            + "TIPO_DOCUMENTO= ?\n"
            + "WHERE NUMERO_DOCUMENTO = ? "
            + "AND TIPO_DOCUMENTO = ?;";
    private final String SQL_DELETE = "DELETE FROM " + getTableName() + "\n"
            + "WHERE NUMERO_DOCUMENTO = ? and TIPO_DOCUMENTO = ?";

    private final String SQL_SELECT_COUNT = "SELECT count(*) FROM " + getTableName() + " AS COUNT";

    public String getTableName() {
        return "proyecto.Cuenta";
    }

    @Override
    public List<Cuenta> findAll() {
        final boolean estaConectado = (conexion != null);
        Connection conec = null;
        PreparedStatement prstmnt = null;
        ResultSet result = null;
        List<Cuenta> lich = new ArrayList<>();

        try {
            if (estaConectado) {
                conec = conexion;
            } else {
                conec = ResourceManager.getConeccion();
            }
            final String SQL = SQL_SELECT;

            System.out.println("Se ejecuto " + SQL);
            prstmnt = conec.prepareStatement(SQL);
            result = prstmnt.executeQuery();

            if (!result.wasNull()) {
                while (result.next()) {
                    
                    Cuenta cuen = new Cuenta();
                    cuen.setNumeroDocumento(result.getString(1));
                    cuen.setTipoDocumento(result.getString(2));
                    cuen.setPrimerNombre(result.getString(3));
                    cuen.setSegundoNombre(result.getString(4));
                    cuen.setPrimerApellido(result.getString(5));
                    cuen.setSegundoApellido(result.getString(6));
                    cuen.setPerfil(result.getString(7));
                    cuen.setFoto(result.getByte(8));
                    lich.add(cuen);
                }
            }

        } catch (Exception e) {
            System.out.println("Fallo el  FindAll: " + e.getMessage());
        } finally {
            ResourceManager.closeResultSet(result);
            ResourceManager.closePreparedStatement(prstmnt);
            if (!estaConectado) {
                ResourceManager.closeConnection(conec);
            }
        }
        return lich;
    }

    @Override
    public void insert(Cuenta dto) {
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
            System.out.println("Se ejecuto " + SQL);
            prstmnt = conec.prepareStatement(SQL);

            prstmnt = conec.prepareStatement(SQL);
            prstmnt.setString(indice++, dto.getNumeroDocumento());
            prstmnt.setString(indice++, dto.getTipoDocumento());
            prstmnt.setString(indice++, dto.getPrimerNombre());
            prstmnt.setString(indice++, dto.getSegundoNombre());
            prstmnt.setString(indice++, dto.getPrimerApellido());
            prstmnt.setString(indice++, dto.getSegundoApellido());

            prstmnt.setString(indice++, llaveDto.getTipoDocumento());
            prstmnt.setString(indice++, llaveDto.getNumeroDocumento());

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

            System.out.println("Se ejecuto " + SQL);
            prstmnt = conec.prepareStatement(SQL);
            prstmnt.setString(indice++, nuevo.getTipoDocumento());
            prstmnt.setString(indice++, nuevo.getNumeroDocumento());
            prstmnt.setString(indice++, viejo.getTipoDocumento());
            prstmnt.setString(indice++, viejo.getNumeroDocumento());

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
            System.out.println("Se ejecuto " + SQL);
            prstmnt = conec.prepareStatement(SQL);
            prstmnt.setString(indice++, dto.getTipoDocumento());
            prstmnt.setString(indice++, dto.getNumeroDocumento());

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
        final boolean estaConectado = (conexion != null);
        Connection conec = null;
        PreparedStatement prstmnt = null;
        ResultSet result = null;
        List<Cuenta> lich = new ArrayList<>();

        try {
            if (estaConectado) {
                conec = conexion;
            } else {
                conec = ResourceManager.getConeccion();
            }
            final String SQL = SQL_SELECT + " where Tipo_Documento = ? AND Num_Documento = ?";

            JOptionPane.showMessageDialog(null, "Se ejecuto " + SQL);
            prstmnt = conec.prepareStatement(SQL);
            int index = 1;
            prstmnt.setString(index++, dto.getTipoDocumento());
            prstmnt.setString(index++, dto.getNumeroDocumento());
            result = prstmnt.executeQuery();

            if (!result.wasNull()) {
                while (result.next()) {
                    Cuenta cuen = new Cuenta();
                    cuen.setTipoDocumento(result.getString(1));
                    cuen.setNumeroDocumento(result.getString(2));
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
            ResourceManager.closeResultSet(result);
            ResourceManager.closePreparedStatement(prstmnt);
            if (!estaConectado) {
                ResourceManager.closeConnection(conec);
            }
        }
        return lich;
    }


    @Override
        public int count() {
                final boolean estaConectado = (conexion != null);
        Connection conec = null;
        PreparedStatement prstmnt = null;
        ResultSet result = null;
        int rowsCount = 0;

        try {
            if (estaConectado) {
                conec = conexion;
            } else {
                conec = ResourceManager.getConeccion();
            }
            final String SQL = SQL_SELECT_COUNT;

            System.out.println("Se ejecuto " + SQL);
            prstmnt = conec.prepareStatement(SQL);
            result = prstmnt.executeQuery();
            while (result.next()) {                
                result.getInt(rowsCount);
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


    
