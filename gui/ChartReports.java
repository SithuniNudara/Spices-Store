package gui;

import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Paint;
import java.sql.Connection;
import java.sql.DriverManager;
import model.MySQL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StackedBarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.*;
import org.knowm.xchart.style.markers.SeriesMarkers;
import java.util.*;

public class ChartReports extends javax.swing.JFrame {

    public ChartReports() {
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        initComponents();
        ImageIcon icon = new ImageIcon(SignIn.class.getResource("/resources/install.png"));
        this.setIconImage(icon.getImage());

        displaySalesCharts();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("ChartReports");
        setResizable(false);

        jPanel1.setLayout(new java.awt.GridLayout(2, 3));

        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 379, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 275, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel3);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 379, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 275, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel4);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 379, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 275, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel5);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 379, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 275, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel6);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 379, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 275, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel7);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 379, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 275, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel8);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseClicked
        JOptionPane.showMessageDialog(this, "OK");
    }//GEN-LAST:event_jPanel3MouseClicked

//    private void displaySalesCharts() {
//
//        jPanel3.removeAll();
//        jPanel4.removeAll();
//        jPanel5.removeAll();
//        jPanel6.removeAll();
//        jPanel7.removeAll();
//        jPanel8.removeAll();
//
//        // Add charts to the respective panels
//        jPanel3.add(createChartPanel(createProductWiseTotalSales(), "Product Wise Total"));
//        jPanel4.add(createColoredBarChartPanel(createSalesbyPaymentMethod(), "Sales by Payment Method", "Payment Method", "Total Sales"));
//        jPanel5.add(createColoredBarChartPanel(createSalesOverTimeMethod(), "Sales Over Time (Lastest)", "Invoice Date", "Total Sales"));
//        jPanel6.add(createChartPanel(SalesByEmployee(), "Sales By Employee"));
//        jPanel7.add(createStackedBarChartPanel(createProductPaymentWiseTotalSales(), "Payment Method for Spices"));
//        jPanel8.add(createAreaChartPanel(createCustomerSalesOverTime(), "Sales by Customer Over Time"));
//
//        // Refresh the layout
//        jPanel3.revalidate();
//        jPanel3.repaint();
//        jPanel4.revalidate();
//        jPanel4.repaint();
//        jPanel5.revalidate();
//        jPanel5.repaint();
//        jPanel6.revalidate();
//        jPanel6.repaint();
//        jPanel7.revalidate();
//        jPanel7.repaint();
//        jPanel8.revalidate();
//        jPanel8.repaint();
//
//        // Set layout
//        jPanel3.setLayout(new java.awt.GridLayout(1, 1));
//        jPanel4.setLayout(new java.awt.GridLayout(1, 1));
//        jPanel5.setLayout(new java.awt.GridLayout(1, 1));
//        jPanel6.setLayout(new java.awt.GridLayout(1, 1));
//        jPanel7.setLayout(new java.awt.GridLayout(1, 1));
//        jPanel8.setLayout(new java.awt.GridLayout(1, 1));
//    }
//
//    private ChartPanel createChartPanel(DefaultPieDataset dataset, String chartTitle) {
//        JFreeChart pieChart = ChartFactory.createPieChart(
//                chartTitle, // chart title
//                dataset, // dataset
//                false, // include legend
//                true,
//                false
//        );
//        return new ChartPanel(pieChart);
//    }
//
//    private DefaultPieDataset createProductWiseTotalSales() {
//        DefaultPieDataset dataset = new DefaultPieDataset();
//        try {
//            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/spicesandherbs", "root", "1234");
//            Statement stmt = conn.createStatement();
//            String query = "SELECT product.name AS product_name, SUM(invoice.paid_amount) AS total_sales \n"
//                    + "FROM invoice INNER JOIN invoice_item ON invoice.id = invoice_item.invoice_id \n"
//                    + "INNER JOIN stock ON invoice_item.stock_id = stock.id \n"
//                    + "INNER JOIN product ON product.id = stock.product_id GROUP BY product.name";
//            ResultSet rs = stmt.executeQuery(query);
//
//            while (rs.next()) {
//                dataset.setValue(rs.getString("product_name"), rs.getInt("total_sales"));
//            }
//            rs.close();
//            stmt.close();
//            conn.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return dataset;
//    }
//
//    private DefaultCategoryDataset createSalesbyPaymentMethod() {
//        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//        try {
//            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/spicesandherbs", "root", "1234");
//            Statement stmt = conn.createStatement();
//            String query = "SELECT payment_method.name AS payment_method_name, SUM(invoice.paid_amount) AS total_sales "
//                    + "FROM invoice "
//                    + "INNER JOIN payment_method ON invoice.payment_method_id = payment_method.id "
//                    + "GROUP BY payment_method.name";
//            ResultSet rs = stmt.executeQuery(query);
//
//            while (rs.next()) {
//                String paymentMethodName = rs.getString("payment_method_name");
//                double totalSales = rs.getDouble("total_sales");
//
//                // Add the payment method and total sales to the dataset
//                dataset.addValue(totalSales, "Total Sales", paymentMethodName);
//            }
//
//            rs.close();
//            stmt.close();
//            conn.close();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return dataset;
//    }
//
//    private DefaultCategoryDataset createSalesOverTimeMethod() {
//        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//        try {
//            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/spicesandherbs", "root", "1234");
//            Statement stmt = conn.createStatement();
//            String query = "SELECT invoice.date AS invoice_date, SUM(invoice.paid_amount) AS total_sales "
//                    + "FROM invoice "
//                    + "GROUP BY invoice.date "
//                    + "ORDER BY invoice.date LIMIT 10";
//            ResultSet rs = stmt.executeQuery(query);
//
//            while (rs.next()) {
//                String invoiceDate = rs.getString("invoice_date");
//                double totalSales = rs.getDouble("total_sales");
//
//                // Add the date and total sales to the dataset
//                dataset.addValue(totalSales, "Total Sales", invoiceDate);
//            }
//
//            rs.close();
//            stmt.close();
//            conn.close();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return dataset;
//    }
//
//    private DefaultPieDataset SalesByEmployee() {
//        DefaultPieDataset dataset = new DefaultPieDataset();
//        try {
//            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/spicesandherbs", "root", "1234");
//            Statement stmt = conn.createStatement();
//            String query = "SELECT emlpoyee.first_name AS employee_name, SUM(invoice.paid_amount) AS total_sales "
//                    + "FROM invoice "
//                    + "INNER JOIN emlpoyee ON invoice.emlpoyee_email = emlpoyee.email "
//                    + "GROUP BY emlpoyee.first_name";
//            ResultSet rs = stmt.executeQuery(query);
//
//            while (rs.next()) {
//                dataset.setValue(rs.getString("employee_name"), rs.getInt("total_sales"));
//            }
//            rs.close();
//            stmt.close();
//            conn.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return dataset;
//    }
//
//    private ChartPanel createColoredBarChartPanel(DefaultCategoryDataset dataset, String chartTitle, String xAxisLabel, String yAxisLabel) {
//        JFreeChart barChart = ChartFactory.createBarChart(
//                chartTitle, // Chart title
//                xAxisLabel, // X-axis Label (dynamic)
//                yAxisLabel, // Y-axis Label (dynamic)
//                dataset, // Data
//                PlotOrientation.VERTICAL, // Orientation
//                false, // Include legend
//                true, // Tooltips
//                false // URLs
//        );
//
//        // Customizing the bar renderer for different colors
//        CategoryPlot plot = barChart.getCategoryPlot();
//        BarRenderer renderer = new BarRenderer() {
//            @Override
//            public Paint getItemPaint(int row, int column) {
//                // Return different colors for each bar
//                return new Color[]{Color.BLUE, Color.GREEN, Color.ORANGE, Color.RED, Color.MAGENTA}[column % 5];
//            }
//        };
//
//        // Set tooltip generator for the renderer
//        renderer.setDefaultToolTipGenerator(new StandardCategoryToolTipGenerator());
//        plot.setRenderer(renderer);
//
//        // Set font size for axis labels
//        plot.getDomainAxis().setLabelFont(new Font("SansSerif", Font.PLAIN, 10));
//        plot.getRangeAxis().setLabelFont(new Font("SansSerif", Font.PLAIN, 10));
//
//        return new ChartPanel(barChart);
//    }
//
//    private DefaultCategoryDataset createProductPaymentWiseTotalSales() {
//        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//
//        try {
//
//            ResultSet rs = MySQL.executeSearch("SELECT product.name AS product_name, payment_method.name AS payment_method_name, SUM(invoice.paid_amount) AS total_sales "
//                    + "FROM invoice "
//                    + "INNER JOIN invoice_item ON invoice.id = invoice_item.invoice_id "
//                    + "INNER JOIN stock ON invoice_item.stock_id = stock.id "
//                    + "INNER JOIN product ON product.id = stock.product_id "
//                    + "INNER JOIN payment_method ON invoice.payment_method_id = payment_method.id "
//                    + "GROUP BY product.name, payment_method.name");
//
//            // Populate dataset with product names, payment methods, and total sales
//            while (rs.next()) {
//                String productName = rs.getString("product_name");
//                String paymentMethodName = rs.getString("payment_method_name");
//                double totalSales = rs.getDouble("total_sales");
//
//                // Add the data to the dataset (payment method stacked for each product)
//                dataset.addValue(totalSales, paymentMethodName, productName);
//            }
//            rs.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return dataset;
//    }
//
///// Method to create the ChartPanel for the stacked bar chart
//    private ChartPanel createStackedBarChartPanel(DefaultCategoryDataset dataset, String chartTitle) {
//        // Create the Stacked Bar Chart
//        JFreeChart stackedBarChart = ChartFactory.createStackedBarChart(
//                chartTitle, // Chart title
//                "Product", // X-axis Label (Products)
//                "Total Sales", // Y-axis Label (Sales Amount)
//                dataset, // Data
//                PlotOrientation.VERTICAL, // Orientation
//                true, // Include legend
//                true, // Tooltips enabled
//                false // URLs
//        );
//
//        // Customize the Stacked Bar Renderer
//        CategoryPlot plot = stackedBarChart.getCategoryPlot();
//        StackedBarRenderer renderer = new StackedBarRenderer();
//
//        // Enable tooltips
//        renderer.setDefaultToolTipGenerator(new StandardCategoryToolTipGenerator());
//
//        // Apply the customized renderer to the plot
//        plot.setRenderer(renderer);
//
//        // Return the ChartPanel containing the chart
//        return new ChartPanel(stackedBarChart);
//    }
//
//    // Method to create the dataset for the area chart
//    private DefaultCategoryDataset createCustomerSalesOverTime() {
//        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//
//        try {
//            // SQL query to get sales by customer over time
//            ResultSet rs = MySQL.executeSearch("SELECT customer.first_name AS customer_name, invoice.date AS invoice_date, SUM(invoice.paid_amount) AS total_sales "
//                    + "FROM invoice "
//                    + "INNER JOIN customer ON invoice.customer_mobile = customer.mobile "
//                    + "GROUP BY customer.first_name, invoice.date "
//                    + "ORDER BY invoice.date");
//
//            // Populate dataset with customer names, invoice dates, and total sales
//            while (rs.next()) {
//                String customerName = rs.getString("customer_name");
//                String invoiceDate = rs.getString("invoice_date");
//                double totalSales = rs.getDouble("total_sales");
//
//                // Add the data to the dataset (customer sales over time)
//                dataset.addValue(totalSales, customerName, invoiceDate);
//            }
//            rs.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return dataset;
//    }
//
//// Method to create the ChartPanel for the area chart
//    private ChartPanel createAreaChartPanel(DefaultCategoryDataset dataset, String chartTitle) {
//        // Create the Area Chart
//        JFreeChart areaChart = ChartFactory.createAreaChart(
//                chartTitle, // Chart title
//                "Invoice Date", // X-axis Label (Dates)
//                "Total Sales", // Y-axis Label (Sales Amount)
//                dataset, // Data
//                PlotOrientation.VERTICAL, // Orientation
//                true, // Include legend
//                true, // Tooltips
//                false // URLs
//        );
//
//        // Return the ChartPanel containing the area chart
//        return new ChartPanel(areaChart);
//    }
private void displaySalesCharts() {
    jPanel3.removeAll();
    jPanel4.removeAll();
    jPanel5.removeAll();
    jPanel6.removeAll();
    jPanel7.removeAll();
    jPanel8.removeAll();

    jPanel3.add(createPieChartPanel(createProductWiseTotalSales(), "Product Wise Total Sales"));
    jPanel4.add(createBarChartPanel(createSalesbyPaymentMethod(), "Sales by Payment Method", "Payment Method", "Total Sales"));
    jPanel5.add(createBarChartPanel(createSalesOverTime(), "Sales Over Time (Latest)", "Invoice Date", "Total Sales"));
    jPanel6.add(createPieChartPanel(SalesByEmployee(), "Sales By Employee"));
    jPanel7.add(createStackedBarChartPanel(createProductPaymentWiseTotalSales(), "Payment Method for Products"));
    jPanel8.add(createAreaChartPanel(createCustomerSalesOverTime(), "Sales by Customer Over Time"));

    // Refresh and layout
    JPanel[] panels = {jPanel3, jPanel4, jPanel5, jPanel6, jPanel7, jPanel8};
    for (JPanel panel : panels) {
        panel.revalidate();
        panel.repaint();
        panel.setLayout(new GridLayout(1, 1));
    }
}

