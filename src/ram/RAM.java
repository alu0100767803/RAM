/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ram;

import java.io.*;
import java.util.*;

/**
 *
 * @author Jorge
 */
public class RAM {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
            /*
            El ArrayList me funciono con esto
        
            Registros prueba = new Registros();
            prueba.set_registros(0,0);
            prueba.set_registros(1,2);
            prueba.set_registros(2,4);
            
            for(int i = 0; i<prueba.get_size(); i++)
            System.out.println(prueba.get_registros(i));
            CEntrada cadena = new CEntrada("Datos.txt");// args[0] en lugar de Datos.txt
            CSalida cadena1 = new CSalida();
            
            cadena1.write(20);
            cadena1.write(10);
            cadena1.write_fichero();
            for ( int i = 0; i < cadena.get_size(); i++ )
              System.out.println(cadena.read());
            
            
            /*String tok = "\\s : * =";
            StringTokenizer token = new StringTokenizer(tok);
            
            while (token.hasMoreTokens())
              System.out.println(token.nextToken());*/
        
        Maquina prueba = new Maquina("test2.ram", "Datos.txt");
        prueba.run();
        
    }
    
}
