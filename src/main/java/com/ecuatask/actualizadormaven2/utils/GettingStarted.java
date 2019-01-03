/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecuatask.actualizadormaven2.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.bind.DatatypeConverter;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Anibal
 */
public class GettingStarted {

    private String vendorName;
    private String appName;
    private String busNo;
    private String username;
    private String password;
    private String scope;
    private String baseURL;
    private TokenResponse tokenResponse;

    public GettingStarted() throws IOException {
        ///https://developer.niceincontact.com/API/CompleteRequestExampleCode
        vendorName = "ecuatask"; //Provide the vendor name
        appName = "ecuataskBooble"; //Provide the application name
        busNo = "Desarrollo"; //Provide the business unit
        username = LeerConfig.getUsuario(); //Provide the username
        password = LeerConfig.getPassword(); //Provide the password
        scope = "";
        baseURL = LeerConfig.getSite()+"oauth/token";
        tokenResponse = new TokenResponse();
    }

    public void getToken() throws JSONException {
        // appName, vendorName, and busNo are values created in Central when 
        // registering an API application. They will need to be stored in your 
        // application

        // username and password are the credentials of an agent on your
        // business unit.
        // scope should be one or more of the following:
        // AdminAPI AgentAPI RealTimeAPI PatronAPI CustomAPI
        // for example, if you would like your application to ONLY access the
        // Admin and Real-Time APIs, the scope string would be "AdminAPI 
        // RealTimeAPI"
        // A default null scope is included and can be altered with the scope
        // variable.
        // The scope variable is used in this example to display what scope is
        // returned with the access token
        // Authorization endpoint
        String endpoint = baseURL;
        // Encoded request string
        String AuthToken = appName + "@" + vendorName + ":" + busNo;
        String encodedAuthToken;
        try {
            encodedAuthToken = DatatypeConverter.printBase64Binary(AuthToken
                    .getBytes("UTF-8"));

            // Since this is a desktop application that is not running in a
            // browser we will use
            // the password "grant type" to complete the post data.
            String postData = "{\"grant_type\":\"password\",\"username\":\""
                    + username + "\",\"password\":\"" + password
                    + "\",\"scope\":\"" + scope + "\",\"client_secret\":\"2KgXxmMpWaAqTr9VD5HcGDEd607E8WT3qHrFmxoV\",\"client_id\":\"5\"}";
            
                    
            
                    
            // la consulta se raliza a http://inventario.ecuatask.localhost/oauth/token?grant_type=password&client_id=11&client_secret=AD3exMOK8AQsZmfjCx7Fc3LUNhGMhcSfhW2uPbCT&username=wcadena@outlook.com&password=wcadena
            URL tokenURL = new URL(endpoint);
           

            // Creating Request
            // Setting necessary headers
            HttpURLConnection connection;
            
            connection = (HttpURLConnection) tokenURL.openConnection();

            connection.setRequestMethod("POST");

           
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
           
            connection.setDoOutput(true);
            OutputStream os = connection.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(os,"UTF-8");
            outputStreamWriter.write(postData.toString());
            outputStreamWriter.flush();
            outputStreamWriter.close();

            System.out.println("POST Request to get Token");
            System.out.println("Response Code : "
                    + connection.getResponseCode());
            System.out.println(connection.getResponseMessage());
            
           
            InputStreamReader isr= new InputStreamReader(connection.getInputStream());
           
            BufferedReader in = new BufferedReader(isr);
            
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                
                response.append(inputLine);
            }
            in.close();

            // print result
            if (response.toString().isEmpty()) {
                System.out.println("Response is empty");
            } else {
                System.out.println(response.toString());
            }

            // Here for json parsing org.json library is used
            // Store the token
            // Here the Token is stored in an object
            System.out.println("---------54sdfgr-->"+response.toString());
            JSONObject jsonObject = new JSONObject(response.toString());

            this.tokenResponse.accessToken = jsonObject.getString("access_token");
            
            this.tokenResponse.tokenType = jsonObject.getString("token_type");
            this.tokenResponse.expiresIn = jsonObject.getInt("expires_in");
            this.tokenResponse.refreshToken = jsonObject.getString("refresh_token");
           
            connection.disconnect();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    // The variables baseURL and accessToken were returned with the successful
    // getToken request.
    public void getAgents() throws IOException {
        // Test to see if you have obtained a token
        if (!tokenResponse.accessToken.isEmpty()) {
            String apiURL = LeerConfig.getSite()+"api/equipo_no_serie?no_serie=MXL2091N5M";
            
            // baseURL was returned with your successful token request
            //String endpoint = this.tokenResponse.resourceServerBaseUri + apiURL;
            String endpoint = apiURL;
            try {
                URL agentResourceURL = new URL(endpoint);

                // Creating Request
                // Setting necessary headers
                HttpURLConnection connection = (HttpURLConnection) agentResourceURL.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Accept","application/json, text/javascript, */*; q=0.01");
                connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
                connection.setRequestProperty("Authorization", "Bearer " + this.tokenResponse.accessToken);
                System.out.println("Bearer " + this.tokenResponse.accessToken);
                
                connection.setDoOutput(true);
                System.out.println("POST Request to get Token");
                System.out.println("Response Code : " + connection.getResponseCode());
                System.out.println(connection.getResponseMessage());
                
                
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                if (response.toString().isEmpty()) {
                    System.out.println("Response is empty");
                } else {                    
                    System.out.println(response.toString());
                }
                connection.disconnect();
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (ProtocolException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            System.out.println("obtain a token");
        }
    }

    public static void main(String[] args) throws JSONException, IOException {
        GettingStarted testObject = new GettingStarted();
        testObject.getToken();
        testObject.getAgents();
    }
}

class TokenResponse {

    String accessToken;
    String tokenType;
    int expiresIn;
    String refreshToken;
    String scope;
    String resourceServerBaseUri;
    String refreshTokenServerUri;
    int agentId;
    int teamId;

}
