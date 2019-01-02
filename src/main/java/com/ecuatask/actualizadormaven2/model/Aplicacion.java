/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecuatask.actualizadormaven2.model;
    
/**
 *
 * @author wcadena
 */
public class Aplicacion {
    public String aplicacion;

    public Aplicacion() {
        this.aplicacion = "";
    }
    public Aplicacion(String aplicacion) {
        this.aplicacion = aplicacion;
    }

    public String getAplicacion() {
        return aplicacion;
    }

    public void setAplicacion(String aplicacion) {
        this.aplicacion = aplicacion;
    }

    @Override
    public String toString() {
        return "Aplicacion{" + "aplicacion=" + aplicacion + '}';
    }
    
}
