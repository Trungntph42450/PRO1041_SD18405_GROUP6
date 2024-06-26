/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import javax.swing.JOptionPane;
import service.servicImp.TaiKhoanServiceImp;

/**
 *
 * @author Nguyen Thanh Trung
 */
public class DoiMatKhauView extends javax.swing.JPanel {

    TaiKhoanServiceImp serviceDMK = new TaiKhoanServiceImp();

    /**
     * Creates new form DoiMatKhauView
     */
    public DoiMatKhauView() {
        initComponents();
        this.setSize(1300, 755);
    }

    public boolean checkEmpty() {
        if (txtMatKhauHT.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng không để trống mật khẩu hiện tại");
            txtMatKhauHT.requestFocus();
            return false;
        }
        if (txtMatKhauM.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng không để trống mật khẩu mới");
            txtMatKhauM.requestFocus();
            return false;
        }
        if (txtMatKhauM.getText().startsWith(" ")) {
            JOptionPane.showMessageDialog(this, "Mật khẩu mới không được bắt đầu bằng khoảng trắng");
            return false;
        }
        if (txtMatKhauM2.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng không để trống nhập lại mật khẩu");
            txtMatKhauM2.requestFocus();
            return false;
        }
        if (txtMatKhauM2.getText().startsWith(" ")) {
            JOptionPane.showMessageDialog(this, "Nhập lại mật khẩu mới không được bắt đầu bằng khoảng trắng");
            return false;
        }
        return true;
    }

    public boolean checkHopLe(String maTK, String mkHienTai, String matKhauMoi, String matKhauMoi2) {
        if (matKhauMoi.length() < 8) {
            JOptionPane.showMessageDialog(this, "Mật khẩu mới tối thiểu là  8 ký tự");
            return false;
        }
        if (matKhauMoi.length() > 20) {
            JOptionPane.showMessageDialog(this, "Mật khẩu mới tối đa là 20 ký tự");
            return false;
        }
        if (!serviceDMK.checkMaTK(maTK)) {
            JOptionPane.showMessageDialog(this, "Mã tài khoản không tồn tại");
            return false;
        }
        if (!serviceDMK.checkPassword(maTK, mkHienTai)) {
            JOptionPane.showMessageDialog(this, "Mật khẩu hiện tại không chính xác");
            return false;
        }

        if (!matKhauMoi.equals(matKhauMoi2)) {
            JOptionPane.showMessageDialog(this, "Mật khẩu mới vừa nhập không khớp");
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

        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtMaTK = new javax.swing.JTextField();
        btnHuyBo = new javax.swing.JButton();
        txtMatKhauM = new javax.swing.JPasswordField();
        btnCapNhatMK = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtMatKhauHT = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtMatKhauM2 = new javax.swing.JPasswordField();

        setBackground(new java.awt.Color(0, 153, 153));

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 26)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("THAY ĐỔI MẬT KHẨU");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(204, 0, 51));
        jLabel4.setText("Nhập lại mật khẩu mới");

