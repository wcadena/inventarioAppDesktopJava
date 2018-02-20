/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.utils;

import java.util.logging.Level;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author Anibal
 */
public class ClientConection {
    static final String URL = "http://inventario.ecuatask.localhost"; //el host
    static final String JSONWS_URL = URL + "/api"; //la ruta de los servicios
    static final String USER = "wcadena@outlook.com", PASS = "wcadena"; //las credenciales
    
    public void connectar(){
        
        //try {
            String randomPhrase = USER + ':' + PASS;

            Base64 base64 = new Base64();
            String passEnc = base64.encodeAsString(randomPhrase.getBytes());
            Client groupsClient = ClientBuilder.newClient();
            WebTarget target = groupsClient.target(JSONWS_URL+"/users"); //esta es la ruta para el servicio de obtener todos los usuarios
            Invocation.Builder invocationBuilder = target.request()
                    .header("Authorization", "Basic " + passEnc); //inicia la autenticacion
            Response response = invocationBuilder.get(); //haciendo GET al URL
            if (response.getStatus() == Response.Status.OK.getStatusCode()) { //si se conecto correctamente...

                String resp = response.readEntity(String.class); //... entonces recibo la cadena..
                System.out.println(resp);
                //LOG.log(Level.INFO, ); //.. y ya tengo la lista

            }
        /*} catch (Exception e) {
            System.err.println("--->");//e.printStackTrace();
            System.err.println(".....>>>"+e);
        }*/
    }
    public static void main(String[] args) throws InterruptedException {
       
        (new ClientConection()).connectar();
        
    }

}
