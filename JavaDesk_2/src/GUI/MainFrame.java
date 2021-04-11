/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import DTO.Armors;
import interfaces.ArmorInterface;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DELL
 */
public class MainFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainFrame
     */
    ArmorInterface ai;
     //ArrayList<Armors> list;
    ArrayList<Armors> armors;
     //String filename="ArmorsData.txt";
    public MainFrame() {
        ConnectServer();
        initComponents();
        loadData();
        armors = new ArrayList<>();
        this.setTitle("Armor Client");
        txtArmorId.setEnabled(true);
        txtArmorId.setText("");
        txtClassification.setEnabled(false);
        txtClassification.setText("");
        txtDefense.setEnabled(false);
        txtDefense.setText("");
        txtDescription.setEnabled(false);
        txtDescription.setText("");
        txtStatus.setEnabled(false);
        txtStatus.setText("");
        txtTime.setEditable(false);
        txtTime.setEnabled(false);
        btCreate.setEnabled(false);
    }
    void loadData(){
        try {
            if(ai.getAllArmor()==null){
                JOptionPane.showMessageDialog(null, "List null");
            }
            else{
                JOptionPane.showMessageDialog(null, "Ok");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    void ConnectServer(){
        try {
            ai=(ArmorInterface) Naming.lookup("rmi://localhost:9000/ArmorService");
            if(ai!=null){
                JOptionPane.showMessageDialog(null, "Connect Server Successfully");
            }
            else{
                JOptionPane.showMessageDialog(null, "Connect Server Fail");
                
            }
        } catch (Exception e) {
            String s = e.getMessage();
            if (s.contains("Connection refused to host")) {
                JOptionPane.showMessageDialog(null, "Can not connect to the server");

            } else {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
            System.exit(0);
        }
    }
    
    void loadTable(){
        DefaultTableModel model = (DefaultTableModel) tbArmor.getModel();
        if(armors !=null && !armors.isEmpty()){
            Vector header = new Vector();
            header.add("ID");
            header.add("Classification");
            header.add("TimeOfCreate");
            header.add("Defense");
            Vector data = new Vector();
            for (Armors a : armors) {
                Vector row = new Vector();
                row.add(a.getArmorId());
                row.add(a.getClassification());
                row.add(a.getTimeofcreate());
                row.add(a.getDefense());
                data.add(row);
            }
            model.setDataVector(data, header);
        }
        else{
            model.setRowCount(0);
        }
    }
    private boolean validInput(){
        String s;
        s=txtArmorId.getText();
        txtArmorId.setText(s);
        if(!s.matches("^A[0-9]+$")){
            JOptionPane.showMessageDialog(null, "The armorId must be A and digit");
            txtArmorId.requestFocus();
            return false;
        }
        if(s.length()>10){
            JOptionPane.showMessageDialog(null, "The armorId must be less or equal 10");
            txtArmorId.requestFocus();
            return false;
        }
        try {
            if(ai.findByArmorId(s) != null){
                JOptionPane.showMessageDialog(null, "The armorId was duplicate");
                txtArmorId.requestFocus();
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        //
        s=txtClassification.getText().trim();
        txtClassification.setText(s);
        if(s.isEmpty()){
            JOptionPane.showMessageDialog(null, "The classification is empty");
            txtClassification.requestFocus();
            return false;
        }
        if(s.length()>30){
            JOptionPane.showMessageDialog(null, "The classification must be less or equal 30");
            txtClassification.requestFocus();
            return false;
        }
        //
        s= txtDefense.getText().trim();
        txtDefense.setText(s);
        if(!s.matches("^[0-9]+$")){
            JOptionPane.showMessageDialog(null, "The Defense must be an integer number > 0");
            txtDefense.requestFocus();
            return false;
        }
        if(s.length()>10){
            JOptionPane.showMessageDialog(null, "The Defense must be less or equal 10 digit");
            txtDefense.requestFocus();
            return false;
        }
        if(Integer.parseInt(s)==0){
            JOptionPane.showMessageDialog(null, "The Defense must be an integer number > 0!!");
            txtDefense.requestFocus();
            return false;
        }
        //
        s=txtDescription.getText().trim();
        txtDescription.setText(s);
        if(s.isEmpty()){
            JOptionPane.showMessageDialog(null, "The description is empty");
            txtDescription.requestFocus();
            return false;
        }
        if(s.length()>300){
            JOptionPane.showMessageDialog(null, "The description must be less or equal 300");
            txtDescription.requestFocus();
            return false;
        }
        //
        s=txtStatus.getText().trim();
        txtStatus.setText(s);
        if(s.isEmpty()){
            JOptionPane.showMessageDialog(null, "The status is empty");
            txtStatus.requestFocus();
            return false;
        }
        if(s.length()>30){
            JOptionPane.showMessageDialog(null, "The status must be less or equal 30");
            txtStatus.requestFocus();
            return false;
        }
        
        return true;
    }
    private boolean validUpdate(){
        String s;
        s=txtClassification.getText().trim();
        txtClassification.setText(s);
        if(s.isEmpty()){
            JOptionPane.showMessageDialog(null, "The classification is empty");
            txtClassification.requestFocus();
            return false;
        }
        if(s.length()>30){
            JOptionPane.showMessageDialog(null, "The classification must be less or equal 30");
            txtClassification.requestFocus();
            return false;
        }
        //
        s= txtDefense.getText().trim();
        txtDefense.setText(s);
        if(!s.matches("^[0-9]+$")){
            JOptionPane.showMessageDialog(null, "The Defense must be a number > 0");
            txtDefense.requestFocus();
            return false;
        }
        if(s.length()>10){
            JOptionPane.showMessageDialog(null, "The Defense must be less or equal 10 digit");
            txtDefense.requestFocus();
            return false;
        }
        if(Integer.parseInt(s)==0){
            JOptionPane.showMessageDialog(null, "The Defense must be a number > 0!!");
            txtDefense.requestFocus();
            return false;
        }
        //
        s=txtDescription.getText().trim();
        txtDescription.setText(s);
        if(s.isEmpty()){
            JOptionPane.showMessageDialog(null, "The description is empty");
            txtDescription.requestFocus();
            return false;
        }
        if(s.length()>300){
            JOptionPane.showMessageDialog(null, "The description must be less or equal 300");
            txtDescription.requestFocus();
            return false;
        }
        //
        s=txtStatus.getText().trim();
        txtStatus.setText(s);
        if(s.isEmpty()){
            JOptionPane.showMessageDialog(null, "The status is empty");
            txtStatus.requestFocus();
            return false;
        }
        if(s.length()>30){
            JOptionPane.showMessageDialog(null, "The status must be less or equal 30");
            txtStatus.requestFocus();
            return false;
        }
        
        return true;
    }
    private boolean validArmorId(){
        String s;
        s=txtArmorId.getText();
        txtArmorId.setText(s);
        if(!s.matches("^A[0-9]+$")){
            JOptionPane.showMessageDialog(null, "The armorId must be A and digit");
            txtArmorId.requestFocus();
            return false;
        }
        if(s.length()>10){
            JOptionPane.showMessageDialog(null, "The armorId must be less or equal 10");
            txtArmorId.requestFocus();
            return false;
        }
        return true;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbArmor = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtArmorId = new javax.swing.JTextField();
        txtClassification = new javax.swing.JTextField();
        txtTime = new javax.swing.JTextField();
        txtDefense = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();
        txtStatus = new javax.swing.JTextField();
        btFind = new javax.swing.JButton();
        btCreate = new javax.swing.JButton();
        btUpdate = new javax.swing.JButton();
        btRemove = new javax.swing.JButton();
        btNew = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btGetAll = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        tbArmor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Classification", "TimeOfCreate", "Defense"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbArmor.getTableHeader().setReorderingAllowed(false);
        tbArmor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbArmorMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbArmor);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Armor's Details:"));

        jLabel2.setText("ArmorID:");

        jLabel3.setText("Classification:");

        jLabel4.setText("TimeOfCreate:");

        jLabel5.setText("Defense:");

        jLabel6.setText("Description:");

        jLabel7.setText("Status:");

        txtTime.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTimeMouseClicked(evt);
            }
        });

        txtDescription.setColumns(20);
        txtDescription.setRows(5);
        jScrollPane2.setViewportView(txtDescription);

        btFind.setText("Find By ArmorID");
        btFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btFindActionPerformed(evt);
            }
        });

        btCreate.setText("Create");
        btCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCreateActionPerformed(evt);
            }
        });

        btUpdate.setText("Update");
        btUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btUpdateActionPerformed(evt);
            }
        });

        btRemove.setText("Remove");
        btRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRemoveActionPerformed(evt);
            }
        });

        btNew.setText("New");
        btNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNewActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btNew)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btCreate)
                        .addGap(34, 34, 34)
                        .addComponent(btUpdate))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtDefense, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtTime, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtClassification, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtArmorId, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtStatus)))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btRemove)
                    .addComponent(btFind)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtArmorId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btFind))
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(txtClassification, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtDefense, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btCreate)
                    .addComponent(btUpdate)
                    .addComponent(btRemove)
                    .addComponent(btNew))
                .addGap(25, 25, 25))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Armor Client");

        btGetAll.setText("Get All");
        btGetAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGetAllActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(370, 370, 370)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(164, 164, 164)
                        .addComponent(btGetAll)))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 17, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addComponent(btGetAll)
                        .addContainerGap())
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCreateActionPerformed
        // TODO add your handling code here:
        if (validInput()) {
            String id = txtArmorId.getText();
            String classification = txtClassification.getText();
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date d = new Date();
            String time = df.format(d);
            int defense = Integer.parseInt(txtDefense.getText());
            String description = txtDescription.getText();
            String status = txtStatus.getText();
            Armors a = new Armors(id, classification, time, defense, description, status);
            //armors.add(a);
            try {
                if (ai.createArmor(a)) {
                    JOptionPane.showMessageDialog(null, "Create Successfully");
                    armors.add(a);
                    //loadTable();
                    
                } else {
                    JOptionPane.showMessageDialog(null, "Create fail");
                }
            } catch (Exception e) {
                String s = e.getMessage();
                if (s != null && !s.isEmpty()) {
                    if (s.contains("Connection refused to host")) {
                        JOptionPane.showMessageDialog(null, "Can not connect to the server");
                         System.exit(0);
                    } else {
                        JOptionPane.showMessageDialog(null, s);
                    }
                }
            }
            loadTable();
            txtArmorId.setEnabled(true);
            txtArmorId.setText("");
            txtClassification.setEnabled(false);
            txtClassification.setText("");
            txtDefense.setEnabled(false);
            txtDefense.setText("");
            txtDescription.setEnabled(false);
            txtDescription.setText("");
            txtStatus.setEnabled(false);
            txtStatus.setText("");
            txtTime.setEnabled(false);
            txtTime.setText("");
            btCreate.setEnabled(false);
            btUpdate.setEnabled(true);
            btRemove.setEnabled(true);
        }
        
    }//GEN-LAST:event_btCreateActionPerformed

    private void btGetAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGetAllActionPerformed
        // TODO add your handling code here:
        try {
            armors.clear();
            armors.addAll(ai.getAllArmor());
            if(armors != null && !armors.isEmpty()){
                loadTable();
            }
            else{
                JOptionPane.showMessageDialog(null, "List empty");
            }
            
        } catch (Exception e) {
            String s = e.getMessage();
            if (s != null && !s.isEmpty()) {
                if (s.contains("Connection refused to host")) {
                    JOptionPane.showMessageDialog(null, "Can not connect to the server");
                    System.exit(0);
                } else {
                    JOptionPane.showMessageDialog(null, s);
                }
            }
        }
        txtArmorId.setEnabled(true);
        txtArmorId.setText("");
        txtClassification.setEnabled(false);
        txtClassification.setText("");
        txtDefense.setEnabled(false);
        txtDefense.setText("");
        txtDescription.setEnabled(false);
        txtDescription.setText("");
        txtStatus.setEnabled(false);
        txtStatus.setText("");
        txtTime.setEnabled(false);
        txtTime.setText("");
        btCreate.setEnabled(false);
        btUpdate.setEnabled(true);
        btRemove.setEnabled(true);
    }//GEN-LAST:event_btGetAllActionPerformed

    private void tbArmorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbArmorMouseClicked
        // TODO add your handling code here:
        try {
            int pos = tbArmor.getSelectedRow();
            Armors a = armors.get(pos);
            txtArmorId.setText(a.getArmorId());
            txtClassification.setText(a.getClassification());
            txtTime.setText(a.getTimeofcreate());
            txtDefense.setText("" + a.getDefense());
            txtDescription.setText(a.getDescription());
            txtStatus.setText(a.getStatus());
            txtArmorId.setEnabled(false);
            txtClassification.setEnabled(true);
            txtDefense.setEnabled(true);
            txtDescription.setEnabled(true);
            txtStatus.setEnabled(true);
            txtTime.setEnabled(true);
            btCreate.setEnabled(false);
            btUpdate.setEnabled(true);
            btRemove.setEnabled(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            loadTable();

        }
    }//GEN-LAST:event_tbArmorMouseClicked

    private void btRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRemoveActionPerformed
        // TODO add your handling code here:
        int pos = tbArmor.getSelectedRow();
        if(pos<0){
            JOptionPane.showMessageDialog(null, "Please choose for removing");
        }
        else {
            int option = JOptionPane.showConfirmDialog(null, "Are you sure to delete?",
                    "Deletion", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                String id = txtArmorId.getText();
                try {
                    if (ai.removeArmor(id)) {
                        JOptionPane.showMessageDialog(null, "The armorID " + id + " is removed");
                        armors.remove(pos);
                        //loadTable();
                    } else {
                        JOptionPane.showMessageDialog(null, "Remove Fail");
                    }
                } catch (Exception e) {
                    String s = e.getMessage();
                    if (s != null && !s.isEmpty()) {
                        if (s.contains("Connection refused to host")) {
                            JOptionPane.showMessageDialog(null, "Can not connect to the server");
                            System.exit(0);
                        } else {
                            JOptionPane.showMessageDialog(null, s);
                        }
                    }
                }
            } else {
                tbArmor.removeRowSelectionInterval(pos, pos);
            }
            loadTable();
            txtArmorId.setEnabled(true);
            txtArmorId.setText("");
            txtClassification.setEnabled(false);
            txtClassification.setText("");
            txtDefense.setEnabled(false);
            txtDefense.setText("");
            txtDescription.setEnabled(false);
            txtDescription.setText("");
            txtStatus.setEnabled(false);
            txtStatus.setText("");
            txtTime.setEnabled(false);
            txtTime.setText("");
        }

    }//GEN-LAST:event_btRemoveActionPerformed

    private void btUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btUpdateActionPerformed
        // TODO add your handling code here:
        int pos = tbArmor.getSelectedRow();
        if(pos<0){
            JOptionPane.showMessageDialog(null, "Please choose for updating");
        }
        else {
            if (validUpdate()) {
                String id = txtArmorId.getText();
                String classification = txtClassification.getText();
                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date d = new Date();
                String time = df.format(d);
                int defense = Integer.parseInt(txtDefense.getText());
                String description = txtDescription.getText();
                String status = txtStatus.getText();
                Armors a = new Armors(id, classification, time, defense, description, status);
                try {
                    if (ai.updateArmor(a)) {
                        armors.set(pos, a);
                        JOptionPane.showMessageDialog(null, "The armorID " + id + " is updated successfully");
                        //loadTable();
                    } else {
                        JOptionPane.showMessageDialog(null, "Update Fail");
                    }

                } catch (Exception e) {
                    String s = e.getMessage();
                    if (s != null && !s.isEmpty()) {
                        if (s.contains("Connection refused to host")) {
                            JOptionPane.showMessageDialog(null, "Can not connect to the server");
                            System.exit(0);
                        } else {
                            JOptionPane.showMessageDialog(null, s);
                        }
                    }
                }
                loadTable();
                txtArmorId.setEnabled(true);
                txtArmorId.setText("");
                txtClassification.setEnabled(false);
                txtClassification.setText("");
                txtDefense.setEnabled(false);
                txtDefense.setText("");
                txtDescription.setEnabled(false);
                txtDescription.setText("");
                txtStatus.setEnabled(false);
                txtStatus.setText("");
                txtTime.setEnabled(false);
                txtTime.setText("");
            }
        }
    }//GEN-LAST:event_btUpdateActionPerformed

    private void btFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btFindActionPerformed
        // TODO add your handling code here:
        if (validArmorId()) {
            String id = txtArmorId.getText();
            try {
                Armors a = ai.findByArmorId(id);
                if (a != null) {
                    armors.clear();
                    armors.add(a);
                    loadTable();
                } else {
                    JOptionPane.showMessageDialog(null, "The armor was not exist");
                }
            } catch (Exception e) {
                String s = e.getMessage();
                if (s != null && !s.isEmpty()) {
                    if (s.contains("Connection refused to host")) {
                        JOptionPane.showMessageDialog(null, "Can not connect to the server");
                        System.exit(0);
                    } else {
                        JOptionPane.showMessageDialog(null, s);
                    }
                }
            }
            //txtArmorId.setText("");
        }
        
    }//GEN-LAST:event_btFindActionPerformed

    private void btNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNewActionPerformed
        // TODO add your handling code here:
        int pos=tbArmor.getSelectedRow();
        if(pos>=0){
            tbArmor.removeRowSelectionInterval(pos, pos);
        }
        txtArmorId.setEnabled(true);
        txtArmorId.setText("");
        txtClassification.setEnabled(true);
        txtClassification.setText("");
        txtDefense.setEnabled(true);
        txtDefense.setText("");
        txtDescription.setEnabled(true);
        txtDescription.setText("");
        txtStatus.setEnabled(true);
        txtStatus.setText("");
        txtTime.setEnabled(true);
        txtTime.setText(" The current time is automatically filled");
        btCreate.setEnabled(true);
        btUpdate.setEnabled(false);
        btRemove.setEnabled(false);
    }//GEN-LAST:event_btNewActionPerformed

    private void txtTimeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTimeMouseClicked
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "The current time is automatically filled\n You don't need to type.");
    }//GEN-LAST:event_txtTimeMouseClicked

   
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCreate;
    private javax.swing.JButton btFind;
    private javax.swing.JButton btGetAll;
    private javax.swing.JButton btNew;
    private javax.swing.JButton btRemove;
    private javax.swing.JButton btUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tbArmor;
    private javax.swing.JTextField txtArmorId;
    private javax.swing.JTextField txtClassification;
    private javax.swing.JTextField txtDefense;
    private javax.swing.JTextArea txtDescription;
    private javax.swing.JTextField txtStatus;
    private javax.swing.JTextField txtTime;
    // End of variables declaration//GEN-END:variables
}
