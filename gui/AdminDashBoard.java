package gui;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.intellijthemes.FlatArcDarkOrangeIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme;
import java.awt.Color;
import model.MySQL;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.style.PieStyler;

public class AdminDashBoard extends javax.swing.JFrame {

    public static String email = "";
    public static String firstNameofAdmin = "";

    public AdminDashBoard(String email, String fname, String lname) {
        initComponents();
        init();
        ImageIcon icon = new ImageIcon(SignIn.class.getResource("/resources/install.png"));
        this.setIconImage(icon.getImage());
        this.email = email;
        jLabel4.setText(fname);
        firstNameofAdmin = fname;
        jLabel5.setText(email);

        setDate();

        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

    }

    public void init() {
        jButton1.putClientProperty(FlatClientProperties.STYLE, "arc:30");
        jButton2.putClientProperty(FlatClientProperties.STYLE, "arc:30");
        jButton3.putClientProperty(FlatClientProperties.STYLE, "arc:30");
        jButton4.putClientProperty(FlatClientProperties.STYLE, "arc:30");
        jButton5.putClientProperty(FlatClientProperties.STYLE, "arc:30");
        jButton6.putClientProperty(FlatClientProperties.STYLE, "arc:30");
        jButton7.putClientProperty(FlatClientProperties.STYLE, "arc:30");
        jButton8.putClientProperty(FlatClientProperties.STYLE, "arc:30");
        jButton9.putClientProperty(FlatClientProperties.STYLE, "arc:30");
        jButton10.putClientProperty(FlatClientProperties.STYLE, "arc:30");
        jButton11.putClientProperty(FlatClientProperties.STYLE, "arc:30");
        jButton12.putClientProperty(FlatClientProperties.STYLE, "arc:30");
        jPanel5.putClientProperty(FlatClientProperties.STYLE, "arc:20");
        jPanel6.putClientProperty(FlatClientProperties.STYLE, "arc:20");
        jPanel7.putClientProperty(FlatClientProperties.STYLE, "arc:20");
        jPanel8.putClientProperty(FlatClientProperties.STYLE, "arc:20");
        jPanel9.putClientProperty(FlatClientProperties.STYLE, "arc:20");
        jPanel10.putClientProperty(FlatClientProperties.STYLE, "arc:20");
        jPanel11.putClientProperty(FlatClientProperties.STYLE, "arc:20");
        jPanel12.putClientProperty(FlatClientProperties.STYLE, "arc:20");
        jPanel13.putClientProperty(FlatClientProperties.STYLE, "arc:20");
        jPanel14.putClientProperty(FlatClientProperties.STYLE, "arc:20");
        jPanel15.putClientProperty(FlatClientProperties.STYLE, "arc:20");
        jPanel16.putClientProperty(FlatClientProperties.STYLE, "arc:20");

        dashimage();
        dashEmployeeImage();
        employeeCount();
        dashCustomerImage();
        CustomerCount();

        dashSupplierImage();
        SupplierCount();

        dashProductImage();
        ProductCount();
        dashStockImage();
        StockCount();
        dashGRNImage();
        GRNCount();

        dashReportImage1();
        dashReportImage2();
        dashReportImage3();
        dashReportImage4();
        dashReportImage5();
        dashReportImage6();

        TotalIncome();
        DailyIncome();
        Totalexpenses();

    }

    public void setDate() {
        Timer timer = new Timer(1000, e -> {
            // Get the current time and date
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss a"); // Format for time
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM d"); // Format for date

            // Format the time and date
            String time = timeFormat.format(calendar.getTime());
            String date = dateFormat.format(calendar.getTime());

            // Update the jLabel6 with time and date using the Event Dispatch Thread
            SwingUtilities.invokeLater(() -> {
                jLabel6.setText("<html><div style='text-align:center;'>" + time + " " + date + "</div></html>");
            });

        });

        // Start the timer to update the time every second
        timer.start();
    }

    private void dashimage() {
        FlatSVGIcon icon = new FlatSVGIcon("resources/logo2.svg", jLabel1.getWidth(), jLabel1.getHeight());
        jLabel1.setIcon(icon);

    }

    private void dashEmployeeImage() {
        FlatSVGIcon icon = new FlatSVGIcon("resources/dash-employee.svg", jLabel9.getWidth(), jLabel9.getHeight());
        jLabel9.setIcon(icon);

    }

    private void dashProductImage() {
        FlatSVGIcon icon = new FlatSVGIcon("resources/dash-Product.svg", jLabel23.getWidth(), jLabel23.getHeight());
        jLabel23.setIcon(icon);

    }

    private void dashSupplierImage() {
        FlatSVGIcon icon = new FlatSVGIcon("resources/supplier-count.svg", jLabel12.getWidth(), jLabel12.getHeight());
        jLabel12.setIcon(icon);

    }

