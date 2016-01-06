package GUI;

import Helper.Config;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import static org.apache.poi.ss.formula.functions.BooleanFunction.NOT;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author huangsmart
 */
public class JChooseFile extends javax.swing.JFrame {

    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(JChooseFile.class);

    /**
     * Creates new form JChooseFile
     */
    public JChooseFile() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser = new javax.swing.JFileChooser();
        jDialogError = new javax.swing.JDialog();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaErrorMsg = new javax.swing.JTextArea();
        jLabelPopupTitle = new javax.swing.JLabel();
        jDialogSuccess = new javax.swing.JDialog();
        jLabelSuccess = new javax.swing.JLabel();
        jDialogLoading = new javax.swing.JDialog();
        jLabelLoading = new javax.swing.JLabel();
        jPanelMain = new javax.swing.JPanel();
        jOpenFile = new javax.swing.JButton();
        jImportData = new javax.swing.JButton();
        jFilePath = new javax.swing.JTextField();
        jTextFieldHost = new javax.swing.JTextField();
        jLabelHost = new javax.swing.JLabel();
        jLabelUser = new javax.swing.JLabel();
        jLabelPassword = new javax.swing.JLabel();
        jTextFieldUser = new javax.swing.JTextField();
        jLabelContact = new javax.swing.JLabel();
        jTextFieldContact = new javax.swing.JTextField();
        jLabelContactPhone = new javax.swing.JLabel();
        jTextFieldContactPhone = new javax.swing.JTextField();
        jLabelPurchaser = new javax.swing.JLabel();
        jTextFieldPurchaser = new javax.swing.JTextField();
        jTextFieldPassword = new javax.swing.JTextField();
        jLabelQuality = new javax.swing.JLabel();
        jTextFieldQuality = new javax.swing.JTextField();

        jFileChooser.setDialogTitle("选择文件");
        jFileChooser.setFileFilter(new ExcelFilter());

        jDialogError.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jDialogError.setTitle("出错啦！");
        jDialogError.setAlwaysOnTop(true);
        jDialogError.setBounds(new java.awt.Rectangle(150, 23, 360, 200));
        jDialogError.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jDialogError.setMinimumSize(new java.awt.Dimension(360, 200));
        jDialogError.setResizable(false);

        jTextAreaErrorMsg.setColumns(20);
        jTextAreaErrorMsg.setRows(5);
        jScrollPane1.setViewportView(jTextAreaErrorMsg);

        jLabelPopupTitle.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabelPopupTitle.setText("出错啦 !");

