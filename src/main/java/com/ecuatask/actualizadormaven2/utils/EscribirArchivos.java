/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecuatask.actualizadormaven2.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Anibal
 */
public class EscribirArchivos {

    public static Properties config = new Properties();
    public static InputStream configInput = null;
    public static OutputStream configOutput = null;

    public static String getLOCALAPPDATA(){
            String dataFolder = System.getenv("LOCALAPPDATA");
            return (dataFolder+"\\ecuatask\\configUser.properties");
    }
    public static void setPropertyValue(String property, String value) {
        try {
            configOutput = new FileOutputStream(EscribirArchivos.getLOCALAPPDATA(),true);
            config.setProperty(property, value);   
            configOutput.close();
        } catch (Exception e) {
            Logger.getLogger(EscribirArchivos.class.getName()).log(Level.SEVERE, null, "Error guardando configuración\n" + e.getMessage());

        }
    }
    
    public static void WritePropertiesFileMain(String key, String data) {
        EscribirArchivos.WritePropertiesFile(key,data,"./util/main.properties");
    }
    
    public static void WritePropertiesFile(String key, String data) {
        EscribirArchivos.WritePropertiesFile(key,data,EscribirArchivos.getLOCALAPPDATA());
    }
    public static void WritePropertiesFile(String key, String data,String archivo) {
        
        FileOutputStream fileOut = null;
        FileInputStream fileIn = null;
        try {           
            Properties configProperty = new Properties();           
            File file = new File(archivo);
            fileIn = new FileInputStream(file);
            configProperty.load(fileIn);
            configProperty.setProperty(key, data);
            fileOut = new FileOutputStream(file);
            configProperty.store(fileOut, "sample properties");

        } catch (Exception ex) {
            Logger.getLogger(EscribirArchivos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            try {
                fileOut.close();
            } catch (IOException ex) {
                Logger.getLogger(EscribirArchivos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public static void saveParamChanges() {
        try {
            Properties props = new Properties();
            props.setProperty("java.version", System.getProperty("java.version"));
            props.setProperty("java.home", System.getProperty("java.home"));
            props.setProperty("user.name", System.getProperty("user.name"));
            
            File f = new File(EscribirArchivos.getLOCALAPPDATA());            
            
            //file.mkdirs(); // wrong! 
            f.getParentFile().mkdirs(); // correct!
            if (!f.exists()) {
                f.createNewFile();
            }
            
            OutputStream out = new FileOutputStream(f);
            props.store(out, "This is an optional header comment string");
            out.close();
        } catch (Exception e) {
            Logger.getLogger(EscribirArchivos.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
