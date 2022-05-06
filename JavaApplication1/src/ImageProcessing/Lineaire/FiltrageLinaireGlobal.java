
package ImageProcessing.Lineaire;

import ImageProcessing.Complexe.Complexe;
import ImageProcessing.Complexe.MatriceComplexe;
import ImageProcessing.Fourier.Fourier;

/**
 *
 * @author alegal200
 */
public class FiltrageLinaireGlobal {
    /*
    first methods   are Fouriers high and low 
    other are butterworthmethodes 
    all methodes send int [][] in black and white 
    
    /*
    *réalisant le filtrage passe-bas de l’image en passant par sa transformée de Fourier et 
    * en utilisant une fenêtre de forme circulaire de rayon égal à la fréquence de coupure.
    */
    public static int[][] filtrePasseBasIdeal(int[][] image,int frequenceCoupure) { 
        MatriceComplexe mat;
        int [] [] imgFinal = image ; 
        double [] []metd  = new double [image.length][image[0].length];
            for (int i = 0; i < image.length; i++) {
                for (int j = 0; j < image[0].length; j++) {
                    metd[i][j] =  image[i][j] ;
                }
        }
        
           mat = Fourier.Fourier2D(metd);
           mat = Fourier.decroise(mat);
           for (int i = 0; i < image.length; i++) {
                for (int j = 0; j < image[0].length; j++) {
                    
                    // distance centre     distance x val centre  ² + distance centre y ² 
                    double dist = Math.pow((image.length/2-i),2) +Math.pow((image[0].length/2-j),2)  ;
                    
                    
                    if( dist < Math.pow(frequenceCoupure,2))
                        mat.set(i, j, (mat.get(i,j)));  
                    else
                         mat.set(i, j, new Complexe(0.0,0.0)); 
                    
                    
                }
                    
                }
           mat = Fourier.decroise(mat);
           mat = Fourier.InverseFourier2D(mat);
              for (int i = 0; i < image.length; i++) {
                for (int j = 0; j < image[0].length; j++) {
                    imgFinal[i][j] =  (int) mat.get(i, j).getPartieReelle() ;
                }
        }
              for (int i = 0; i < imgFinal.length; i++) {
            for (int j = 0; j < imgFinal[0].length; j++) {   
                 if(imgFinal[i][j] > 256 )
                     imgFinal[i][j] = 255 ;
                 if(imgFinal[i][j] < 0)
                     imgFinal[i][j] = 0 ;
            }
        } 
              
              
        return imgFinal ;   
    }
    