    private void dashCustomerImage() {
        FlatSVGIcon icon = new FlatSVGIcon("resources/customer-count.svg", jLabel14.getWidth(), jLabel14.getHeight());
        jLabel14.setIcon(icon);

    }

    private void dashGRNImage() {
        FlatSVGIcon icon = new FlatSVGIcon("resources/grn.svg", jLabel17.getWidth(), jLabel17.getHeight());
        jLabel17.setIcon(icon);

    }

    private void dashStockImage() {
        FlatSVGIcon icon = new FlatSVGIcon("resources/stock.svg", jLabel20.getWidth(), jLabel20.getHeight());
        jLabel20.setIcon(icon);

    }

    private void dashReportImage1() {
        FlatSVGIcon icon = new FlatSVGIcon("resources/report.svg", jLabel26.getWidth(), jLabel26.getHeight());
        jLabel26.setIcon(icon);

    }

    private void dashReportImage2() {
        FlatSVGIcon icon = new FlatSVGIcon("resources/report2.svg", jLabel29.getWidth(), jLabel29.getHeight());
        jLabel29.setIcon(icon);

    }

    private void dashReportImage3() {
        FlatSVGIcon icon = new FlatSVGIcon("resources/report3.svg", jLabel32.getWidth(), jLabel32.getHeight());
        jLabel32.setIcon(icon);

    }

    private void dashReportImage4() {
        FlatSVGIcon icon = new FlatSVGIcon("resources/dash-profit.svg", jLabel30.getWidth(), jLabel30.getHeight());
        jLabel30.setIcon(icon);

    }

    private void dashReportImage5() {
        FlatSVGIcon icon = new FlatSVGIcon("resources/dash-profit.svg", jLabel35.getWidth(), jLabel35.getHeight());
        jLabel35.setIcon(icon);

    }

    private void dashReportImage6() {
        FlatSVGIcon icon = new FlatSVGIcon("resources/dash-loss.svg", jLabel38.getWidth(), jLabel38.getHeight());
        jLabel38.setIcon(icon);

    }

    private void employeeCount() {

        Timer timer = new Timer(1000, e -> {  // Refresh every 1 seconds (5000 ms)
            try {
                // Declare an array to hold the count (to satisfy the lambda expression rule)
                final int[] count = {0};

                // Execute your query to get the employee count
                ResultSet rs = MySQL.executeSearch("SELECT * FROM `emlpoyee` WHERE `employee_type_id` = '2' AND `emlpoyee`.`status` = 'Active' ");

                // Count the number of rows returned by the query
                while (rs.next()) {
                    count[0] += 1;
                }

                // Ensure the jLabel8 update is executed on the Event Dispatch Thread
                SwingUtilities.invokeLater(() -> jLabel8.setText(String.valueOf(count[0])));

            } catch (Exception ex) {
                Logger logger = Splash.getLoggerObject();
                logger.log(Level.WARNING, "Employee Count Error", ex);
            }
        });

        // Start the timer
        timer.start();
    }

    private void ProductCount() {
        Timer timer = new Timer(1000, e -> {  // Refresh every 1 seconds (5000 ms)
            try {
                // Declare an array to hold the count (to satisfy the lambda expression rule)
                final int[] count = {0};

                // Execute your query to get the employee count
                ResultSet rs = MySQL.executeSearch("SELECT * FROM `product`");

                // Count the number of rows returned by the query
                while (rs.next()) {
                    count[0] += 1;
                }

                // Ensure the jLabel24 update is executed on the Event Dispatch Thread
                SwingUtilities.invokeLater(() -> jLabel24.setText(String.valueOf(count[0])));

            } catch (Exception ex) {
                Logger logger = Splash.getLoggerObject();
                logger.log(Level.WARNING, "Product Count Error", ex);
            }
        });

        // Start the timer
        timer.start();
    }

    private void SupplierCount() {
        Timer timer = new Timer(1000, e -> {  // Refresh every 1 seconds (5000 ms)
            try {
                // Declare an array to hold the count (to satisfy the lambda expression rule)
                final int[] count = {0};

                // Execute your query to get the employee count
                ResultSet rs = MySQL.executeSearch("SELECT * FROM `supplier` WHERE `status` = 'Active' ");

                // Count the number of rows returned by the query
                while (rs.next()) {
                    count[0] += 1;
                }

                // Ensure the jLabel24 update is executed on the Event Dispatch Thread
                SwingUtilities.invokeLater(() -> jLabel11.setText(String.valueOf(count[0])));

            } catch (Exception ex) {
                Logger logger = Splash.getLoggerObject();
                logger.log(Level.WARNING, "Supplier Count Error", ex);
            }
        });

        // Start the timer
        timer.start();
    }

