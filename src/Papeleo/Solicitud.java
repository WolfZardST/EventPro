/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Papeleo;
import Usuario.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author danae
 */
public class Solicitud {
    //private static long contador = 100000;
    private int numero;
    private Cliente cliente;
    private Planificador planificador;
    private Date fechaSolicitud;
    private Date fechaEvento;
    private String id;
    private EstadoSolicitud estadoSolicitud;
    private TipoEvento tipoEvento;
    private double precioBase;
    
 //---- Setters ------
    /**
     * 
     * @param numero 
     */
    public void setNumero(int numero){
        this.numero = numero;  
    }
    /**
     * 
     * @param cliente 
     */
    public void setCliente(Cliente cliente){
        this.cliente = cliente;
    }
    /**
     * 
     * @param planificador 
     */
    public void setPlanificador(Planificador planificador){
        this.planificador = planificador;
    }
    /**
     * 
     * @param fechaSolicitud 
     */
    public void setFechaSolicitud(Date fechaSolicitud){
        this.fechaSolicitud = fechaSolicitud;
    }
    /**
     * 
     * @param fechaEvento 
     */
    public void setFechaEvento(Date fechaEvento){
        this.fechaEvento = fechaEvento;
    }
    /**
     * 
     * @param estadoSolicitud 
     */
     public void setEstadoSolicitud(EstadoSolicitud estadoSolicitud){
        this.estadoSolicitud = estadoSolicitud;
    }
     
    //----- Getters -----
    /**
     * Devuelve numero
     * @return 
     */
    public int getNumero(){
        return this.numero;
    }
    /**
     * Obtiene cliente
     * @return 
     */
    
    public Cliente getCliente(){
        return this.cliente;
    }
    
    /**
     * Devuelve el planificador
     * @return 
     */
    
    public Planificador getPlanificador(){
        return this.planificador;
    }
    
    /**
     * Devuelve fecha de solicitud
     * @return 
     */
    
    public Date getFechaSolicitud(){
        return this.fechaSolicitud;
    }

    /**
     * Devuelve fecha del evento
     * @return 
     */
    public Date getFechaEvento(){
        return this.fechaEvento;
    }
    
    /**
     * Devuelve el ID de la solicitud
     * @return 
     */
    public String getId(){
        return this.id;
    }
    
    /**
     * 
     * @param id 
     */

    public void setId(String id) {
        this.id = id;
    }
    
    /**
     * 
     * @param tipoEvento 
     */

    public void setTipoEvento(TipoEvento tipoEvento) {
        this.tipoEvento = tipoEvento;
    }
    
    /**
     * 
     * @param precioBase 
     */

    public void setPrecioBase(double precioBase) {
        this.precioBase = precioBase;
    }
    
    /**
     * Devuelve el estado de la solicitud
     * @return 
     */
    public EstadoSolicitud getEstadoSolicitud(){
        return this.estadoSolicitud;
    }
    
    /**
     * Devuelve de que tipo es el evento
     * @return 
     */
    
    public TipoEvento getTipoEvento(){
        return this.tipoEvento;
    }
    
    
    // ---- Constructores ---- 
       
    /**
     * 
     * @param cliente
     * @param fechaSolicitud
     * @param fechaEvento
     * @param usuarios
     * @param tipoEvento
     * @param precioBase 
     */
    public Solicitud(Cliente cliente,Date fechaSolicitud, String fechaEvento, ArrayList<Usuario> usuarios, TipoEvento tipoEvento,double precioBase){
        this.cliente = cliente;
        this.id =generarId() ;
        //this.contador += 1;
        this.fechaSolicitud =  new Date();      
        this.planificador = AsignarPlanificador(usuarios);
        this.estadoSolicitud = EstadoSolicitud.PENDIENTE;
        this.precioBase = precioBase;
        this.tipoEvento = tipoEvento;
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
        Date fecha = new Date();
        try {
            fecha = dateFormat.parse(fechaEvento);
        } catch (ParseException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.fechaEvento = fecha;
        
             
    }
    
    
    /**
     * Asigna el planificador a la solicitud
     * @param usuarios
     * @return 
     */
    
    
    
    public Planificador AsignarPlanificador(ArrayList<Usuario> usuarios){
        // Crear array Planificadores
        ArrayList <Planificador> planificadores = new ArrayList();
        for (Usuario usuario: usuarios){
            if(usuario.getTipo() == 'P')
                planificadores.add((Planificador)usuario);                     
        }              
        // Generar valor aleatorio entre 0 y longitud de la Arraylist Planificador
        int tamano = planificadores.size();
        Random r = new Random();
        int aleatorio = r.nextInt(tamano);
        Planificador planificador = planificadores.get(aleatorio);
               
        return planificador;        
    }
    
 
    
    @Override
    /**
     * Muetra los datos de la solicitud
     */
    public String toString(){
        System.out.println("DATOS:");
        System.out.println("CLIENTE: "+cliente.getNombre()+" "+cliente.getApellido());
        System.out.println("PLANIFICADOR ASIGNADO: "+planificador.getNombre()+" "+planificador.getApellido());
        System.out.println("FECHA DE REGISTRO: "+fechaSolicitud);
        System.out.println("TIPO EVENTO: "+tipoEvento);
        System.out.println("FECHA DEL EVENTO: "+new SimpleDateFormat("dd/MM/yyyy").format(fechaEvento));
        System.out.println("PRECIO BASE: "+precioBase);
        
        return null;
    
    }
    /**
     * Genera el dato de la id de la solicitud
     * @return numero
     */
    private String generarId(){
        int id=0;
        String numero="";
        for(int i=0;i<5;i++){
            id= (int) Math.floor(Math.random() * 10);;
            numero=numero+Integer.toString(id);
        
        }
        return numero;
    }
    /**
     * Dada una solicitud, devuelve un archivo con los datos de la solicitud
     * 
     * @param solicitud 
     */
    public static void crearSolicitud(Solicitud solicitud){
        
        FileWriter fichero = null;
        BufferedWriter bw = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter("solicitud.txt", true);
            bw = new BufferedWriter(fichero);
            
            String fechaSolicitud = solicitud.getFechaSolicitud().getDate()+"/"+
                    solicitud.getFechaSolicitud().getMonth()+"/"+(solicitud.getFechaSolicitud().getYear()+1900);
            int mes=0;
            if (solicitud.getFechaEvento().getMonth()>0 && solicitud.getFechaEvento().getMonth()<=11)
                mes=solicitud.getFechaEvento().getMonth();
            else if(solicitud.getFechaEvento().getMonth()==0)
                mes=12;
            
            String fechaEvento = solicitud.getFechaEvento().getDate()+"/"+mes+"/"+
                    (solicitud.getFechaEvento().getYear()+1900);
            
            String[] datos = {solicitud.getId(), solicitud.getCliente().getNombre(),
                solicitud.getPlanificador().getNombre(),fechaSolicitud,
            fechaEvento,solicitud.getEstadoSolicitud().toString()};
            
            
            String linea=linea="\n"+datos[0];;

            for (int i = 1; i < datos.length; i++) {
                
                linea += ","+datos[i];
            }
            System.out.println(linea);
            bw.write(linea);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Nuevamente aprovechamos el finally para 
                // asegurarnos que se cierra el fichero.
                if (null != fichero) {
                    //fichero.close();
                    bw.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

  }
    
    
}
    
