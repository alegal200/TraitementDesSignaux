/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication1;

import isilimageprocessing.IsilImageProcessing;

/**
 *
 * @author alexg
 */
public class JavaApplication1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("hey");
        System.out.println("2");
        for (int i = 0; i < 20; i++) {
            System.out.println( (int)( Math.random()*100/3 +100) +"," +(int)( Math.random()*100/3 +20) +",1");
        }
        //System.out.println("->"+n.toString());
        IsilImageProcessing img = new IsilImageProcessing() ;
        img.show();
                
        }

    
    
}