// Pie Chart
private XChartPanel<PieChart> createPieChartPanel(Map<String, Number> data, String title) {
    PieChart chart = new PieChartBuilder().title(title).width(600).height(400).build();
    data.forEach(chart::addSeries);
    chart.getStyler().setLegendVisible(true);
    return new XChartPanel<>(chart);
}

// Bar Chart
private XChartPanel<CategoryChart> createBarChartPanel(Map<String, Number> data, String title, String xAxisTitle, String yAxisTitle) {
    CategoryChart chart = new CategoryChartBuilder().title(title).xAxisTitle(xAxisTitle).yAxisTitle(yAxisTitle).width(600).height(400).build();
    chart.getStyler().setLegendVisible(false);
    chart.getStyler().setHasAnnotations(true);
    chart.addSeries("Total Sales", new ArrayList<>(data.keySet()), new ArrayList<>(data.values()));
    return new XChartPanel<>(chart);
}

// Stacked Bar Chart
private XChartPanel<CategoryChart> createStackedBarChartPanel(Map<String, Map<String, Number>> data, String title) {
    CategoryChart chart = new CategoryChartBuilder().title(title).xAxisTitle("Product").yAxisTitle("Total Sales").width(600).height(400).build();
    chart.getStyler().setLegendVisible(true);
    chart.getStyler().setStacked(true);

    Set<String> categories = data.keySet();
    Set<String> paymentMethods = new HashSet<>();
    data.values().forEach(map -> paymentMethods.addAll(map.keySet()));

    for (String paymentMethod : paymentMethods) {
        List<Number> values = new ArrayList<>();
        for (String category : categories) {
            Map<String, Number> categoryData = data.getOrDefault(category, new HashMap<>());
            values.add(categoryData.getOrDefault(paymentMethod, 0));
        }
        chart.addSeries(paymentMethod, new ArrayList<>(categories), values);
    }
    return new XChartPanel<>(chart);
}

