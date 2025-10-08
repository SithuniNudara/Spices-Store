package gui;

import com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import model.MySQL;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.PieSeries.PieSeriesRenderStyle;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.style.PieStyler.AnnotationType;

public class BusinessStatus extends javax.swing.JFrame {

    public BusinessStatus() {
        init();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        initComponents();
        displaySalesCharts();
        ImageIcon icon = new ImageIcon(SignIn.class.getResource("/resources/install.png"));
        this.setIconImage(icon.getImage());
    }

    public void init() {
        TotalIncome();
        Totalexpenses();
    }

    private void displaySalesCharts() {
        jPanel2.removeAll();
        jPanel5.removeAll();
        jPanel6.removeAll();

        jPanel2.setLayout(new GridLayout(1, 1));
        jPanel5.setLayout(new GridLayout(1, 1));
        jPanel6.setLayout(new GridLayout(1, 1));

        double totalIncome = fetchIncomeData();
        double totalExpenses = fetchExpensesData();

        jPanel2.add(createIncomeVsExpensesDoughnutChart(totalIncome, totalExpenses));

        jPanel5.add(createLineChartPanel(fetchDailySalesRevenueData(), "Sales Revenue (Latest)", "Date", "Total Sales Revenue"));

        jPanel6.add(createLineChartPanel(fetchDailyPurchaseCostData(), "Purchase Costs (Latest)", "Date", "Total Purchase Cost"));

        jPanel2.revalidate();
        jPanel2.repaint();
        jPanel5.revalidate();
        jPanel5.repaint();
        jPanel6.revalidate();
        jPanel6.repaint();
    }

    private XChartPanel<PieChart> createIncomeVsExpensesDoughnutChart(double income, double expenses) {
        PieChart chart = new PieChartBuilder().width(600).height(400).title("Income vs Expenses").build();

        chart.getStyler().setLegendVisible(false);
        chart.getStyler().setAnnotationType(AnnotationType.LabelAndPercentage);
        chart.getStyler().setAnnotationDistance(.82);
        chart.getStyler().setPlotContentSize(.9);
        chart.getStyler().setDefaultSeriesRenderStyle(PieSeriesRenderStyle.Donut);

        chart.addSeries("Income", income);
        chart.addSeries("Expenses", expenses);

        return new XChartPanel<>(chart);
    }

    private XChartPanel<CategoryChart> createLineChartPanel(Map<String, Double> data, String chartTitle, String xAxisTitle, String yAxisTitle) {
        CategoryChart chart = new CategoryChartBuilder().width(600).height(400).title(chartTitle)
                .xAxisTitle(xAxisTitle).yAxisTitle(yAxisTitle).build();

        chart.getStyler().setLegendVisible(false);
        chart.getStyler().setHasAnnotations(true);
        chart.getStyler().setAvailableSpaceFill(.6);
        chart.getStyler().setMarkerSize(6);

        // Sort dates (X-axis) chronologically
        List<String> dates = new ArrayList<>(data.keySet());
        Collections.sort(dates);
        List<Double> values = new ArrayList<>();
        for (String date : dates) {
            values.add(data.get(date));
        }

        chart.addSeries(chartTitle, dates, values);

        return new XChartPanel<>(chart);
    }

