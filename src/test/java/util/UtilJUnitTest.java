/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import com.ecuatask.actualizadormaven2.utils.GettingStarted;
import com.ecuatask.actualizadormaven2.utils.LeerArchivos;
import com.ecuatask.actualizadormaven2.utils.LeerConfig;
import com.ecuatask.actualizadormaven2.utils.PruebaRuntime;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import javax.xml.bind.DatatypeConverter;
import junit.framework.TestCase;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Humberto
 */
public class UtilJUnitTest extends TestCase {
    
   
    public void testRun(){
        PruebaRuntime p =new PruebaRuntime();
        System.out.println("-->"+p.getContenido());
    }
    public void tes_dtApiConnect() throws IOException{
        GettingStarted testObject = new GettingStarted();
        testObject.getToken();
        testObject.getAgents();
    }
    public void tes_dtArchivos() throws InterruptedException{
        String[] args =null;
        LeerArchivos.LeerArchivos(args);
    }
}