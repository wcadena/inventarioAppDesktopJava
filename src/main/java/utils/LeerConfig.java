/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Anibal
 */
public class LeerConfig {
    public static String getAppVersion() throws IOException {

        String versionString = null;

        //to load application's properties, we use this class
        Properties mainProperties = new Properties();

        FileInputStream file;

        //the base folder is ./, the root of the main.properties file  
        String path = "./util/main.properties";

        //load the file handle for main.properties
        file = new FileInputStream(path);

        //load all the properties from this file
        mainProperties.load(file);

        //we have loaded the properties, so close the file handle
        file.close();

        //retrieve the property we are intrested, the app.version
        versionString = mainProperties.getProperty("app.version");

        //System.out.println("-----"+versionString);
        return versionString;
    }
    
    private static String getData(String archivo, String propiedad) throws IOException {

        String versionString = null;

        //to load application's properties, we use this class
        Properties mainProperties = new Properties();

        FileInputStream file;

        //the base folder is ./, the root of the main.properties file  
        String path = "./util/"+archivo;

        //load the file handle for main.properties
        file = new FileInputStream(path);

        //load all the properties from this file
        mainProperties.load(file);

        //we have loaded the properties, so close the file handle
        file.close();

        //retrieve the property we are intrested, the app.version
        versionString = mainProperties.getProperty(propiedad);

        //System.out.println("-----"+versionString);
        return versionString;
    }

    public static String getUsuario() throws IOException {
        return LeerConfig.getData("main.properties","app.user");
    } 
    public static String getPassword() throws IOException {
        return LeerConfig.getData("main.properties","app.password");
    } 
    public static String getSite() throws IOException {
        return LeerConfig.getData("main.properties","app.site");
    } 
}
