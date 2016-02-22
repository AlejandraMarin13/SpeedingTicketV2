/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.sena.modelo.dao.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import static java.sql.DriverManager.getConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author PCOPEN
 */
public class ResourceManager { // de donde sale eso
  
   private static String JDBC_URL = "jdbc:mysql://localhost:3306/proyecto";
    private static String JDBC_USER = "root";
    private static String JDBC_PASSWORD = "123456789";

    public static Connection getConeccion() {

        Connection conexion = null;
        try {
            conexion = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
        } catch (Exception e) {
            System.err.println("No hay conexion" + e.getMessage());

        }
        return conexion;
    }

    public static void closeConnection(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (Exception e) {

            System.err.println("Error al cerrar la conexion" + e.getMessage());
        }

    }
   public static void close(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException sqle) {
            System.out.println("No se cerro ResultSet"+sqle.getMessage());
        }
    }
    public static void closePreparedStatement(PreparedStatement sentencia) {
        try {
            if (sentencia != null) {
                sentencia.close();
            }
        } catch (Exception e) {

            System.err.println("Error al cerrar el prepared statement" + e.getMessage());
        }

    }

    


}
   
   

