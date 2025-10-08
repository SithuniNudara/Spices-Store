package gui;

import com.formdev.flatlaf.themes.FlatMacLightLaf;
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
import org.knowm.xchart.XChartPanel;

public class PurchaseReport extends javax.swing.JFrame {

    public PurchaseReport() {
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        ImageIcon icon = new ImageIcon(SignIn.class.getResource("/resources/install.png"));
        this.setIconImage(icon.getImage());
        displayCharts();
    }

    private void displayCharts() {

        jPanel3.removeAll();
        jPanel4.removeAll();

        jPanel3.setLayout(new java.awt.GridLayout(1, 1));
        jPanel4.setLayout(new java.awt.GridLayout(1, 1));

        Map<String, Integer> supplierData = fetchSupplierData();
        Map<String, Integer> productData = fetchProductData();

        jPanel3.add(createBarChartPanel(supplierData, "Top Suppliers by Total Purchased", "Supplier", "Total Purchased"));
        jPanel4.add(createBarChartPanel(productData, "Product-wise Purchases", "Product", "Total Purchased"));

        jPanel3.revalidate();
        jPanel3.repaint();
        jPanel4.revalidate();
        jPanel4.repaint();
    }

    private XChartPanel<CategoryChart> createBarChartPanel(Map<String, Integer> data, String title, String xAxisTitle, String yAxisTitle) {
        CategoryChart chart = new CategoryChartBuilder()
                .width(600)
                .height(400)
                .title(title)
                .xAxisTitle(xAxisTitle)
                .yAxisTitle(yAxisTitle)
                .build();

        chart.getStyler().setLegendVisible(false);
        chart.getStyler().setHasAnnotations(true);
        chart.getStyler().setAvailableSpaceFill(0.8f);
        chart.getStyler().setAxisTickLabelsFont(new Font("SansSerif", Font.PLAIN, 10));
        chart.getStyler().setAxisTitleFont(new Font("SansSerif", Font.PLAIN, 10));
        chart.getStyler().setChartTitleFont(new Font("SansSerif", Font.BOLD, 12));

        List<String> categories = new ArrayList<>(data.keySet());
        List<Integer> values = new ArrayList<>(data.values());

        chart.addSeries(title, categories, values);

        return new XChartPanel<>(chart);
    }

    private Map<String, Integer> fetchSupplierData() {
        Map<String, Integer> data = new LinkedHashMap<>();
        String query = "SELECT supplier.first_name AS first_name, SUM(grn_item.qty) AS total_purchased "
                + "FROM grn INNER JOIN grn_item ON grn.id = grn_item.grn_id "
                + "INNER JOIN supplier ON grn.supplier_mobile = supplier.mobile "
                + "GROUP BY supplier.mobile";
        try (ResultSet rs = MySQL.executeSearch(query)) {
            while (rs.next()) {
                data.put(rs.getString("first_name"), rs.getInt("total_purchased"));
            }
        } catch (Exception e) {
            Logger logger = Splash.getLoggerObject();
            logger.log(Level.WARNING, "Supplier dataset Error", e);
        }
        return data;
    }

    private Map<String, Integer> fetchProductData() {
        Map<String, Integer> data = new LinkedHashMap<>();
        String query = "SELECT product.name AS product_name, SUM(grn_item.qty) AS total_purchased "
                + "FROM product INNER JOIN stock ON stock.product_id = product.id "
                + "INNER JOIN grn_item ON grn_item.stock_id = stock.id "
                + "GROUP BY product.name";
        try (ResultSet rs = MySQL.executeSearch(query)) {
            while (rs.next()) {
                data.put(rs.getString("product_name"), rs.getInt("total_purchased"));
            }
        } catch (Exception e) {
            Logger logger = Splash.getLoggerObject();
            logger.log(Level.WARNING, "Create Product Error", e);
        }
        return data;
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
        setTitle("Purchase Report");
        setResizable(false);

        jPanel2.setPreferredSize(new java.awt.Dimension(980, 50));
        jPanel2.setLayout(new java.awt.GridLayout(1, 0));

        jButton1.setBackground(new java.awt.Color(153, 0, 51));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Print Details");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1);

        jButton2.setBackground(new java.awt.Color(0, 0, 102));
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
            .addGap(0, 722, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 593, Short.MAX_VALUE)
        );

        jPanel6.add(jPanel3);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 722, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 593, Short.MAX_VALUE)
        );

        jPanel6.add(jPanel4);

        getContentPane().add(jPanel6, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {

            InputStream path = this.getClass().getResourceAsStream("/reports/SpicesStore_SupplierWisePurchase.jasper");

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/spicesandherbs", "root", "1234");

            JasperPrint report = JasperFillManager.fillReport(path, null, connection);

            JasperViewer.viewReport(report, false);

            connection.close();

        } catch (Exception e) {
            Logger logger = Splash.getLoggerObject();
            logger.log(Level.WARNING, "Suplierwise Purchase Report Error", e);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {

            InputStream path = this.getClass().getResourceAsStream("/reports/SpicesStore_ProductsWisePurchases.jasper");

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/spicesandherbs", "root", "1234");

            JasperPrint report = JasperFillManager.fillReport(path, null, connection);

            JasperViewer.viewReport(report, false);

            connection.close();

        } catch (Exception e) {
            Logger logger = Splash.getLoggerObject();
            logger.log(Level.WARNING, "Productwise purchases Error", e);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        FlatMacLightLaf.setup();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PurchaseReport().setVisible(true);
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
