/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaargenprog.tpijava.Resource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ehmar
 */
public class Conexion {
  private static Connection conexion = null;
  private static String nombreDB = "dbtpjava";
  private static String host = "localhost";
  private static String puerto = "3306";
  private static String usuarioDB = "root";
  private static String contrasenia = "@EHMar002";
  
  public static Connection getConexion() throws SQLException {
      if (conexion == null) {
          conexion = DriverManager.getConnection("jdbc:mysql://" + host + ":" + puerto + "/" + nombreDB,usuarioDB,contrasenia);
      }
      return conexion;
  }
  
  public static void cerrarConexion() throws SQLException{
      if(conexion != null && !conexion.isClosed()){
        conexion.close();
        conexion = null;
      }
  }
}
