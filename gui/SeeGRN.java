package gui;

import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import model.MySQL;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.view.JasperViewer;

public class SeeGRN extends javax.swing.JDialog {

    public SeeGRN(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        loadGrnDetails();
        ImageIcon icon = new ImageIcon(SignIn.class.getResource("/resources/install.png"));
        this.setIconImage(icon.getImage());
    }

    public void loadGrnDetails() {

        try {
            ResultSet rs = MySQL.executeSearch("SELECT * FROM `grn` \n"
                    + "INNER JOIN `grn_item`\n"
                    + "ON `grn`.`id` = `grn_item`.`grn_id`\n"
                    + "INNER JOIN `stock`\n"
                    + "ON `grn_item`.`stock_id` = `stock`.`id`\n"
                    + "INNER JOIN `product`\n"
                    + "ON `stock`.`product_id` = `product`.`id`\n"
                    + "INNER JOIN `brand` \n"
                    + "ON `brand`.`id` = `product`.`brand_id`\n"
                    + "INNER JOIN `emlpoyee`\n"
                    + "ON `grn`.`emlpoyee_email` = `emlpoyee`.`email`\n"
                    + "INNER JOIN `supplier`\n"
                    + "ON `grn`.`supplier_mobile` = `supplier`.`mobile` ORDER BY `exp` ASC");

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);
            int count = 0;

            while (rs.next()) {
                count += 1;
                Vector<String> v = new Vector<>();
                v.add(rs.getString("grn.id"));
                v.add(rs.getString("supplier.first_name"));
                v.add(rs.getString("grn.date"));
                v.add(rs.getString("product.name"));
                v.add(rs.getString("brand.name"));
                v.add(rs.getString("grn_item.qty"));
                v.add(rs.getString("grn_item.price"));
                v.add(rs.getString("stock.exp"));
                v.add(rs.getString("stock.mfd"));
                v.add(rs.getString("grn.paid_amount"));
                v.add(rs.getString("emlpoyee.first_name"));
                model.addRow(v);

            }

            countOfGRN.setText(String.valueOf(count));

        } catch (Exception e) {
            Logger logger = Splash.getLoggerObject();
            logger.log(Level.WARNING, "Load GRN Details Error", e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        countOfGRN = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("GRN Details");

        jPanel1.setBackground(new java.awt.Color(255, 165, 0));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "GRN ID", "Supplier", "Added Date", "Product", "Brand", "Quntity", "Unit Price", "EXP", "MFD", "Paid Amount", "Employee"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Quntity");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Expire Date");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Good Receive Note");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("GRN ID");

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Product Name");

        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField3KeyReleased(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Supplier Name");

        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField4KeyReleased(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Search Options");

        jButton1.setBackground(new java.awt.Color(0, 0, 0));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Print");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        countOfGRN.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        countOfGRN.setForeground(new java.awt.Color(255, 255, 255));
        countOfGRN.setText("0");

        jButton2.setBackground(new java.awt.Color(204, 102, 0));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Reports");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "EXP ASC", "EXP DESC" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(354, 354, 354)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(countOfGRN, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(230, 230, 230)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE))
                                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(11, 11, 11))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(140, 140, 140)
                                        .addComponent(jLabel1)
                                        .addGap(136, 136, 136))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(12, 12, 12)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addGap(12, 12, 12)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6))
                                .addGap(73, 73, 73))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(countOfGRN))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7)
                                .addGap(43, 43, 43)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        try {

            String selectedItem = String.valueOf(jComboBox1.getSelectedItem());
            String Orderby = "EXP ASC";

            // Use .equals() to compare strings in Java instead of "=="
            if (selectedItem.equals("EXP ASC")) {
                Orderby = "EXP ASC";
            } else {
                Orderby = "EXP DESC";
            }

            ResultSet rs = MySQL.executeSearch("SELECT * FROM `grn` \n"
                    + "INNER JOIN `grn_item`\n"
                    + "ON `grn`.`id` = `grn_item`.`grn_id`\n"
                    + "INNER JOIN `stock`\n"
                    + "ON `grn_item`.`stock_id` = `stock`.`id`\n"
                    + "INNER JOIN `product`\n"
                    + "ON `stock`.`product_id` = `product`.`id`\n"
                    + "INNER JOIN `brand` \n"
                    + "ON `brand`.`id` = `product`.`brand_id`\n" // Correct join condition
                    + "INNER JOIN `emlpoyee`\n"
                    + "ON `grn`.`emlpoyee_email` = `emlpoyee`.`email`\n"
                    + "INNER JOIN `supplier`\n"
                    + "ON `grn`.`supplier_mobile` = `supplier`.`mobile` ORDER BY " + Orderby + " ");

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            int count = 0;

            while (rs.next()) {
                count += 1;

                Vector<String> v = new Vector<>();
                v.add(rs.getString("grn.id"));
                v.add(rs.getString("supplier.first_name"));
                v.add(rs.getString("grn.date"));
                v.add(rs.getString("product.name"));
                v.add(rs.getString("brand.name"));
                v.add(rs.getString("grn_item.qty"));
                v.add(rs.getString("grn_item.price"));
                v.add(rs.getString("stock.exp"));
                v.add(rs.getString("stock.mfd"));
                v.add(rs.getString("grn.paid_amount"));
                v.add(rs.getString("emlpoyee.first_name"));
                model.addRow(v);
            }

            countOfGRN.setText(String.valueOf(count));

        } catch (Exception e) {
            Logger logger = Splash.getLoggerObject();
            logger.log(Level.WARNING, "State changed Error", e);
        }

    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
        try {

            String grnID = jTextField2.getText();

            ResultSet rs = MySQL.executeSearch("SELECT * FROM `grn` \n"
                    + "INNER JOIN `grn_item`\n"
                    + "ON `grn`.`id` = `grn_item`.`grn_id`\n"
                    + "INNER JOIN `stock`\n"
                    + "ON `grn_item`.`stock_id` = `stock`.`id`\n"
                    + "INNER JOIN `product`\n"
                    + "ON `stock`.`product_id` = `product`.`id`\n"
                    + "INNER JOIN `brand` \n"
                    + "ON `brand`.`id` = `product`.`brand_id`\n"
                    + "INNER JOIN `emlpoyee`\n"
                    + "ON `grn`.`emlpoyee_email` = `emlpoyee`.`email`\n"
                    + "INNER JOIN `supplier`\n"
                    + "ON `grn`.`supplier_mobile` = `supplier`.`mobile` WHERE `grn`.`id` LIKE '%" + grnID + "%' ");

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);
            int count = 0;

            while (rs.next()) {
                count += 1;
                Vector<String> v = new Vector<>();
                v.add(rs.getString("grn.id"));
                v.add(rs.getString("supplier.first_name"));
                v.add(rs.getString("grn.date"));
                v.add(rs.getString("product.name"));
                v.add(rs.getString("brand.name"));
                v.add(rs.getString("grn_item.qty"));
                v.add(rs.getString("grn_item.price"));
                v.add(rs.getString("stock.exp"));
                v.add(rs.getString("stock.mfd"));
                v.add(rs.getString("grn.paid_amount"));
                v.add(rs.getString("emlpoyee.first_name"));
                model.addRow(v);

            }

            countOfGRN.setText(String.valueOf(count));
            // loadGrnDetails();

        } catch (Exception e) {
            Logger logger = Splash.getLoggerObject();
            logger.log(Level.WARNING, "GRN ID Error", e);
        }
    }//GEN-LAST:event_jTextField2KeyReleased

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        try {

            String grnQTY = jTextField1.getText();

            ResultSet rs = MySQL.executeSearch("SELECT * FROM `grn` \n"
                    + "INNER JOIN `grn_item`\n"
                    + "ON `grn`.`id` = `grn_item`.`grn_id`\n"
                    + "INNER JOIN `stock`\n"
                    + "ON `grn_item`.`stock_id` = `stock`.`id`\n"
                    + "INNER JOIN `product`\n"
                    + "ON `stock`.`product_id` = `product`.`id`\n"
                    + "INNER JOIN `brand` \n"
                    + "ON `brand`.`id` = `product`.`brand_id`\n"
                    + "INNER JOIN `emlpoyee`\n"
                    + "ON `grn`.`emlpoyee_email` = `emlpoyee`.`email`\n"
                    + "INNER JOIN `supplier`\n"
                    + "ON `grn`.`supplier_mobile` = `supplier`.`mobile` WHERE `grn_item`.`qty` LIKE '" + grnQTY + "%' ");

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);
            int count = 0;

            while (rs.next()) {
                count += 1;
                Vector<String> v = new Vector<>();
                v.add(rs.getString("grn.id"));
                v.add(rs.getString("supplier.first_name"));
                v.add(rs.getString("grn.date"));
                v.add(rs.getString("product.name"));
                v.add(rs.getString("brand.name"));
                v.add(rs.getString("grn_item.qty"));
                v.add(rs.getString("grn_item.price"));
                v.add(rs.getString("stock.exp"));
                v.add(rs.getString("stock.mfd"));
                v.add(rs.getString("grn.paid_amount"));
                v.add(rs.getString("emlpoyee.first_name"));
                model.addRow(v);

            }

            countOfGRN.setText(String.valueOf(count));
            // loadGrnDetails();

        } catch (Exception e) {
            Logger logger = Splash.getLoggerObject();
            logger.log(Level.WARNING, "QTY Error", e);
        }
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jTextField3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyReleased
        try {

            String product = jTextField3.getText();

            ResultSet rs = MySQL.executeSearch("SELECT * FROM `grn` \n"
                    + "INNER JOIN `grn_item`\n"
                    + "ON `grn`.`id` = `grn_item`.`grn_id`\n"
                    + "INNER JOIN `stock`\n"
                    + "ON `grn_item`.`stock_id` = `stock`.`id`\n"
                    + "INNER JOIN `product`\n"
                    + "ON `stock`.`product_id` = `product`.`id`\n"
                    + "INNER JOIN `brand` \n"
                    + "ON `brand`.`id` = `product`.`brand_id`\n"
                    + "INNER JOIN `emlpoyee`\n"
                    + "ON `grn`.`emlpoyee_email` = `emlpoyee`.`email`\n"
                    + "INNER JOIN `supplier`\n"
                    + "ON `grn`.`supplier_mobile` = `supplier`.`mobile` WHERE `product`.`name` LIKE '%" + product + "%' ");

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);
            int count = 0;

            while (rs.next()) {
                count += 1;
                Vector<String> v = new Vector<>();
                v.add(rs.getString("grn.id"));
                v.add(rs.getString("supplier.first_name"));
                v.add(rs.getString("grn.date"));
                v.add(rs.getString("product.name"));
                v.add(rs.getString("brand.name"));
                v.add(rs.getString("grn_item.qty"));
                v.add(rs.getString("grn_item.price"));
                v.add(rs.getString("stock.exp"));
                v.add(rs.getString("stock.mfd"));
                v.add(rs.getString("grn.paid_amount"));
                v.add(rs.getString("emlpoyee.first_name"));
                model.addRow(v);

            }

            countOfGRN.setText(String.valueOf(count));
            // loadGrnDetails();

        } catch (Exception e) {
            Logger logger = Splash.getLoggerObject();
            logger.log(Level.WARNING, "product Error", e);
        }
    }//GEN-LAST:event_jTextField3KeyReleased

    private void jTextField4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyReleased
        try {

            String name = jTextField4.getText();

            ResultSet rs = MySQL.executeSearch("SELECT * FROM `grn` \n"
                    + "INNER JOIN `grn_item`\n"
                    + "ON `grn`.`id` = `grn_item`.`grn_id`\n"
                    + "INNER JOIN `stock`\n"
                    + "ON `grn_item`.`stock_id` = `stock`.`id`\n"
                    + "INNER JOIN `product`\n"
                    + "ON `stock`.`product_id` = `product`.`id`\n"
                    + "INNER JOIN `brand` \n"
                    + "ON `brand`.`id` = `product`.`brand_id`\n"
                    + "INNER JOIN `emlpoyee`\n"
                    + "ON `grn`.`emlpoyee_email` = `emlpoyee`.`email`\n"
                    + "INNER JOIN `supplier`\n"
                    + "ON `grn`.`supplier_mobile` = `supplier`.`mobile` WHERE `supplier`.`first_name` LIKE '" + name + "%' ");

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);
            int count = 0;

            while (rs.next()) {
                count += 1;
                Vector<String> v = new Vector<>();
                v.add(rs.getString("grn.id"));
                v.add(rs.getString("supplier.first_name"));
                v.add(rs.getString("grn.date"));
                v.add(rs.getString("product.name"));
                v.add(rs.getString("brand.name"));
                v.add(rs.getString("grn_item.qty"));
                v.add(rs.getString("grn_item.price"));
                v.add(rs.getString("stock.exp"));
                v.add(rs.getString("stock.mfd"));
                v.add(rs.getString("grn.paid_amount"));
                v.add(rs.getString("emlpoyee.first_name"));
                model.addRow(v);

            }

            countOfGRN.setText(String.valueOf(count));
            // loadGrnDetails();

        } catch (Exception e) {
            Logger logger = Splash.getLoggerObject();
            logger.log(Level.WARNING, "name Error", e);
        }
    }//GEN-LAST:event_jTextField4KeyReleased

    //print
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {

            InputStream path = this.getClass().getResourceAsStream("/reports/SpicesStore_ViewGRNREPORT.jasper");

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/spicesandherbs", "root", "1234");

            JasperPrint report = JasperFillManager.fillReport(path, null, connection);

            this.dispose();

            JasperViewer.viewReport(report, false);

            int response = JOptionPane.showConfirmDialog(null, "Do you want to Save Report As PDF?", "Confirm", JOptionPane.YES_NO_OPTION);

            if (response == JOptionPane.YES_OPTION) {
                JasperPrintManager.printReport(report, false);

            } else if (response == JOptionPane.NO_OPTION) {

            }

            connection.close();

        } catch (Exception e) {
            Logger logger = Splash.getLoggerObject();
            logger.log(Level.WARNING, "GRN report Error", e);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        GRNReports grnrep = new GRNReports();
        this.dispose();
        grnrep.setVisible(true);

    }//GEN-LAST:event_jButton2ActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        FlatMacLightLaf.setup();

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                SeeGRN dialog = new SeeGRN(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel countOfGRN;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables

}
