package gui;

import com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;


public class Splash extends javax.swing.JFrame {
    
    private static final Logger logger = Logger.getLogger(Splash.class.getName());
    private static FileHandler fileHandler;

    private static Splash splash;
    private Thread PROGRESS_THREAD;
    private int PROGRESS_VALUE;
    
    
    public Splash() {
        initComponents();
        ImageIcon icon = new ImageIcon(SignIn.class.getResource("/resources/install.png"));
        this.setIconImage(icon.getImage());
        loadAnimation();
        initilaizedLogging();
    }
    
     private void initilaizedLogging() {

        String homePath = System.getProperty("user.home");

        String folderPath = homePath
                + File.separator + "Documents"
                + File.separator + "Loggers"
                + File.separator + "Logs";

        File NewFolder = new File(folderPath);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss");
        // System.out.println(sdf.format(new Date()));

        if (NewFolder.exists()) {
            
            String fileName = sdf.format(new Date());
            
            if (fileHandler == null) {
                
                try {
                    
                      Splash.fileHandler = new FileHandler(folderPath+"/"+fileName+".txt");
                      fileHandler.setFormatter(new SimpleFormatter());
                      logger.addHandler(fileHandler);
                      logger.setLevel(Level.ALL);
                      logger.info("Log file Created Successfully!");
                      
                } catch (IOException e) {
                    
                    logger.log(Level.WARNING,"Log File Creation Unsuccessful!",e);
                    
                }

            }
            
            return;
        }

        NewFolder.mkdirs();

    }
    
    public static Logger getLoggerObject(){
        return Splash.logger;
    }
   private void loadAnimation() {
    splash = this; // Initialize splash instance
    Random random = new Random(); // Single Random instance

    PROGRESS_THREAD = new Thread(() -> {
        while (PROGRESS_VALUE < 100) {
            PROGRESS_VALUE += random.nextInt(10) + 5;
            if (PROGRESS_VALUE > 100) {
                PROGRESS_VALUE = 100;
            }
            
            // Use SwingUtilities.invokeLater to update Swing components
            SwingUtilities.invokeLater(() -> {
                jProgressBar1.setValue(PROGRESS_VALUE);
                if (PROGRESS_VALUE < 20) {
                    jLabel1.setText("Loading...");
                } else if (PROGRESS_VALUE < 30) {
                    jLabel1.setText("Please wait...");
                } else if (PROGRESS_VALUE < 50) {
                    jLabel1.setText("Initializing...");
                } else if (PROGRESS_VALUE < 65) {
                    jLabel1.setText("Preparing your data...");
                } else if (PROGRESS_VALUE < 80) {
                    jLabel1.setText("Setting up environment...");
                } else if (PROGRESS_VALUE < 99) {
                    jLabel1.setText("Fetching necessary resources...");
                } else {
                    jLabel1.setText("Done!");
                }
            });

            try {
                Thread.sleep(random.nextInt(1000) + 500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        // Dispose and open SignIn on the EDT
        SwingUtilities.invokeLater(() -> {
            splash.dispose();
            new SignIn().setVisible(true);
        });
    });
    
    PROGRESS_THREAD.start();
}


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setEnabled(false);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        jLabel1.setText("Loading ......");

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/291.gif"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(85, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        FlatArcOrangeIJTheme.setup();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Splash().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar jProgressBar1;
    // End of variables declaration//GEN-END:variables
}
