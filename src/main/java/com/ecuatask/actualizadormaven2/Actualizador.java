/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecuatask.actualizadormaven2;

import com.ecuatask.actualizadormaven2.ini.ActualizadorJNLP;
import javax.swing.JFrame;

/**
 *
 * @author wcadena
 */
public class Actualizador {

    
    public static void main(String[] args) {
        // TODO code application logic here
        ActualizadorJNLP applet = new ActualizadorJNLP();
        applet.init();

        JFrame frame = new JFrame("Inventarios");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add( applet );
        frame.pack();
        frame.setLocationRelativeTo( null );
        frame.setVisible( true );

        applet.start();
    }
    
}
