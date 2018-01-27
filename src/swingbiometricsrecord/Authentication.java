/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swingbiometricsrecord;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author eleazar
 */
public class Authentication extends javax.swing.JFrame {
    Database database = new Database();
    Security security = new Security();
    static int courseNumber, userPosition;
    static String userCourse, softwareUser;

    public Authentication() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        usernameField = new javax.swing.JTextField();
        passwordField = new javax.swing.JPasswordField();
        usernameLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        loginButton = new javax.swing.JButton();
        schoolName = new javax.swing.JLabel();
        systemName = new javax.swing.JLabel();
        schoolLogo = new javax.swing.JLabel();
        userAs = new javax.swing.JComboBox<>();
        loginAsLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(40, 40, 137));
        setForeground(new java.awt.Color(95, 132, 209));

        usernameLabel.setText("Username");

        passwordLabel.setText("Password");

        loginButton.setText("Login");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        schoolName.setText("Bicol University Gubat Campus");

        systemName.setText("Department Based Organization Record and Monitoring System");

        userAs.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "admin", "dbo officer" }));

        loginAsLabel.setText("Login as:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 32, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(systemName)
                        .addGap(36, 36, 36))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(schoolName)
                        .addGap(122, 122, 122))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(146, 146, 146)
                        .addComponent(schoolLogo))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(174, 174, 174)
                        .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(131, 131, 131)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(loginAsLabel)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(usernameField)
                                .addComponent(usernameLabel)
                                .addComponent(passwordField)
                                .addComponent(passwordLabel)
                                .addComponent(userAs, 0, 164, Short.MAX_VALUE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(schoolName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(systemName)
                .addGap(18, 18, 18)
                .addComponent(schoolLogo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(loginAsLabel)
                .addGap(4, 4, 4)
                .addComponent(userAs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(usernameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(passwordLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(loginButton)
                .addGap(39, 39, 39))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        String choice = userAs.getSelectedItem() + "";
        if (choice.equals("admin")){
           loginAsAdmin();
        }else if(choice.equals("dbo officer")){
            loginAsDboOfficer();
        }
    }//GEN-LAST:event_loginButtonActionPerformed

    public void loginAsAdmin(){
        String adminLogin = "SELECT * FROM `user` WHERE `username` = '"+usernameField.getText()+"' AND `password` = '"+security.encrypt(passwordField.getText())+"' ";
        System.out.println(adminLogin);
        try{
            database.connect();
            Statement statement = database.connection.createStatement();
            ResultSet result = statement.executeQuery(adminLogin);
            if (result.next()){
                this.dispose();
                BiometricRegistration.main();
            }else{
                JOptionPane.showMessageDialog(null, "Username/Password doesn't match");
            }
            database.connection.close();
        }catch(SQLException sql){
            sql.printStackTrace();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        usernameField.setText("");
        passwordField.setText("");
    }
    
    public void loginAsDboOfficer(){
        String loginQuery = "SELECT * FROM `students` JOIN `courses` ON `students`.`course` = `courses`.`courseId` WHERE `appUserName` = '"+usernameField.getText()+"' AND `appPassword` = '"+security.encrypt(passwordField.getText())+"' ";
        try{
            database.connect();
            Statement statement = database.connection.createStatement();
            ResultSet resultset = statement.executeQuery(loginQuery);
            if(resultset.next()){
                courseNumber = resultset.getInt("course");
                userCourse = resultset.getString("organization");
                softwareUser = resultset.getString("firstName")+" "+resultset.getString("lastName");
                userPosition = resultset.getInt("position");
                this.dispose();
                EventsSelector.main();
            }else{
                JOptionPane.showMessageDialog(null, "Username/Password doesn't match");
            }
            database.connection.close();
        }catch(SQLException sql){
            
        }
    }
    
    public static void main() {
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
            java.util.logging.Logger.getLogger(Authentication.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Authentication.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Authentication.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Authentication.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Authentication().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel loginAsLabel;
    private javax.swing.JButton loginButton;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JLabel schoolLogo;
    private javax.swing.JLabel schoolName;
    private javax.swing.JLabel systemName;
    private javax.swing.JComboBox<String> userAs;
    private javax.swing.JTextField usernameField;
    private javax.swing.JLabel usernameLabel;
    // End of variables declaration//GEN-END:variables
}
