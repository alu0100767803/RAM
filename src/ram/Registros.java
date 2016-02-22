/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ram;

import java.util.*;

/**
 *
 * @author Jorge
 */
public class Registros {
    
    private ArrayList<Integer> registros;
    
    public Registros(){
      registros = new ArrayList<Integer>();
    }
    
    public void set_registros(int pos, Integer elem){
      registros.add(pos,elem);
    }
    
    public int get_registros(int pos){
      return registros.get(pos);
    }
    
    public int get_size(){
      return registros.size();
    }
   

    
}
