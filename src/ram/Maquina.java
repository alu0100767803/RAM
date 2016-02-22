/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ram;


import java.io.*;
import static java.lang.System.exit;
import java.util.*;

/**
 *
 * @author Jorge
 */
public class Maquina {
    
   private ArrayList<Fila> ram;
   private boolean bucle;
   private int acumulador;
   private CEntrada c_entrada;
   private CSalida c_salida;
   private Registros registros;
   
  public Maquina(String cadena, String c_e) throws FileNotFoundException, IOException{
    bucle = false; // variable que va a mantener el bucle activo
    acumulador = 0; //acumulador de nuestra maquina con el que vamos a trabajar
    c_entrada = new CEntrada(c_e); //cadena de lectura
    c_salida = new CSalida(); // cadena de escritura
    registros = new Registros(); //registros de nuestro programa
    ram = new ArrayList<Fila>(); // Nuestro programa RAM cargado en memoria
    
    //registros auxiliares para pasar a la clase Fila
    String aux_etq = null;
    String aux_ins = null;
    String aux_dir = null;
    int aux_ele = 0;
    
    BufferedReader reader = new BufferedReader(new FileReader(cadena));
    String linea;
   
    while((linea = reader.readLine()) != null){
     String [] token = linea.split("\\s+");
     for ( int i = 0; i < token.length; i++){
       if ((i < token.length-1) && (token[i+1].equals(":"))) //Comprueba si es etiqueta
         aux_etq = new String(token[i]);
       else if ( (!token[i].equals(":")) && (i < token.length-1)) //COmprueba si es instruccion
         aux_ins = new String(token[i]);
       
       else if ((i == token.length-1) && (!token[i].equals(":"))){ //Comprueba si es un numero o simbolo
         if (token[i].equals("HALT"))
           aux_ins = new String(token[i]);
         else if ((aux_ins.equals("JUMP")) || (aux_ins.equals("JZERO")) || (aux_ins.equals("JGTZ")))
           aux_dir = new String(token[i]);
         else{
           //divide el ultimo token en 2 si es necesario en direccionamiento y valor
           String prueba = token[i];
           char[] aux = prueba.toCharArray();
           if((aux[0] == '*') || (aux[0] == '=')){
             aux_dir = Character.toString(aux[0]);
             aux_ele =  Character.getNumericValue(aux[1]); //modificado
           }
           
           else
             aux_ele =Character.getNumericValue(aux[0]); //modificado
          }
       }
     }
     Fila aux = new Fila(aux_etq, aux_ins, aux_dir,aux_ele);
     ram.add(aux);
     aux_etq = null;
     aux_ins = null;
     aux_dir = null;
     aux_ele = 0;
    }
     
  }
  
  public void run() throws IOException{
    int aux = 0; // nos va indicar por que instruccion vamos en el programa
    while(!bucle){
      switch(ram.get(aux).get_ins()){
          case "LOAD":
              System.out.println("Estoy accediendo a load");
              load(aux);
              aux++;
              break;
          case "STORE":
              System.out.println("Estoy accediendo a store");
              store(aux);
              aux++;
              break;
          case "READ":
              System.out.println("Estoy accediendo a read");
              read(aux); 
              aux++;
              break;
          case "WRITE":
              System.out.println("Estoy entrando a write");
              write(aux); 
              aux++;
              break;
          case "ADD":
              add(aux); 
              aux++;
              break;
          case "SUB":
              System.out.println("Estoy accediendo a sub");
              sub(aux); 
              System.out.println("resta : " + acumulador);
              aux++;
              break;
          case "MUL":
              System.out.println("Estoy accediendo a mul");
              mul(aux); 
              System.out.println("mul : " + acumulador);
              aux++;
              break;
          case "DIV":
              div(aux); 
              aux++;
              break;
          case "HALT":
              System.out.println("Estoy accediendo a halt");
              halt();
              aux = 0;
              break;
          case "JUMP":
              System.out.println("Estoy accediendo a jump");
              aux = jump(aux); 
              break;
          case "JZERO":
              aux = jzero(aux); 
              break;
          case "JGTZ":
              System.out.println("Estoy accediendo a jgtz");
              aux = jgtz(aux); 
              break;
          default: 
              throw new IllegalArgumentException("Instruccion invalida");
      }
    }
   }
  
  public void load(int pos){
    if(ram.get(pos).get_dir() == null){
       acumulador = registros.get_registros(ram.get(pos).get_ele()-1);
    }
    else if(ram.get(pos).get_dir().equals("*")){
      int aux = registros.get_registros(ram.get(pos).get_ele()-1);
      acumulador = registros.get_registros(aux-1);
    
    }
    else if(ram.get(pos).get_dir().equals("=")){
      acumulador = ram.get(pos).get_ele();
    }
  }
  
  public void store(int pos){
    if(ram.get(pos).get_dir() == null){
      registros.set_registros(ram.get(pos).get_ele()-1, acumulador);
    }
    else if(ram.get(pos).get_dir().equals("*")){
      int aux = registros.get_registros(ram.get(pos).get_ele()-1);
      registros.set_registros(aux, acumulador);
    }
    else if(ram.get(pos).get_dir().equals("=")){
      System.out.println("ERROR: Guardando datos en registros de forma incorrecta");
      exit(0);
    }
  }
  
