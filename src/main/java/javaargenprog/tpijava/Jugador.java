/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaargenprog.tpijava;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ehmar
 */
public class Jugador {
    private String nombre;
    private List<Pronostico> pronosticos;
    private Integer puntos;
    private Integer puntosPorBonus;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.puntos = 0;
        this.pronosticos = new ArrayList<Pronostico>();
        puntosPorBonus = 0;
    }

    public List<Pronostico> getPronosticos() {
        return pronosticos;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getPuntos() {
        return puntos;
    }

    public void setPronosticos(List<Pronostico> pronosticos) {
        this.pronosticos = pronosticos;
    }

    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }
    
    public void addPronostico(Pronostico pronostico){
        pronosticos.add(pronostico);
    }

    public int puntosPorPronostico() {
        int puntosPro = 0;
        for (Pronostico pronostico : pronosticos){
            puntosPro += pronostico.puntos();
        }
        return puntosPro;
    }

    public void incrementarPuntosPorBonus(int i) {
        puntosPorBonus += i;
    }
    
    public int puntosTotales(){
        return this.puntosPorPronostico() + this.puntosPorBonus;
    }
}
