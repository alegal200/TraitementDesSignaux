package ImageProcessing.Histogramme;


import CImage.CImageNG;
import CImage.Exceptions.CImageNGException;
import CImage.Observers.JLabelBeanCImage;
import CImage.Observers.JLabelCImage;
import CImage.Observers.JPanelCImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */

/**
 *
 * @author Ghali
 */
public class Transfo_dialog extends javax.swing.JDialog {


    private CImageNG imageNG;
    private JLabelCImage lab = new JLabelCImage();
 
    
    /**
     * Creates new form Transfo_dialog
     */
    public Transfo_dialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        imageNG = null;
    }
    
    public Transfo_dialog(java.awt.Frame parent, boolean modal,int[][] image) {
        super(parent, modal);
        initComponents();
       
        int histo[];
        histo = Histogramme.Histogramme256(image);
        
        try {  
            imageNG = new CImageNG(image) ;
            lab.setCImage(imageNG);
            jScrollPane1.setViewportView(lab);
            
        } catch (CImageNGException ex) {
            Logger.getLogger(Transfo_dialog.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        // Cr�ation du dataset
        XYSeries serie = new XYSeries("Histo");
        for (int i = 0; i < 256; i++) {
            serie.add(i, histo[i]);
        }
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(serie);

        // Creation du chart
        JFreeChart chart = ChartFactory.createHistogram("Histogramme", "Niveaux de gris", "Nombre de pixels", dataset, PlotOrientation.VERTICAL, false, false, false);

        XYPlot plot = (XYPlot) chart.getXYPlot();
        ValueAxis axeX = plot.getDomainAxis();
        axeX.setRange(0, 255);
        plot.setDomainAxis(axeX);

        // creation d'une frame
        ChartFrame frame = new ChartFrame("Histogramme de l'image", chart);
        frame.pack();
        frame.setVisible(true);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Transfo_dialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Transfo_dialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Transfo_dialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Transfo_dialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Transfo_dialog dialog = new Transfo_dialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
