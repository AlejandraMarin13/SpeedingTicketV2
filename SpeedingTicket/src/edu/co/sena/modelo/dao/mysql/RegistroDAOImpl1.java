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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author NICO-DAVID
 */
public class RegistroDAOImpl1 implements RegistroDAO {

    private Connection conexion;

    private final String SQL_SELECT = "select * from " + getTableName() + "";

    private final String SQL_INSERT = "INSERT INTO " + getTableName() + "\n"
            + "(`ID_REGISTRO`,\n"
            + "`CUENTA_NUMERO_DOCUMENTO`,\n"
            + "`CUENTA_TIPO_DOCUMENTO`,\n"
            + "`FECHA_ENTRADA`,\n"
            + "`FECHA_SALIDA`)\n"
            + "VALUES\n"
            + "(?,?,?,?,?,?,?,?);";

    private final String SQL_UPDATE = "UPDATE " + getTableName() + "\n"
            + "SET\n"
            + "`CUENTA_NUMERO_DOCUMENTO` = ?,\n"
            + "`CUENTA_TIPO_DOCUMENTO` = ?,\n"
            + "`FECHA_ENTRADA` = ?,\n"
            + "`FECHA_SALIDA` = ?\n"
            + "WHERE `ID_REGISTRO` = ?;";
    private final String SQL_UPDATEPK = "UPDATE " + getTableName() + "\n"
            + "SET\n"
            + "ID_REGISTRO = ?\n"
            + "WHERE ID_REGISTRO = ?;";

    private final String SQL_SELECTPK = "SELECT * FROM "
            + getTableName() + " where ID_REGISTRO = ?;";

    private final String SQL_COUNT = "select count(*)from " + getTableName() + ";";

    private final String SQL_DELETE = "DELETE FROM " + getTableName() + "\n"
            + "WHERE ID_REGISTRO = ?;";

    @Override
    public List<Registro> findAll() {
        // declaracion de variables
        final boolean estaConectado = (conexion != null);
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Registro> lista = new ArrayList<>();

        try {
            // obtener el la conexion 

            if (estaConectado) {
                conn = conexion;
            } else {
                conn = ResourceManager.getConeccion();
            }

            // construct the SQL statement
            final String SQL = SQL_SELECT;

            System.out.println("Se Ha Ejecutado " + SQL);
            stmt = conn.prepareStatement(SQL);
            rs = stmt.executeQuery();

            if (!rs.wasNull()) {
                while (rs.next()) {
                    Registro registro = new Registro();
                    registro.setIdRegistro(rs.getInt(1));
                    registro.setCuentaNumeroDocumento(rs.getString(2));
                    registro.setCuentaTipoDocumento(rs.getString(3));
                    registro.setFechaEntrada(rs.getTimestamp(4));
                    registro.setFechaSalida(rs.getTimestamp(5));

                    lista.add(registro);
                }
            }

        } catch (Exception e) {
            System.out.println("error en el findAll" + e.getMessage());
        } finally {
            ResourceManager.close(rs);
            ResourceManager.close((ResultSet) stmt);
            if (!estaConectado) {
                ResourceManager.closeConnection(conn);
            }
        }
        return lista;
    }

    @Override
    public void insert(Registro dto) {
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
            final String SQL = SQL_INSERT;
            int indice = 1;
            System.out.println("se ejecuto " + SQL);
            stmt = conn.prepareStatement(SQL);
            stmt.setInt(indice++, dto.getIdRegistro());
            stmt.setString(indice++, dto.getCuentaNumeroDocumento());
            stmt.setString(indice++, dto.getCuentaTipoDocumento());
            stmt.setTimestamp(indice++, (Timestamp) dto.getFechaEntrada());
            stmt.setTimestamp(indice++, (Timestamp) dto.getFechaSalida());

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
    public void update(RegistroPk llaveDto, Registro dto) {
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
            final String SQL = SQL_UPDATE;
            int indice = 1;
            System.out.println("se ejecuto " + SQL);
            stmt = conn.prepareStatement(SQL);
            stmt.setInt(indice++, dto.getIdRegistro());
            stmt.setString(indice++, dto.getCuentaNumeroDocumento());
            stmt.setString(indice++, dto.getCuentaTipoDocumento());
            stmt.setTimestamp(indice++, (Timestamp) dto.getFechaEntrada());
            stmt.setTimestamp(indice++, (Timestamp) dto.getFechaSalida());

            rs = stmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("error en el findAll " + e.getMessage());
        } finally {
            ResourceManager.close((ResultSet) stmt);
            if (!estaConectado) {
                ResourceManager.closeConnection(conn);
            }
        }

    }

    @Override
    public void updatePk(RegistroPk viejo, RegistroPk nuevo) {
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
            final String SQL = SQL_UPDATEPK;
            int indice = 1;
            System.out.println("se ejecuto " + SQL);
            stmt = conn.prepareStatement(SQL);
            stmt.setString(indice++, nuevo.getIdRegistro());
            stmt.setString(indice++, viejo.getIdRegistro());

            rs = stmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("error en el findAll " + e.getMessage());
        } finally {
            ResourceManager.close((ResultSet) stmt);
            if (!estaConectado) {
                ResourceManager.closeConnection(conn);
            }
        }

    }

    public void delete(RegistroPk dot) {
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
            stmt.setString(indice++, dot.getIdRegistro());

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
    public List<Registro> findByPK(RegistroPk dto) {
        final boolean estaConectado = (conexion != null);
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Registro> lista = new ArrayList<>();

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
            stmt.setInt(indice++, dto.getNumeroRegistro());
            rs = stmt.executeQuery();
            if (!rs.wasNull()) {
                while (rs.next()) {
                    Registro registro = new Registro();
                    registro.setIdRegistro(rs.getInt(1));
                    registro.setCuentaNumeroDocumento(rs.getString(2));
                    registro.setCuentaTipoDocumento(rs.getString(3));
                    registro.setFechaEntrada(rs.getTimestamp(4));
                    registro.setFechaSalida(rs.getTimestamp(5));

                    lista.add(registro);

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
        return lista;
    }

    @Override
    public int count() {
        final boolean estaConectado = (conexion != null);
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int registro = 0;
        try {
            // obtener el la conexion 

            if (estaConectado) {
                conn = conexion;
            } else {
                conn = ResourceManager.getConeccion();
            }

            // construct the SQL statement
            final String SQL = SQL_COUNT;
            stmt = conn.prepareStatement(SQL);
            System.out.println("se ejecuto " + SQL);
            rs = stmt.executeQuery();
            rs.next();
            registro = rs.getInt(1);
        } catch (Exception e) {
            System.out.println("error en el findAll" + e.getMessage());
        } finally {
            ResourceManager.close((ResultSet) stmt);
            if (!estaConectado) {
                ResourceManager.closeConnection(conn);
            }
        }
        return registro;
    }

    public String getTableName() {
        return "proyecto.registro";
    }
}
