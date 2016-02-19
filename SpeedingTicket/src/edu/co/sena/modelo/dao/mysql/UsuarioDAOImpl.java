/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.sena.modelo.dao.mysql;

import edu.co.sena.dao.UsuarioDAO;
import edu.co.sena.modelo.dto.Usuario;
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
            + "WHERE CUENTA_TIPO_DOCUMENTO = ? "
            + "AND CONTRASEÑA= ? "
            + "AND CORREO = ? "
            + "AND ROL = ?";
    public final String SQL_DELETE = "DELETE" + getTableName()
            + "CONTRASEÑA\n";

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
        List<Usuario> Usuario = new ArrayList<>();

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
                    usu.setCuentaNumeroDocumento(result.getString(1));
                    usu.setCuentaTipoDocumento(result.getString(2));
                    usu.setContraseña(result.getString(3));
                    usu.setCorreo(result.getString(4));
                    usu.setRol(result.getString(5));

                }
            }

        } catch (Exception _e) {
            System.out.println("Fallo el  findAll");
        } finally {
            ResourceManager.closeResultSet(result);
            ResourceManager.closePreparedStatement(prepare);
            if (!estaConectado) {
                ResourceManager.closeConnection(conec);
            }
        }
        return Usuario;
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
            prepa.setString(indice++, UsuarioDTO.getCuentaTipoDocumento());
            prepa.setString(indice++, UsuarioDTO.getCuentaNumeroDocumento());
            prepa.setString(indice++, UsuarioDTO.getContraseña());
            prepa.setString(indice++, UsuarioDTO.getCorreo());
            prepa.setString(indice++, UsuarioDTO.getRol());

            result = prepa.executeUpdate();

        } catch (Exception _e) {
            System.out.println("Fallo el findAll");
        } finally {
            ResourceManager.closePreparedStatement(prepa);
            if (!estaConectado) {
                ResourceManager.closeResultSet((ResultSet) conec);
            }
        }

    }

    @Override
    public void update(Usuario UsuarioDTO) {

        //  SE DECLARAN LAS VARIABLES 
        final boolean estaConectado = (conexion != null);
        Connection conec = null;
        PreparedStatement prepa = null;
        int result;

        // AQUI SE CONSIGUE LA CONEXION 
        try {
            if (estaConectado) {
                conec = conexion;
            } else {
                conec = ResourceManager.getConeccion();
            }

            // SE ASOCIA CON LA BASE DE DATOS 
            final String SQL = SQL_UPDATE;
            int indice = 1;
            System.out.println("se ejecuto " + SQL);
            prepa = conec.prepareStatement(SQL);
            prepa.setString(indice++, UsuarioDTO.getCuentaTipoDocumento());
            prepa.setString(indice++, UsuarioDTO.getCuentaNumeroDocumento());
            prepa.setString(indice++, UsuarioDTO.getContraseña());
            prepa.setString(indice++, UsuarioDTO.getCorreo());
            prepa.setString(indice++, UsuarioDTO.getRol());

            result = prepa.executeUpdate();

        } catch (Exception _e) {
            System.out.println("error en el findAll");
        } finally {
            ResourceManager.closePreparedStatement(prepa);
            if (!estaConectado) {
                ResourceManager.closeConnection(conec);

            }
        }

    }
}
