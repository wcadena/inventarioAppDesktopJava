/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.model.api.hilos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.java.ini.ActualizadorJNLP;
import main.java.model.TokenResponse;
import main.java.model.api.EquipoApi;
import main.java.utils.ConectarRestfull;
import model.Aplicacion;

/**
 *
 * @author Anibal
 */
public class ActualizarAplicaciones implements Runnable {

    
    @Override
    public void run() {
        enviarAplicacion();
    }

    private main.java.model.TokenResponse tokenResponse;
    private EquipoApi _equipo;
    private ArrayList<Aplicacion> _aplicaciones;

    public ActualizarAplicaciones(TokenResponse tokenResponse, EquipoApi _equipo, ArrayList<Aplicacion> _aplicaciones) {
        this.tokenResponse = tokenResponse;
        this._equipo = _equipo;
        this._aplicaciones = _aplicaciones;
    }
    
    
    public void enviarAplicacion( ){
        System.out.println("-------------------------->Accion ");
        
        try {
            ConectarRestfull conn = new ConectarRestfull();
            for (Aplicacion temp : _aplicaciones) {
                String result =conn.setAplicacion(tokenResponse, _equipo, temp.getAplicacion());
                
                int intIndex = result.indexOf("\"error\":\"");
                
                if (intIndex > 0) {                    
                    Thread.sleep(60000);
                    result = conn.setAplicacion(tokenResponse, _equipo, temp.getAplicacion());
                    intIndex = result.indexOf("\"error\":\"");
                    if (intIndex > 0) { 
                        Logger.getLogger(ActualizarAplicaciones.class.getName()).log(Level.SEVERE, null, "No se actualizo una aplicacion: ["+result+"]");
                    }
                }                                 
            }
        } catch (IOException ex) {
            Logger.getLogger(ActualizarAplicaciones.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ActualizarAplicaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
