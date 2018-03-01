/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.utils;

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
import javax.xml.bind.DatatypeConverter;
import org.json.JSONException;
import org.json.JSONObject;


/**
 *
 * @author Anibal
 */
public class ConectarRestfull {
    
    private String vendorName;
    private String appName;
    private String busNo;
    private String username;
    private String password;
    private String scope;
    private String baseURL;
    private main.java.model.TokenResponse tokenResponse;
    
    

    public ConectarRestfull(String vendorName, String appName, String busNo, String username, String password, String scope, String baseURL, main.java.model.TokenResponse tokenResponse) {
        this.vendorName = vendorName;
        this.appName = appName;
        this.busNo = busNo;
        this.username = username;
        this.password = password;
        this.scope = scope;
        this.baseURL = baseURL;
        this.tokenResponse = tokenResponse;
    }
    
    public ConectarRestfull() throws IOException {
         ///https://developer.niceincontact.com/API/CompleteRequestExampleCode
        vendorName = "ecuatask"; //Provide the vendor name
        appName = "ecuataskBooble"; //Provide the application name
        busNo = "Desarrollo"; //Provide the business unit
        username = LeerConfig.getUsuario(); //Provide the username
        password = LeerConfig.getPassword(); //Provide the password
        scope = "";
        baseURL = LeerConfig.getSite();
        
        tokenResponse = new main.java.model.TokenResponse();
    }
    
    
    
    
    public main.java.model.TokenResponse getToken() throws JSONException, IOException ,UnsupportedEncodingException,MalformedURLException,ProtocolException{
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
        String endpoint = baseURL+"/oauth/token";
        // Encoded request string
        String AuthToken = appName + "@" + vendorName + ":" + busNo;
        String encodedAuthToken;
        
            encodedAuthToken = DatatypeConverter.printBase64Binary(AuthToken
                    .getBytes("UTF-8"));

            // Since this is a desktop application that is not running in a
            // browser we will use
            // the password "grant type" to complete the post data.
            String postData = "{\"grant_type\":\"password\",\"username\":\""
                    + username + "\",\"password\":\"" + password
                    + "\",\"scope\":\"" + scope + "\",\"client_secret\":\""+LeerConfig.getClienteSecret()+"\",\"client_id\":\""+LeerConfig.getClienteId()+"\"}";
            
                    //+ "\",\"scope\":\"" + scope + "\"}";
            
            URL tokenURL = new URL(endpoint);
           // URL url = new URL(null, endpoint, new sun.net.www.protocol.https.Handler());

            // Creating Request
            // Setting necessary headers
            HttpURLConnection connection;
            
            connection = (HttpURLConnection) tokenURL.openConnection();

            connection.setRequestMethod("POST");

            //connection.setRequestProperty("Accept","application/json, text/javascript, */*; q=0.01");
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            //connection.setRequestProperty("Authorization", "basic "+ encodedAuthToken);
            //connection.setRequestProperty("Content-Length", Integer.toString(postData.toString().length()));
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
            ////////////////
            /*InputStream indd = connection.getErrorStream();

            if (indd == null) {
                indd = connection.getInputStream();
            }
            ByteArrayOutputStream resultdd = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = indd.read(buffer)) != -1) {
                resultdd.write(buffer, 0, length);
            }
            System.out.println("--*-*--->" + resultdd.toString());;//para ver el error
            */
            ////////////////
            
           
            InputStreamReader isr= new InputStreamReader(connection.getInputStream());
            //InputStreamReader isr= new InputStreamReader(connection.getInputStream(), Charset.forName("UTF-8"));
            BufferedReader in = new BufferedReader(isr);
            
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                //System.out.println("======>"+inputLine);
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
            
            JSONObject jsonObject = new JSONObject(response.toString());

            this.tokenResponse.setAccessToken(jsonObject.getString("access_token"));
            //this.tokenResponse.resourceServerBaseUri = jsonObject.getString("resource_server_base_uri");
            this.tokenResponse.setToken_type(jsonObject.getString("token_type"));
            this.tokenResponse.setExpiresIn(jsonObject.getInt("expires_in"));
            this.tokenResponse.setRefreshToken(jsonObject.getString("refresh_token"));
            //this.tokenResponse.scope = jsonObject.getString("scope");
            //this.tokenResponse.refreshTokenServerUri = jsonObject.getString("refresh_token_server_uri");
            //this.tokenResponse.teamId = jsonObject.getInt("team_id");
            //this.tokenResponse.agentId = jsonObject.getInt("agent_id");

            connection.disconnect();
            return this.tokenResponse;
        
        
       
    }
    
}
