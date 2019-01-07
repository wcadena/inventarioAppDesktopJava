/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecuatask.actualizadormaven.model.api.hilos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.JToggleButton;
import com.ecuatask.actualizadormaven.model.TokenResponse;
import com.ecuatask.actualizadormaven.model.api.EquipoApi;
import com.ecuatask.actualizadormaven.utils.ConectarRestfull;
import com.ecuatask.actualizadormaven.model.Aplicacion;

/**
 *
 * @author Anibal
 */
public class ActualizarAplicaciones implements Runnable {

    
    @Override
    public void run() {
        enviarAplicacion();
    }

    private final com.ecuatask.actualizadormaven.model.TokenResponse tokenResponse;
    private final EquipoApi _equipo;
    private final ArrayList<Aplicacion> _aplicaciones;
    
    private final JProgressBar jProgressBar;
    private final javax.swing.JButton jButton1;
    private final javax.swing.JToggleButton jToggleButton1;
    private final javax.swing.JToggleButton jToggleButton2;

    public ActualizarAplicaciones(TokenResponse tokenResponse, EquipoApi _equipo, ArrayList<Aplicacion> _aplicaciones, JProgressBar jProgressBar, JButton jButton1, JToggleButton jToggleButton1, JToggleButton jToggleButton2) {
        this.tokenResponse = tokenResponse;
        this._equipo = _equipo;
        this._aplicaciones = _aplicaciones;
        this.jProgressBar = jProgressBar;
        this.jButton1 = jButton1;
        this.jToggleButton1 = jToggleButton1;
        this.jToggleButton2 = jToggleButton2;
        
        this.jProgressBar.setMaximum( this._aplicaciones.size());
        this.jToggleButton1.setText("En Ejecucion");
        this.jToggleButton1.setEnabled(false);
        this.jToggleButton2.setEnabled(false);
        this.jButton1.setEnabled(false);
    }

   
    
    
    public void enviarAplicacion( ){
        
        
        try {
            ConectarRestfull conn = new ConectarRestfull();
            int i=1;
            for (Aplicacion temp : _aplicaciones) {
                String result =conn.setAplicacion(tokenResponse, _equipo, temp.getAplicacion());
                
                this.jProgressBar.setValue(i++);
                
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
        this.jToggleButton1.setText("Actualizado!!!!!!!!");
        this.jToggleButton1.setEnabled(true);
        this.jToggleButton2.setEnabled(true);
        this.jButton1.setEnabled(true);
    }
}
