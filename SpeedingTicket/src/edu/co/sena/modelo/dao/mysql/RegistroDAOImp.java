/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.sena.modelo.dao.mysql;

import edu.co.sena.dao.RegistroDAO;
import edu.co.sena.modelo.dto.Cuenta;
import edu.co.sena.modelo.dto.CuentaPk;
import edu.co.sena.modelo.dto.Registro;
import edu.co.sena.modelo.dto.RegistroPk;
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
public class RegistroDAOImp implements RegistroDAO {

    private Connection conexion;

    private final String SQL_SELECT = "SELECT * FROM " + getTableName() + "";

    private final String SQL_INSERT = "INSERT INTO " + getTableName() + "\n"
            + "(Cuenta_numero_documento,\n"
            + "Cuenta_tipo_documento,\n"
            + "fecha_hora_entrada,\n"
            + "fecha_hora_salida,\n"
            + "motivo_visita,\n"
            + "rol)\n"
            + "VALUES\n"
            + "(?,?,?,?,?,?);";

    private final String SQL_UPDATE = "UPDATE " + getTableName() + "\n"
            + "SET\n"
            + "fecha_hora_entrada = ?,\n"
            + "fecha_hora_salida = ?,\n"
            + "motivo_visita = ?,\n"
            + "rol = ?\n"
            + "WHERE id_registro = ? AND Cuenta_numero_documento = ? AND Cuenta_tipo_documento = ?;";

    private final String SQL_UPDATEPK = "UPDATE " + getTableName() + "\n"
            + "SET\n"
            + "Cuenta_numero_documento = ?,\n"
            + "Cuenta_tipo_documento = ?\n"
            + "WHERE id_registro = ? AND Cuenta_numero_documento = ? AND Cuenta_tipo_documento = ?";

    private final String SQL_DELETE = "DELETE FROM " + getTableName() + "\n"
            + "WHERE id_registro = ? AND Cuenta_numero_documento = ? AND Cuenta_tipo_documento = ?";

    private final String SQL_SELECT_COUNT = "SELECT count(*) FROM " + getTableName() + "";

    private String getTableName() {
        return "proyecto.Registro";
    }

