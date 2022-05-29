
package ImageProcessing.Contours;

import ImageProcessing.Lineaire.FiltrageLineaireLocal;

/**
 *
 * @author alexg
 */



/*
here there are juste methode how use  FiltrageLineaireLocal.filtreMasqueConvolution 
    each one have it mask

*/



public class ContoursLineaire {
    
           /*: calculant le gradient de 
Prewitt de l’image. Si dir=1, le calcul du gradient se fera dans la direction horizontale. 
Si dir=2, le calcul du gradient se fera dans la direction verticale.*/   
    
public static int[][] gradientPrewitt(int[][] image,int dir){
    
    if (dir == 1 ){
        double mat [][] = new double  [3][3];
        mat[0][0] = -1/3.0;
        mat[1][0] = -1/3.0;
        mat[2][0] = -1/3.0;
        
        mat[0][1] = 0.0 ;
        mat[1][1] = 0.0 ;
        mat[2][1] = 0.0 ;
        
        mat[0][2] = 1/3.0;
        mat[1][2] = 1/3.0;
        mat[2][2] = 1/3.0;
        return   derniereRetouches( FiltrageLineaireLocal.filtreMasqueConvolution(image, mat));
                
    }
        
    else if(dir == 2){
        double mat [][] = new double  [3][3];
        mat[0][0] = -1/3.0;
        mat[0][1] = -1/3.0;
        mat[0][2] = -1/3.0;
        
        mat[1][0] = 0.0 ;
        mat[1][1] = 0.0 ;
        mat[1][2] = 0.0 ;
        
        mat[2][0] = 1/3.0;
        mat[2][1] = 1/3.0;
        mat[2][2] = 1/3.0;
        return  derniereRetouches( FiltrageLineaireLocal.filtreMasqueConvolution(image, mat)  );
    }
            
    else
    
    return null ;
}
  /*: calculant le gradient de 
Sobel de l’image. Si dir=1, le calcul du gradient se fera dans la direction horizontale. 
Si dir=2, le calcul du gradient se fera dans la direction verticale.*/
public static int[][] gradientSobel(int[][] image,int dir){
     if (dir == 1 ){
        double mat [][] = new double  [3][3];
        mat[0][0] = -1/4.0;
        mat[1][0] = -1/2.0;
        mat[2][0] = -1/4.0;
        
        mat[0][1] = 0.0 ;
        mat[1][1] = 0.0 ;
        mat[2][1] = 0.0 ;
        
        mat[0][2] = 1/4.0;
        mat[1][2] = 1/2.0;
        mat[2][2] = 1/4.0;
        return  derniereRetouches( FiltrageLineaireLocal.filtreMasqueConvolution(image, mat) );
                
    }
        
    else if(dir == 2){
        double mat [][] = new double  [3][3];
        mat[0][0] = -1/4.0;
        mat[0][1] = -1/2.0;
        mat[0][2] = -1/4.0;
       
        mat[1][0] = 0.0 ;
        mat[1][1] = 0.0 ;
        mat[1][2] = 0.0 ;
        
        mat[2][0] = 1/4.0;
        mat[2][1] = 1/2.0;
        mat[2][2] = 1/4.0;
        return derniereRetouches( FiltrageLineaireLocal.filtreMasqueConvolution(image, mat));
    }
            
    else
    
    return null ;
}
         /* : calculant le laplacien (formule 1.52 des 
notes de cours) de l’image.*/
 public static int[][] laplacien4(int[][] image){
     double mat [][] = new double  [3][3];
        mat[0][0] = 0.0;
        mat[0][1] = 1.0/1.0;
        mat[0][2] = 0.0;
       
        mat[1][0] = 1.0/1.0 ;
        mat[1][1] = -4.0/1.0 ;
        mat[1][2] = 1.0/1.0 ;
        
        mat[2][0] = 0.0;
        mat[2][1] = 1.0/1.0;
        mat[2][2] = 0.0;
        return  derniereRetouches( FiltrageLineaireLocal.filtreMasqueConvolution(image, mat));
 }
 /*
         : calculant le laplacien (formule 1.53 des 
notes de cours) de l’image.*/
 
 public static int[][] laplacien8(int[][] image){
     
        double mat [][] = new double  [3][3];
        mat[0][0] = 1.0/1.0;
        mat[0][1] = 1.0/1.0;
        mat[0][2] = 1.0/1.0;
       
        mat[1][0] = 1.0/1.0 ;
        mat[1][1] = -8.0/1.0 ;
        mat[1][2] = 1.0/1.0 ;
        
        mat[2][0] = 1.0/1.0;
        mat[2][1] = 1.0/1.0;
        mat[2][2] = 1.0/1.0;
        return  derniereRetouches( FiltrageLineaireLocal.filtreMasqueConvolution(image, mat));
 }
     
     
 
public static int[][]derniereRetouches(int [][] a ){
    int img [][] = new int [a.length] [a[0].length];
    for (int i = 0; i < a.length; i++) {
        for (int j = 0; j < a[0].length; j++) {
            img[i][j]= 128+ a[i][j];
            // warning you can remove this for laplacien maybe 
            if(img[i][j]< 0)
                img[i][j] =0 ;
            if(img[i][j]> 255)
                img[i][j] = 255 ;
            
        }
    }
    
    return img ;
}      
         
   
    
    
    
}
