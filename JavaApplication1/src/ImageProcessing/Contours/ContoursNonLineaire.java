/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ImageProcessing.Contours;

import static ImageProcessing.NonLineaire.MorphoElementaire.*;

/**
 *
 * @author alexg
 */
public class ContoursNonLineaire {
   
 public static int[][] gradientErosion(int[][] image) {
     return multimg(image , erosion(image,3),-1.0 );}
  
 public static int[][] gradientDilatation(int[][] image) {
     return  multimg( dilatation(image,3) ,image ,-1.0)   ;}

 public static int[][] gradientBeucher(int[][] image) {
     return   multimg( dilatation(image,3), erosion(image,3),-1.0 );}
 
 public static int[][] laplacienNonLineaire(int[][] image) {
     return multimg( gradientDilatation(image) , gradientErosion(image) , -1.0);}
 
    
 // this function can add or sustrate both img if mul  = 1 or -1 
 public static int[][] multimg(int [][]img1,int[][] img2,double mult ){
     // better if we verify the size 
     try{
      int img [][] = new int [img1.length] [img1[0].length];
     for (int i = 0; i < img1.length; i++) {
         for (int j = 0; j < img1[0].length; j++) {
             img[i][j]= (int) (img1[i][j]+ mult*img2[i][j] ) ;
             if( img[i][j] > 255)
                 img[i][j] = 255 ;
             if( img[i][j] <0)
                 img[i][j] = 0 ;
         }
     }
     return img;
     }catch(Exception e){
         System.out.println("error"+e);
         return null ;
     }
 }

    public static int[][] black(int[][] img1, int[][] img2) {
         try{
      int img [][] = new int [img1.length] [img1[0].length];
     for (int i = 0; i < img1.length; i++) {
         for (int j = 0; j < img1[0].length; j++) {
             if( img2[i][j] != 0 )   
             img[i][j]=   img1[i][j]  ;
            
         }
     }
     return img;
     }catch(Exception e){
         System.out.println("error"+e);
         return null ;
     }
    
    
    }
    
}
