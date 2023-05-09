/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaargenprog.tpijava;

/**
 *
 * @author ehmar
 */
public class Pronostico {
   private Partido partido;
   private Equipo equipo;
   private EnumResultado resultado;

    public Pronostico(Partido partido, Equipo equipo, EnumResultado resultado) {
        this.partido = partido;
        this.equipo = equipo;
        this.resultado = resultado;
    }

    public Partido getPartido() {
        return partido;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public EnumResultado getResultado() {
        return resultado;
    }
   
    public int puntos(){
        if (this.resultado.equals(this.partido.resultado(equipo))){
            return 1;            
        } else  return 0;
    }
    
}