  public void read(int pos){
    if(ram.get(pos).get_dir() == null){
      registros.set_registros(ram.get(pos).get_ele()-1, c_entrada.read());
    }
    else if(ram.get(pos).get_dir().equals("*")){ //falta mejorar
      int aux = registros.get_registros(ram.get(pos).get_ele()-1);
      registros.set_registros(aux, c_entrada.read());
    }
    else if(ram.get(pos).get_dir().equals("=")){
      System.out.println("ERROR: Leyendo datos de forma incorrecta");
      exit(0);
    }
  }
  
  public void write(int pos){
    if(ram.get(pos).get_dir() == null){
      int aux = registros.get_registros(ram.get(pos).get_ele()-1);
      System.out.println(aux);
      c_salida.write(aux);  
    }
    else if(ram.get(pos).get_dir().equals("*")){ //falta mejorar
      int aux = registros.get_registros(ram.get(pos).get_ele()-1);
      c_salida.write(registros.get_registros(aux-1));
    }
    else if(ram.get(pos).get_dir().equals("=")){
      c_salida.write(ram.get(pos).get_ele());
    }     
  }
  
  public void add(int pos){
    if(ram.get(pos).get_dir() == null){
      int aux = registros.get_registros(ram.get(pos).get_ele());
      acumulador = acumulador + aux;
    }
    else if(ram.get(pos).get_dir().equals("*")){ //falta mejorar
      int aux = registros.get_registros(ram.get(pos).get_ele()-1);
      acumulador = acumulador + registros.get_registros(aux-1);
    }
    else if(ram.get(pos).get_dir().equals("=")){
      acumulador = acumulador + ram.get(pos).get_ele();
    }  
  }
  
  public void sub(int pos){
    if(ram.get(pos).get_dir() == null){
    int aux = registros.get_registros(ram.get(pos).get_ele()-1);
    acumulador = acumulador - aux;    
    }
    else if(ram.get(pos).get_dir().equals("*")){ //falta mejorar
      int aux = registros.get_registros(ram.get(pos).get_ele()-1);
      acumulador = acumulador - registros.get_registros(aux-1);      
    }
    else if(ram.get(pos).get_dir().equals("=")){
      acumulador = acumulador - ram.get(pos).get_ele();
      System.out.println(ram.get(pos).get_ele());
    }      
  }
  
  public void mul(int pos){
    if(ram.get(pos).get_dir() == null){
      int aux = registros.get_registros(ram.get(pos).get_ele()-1);
      acumulador = acumulador * aux;       
    }
    else if(ram.get(pos).get_dir().equals("*")){ //falta mejorar
      int aux = registros.get_registros(ram.get(pos).get_ele()-1);
      acumulador = acumulador * registros.get_registros(aux-1);      
    }
    else if(ram.get(pos).get_dir().equals("=")){
      acumulador = acumulador * ram.get(pos).get_ele();
    }        
  }
  
  public void div(int pos){
    if(ram.get(pos).get_dir() == null){
      int aux = registros.get_registros(ram.get(pos).get_ele()-1);
      acumulador = acumulador / aux;   
    }
    else if(ram.get(pos).get_dir().equals("*")){ //falta mejorar
      int aux = registros.get_registros(ram.get(pos).get_ele()-1);
      acumulador = acumulador / registros.get_registros(aux-1);      
    }
    else if(ram.get(pos).get_dir().equals("=")){
      acumulador = acumulador / ram.get(pos).get_ele();
    }        
  }
  
  public void halt() throws IOException{
    bucle = true;
    c_salida.write_fichero();
  }
  
  public int jump(int pos){
    int aux = 0;
    for(int i = 0; i < get_size(); i++){
      if(ram.get(i).get_etq() != null){
        if (ram.get(pos).get_dir().equals(ram.get(i).get_etq()))
          aux = i;
      
      } 
    }
    return aux;
  }
  
  public int jzero(int pos){
    if (acumulador == 0){
      int aux = 0;
      for(int i = 0; i < get_size(); i++){
        if((ram.get(i).get_etq() != null)){
          if (ram.get(pos).get_dir().equals(ram.get(i).get_etq()))
            aux = i;
        }
      }
      return aux;
    }
    else{ 
      pos++;
      return pos;
    }
      
  }
  
  public int jgtz(int pos){
    if (acumulador > 0){
      int aux = 0;
      for(int i = 0; i < get_size(); i++){
        if((ram.get(i).get_etq() != null)){
          if (ram.get(pos).get_dir().equals(ram.get(i).get_etq())){
              aux = i;
          }
        }
      }
      System.out.println(aux);
      return aux;
    }
    else{ 
      pos++;
      return pos;
    }    
  }
  
  public int get_size()
  {
   return ram.size();
  }
  public Fila get_fila(int index){
   return ram.get(index);
  }
    
}
