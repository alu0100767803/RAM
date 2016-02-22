package ram;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jorge
 */
public class Fila {
  private String etiqueta;
  private String instrucciones;
  private String direccionamiento;
  private int elemento;
  
  public Fila( String etq, String ins, String dir, int ele ){
    etiqueta = etq;
    instrucciones = ins;
    direccionamiento = dir;
    elemento = ele;
  }
  
  public String get_etq(){
    return etiqueta;
  }
  
  public String get_ins(){
    return instrucciones;
  }
  
  public String get_dir(){
    return direccionamiento;
  }
  
  public int get_ele(){
    return elemento;
  }
    
}
