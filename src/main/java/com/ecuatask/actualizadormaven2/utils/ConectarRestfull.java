/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecuatask.actualizadormaven2.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.DatatypeConverter;
import com.ecuatask.actualizadormaven2.model.api.EquipoApi;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import com.ecuatask.actualizadormaven2.model.TokenResponse;

import java.io.IOException;
import java.io.InputStreamReader;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.ecuatask.actualizadormaven2.model.api.ErrorApi;
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
    private TokenResponse tokenResponse;

    

    public ConectarRestfull() throws IOException {
        // ejemplo /https://developer.niceincontact.com/API/CompleteRequestExampleCode
        vendorName = "ecuatask"; //Provide the vendor name
        appName = "ecuataskBooble"; //Provide the application name
        busNo = "Desarrollo"; //Provide the business unit
        username = LeerConfig.getUsuario(); //Provide the username
        password = LeerConfig.getPassword(); //Provide the password
        scope = "";
        baseURL = LeerConfig.getSite();

        tokenResponse = new TokenResponse();
    }

    public TokenResponse getToken() throws JSONException, IOException, UnsupportedEncodingException, MalformedURLException, ProtocolException {
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
        String endpoint = baseURL + "/oauth/token";
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
                + "\",\"scope\":\"" + scope + "\",\"client_secret\":\"" + LeerConfig.getClienteSecret() + "\",\"client_id\":\"" + LeerConfig.getClienteId() + "\"}";

       
        URL tokenURL = new URL(endpoint);
       

        // Creating Request
        // Setting necessary headers
        HttpURLConnection connection;

        connection = (HttpURLConnection) tokenURL.openConnection();

        connection.setRequestMethod("POST");

       
        connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
       
       
        connection.setDoOutput(true);
        OutputStream os = connection.getOutputStream();
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(os, "UTF-8");
        outputStreamWriter.write(postData.toString());
        outputStreamWriter.flush();
        outputStreamWriter.close();

        System.out.println("POST Request to get Token");
        System.out.println("Response Code : "
                + connection.getResponseCode());
        System.out.println(connection.getResponseMessage());
       
        InputStreamReader isr = new InputStreamReader(connection.getInputStream());
       
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
        JSONObject jsonObject = new JSONObject(response.toString());

        this.tokenResponse.setAccessToken(jsonObject.getString("access_token"));
       
        this.tokenResponse.setToken_type(jsonObject.getString("token_type"));
        this.tokenResponse.setExpiresIn(jsonObject.getInt("expires_in"));
        this.tokenResponse.setRefreshToken(jsonObject.getString("refresh_token"));
        connection.disconnect();
        return this.tokenResponse;
    }
    private boolean error;

    public Object getEquipo_no_serie(TokenResponse tokenResponse, String equipo) throws URISyntaxException, Exception {
        List<NameValuePair> nvPairList = new ArrayList<NameValuePair>();
        NameValuePair nv2 = new BasicNameValuePair("no_serie", equipo);
        nvPairList.add(nv2);
        String URL = LeerConfig.getSite()+"/api/equipo_no_serie";
        String result = this.get(tokenResponse, URL, "no_serie="+equipo);
        
        Gson gson = new GsonBuilder().create();
        
        int intIndex = result.indexOf("\"error\":\"");
        if(intIndex > 0){
             return gson.fromJson(result, ErrorApi.class);
          }else{
             return gson.fromJson(result, EquipoApi.class);
          }                        
    }
    
    public String setAplicacion(TokenResponse tokenResponse,EquipoApi equipo, String aplicacion) throws URISyntaxException, Exception {
        List<NameValuePair> nvPairList = new ArrayList<NameValuePair>();
        NameValuePair nv2 = new BasicNameValuePair("check_list_id", equipo.data.check_list_id);
        NameValuePair nv3 = new BasicNameValuePair("opciones_check_list_id", LeerConfig.getOpcionesCheckListId());
        NameValuePair nv4 = new BasicNameValuePair("valor1", aplicacion);
        nvPairList.add(nv2);
        nvPairList.add(nv3);
        nvPairList.add(nv4);
        String URL = LeerConfig.getSite()+"api/check_list__opciones_check_lists";
        String result = this.post(tokenResponse, URL, "check_list_id="+equipo.data.check_list_id+
                "&opciones_check_list_id="+LeerConfig.getOpcionesCheckListId()+
                "&valor1="+ (new String(aplicacion.getBytes("UTF-8"), "ISO-8859-1")) );        
        return result;
    }

    public void setGetAction(TokenResponse tokenResponse, String URL, List<NameValuePair> parametros) throws URISyntaxException {
        // Test to see if you have obtained a token
        this.tokenResponse = tokenResponse;
        URIBuilder builder = new URIBuilder(URL);        
        if (!tokenResponse.getAccessToken().isEmpty()) {
            String apiURL = URL;            
            // baseURL was returned with your successful token request
       
            String endpoint = apiURL;
            try {
                URL agentResourceURL = new URL(endpoint);

                // Creating Request
                // Setting necessary headers
                HttpURLConnection connection = (HttpURLConnection) agentResourceURL.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Accept", "application/json, text/javascript, */*; q=0.01");
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
                connection.setRequestProperty("Authorization", "Bearer " + this.tokenResponse.getAccessToken());
                System.out.println("Bearer " + this.tokenResponse.getAccessToken());

                connection.setDoOutput(true);
                System.out.println("POST Request to get Token");
                System.out.println("Response Code : " + connection.getResponseCode());
                System.out.println(connection.getResponseMessage());

       
                InputStream indd = connection.getErrorStream();

                if (indd == null) {
                    indd = connection.getInputStream();
                }
                ByteArrayOutputStream resultdd = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int length;
                while ((length = indd.read(buffer)) != -1) {
                    resultdd.write(buffer, 0, length);
                }
                

       
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

    public String post(TokenResponse tokenResponse,String url, String params) throws Exception {
        this.tokenResponse = tokenResponse;
        String result = null;

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("POST");
       
        con.setRequestProperty("Accept", "application/json, text/javascript, */*; q=0.01");
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
       
        con.setRequestProperty("Authorization", "Bearer " + this.tokenResponse.getAccessToken());


        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(params);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("'POST' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        System.out.println("Response Body : ");
        BufferedReader in ;
        
        String error ="";
        try{
            in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        }catch(Exception ex){
            error = ex.toString();
            in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            Logger.getLogger(ConectarRestfull.class.getName()).log(Level.SEVERE, null, "Error en post: ["+error+"]");
        }finally{
       
        }
        
        
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        result = response.toString();
        System.out.println(result);
        return result;

    }

    public String get(TokenResponse tokenResponse,String url, String params) throws Exception {
        this.tokenResponse = tokenResponse;
        String result = null;

        URL obj = new URL(url + "?" + params);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/json, text/javascript, */*; q=0.01");
        
        con.setRequestProperty("Authorization", "Bearer " + this.tokenResponse.getAccessToken());

        

        int responseCode = con.getResponseCode();
        System.out.println("'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        System.out.println("Response Body : ");
       
        BufferedReader in ;
        String error ="";
        try{
            in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        }catch(Exception ex){
            error = ex.toString();
            in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            Logger.getLogger(ConectarRestfull.class.getName()).log(Level.SEVERE, null, "Error en get: ["+error+"]");
        }finally{
            System.err.println("->>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+error);
        }
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        result = response.toString();
        System.out.println(result);
        return result;       
        
        
        
    }

}