    private void CustomerCount() {
        Timer timer = new Timer(1000, e -> {  // Refresh every 1 seconds 
            try {
                // Declare an array to hold the count (to satisfy the lambda expression rule)
                final int[] count = {0};

                // Execute your query to get the employee count
                ResultSet rs = MySQL.executeSearch("SELECT * FROM `customer` WHERE `status` = 'Active' ");

                // Count the number of rows returned by the query
                while (rs.next()) {
                    count[0] += 1;
                }

                // Ensure the jLabel24 update is executed on the Event Dispatch Thread
                SwingUtilities.invokeLater(() -> jLabel15.setText(String.valueOf(count[0])));

            } catch (Exception ex) {
                Logger logger = Splash.getLoggerObject();
                logger.log(Level.WARNING, "Not A Admin or Employee", ex);
            }
        });

        // Start the timer
        timer.start();
    }

    private void GRNCount() {
        Timer timer = new Timer(1000, e -> {  // Refresh every 1 seconds (5000 ms)
            try {
                // Declare an array to hold the count (to satisfy the lambda expression rule)
                final int[] count = {0};

                // Execute your query to get the employee count
                ResultSet rs = MySQL.executeSearch("SELECT * FROM `grn`");

                // Count the number of rows returned by the query
                while (rs.next()) {
                    count[0] += 1;
                }

                // Ensure the jLabel24 update is executed on the Event Dispatch Thread
                SwingUtilities.invokeLater(() -> jLabel18.setText(String.valueOf(count[0])));

            } catch (Exception ex) {
                Logger logger = Splash.getLoggerObject();
                logger.log(Level.WARNING, "GRN Count Error", ex);
            }
        });

        // Start the timer
        timer.start();
    }

    private void StockCount() {
        Timer timer = new Timer(1000, e -> {  // Refresh every 1 seconds (5000 ms)
            try {
                // Declare an array to hold the count (to satisfy the lambda expression rule)
                final int[] count = {0};

                // Execute your query to get the employee count
                ResultSet rs = MySQL.executeSearch("SELECT * FROM `stock`");

                // Count the number of rows returned by the query
                while (rs.next()) {
                    count[0] += 1;
                }

                // Ensure the jLabel24 update is executed on the Event Dispatch Thread
                SwingUtilities.invokeLater(() -> jLabel21.setText(String.valueOf(count[0])));

            } catch (Exception ex) {
                Logger logger = Splash.getLoggerObject();
                logger.log(Level.WARNING, "Stock Count Error", ex);
            }
        });

        // Start the timer
        timer.start();
    }

    private void TotalIncome() {
        Timer timer = new Timer(1000, e -> {  // Refresh every 1 seconds 
            try {
                // Execute your query to get the total income
                ResultSet rs = MySQL.executeSearch("SELECT SUM(invoice.paid_amount) - SUM(invoice.discount) AS total_income FROM invoice");

                // Declare an array to hold the total income (to satisfy the lambda expression rule)
                final String[] totalIncome = {""};

                // Retrieve the result from the query
                if (rs.next()) {
                    totalIncome[0] = rs.getString("total_income");
                }

                // Ensure the jLabel33 update is executed on the Event Dispatch Thread
                SwingUtilities.invokeLater(() -> jLabel33.setText(totalIncome[0]));

            } catch (Exception ex) {
                Logger logger = Splash.getLoggerObject();
                logger.log(Level.WARNING, "TotalIncome error", ex);
            }
        });

        // Start the timer
        timer.start();
    }

    private void DailyIncome() {
        Timer timer = new Timer(1000, e -> {  // Refresh every 1 seconds
            try {
                // Declare an array to hold the total income (to satisfy the lambda expression rule)
                final String[] todayIncome = {"0.00"};

                // Execute your query to get today's total income
                ResultSet rs = MySQL.executeSearch("SELECT SUM(invoice.paid_amount) AS total_income "
                        + "FROM invoice "
                        + "WHERE `date` = CURDATE()");

                // Retrieve the result from the query
                if (rs.next()) {
                    // If the total income is null, set the value to "0.00"
                    if (rs.getString("total_income") == null) {
                        todayIncome[0] = "0.00";
                    } else {
                        todayIncome[0] = rs.getString("total_income");
                    }
                }

                // Ensure the jLabel36 update is executed on the Event Dispatch Thread
                SwingUtilities.invokeLater(() -> jLabel36.setText(todayIncome[0]));

            } catch (Exception ex) {
                Logger logger = Splash.getLoggerObject();
                logger.log(Level.WARNING, "Daily Income Error", ex);
            }
        });

        // Start the timer
        timer.start();
    }

    private void Totalexpenses() {
        Timer timer = new Timer(1000, e -> {  // Refresh every 1 seconds
            try {
                // Declare an array to hold the total expenses (to satisfy the lambda expression rule)
                final String[] totalExpenses = {""};

                // Execute your query to get the total expenses
                ResultSet rs = MySQL.executeSearch("SELECT SUM(grn.paid_amount) AS total_expenses FROM grn");

                // Retrieve the result from the query
                if (rs.next()) {
                    // If the total expenses is null, set the value to "0.00"
                    if (rs.getString("total_expenses") == null) {
                        totalExpenses[0] = "0.00";
                    } else {
                        totalExpenses[0] = rs.getString("total_expenses");
                    }
                }

                // Ensure the jLabel39 update is executed on the Event Dispatch Thread
                SwingUtilities.invokeLater(() -> jLabel39.setText(totalExpenses[0]));

            } catch (Exception ex) {
                Logger logger = Splash.getLoggerObject();
                logger.log(Level.WARNING, "Total Expenses  Error", ex);
            }
        });

        // Start the timer
        timer.start();

    }

