/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author wcadena
 */
import ini.ActualizadorJNLP;
import java.io.*; 
import java.util.List;
import javax.swing.JTextArea;

/** 
 * Ejemplo simple de arranque de un programa externo desde java. 
 * @author  Chuidiang 
 */ 
public class PruebaRuntime implements Runnable { 
     
    /** Creates a new instance of PruebaRuntime */ 
    private String contenido;

    

    
    
    private void println(String contenido){
        this.contenido = this.contenido+"\n"+contenido;
    }
    private LeerArchivos LeerArchivos;
    
    public PruebaRuntime(LeerArchivos LeerArchivos)  
    { 
        this.LeerArchivos = LeerArchivos;
        try 
        { 
            // Se lanza el ejecutable. 
            Process p=Runtime.getRuntime().exec ("cmd /c util\\psinfo.exe -d -s"); 
             
            // Se obtiene el stream de salida del programa 
            InputStream is = p.getInputStream(); 
             
            /* Se prepara un bufferedReader para poder leer la salida más comodamente. */ 
            BufferedReader br = new BufferedReader (new InputStreamReader (is)); 
             
            // Se lee la primera linea 
            String aux = br.readLine(); 
             
            // Mientras se haya leido alguna linea 
            while (aux!=null) 
            { 
                // Se escribe la linea en pantalla 
                this.println (aux); 
                 
                // y se lee la siguiente. 
                aux = br.readLine(); 
            } 
        }  
        catch (Exception e) 
        { 
            // Excepciones si hay algún problema al arrancar el ejecutable o al leer su salida.*/
            e.printStackTrace(); 
        } 
    }
    
    public PruebaRuntime()  
    { 
        
        try 
        { 
            // Se lanza el ejecutable. 
            Process p=Runtime.getRuntime().exec ("cmd /c util\\psinfo.exe -d -s"); 
             
            // Se obtiene el stream de salida del programa 
            InputStream is = p.getInputStream(); 
             
            /* Se prepara un bufferedReader para poder leer la salida más comodamente. */ 
            BufferedReader br = new BufferedReader (new InputStreamReader (is)); 
             
            // Se lee la primera linea 
            String aux = br.readLine(); 
             
            // Mientras se haya leido alguna linea 
            while (aux!=null) 
            { 
                // Se escribe la linea en pantalla 
                this.println (aux); 
                 
                // y se lee la siguiente. 
                aux = br.readLine(); 
            } 
        }  
        catch (Exception e) 
        { 
            // Excepciones si hay algún problema al arrancar el ejecutable o al leer su salida.*/
            e.printStackTrace(); 
        } 
    }
     
    /** 
     * Crea la clase principal que ejecuta el comando dir y escribe en pantalla 
     * lo que devuelve dicho comando. 
     * 
     * @param args the command line arguments 
     */ 
    public static void main(String[] args) { 
        PruebaRuntime p =new PruebaRuntime();
        System.out.println("-->"+p.contenido);
        
    }

    public String getContenido() {
        return contenido;
    }
    
    private ActualizadorJNLP aThis0; 
    private JTextArea jTextArea1; 
    private JTextArea jTextArea2; 
    private JTextArea jTextArea3;
    
    public PruebaRuntime(LeerArchivos LeerArchivos_2, ActualizadorJNLP aThis0, JTextArea jTextArea1, JTextArea jTextArea2, JTextArea jTextArea3) {
        this.LeerArchivos = LeerArchivos_2;
        this.aThis0 = aThis0;
        this.jTextArea1 = jTextArea1;
        this.jTextArea2 = jTextArea2;
        this.jTextArea3 = jTextArea3;
    }

    @Override
    public void run() {
        String dato = this.getContenido();
        int j=0;
        List<String> tar = this.LeerArchivos.traductores;
        for (String dat : tar) {
            //System.out.println(j++);
            this.LeerArchivos.parseDoc(dato, dat);
            if(this.jTextArea1!= null){
                this.jTextArea1.setText(dat);
            }
            
        }
    }
    public void run2() {
        String dato = this.getContenido();
        int j=0;
        List<String> tar = this.LeerArchivos.traductores;
        for (String dat : tar) {
            //System.out.println(j++);
            this.LeerArchivos.parseDoc(dato, dat);
        }
    }
    
}