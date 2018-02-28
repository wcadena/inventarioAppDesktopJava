/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.utils;

import com.ibm.oauth.OAuth2Details;
import com.ibm.oauth.OAuthConstants;
import com.ibm.oauth.OAuthUtils;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import org.apache.commons.codec.binary.Base64;
/////////////////////
import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;
/////////////////////////////////////////
/*
         *
         * @author Anibal
         */
import static com.ibm.oauth.OAuthUtils.getAccessToken;
import static com.ibm.oauth.OAuthUtils.getAuthorizationHeaderForAccessToken;
import static com.ibm.oauth.OAuthUtils.getBasicAuthorizationHeader;
import static com.ibm.oauth.OAuthUtils.handleResponse;
import static com.ibm.oauth.OAuthUtils.isValid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.persistence.Basic;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

public class ClientConection {

    static final String URL = "http://inventario.ecuatask.localhost"; //el host
    static final String JSONWS_URL = URL + "/api"; //la ruta de los servicios
    static final String USER = "wcadena@outlook.com", PASS = "wcadena"; //las credenciales

    public void conectar2() {

        Credentials creds = null;
        creds = new UsernamePasswordCredentials("wcadena@outlook.com", "wcadena");

        //create a singular HttpClient object
        HttpClient client = new HttpClient();

        //establish a connection within 5 seconds
        client.getHttpConnectionManager().
                getParams().setConnectionTimeout(5000);

        //set the default credentials
        if (creds != null) {
            client.getState().setCredentials(AuthScope.ANY, creds);
        }

        String url = "http://inventario.ecuatask.localhost/oauth/token";
        HttpMethod method = null;

        //create a method object
        method = new GetMethod(url);
        method.setFollowRedirects(true);
        //} catch (MalformedURLException murle) {
        //    System.out.println("<url> argument '" + url
        //            + "' is not a valid URL");
        //    System.exit(-2);
        //}

        //execute the method
        String responseBody = null;
        try {
            client.executeMethod(method);
            responseBody = method.getResponseBodyAsString();
        } catch (HttpException he) {
            System.err.println("Http error connecting to '" + url + "'");
            System.err.println(he.getMessage());
            System.exit(-4);
        } catch (IOException ioe) {
            System.err.println("Unable to connect to '" + url + "'");
            System.exit(-3);
        }

        //write out the request headers
        System.out.println("*** Request ***");
        System.out.println("Request Path: " + method.getPath());
        System.out.println("Request Query: " + method.getQueryString());
        Header[] requestHeaders = method.getRequestHeaders();
        for (int i = 0; i < requestHeaders.length; i++) {
            System.out.print(requestHeaders[i]);
        }

        //write out the response headers
        System.out.println("*** Response ***");
        System.out.println("Status Line: " + method.getStatusLine());
        Header[] responseHeaders = method.getResponseHeaders();
        for (int i = 0; i < responseHeaders.length; i++) {
            System.out.print(responseHeaders[i]);
        }

        //write out the response body
        System.out.println("*** Response Body ***");
        System.out.println(responseBody);

        //clean up the connection resources
        method.releaseConnection();

        System.exit(0);
    }

