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
import javax.swing.JOptionPane;

/**
 *
 * @author PCOPEN
 */
public class RegistroEquipoDAOImp implements RegistroEquipoDAO  {
    
    
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
            + "AND PROPIEDAD_CUENTA_NUMERO_DOCUMENTO = ? ? "
            + "WHERE PROPIEDAD_CUENTA_TIPO_DOCUMENTO= ?";
            

    private final String SQL_UPDATEPK = "UPDATE" + getTableName() + "\n"
            + "SET\n"
            + "REGISTRO_ID_REGISTRO= ?"
            + "PROPIEDAD_EQUIPO_ID_EQUIPO = ? "
            + "PROPIEDAD CUENTA_NUMERO_DOCUMENTO = ?"
            + "WHERE REGISTRO_ID_RESGISTRO = ?"
            + "AND PROPIEDAD_EQUIPO_ID_EQUIPO = ? "
            + "AND PROPIEDAD_CUENTAIPO_DOCUMENTO_= ?";

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
            ResourceManager.closeResultSet(result);;
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
            JOptionPane.showMessageDialog(null, "Se ejecuto " + SQL);
            prstmnt = conec.prepareStatement(SQL);
            prstmnt.setString(indice++, registroEquipoDTO.getPropietarioEquipoIdEquipo());
            prstmnt.setString(indice++, registroEquipoDTO.getPropietarioCuentaNumeroDocumento());
            prstmnt.setString(indice++, registroEquipoDTO.getPropietariocuentaTipoDocumento());
            prstmnt.setInt(indice ++, registroEquipoDTO.getRegistroIdRegistro());

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
    public void updatePK(RegistroEquipoPk nuevo, RegistroEquipoPk viejo) {
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
            JOptionPane.showMessageDialog(null, "Se ejecuto " + SQL);
            prstmnt = conec.prepareStatement(SQL);
            prstmnt.setString(indice++, nuevo.getRegistroIdRegistro());
            prstmnt.setString(indice++, nuevo.getPropietarioEquipoIdEquipo());
            prstmnt.setString(indice++, nuevo.getPropietarioCuentaNumeroDocumento());
            prstmnt.setString(indice++, nuevo.getPropietarioTipoDocumento());
            prstmnt.setString(indice++, viejo.getPropietarioEquipoIdEquipo());
            

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
}