// Area Chart
private XChartPanel<XYChart> createAreaChartPanel(Map<String, Map<String, Number>> data, String title) {
    XYChart chart = new XYChartBuilder()
            .title(title)
            .xAxisTitle("Invoice Date")
            .yAxisTitle("Total Sales")
            .width(600)
            .height(400)
            .build();

    chart.getStyler().setLegendVisible(true);
    chart.getStyler().setMarkerSize(4);

    // Dates (x-axis labels)
    Set<String> datesSet = new TreeSet<>();
    data.values().forEach(map -> datesSet.addAll(map.keySet()));
    List<String> dates = new ArrayList<>(datesSet);

    // Convert date strings to numeric indices for XYChart
    List<Double> xData = new ArrayList<>();
    for (int i = 0; i < dates.size(); i++) {
        xData.add((double) i);
    }

    // Add each customer series
    for (Map.Entry<String, Map<String, Number>> entry : data.entrySet()) {
        String customer = entry.getKey();
        Map<String, Number> sales = entry.getValue();

        List<Double> yData = new ArrayList<>();
        for (String date : dates) {
            yData.add(sales.getOrDefault(date, 0).doubleValue());
        }

        XYSeries series = chart.addSeries(customer, xData, yData);
        series.setXYSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Area);
    }


    return new XChartPanel<>(chart);
}