        javax.swing.GroupLayout jDialogErrorLayout = new javax.swing.GroupLayout(jDialogError.getContentPane());
        jDialogError.getContentPane().setLayout(jDialogErrorLayout);
        jDialogErrorLayout.setHorizontalGroup(
            jDialogErrorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogErrorLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jDialogErrorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelPopupTitle))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jDialogErrorLayout.setVerticalGroup(
            jDialogErrorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogErrorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelPopupTitle)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jDialogSuccess.setTitle("导入成功");
        jDialogSuccess.setAlwaysOnTop(true);
        jDialogSuccess.setBounds(new java.awt.Rectangle(150, 50, 300, 100));
        jDialogSuccess.setMaximumSize(new java.awt.Dimension(300, 100));
        jDialogSuccess.setMinimumSize(new java.awt.Dimension(300, 100));

        jLabelSuccess.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabelSuccess.setText("导入成功！");

        javax.swing.GroupLayout jDialogSuccessLayout = new javax.swing.GroupLayout(jDialogSuccess.getContentPane());
        jDialogSuccess.getContentPane().setLayout(jDialogSuccessLayout);
        jDialogSuccessLayout.setHorizontalGroup(
            jDialogSuccessLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogSuccessLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabelSuccess)
                .addContainerGap(139, Short.MAX_VALUE))
        );
        jDialogSuccessLayout.setVerticalGroup(
            jDialogSuccessLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogSuccessLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabelSuccess)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jDialogLoading.setTitle("导入数据中，请稍后");
        jDialogLoading.setAlwaysOnTop(true);
        jDialogLoading.setBounds(new java.awt.Rectangle(150, 50, 300, 100));
        jDialogLoading.setIconImage(null);
        jDialogLoading.setMaximumSize(new java.awt.Dimension(300, 100));
        jDialogLoading.setMinimumSize(new java.awt.Dimension(300, 100));
        jDialogLoading.setPreferredSize(new java.awt.Dimension(300, 100));

        jLabelLoading.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabelLoading.setText("导入数据中，请稍后");

        javax.swing.GroupLayout jDialogLoadingLayout = new javax.swing.GroupLayout(jDialogLoading.getContentPane());
        jDialogLoading.getContentPane().setLayout(jDialogLoadingLayout);
        jDialogLoadingLayout.setHorizontalGroup(
            jDialogLoadingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogLoadingLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabelLoading)
                .addContainerGap(67, Short.MAX_VALUE))
        );
        jDialogLoadingLayout.setVerticalGroup(
            jDialogLoadingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogLoadingLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabelLoading)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jDialogLoading.getAccessibleContext().setAccessibleName("");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("德典数据导入工具");
        setMinimumSize(new java.awt.Dimension(660, 280));
        setResizable(false);

        jOpenFile.setText("打开文件");
        jOpenFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jOpenFileActionPerformed(evt);
            }
        });

        jImportData.setText("导入");
        jImportData.setEnabled(false);
        jImportData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jImportDataActionPerformed(evt);
            }
        });

        jFilePath.setEditable(false);
        jFilePath.setToolTipText("输入文件路径");
        jFilePath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFilePathActionPerformed(evt);
            }
        });

        jTextFieldHost.setEditable(false);
        jTextFieldHost.setText(Config.getInstance().getValue("host"));
        jTextFieldHost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldHostActionPerformed(evt);
            }
        });
        jTextFieldHost.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTextFieldHostPropertyChange(evt);
            }
        });
        jTextFieldHost.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                warn();
            }
            public void removeUpdate(DocumentEvent e) {
                warn();
            }
            public void insertUpdate(DocumentEvent e) {
                warn();
            }

            public void warn() {
                System.out.println("Test Change lisenter -> " + jTextFieldHost.getText());
            }
        });

        jLabelHost.setText("HOST");

        jLabelUser.setText("用户");

        jLabelPassword.setText("密码");

        jTextFieldUser.setEditable(false);
        jTextFieldUser.setText(Config.getInstance().getValue("user")
        );

        jLabelContact.setText("联系人");

        jTextFieldContact.setEditable(false);
        jTextFieldContact.setText(Config.getInstance().getValue("contactPerson")
        );

        jLabelContactPhone.setText("联系电话");

        jTextFieldContactPhone.setEditable(false);
        jTextFieldContactPhone.setText(Config.getInstance().getValue("contactPhoneNumber")
        );

        jLabelPurchaser.setText("采购员");

        jTextFieldPurchaser.setEditable(false);
        jTextFieldPurchaser.setText(Config.getInstance().getValue("purchaser")
        );

        jTextFieldPassword.setEditable(false);
        jTextFieldPassword.setText(Config.getInstance().getValue("password"));

        jLabelQuality.setText("质检员");

        jTextFieldQuality.setEditable(false);
        jTextFieldQuality.setText(Config.getInstance().getValue("qualityPerson"));

        javax.swing.GroupLayout jPanelMainLayout = new javax.swing.GroupLayout(jPanelMain);
        jPanelMain.setLayout(jPanelMainLayout);
        jPanelMainLayout.setHorizontalGroup(
            jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMainLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelMainLayout.createSequentialGroup()
                        .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jImportData, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jOpenFile, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18))
                    .addGroup(jPanelMainLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelUser, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelHost, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)))
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jFilePath, javax.swing.GroupLayout.DEFAULT_SIZE, 522, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMainLayout.createSequentialGroup()
                        .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanelMainLayout.createSequentialGroup()
                                .addComponent(jTextFieldUser, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(90, 90, 90)
                                .addComponent(jLabelContactPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextFieldContactPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelMainLayout.createSequentialGroup()
                                .addComponent(jTextFieldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(90, 90, 90)
                                .addComponent(jLabelPurchaser, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextFieldPurchaser, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelMainLayout.createSequentialGroup()
                                .addComponent(jTextFieldHost, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(90, 90, 90)
                                .addComponent(jLabelContact, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextFieldContact, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(49, 49, 49))
                    .addGroup(jPanelMainLayout.createSequentialGroup()
                        .addGap(236, 236, 236)
                        .addComponent(jLabelQuality, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldQuality, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(3, 3, 3))
        );
        jPanelMainLayout.setVerticalGroup(
            jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMainLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jOpenFile)
                    .addComponent(jFilePath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jImportData)
                .addGap(18, 18, 18)
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextFieldContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelContact))
                    .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextFieldHost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelHost)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextFieldContactPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelContactPhone))
                    .addComponent(jLabelUser)
                    .addComponent(jTextFieldUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextFieldPurchaser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelPurchaser))
                    .addComponent(jLabelPassword)
                    .addComponent(jTextFieldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelQuality)
                    .addComponent(jTextFieldQuality, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanelMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jImportDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jImportDataActionPerformed
        // TODO add your handling code here:
        String filePath = jFilePath.getText();
        if (!filePath.isEmpty()) {
            jDialogLoading.setVisible(true);
            jImportData.setEnabled(false);
            try {
                ImportFile.doImport(filePath);
                jDialogLoading.dispose();
                jDialogSuccess.setVisible(true);
            } catch (Exception ex) {
                jDialogLoading.dispose();
                jTextAreaErrorMsg.setText("导入文件的时候出错了，请检查原文件是否符合规范");
                jDialogError.setVisible(true);
                logger.error("Error in importing files. \n" + ex);
            }
        }


    }//GEN-LAST:event_jImportDataActionPerformed

    private void jFilePathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFilePathActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFilePathActionPerformed

    private void jOpenFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jOpenFileActionPerformed
        int returnVal = jFileChooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = jFileChooser.getSelectedFile();
            System.out.println("Get file path" + file.getAbsolutePath());
            jFilePath.setText(file.getAbsolutePath());
            jImportData.setEnabled(true);
        } else {
            System.out.println("File access cancelled by user.");
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jOpenFileActionPerformed

    private void jTextFieldHostPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTextFieldHostPropertyChange
        // TODO add your handling code here:
        Config.getInstance().setValue("host", jTextFieldHost.getText());
    }//GEN-LAST:event_jTextFieldHostPropertyChange

    private void jTextFieldHostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldHostActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldHostActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws IOException {
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
            java.util.logging.Logger.getLogger(JChooseFile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JChooseFile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JChooseFile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JChooseFile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JChooseFile().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog jDialogError;
    private javax.swing.JDialog jDialogLoading;
    private javax.swing.JDialog jDialogSuccess;
    private javax.swing.JFileChooser jFileChooser;
    private javax.swing.JTextField jFilePath;
    private javax.swing.JButton jImportData;
    private javax.swing.JLabel jLabelContact;
    private javax.swing.JLabel jLabelContactPhone;
    private javax.swing.JLabel jLabelHost;
    private javax.swing.JLabel jLabelLoading;
    private javax.swing.JLabel jLabelPassword;
    private javax.swing.JLabel jLabelPopupTitle;
    private javax.swing.JLabel jLabelPurchaser;
    private javax.swing.JLabel jLabelQuality;
    private javax.swing.JLabel jLabelSuccess;
    private javax.swing.JLabel jLabelUser;
    private javax.swing.JButton jOpenFile;
    private javax.swing.JPanel jPanelMain;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaErrorMsg;
    private javax.swing.JTextField jTextFieldContact;
    private javax.swing.JTextField jTextFieldContactPhone;
    private javax.swing.JTextField jTextFieldHost;
    private javax.swing.JTextField jTextFieldPassword;
    private javax.swing.JTextField jTextFieldPurchaser;
    private javax.swing.JTextField jTextFieldQuality;
    private javax.swing.JTextField jTextFieldUser;
    // End of variables declaration//GEN-END:variables

}
