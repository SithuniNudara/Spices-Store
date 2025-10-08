package gui;

import com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import model.MySQL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
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
import org.knowm.xchart.PieSeries.PieSeriesRenderStyle;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.style.PieStyler;
import org.knowm.xchart.style.PieStyler.AnnotationType;

public class CustomerChart extends javax.swing.JFrame {

    public CustomerChart() {
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        initComponents();
        ImageIcon icon = new ImageIcon(SignIn.class.getResource("/resources/install.png"));
        this.setIconImage(icon.getImage());
        displayPieCharts();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel6 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Customer Chart");
        setResizable(false);

        jPanel6.setBackground(new java.awt.Color(22, 160, 133));
        jPanel6.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jPanel6.setPreferredSize(new java.awt.Dimension(971, 50));
        jPanel6.setLayout(new java.awt.GridLayout(1, 0));

        jButton1.setBackground(new java.awt.Color(204, 0, 51));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Print Report Details");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton1);

        jButton2.setBackground(new java.awt.Color(0, 102, 102));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Print Report Details");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton2);

        getContentPane().add(jPanel6, java.awt.BorderLayout.PAGE_END);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jPanel2.setLayout(new java.awt.GridLayout(1, 2, 5, 5));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 478, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 541, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel3);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 478, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 541, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel4);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {

            InputStream path = this.getClass().getResourceAsStream("/reports/SpicesStore_CustomerChart1.jasper");

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/spicesandherbs", "root", "1234");

            JasperPrint report = JasperFillManager.fillReport(path, null, connection);

            JasperViewer.viewReport(report, false);

            connection.close();

        } catch (Exception e) {
            Logger logger = Splash.getLoggerObject();
            logger.log(Level.WARNING, "Customer Chart1 Error", e);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {

            InputStream path = this.getClass().getResourceAsStream("/reports/SpicesStore_CustomerChart2.jasper");

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/spicesandherbs", "root", "1234");

            JasperPrint report = JasperFillManager.fillReport(path, null, connection);

            JasperViewer.viewReport(report, false);

            connection.close();

        } catch (Exception e) {
            Logger logger = Splash.getLoggerObject();
            logger.log(Level.WARNING, "Customer Chart2 Error", e);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void displayPieCharts() {

        jPanel3.add(createDoughnutChartPanel(createInvoiceDistributionData(), "Invoice Distribution by Customer"));
        jPanel4.add(createBarChartPanel(createCustomerSalesMap(), "Total Sales by Customer"));

        jPanel3.setLayout(new java.awt.GridLayout(1, 2));
        jPanel4.setLayout(new java.awt.GridLayout(1, 2));

        jPanel3.revalidate();
        jPanel3.repaint();

        jPanel4.revalidate();
        jPanel4.repaint();
    }

    private XChartPanel<PieChart> createDoughnutChartPanel(Map<String, Integer> data, String chartTitle) {
        PieChart chart = new PieChartBuilder().width(600).height(400).title(chartTitle).build();

        chart.getStyler().setLegendVisible(false);
        chart.getStyler().setAnnotationType(AnnotationType.LabelAndPercentage);
        chart.getStyler().setAnnotationDistance(.82);
        chart.getStyler().setPlotContentSize(.9);
        chart.getStyler().setDefaultSeriesRenderStyle(PieSeriesRenderStyle.Donut);

        // Add data
        for (Map.Entry<String, Integer> entry : data.entrySet()) {
            chart.addSeries(entry.getKey(), entry.getValue());
        }

        return new XChartPanel<>(chart);
    }

    private Map<String, Integer> createInvoiceDistributionData() {
        Map<String, Integer> data = new HashMap<>();
        try {

            ResultSet rs = MySQL.executeSearch("SELECT customer.first_name AS customer_name, COUNT(invoice.id) AS invoice_count "
                    + "FROM customer INNER JOIN invoice ON customer.mobile = invoice.customer_mobile "
                    + "GROUP BY customer.first_name");

            while (rs.next()) {
                data.put(rs.getString("customer_name"), rs.getInt("invoice_count"));
            }
        } catch (Exception e) {
            Logger logger = Splash.getLoggerObject();
            logger.log(Level.WARNING, "Create Invoice Distribution Dataset Error", e);
        }
        return data;
    }

    private Map<String, Double> createCustomerSalesMap() {
        Map<String, Double> dataMap = new LinkedHashMap<>();
        try {

            String query = "SELECT customer.first_name AS customer_name, SUM(invoice.paid_amount) AS total_sales "
                    + "FROM customer "
                    + "INNER JOIN invoice ON customer.mobile = invoice.customer_mobile "
                    + "GROUP BY customer.first_name";

            ResultSet rs = MySQL.executeSearch(query);

            while (rs.next()) {
                String customerName = rs.getString("customer_name");
                double totalSales = rs.getDouble("total_sales");
                dataMap.put(customerName, totalSales);
            }

            rs.close();
        } catch (Exception e) {
            Logger logger = Splash.getLoggerObject();
            logger.log(Level.WARNING, "CreateCustomerSalesMap Error", e);
        }
        return dataMap;
    }

    private JPanel createBarChartPanel(Map<String, Double> dataMap, String chartTitle) {
        CategoryChart chart = new CategoryChartBuilder().width(400).height(300)
                .title(chartTitle)
                .xAxisTitle("Customer Name")
                .yAxisTitle("Total Sales")
                .build();

        chart.getStyler().setLegendVisible(false);
        chart.getStyler().setHasAnnotations(true);
        chart.getStyler().setXAxisLabelRotation(45);

        List<String> customerNames = new ArrayList<>(dataMap.keySet());
        List<Double> totalSales = new ArrayList<>(dataMap.values());

        chart.addSeries("Total Sales", customerNames, totalSales);

        return new XChartPanel<>(chart);
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        FlatArcOrangeIJTheme.setup();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CustomerChart().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    // End of variables declaration//GEN-END:variables
}