    @Override
    public List<Registro> findAll() {

        final boolean estaConectado = (conexion != null);
        Connection conec = null;
        PreparedStatement prstmnt = null;
        ResultSet result = null;
        List<Registro> lich = new ArrayList<>();

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
                    Registro registro = new Registro();
                    registro.setIdRegistro(result.getInt(1));
                    registro.setCuentaNumeroDocumento(result.getString(2));
                    registro.setCuentaTipoDocumento(result.getString(3));
                    registro.setFechaHoraEntrada(result.getTimestamp(4));
                    registro.setFechaHoraSalida(result.getTimestamp(5));
                    registro.setMotivoVisita(result.getString(6));
                    registro.setRol(result.getString(7));

                    lich.add(registro);
                }
            }

        } catch (Exception e) {
            System.out.println("Error dentro del FindAll: " + e.getMessage());
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
    public void insert(Registro dto) {
        final boolean estaConectado = (conexion != null);
        Connection conec = null;
        PreparedStatement prstmnt = null;
        int resul;
        try {
            if (estaConectado) {
                conec = conexion;
            } else {
                conec = ResourceManager.getConeccion();
            }
            final String SQL = SQL_INSERT;
            int indice = 1;
            System.out.println("Se conecto" + SQL);
            prstmnt = conec.prepareStatement(SQL);
            prstmnt.setString(indice++, dto.getCuentaNumeroDocumento());
            prstmnt.setString(indice++, dto.getCuentaTipoDocumento());
            prstmnt.setTimestamp(indice++, dto.getFechaHoraEntrada());
            prstmnt.setTimestamp(indice++, dto.getFechaHoraSalida());
            prstmnt.setString(indice++, dto.getMotivoVisita());
            prstmnt.setString(indice++, dto.getRol());

            resul = prstmnt.executeUpdate();

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
    public void update(RegistroPk llaveDto, Registro dto) {

        final boolean estaConectado = (conexion != null);
        Connection conec = null;
        PreparedStatement prstmnt = null;
        int resul;

        try {
            if (estaConectado) {
                conec = conexion;
            } else {
                conec = ResourceManager.getConeccion();
            }
            final String SQL = SQL_UPDATE;
            int indice = 1;
            System.out.println("Se conecto " + SQL);
            prstmnt = conec.prepareStatement(SQL);

            prstmnt.setTimestamp(indice++, dto.getFechaHoraEntrada());
            prstmnt.setTimestamp(indice++, dto.getFechaHoraSalida());
            prstmnt.setString(indice++, dto.getMotivoVisita());
            prstmnt.setString(indice++, dto.getRol());

            prstmnt.setString(indice++, llaveDto.getIdRegistro());
            prstmnt.setString(indice++, llaveDto.getCuentaNumeroDocumento());
            prstmnt.setString(indice++, llaveDto.getCuentaTipoDocumento());

            resul = prstmnt.executeUpdate();

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
    public void updatePk(RegistroPk viejo, RegistroPk nuevo) {
        final boolean estaConectado = (conexion != null);
        Connection conec = null;
        PreparedStatement prstmnt = null;
        int resul;

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
            prstmnt.setString(indice++, nuevo.getCuentaNumeroDocumento());
            prstmnt.setString(indice++, nuevo.getCuentaTipoDocumento());
            prstmnt.setString(indice++, viejo.getIdRegistro());
            prstmnt.setString(indice++, viejo.getCuentaNumeroDocumento());
            prstmnt.setString(indice++, viejo.getCuentaTipoDocumento());

            resul = prstmnt.executeUpdate();

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
    public void delete(RegistroPk dto) {
        final boolean estaConectado = (conexion != null);
        Connection conec = null;
        PreparedStatement prstmnt = null;
        int resul;
        try {
            if (estaConectado) {
                conec = conexion;
            } else {
                conec = ResourceManager.getConeccion();
            }
            final String SQL = SQL_DELETE;
            int indice = 1;
            JOptionPane.showMessageDialog(null, "Se ejecuto " + SQL);
            prstmnt = conec.prepareStatement(SQL);
            prstmnt.setString(indice++, dto.getCuentaNumeroDocumento());
            prstmnt.setString(indice++, dto.getCuentaTipoDocumento());

            resul = prstmnt.executeUpdate();

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
    public List<Registro> findByPK(RegistroPk dto) {

        //SE DECLARAN LAS VARIABLES HA USAR
        final boolean estaConectado = (conexion != null);
        Connection conec = null;
        PreparedStatement prstmnt = null;
        ResultSet result = null;
        List<Registro> lich = new ArrayList<>();

        // SE REALIZA LA CONEXION 
        try {
            if (estaConectado) {
                conec = conexion;
            } else {
                conec = ResourceManager.getConeccion();
            }

            // GUIA DE LAS TABLAS DE BASE DE DATOS
            final String SQL = SQL_SELECT + " where id_registro = ? and where numero_documento = ? AND tipo_documento = ?";

            System.out.println("Se ejecuto " + SQL);
            prstmnt = conec.prepareStatement(SQL);
            int index = 1;
            prstmnt.setString(index++, dto.getIdRegistro());
            prstmnt.setString(index++, dto.getCuentaNumeroDocumento());
            prstmnt.setString(index++, dto.getCuentaTipoDocumento());
            result = prstmnt.executeQuery();

            if (!result.wasNull()) {
                while (result.next()) {
                    Registro regis = new Registro();
                    regis.setIdRegistro(result.getInt(1));
                    regis.setCuentaNumeroDocumento(result.getString(2));
                    regis.setCuentaTipoDocumento(result.getString(3));
                    regis.setFechaHoraEntrada(result.getTimestamp(4));
                    regis.setFechaHoraSalida(result.getTimestamp(5));
                    regis.setMotivoVisita(result.getString(6));
                    regis.setRol(result.getString(7));

                    lich.add(regis);

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
            ResourceManager.closeResultSet(rs);
            ResourceManager.closePreparedStatement(prstmt);
            if (!estaConectado) {
                ResourceManager.closeConnection(conex);
            }
        }
        return rowsCount;
    }
}
