package gui;

import com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import model.MySQL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.style.PieStyler;

public class SupplierCharts extends javax.swing.JFrame {

    public SupplierCharts() {
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        initComponents();
        ImageIcon icon = new ImageIcon(SignIn.class.getResource("/resources/install.png"));
        this.setIconImage(icon.getImage());
        displayPieCharts();
    }

    private void displayPieCharts() {
        // Create and add the panels to the respective panels
        jPanel3.add(createXChartPieChartPanel(createSupplierMap(), "Supplier Distribution by Company"));
        jPanel4.add(createXChartBarChartPanel(createCompanySupplierMap(), "Company-Supplier Distribution"));

        // Set the layout and refresh
        jPanel3.setLayout(new java.awt.GridLayout(1, 3));
        jPanel4.setLayout(new java.awt.GridLayout(1, 3));

        jPanel3.revalidate();
        jPanel3.repaint();

        jPanel4.revalidate();
        jPanel4.repaint();
    }

    private Map<String, Integer> createSupplierMap() {
        Map<String, Integer> dataMap = new LinkedHashMap<>();
        try {
            String query = "SELECT company.name AS company_name, COUNT(*) AS supplier_count "
                    + "FROM supplier "
                    + "INNER JOIN company ON supplier.company_id = company.id "
                    + "GROUP BY company.name";
            ResultSet rs = MySQL.executeSearch(query);

            while (rs.next()) {
                dataMap.put(rs.getString("company_name"), rs.getInt("supplier_count"));
            }

            rs.close();

        } catch (Exception e) {
            Logger logger = Splash.getLoggerObject();
            logger.log(Level.WARNING, "Create Supplier Map Error", e);
        }
        return dataMap;
    }

    private JPanel createXChartPieChartPanel(Map<String, Integer> dataMap, String chartTitle) {
        PieChart chart = new PieChartBuilder().width(400).height(300).title(chartTitle).build();

        // Customize chart style
        chart.getStyler().setLegendVisible(false);
        chart.getStyler().setAnnotationType(PieStyler.AnnotationType.LabelAndPercentage);
        chart.getStyler().setAnnotationDistance(1.15);
        chart.getStyler().setPlotContentSize(.7);

        // Add data
        for (Map.Entry<String, Integer> entry : dataMap.entrySet()) {
            chart.addSeries(entry.getKey(), entry.getValue());
        }

        return new XChartPanel<>(chart);
    }

    private Map<String, Integer> createCompanySupplierMap() {
        Map<String, Integer> dataMap = new LinkedHashMap<>();
        try {
            
            String query = "SELECT company.name AS company_name, COUNT(*) AS supplier_count "
                    + "FROM supplier "
                    + "INNER JOIN company ON supplier.company_id = company.id "
                    + "GROUP BY company.name";
            ResultSet rs = MySQL.executeSearch(query);

            while (rs.next()) {
                dataMap.put(rs.getString("company_name"), rs.getInt("supplier_count"));
            }

        } catch (Exception e) {
            Logger logger = Splash.getLoggerObject();
            logger.log(Level.WARNING, "Create Company Supplier Map Error", e);
        }
        return dataMap;
    }

    private JPanel createXChartBarChartPanel(Map<String, Integer> dataMap, String chartTitle) {
        CategoryChart chart = new CategoryChartBuilder().width(400).height(300)
                .title(chartTitle)
                .xAxisTitle("Company")
                .yAxisTitle("Suppliers")
                .build();

        // Customize chart
        chart.getStyler().setLegendVisible(false);
        chart.getStyler().setHasAnnotations(true);

        // Prepare data
        List<String> companies = new ArrayList<>(dataMap.keySet());
        List<Integer> supplierCounts = new ArrayList<>(dataMap.values());

        // Add data series
        chart.addSeries("Suppliers", companies, supplierCounts);

        return new XChartPanel<>(chart);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Supplier Chart Report");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jPanel1.setLayout(new java.awt.GridLayout(1, 2, 5, 5));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 477, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 495, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel3);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 477, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 495, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel4);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel6.setBackground(new java.awt.Color(22, 160, 133));
        jPanel6.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jPanel6.setPreferredSize(new java.awt.Dimension(975, 50));
        jPanel6.setLayout(new java.awt.GridLayout(1, 2));

        jButton1.setBackground(new java.awt.Color(204, 0, 0));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Print Details");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton1);

        jButton2.setBackground(new java.awt.Color(0, 102, 102));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Print Details");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton2);

        getContentPane().add(jPanel6, java.awt.BorderLayout.PAGE_END);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    //print1
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {

            InputStream path = this.getClass().getResourceAsStream("/reports/SpicesStore_SupplierChart1.jasper");

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/spicesandherbs", "root", "1234");

            JasperPrint report = JasperFillManager.fillReport(path, null, connection);

            JasperViewer.viewReport(report, false);

            connection.close();

        } catch (Exception e) {
            Logger logger = Splash.getLoggerObject();
            logger.log(Level.WARNING, "Chart 1 Error", e);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    //print2
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {

            InputStream path = this.getClass().getResourceAsStream("/reports/SpicesStore_SupplierChart2.jasper");

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/spicesandherbs", "root", "1234");

            JasperPrint report = JasperFillManager.fillReport(path, null, connection);

            JasperViewer.viewReport(report, false);

            connection.close();

        } catch (Exception e) {
            Logger logger = Splash.getLoggerObject();
            logger.log(Level.WARNING, "chart 2 Error", e);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        FlatArcOrangeIJTheme.setup();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SupplierCharts().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    // End of variables declaration//GEN-END:variables
}
