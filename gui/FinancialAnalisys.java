package gui;

import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.Color;
import java.awt.Font;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import model.MySQL;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.CategorySeries;
import org.knowm.xchart.XChartPanel;

public class FinancialAnalisys extends javax.swing.JFrame {

    public FinancialAnalisys() {
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        ImageIcon icon = new ImageIcon(SignIn.class.getResource("/resources/install.png"));
        this.setIconImage(icon.getImage());
        displaySalesCharts();
    }

    private void displaySalesCharts() {
        jPanel3.removeAll();
        jPanel4.removeAll();

        Map<String, Double> dailySalesRevenue = fetchDailySalesRevenue();
        Map<String, Double> dailyPurchaseCost = fetchDailyPurchaseCost();

        jPanel3.add(createColoredBarChartPanel(dailySalesRevenue, "Daily Sales Revenue (Last 10 Days)", "Date", "Total Sales Revenue"));
        jPanel4.add(createColoredBarChartPanel(dailyPurchaseCost, "Daily Purchase Costs (Last 10 Days)", "Date", "Total Purchase Cost"));

        jPanel3.setLayout(new java.awt.GridLayout(1, 1));
        jPanel4.setLayout(new java.awt.GridLayout(1, 1));

        jPanel3.revalidate();
        jPanel3.repaint();
        jPanel4.revalidate();
        jPanel4.repaint();
    }

    private Map<String, Double> fetchDailySalesRevenue() {
        Map<String, Double> data = new LinkedHashMap<>();
        String query = "SELECT DATE(grn.date) AS sales_date, SUM(grn_item.qty * stock.price) AS total_sales_revenue "
                + "FROM grn INNER JOIN grn_item ON grn.id = grn_item.grn_id "
                + "INNER JOIN stock ON grn_item.stock_id = stock.id "
                + "GROUP BY sales_date ORDER BY sales_date DESC LIMIT 10";
        try (ResultSet rs = MySQL.executeSearch(query)) {
            while (rs.next()) {
                data.put(rs.getString("sales_date"), rs.getDouble("total_sales_revenue"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    private Map<String, Double> fetchDailyPurchaseCost() {
        Map<String, Double> data = new LinkedHashMap<>();
        String query = "SELECT DATE(invoice.date) AS purchase_date, SUM(invoice_item.qty * stock.price) AS total_purchase_cost "
                + "FROM invoice INNER JOIN invoice_item ON invoice.id = invoice_item.invoice_id "
                + "INNER JOIN stock ON invoice_item.stock_id = stock.id "
                + "GROUP BY purchase_date ORDER BY purchase_date DESC LIMIT 10";
        try (ResultSet rs = MySQL.executeSearch(query)) {
            while (rs.next()) {
                data.put(rs.getString("purchase_date"), rs.getDouble("total_purchase_cost"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    private XChartPanel<CategoryChart> createColoredBarChartPanel(Map<String, Double> data, String title, String xAxisTitle, String yAxisTitle) {
        CategoryChart chart = new CategoryChartBuilder()
                .width(600)
                .height(400)
                .title(title)
                .xAxisTitle(xAxisTitle)
                .yAxisTitle(yAxisTitle)
                .build();

        // Styling
        chart.getStyler().setLegendVisible(false);
        chart.getStyler().setHasAnnotations(true);
        chart.getStyler().setAvailableSpaceFill(0.8f);
        chart.getStyler().setAxisTickLabelsFont(new Font("SansSerif", Font.PLAIN, 10));
        chart.getStyler().setAxisTitleFont(new Font("SansSerif", Font.PLAIN, 10));
        chart.getStyler().setChartTitleFont(new Font("SansSerif", Font.BOLD, 12));

        // Data
        List<String> dates = new ArrayList<>(data.keySet());
        List<Double> values = new ArrayList<>(data.values());
        CategorySeries series = chart.addSeries(title, dates, values);

        // Custom coloring: cycle through 5 colors
        Color[] colors = {Color.BLUE, Color.GREEN, Color.ORANGE, Color.RED, Color.MAGENTA};
        List<Color> barColors = new ArrayList<>();
        for (int i = 0; i < values.size(); i++) {
            barColors.add(colors[i % colors.length]);
        }
        chart.getStyler().setSeriesColors(barColors.toArray(new Color[0]));

        return new XChartPanel<>(chart);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Daily Sales Report");

        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jPanel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jPanel2.setPreferredSize(new java.awt.Dimension(1211, 50));
        jPanel2.setLayout(new java.awt.GridLayout(1, 0));

        jButton1.setBackground(new java.awt.Color(0, 0, 102));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Print Details");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1);

        jButton2.setBackground(new java.awt.Color(153, 0, 0));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Print Details");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2);

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_END);

        jPanel6.setLayout(new java.awt.GridLayout(1, 0));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 605, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 250, Short.MAX_VALUE)
        );

        jPanel6.add(jPanel3);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 605, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 250, Short.MAX_VALUE)
        );

        jPanel6.add(jPanel4);

        getContentPane().add(jPanel6, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {

            InputStream path = this.getClass().getResourceAsStream("/reports/SpicesStore_DailySalesRevenue.jasper");

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/spicesandherbs", "root", "1234");

            JasperPrint report = JasperFillManager.fillReport(path, null, connection);

            JasperViewer.viewReport(report, false);

            connection.close();

        } catch (Exception e) {
            Logger logger = Splash.getLoggerObject();
            logger.log(Level.WARNING, "Daily Purchase Cost Report Viewing Error", e);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // Daily Sales Revenue.jasper
        try {

            InputStream path = this.getClass().getResourceAsStream("/reports/SpicesStore_DailyPurchaseCostReport.jasper");

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/spicesandherbs", "root", "1234");

            JasperPrint report = JasperFillManager.fillReport(path, null, connection);

            JasperViewer.viewReport(report, false);

            connection.close();

        } catch (Exception e) {
            Logger logger = Splash.getLoggerObject();
            logger.log(Level.WARNING, " Daily Sales Report Error", e);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        FlatMacLightLaf.setup();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FinancialAnalisys().setVisible(true);
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
