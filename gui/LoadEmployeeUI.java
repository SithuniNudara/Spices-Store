package gui;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.MySQL;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class LoadEmployeeUI extends javax.swing.JFrame {

    private EmployeeRegistration er;

    public void setER(EmployeeRegistration er) {
        this.er = er;
    }
    private String MobileNum;

    public void setMobileNumber(String mobile) {
        this.MobileNum = mobile;
    }

    public LoadEmployeeUI() {
        initComponents();
        loadEmployee();
        jTextField2.grabFocus();
        loadGender();
        ImageIcon icon = new ImageIcon(SignIn.class.getResource("/resources/install.png"));
        this.setIconImage(icon.getImage());
        round();

    }

    private void round() {
        jPanel2.putClientProperty(FlatClientProperties.STYLE, "arc:20");
        jPanel3.putClientProperty(FlatClientProperties.STYLE, "arc:20");
        jPanel1.putClientProperty(FlatClientProperties.STYLE, "arc:20");
        jButton1.putClientProperty(FlatClientProperties.STYLE, "arc:10");
        jButton2.putClientProperty(FlatClientProperties.STYLE, "arc:10");
        jButton3.putClientProperty(FlatClientProperties.STYLE, "arc:10");
        jButton4.putClientProperty(FlatClientProperties.STYLE, "arc:10");
        jButton6.putClientProperty(FlatClientProperties.STYLE, "arc:10");
    }

    private void loadEmployee() {
        try {
            // HashMap to count unique employees
            HashMap<String, String> emCount = new HashMap<>();

            // Updated query to use LEFT JOIN for employee_address and city
            String query = " SELECT * FROM `emlpoyee`\n"
                    + "INNER JOIN `employee_type` ON `emlpoyee`.`employee_type_id` = `employee_type`.`id`\n"
                    + "INNER JOIN `gender` ON `emlpoyee`.`gender_id` = `gender`.`id`\n"
                    + "LEFT JOIN `employee_address` ON `emlpoyee`.`email` = `employee_address`.`emlpoyee_email` \n"
                    + "LEFT JOIN `city` ON `employee_address`.`city_id` = `city`.`id`\n"
                    + "WHERE `employee_type_id` = '2'\n"
                    + "ORDER BY `emlpoyee`.`date_registered` ASC ";

            ResultSet resultset = MySQL.executeSearch(query);

            // Clear the table before loading new data
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            int count = 0;
            while (resultset.next()) {
                Vector<String> vector = new Vector<>();

                String status = String.valueOf(resultset.getString("Status"));

                if (status.equals("Active")) {
                    count += 1;
                }

                // Add basic employee information
                vector.add(resultset.getString("email"));
                vector.add(resultset.getString("first_name"));
                vector.add(resultset.getString("last_name"));
                vector.add(resultset.getString("nic"));
                vector.add(resultset.getString("mobile"));
                vector.add(resultset.getString("password"));
                vector.add(resultset.getString("gender.name"));
                vector.add(resultset.getString("employee_type.name"));

                // Handle address fields (can be null if employee has no address)
                String line1 = resultset.getString("employee_address.line1");
                String line2 = resultset.getString("employee_address.line2");
                String city = resultset.getString("city.name");

                // If no address is found, default to "No Address"
                if (line1 == null || line2 == null) {
                    vector.add("No Address");
                } else {
                    vector.add(line1 + " " + line2);
                }

                // Add city or "No City" if no city found
                if (city == null) {
                    vector.add("No City");
                } else {
                    vector.add(city);
                }

                vector.add(resultset.getString("status"));

                // Add the row to the table model
                model.addRow(vector);

                // Add to the employee count HashMap (using email to avoid duplicates)
                emCount.put(resultset.getString("email"), resultset.getString("first_name"));
            }

            // Update the employee count label
            jLabel2.setText(String.valueOf(count));

        } catch (Exception e) {
            Logger logger = Splash.getLoggerObject();
            logger.log(Level.WARNING, "loading employee error", e);
        }
    }

    private void loadGender() {
        try {

            ResultSet resultset = MySQL.executeSearch(" SELECT * FROM `gender` ");

            Vector<String> vector = new Vector<>();

            while (resultset.next()) {

                vector.add(resultset.getString("name"));

            }
            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox1.setModel(model);

        } catch (Exception e) {
            Logger logger = Splash.getLoggerObject();
            logger.log(Level.WARNING, "loadgender error", e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jToggleButton1 = new javax.swing.JToggleButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jTextField2 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Employee Details");
        setBackground(new java.awt.Color(204, 204, 204));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(76, 175, 80));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Email", "First Name", "Last Name", "NIC", "Mobile", "Password", "Gender", "Type", "City", "Address", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("Employee Count :");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("0");

        jToggleButton1.setSelected(true);
        jToggleButton1.setText("Active");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1030, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(76, 175, 80));

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("Employee Details");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(21, 21, 21))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jLabel5.setText("Search By Name");

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jLabel4.setText("Search By NIC");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jLabel3.setText("Search By Gender");

        jButton3.setBackground(new java.awt.Color(198, 40, 40));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Clear");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(55, 71, 79));
        jButton2.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Print");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(0, 102, 102));
        jButton1.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Charts");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(0, 0, 153));
        jButton6.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Attendance");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(0, 0, 0));
        jButton4.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Add More Details");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 14, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int row = jTable1.getSelectedRow();

        if (row != -1) {

            if (evt.getClickCount() == 2) {
                String email = String.valueOf(jTable1.getValueAt(row, 0));
                String fname = String.valueOf(jTable1.getValueAt(row, 1));
                String lname = String.valueOf(jTable1.getValueAt(row, 2));
                String nic = String.valueOf(jTable1.getValueAt(row, 3));

                String mobile = String.valueOf(jTable1.getValueAt(row, 4));
                String password = String.valueOf(jTable1.getValueAt(row, 5));
                String gender = String.valueOf(jTable1.getValueAt(row, 6));
                String type = String.valueOf(jTable1.getValueAt(row, 7));

                er.setMobileNumber(mobile);
                er.setEmail(email);
                er.setFirstName(fname);
                er.setLastName(lname);
                er.setNIC(nic);
                er.setPassword(password);
                er.setGender(gender);
                er.setType(type);

                this.dispose();

                er.BlockEmail(email);

                er.setMobileForQuery(String.valueOf(jTable1.getValueAt(row, 4)));
            }

        } else {
            JOptionPane.showMessageDialog(this, "Not Selected Row");
        }

        if (row != -1) {
            String status = String.valueOf(jTable1.getValueAt(row, 10)); 
            jToggleButton1.setSelected(status.equals("Active"));
            jToggleButton1.setText(status);
        }

    }//GEN-LAST:event_jTable1MouseClicked

    //print
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        try (InputStream path = this.getClass().getResourceAsStream("/reports/SpicesStore_EmployeeReport.jasper")) {

            if (path == null) {
                throw new FileNotFoundException("Report file not found in the specified path.");
            }

            if (jTable1 == null || jTable1.getModel() == null) {
                throw new IllegalStateException("Table or table model is not initialized.");
            }

            JRTableModelDataSource dataSource = new JRTableModelDataSource(jTable1.getModel());

            // Fill the report
            JasperPrint jasperPrint = JasperFillManager.fillReport(path, null, dataSource);

            // View the report
            JasperViewer.viewReport(jasperPrint, false);
        } catch (FileNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (JRException e) {
            System.err.println("JasperReports error: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    //chart
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        EmployeeChart ec = new EmployeeChart();
        ec.setVisible(true);

    }//GEN-LAST:event_jButton1ActionPerformed

    //search by name
    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
        String fname = jTextField2.getText();
        try {
            // HashMap to count unique employees
            HashMap<String, String> emCount = new HashMap<>();

            // Updated query to use LEFT JOIN for employee_address and city
            String query = " SELECT * FROM `emlpoyee` "
                    + " INNER JOIN `employee_type` ON `emlpoyee`.`employee_type_id` = `employee_type`.`id` "
                    + " INNER JOIN `gender` ON `emlpoyee`.`gender_id` = `gender`.`id` "
                    + " LEFT JOIN `employee_address` ON `emlpoyee`.`email` = `employee_address`.`emlpoyee_email` "
                    + " LEFT JOIN `city` ON `employee_address`.`city_id` = `city`.`id` "
                    + " WHERE `first_name` LIKE '%" + fname + "%' AND `emlpoyee`.`employee_type_id` = '2' ORDER BY `emlpoyee`.`date_registered` ASC ";

            ResultSet resultset = MySQL.executeSearch(query);

            // Clear the table before loading new data
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            // Loop through the result set and populate the table
            while (resultset.next()) {
                Vector<String> vector = new Vector<>();

                // Add basic employee information
                vector.add(resultset.getString("email"));
                vector.add(resultset.getString("first_name"));
                vector.add(resultset.getString("last_name"));
                vector.add(resultset.getString("nic"));
                vector.add(resultset.getString("mobile"));
                vector.add(resultset.getString("password"));
                vector.add(resultset.getString("gender.name"));
                vector.add(resultset.getString("employee_type.name"));

                // Handle address fields (can be null if employee has no address)
                String line1 = resultset.getString("employee_address.line1");
                String line2 = resultset.getString("employee_address.line2");
                String city = resultset.getString("city.name");

                // If no address is found, default to "No Address"
                if (line1 == null || line2 == null) {
                    vector.add("No Address");
                } else {
                    vector.add(line1 + " " + line2);
                }

                // Add city or "No City" if no city found
                if (city == null) {
                    vector.add("No City");
                } else {
                    vector.add(city);
                }

                vector.add(resultset.getString("status"));

                // Add the row to the table model
                model.addRow(vector);

                // Add to the employee count HashMap (using email to avoid duplicates)
                emCount.put(resultset.getString("email"), resultset.getString("first_name"));
            }

            // Update the employee count label
            jLabel2.setText(String.valueOf(emCount.size()));

        } catch (Exception e) {
            Logger logger = Splash.getLoggerObject();
            logger.log(Level.WARNING, "Serach by name error", e);
        }
    }//GEN-LAST:event_jTextField2KeyReleased

    //
    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed

    }//GEN-LAST:event_jTextField1ActionPerformed