    public void connectar() {

        //try {
        String randomPhrase = USER + ':' + PASS;

        Base64 base64 = new Base64();
        String passEnc = base64.encodeAsString(randomPhrase.getBytes());
        Client groupsClient = ClientBuilder.newClient();
        WebTarget target = groupsClient.target(JSONWS_URL + "/users"); //esta es la ruta para el servicio de obtener todos los usuarios
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

    public void conecta3IBM() throws IOException {
        //Load the properties file
        Properties config = OAuthUtils.getClientConfigProps(OAuthConstants.CONFIG_FILE_PATH);

//Generate the OAuthDetails bean from the config properties file
        OAuth2Details oauthDetails = OAuthUtils.createOAuthDetails(config);
        System.out.println("------1111111111111");
//Validate Input

        System.out.println("4444444444444444444444444444444444444444444444444444444444444444444----*-*898->"+oauthDetails);
        oauthDetails.setAccessToken("2KgXxmMpWaAqTr9VD5HcGDEd607E8WT3qHrFmxoV");
        oauthDetails.setClientId("5");
        oauthDetails.setClientSecret("2KgXxmMpWaAqTr9VD5HcGDEd607E8WT3qHrFmxoV");
        oauthDetails.setGrantType("password");
        oauthDetails.setUsername("wcadena@outlook.com");
        oauthDetails.setPassword("wcadena");
        oauthDetails.setAccessTokenRequest(true);
        System.out.println("4444444444444444444444444444444444444444444444444444444444444444444----*-*898->"+oauthDetails);

        if (!OAuthUtils.isValidInput(oauthDetails)) {
            System.out.println("Please provide valid config properties to continue.");
            System.exit(0);
        }
        System.out.println("1111111111111111111111111111111");
//Determine operation
System.out.println("------------------------->"+oauthDetails.isAccessTokenRequest());
        if (oauthDetails.isAccessTokenRequest()) {
            //Generate new Access token
            String accessToken = OAuthUtils.getAccessToken(oauthDetails);
            if (OAuthUtils.isValid(accessToken)) {
                System.out.println("Successfully generated Access token for client_credentials grant_type: " + accessToken);
            } else {
                System.out.println("Could not generate Access token for client_credentials grant_type");
            }
        } else {
            //Access protected resource from server using OAuth2.0
            //Response from the resource server must be in Json or Urlencoded or xml
            System.out.println("Resource endpoint url: " + oauthDetails.getResourceServerUrl());
            System.out.println("Attempting to retrieve protected resource");
            com.ibm.oauth.OAuthUtils.getProtectedResource(oauthDetails);
        }
        /////////////////////////////////////////////////
        //segunda parte del codigo
        ////////////////////////////////////////////////
        String resourceURL = oauthDetails.getResourceServerUrl();

        HttpGet get = new HttpGet(resourceURL);
        get.addHeader(OAuthConstants.AUTHORIZATION, getAuthorizationHeaderForAccessToken(oauthDetails
                .getAccessToken()));
        DefaultHttpClient client = new DefaultHttpClient();
        HttpResponse response = null;
        int code = -1;
        try {
            response = client.execute(get);
            code = response.getStatusLine().getStatusCode();
            if (code == 401) {
                // Access token is invalid or expired. Regenerate the access
                // token
                System.out
                        .println("Access token is invalid or expired. Regenerating access    token....");
                String accessToken = getAccessToken(oauthDetails);
                if (isValid(accessToken)) {
                    // update the access token
                    // System.out.println("New access token: " + accessToken);
                    oauthDetails.setAccessToken(accessToken);
                    get.removeHeaders(OAuthConstants.AUTHORIZATION);
                    get.addHeader(OAuthConstants.AUTHORIZATION,
                            getAuthorizationHeaderForAccessToken(oauthDetails
                                    .getAccessToken()));
                    get.releaseConnection();
                    response = client.execute(get);
                    code = response.getStatusLine().getStatusCode();
                    if (code == 401) {
                        throw new RuntimeException(
                                "Could not access protected resource. Server returned http          code: " + code);

                    }

                } else {
                    throw new RuntimeException(
                            "Could not regenerate access token");
                }

            }

            handleResponse(response);
        } catch (Exception ex) {
            throw new RuntimeException(
                    "salto error aca---->" + ex);
        }
        //////////////////////////////////////////
        // tercer grupo de codigo
        ////////////////////////////////////////////
        HttpPost post = new HttpPost(
                oauthDetails.getAuthenticationServerUrl());
        String clientId = oauthDetails.getClientId();
        String clientSecret = oauthDetails.getClientSecret();
        String scope = oauthDetails.getScope();

        List<BasicNameValuePair> parametersBody
                = new ArrayList<BasicNameValuePair>();
        parametersBody.add(new BasicNameValuePair(OAuthConstants.GRANT_TYPE,
                oauthDetails.getGrantType()));
        parametersBody.add(new BasicNameValuePair(OAuthConstants.CLIENT_ID,
                clientId));
        parametersBody.add(new BasicNameValuePair(OAuthConstants.CLIENT_SECRET,
                clientSecret));

        if (isValid(scope)) {
            parametersBody.add(new BasicNameValuePair(OAuthConstants.SCOPE, scope));
        }
        
        org.apache.http.impl.client.DefaultHttpClient cliente;

        cliente = new org.apache.http.impl.client.DefaultHttpClient();
        HttpResponse response2 = null;
        String accessToken = null;
        try {
            post.setEntity(new UrlEncodedFormEntity(parametersBody,
                    HTTP.UTF_8));

            response = client.execute(post);
            int code2 =0;
            code2 = response2.getStatusLine().getStatusCode();
            if (code == 401) {
                System.out.println("Authorization server expects Basic authentication");
                // Add Basic Authorization header
                post.addHeader(
                        OAuthConstants.AUTHORIZATION,
                        getBasicAuthorizationHeader(OAuthConstants.CLIENT_ID,
                                OAuthConstants.CLIENT_SECRET));
                System.out.println("Retry with client credentials");
                post.releaseConnection();
                response = client.execute(post);
                code = response.getStatusLine().getStatusCode();

                if (code == 401) {
                    throw new RuntimeException("Could not retrieve access token for client: " + clientId);
                }
            }

            Map<String, String> map = handleResponse(response);
            accessToken = map.get(OAuthConstants.ACCESS_TOKEN);
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //return accessToken;
        System.out.println("-------------------------->");
        System.out.println(accessToken);
    }
    

    public static void main(String[] args) throws InterruptedException {

        //(new ClientConection()).connectar();//conecta
        try {
            (new ClientConection()).conecta3IBM();
            //(new ClientConection()).conectar2();
        } catch (IOException ex) {
            Logger.getLogger(ClientConection.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
