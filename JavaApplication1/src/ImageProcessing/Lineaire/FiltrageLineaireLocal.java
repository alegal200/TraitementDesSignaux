
package ImageProcessing.Lineaire;

/**
 *
 * @author alexg
 */
public class FiltrageLineaireLocal {
    
    
    /*
        réalisant le filtrage local de l’image en utilisant le masque de convolution, celui-ci 
        étant une matrice carrée quelconque de dimension nxn, avec n impair

    
    */
    public static int[][] filtreMasqueConvolution(int[][] image, double [][] masque) {
       int rayon_Masque = (masque.length-1)/2 ;
       int img_Sortie [][] = new int[image.length][image[0].length];
       int image_Etendue [][] = new int[image.length+2 *rayon_Masque ][image[0].length +2 *rayon_Masque ];
        // copier l image dans l image étendue 
            for (int i = 0; i < image.length; i++) {
                for (int j = 0; j <image[0].length; j++) {
                    image_Etendue [i+rayon_Masque][j+rayon_Masque] = image[i][j];
                }
        }
        //  bord gauche
        int tmp = rayon_Masque*2-1;
        for (int i = 0; i < rayon_Masque; i++) {
            for (int j = 0; j < image_Etendue[0].length; j++) {
                image_Etendue [i][j] =  image_Etendue [tmp][j];
            }
            tmp--;
        }
       // bord haut
        tmp = rayon_Masque*2-1;
         for (int j = 0; j < rayon_Masque; j++) {
            for (int i = 0; i < image_Etendue.length; i++) {         
                image_Etendue [i][j] =  image_Etendue [i][tmp];
            }
            tmp--;
        }
        // bord bas  
       tmp = image_Etendue[0].length-(rayon_Masque*2);
         for (int j = image_Etendue[0].length-rayon_Masque; j < image_Etendue[0].length; j++) {
            for (int i = 0; i < image_Etendue.length; i++) {         
                image_Etendue [i][j] =  image_Etendue [i][tmp];
            }
            tmp++;
        }  
        // bord droitte 
        tmp = image_Etendue.length-(rayon_Masque*2);
        for (int i = image_Etendue.length-rayon_Masque ; i < image_Etendue.length ; i++) {
            for (int j = 0; j < image_Etendue[0].length; j++) {
                image_Etendue [i][j] =  image_Etendue [tmp][j];
            }
            tmp++;
        }    
          
        // ! normalement il reste a traiter les coins pour avoir un résultat parfait
        
            
    //    System.out.println("la matrice a été copiée");
     //   System.out.println("rayon masque = "+rayon_Masque);
            // parcour de l image 
        for (int i = rayon_Masque; i < image_Etendue.length - rayon_Masque ; i++) {
            
            for (int j = rayon_Masque; j < image_Etendue[0].length - rayon_Masque ; j++) {
                // pour chaque pixel 
                   // System.out.println("[ "+i+"] ["+j+"]");                  
                    int pos_X_Masque = 0 ;
                    double val_Pixel = 0 ;
                    
                    for (int k = i-rayon_Masque; k < i+rayon_Masque +1 ; k++) {
                        int pos_Y_Masque = 0 ;
                        for (int l = j-rayon_Masque; l < j+rayon_Masque+1; l++) {
                            //System.out.println("/[ "+k+"] ["+l+"]\");
                            val_Pixel += masque[pos_X_Masque][pos_Y_Masque]*image_Etendue[k][l] ;
                           // System.out.println("mat"+masque[pos_X_Masque][pos_Y_Masque]+"    imgEt"+image_Etendue[i][j]+" = >"+val_Pixel);
                            pos_Y_Masque++;
                        }
                        pos_X_Masque++;
                }     
                 img_Sortie [i-rayon_Masque][j-rayon_Masque]  = ( int ) val_Pixel ;
                     
            }
        }
            

        return img_Sortie ;
    }
    /*
                réalisant un 
           filtrage moyenneur de l’image avec un masque de convolution de dimension 
           tailleMasque x tailleMasque. Cette méthode peut appeler la méthode 
           filtreMasqueConvolution avec le masque adéquat.

    */
    public static int[][] filtreMoyenneur(int[][] image, int tailleMasque){ 
      
        if(tailleMasque%2== 0)
            return null ;
        double mat [][] = new  double[tailleMasque][tailleMasque];
        double val = 1.0/(tailleMasque*tailleMasque) ; // ! 1.0 sinon cast int/int 
        for (int i = 0; i < tailleMasque; i++) {
            for (int j = 0; j < tailleMasque; j++) {
                mat[i][j]=val ;
            }
        }
        //System.out.println("appel function du haut");
        return filtreMasqueConvolution(image, mat) ;
        
        }
    
}
