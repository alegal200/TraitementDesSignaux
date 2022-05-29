/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ImageProcessing.Seuillage;

import java.util.ArrayList;

/**
 *
 * @author alexg
 */
public class Seuillage {
 
 public static int[][] seuillageSimple(int[][] image, int seuil) { 
        
        int img [][] = new int [image.length] [image[0].length];
       for (int i = 0; i < image.length; i++) {
        for (int j = 0; j < image[0].length; j++) {
            
            if(image[i][j] < seuil )
            img[i][j] = 0 ;
            else 
            img[i][j] = 255 ;
            
        }}
    
       return img ; 
    }
 public static int[][] seuillageDouble(int[][] image,int seuil1, int seuil2) { 
     
     
     int img [][] = new int [image.length] [image[0].length];
       for (int i = 0; i < image.length; i++) {
        for (int j = 0; j < image[0].length; j++) {
            
            if(image[i][j] < seuil1 )
                img[i][j] = 0 ;
            else if (image[i][j] < seuil2 )
                img[i][j] = 128 ;
            else
                 img[i][j] = 255 ;
            
        }}
  return img ; 
 
 }
 public static int[][] seuillageAutomatique(int[][] image) {
     
     //1. Choisir un seuil T initial (moyenne, médiane, ...)
      
     
     ArrayList arg1 = new ArrayList<>() ;   
     ArrayList arg2 = new ArrayList<>() ;  
     int T = 128 ;
     int Tpreced = 255 ;
     while( Tpreced > T ){ // google
     
     
//2. On obtient alors 2 groupes de pixels : le groupe G1  ontenant les pixels d'intensité supérieure à T et le groupe G2  ontenant les pixels d'intensité inférieure à T .
         for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[0].length; j++) {
                if(image[i][j]> T )
                    arg1.add(image[i][j]);
                else
                   arg2.add( image[i][j]); 
            }
        }


//3. Caluler les moyennes des niveaux de gris sur les deux groupes de pixels G1 et G2 ; eifournit les deux moyennes µ1 et µ2.
    
        double tmp1 =0, tmp2 =0 ;
            
            for (int i = 0; i < arg1.size() ; i++) {
             tmp1 += ( (int) arg1.get(i))/(arg1.size()*1.0);
         }
            for (int i = 0; i < arg2.size() ; i++) {
             tmp2 += ( (int) arg2.get(i))/(arg2.size()*1.0);
         }
            arg1.clear();
            arg2.clear();
            System.out.println("tmp 1 "+tmp1+"   2 "+tmp2);
//4. Mettre à jour la valeur du seuil
        
        
        
        
//T → µ1 + µ2 
//        2
     Tpreced = T ;
     T = (int) (tmp1+tmp2 /2);
         System.out.println("T "+T+"T preced "+Tpreced);
     
// 5. Reommener à l'étape 1 jusqu'à e que la valeur du seuil T onverge vers une valeur constante.     
     }   
     
     
     
     
     
     
     
     
     
     return seuillageSimple(image, T);
 
 }
}
