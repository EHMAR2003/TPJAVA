/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaargenprog.tpijava;

/**
 *
 * @author ehmar
 */
public class Partido {
    private Equipo equipo1;
    private Equipo equipo2;
    private int golesEquipo1;
    private int golesEquipo2;
    private Ronda ronda ;

    public Partido(Equipo equipo1, Equipo equipo2) {
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
    }

    public void setRonda(Ronda ronda) {
        this.ronda = ronda;
    }

    public Ronda getRonda() {
        return ronda;
    }

    public Equipo getEquipo1() {
        return equipo1;
    }

    public Equipo getEquipo2() {
        return equipo2;
    }

    public int getGolesEquipo1() {
        return golesEquipo1;
    }

    public int getGolesEquipo2() {
        return golesEquipo2;
    }

    public void setGolesEquipo1(int golesEquipo1) {
        this.golesEquipo1 = golesEquipo1;
    }

    public void setGolesEquipo2(int golesEquipo2) {
        this.golesEquipo2 = golesEquipo2;
    }
    
   public EnumResultado resultado(Equipo equipo){
        if (equipo.getNombre().equals(equipo1.getNombre())){
            if (golesEquipo1 > golesEquipo2){
                return EnumResultado.GANADOR;
            }
            if (golesEquipo1<golesEquipo2){
                return EnumResultado.PERDEDOR;
            } else return EnumResultado.EMPATE;
        }
        if (equipo.getNombre().equals(equipo2.getNombre())){
            if (golesEquipo2>golesEquipo1){
                return EnumResultado.GANADOR;
            }
            if (golesEquipo2<golesEquipo1){
                return EnumResultado.PERDEDOR;
            } else return EnumResultado.EMPATE;
        }        
        return null;
    }
}