//nic
    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        String nic = jTextField1.getText();
        try {
            // HashMap to count unique employees
            HashMap<String, String> emCount = new HashMap<>();

            // Updated query to use LEFT JOIN for employee_address and city
            String query = " SELECT * FROM `emlpoyee` "
                    + " INNER JOIN `employee_type` ON `emlpoyee`.`employee_type_id` = `employee_type`.`id` "
                    + " INNER JOIN `gender` ON `emlpoyee`.`gender_id` = `gender`.`id` "
                    + " LEFT JOIN `employee_address` ON `emlpoyee`.`email` = `employee_address`.`emlpoyee_email` "
                    + " LEFT JOIN `city` ON `employee_address`.`city_id` = `city`.`id` "
                    + " WHERE `nic` LIKE '%" + nic + "%'  AND `emlpoyee`.`employee_type_id` = '2' ORDER BY `emlpoyee`.`date_registered` ASC ";

            ResultSet resultset = MySQL.executeSearch(query);

            // Clear the table before loading new data
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            // Loop through the result set and populate the table
            while (resultset.next()) {
                Vector<String> vector = new Vector<>();

                // Add basic employee information
                vector.add(resultset.getString("email"));
                vector.add(resultset.getString("first_name"));
                vector.add(resultset.getString("last_name"));
                vector.add(resultset.getString("nic"));
                vector.add(resultset.getString("mobile"));
                vector.add(resultset.getString("password"));
                vector.add(resultset.getString("gender.name"));
                vector.add(resultset.getString("employee_type.name"));

                // Handle address fields (can be null if employee has no address)
                String line1 = resultset.getString("employee_address.line1");
                String line2 = resultset.getString("employee_address.line2");
                String city = resultset.getString("city.name");

                // If no address is found, default to "No Address"
                if (line1 == null || line2 == null) {
                    vector.add("No Address");
                } else {
                    vector.add(line1 + " " + line2);
                }

                // Add city or "No City" if no city found
                if (city == null) {
                    vector.add("No City");
                } else {
                    vector.add(city);
                }

                vector.add(resultset.getString("status"));

                // Add the row to the table model
                model.addRow(vector);

                // Add to the employee count HashMap (using email to avoid duplicates)
                emCount.put(resultset.getString("email"), resultset.getString("first_name"));
            }

            // Update the employee count label
            jLabel2.setText(String.valueOf(emCount.size()));

        } catch (Exception e) {
            Logger logger = Splash.getLoggerObject();
            logger.log(Level.WARNING, "Updating error", e);
        }
    }//GEN-LAST:event_jTextField1KeyReleased

    //gender
    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        try {
            String gen = "";
            String gender = String.valueOf(jComboBox1.getSelectedItem());
            if (gender.equals("Male")) {
                gen = "Male";
            } else {
                gen = "Female";
            }

            HashMap<String, String> emCount = new HashMap<>();

            ResultSet resultset = MySQL.executeSearch("SELECT * FROM `emlpoyee` INNER JOIN `employee_type` \n"
                    + "ON `emlpoyee`.`employee_type_id` = `employee_type`.`id` \n"
                    + "INNER JOIN `gender` ON `emlpoyee`.`gender_id` = `gender`.`id` \n"
                    + "LEFT JOIN `employee_address` ON `emlpoyee`.`email` = `employee_address`.`emlpoyee_email` \n"
                    + "LEFT JOIN `city` ON `employee_address`.`city_id` = `city`.`id`\n"
                    + "WHERE `gender`.`name` = '" + gen + "' AND `emlpoyee`.`employee_type_id` = '2'  ORDER BY `emlpoyee`.`date_registered` ASC");

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            while (resultset.next()) {

                Vector<String> vector = new Vector<>();
                vector.add(resultset.getString("email"));
                vector.add(resultset.getString("first_name"));
                vector.add(resultset.getString("last_name"));
                vector.add(resultset.getString("nic"));
                vector.add(resultset.getString("mobile"));
                vector.add(resultset.getString("password"));
                vector.add(resultset.getString("gender.name"));
                vector.add(resultset.getString("employee_type.name"));
                emCount.put(resultset.getString("email"), resultset.getString("first_name"));

                // Handle address fields (can be null if employee has no address)
                String line1 = resultset.getString("employee_address.line1");
                String line2 = resultset.getString("employee_address.line2");
                String city = resultset.getString("city.name");

                // If no address is found, default to "No Address"
                if (line1 == null || line2 == null) {
                    vector.add("No Address");
                } else {
                    vector.add(line1 + " " + line2);
                }

                // Add city or "No City" if no city found
                if (city == null) {
                    vector.add("No City");
                } else {
                    vector.add(city);
                }

                vector.add(resultset.getString("status"));

                model.addRow(vector);

                jLabel2.setText(String.valueOf(emCount.size()));

            }

        } catch (Exception e) {
            Logger logger = Splash.getLoggerObject();
            logger.log(Level.WARNING, "Gender Error", e);
        }
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    //refresh
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        loadEmployee();
        loadGender();
    }//GEN-LAST:event_jButton3ActionPerformed

    //add more details
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        AddMoreEmployeeDetails1 ed = new AddMoreEmployeeDetails1();
        ed.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        LoadAttendance la = new LoadAttendance();
        la.setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    //toggle button
    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed

        int row = jTable1.getSelectedRow();

        if (row != -1) {
            String email = String.valueOf(jTable1.getValueAt(row, 0));
            String currentStatus = String.valueOf(jTable1.getValueAt(row, 10)); // Assuming status column is at index 10

            try {
                String newStatus = currentStatus.equals("Active") ? "Deactive" : "Active";

                MySQL.executeIUD("UPDATE `emlpoyee` SET `status` = '" + newStatus + "' WHERE `email` = '" + email + "'");

                JOptionPane.showMessageDialog(this, "Employee status updated to: " + newStatus);

                loadEmployee(); // reload table data

                // Optionally set toggle text accordingly
                jToggleButton1.setText(newStatus);

            } catch (Exception e) {
                Logger logger = Splash.getLoggerObject();
                logger.log(Level.WARNING, "Toggle status error", e);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select an employee from the table.");
        }


    }//GEN-LAST:event_jToggleButton1ActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        FlatMacLightLaf.setup();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoadEmployeeUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JToggleButton jToggleButton1;
    // End of variables declaration//GEN-END:variables
}
