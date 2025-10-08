package gui;

import com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import model.MySQL;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.style.PieStyler;

public class EmployeeChart extends javax.swing.JFrame {

    public EmployeeChart() {
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        initComponents();
        ImageIcon icon = new ImageIcon(SignIn.class.getResource("/resources/install.png"));
        this.setIconImage(icon.getImage());
        displayPieCharts();
    }

    private void displayPieCharts() {
        // Remove any existing charts
        jPanel3.removeAll();
        jPanel4.removeAll();
        jPanel5.removeAll();

        // Layout for panels
        jPanel3.setLayout(new java.awt.GridLayout(1, 1));
        jPanel4.setLayout(new java.awt.GridLayout(1, 1));
        jPanel5.setLayout(new java.awt.GridLayout(1, 1));

        // Create and add XChart panels
        jPanel3.add(createDoughnutChartPanel(createGenderDataset(), "Gender Distribution"));
        jPanel4.add(createDoughnutChartPanel(createEmployeeTypeDataset(), "Employee Type Distribution"));
        jPanel5.add(createDoughnutChartPanel(createCityDataset(), "City Distribution"));

        // Refresh
        jPanel3.revalidate();
        jPanel3.repaint();
        jPanel4.revalidate();
        jPanel4.repaint();
        jPanel5.revalidate();
        jPanel5.repaint();
    }

    private XChartPanel<PieChart> createDoughnutChartPanel(Map<String, Integer> dataset, String chartTitle) {
        PieChart chart = new PieChartBuilder().width(400).height(300).title(chartTitle).build();

        // Customize chart appearance
        chart.getStyler().setLegendVisible(false);
        chart.getStyler().setAnnotationType(PieStyler.AnnotationType.LabelAndPercentage);
        chart.getStyler().setAnnotationDistance(1.15);
        chart.getStyler().setPlotContentSize(.8);
        // chart.getStyler().setDefaultSeriesRenderStyle(PieStyler.ChartTheme.XChart);
        chart.getStyler().setDonutThickness(0.3); // 30% of chart radius

        // Add data
        for (Map.Entry<String, Integer> entry : dataset.entrySet()) {
            chart.addSeries(entry.getKey(), entry.getValue());
        }

        return new XChartPanel<>(chart);
    }

    private Map<String, Integer> createGenderDataset() {
        Map<String, Integer> dataset = new LinkedHashMap<>();
        try {
            ResultSet rs = MySQL.executeSearch("SELECT gender.name, COUNT(*) AS employee_count \n"
                    + "FROM emlpoyee \n"
                    + "INNER JOIN gender ON emlpoyee.gender_id = gender.id\n"
                    + "WHERE emlpoyee.`status` = 'Active'\n"
                    + "GROUP BY gender.name");

            while (rs.next()) {
                dataset.put(rs.getString("name"), rs.getInt("employee_count"));
            }
        } catch (Exception e) {
            Logger logger = Splash.getLoggerObject();
            logger.log(Level.WARNING, "gender dataset error", e);
        }
        return dataset;
    }

    private Map<String, Integer> createEmployeeTypeDataset() {
        Map<String, Integer> dataset = new LinkedHashMap<>();
        try {
            ResultSet rs = MySQL.executeSearch("SELECT employee_type.name AS employee_type_name, COUNT(*) AS employee_count \n"
                    + "FROM emlpoyee \n"
                    + "INNER JOIN employee_type ON emlpoyee.employee_type_id = employee_type.id \n"
                    + "GROUP BY employee_type.name");

            while (rs.next()) {
                dataset.put(rs.getString("employee_type_name"), rs.getInt("employee_count"));
            }
        } catch (Exception e) {
            Logger logger = Splash.getLoggerObject();
            logger.log(Level.WARNING, "employee type Error", e);
        }
        return dataset;
    }

    private Map<String, Integer> createCityDataset() {
        Map<String, Integer> dataset = new LinkedHashMap<>();
        try {
            ResultSet rs = MySQL.executeSearch("SELECT city.name AS city_name, COUNT(*) AS employee_count \n"
                    + "                FROM emlpoyee \n"
                    + "                INNER JOIN employee_address ON emlpoyee.email = employee_address.emlpoyee_email\n"
                    + "                INNER JOIN city ON employee_address.city_id = city.id \n"
                    + "                GROUP BY city.name");

            while (rs.next()) {
                dataset.put(rs.getString("city_name"), rs.getInt("employee_count"));
            }
        } catch (Exception e) {
            Logger logger = Splash.getLoggerObject();
            logger.log(Level.WARNING, "create city Error", e);
        }
        return dataset;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Employee Chart Reports");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jPanel1.setLayout(new java.awt.GridLayout(1, 3, 5, 5));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 315, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 538, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel3);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 315, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 538, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel4);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 315, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 538, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel5);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel6.setBackground(new java.awt.Color(22, 160, 133));
        jPanel6.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jPanel6.setPreferredSize(new java.awt.Dimension(966, 50));
        jPanel6.setLayout(new java.awt.GridLayout(1, 0));

        jButton1.setBackground(new java.awt.Color(153, 0, 51));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Print Details");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton1);

        jButton2.setBackground(new java.awt.Color(0, 102, 102));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Print Details");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton2);

        jButton3.setBackground(new java.awt.Color(0, 0, 0));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Print Details");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton3);

        getContentPane().add(jPanel6, java.awt.BorderLayout.PAGE_END);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    //gender
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {

            InputStream path = this.getClass().getResourceAsStream("/reports/SpicesStore_EmployeeChart1.jasper");

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/spicesandherbs", "root", "1234");

            JasperPrint report = JasperFillManager.fillReport(path, null, connection);

            JasperViewer.viewReport(report, false);

            connection.close();

        } catch (Exception e) {
            Logger logger = Splash.getLoggerObject();
            logger.log(Level.WARNING, "gender error", e);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {

            InputStream path = this.getClass().getResourceAsStream("/reports/SpicesStore_EmployeeChart2.jasper");

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/spicesandherbs", "root", "1234");

            JasperPrint report = JasperFillManager.fillReport(path, null, connection);

            JasperViewer.viewReport(report, false);

            connection.close();

        } catch (Exception e) {
            Logger logger = Splash.getLoggerObject();
            logger.log(Level.WARNING, "Employeechart1 Error", e);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {

            InputStream path = this.getClass().getResourceAsStream("/reports/SpicesStore_EmployeeChart3.jasper");

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/spicesandherbs", "root", "1234");

            JasperPrint report = JasperFillManager.fillReport(path, null, connection);

            JasperViewer.viewReport(report, false);

            connection.close();

        } catch (Exception e) {
            Logger logger = Splash.getLoggerObject();
            logger.log(Level.WARNING, "EmployeecityReport Error", e);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        FlatArcOrangeIJTheme.setup();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EmployeeChart().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    // End of variables declaration//GEN-END:variables
}
