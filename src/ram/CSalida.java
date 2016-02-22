/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ram;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 *
 * @author Jorge
 */
public class CSalida {
    
  private ArrayList<Integer> cadenas;
  private int cescritura;
  
  public CSalida(){
    cadenas = new ArrayList<Integer>();
    cescritura=0;
  }
  
  public void write(Integer elem){
      cadenas.add(cescritura, elem);
      cescritura++;
    
  }
  
  public int get_csalida(int pos){
    return cadenas.get(pos);
  }
  
  public void write_fichero() throws IOException{
    FileWriter fichero = new FileWriter("resulatdo.txt");  
      for (int i = 0; i < cadenas.size(); i++)
        fichero.write(cadenas.get(i) + " ");  
      fichero.close();
  }
}
