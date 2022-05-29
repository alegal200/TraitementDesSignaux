
package ImageProcessing.NonLineaire;

import java.util.Arrays;

/**
 *
 * @author alexg
 */
public class MorphoComplexe {
    
    
public static int[][] filtreMedian(int[][] image, int tailleMasque) {
    
return filtreDeRangN(image , tailleMasque , tailleMasque*tailleMasque/2 +1 );
}

public static int[][] filtreDeRangN(int[][] image, int tailleMasque, int rang) {
int rayon_Masque = (tailleMasque-1)/2 ;
       int img_Sortie [][] = new int[image.length][image[0].length];
       int image_Etendue [][] = new int[image.length+2 *rayon_Masque ][image[0].length +2 *rayon_Masque ];
        // copier l image dans l image étendue 
            for (int i = 0; i < image.length; i++) {
                for (int j = 0; j <image[0].length; j++) {
                    image_Etendue [i+rayon_Masque][j+rayon_Masque] = image[i][j];
                }
            }
                
                
                
        for (int i = rayon_Masque; i < image_Etendue.length - rayon_Masque ; i++) {
         for (int j = rayon_Masque; j < image_Etendue[0].length - rayon_Masque ; j++) {
             // pour chaque pixel 
                 int pos_X_Masque = 0 ;
                 int val_Pixel[] = new int[tailleMasque*tailleMasque] ;
                 int pos = 0 ;
                 for (int k = i-rayon_Masque; k < i+rayon_Masque +1 ; k++) {
                     int pos_Y_Masque = 0 ;
                     for (int l = j-rayon_Masque; l < j+rayon_Masque+1; l++) {
                         val_Pixel[pos] = image_Etendue[k][l] ;
                        pos_Y_Masque++;
                        pos ++;
                     }
                     pos_X_Masque++;
             }     
             val_Pixel = trie( val_Pixel);
              img_Sortie [i-rayon_Masque][j-rayon_Masque]  = ( int ) val_Pixel[rang] ;

         }
     }


     return img_Sortie ;

}

private static int[] trie(int[] vv) {
    int v []= vv ;
    Arrays.sort(v);
     return v ;
    }


//: réalise la dilatation géodésique (de taille nbIter) de l’image 
//conditionnellement au masque géodésique, nbIter étant plus grand ou égal à 1.
public static int[][] dilatationGeodesique(int[][] image,int[][] masqueGeodesique, int nbIter){
    if(nbIter < 1 )
        return null ; 
   int img_Sortie [][] = image ; 
  
    for (int i = 0; i < nbIter; i++) {
          // prend le filtre on le dilate et on
       img_Sortie = MorphoElementaire.dilatation(img_Sortie,3);
       img_Sortie = intersect(img_Sortie, masqueGeodesique );//i
    }
    
    
    
    return img_Sortie ; }
 
//  : réalise la reconstruction géodésique de l’image 
// conditionnellement au masque géodésique.
public static int[][] reconstructionGeodesique(int[][] image, int[][] masqueGeodesique) {
    int imgprec [][] = image ;
    int imgact  [][] = image ; 
    int i = 1 ;
   do{  
        imgprec = imgact ;
        imgact = dilatationGeodesique( imgprec , masqueGeodesique , 1);
        i++;
    }while(! comp(imgprec , imgact));
    
   return imgact ;
   

}

private static int[][] intersect(int[][] imgAVer, int[][] imageReel) { // pt point , grosse img
   int img_sortie [][] = new int [ imgAVer.length][ imgAVer[0].length] ;
    for (int i = 0; i < imgAVer.length; i++) {
        for (int j = 0; j < imgAVer[0].length; j++) {
            if(imgAVer[i][j] == imageReel[i][j])
                img_sortie[i][j] = imageReel[i][j] ;
            else
            img_sortie[i][j] = 0 ;
        }
        
    }
  
    
    return img_sortie ;
}

private static boolean comp(int[][] imgprec, int[][] imgact) {
        for (int i = 0; i < imgprec.length; i++) {
            for (int j = 0; j < imgprec[0].length; j++) {
                if(imgprec[i][j]!=  imgact[i][j]){
                    return false ;
                }
                    
            }
  
        }
        
        return true ;
        
    }
      

    
}