// Helper to map numeric indices to date labels
private Map<Double, Object> createXAxisTickLabelsMap(List<Double> xData, List<String> dates) {
    Map<Double, Object> tickLabelsMap = new HashMap<>();
    for (int i = 0; i < xData.size(); i++) {
        tickLabelsMap.put(xData.get(i), dates.get(i));
    }
    return tickLabelsMap;
}


// Datasets (as Map structures)

// Pie dataset
private Map<String, Number> createProductWiseTotalSales() {
    Map<String, Number> data = new HashMap<>();
    try (Connection conn = getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(
                 "SELECT product.name, SUM(invoice.paid_amount) FROM invoice " +
                         "INNER JOIN invoice_item ON invoice.id = invoice_item.invoice_id " +
                         "INNER JOIN stock ON invoice_item.stock_id = stock.id " +
                         "INNER JOIN product ON product.id = stock.product_id " +
                         "GROUP BY product.name")) {
        while (rs.next()) data.put(rs.getString(1), rs.getDouble(2));
    } catch (Exception e) { e.printStackTrace(); }
    return data;
}

private Map<String, Number> createSalesbyPaymentMethod() {
    Map<String, Number> data = new HashMap<>();
    try (Connection conn = getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(
                 "SELECT payment_method.name, SUM(invoice.paid_amount) FROM invoice " +
                         "INNER JOIN payment_method ON invoice.payment_method_id = payment_method.id " +
                         "GROUP BY payment_method.name")) {
        while (rs.next()) data.put(rs.getString(1), rs.getDouble(2));
    } catch (Exception e) { e.printStackTrace(); }
    return data;
}

