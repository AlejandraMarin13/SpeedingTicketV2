/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.sena.modelo.dao.mysql;

import edu.co.sena.dao.UsuarioDAO;
import edu.co.sena.modelo.dto.Usuario;
import edu.co.sena.modelo.dto.UsuarioPk;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PCOPEN
 */
public class UsuarioDAOImpl implements UsuarioDAO {

    private Connection conexion;

    private final String SQL_SELECT = "select * from " + getTableName() + "";

    private final String SQL_INSERT = "INSERT INTO " + getTableName() + "\n"
            + "(LOGIN,\n"
            + "CUENTA_NUMERO_DOCUMENTO,\n"
            + "CUENTA_TIPO_DOCUMENTO,\n"
            + "CONTRASEÑA,\n"
            + "CORREO,\n"
            + " ROL\n"
            + "VALUES\n"
            + "(?,?,?,?,?)";

    private final String SQL_UPDATE = "UPDATE " + getTableName() + "\n"
            + "SET\n"
            + "CUENTA_NUMERO_DOCUMENTO = ?,\n"
            + "CUENTA_TIPO_DOCUMENTO = ? "
            + "CONTRASEÑA= ? "
            + "CORREO = ? "
            + "ROL = ?" 
            + "WHERE LOGIN = ?;";
    public final String SQL_DELETE = "DELETE" + getTableName()
            + "WHERE LOGIN = ?;";

    private final String SQL_UPDATEPK = "SELECT * FROM "
            + getTableName() + " where CUENTA_NUMERO_DOCUMENTO"
            + " AND CUENTA_TIPO_DOCUMENTO =?;";
    private final String SQL_SELECT_COUNT = "SELECT count(*) FROM " + getTableName() + "";

    public String getTableName() {
        return "proyecto.Usuario";
    }

    @Override
    public List<Usuario> findAll() {

        // SE DECLARAN LAS VARIABLES 
        final boolean estaConectado = (conexion != null);
        Connection conec = null;
        PreparedStatement prepare = null;
        ResultSet result = null;
        List<Usuario> usuario = new ArrayList<>();

        try {
            // AQUI SE HACE LA CONEXION

            if (estaConectado) {
                conec = conexion;
            } else {
                conec = ResourceManager.getConeccion();
            }

            // SE ASOCIA  CON LA BASE DE DATOS 
            final String SQL = SQL_SELECT;

            System.out.println("se ejecuto " + SQL);
            prepare = conec.prepareStatement(SQL);
            result = prepare.executeQuery();

            if (!result.wasNull()) {

                while (result.next()) {

                    Usuario usu = new Usuario();
                    usu.setCorreo(result.getString(1));
                    usu.setContraseña(result.getString(2));
                    usu.setCuentaNumeroDocumento(result.getString(3));
                    usu.setCuentaTipoDocumento(result.getString(4));
                    usu.setRol(result.getString(5));

                    usuario.add(usu);
                }
            }

        } catch (Exception e) {
            System.out.println("Fallo el  findAll" + e.getMessage());
        } finally {
            ResourceManager.close(result);
            ResourceManager.closePreparedStatement(prepare);
            if (!estaConectado) {
                ResourceManager.closeConnection(conec);
            }
        }
        return usuario;
    }

    @Override
    public void insert(Usuario UsuarioDTO) {

        final boolean estaConectado = (conexion != null);
        Connection conec = null;
        PreparedStatement prepa = null;
        int result;

        try {

            //AQUI SE CONSIGUE LA CONEXION
            if (estaConectado) {
                conec = conexion;
            } else {
                conec = ResourceManager.getConeccion();
            }

            // SE ASOCIA CON LA BASE DE DATOS
            final String SQL = SQL_INSERT;
            int indice = 1;
            System.out.println("se ejecuto " + SQL);
            prepa = conec.prepareStatement(SQL);
            prepa.setString(indice++, UsuarioDTO.getCorreo());
            prepa.setString(indice++, UsuarioDTO.getContraseña());
            prepa.setString(indice++, UsuarioDTO.getCuentaNumeroDocumento());
            prepa.setString(indice++, UsuarioDTO.getCuentaTipoDocumento());
            prepa.setString(indice++, UsuarioDTO.getRol());

            result = prepa.executeUpdate();

        } catch (Exception e) {
            System.out.println("Fallo el findAll" + e.getMessage());
        } finally {
            ResourceManager.closePreparedStatement(prepa);
            if (!estaConectado) {
                ResourceManager.closeConnection(conec);
            }
        }

    }

    @Override
    public void update(UsuarioPk llaveDto, Usuario dto) {

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
            prstmnt.setString(indice++, llaveDto.getLogin());
            prstmnt.setString(indice++, dto.getCorreo());
            prstmnt.setString(indice++, dto.getContraseña());
            prstmnt.setString(indice++, dto.getRol());
            prstmnt.setString(indice++, dto.getCuentaNumeroDocumento());
            prstmnt.setString(indice++, dto.getCuentaTipoDocumento());

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
    public void updatePk(UsuarioPk viejo, UsuarioPk nuevo) {
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
            prstmnt.setString(indice++, nuevo.getLogin());
            prstmnt.setString(indice++, viejo.getLogin());
            ;

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
    public void delete(UsuarioPk dto) {
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

            prstm.setString(indice++, dto.getLogin());

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
    public List<Usuario> findByPK(UsuarioPk dto) {
        final boolean estaConectado = (conexion != null);
        Connection conex = null;
        PreparedStatement prstmnt = null;
        ResultSet result = null;
        List<Usuario> lich = new ArrayList<>();

        // SE REALIZA LA CONEXION 
        try {
            if (estaConectado) {
                conex = conexion;
            } else {
                conex = ResourceManager.getConeccion();
            }

            // GUIA DE LAS TABLAS DE BASE DE DATOS
            final String SQL = SQL_UPDATE;

            System.out.println("Se ejecuto " + SQL);
            prstmnt = conex.prepareStatement(SQL);
            int index = 1;
            prstmnt.setString(index++, dto.getLogin());

            result = prstmnt.executeQuery();

            if (!result.wasNull()) {
                while (result.next()) {
                    Usuario usario = new Usuario();
                    usario.setContraseña(result.getString(1));
                    usario.setCorreo(result.getString(2));
                    usario.setCuentaNumeroDocumento(result.getString(3));
                    usario.setCuentaTipoDocumento(result.getString(3));
                    usario.setRol(result.getString(3));

                    lich.add(usario);

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
