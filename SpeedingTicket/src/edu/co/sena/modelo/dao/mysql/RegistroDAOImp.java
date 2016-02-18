/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.sena.modelo.dao.mysql;

import edu.co.sena.dao.RegistroDAO;
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
            + "(ID_REGISTRO,\n"
            + "CUENTA_TIPO_DOCUMENTO,\n"
            + "CUENTA_NUMERO_DOCUMENTO,\n"
            + "FECHA_HORA_ENTRADA,\n"
            + "FECHA_HORA_SALIDA,\n"
            + "ROL,\n"
            + "MOTIVO_VISITA)"
            + "VALUES\n"
            + "(?,?,?,?,?,?,?);";

    private final String SQL_UPDATE = "UPDATE " + getTableName() + "\n"
            + "SET\n"
            + "FECHA_HORA_ENTRADA = ?,\n"
            + "FECHA_HORA_SALIDA = ?,\n"
            + "ROL_REG = ?,\n"
            + "MOTIVO_VISITA = ?\n"
            + "WHERE ID_REGISTRO = ? AND CUENTATIPO_DOCUMENTO = ? AND CUENTA_NUMERO_DOCUMENTO = ?";

    private final String SQL_UPDATEPK = "UPDATE " + getTableName() + "\n"
            + "SET\n"
            + "CUENTA_TIPO_DOCUMENTO = ?,\n"
            + "CUENTA_NUMERO_DOCUMENTO = ?\n"
            + "WHERE ID_REGISTRO = ? AND CUENTA_TIPO_DOCUMENTO = ? AND CUENTA_NUMERO_DOCUMENTO = ?";

    private final String SQL_DELETE = "DELETE FROM " + getTableName() + "\n"
            + "WHERE ID_REGISTRO = ? AND CUENTA_TIPO_DOCUMENTO = ? AND CUENTA_NUMERO_DOCUMENTO = ?;";

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

            JOptionPane.showMessageDialog(null, "Se ejecuto " + SQL);
            prstmnt = conec.prepareStatement(SQL);
            result = prstmnt.executeQuery();

            if (!result.wasNull()) {
                while (result.next()) {
                    Registro registro = new Registro();
                    registro.setIdRegistro(result.getInt(1));
                    registro.setCuentaTipoDocumento(result.getString(2));
                    registro.setCuentaNumeroDocumento(result.getString(3));
                    registro.setFechaHoraEntrada(result.getTimestamp(4));
                    registro.setFechaHoraSalida(result.getTimestamp(5));
                    registro.setRol(result.getString(6));
                    registro.setMotivoVisita(result.getString(7));

                    registro.setMotivoVisita(result.getString(7));
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
            JOptionPane.showMessageDialog(null, "Se ejecuto " + SQL);
            prstmnt = conec.prepareStatement(SQL);
            prstmnt.setTimestamp(indice++, dto.getFechaHoraEntrada());
            prstmnt.setTimestamp(indice++, dto.getFechaHoraSalida());
            prstmnt.setString(indice++, dto.getRol());
            prstmnt.setString(indice++, dto.getCuentaTipoDocumento());
            prstmnt.setString(indice++, dto.getCuentaNumeroDocumento());
            prstmnt.setString(indice++, dto.getMotivoVisita());

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
            JOptionPane.showMessageDialog(null, "Se ejecuto " + SQL);
            prstmnt = conec.prepareStatement(SQL);
            prstmnt.setString(indice++, llaveDto.getIdEqupo());
            prstmnt.setString(indice++, llaveDto.getCuentaTipoDocumento());
            prstmnt.setString(indice++, llaveDto.getCuentaNumeroDodumento());
            prstmnt.setTimestamp(indice++, dto.getFechaHoraEntrada());
            prstmnt.setTimestamp(indice++, dto.getFechaHoraSalida());
            prstmnt.setString(indice++, dto.getRol());
            prstmnt.setString(indice++, dto.getMotivoVisita());

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
            prstmnt.setString(indice++, nuevo.getCuentaTipoDocumento());
            prstmnt.setString(indice++, nuevo.getCuentaNumeroDodumento());
            prstmnt.setString(indice++, viejo.getIdEqupo());
            prstmnt.setString(indice++, viejo.getCuentaTipoDocumento());
            prstmnt.setString(indice++, viejo.getCuentaNumeroDodumento());

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
            prstmnt.setString(indice++, dto.getCuentaTipoDocumento());
            prstmnt.setString(indice++, dto.getCuentaNumeroDodumento());

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
        final boolean estaConectado = (conexion != null);
        Connection conec = null;
        PreparedStatement prstmnt = null;
        ResultSet resul = null;
        List<Registro> lich = new ArrayList<>();

        try {
            if (estaConectado) {
                conec = conexion;
            } else {
                conec = ResourceManager.getConeccion();
            }
            final String SQL = SQL_SELECT + " WHERE ID_REGISTRO= ? AND CUENTA_TIPO_DOCUMENTO = ? AND CUENTA_NUMERO_DOCUMENTO= ?";

            JOptionPane.showMessageDialog(null, "Se ejecuto " + SQL);
            prstmnt = conec.prepareStatement(SQL);
            int indice = 1;
            prstmnt.setString(indice++, dto.getIdEqupo());
            prstmnt.setString(indice++, dto.getCuentaTipoDocumento());
            prstmnt.setString(indice++, dto.getCuentaNumeroDodumento());
            resul = prstmnt.executeQuery();

            if (!resul.wasNull()) {
                while (resul.next()) {
                    Registro registro = new Registro();
                    registro.setIdRegistro(resul.getInt(1));
                    registro.setCuentaTipoDocumento(resul.getString(2));
                    registro.setCuentaNumeroDocumento(resul.getString(3));
                    registro.setFechaHoraEntrada(resul.getTimestamp(4));
                    registro.setFechaHoraSalida(resul.getTimestamp(5));
                    registro.setRol(resul.getString(6));
                    registro.setMotivoVisita(resul.getString(7));
                    lich.add(registro);
                }
            }

        } catch (Exception e) {
            System.out.println("Error dentro del FindByPK: " + e.getMessage());
        } finally {
            ResourceManager.closeResultSet(resul);
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
        ResultSet resul = null;
        Integer rowCount = 0;

        try {
            if (estaConectado) {
                conec = conexion;
            } else {
                conec = ResourceManager.getConeccion();
            }
            final String SQL = SQL_SELECT_COUNT;

            JOptionPane.showMessageDialog(null, "Se ejecuto " + SQL);
            prstmnt = conec.prepareStatement(SQL);
            resul = prstmnt.executeQuery();
            if (!resul.wasNull()) {
                while (resul.next()) {
                    rowCount = resul.getInt(1);

                }

            }

        } catch (Exception e) {
            System.out.println("Error dentro del SelectCount: " + e.getMessage());
        } finally {
            ResourceManager.closeResultSet(resul);
            ResourceManager.closePreparedStatement(prstmnt);
            if (!estaConectado) {
                ResourceManager.closeConnection(conec);
            }
        }
        return rowCount;

    }
}