private Map<String, Number> createSalesOverTime() {
    Map<String, Number> data = new LinkedHashMap<>();
    try (Connection conn = getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(
                 "SELECT invoice.date, SUM(invoice.paid_amount) FROM invoice " +
                         "GROUP BY invoice.date ORDER BY invoice.date DESC LIMIT 5")) {
        while (rs.next()) data.put(rs.getString(1), rs.getDouble(2));
    } catch (Exception e) { e.printStackTrace(); }
    return data;
}

private Map<String, Number> SalesByEmployee() {
    Map<String, Number> data = new HashMap<>();
    try (Connection conn = getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(
                 "SELECT emlpoyee.first_name, SUM(invoice.paid_amount) FROM invoice " +
                         "INNER JOIN emlpoyee ON invoice.emlpoyee_email = emlpoyee.email " +
                         "GROUP BY emlpoyee.first_name")) {
        while (rs.next()) data.put(rs.getString(1), rs.getDouble(2));
    } catch (Exception e) { e.printStackTrace(); }
    return data;
}

// Stacked Bar dataset
private Map<String, Map<String, Number>> createProductPaymentWiseTotalSales() {
    Map<String, Map<String, Number>> data = new HashMap<>();
    try (Connection conn = getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(
                 "SELECT product.name, payment_method.name, SUM(invoice.paid_amount) FROM invoice " +
                         "INNER JOIN invoice_item ON invoice.id = invoice_item.invoice_id " +
                         "INNER JOIN stock ON invoice_item.stock_id = stock.id " +
                         "INNER JOIN product ON product.id = stock.product_id " +
                         "INNER JOIN payment_method ON invoice.payment_method_id = payment_method.id " +
                         "GROUP BY product.name, payment_method.name")) {
        while (rs.next()) {
            String product = rs.getString(1);
            String payment = rs.getString(2);
            double sales = rs.getDouble(3);
            data.computeIfAbsent(product, k -> new HashMap<>()).put(payment, sales);
        }
    } catch (Exception e) { e.printStackTrace(); }
    return data;
}

// Area Chart dataset
private Map<String, Map<String, Number>> createCustomerSalesOverTime() {
    Map<String, Map<String, Number>> data = new HashMap<>();
    try (Connection conn = getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(
                 "SELECT customer.first_name, invoice.date, SUM(invoice.paid_amount) FROM invoice " +
                         "INNER JOIN customer ON invoice.customer_mobile = customer.mobile " +
                         "GROUP BY customer.first_name, invoice.date ORDER BY invoice.date")) {
        while (rs.next()) {
            String customer = rs.getString(1);
            String date = rs.getString(2);
            double sales = rs.getDouble(3);
            data.computeIfAbsent(customer, k -> new HashMap<>()).put(date, sales);
        }
    } catch (Exception e) { e.printStackTrace(); }
    return data;
}

// Utility method for DB connection
private Connection getConnection() throws Exception {
    return DriverManager.getConnection("jdbc:mysql://localhost:3306/spicesandherbs", "root", "1234");
}
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        FlatMacLightLaf.setup();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChartReports().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    // End of variables declaration//GEN-END:variables
}
