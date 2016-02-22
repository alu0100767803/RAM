/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ram;

import static java.lang.System.exit;
import java.util.*;
import java.io.*;

/**
 *
 * @author Jorge
 */
public class CEntrada {
    
    private ArrayList<Integer> cadenae;
    private int clectura;
    
    public CEntrada(String cadena) throws FileNotFoundException, IOException{
     cadenae = new ArrayList<Integer>();
     clectura = 0;
     
     BufferedReader reader = new BufferedReader(new FileReader(cadena));
     String linea = reader.readLine();
     String [] token = linea.split("\\s");
     for ( int i = 0; i < token.length; i++)
       cadenae.add(new Integer(token[i]));
    }
    
    public int read(){
      if(clectura < cadenae.size()){  // En este bucle if puede que haya fallos de acceder a memoria
        int aux = cadenae.get(clectura);
        clectura++;
        System.out.println(aux);
        return aux;
      }
  
      else{
        System.out.println("Accediendo a valor inexistene");
        exit(0); //no se si puede salir asi del programa 
        return 0;// no se como arreglar para que salga con error, por que me esta devolviendo siempre un 0
      }
    }
    
    public int get_size() throws IOException{
      return cadenae.size();
    }
    
}
