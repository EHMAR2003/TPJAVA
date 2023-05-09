/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaargenprog.tpijava.Resource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ehmar
 */
public class RepoEquipos {
    //private Connection conexion;
    
    public boolean controlEquipo (String nomEquipo) throws SQLException{
        Connection conexion = Conexion.getConexion();
        boolean existe = false;        
        PreparedStatement sentenciaBusqueda = conexion.prepareStatement("SELECT Nombre FROM equipos WHERE Nombre = ?");
        sentenciaBusqueda.setString(1, nomEquipo);
        ResultSet resultado = sentenciaBusqueda.executeQuery();
        while (resultado.next()){
            existe = true;
        }    
        return existe;    
    }
    
}
