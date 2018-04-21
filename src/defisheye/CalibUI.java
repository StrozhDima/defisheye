/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package defisheye;

/**
 *
 * @author Dzmitry
 */
public class CalibUI extends javax.swing.JDialog {

    /**
     * Creates new form CalibUI
     */
    public CalibUI(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
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

        panelToolsCalib = new javax.swing.JPanel();
        labelCalibPathExamples = new javax.swing.JLabel();
        textFieldCalibPathExamples = new javax.swing.JTextField();
        labelCalibPlanarType = new javax.swing.JLabel();
        comboBoxCalibPlanarType = new javax.swing.JComboBox<>();
        labelCalibPlanarRows = new javax.swing.JLabel();
        spinnerCalibRows = new javax.swing.JSpinner();
        labelCalibPlanarCols = new javax.swing.JLabel();
        spinnerCalibCols = new javax.swing.JSpinner();
        labelCalibPlanarSquareWidth = new javax.swing.JLabel();
        spinnerCalibSquareWidth = new javax.swing.JSpinner();
        labelCalibPlanarSpaceWidth = new javax.swing.JLabel();
        spinnerCalibSpaceWidth = new javax.swing.JSpinner();
        labelCalibPlanarCenterDistance = new javax.swing.JLabel();
        spinnerCalibCenterDistance = new javax.swing.JSpinner();
        buttonCalibRun = new javax.swing.JButton();
        panelPlanarImageCalib = new javax.swing.JPanel();
        menuBarCalib = new javax.swing.JMenuBar();
        menuFileCalib = new javax.swing.JMenu();
        menuItemOpenCalib = new javax.swing.JMenuItem();
        menuItemCloseCalib = new javax.swing.JMenuItem();
        menuItemExitCalib = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Auto camera calibration");
        setBackground(new java.awt.Color(0, 102, 153));
        setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        setLocationByPlatform(true);
        setMaximumSize(new java.awt.Dimension(800, 600));
        setMinimumSize(new java.awt.Dimension(800, 600));
        setModal(true);
        setName("dialogAutoCalib"); // NOI18N
        setPreferredSize(new java.awt.Dimension(800, 600));
        setResizable(false);

        panelToolsCalib.setBackground(new java.awt.Color(0, 204, 204));
        panelToolsCalib.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelToolsCalib.setForeground(new java.awt.Color(255, 255, 255));
        panelToolsCalib.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N

        labelCalibPathExamples.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        labelCalibPathExamples.setForeground(new java.awt.Color(255, 255, 255));
        labelCalibPathExamples.setText("Path examples:");

        textFieldCalibPathExamples.setBackground(new java.awt.Color(0, 204, 204));
        textFieldCalibPathExamples.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        textFieldCalibPathExamples.setForeground(new java.awt.Color(255, 255, 255));
        textFieldCalibPathExamples.setToolTipText("");
        textFieldCalibPathExamples.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        textFieldCalibPathExamples.setCaretColor(new java.awt.Color(255, 255, 255));
        textFieldCalibPathExamples.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        textFieldCalibPathExamples.setDisabledTextColor(new java.awt.Color(0, 51, 102));
        textFieldCalibPathExamples.setName(""); // NOI18N
        textFieldCalibPathExamples.setPreferredSize(new java.awt.Dimension(135, 23));
        textFieldCalibPathExamples.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldCalibPathExamplesActionPerformed(evt);
            }
        });

        labelCalibPlanarType.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        labelCalibPlanarType.setForeground(new java.awt.Color(255, 255, 255));
        labelCalibPlanarType.setText("Planar type:");

        comboBoxCalibPlanarType.setBackground(new java.awt.Color(0, 204, 204));
        comboBoxCalibPlanarType.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        comboBoxCalibPlanarType.setForeground(new java.awt.Color(255, 255, 255));
        comboBoxCalibPlanarType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chessboard", "Square Grids", "Circle Hexagonal", "Circle Regular Grid" }));
        comboBoxCalibPlanarType.setName(""); // NOI18N
        comboBoxCalibPlanarType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxCalibPlanarTypeActionPerformed(evt);
            }
        });

        labelCalibPlanarRows.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        labelCalibPlanarRows.setForeground(new java.awt.Color(255, 255, 255));
        labelCalibPlanarRows.setText("Rows:");

        spinnerCalibRows.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        spinnerCalibRows.setModel(new javax.swing.SpinnerNumberModel());
        spinnerCalibRows.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        labelCalibPlanarCols.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        labelCalibPlanarCols.setForeground(new java.awt.Color(255, 255, 255));
        labelCalibPlanarCols.setText("Cols:");

        spinnerCalibCols.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        spinnerCalibCols.setModel(new javax.swing.SpinnerNumberModel());
        spinnerCalibCols.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        labelCalibPlanarSquareWidth.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        labelCalibPlanarSquareWidth.setForeground(new java.awt.Color(255, 255, 255));
        labelCalibPlanarSquareWidth.setText("Square Width:");

        spinnerCalibSquareWidth.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        spinnerCalibSquareWidth.setModel(new javax.swing.SpinnerNumberModel());
        spinnerCalibSquareWidth.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        labelCalibPlanarSpaceWidth.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        labelCalibPlanarSpaceWidth.setForeground(new java.awt.Color(255, 255, 255));
        labelCalibPlanarSpaceWidth.setText("Space Width:");

        spinnerCalibSpaceWidth.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        spinnerCalibSpaceWidth.setModel(new javax.swing.SpinnerNumberModel());
        spinnerCalibSpaceWidth.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        labelCalibPlanarCenterDistance.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        labelCalibPlanarCenterDistance.setForeground(new java.awt.Color(255, 255, 255));
        labelCalibPlanarCenterDistance.setText("Center Distance:");

        spinnerCalibCenterDistance.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        spinnerCalibCenterDistance.setModel(new javax.swing.SpinnerNumberModel());
        spinnerCalibCenterDistance.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        buttonCalibRun.setBackground(new java.awt.Color(0, 153, 153));
        buttonCalibRun.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        buttonCalibRun.setForeground(new java.awt.Color(255, 255, 255));
        buttonCalibRun.setText("Run");
        buttonCalibRun.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonCalibRunMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelToolsCalibLayout = new javax.swing.GroupLayout(panelToolsCalib);
        panelToolsCalib.setLayout(panelToolsCalibLayout);
        panelToolsCalibLayout.setHorizontalGroup(
            panelToolsCalibLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelToolsCalibLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelToolsCalibLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelCalibPathExamples, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textFieldCalibPathExamples, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelToolsCalibLayout.createSequentialGroup()
                        .addGroup(panelToolsCalibLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelToolsCalibLayout.createSequentialGroup()
                                .addComponent(labelCalibPlanarType, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboBoxCalibPlanarType, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelToolsCalibLayout.createSequentialGroup()
                                .addGroup(panelToolsCalibLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(labelCalibPlanarSpaceWidth, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(labelCalibPlanarCenterDistance, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelToolsCalibLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(spinnerCalibCenterDistance, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelToolsCalibLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(spinnerCalibRows, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                                        .addComponent(spinnerCalibCols)
                                        .addComponent(spinnerCalibSquareWidth)
                                        .addComponent(spinnerCalibSpaceWidth))))
                            .addComponent(labelCalibPlanarSquareWidth, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelCalibPlanarCols, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelCalibPlanarRows, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(buttonCalibRun, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelToolsCalibLayout.setVerticalGroup(
            panelToolsCalibLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelToolsCalibLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelCalibPathExamples, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textFieldCalibPathExamples, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelToolsCalibLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCalibPlanarType, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBoxCalibPlanarType, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelToolsCalibLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelCalibPlanarRows, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(spinnerCalibRows, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelToolsCalibLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelCalibPlanarCols, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(spinnerCalibCols, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelToolsCalibLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelCalibPlanarSquareWidth, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(spinnerCalibSquareWidth, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelToolsCalibLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelCalibPlanarSpaceWidth, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(spinnerCalibSpaceWidth, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelToolsCalibLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelCalibPlanarCenterDistance, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(spinnerCalibCenterDistance, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 129, Short.MAX_VALUE)
                .addComponent(buttonCalibRun, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        comboBoxCalibPlanarType.getAccessibleContext().setAccessibleDescription("Choose type");

        panelPlanarImageCalib.setBackground(new java.awt.Color(0, 0, 51));
        panelPlanarImageCalib.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelPlanarImageCalib.setForeground(new java.awt.Color(255, 255, 255));
        panelPlanarImageCalib.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N

        javax.swing.GroupLayout panelPlanarImageCalibLayout = new javax.swing.GroupLayout(panelPlanarImageCalib);
        panelPlanarImageCalib.setLayout(panelPlanarImageCalibLayout);
        panelPlanarImageCalibLayout.setHorizontalGroup(
            panelPlanarImageCalibLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        panelPlanarImageCalibLayout.setVerticalGroup(
            panelPlanarImageCalibLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        menuBarCalib.setBackground(new java.awt.Color(0, 204, 204));
        menuBarCalib.setForeground(new java.awt.Color(255, 255, 255));
        menuBarCalib.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N

        menuFileCalib.setBackground(new java.awt.Color(0, 204, 204));
        menuFileCalib.setForeground(new java.awt.Color(255, 255, 255));
        menuFileCalib.setText("File");
        menuFileCalib.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        menuFileCalib.setOpaque(true);

        menuItemOpenCalib.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        menuItemOpenCalib.setBackground(new java.awt.Color(0, 204, 204));
        menuItemOpenCalib.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        menuItemOpenCalib.setForeground(new java.awt.Color(255, 255, 255));
        menuItemOpenCalib.setIcon(new javax.swing.ImageIcon(getClass().getResource("/defisheye/Open_24x24.png"))); // NOI18N
        menuItemOpenCalib.setText("Open...");
        menuItemOpenCalib.setOpaque(true);
        menuFileCalib.add(menuItemOpenCalib);

        menuItemCloseCalib.setBackground(new java.awt.Color(0, 204, 204));
        menuItemCloseCalib.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        menuItemCloseCalib.setForeground(new java.awt.Color(255, 255, 255));
        menuItemCloseCalib.setIcon(new javax.swing.ImageIcon(getClass().getResource("/defisheye/Close_24x24.png"))); // NOI18N
        menuItemCloseCalib.setText("Close");
        menuItemCloseCalib.setOpaque(true);
        menuItemCloseCalib.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemCloseCalibActionPerformed(evt);
            }
        });
        menuFileCalib.add(menuItemCloseCalib);

        menuItemExitCalib.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0));
        menuItemExitCalib.setBackground(new java.awt.Color(0, 204, 204));
        menuItemExitCalib.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        menuItemExitCalib.setForeground(new java.awt.Color(255, 255, 255));
        menuItemExitCalib.setIcon(new javax.swing.ImageIcon(getClass().getResource("/defisheye/Exit_24x24.png"))); // NOI18N
        menuItemExitCalib.setText("Exit");
        menuItemExitCalib.setOpaque(true);
        menuFileCalib.add(menuItemExitCalib);

        menuBarCalib.add(menuFileCalib);

        setJMenuBar(menuBarCalib);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelToolsCalib, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelPlanarImageCalib, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelToolsCalib, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelPlanarImageCalib, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuItemCloseCalibActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemCloseCalibActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuItemCloseCalibActionPerformed

    private void textFieldCalibPathExamplesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldCalibPathExamplesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldCalibPathExamplesActionPerformed

    private void comboBoxCalibPlanarTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxCalibPlanarTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxCalibPlanarTypeActionPerformed

    private void buttonCalibRunMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonCalibRunMouseClicked

    }//GEN-LAST:event_buttonCalibRunMouseClicked

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
            java.util.logging.Logger.getLogger(CalibUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CalibUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CalibUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CalibUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CalibUI dialog = new CalibUI(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton buttonCalibRun;
    private javax.swing.JComboBox<String> comboBoxCalibPlanarType;
    private javax.swing.JLabel labelCalibPathExamples;
    private javax.swing.JLabel labelCalibPlanarCenterDistance;
    private javax.swing.JLabel labelCalibPlanarCols;
    private javax.swing.JLabel labelCalibPlanarRows;
    private javax.swing.JLabel labelCalibPlanarSpaceWidth;
    private javax.swing.JLabel labelCalibPlanarSquareWidth;
    private javax.swing.JLabel labelCalibPlanarType;
    private javax.swing.JMenuBar menuBarCalib;
    private javax.swing.JMenu menuFileCalib;
    private javax.swing.JMenuItem menuItemCloseCalib;
    private javax.swing.JMenuItem menuItemExitCalib;
    private javax.swing.JMenuItem menuItemOpenCalib;
    private javax.swing.JPanel panelPlanarImageCalib;
    private javax.swing.JPanel panelToolsCalib;
    private javax.swing.JSpinner spinnerCalibCenterDistance;
    private javax.swing.JSpinner spinnerCalibCols;
    private javax.swing.JSpinner spinnerCalibRows;
    private javax.swing.JSpinner spinnerCalibSpaceWidth;
    private javax.swing.JSpinner spinnerCalibSquareWidth;
    private javax.swing.JTextField textFieldCalibPathExamples;
    // End of variables declaration//GEN-END:variables
}
