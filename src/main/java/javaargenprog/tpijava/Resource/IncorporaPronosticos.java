/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaargenprog.tpijava.Resource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author ehmar
 */
public class IncorporaPronosticos {
    
    public void agregarPronostico(String Eq1, String G1, String E, String G2, String Eq2, String Jug) throws SQLException { 
        Connection conexion = Conexion.getConexion();
        PreparedStatement sentenciaInsert = conexion.prepareStatement("INSERT INTO pronostico (Equipo1, Gana1, Empata, Gana2, Equipo2, Jugador) VALUES (?,?,?,?,?,?)");
        sentenciaInsert.setString(1, Eq1);
        sentenciaInsert.setString(2, G1);
        sentenciaInsert.setString(3, E);
        sentenciaInsert.setString(4, G2);
        sentenciaInsert.setString(5, Eq2);
        sentenciaInsert.setString(6, Jug);
        sentenciaInsert.executeUpdate();
    }    
}
