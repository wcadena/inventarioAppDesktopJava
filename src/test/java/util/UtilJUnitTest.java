/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import com.ecuatask.actualizadormaven2.utils.ConectarRestfull;
import com.ecuatask.actualizadormaven2.utils.LeerArchivos;
import com.ecuatask.actualizadormaven2.utils.PruebaRuntime;


import java.io.IOException;
import junit.framework.TestCase;

/**
 *
 * @author Humberto
 */
public class UtilJUnitTest extends TestCase {
    
   
    public void testRun(){
        PruebaRuntime p =new PruebaRuntime();
        System.out.println("-->"+p.getContenido());
    }
    public void testConnection() throws InterruptedException, IOException{
        ConectarRestfull c = new ConectarRestfull();
        System.out.println("---->"+c.getToken());;
    }
    public void testPruebaruntime() throws InterruptedException{
        LeerArchivos p = new LeerArchivos();      
        p.cargarTraductores();
       // MyHelloPanel h ;
    }
}