    public void GenderChartPanel() {

        List<String> genderDescriptions = new ArrayList<>();
        List<Integer> employeeCounts = new ArrayList<>();

        try {
            // Execute SQL query to get employee count grouped by gender
            ResultSet rs = MySQL.executeSearch(
                    "SELECT gender.name, COUNT(*) AS employee_count \n"
                    + "FROM emlpoyee INNER JOIN gender \n"
                    + "ON emlpoyee.gender_id = gender.id\n"
                    + "WHERE emlpoyee.employee_type_id = '2' AND emlpoyee.`status`= 'Active'\n"
                    + "GROUP BY gender.name"
            );

            // Populate lists with gender name and employee count
            while (rs.next()) {
                String genderDescription = rs.getString("gender.name");
                int employeeCount = rs.getInt("employee_count");
                genderDescriptions.add(genderDescription);
                employeeCounts.add(employeeCount);
            }

        } catch (Exception e) {
            Logger logger = Splash.getLoggerObject();
            logger.log(Level.WARNING, "Gender Chart Panel Error", e);
        }

        // Create XChart PieChart
        PieChart chart = new PieChartBuilder()
                .width(500)
                .height(500)
                .title("Employee Gender Distribution")
                .build();

        // Customize chart style
        chart.getStyler().setLegendVisible(true);
        chart.getStyler().setAnnotationType(PieStyler.AnnotationType.LabelAndPercentage);
        chart.getStyler().setAnnotationDistance(1.15);
        chart.getStyler().setPlotContentSize(0.8);
        chart.getStyler().setChartTitleVisible(true);

        // Add data
        for (int i = 0; i < genderDescriptions.size(); i++) {
            chart.addSeries(genderDescriptions.get(i), employeeCounts.get(i));
        }

        // Create a chart panel for Swing
        XChartPanel<PieChart> chartPanel = new XChartPanel<>(chart);

        // Display in a JFrame (similar to original behavior)
        JFrame frame = new JFrame();
        frame.setTitle("Gender Distribution Chart");
        frame.setBackground(Color.WHITE);
        frame.setSize(800, 600);
        frame.setContentPane(chartPanel);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

    }