        btnHuyBo.setBackground(new java.awt.Color(0, 153, 102));
        btnHuyBo.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        btnHuyBo.setForeground(new java.awt.Color(255, 255, 255));
        btnHuyBo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/close.png"))); // NOI18N
        btnHuyBo.setText("HỦY BỎ");
        btnHuyBo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHuyBoMouseClicked(evt);
            }
        });

        txtMatKhauM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMatKhauMActionPerformed(evt);
            }
        });
        txtMatKhauM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMatKhauMKeyReleased(evt);
            }
        });

        btnCapNhatMK.setBackground(new java.awt.Color(51, 204, 255));
        btnCapNhatMK.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        btnCapNhatMK.setForeground(new java.awt.Color(255, 255, 255));
        btnCapNhatMK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/doimatkhau.png"))); // NOI18N
        btnCapNhatMK.setText("Xác Nhận");
        btnCapNhatMK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCapNhatMKMouseClicked(evt);
            }
        });
        btnCapNhatMK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatMKActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 0, 51));
        jLabel3.setText("Mật khẩu hiện tại");

        txtMatKhauHT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMatKhauHTActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(204, 0, 51));
        jLabel5.setText("Mã tài khoản");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 0, 51));
        jLabel2.setText("Mật khẩu mới");

        txtMatKhauM2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMatKhauM2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(219, 219, 219)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5)
                            .addComponent(txtMaTK, javax.swing.GroupLayout.DEFAULT_SIZE, 729, Short.MAX_VALUE)
                            .addComponent(jLabel3)
                            .addComponent(txtMatKhauHT, javax.swing.GroupLayout.DEFAULT_SIZE, 729, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMatKhauM, javax.swing.GroupLayout.DEFAULT_SIZE, 729, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(129, 129, 129)
                                .addComponent(btnCapNhatMK)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnHuyBo, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(129, 129, 129))
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMatKhauM2)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(337, 337, 337)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(329, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(jLabel1)
                .addGap(30, 30, 30)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMaTK, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMatKhauHT, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMatKhauM, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMatKhauM2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCapNhatMK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnHuyBo))
                .addGap(386, 386, 386))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnHuyBoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHuyBoMouseClicked
        // TODO add your handling code here:
        txtMaTK.setText("");
        txtMatKhauHT.setText("");
        txtMatKhauM.setText("");
        txtMatKhauM2.setText("");
    }//GEN-LAST:event_btnHuyBoMouseClicked

    private void txtMatKhauMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMatKhauMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMatKhauMActionPerformed

    private void btnCapNhatMKMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCapNhatMKMouseClicked
        // TODO add your handling code here:
//        if (checkEmpty()) {
//            String maTK = txtMaTK.getText();
//            String matKhauHT = new String(txtMatKhauHT.getPassword());
//            String matKhauMoi = new String(txtMatKhauM.getPassword());
//            String matKhauMoi2 = new String(txtMatKhauM2.getPassword());
//            if (checkHopLe(maTK, matKhauHT, matKhauMoi, matKhauMoi2)) {
//                if (serviceDMK.updatePass(maTK, matKhauMoi) > 0) {
//                    JOptionPane.showMessageDialog(this, "Đổi mật khẩu thành công");
//                } else {
//                    JOptionPane.showMessageDialog(this, "Đổi mật khẩu thất bại");
//                }
//            }
//        }
//        this.setVisible(true);
        if (checkEmpty()) {
            String maTK = txtMaTK.getText();
            String matKhauHT = new String(txtMatKhauHT.getPassword());
            String matKhauMoi = new String(txtMatKhauM.getPassword());
            String matKhauMoi2 = new String(txtMatKhauM2.getPassword());

            int result = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn đổi mật khẩu?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) { // Nếu người dùng chọn 'Yes'
                if (checkHopLe(maTK, matKhauHT, matKhauMoi, matKhauMoi2)) {
                    if (serviceDMK.updatePass(maTK, matKhauMoi) > 0) {
                        JOptionPane.showMessageDialog(this, "Đổi mật khẩu thành công");
                    } else {
                        JOptionPane.showMessageDialog(this, "Đổi mật khẩu thất bại");
                    }
                }
            }
        }
        this.setVisible(true);
    }//GEN-LAST:event_btnCapNhatMKMouseClicked

    private void btnCapNhatMKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatMKActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCapNhatMKActionPerformed

    private void txtMatKhauHTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMatKhauHTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMatKhauHTActionPerformed

    private void txtMatKhauM2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMatKhauM2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMatKhauM2ActionPerformed

    private void txtMatKhauMKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMatKhauMKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMatKhauMKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhatMK;
    private javax.swing.JButton btnHuyBo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField txtMaTK;
    private javax.swing.JPasswordField txtMatKhauHT;
    private javax.swing.JPasswordField txtMatKhauM;
    private javax.swing.JPasswordField txtMatKhauM2;
    // End of variables declaration//GEN-END:variables
}
