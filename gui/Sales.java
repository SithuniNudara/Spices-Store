package gui;

import com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme;
import java.awt.GridLayout;
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
import org.knowm.xchart.PieSeries;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.style.PieStyler;

public class Sales extends javax.swing.JFrame {

    public Sales() {
        initComponents();

        ImageIcon icon = new ImageIcon(SignIn.class.getResource("/resources/install.png"));
        this.setIconImage(icon.getImage());

        displayPieCharts();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

private void displayPieCharts() {
    jPanel3.removeAll();
    jPanel4.removeAll();
    jPanel5.removeAll();

    jPanel3.setLayout(new GridLayout(1, 3));
    jPanel4.setLayout(new GridLayout(1, 3));
    jPanel5.setLayout(new GridLayout(1, 3));

    Map<String, Integer> productSalesData = fetchProductSalesData();
    Map<String, Integer> brandSalesData = fetchBrandSalesData();
    Map<String, Integer> supplierSalesData = fetchSupplierSalesData();

    jPanel3.add(createPieChartPanel(productSalesData, "Product-wise Sales"));
    jPanel4.add(createDoughnutChartPanel(brandSalesData, "Brand-wise Sales"));
    jPanel5.add(createPieChartPanel(supplierSalesData, "Supplier-wise Sales"));

    jPanel3.revalidate();
    jPanel3.repaint();
    jPanel4.revalidate();
    jPanel4.repaint();
    jPanel5.revalidate();
    jPanel5.repaint();
}

private XChartPanel<PieChart> createPieChartPanel(Map<String, Integer> data, String title) {
    PieChart chart = new PieChartBuilder().width(400).height(300).title(title).build();
    chart.getStyler().setLegendVisible(false);
    chart.getStyler().setAnnotationType(PieStyler.AnnotationType.LabelAndPercentage);
    chart.getStyler().setAnnotationDistance(0.8);
    chart.getStyler().setPlotContentSize(0.9);

    for (Map.Entry<String, Integer> entry : data.entrySet()) {
        chart.addSeries(entry.getKey(), entry.getValue());
    }

    return new XChartPanel<>(chart);
}

private XChartPanel<PieChart> createDoughnutChartPanel(Map<String, Integer> data, String title) {
    PieChart chart = new PieChartBuilder().width(400).height(300).title(title).build();
    chart.getStyler().setLegendVisible(false);
    chart.getStyler().setAnnotationType(PieStyler.AnnotationType.LabelAndPercentage);
    chart.getStyler().setAnnotationDistance(0.82);
    chart.getStyler().setPlotContentSize(0.9);
    chart.getStyler().setDefaultSeriesRenderStyle(PieSeries.PieSeriesRenderStyle.Donut);

    for (Map.Entry<String, Integer> entry : data.entrySet()) {
        chart.addSeries(entry.getKey(), entry.getValue());
    }

    return new XChartPanel<>(chart);
}

private Map<String, Integer> fetchProductSalesData() {
    Map<String, Integer> data = new LinkedHashMap<>();
    String query = "SELECT product.name, SUM(grn_item.qty) AS total_quantity_sold "
            + "FROM grn INNER JOIN grn_item ON grn.id = grn_item.grn_id "
            + "INNER JOIN stock ON grn_item.stock_id = stock.id "
            + "INNER JOIN product ON stock.product_id = product.id GROUP BY product.id";
    try (ResultSet rs = MySQL.executeSearch(query)) {
        while (rs.next()) {
            data.put(rs.getString("product.name"), rs.getInt("total_quantity_sold"));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return data;
}

private Map<String, Integer> fetchBrandSalesData() {
    Map<String, Integer> data = new LinkedHashMap<>();
    String query = "SELECT brand.name, SUM(grn_item.qty) AS total_quantity_sold "
            + "FROM grn INNER JOIN grn_item ON grn.id = grn_item.grn_id "
            + "INNER JOIN stock ON grn_item.stock_id = stock.id "
            + "INNER JOIN product ON stock.product_id = product.id "
            + "INNER JOIN brand ON product.brand_id = brand.id GROUP BY brand.id";
    try (ResultSet rs = MySQL.executeSearch(query)) {
        while (rs.next()) {
            data.put(rs.getString("brand.name"), rs.getInt("total_quantity_sold"));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return data;
}

private Map<String, Integer> fetchSupplierSalesData() {
    Map<String, Integer> data = new LinkedHashMap<>();
    String query = "SELECT supplier.first_name, SUM(grn_item.qty) AS total_quantity_sold "
            + "FROM grn INNER JOIN grn_item ON grn.id = grn_item.grn_id "
            + "INNER JOIN supplier ON grn.supplier_mobile = supplier.mobile GROUP BY supplier.mobile";
    try (ResultSet rs = MySQL.executeSearch(query)) {
        while (rs.next()) {
            data.put(rs.getString("supplier.first_name"), rs.getInt("total_quantity_sold"));
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
        setTitle("Sales");
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(22, 160, 133));
        jPanel2.setPreferredSize(new java.awt.Dimension(967, 50));
        jPanel2.setLayout(new java.awt.GridLayout(1, 0));

        jButton1.setBackground(new java.awt.Color(153, 0, 0));
        jButton1.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Print Deatails");
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

        jButton3.setBackground(new java.awt.Color(51, 51, 51));
        jButton3.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Print Details");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3);

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_END);

        jPanel6.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jPanel6.setLayout(new java.awt.GridLayout(1, 0));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 369, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 587, Short.MAX_VALUE)
        );

        jPanel6.add(jPanel3);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 369, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 587, Short.MAX_VALUE)
        );

        jPanel6.add(jPanel4);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 369, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 587, Short.MAX_VALUE)
        );

        jPanel6.add(jPanel5);

        getContentPane().add(jPanel6, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {

            InputStream path = this.getClass().getResourceAsStream("/reports/SpicesStore_ProductwiseSale.jasper");

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/spicesandherbs", "root", "1234");

            JasperPrint report = JasperFillManager.fillReport(path, null, connection);

            JasperViewer.viewReport(report, false);

            connection.close();

        } catch (Exception e) {
            Logger logger = Splash.getLoggerObject();
            logger.log(Level.WARNING, "Productwisesale.jasper Error", e);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {

            InputStream path = this.getClass().getResourceAsStream("/reports/SpicesStore_BrandWiseSales.jasper");

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/spicesandherbs", "root", "1234");

            JasperPrint report = JasperFillManager.fillReport(path, null, connection);

            JasperViewer.viewReport(report, false);

            connection.close();

        } catch (Exception e) {
            Logger logger = Splash.getLoggerObject();
            logger.log(Level.WARNING, "Brand Wise sales Error", e);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {

            InputStream path = this.getClass().getResourceAsStream("/reports/SpicesStore_SupplierWiseSale.jasper");

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/spicesandherbs", "root", "1234");

            JasperPrint report = JasperFillManager.fillReport(path, null, connection);

            JasperViewer.viewReport(report, false);

            connection.close();

        } catch (Exception e) {
            Logger logger = Splash.getLoggerObject();
            logger.log(Level.WARNING, "Supplier wise sale Error", e);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        FlatArcOrangeIJTheme.setup();
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Sales().setVisible(true);
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