    public void SupplierChartPanel() {
        Map<String, Integer> companySupplierCounts = new LinkedHashMap<>();

        try {
            ResultSet rs = MySQL.executeSearch(
                    "SELECT company.name, COUNT(supplier.mobile) AS supplier_count \n"
                    + "FROM supplier\n"
                    + "INNER JOIN company ON supplier.company_id = company.id \n"
                    + "WHERE supplier.`status` = 'Active' AND company.`status` = 'Active'\n"
                    + "GROUP BY company.name"
            );

            while (rs.next()) {
                String companyName = rs.getString("company.name");
                int supplierCount = rs.getInt("supplier_count");
                companySupplierCounts.put(companyName, supplierCount);
            }
        } catch (Exception e) {
            Logger logger = Splash.getLoggerObject();
            logger.log(Level.WARNING, "Supplier Chart Panel Error", e);
        }

        // Build XChart Bar Chart
        CategoryChart chart = new CategoryChartBuilder()
                .title("Suppliers per Company")
                .xAxisTitle("Company")
                .yAxisTitle("Number of Suppliers")
                .width(800)
                .height(600)
                .build();

        chart.addSeries("Suppliers", new ArrayList<>(companySupplierCounts.keySet()), new ArrayList<>(companySupplierCounts.values()));

        XChartPanel<?> chartPanel = new XChartPanel<>(chart);

        JFrame frame = new JFrame("Supplier Count");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(chartPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void CustomerChartPanel() {
        Map<String, Integer> dateCustomerCounts = new LinkedHashMap<>();

        try {
            ResultSet rs = MySQL.executeSearch(
                    "SELECT customer.registerd_date, COUNT(DISTINCT mobile) AS customer_count \n"
                    + "FROM customer\n"
                    + "WHERE customer.`status` = 'Active'\n"
                    + "GROUP BY registerd_date \n"
                    + "ORDER BY registerd_date"
            );

            while (rs.next()) {
                String date = rs.getString("registerd_date");
                int count = rs.getInt("customer_count");
                dateCustomerCounts.put(date, count);
            }
        } catch (Exception e) {
            Logger logger = Splash.getLoggerObject();
            logger.log(Level.WARNING, "Customer Chart Panel Error", e);
        }

        CategoryChart chart = new CategoryChartBuilder()
                .title("Customer Registration Over Time")
                .xAxisTitle("Registration Date")
                .yAxisTitle("Number of Customers")
                .width(800)
                .height(600)
                .build();

        chart.addSeries("Customers", new ArrayList<>(dateCustomerCounts.keySet()), new ArrayList<>(dateCustomerCounts.values()));
        XChartPanel<?> chartPanel = new XChartPanel<>(chart);

        JFrame frame = new JFrame("Customer Count");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(chartPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void GRNCountPanel() {
        Map<String, Integer> supplierGRNCounts = new LinkedHashMap<>();

        try {
            ResultSet rs = MySQL.executeSearch(
                    "SELECT supplier.first_name, COUNT(DISTINCT grn.id) AS grn_count \n"
                    + "FROM supplier \n"
                    + "INNER JOIN grn \n"
                    + "ON grn.supplier_mobile = supplier.mobile\n"
                    + "INNER JOIN grn_item ON grn_item.grn_id = grn.id\n"
                    + "INNER JOIN stock ON stock.id = grn_item.stock_id\n"
                    + "GROUP BY supplier.first_name"
            );

            while (rs.next()) {
                String name = rs.getString("supplier.first_name");
                int count = rs.getInt("grn_count");
                supplierGRNCounts.put(name, count);
            }
        } catch (Exception e) {
            Logger logger = Splash.getLoggerObject();
            logger.log(Level.WARNING, "GRN Chart Error", e);
        }

        PieChart chart = new PieChartBuilder()
                .title("Supplier GRN Count")
                .width(800)
                .height(600)
                .build();

        for (Map.Entry<String, Integer> entry : supplierGRNCounts.entrySet()) {
            chart.addSeries(entry.getKey(), entry.getValue());
        }

        XChartPanel<?> chartPanel = new XChartPanel<>(chart);

        JFrame frame = new JFrame("GRN Report");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(chartPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    public void productStockCount() {

        Map<String, Integer> productStocks = new LinkedHashMap<>();

        try {
            ResultSet rs = MySQL.executeSearch(
                    "SELECT product.name, SUM(stock.qty) AS product_count "
                    + "FROM product "
                    + "INNER JOIN stock ON product.id = stock.product_id "
                    + "GROUP BY product.name LIMIT 10"
            );

            while (rs.next()) {
                String name = rs.getString("product.name");
                int count = rs.getInt("product_count");
                productStocks.put(name, count);
            }
        } catch (Exception e) {
            Logger logger = Splash.getLoggerObject();
            logger.log(Level.WARNING, "Product Chart Error", e);
        }

        CategoryChart chart = new CategoryChartBuilder()
                .title("Product Stock Count")
                .xAxisTitle("Product")
                .yAxisTitle("Stock Quantity")
                .width(800)
                .height(600)
                .build();

        chart.addSeries("Products", new ArrayList<>(productStocks.keySet()), new ArrayList<>(productStocks.values()));
        XChartPanel<?> chartPanel = new XChartPanel<>(chart);

        JFrame frame = new JFrame("Product Stock Report");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(chartPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    public void Top10Selling() {

        Map<String, Integer> productSales = new LinkedHashMap<>();

        try {
            ResultSet rs = MySQL.executeSearch(
                    "SELECT product.name, SUM(invoice_item.qty) AS total_sold\n"
                    + "FROM invoice INNER JOIN invoice_item ON invoice.id = invoice_item.invoice_id \n"
                    + "INNER JOIN stock ON invoice_item.stock_id = stock.id \n"
                    + "INNER JOIN product ON stock.product_id = product.id\n"
                    + "GROUP BY product.name ORDER BY total_sold DESC LIMIT 10"
            );

            while (rs.next()) {
                String name = rs.getString("product.name");
                int count = rs.getInt("total_sold");
                productSales.put(name, count);
            }
        } catch (Exception e) {
            Logger logger = Splash.getLoggerObject();
            logger.log(Level.WARNING, "Top 10 Selling Products error", e);
        }

        CategoryChart chart = new CategoryChartBuilder()
                .title("Top 10 Selling Products")
                .xAxisTitle("Product")
                .yAxisTitle("Quantity Sold")
                .width(800)
                .height(600)
                .build();

        chart.addSeries("Products", new ArrayList<>(productSales.keySet()), new ArrayList<>(productSales.values()));
        XChartPanel<?> chartPanel = new XChartPanel<>(chart);

        JFrame frame = new JFrame("Product Reports");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(chartPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    public void SupplierPayment() {

        Map<String, Double> supplierPayments = new LinkedHashMap<>();

        try {
            ResultSet rs = MySQL.executeSearch("SELECT * FROM `grn` INNER JOIN `supplier` ON `grn`.`supplier_mobile` = `supplier`.`mobile`");

            while (rs.next()) {
                double amount = rs.getDouble("paid_amount");
                String supplier = rs.getString("supplier.first_name");
                supplierPayments.put(supplier, supplierPayments.getOrDefault(supplier, 0.0) + amount);
            }
        } catch (Exception e) {
            Logger logger = Splash.getLoggerObject();
            logger.log(Level.WARNING, "Supplier Payments error", e);
        }

        PieChart chart = new PieChartBuilder()
                .title("Supplier Payments")
                .width(800)
                .height(600)
                .build();

        for (Map.Entry<String, Double> entry : supplierPayments.entrySet()) {
            chart.addSeries(entry.getKey(), entry.getValue());
        }

        XChartPanel<?> chartPanel = new XChartPanel<>(chart);

        JFrame frame = new JFrame("Supplier Report");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(chartPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    public void customerPoints() {

        Map<String, Double> customerPoints = new LinkedHashMap<>();

        try {
            ResultSet rs = MySQL.executeSearch("SELECT * FROM `customer`");

            while (rs.next()) {
                double points = rs.getDouble("points");
                String name = rs.getString("first_name");
                customerPoints.put(name, points);
            }
        } catch (Exception e) {
            Logger logger = Splash.getLoggerObject();
            logger.log(Level.WARNING, "Customer points chart error", e);
        }

        CategoryChart chart = new CategoryChartBuilder()
                .title("Customer Points")
                .xAxisTitle("Customer")
                .yAxisTitle("Points")
                .width(800)
                .height(600)
                .build();

        chart.addSeries("Points", new ArrayList<>(customerPoints.keySet()), new ArrayList<>(customerPoints.values()));
        XChartPanel<?> chartPanel = new XChartPanel<>(chart);

        JFrame frame = new JFrame("Customer Report");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(chartPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

//    public void latestPayments() {
//
//        Map<String, Double> latestPayments = new LinkedHashMap<>();
//
//        try {
//            ResultSet rs = MySQL.executeSearch("SELECT * FROM `invoice` ORDER BY `date` DESC LIMIT 5");
//
//            while (rs.next()) {
//                double amount = rs.getDouble("paid_amount");
//                String date = rs.getString("date");
//                latestPayments.put(date, amount);
//            }
//        } catch (Exception e) {
//            Logger logger = Splash.getLoggerObject();
//            logger.log(Level.WARNING, "Latest Payments error", e);
//        }
//
//        CategoryChart chart = new CategoryChartBuilder()
//                .title("Latest Payments")
//                .xAxisTitle("Invoice Date")
//                .yAxisTitle("Paid Amount")
//                .width(800)
//                .height(600)
//                .build();
//
//        chart.addSeries("Paid Amount", new ArrayList<>(latestPayments.keySet()), new ArrayList<>(latestPayments.values()));
//        XChartPanel<?> chartPanel = new XChartPanel<>(chart);
//
//        JFrame frame = new JFrame("Payment Report");
//        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        frame.add(chartPanel);
//        frame.pack();
//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);
//
//    }
    public void latestPayments() {
        Map<String, Double> latestPayments = new LinkedHashMap<>();

        try {
            // Group by date, sum paid_amount per date, and get last 5 days
            ResultSet rs = MySQL.executeSearch(
                    "SELECT `date`, SUM(`paid_amount`) AS total_paid "
                    + "FROM `invoice` "
                    + "GROUP BY `date` "
                    + "ORDER BY `date` DESC "
                    + "LIMIT 5"
            );

            // Add to map in reverse order to show oldest to newest (left to right on chart)
            LinkedList<String> dateList = new LinkedList<>();
            LinkedList<Double> amountList = new LinkedList<>();

            while (rs.next()) {
                String date = rs.getString("date");
                double amount = rs.getDouble("total_paid");

                // Insert at the beginning to reverse order
                dateList.addFirst(date);
                amountList.addFirst(amount);
            }

            for (int i = 0; i < dateList.size(); i++) {
                latestPayments.put(dateList.get(i), amountList.get(i));
            }

        } catch (Exception e) {
            Logger logger = Splash.getLoggerObject();
            logger.log(Level.WARNING, "Latest Payments error", e);
        }

        // Create the chart
        CategoryChart chart = new CategoryChartBuilder()
                .title("Latest Payments")
                .xAxisTitle("Invoice Date")
                .yAxisTitle("Total Paid Amount")
                .width(800)
                .height(600)
                .build();

        chart.addSeries("Paid Amount", new ArrayList<>(latestPayments.keySet()), new ArrayList<>(latestPayments.values()));

        XChartPanel<?> chartPanel = new XChartPanel<>(chart);

        JFrame frame = new JFrame("Payment Report");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(chartPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Admin DashBoard");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 165, 0));

        jLabel1.setText("jLabel1");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Spices & Herbs");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Welcome");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("First Name");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("User@gmail.com");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Today date");
        jLabel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(30, 30, 30)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 3, 3, 3));
        jPanel3.setLayout(new java.awt.GridLayout(12, 1, 0, 5));

        jButton1.setBackground(new java.awt.Color(39, 174, 96));
        jButton1.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Employee");
        jButton1.setPreferredSize(new java.awt.Dimension(100, 10));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1);

        jButton2.setBackground(new java.awt.Color(39, 174, 96));
        jButton2.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Supplier");
        jButton2.setPreferredSize(new java.awt.Dimension(100, 10));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton2);

        jButton3.setBackground(new java.awt.Color(39, 174, 96));
        jButton3.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Customer");
        jButton3.setPreferredSize(new java.awt.Dimension(100, 10));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton3);

        jButton4.setBackground(new java.awt.Color(39, 174, 96));
        jButton4.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("GRN");
        jButton4.setPreferredSize(new java.awt.Dimension(100, 10));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton4);

        jButton5.setBackground(new java.awt.Color(39, 174, 96));
        jButton5.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Stock");
        jButton5.setPreferredSize(new java.awt.Dimension(100, 10));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton5);

        jButton6.setBackground(new java.awt.Color(39, 174, 96));
        jButton6.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Product");
        jButton6.setPreferredSize(new java.awt.Dimension(100, 10));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton6);

        jButton7.setBackground(new java.awt.Color(39, 174, 96));
        jButton7.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("Invoice");
        jButton7.setPreferredSize(new java.awt.Dimension(100, 10));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton7);

        jButton8.setBackground(new java.awt.Color(39, 174, 96));
        jButton8.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setText("Send Emails");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton8);

        jButton10.setBackground(new java.awt.Color(39, 174, 96));
        jButton10.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        jButton10.setForeground(new java.awt.Color(255, 255, 255));
        jButton10.setText("Edit Profile");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton10);

        jButton12.setBackground(new java.awt.Color(39, 174, 96));
        jButton12.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        jButton12.setForeground(new java.awt.Color(255, 255, 255));
        jButton12.setText("Return Item");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton12);

        jButton11.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        jButton11.setText("Change Theme");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton11);

        jButton9.setBackground(new java.awt.Color(0, 51, 51));
        jButton9.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText("Sign Out");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton9);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jPanel4.setLayout(new java.awt.GridLayout(4, 3, 5, 5));

        jPanel5.setBackground(new java.awt.Color(248, 204, 27));
        jPanel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel5MouseClicked(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setText("Total Of Employee");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("0");

        jLabel9.setText("img");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap(102, Short.MAX_VALUE)
                        .addComponent(jLabel7)))
                .addContainerGap(96, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel4.add(jPanel5);

        jPanel6.setBackground(new java.awt.Color(248, 204, 27));
        jPanel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel6MouseClicked(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel16.setText("Total Of GRN");

        jLabel17.setText("img");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("0");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(41, Short.MAX_VALUE)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 118, Short.MAX_VALUE)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel16)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel4.add(jPanel6);