    private Map<String, Double> fetchDailySalesRevenueData() {
        Map<String, Double> data = new LinkedHashMap<>();
        try {

            ResultSet rs = MySQL.executeSearch("SELECT DATE(grn.date) AS sales_date, SUM(grn_item.qty * stock.price) AS total_sales_revenue "
                    + "FROM grn INNER JOIN grn_item ON grn.id = grn_item.grn_id "
                    + "INNER JOIN stock ON grn_item.stock_id = stock.id "
                    + "GROUP BY sales_date ORDER BY sales_date DESC LIMIT 5");

            while (rs.next()) {
                data.put(rs.getString("sales_date"), rs.getDouble("total_sales_revenue"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    private Map<String, Double> fetchDailyPurchaseCostData() {
        Map<String, Double> data = new LinkedHashMap<>();
        try {
            ResultSet rs = MySQL.executeSearch("SELECT DATE(invoice.date) AS purchase_date, SUM(invoice_item.qty * stock.price) AS total_purchase_cost "
                    + "FROM invoice INNER JOIN invoice_item ON invoice.id = invoice_item.invoice_id "
                    + "INNER JOIN stock ON invoice_item.stock_id = stock.id "
                    + "GROUP BY purchase_date ORDER BY purchase_date DESC LIMIT 5");

            while (rs.next()) {
                data.put(rs.getString("purchase_date"), rs.getDouble("total_purchase_cost"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    private double fetchIncomeData() {
        String incomeQuery = "SELECT SUM(invoice.paid_amount) - SUM(invoice.discount) AS total_income FROM invoice";
        double totalIncome = 0.0;
        try {
            // Using MySQL class to execute query
            ResultSet rs = MySQL.executeSearch(incomeQuery);
            if (rs.next()) {
                totalIncome = rs.getDouble("total_income");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalIncome;
    }

    private double fetchExpensesData() {
        String expensesQuery = "SELECT SUM(grn.paid_amount) AS total_expenses FROM grn";
        double totalExpenses = 0.0;
        try {
            // Using MySQL class to execute query
            ResultSet rs = MySQL.executeSearch(expensesQuery);
            if (rs.next()) {
                totalExpenses = rs.getDouble("total_expenses");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalExpenses;
    }

    private void TotalIncome() {
        Timer timer = new Timer(1000, e -> {
            try {

                ResultSet rs = MySQL.executeSearch("SELECT SUM(invoice.paid_amount) - SUM(invoice.discount) AS total_income FROM invoice");

                final String[] totalIncome = {""};

                if (rs.next()) {
                    totalIncome[0] = rs.getString("total_income");
                }

                SwingUtilities.invokeLater(() -> jTextField1.setText(totalIncome[0]));

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        timer.start();
    }

    private void Totalexpenses() {

        Timer timer = new Timer(1000, e -> {
            try {

                final String[] totalExpenses = {""};

                ResultSet rs = MySQL.executeSearch("SELECT SUM(grn.paid_amount) AS total_expenses FROM grn   ");

                if (rs.next()) {
                    if (rs.getString("total_expenses") == null) {
                        totalExpenses[0] = "0.00";
                    } else {
                        totalExpenses[0] = rs.getString("total_expenses");
                    }
                }

                double income = Double.parseDouble(jTextField1.getText());
                double expenses = Double.parseDouble(jTextField2.getText());
                double difference = income - expenses;

                jLabel6.setText(String.valueOf(difference));

                SwingUtilities.invokeLater(() -> jTextField2.setText(totalExpenses[0]));

                if (difference < 0) {
                    jLabel4.setText("Loss");
                } else {
                    jLabel4.setText("Profitable");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        timer.start();
    }

    private void Difference() {

        try {

            double income = Double.parseDouble(jTextField1.getText());
            double expenses = Double.parseDouble(jTextField2.getText());
            double difference = income - expenses;

            jLabel6.setText(String.valueOf(difference));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Business Status");
        setResizable(false);

        jPanel1.setPreferredSize(new java.awt.Dimension(961, 350));
        jPanel1.setLayout(new java.awt.GridLayout(1, 2));

        jPanel4.setPreferredSize(new java.awt.Dimension(1467, 400));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Total Income ");

        jTextField1.setEditable(false);
        jTextField1.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setText("0.00");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Total Expences");

        jTextField2.setEditable(false);
        jTextField2.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField2.setText("0.00");

        jLabel6.setBackground(new java.awt.Color(204, 0, 153));
        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Difference");
        jLabel6.setOpaque(true);

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Profit or Loss");
        jLabel4.setOpaque(true);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Status Of Business");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(394, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(82, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel4);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 897, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 350, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(1794, 500));
        jPanel3.setLayout(new java.awt.GridLayout(1, 0));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 897, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel5);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 897, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel6);

        getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        FlatArcOrangeIJTheme.setup();

        /* Create and display the form */
        SwingUtilities.invokeLater(() -> {
            BusinessStatus bs = new BusinessStatus();
            bs.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            bs.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
