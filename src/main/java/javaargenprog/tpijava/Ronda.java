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
public class Ronda {
    private String numero;
    private List<Partido> partidos;

    public Ronda(String numero) {
        this.numero = numero;
        partidos = new ArrayList<Partido>();
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setPartidos(List<Partido> partidos) {
        this.partidos = partidos;
    }
    
    public void addPartido(Partido partido){
        this.partidos.add(partido);
    }

    boolean acertoTodos(List<Pronostico> apuestas) {
        int puntosDisponiblesRonda = this.partidos.size();
        int puntosObtenidosRonda = 0;
        for (Pronostico pronostico : apuestas){
            if (pronostico.getPartido().getRonda().getNumero().equals(this.numero)){
                puntosObtenidosRonda += pronostico.puntos();
            }
        }
        return puntosDisponiblesRonda == puntosObtenidosRonda;
    }
}