        jPanel7.setBackground(new java.awt.Color(248, 204, 27));
        jPanel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel7MouseClicked(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel25.setText("Latest GRN Report");

        jLabel26.setText("img");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel25)
                .addContainerGap(150, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        jPanel4.add(jPanel7);

        jPanel8.setBackground(new java.awt.Color(248, 204, 27));
        jPanel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel8MouseClicked(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setText("Total Of Supplier");

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("img");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("0");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap(112, Short.MAX_VALUE)
                        .addComponent(jLabel10))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(98, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel4.add(jPanel8);

        jPanel9.setBackground(new java.awt.Color(248, 204, 27));
        jPanel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel9MouseClicked(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel19.setText("Total Of Stock");

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("0");

        jLabel20.setText("img");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(117, Short.MAX_VALUE)
                .addComponent(jLabel19)
                .addContainerGap(117, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(jLabel19)
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel4.add(jPanel9);

        jPanel10.setBackground(new java.awt.Color(248, 204, 27));
        jPanel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel10MouseClicked(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel28.setText("Latest Customer Report");

        jLabel29.setText("img");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel28)
                .addContainerGap(124, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(jLabel28)
                .addGap(18, 18, 18)
                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        jPanel4.add(jPanel10);

        jPanel11.setBackground(new java.awt.Color(248, 204, 27));
        jPanel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel11MouseClicked(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel13.setText("Total Of Customer");

        jLabel14.setText("img");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("0");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addContainerGap(103, Short.MAX_VALUE)
                        .addComponent(jLabel13)))
                .addContainerGap(96, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel4.add(jPanel11);

        jPanel12.setBackground(new java.awt.Color(248, 204, 27));
        jPanel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel12MouseClicked(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel22.setText("Total Of Product");

        jLabel23.setText("img");

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("0");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(91, Short.MAX_VALUE)
                .addComponent(jLabel22)
                .addContainerGap(123, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jLabel22)
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34))))
        );

        jPanel4.add(jPanel12);

        jPanel13.setBackground(new java.awt.Color(248, 204, 27));
        jPanel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel13MouseClicked(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel31.setText("Latest Invoice Report");

        jLabel32.setText("img");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel31)
                .addContainerGap(134, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jLabel31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        jPanel4.add(jPanel13);

        jPanel14.setBackground(new java.awt.Color(248, 204, 27));

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel27.setText("Total Income");

        jLabel30.setText("img");

        jLabel33.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText("0");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap(123, Short.MAX_VALUE)
                .addComponent(jLabel27)
                .addContainerGap(121, Short.MAX_VALUE))
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(50, 50, 50))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        jPanel4.add(jPanel14);

