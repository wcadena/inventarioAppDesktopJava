/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.utils;

import main.java.ini.ActualizadorJNLP;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JTextArea;
import model.Aplicacion;
import model.Disco;
import model.Maquina;

/**
 *
 * @author wcadena
 */
public class LeerArchivos implements Runnable {

    public LeerArchivos() {
        this._aplicaciones = new ArrayList<Aplicacion>();
        this._discos = new ArrayList<Disco>();
        this._maquinas  =new ArrayList<Maquina>();
        this.cargarTraductores();
    }
    

    public List<String> traductores;
    
    private ArrayList<Aplicacion> _aplicaciones;
    private ArrayList<Disco>    _discos;
    private ArrayList<Maquina>  _maquinas;
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
    
    

    public LeerArchivos(ActualizadorJNLP aThis, JTextArea jTextArea1, JTextArea jTextArea2, JTextArea jTextArea3) throws InterruptedException {
        this.aThis = aThis;
        this.jTextArea1 = jTextArea1;
        this.jTextArea2 = jTextArea2;
        this.jTextArea3 = jTextArea3;
        this.arrancaConLectura();
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
        this.traductores = new ArrayList<String>();
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
        ArrayList<Aplicacion> lista = new ArrayList<Aplicacion>();
        //model.Aplicacion a =new model.Aplicacion();
        //System.out.println("******+=>"+dato);
        StringTokenizer st = new StringTokenizer(dato,"\n");
        //System.out.println("******->"+st.nextToken());
        //System.out.println("******->"+st.nextToken());
        boolean lee=false;
        while (st.hasMoreTokens()) {
            String linea =st.nextToken();                        
            if(lee){
                //System.out.println("\\\\"+linea);
                lista.add(new Aplicacion(linea));
            }
            if (linea.equals("Applications:")) {
                lee=true;
            }
        }
        
       /* StringTokenizer st2 = new StringTokenizer(st.nextToken());
        while (st2.hasMoreTokens()) {
            System.out.println("//"+st2.nextToken());
        }*/
        return lista;
    }

    public Disco setDisco(String dato) {
        Disco di = new Disco();
        StringTokenizer st = new StringTokenizer(dato, "  ");
        di.setVolume(st.nextToken());
        di.setType(st.nextToken());
        di.setFormat(st.nextToken());
        di.setLabel(st.nextToken());
        di.setSize(st.nextToken());
        di.setFree(st.nextToken());
        di.setFree_porsiento(st.nextToken());
        return di;
    }
    /**
     * Para leer el docuemnto con un archivo que es lo que va a leer y el
     * traductor q es el tipo de archivo que encontrara o el tipo de documento a
     * buscar
     *
     * @param Dirarchivo
     * @param Traductor
     */
    public void parseDoc(String contenidoarchivo, String Traductor) {
        //utils.LectorArchivo l=new utils.LectorArchivo(contenidoarchivo.getArchivo());
        //String input= l.getTextoTotal();
        String input = contenidoarchivo;
        //= "User clientId=23421. Some more text clientId=33432. This clientNum=100";

        //Pattern p = Pattern.compile("RFF[(]AVF:(.*?)-[.*?]DOC[(]P:110:111[(](.*?)-");
        //Pattern p = Pattern.compile("RFF[(]AVF:(.*?)-\n.*?DOC[(]P:110:111[(](.*?)-\n(.*?)-");//x3
        Pattern p = Pattern.compile(Traductor);
        Matcher m = p.matcher(input);

        StringBuffer result = new StringBuffer();
        Maquina mod = new Maquina();
        Disco di = new model.Disco();
        ArrayList<Aplicacion> apli = new ArrayList<Aplicacion>();

        //System.out.println("jasdf;oaidsjfadfh");
        while (m.find()) {
            //System.out.print("Start index: " + m.start());
            //System.out.print(" End index: " + m.end() + " ");
            //System.out.println("----->");
            int i = 1;
            int porte = m.groupCount();
            //System.out.println("Porte:"+porte);
            //mod.setId(1);
            //System.out.print("Nombre: " + m.group(1));
            //System.out.println("****"+m.toString());
            if(porte==1){                
                apli = setAplicacion(contenidoarchivo);
                
                Iterator<Aplicacion> nombreIterator = apli.iterator();
                this._aplicaciones = apli;
                while(nombreIterator.hasNext()){
                        Aplicacion elemento = nombreIterator.next();
                        System.out.println("+->"+elemento);
                }
                
            }
            if(porte==6){                
                for (int j = 0; j < 6; j++) {
                    //System.out.println("****"+m.group(j).trim().length());
                    if(m.group(j).trim()!= null&&m.group(j).trim().length()>64){
                        di = this.setDisco(m.group(i++).trim());
                        this._discos.add(di);
                        System.out.println("=->"+di.toString());
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
            
            if(mod != null)
                this._maquinas.add(mod);
           }            
        }
        //m.appendTail(result);
        //System.out.println(result);
    }

    

   
    

    public static void main(String[] args) throws InterruptedException {
        //new LeerArchivos().
        LeerArchivos.LeerArchivos(args);
        //ParseDocument.main_test(args);
        
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
        thread1.run();
        
    }
    public void arrancaConLectura() throws InterruptedException{
        LeerArchivos p = new LeerArchivos();      
        this.jTextArea1.setText("AAAAAAAAAAAAAAAAAAA");
        PruebaRuntime pr = new PruebaRuntime(this,this.aThis,this.jTextArea1 ,this.jTextArea2, this.jTextArea3);
        
        Thread thread1 = new Thread(pr, "Thread 1");
        
        thread1.run();
        
    }
/*
    

    public void traductorTest(String archivo, String traductor) {
        utils.LectorArchivo l = new utils.LectorArchivo(archivo);
        String input = l.getTextoTotal();
        //= "User clientId=23421. Some more text clientId=33432. This clientNum=100";

        //Pattern p = Pattern.compile("RFF[(]AVF:(.*?)-[.*?]DOC[(]P:110:111[(](.*?)-");
        //Pattern p = Pattern.compile("RFF[(]AVF:(.*?)-\n.*?DOC[(]P:110:111[(](.*?)-\n(.*?)-");//x3
        Pattern p = Pattern.compile(traductor);
        Matcher m = p.matcher(input);

        StringBuffer result = new StringBuffer();
        while (m.find()) {
            System.out.print("Nombre: " + m.group(1));
            System.out.print("  \tSexo:" + m.group(2));
            System.out.print("  \tDTM:" + m.group(3).trim());
            System.out.print("  \tDTM:" + m.group(4).trim());
            System.out.print("  \tLOC174:" + m.group(5).trim() + "");
            System.out.print("  \tLOC178:" + m.group(6).trim() + "");
            System.out.print("  \tLOC179:" + m.group(7).trim() + "");
            System.out.print("  \tNat:" + m.group(8).trim() + "");
            System.out.print("  \tRFF" + m.group(9).trim() + "");
            System.out.print("  \tDocu:" + m.group(10).trim() + "");
            System.out.print("  \tDTM" + m.group(11).trim() + "");
            System.out.print("  \tLOC91" + m.group(12).trim() + "");/** /

            System.out.println("");
            //m.appendReplacement(result, m.group(1) + "***masked***");
        }
        //m.appendTail(result);
        System.out.println(result);
    }
*/

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
