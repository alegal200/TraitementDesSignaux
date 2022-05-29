/*
 * Histogramme.java
 *
 * Created on 23 septembre 2007, 20:22
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package ImageProcessing.Histogramme;

/**
 *
 * @author Jean-Marc
 */
public class Histogramme {

    public static int[] Histogramme256(int mat[][]) {
        int M = mat.length;
        int N = mat[0].length;
        int histo[] = new int[256];

        for (int i = 0; i < 256; i++) {
            histo[i] = 0;
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if ((mat[i][j] >= 0) && (mat[i][j] <= 255)) {
                    histo[mat[i][j]]++;
                }
            }
        }

        return histo;
    }

    public static int minimum(int[][] image) {
        int min = 255;

        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[i].length; j++) {

                if (image[i][j] < min) {
                    min = image[i][j];
                }

            }
        }

        return min;
    }

    public static int maximum(int[][] image) {
        int max = 0;

        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[i].length; j++) {

                if (image[i][j] > max) {
                    max = image[i][j];
                }

            }
        }

        return max;
    }

    public static int luminance(int[][] image) {
        //La luminance est la moyenne de tout les pixel
        int moy = 0;

        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[i].length; j++) {
                moy = moy + image[i][j];
            }
        }

        moy = moy / (image.length * image[0].length);
        return moy;

    }

    public static double contraste1(int[][] image) {
        int luminance = luminance(image);

        double contraste = 0;

        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[i].length; j++) {
                contraste = contraste + Math.pow(image[i][j] - luminance, 2);
            }
        }

        contraste = contraste / (image.length * image[0].length);
        contraste = Math.sqrt(contraste);

        return contraste;
    }

    public static double contraste2(int[][] image) {
        double max = (double) maximum(image);
        double min = (double) minimum(image);

        double contraste = (max - min) / (max + min);

        return contraste;
    }

    public static int[][] rehaussement(int[][] image, int[] courbeTonale) {

        int[][] ImageRehaussed = new int[image.length][image[0].length];

        for (int i = 0; i < ImageRehaussed.length; i++) {
            for (int j = 0; j < ImageRehaussed[i].length; j++) {
                ImageRehaussed[i][j] = courbeTonale[image[i][j]];

              //  System.out.println(image[i][j] + "==>" + ImageRehaussed[i][j]);
            }
        }

        return ImageRehaussed;
    }

    public static int[] creeCourbeTonaleLineaire() {
        //L'indice du vecteur correspond a l'intensit� I originale
        int[] courbeTonale = new int[256];

        for (int i = 0; i < courbeTonale.length; i++) {

            courbeTonale[i] = (255 * (i - 0)) / (255 - 0);

            //System.out.println("Tonal:"+courbeTonale[i]);
        }

        return courbeTonale;
    }

    public static int[] creeCourbeTonaleLineaireSaturation(int smin, int smax) {
        //L'indice du vecteur correspond a l'intensit� I originale
        int[] courbeTonale = new int[256];

        for (int i = 0; i < courbeTonale.length; i++) {

            if (i < smin) {
                courbeTonale[i] = 0;
            } else if (smax < i) {
                courbeTonale[i] = 255;
            } else {
                courbeTonale[i] = (255 * (i - smin)) / (smax - smin);
            }

            //System.out.println("Tonal:"+courbeTonale[i]);
        }

        return courbeTonale;
    }

    public static int[] creeCourbeTonaleGamma(double gamma) {
        //L'indice du vecteur correspond a l'intensit� I originale
        int[] courbeTonale = new int[256];

        for (int i = 0; i < courbeTonale.length; i++) {

            courbeTonale[i] = (int) (255 * Math.pow((i / 255.0), (1 / gamma)));

            //System.out.println("Tonal:"+courbeTonale[i]);
        }

        return courbeTonale;
    }

    public static int[] creeCourbeTonaleNegatif() {
        //L'indice du vecteur correspond a l'intensit� I originale
        int[] courbeTonale = new int[256];

        for (int i = 0; i < courbeTonale.length; i++) {

            courbeTonale[i] = 255 - i;

            //System.out.println("Tonal:"+courbeTonale[i]);
        }

        return courbeTonale;
    }

    public static int[] creeCourbeTonaleEgalisation(int[][] image) {

        //Calcul de l'histogramme
        int histo[];
        histo = Histogramme.Histogramme256(image);

        //Normalisation de l'histogramme
        double histo_normalise[] = new double[256];
        for (int i = 0; i < 256; i++) {
            histo_normalise[i] = (double) histo[i] / (image.length * image[0].length) ;
        }

        //Caclul histogramme des frequences cumulees sur l'histo normalis�
        double histo_cumule[] = new double[256];
        for (int i = 0; i < 256; i++) {
            for (int l = 0; l <= i; l++) {
                histo_cumule[i] = histo_cumule[i] + histo_normalise[l];
            }
        }

        //Transformation des niveaux de gris suivant la loi
        int[] courbeTonale = new int[256];

        for (int i = 0; i < courbeTonale.length; i++) {

            courbeTonale[i] = (int) (255*histo_cumule[i]);

            //System.out.println("Tonal:"+courbeTonale[i]);
        }

        return courbeTonale;
    }

}
