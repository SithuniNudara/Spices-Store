package gui;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.GridLayout;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import model.MySQL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

public class InventoryManagement extends javax.swing.JFrame {

    public InventoryManagement() {
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        ImageIcon icon = new ImageIcon(SignIn.class.getResource("/resources/install.png"));
        this.setIconImage(icon.getImage());
        displayCharts();
    }

    private void displayCharts() {
        jPanel3.removeAll();
        jPanel4.removeAll();
        jPanel5.removeAll();

        jPanel3.setLayout(new GridLayout(1, 1));
        jPanel4.setLayout(new GridLayout(1, 1));
        jPanel5.setLayout(new GridLayout(1, 1));

        Map<String, Integer> productStockData = fetchProductStockData();
        Map<String, Integer> topSellingProductsData = fetchTopSellingProductsData();
        Map<Integer, Double> stockTurnoverData = fetchStockTurnoverData();

        jPanel3.add(createBarChartPanel(productStockData, "Product-wise Stock Levels", "Product", "Stock Quantity"));
        jPanel4.add(createLineChartPanel(stockTurnoverData, "Product-wise Stock Turnover Rate", "Index", "Turnover Rate"));
        jPanel5.add(createBarChartPanel(topSellingProductsData, "Top 10 Selling Products", "Product", "Total Sold"));

        jPanel3.revalidate();
        jPanel3.repaint();
        jPanel4.revalidate();
        jPanel4.repaint();
        jPanel5.revalidate();
        jPanel5.repaint();
    }

    private XChartPanel<CategoryChart> createBarChartPanel(Map<String, Integer> data, String title, String xAxisTitle, String yAxisTitle) {
        CategoryChart chart = new CategoryChartBuilder().width(400).height(300).title(title).xAxisTitle(xAxisTitle).yAxisTitle(yAxisTitle).build();
        chart.getStyler().setLegendVisible(false);
        chart.getStyler().setHasAnnotations(true);
        chart.getStyler().setAvailableSpaceFill(0.8);

        List<String> categories = new ArrayList<>(data.keySet());
        List<Integer> values = new ArrayList<>(data.values());

        chart.addSeries(title, categories, values);

        return new XChartPanel<>(chart);
    }

    private XChartPanel<XYChart> createLineChartPanel(Map<Integer, Double> data, String title, String xAxisTitle, String yAxisTitle) {
        XYChart chart = new XYChartBuilder().width(400).height(300).title(title).xAxisTitle(xAxisTitle).yAxisTitle(yAxisTitle).build();
        chart.getStyler().setLegendVisible(false);
        chart.getStyler().setMarkerSize(6);

        List<Integer> xData = new ArrayList<>(data.keySet());
        List<Double> yData = new ArrayList<>(data.values());

        chart.addSeries(title, xData, yData);

        return new XChartPanel<>(chart);
    }

    private Map<String, Integer> fetchProductStockData() {
        Map<String, Integer> data = new LinkedHashMap<>();
        String query = "SELECT product.name, stock.qty AS current_stock "
                + "FROM stock INNER JOIN product ON stock.product_id = product.id";
        try (ResultSet rs = MySQL.executeSearch(query)) {
            while (rs.next()) {
                data.put(rs.getString("product.name"), rs.getInt("current_stock"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    private Map<String, Integer> fetchTopSellingProductsData() {
        Map<String, Integer> data = new LinkedHashMap<>();
        String query = "SELECT product.name, SUM(grn_item.qty) AS total_sold "
                + "FROM grn INNER JOIN grn_item ON grn.id = grn_item.grn_id "
                + "INNER JOIN stock ON grn_item.stock_id = stock.id "
                + "INNER JOIN product ON stock.product_id = product.id "
                + "GROUP BY product.id ORDER BY total_sold DESC LIMIT 10";
        try (ResultSet rs = MySQL.executeSearch(query)) {
            while (rs.next()) {
                data.put(rs.getString("product.name"), rs.getInt("total_sold"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    private Map<Integer, Double> fetchStockTurnoverData() {
        Map<Integer, Double> data = new LinkedHashMap<>();
        String query = "SELECT product.name, SUM(grn_item.qty) / AVG(stock.qty) AS stock_turnover_rate "
                + "FROM grn INNER JOIN grn_item ON grn.id = grn_item.grn_id "
                + "INNER JOIN stock ON grn_item.stock_id = stock.id "
                + "INNER JOIN product ON stock.product_id = product.id GROUP BY product.id";
        try (ResultSet rs = MySQL.executeSearch(query)) {
            int index = 1;
            while (rs.next()) {
                double rate = rs.getDouble("stock_turnover_rate");
                data.put(index, rate);
                index++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Invoice Reports\n");

        jPanel2.setBackground(new java.awt.Color(22, 160, 133));
        jPanel2.setPreferredSize(new java.awt.Dimension(958, 50));
        jPanel2.setLayout(new java.awt.GridLayout(1, 0));

        jButton1.setBackground(new java.awt.Color(153, 0, 0));
        jButton1.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Print Details");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1);

        jButton2.setBackground(new java.awt.Color(0, 102, 102));
        jButton2.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Print Details");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2);

        jButton3.setBackground(new java.awt.Color(0, 0, 0));
        jButton3.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Print Deatils");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3);

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_END);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jPanel6.setLayout(new java.awt.GridLayout(1, 0));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 505, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 643, Short.MAX_VALUE)
        );

        jPanel6.add(jPanel3);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 505, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 643, Short.MAX_VALUE)
        );

        jPanel6.add(jPanel4);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 505, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 643, Short.MAX_VALUE)
        );

        jPanel6.add(jPanel5);

        getContentPane().add(jPanel6, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {

            InputStream path = this.getClass().getResourceAsStream("/reports/Spices_Store_ProductWiseStockLevelReport2.jasper");

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/spicesandherbs", "root", "1234");

            JasperPrint report = JasperFillManager.fillReport(path, null, connection);

            JasperViewer.viewReport(report, false);

            connection.close();

        } catch (Exception e) {
            Logger logger = Splash.getLoggerObject();
            logger.log(Level.WARNING, "Prodcut Wise Stock Error", e);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {

            InputStream path = this.getClass().getResourceAsStream("/reports/SpicesStore_ProductWiseStockTurnOverRate1.jasper");

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/spicesandherbs", "root", "1234");

            JasperPrint report = JasperFillManager.fillReport(path, null, connection);

            JasperViewer.viewReport(report, false);

            connection.close();

        } catch (Exception e) {
            Logger logger = Splash.getLoggerObject();
            logger.log(Level.WARNING, "Product Wise Stock Error", e);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {

            InputStream path = this.getClass().getResourceAsStream("/reports/SpicesStore_Top10SellingProducts.jasper");

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/spicesandherbs", "root", "1234");

            JasperPrint report = JasperFillManager.fillReport(path, null, connection);

            JasperViewer.viewReport(report, false);

            connection.close();

        } catch (Exception e) {
            Logger logger = Splash.getLoggerObject();
            logger.log(Level.WARNING, "Top10 Selling Products Error", e);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        FlatMacLightLaf.setup();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InventoryManagement().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    // End of variables declaration//GEN-END:variables
}