    /*
    *réalisant le filtrage passe-haut de l’image en passant par sa transformée de Fourier et 
    *    en utilisant une fenêtre de forme circulaire de rayon égal à la fréquence de coupure.
    */
    public static int[][] filtrePasseHautIdeal(int[][] image,int frequenceCoupure)   { 
    
     MatriceComplexe mat;
        int [] [] imgFinal = image ; 
        double [] []metd  = new double [image.length][image[0].length];
            for (int i = 0; i < image.length; i++) {
                for (int j = 0; j < image[0].length; j++) {
                    metd[i][j] =  image[i][j] ;
                }
        }
        
           mat = Fourier.Fourier2D(metd);
           mat = Fourier.decroise(mat);
           for (int i = 0; i < image.length; i++) {
                for (int j = 0; j < image[0].length; j++) {
                    
                    // distance centre     distance x val centre  ² + distance centre y ² 
                    double dist = Math.pow((image.length/2-i),2) +Math.pow((image[0].length/2-j),2)  ;
                    if( dist >= Math.pow(frequenceCoupure,2))//
                        mat.set(i, j, (mat.get(i,j)));  
                    else
                         mat.set(i, j, new Complexe(0.0,0.0));  
                    
                     
                }
                    
                }
           mat = Fourier.decroise(mat);
           mat = Fourier.InverseFourier2D(mat);
              for (int i = 0; i < image.length; i++) {
                for (int j = 0; j < image[0].length; j++) {
                    imgFinal[i][j] =  (int) mat.get(i, j).getPartieReelle() ;
                }
        }
              
        for (int i = 0; i < imgFinal.length; i++) {
            for (int j = 0; j < imgFinal[0].length; j++) {
                 imgFinal[i][j] += 128 ;
                 if(imgFinal[i][j] > 256 )
                     imgFinal[i][j] = 255 ;
                 if(imgFinal[i][j] < 0)
                     imgFinal[i][j] = 0 ;
            }
        }
       
              for (int i = 0; i < imgFinal.length; i++) {
            for (int j = 0; j < imgFinal[0].length; j++) {   
                 if(imgFinal[i][j] > 256 )
                     imgFinal[i][j] = 255 ;
                 if(imgFinal[i][j] < 0)
                     imgFinal[i][j] = 0 ;
            }
        }  
              
              
              
        return imgFinal ;     
    
    }
    /*
     réalisant le filtrage passe-bas de l’image en passant par 
        sa transformée de Fourier et en utilisant la fonction de transfert de Butterworth 
        correspondante
    */
    public static int[][] filtrePasseBasButterworth(int[][] image,int frequenceCoupure,int ordre){ 
        
     MatriceComplexe mat;
        int [] [] imgFinal = image ; 
        double [] []metd  = new double [image.length][image[0].length];
            for (int i = 0; i < image.length; i++) {
                for (int j = 0; j < image[0].length; j++) {
                    metd[i][j] =  image[i][j] ;
                }
        }
        
           mat = Fourier.Fourier2D(metd);
           mat = Fourier.decroise(mat);
           for (int i = 0; i < image.length; i++) {
                for (int j = 0; j < image[0].length; j++) {
                    
                    // distance centre     distance x val centre  ² + distance centre y ² 
                    Complexe cplx = mat.get(i,j) ;
                    double attenuation  = butterworth(image.length/2-i ,image[0].length/2-j , ordre , -1*frequenceCoupure ) ;
                     cplx.setPartieImaginaire(cplx.getPartieImaginaire() *attenuation  );
                     cplx.setPartieReelle(cplx.getPartieReelle()*attenuation );
                     mat.set(i,j, cplx);

                 
                }
                    
                }
           mat = Fourier.decroise(mat);
           mat = Fourier.InverseFourier2D(mat);
              for (int i = 0; i < image.length; i++) {
                for (int j = 0; j < image[0].length; j++) {
                    imgFinal[i][j] =  (int) mat.get(i, j).getPartieReelle() ;
                }
        }
              
               for (int i = 0; i < imgFinal.length; i++) {
             for (int j = 0; j < imgFinal[0].length; j++) {
                 
                 if(imgFinal[i][j]>255)
                     imgFinal[i][j] = 255 ;
                 if(imgFinal[i][j]<0)
                     imgFinal[i][j]=0;
                }
            }
              
              
        return imgFinal ;  
    
    
    }
    /*
            : réalisant le filtrage passe-haut de l’image en passant par sa transformée de 
            Fourier et en utilisant la fonction de transfert de Butterworth correspondante.

    */
     public static int[][] filtrePasseHautButterworth(int[][] image,int frequenceCoupure, int ordre) {
      int[][] m = FiltrageLinaireGlobal.filtrePasseBasButterworth(image,(-1*frequenceCoupure), ordre);
      
         for (int i = 0; i < m.length; i++) {
             for (int j = 0; j < m[0].length; j++) {
                 m[i][j]+=128 ;
                 if(m[i][j]>255)
                     m[i][j] = 255 ;
                 if(m[i][j]<0)
                     m[i][j]=0;
             }
         }
      return m ;
     }  
     
     
     /*
      this function return the value of butterworth function you can chose passehigh or passelow ( in fuction ofthe sign of the radius
      if radius is negatif -> passe bas   
     */
     private static double butterworth(int u , int v, int ordre ,int radius){
         if(radius == 0)
             return 0.0 ;
         if(radius  > 0)
            return  1/( 1+ Math.pow( radius/Math.sqrt( (double)  (u*u+v*v))   , 2*ordre));
         else
            return 1/( 1+ Math.pow( Math.sqrt( (double)  (u*u+v*v))/(-1*radius)   , 2*ordre));
     }
}
