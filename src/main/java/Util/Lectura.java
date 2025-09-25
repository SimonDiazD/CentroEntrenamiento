/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import java.util.Scanner;

/**
 *
 * @author juancamilo.gonzalez
 */
public class Lectura {
    private Scanner entrada = new Scanner (System.in);
    
    public String leerString(String mensaje){
        System.out.println(""+mensaje);
        return entrada.nextLine();
    }
    
    public int leerInt(String mensaje){
        System.out.println(""+mensaje);
        return entrada.nextInt();  
    }
    
    public float leerFloat(String mensaje){
        System.out.println(""+mensaje);
        return entrada.nextFloat();
    }
    
    public double leerDouble(String mensaje){
        System.out.println(""+mensaje);
        return entrada.nextDouble();
    }
    
    public Boolean leerBoolean(String mensaje){
        System.out.println(""+mensaje);
        return entrada.nextBoolean();
        
    }
}