        jPanel15.setBackground(new java.awt.Color(248, 204, 27));

        jLabel34.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel34.setText("Daily Income");

        jLabel35.setText("img");

        jLabel36.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel36.setText("0");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel34)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(jLabel34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jPanel4.add(jPanel15);

        jPanel16.setBackground(new java.awt.Color(248, 204, 27));

        jLabel37.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel37.setText("Total Expenses");

        jLabel38.setText("img");

        jLabel39.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel39.setText("0");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel37)
                    .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(67, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel37)
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jPanel4.add(jPanel16);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseClicked
        GenderChartPanel();
    }//GEN-LAST:event_jPanel5MouseClicked

    private void jPanel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel8MouseClicked
        SupplierChartPanel();
    }//GEN-LAST:event_jPanel8MouseClicked

    private void jPanel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel11MouseClicked
        CustomerChartPanel();
    }//GEN-LAST:event_jPanel11MouseClicked

    private void jPanel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MouseClicked
        GRNCountPanel();
    }//GEN-LAST:event_jPanel6MouseClicked

    private void jPanel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel9MouseClicked
        // Create a dataset for the bar chart
        productStockCount();
    }//GEN-LAST:event_jPanel9MouseClicked

    private void jPanel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel12MouseClicked
        Top10Selling();
    }//GEN-LAST:event_jPanel12MouseClicked

    private void jPanel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel7MouseClicked
        SupplierPayment();
    }//GEN-LAST:event_jPanel7MouseClicked

    private void jPanel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel10MouseClicked
        customerPoints();
    }//GEN-LAST:event_jPanel10MouseClicked

    private void jPanel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel13MouseClicked
        latestPayments();
    }//GEN-LAST:event_jPanel13MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        EmployeeRegistration em = new EmployeeRegistration();
        em.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        SupplierRegistration sr = new SupplierRegistration();
        sr.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        CustomerRegistration cr = new CustomerRegistration();
        cr.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        ConfirmMsg confirm = new ConfirmMsg();
        confirm.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        Product p = new Product();
        p.setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        LoadStock ls = new LoadStock(this, true);
        ls.setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        ConfirmMsg2 msg2 = new ConfirmMsg2();
        msg2.setVisible(true);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        SendEmails sendmail = new SendEmails(email, firstNameofAdmin);
        sendmail.setVisible(true);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        this.dispose();
        SignIn signin = new SignIn();
        signin.setVisible(true);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        AdminProfile ap = new AdminProfile(email);
        ap.setVisible(true);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        if (jButton11.getText().equals("Light")) {
            java.awt.EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    FlatAnimatedLafChange.showSnapshot();
                    FlatArcOrangeIJTheme.setup();

                    jButton11.setText("Dark");
                    jButton11.setBackground(Color.WHITE);
                    jButton11.setForeground(Color.BLACK);

                    UIManager.put("Panel.background", Color.WHITE);
                    UIManager.put("Label.foreground", Color.BLACK);
                    UIManager.put("Button.background", Color.WHITE);
                    UIManager.put("Button.foreground", Color.BLACK);
                    UIManager.put("TextField.background", Color.WHITE);
                    UIManager.put("TextField.foreground", Color.BLACK);

                    FlatLaf.updateUI();
                    FlatAnimatedLafChange.hideSnapshotWithAnimation();
                }
            });

        } else {
            java.awt.EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    FlatAnimatedLafChange.showSnapshot();
                    FlatArcDarkOrangeIJTheme.setup();
                    jButton11.setText("Light");
                    // Customize colors for the dark theme

                    UIManager.put("Panel.background", new Color(43, 43, 43));
                    UIManager.put("Label.foreground", Color.BLACK);
                    UIManager.put("Button.background", new Color(60, 63, 65));
                    UIManager.put("Button.foreground", Color.WHITE);
                    UIManager.put("TextField.background", new Color(69, 73, 74));
                    UIManager.put("TextField.foreground", Color.WHITE);

                    FlatLaf.updateUI();
                    FlatAnimatedLafChange.hideSnapshotWithAnimation();
                }
            });

        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        Return returnInterface = new Return(jLabel5.getText());
        returnInterface.setVisible(true);

    }//GEN-LAST:event_jButton12ActionPerformed

    public static String getJlabelEmail() {
        return jLabel5.getText();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    public static javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    // End of variables declaration//GEN-END:variables
}
