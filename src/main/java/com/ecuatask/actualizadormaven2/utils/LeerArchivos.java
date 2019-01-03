/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecuatask.actualizadormaven2.utils;

import com.ecuatask.actualizadormaven2.ini.ActualizadorJNLP;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JTextArea;
import com.ecuatask.actualizadormaven2.model.Aplicacion;
import com.ecuatask.actualizadormaven2.model.Disco;
import com.ecuatask.actualizadormaven2.model.Maquina;

/**
 *
 * @author wcadena
 */
public final class LeerArchivos implements Runnable {

    public LeerArchivos() {
        this._aplicaciones = new ArrayList<>();
        this._discos = new ArrayList<>();
        this._maquinas  =new ArrayList<>();
        this.cargarTraductores();
    }
    

    public List<String> traductores;
    
    private ArrayList<Aplicacion> _aplicaciones;
    private final ArrayList<Disco>    _discos;
    private final ArrayList<Maquina>  _maquinas;
    private ActualizadorJNLP aThis; 
    private JTextArea jTextArea1; 
    private JTextArea jTextArea2; 
    private JTextArea jTextArea3;

    public ArrayList<Aplicacion> getAplicaciones() {
        return _aplicaciones;
    }

    public ArrayList<Disco> getDiscos() {
        return _discos;
    }

    public ArrayList<Maquina> getMaquinas() {
        return _maquinas;
    }
    
    

   
    
    

   
    

    public void setTraductores(String traductor) {
        this.traductores.add(traductor);
    }
    private String primerTraductor;

    public String getPrimerTraductor() {
        return primerTraductor;
    }

    public void setPrimerTraductor(String primerTraductor) {
        this.primerTraductor = primerTraductor;
    }

    public void cargarTraductores() {
        this.traductores = new ArrayList<>();
        this.primerTraductor = "System information for \\\\\\\\(.*?):\\W"//1
               +"Uptime:(.+.*?)\\W"
               +"Kernel version:(.+.*?)\\W"
                +"Product type:(.+.*?)\\W"
                +"Product version:(.+.*?)\\W"
                +"Service pack:(.+.*?)\\W"
                +"Kernel build number:(.+.*?)\\W"
                +"Registered organization:(.+.*?)\\W"
                +"Registered owner:(.+.*?)\\W"
                +"IE version:(.+.*?)\\W"
                +"System root:(.+.*?)\\W"
                +"Processors:(.+.*?)\\W"
                +"Processor speed:(.+.*?)\\W"
                +"Processor type:(.+.*?)\\W"
                +"Physical memory:(.+.*?)\\W"
                +"Video driver:(.+.*?)\\W"
                
                +"";
                //+ "Uptime:(.*?)\\W";
        this.setTraductores(primerTraductor);//1
        this.setTraductores("Volume Type       Format     Label                      Size       Free   Free\\W"
                + "(.+.*?)\\W"
                + "(.+.*?)\\W"
                + "(.+.*?)\\W"
                + "(.+.*?)\\W"
                + "(.+.*?)\\W"
                + "(.+.*?)\\W"
                + "Applications:\\W"                
                +""
        );//2
        this.setTraductores("Applications:\\W"  
                + "(.+.*?)\\W"
                +""
        );//2
        
        

    }
    
    public ArrayList<Aplicacion> setAplicacion(String dato) {
        ArrayList<Aplicacion> lista = new ArrayList<>();
        
        StringTokenizer st = new StringTokenizer(dato,"\n");
        boolean lee=false;
        while (st.hasMoreTokens()) {
            String linea =st.nextToken();                        
            if(lee){
                lista.add(new Aplicacion(linea));
            }
            if (linea.equals("Applications:")) {
                lee=true;
            }
        }
        
       
        return lista;
    }

    public Disco setDisco(String dato) {
        Disco di = new Disco();
        StringTokenizer st = new StringTokenizer(dato, "  ");
        
        di.setVolume((st.hasMoreTokens())?st.nextToken():"");
        di.setType((st.hasMoreTokens())?st.nextToken():"");
        di.setFormat((st.hasMoreTokens())?st.nextToken():"");
        di.setLabel((st.hasMoreTokens())?st.nextToken():"");
        di.setSize((st.hasMoreTokens())?st.nextToken():"");
        di.setFree((st.hasMoreTokens())?st.nextToken():"");
        di.setFree_porsiento((st.hasMoreTokens())?st.nextToken():"");
        return di;
    }
    /**
     * Para leer el docuemnto con un archivo que es lo que va a leer y el
     * traductor q es el tipo de archivo que encontrara o el tipo de documento a
     * buscar
     *
     * @param contenidoarchivo
     * @param Traductor
     */
    public void parseDoc(String contenidoarchivo, String Traductor) {
        String input = contenidoarchivo;
        Pattern p = Pattern.compile(Traductor);
        Matcher m = p.matcher(input);

        StringBuilder result = new StringBuilder();
        Maquina mod = new Maquina();
        Disco di = new com.ecuatask.actualizadormaven2.model.Disco();
        ArrayList<Aplicacion> apli = new ArrayList<Aplicacion>();

        while (m.find()) {
            int i = 1;
            int porte = m.groupCount();
        
            if(porte==1){                
                apli = setAplicacion(contenidoarchivo);
                
                Iterator<Aplicacion> nombreIterator = apli.iterator();
                this._aplicaciones = apli;
                while(nombreIterator.hasNext()){
                        Aplicacion elemento = nombreIterator.next();
                }
                
            }
            if(porte==6){                
                for (int j = 0; j < 6; j++) {                    
                    if(m.group(j).trim()!= null&&m.group(j).trim().length()>64){
                        di = this.setDisco(m.group(i++).trim());
                        this._discos.add(di);
                    }                    
                }                
            }
           if(porte==16){
               
            mod.setNombre(m.group(i++).trim());
            mod.setUptime(m.group(i++).trim());
            mod.setKernel_version(m.group(i++).trim());
            mod.setProduct_type(m.group(i++).trim());
            mod.setProduct_version(m.group(i++).trim());
            mod.setService_pack(m.group(i++).trim());
            mod.setKernel_build_number(m.group(i++).trim());
            mod.setRegistered_organization(m.group(i++).trim());
            mod.setRegistered_owner(m.group(i++).trim());
            mod.setIe_version(m.group(i++).trim());
            mod.setSystem_root(m.group(i++).trim());
            mod.setProcessors(m.group(i++).trim());
            mod.setProcessor_speed(m.group(i++).trim());
            mod.setProcessor_type(m.group(i++).trim());
            mod.setPhysical_memory(m.group(i++).trim());
            mod.setVideo_driver(m.group(i++).trim());/**/
            
            System.out.println("->"+mod.toString().trim());
            
           }            
        }
            
    }

    

   
    

    
    public static void LeerArchivos(String[] args) throws InterruptedException {                
        LeerArchivos a =new LeerArchivos();
        a.arranca();
    }
    public void arranca() throws InterruptedException{
        LeerArchivos p = new LeerArchivos();                
        PruebaRuntime pr = new PruebaRuntime(this);
        
        Thread thread1 = new Thread(pr, "Thread 1");
        thread1.join();
        thread1.start();
        
    }
    @Override
    public void run() {
       LeerArchivos a =new LeerArchivos();
        try {
            a.arranca();
        } catch (InterruptedException ex) {
            Logger.getLogger(LeerArchivos.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
}
