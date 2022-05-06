
package ImageProcessing.NonLineaire;

/**
 *
 * @author alegal200
 */
public class MorphoElementaire {
 
 public static int[][] erosion(int [][] image,int tailleMasque){
 return MorphoComplexe.filtreDeRangN(image, tailleMasque, 0);
 }
 public static int[][] dilatation(int [][] image,int tailleMasque){
  return MorphoComplexe.filtreDeRangN(image, tailleMasque, tailleMasque*tailleMasque-1);
 }  
 public static int[][] ouverture(int [][] image,int tailleMasque){
     return erosion(dilatation(image ,tailleMasque),tailleMasque);
 }
 public static int[][] fermeture(int [][] image,int tailleMasque){
    return dilatation(erosion(image ,tailleMasque),tailleMasque); 
 }
 
}
