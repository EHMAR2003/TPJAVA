/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaargenprog.tpijava;

/**
 *
 * @author ehmar
 */
public class Equipo {
    private String nombre;
    private String descripcion;

    public Equipo(String nombre) {
	this.nombre = nombre;
    }

    public String getNombre() {
	return nombre;
    }

    public String getDescripcion() {
	return descripcion;
    }

    public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
    }
}
