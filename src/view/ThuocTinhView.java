/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.ChatLieu;
import model.KichThuoc;
import model.MauSac;
import service.servicImp.ChatLieuServiceImp;
import service.servicImp.KichThuocServiceImp;
import service.servicImp.MauSacServiceImp;

/**
 *
 * @author Admin
 */
public class ThuocTinhView extends javax.swing.JPanel {

    DefaultTableModel mol = new DefaultTableModel();
    MauSacServiceImp serviceMS = new MauSacServiceImp();
    ChatLieuServiceImp serviceCl = new ChatLieuServiceImp();
    KichThuocServiceImp serviceKT = new KichThuocServiceImp();
    DefaultComboBoxModel<ChatLieu> cbxChatLieu = new DefaultComboBoxModel<>();
    DefaultComboBoxModel<MauSac> cbxMauSac = new DefaultComboBoxModel<>();
    DefaultComboBoxModel<KichThuoc> cbxKichThuoc = new DefaultComboBoxModel<>();
    int index = -1;
    int trangMS = 1, soTrangMS, tongBanGhiMS;
    int trangCL = 1, soTrangCL, tongBanGhiCL;
    int trangKT = 1, soTrangKT, tongBanGhiKT;

    /**
     * Creates new form ThuocTinhView
     */
    public ThuocTinhView() {
        initComponents();
        this.setSize(1300, 755);
        loadPageCL();
        loadPageMS();
        loadPageKT();
        rdConhang2.setSelected(true);
        cboCL.setSelectedIndex(-1);
        cboMS.setSelectedIndex(-1);
        cboKT.setSelectedIndex(-1);
    }

    public ChatLieu savesCL() {
        String maCL, tenCL;
        boolean trangThai;
        maCL = txtTenMa.getText();
        tenCL = txtTenThuocTinh.getText();
        if (rdConhang2.isSelected()) {
            trangThai = true;
        } else {
            trangThai = false;
        }
        return new ChatLieu(maCL, tenCL, trangThai);
    }

    public void loadCboChatLieu(List<ChatLieu> list) {
        cbxChatLieu.removeAllElements();
        for (ChatLieu chatLieu : list) {
            cbxChatLieu.addElement(chatLieu);
        }
        cboCL.setModel((ComboBoxModel) cbxChatLieu);
    }

    public void loadCboMauSac(List<MauSac> list) {
        cbxMauSac.removeAllElements();
        for (MauSac mauSac : list) {
            cbxMauSac.addElement(mauSac);
        }
        cboMS.setModel((ComboBoxModel) cbxMauSac);
    }

    public void loadCboxKichThuoc(List<KichThuoc> list) {
        cbxKichThuoc.removeAllElements();
        for (KichThuoc kichThuoc : list) {
            cbxKichThuoc.addElement(kichThuoc);
        }
        cboKT.setModel((ComboBoxModel) cbxKichThuoc);
    }

    public MauSac savesMS() {
        String maMS, tenMS;
        boolean trangThai;
        maMS = txtTenMa.getText();
        tenMS = txtTenThuocTinh.getText();
        if (rdConhang2.isSelected()) {
            trangThai = true;
        } else {
            trangThai = false;
        }
        return new MauSac(maMS, tenMS, trangThai);
    }

    public KichThuoc savesKT() {
        String maKT, tenKT;
        boolean trangThai;
        maKT = txtTenMa.getText();
        tenKT = txtTenThuocTinh.getText();
        if (rdConhang2.isSelected()) {
            trangThai = true;
        } else {
            trangThai = false;
        }
        return new KichThuoc(maKT, tenKT, trangThai);
    }

    public void fillChatLieu(List<ChatLieu> list) {
        mol = (DefaultTableModel) tblChatLieu.getModel();
        mol.setRowCount(0);
        for (ChatLieu chatLieu : list) {
            mol.addRow(new Object[]{
                chatLieu.getMaChatLieu(), chatLieu.getTenChatLieu(), chatLieu.isTrangThai() ? "Còn hàng" : "Hết hàng"
            });
        }
    }

    public void fillMauSac(List<MauSac> list) {
        mol = (DefaultTableModel) tblMauSac.getModel();
        mol.setRowCount(0);
        for (MauSac mauSac : list) {
            mol.addRow(new Object[]{
                mauSac.getMaMauSac(), mauSac.getTenMauSac(), mauSac.isTrangThai() ? "Còn hàng" : "Hết hàng"
            });
        }
    }

    public void fillKichThuoc(List<KichThuoc> list) {
        mol = (DefaultTableModel) tblKichThuoc.getModel();
        mol.setRowCount(0);
        for (KichThuoc kichThuoc : list) {
            mol.addRow(new Object[]{
                kichThuoc.getMaKichThuoc(), kichThuoc.getTenKichThuoc(), kichThuoc.isTrangThai() ? "Còn hàng" : "Hết hàng"
            });
        }
    }

    public void detailThuocTinhCL(int index) {
        txtTenMa.setText(tblChatLieu.getValueAt(index, 0).toString());
        txtTenThuocTinh.setText(tblChatLieu.getValueAt(index, 1).toString());
        if (tblChatLieu.getValueAt(index, 2).toString().equals("Còn hàng")) {
            rdConhang2.setSelected(true);
        } else {
            rdHethang2.setSelected(true);
        }
    }

    public void detailThuocTinhMS(int index) {
        txtTenMa.setText(tblMauSac.getValueAt(index, 0).toString());
        txtTenThuocTinh.setText(tblMauSac.getValueAt(index, 1).toString());
        if (tblMauSac.getValueAt(index, 2).toString().equals("Còn hàng")) {
            rdConhang2.setSelected(true);
        } else {
            rdHethang2.setSelected(true);
        }
    }

    public void detailThuocTinhKT(int index) {
        txtTenMa.setText(tblKichThuoc.getValueAt(index, 0).toString());
        txtTenThuocTinh.setText(tblKichThuoc.getValueAt(index, 1).toString());
        if (tblKichThuoc.getValueAt(index, 2).toString().equals("Còn hàng")) {
            rdConhang2.setSelected(true);
        } else {
            rdHethang2.setSelected(true);
        }
    }

    public void loadPageMS() {
        tongBanGhiMS = serviceMS.tongBanGhi();
        if (tongBanGhiMS % 5 == 0) {
            soTrangMS = tongBanGhiMS / 5;
        } else {
            soTrangMS = tongBanGhiMS / 5 + 1;
        }
        lbSoTrang.setText(trangMS + " of " + soTrangMS);
        fillMauSac(serviceMS.listPageMS(trangMS));
    }

    public void loadPageCL() {
        tongBanGhiCL = serviceCl.tongBanGhi();
        if (tongBanGhiCL % 5 == 0) {
            soTrangCL = tongBanGhiCL / 5;
        } else {
            soTrangCL = tongBanGhiCL / 5 + 1;
        }
        lbSoTrang.setText(trangCL + " of " + soTrangCL);
        fillChatLieu(serviceCl.listPageCL(trangCL));
    }

    public void loadPageKT() {
        tongBanGhiKT = serviceKT.tongBanGhi();
        if (tongBanGhiKT % 5 == 0) {
            soTrangKT = tongBanGhiKT / 5;
        } else {
            soTrangKT = tongBanGhiKT / 5 + 1;
        }
        lbSoTrang.setText(trangKT + " of " + soTrangKT);
        fillKichThuoc(serviceKT.listPageKT(trangKT));
    }

    public boolean validateTT() {
        String tenTTVali = "^[\\p{L}\\s]+$";
        if (txtTenMa.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã trống");
            return false;
        }
        if (txtTenThuocTinh.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên trống");
            return false;
        } else if (!txtTenThuocTinh.getText().trim().matches(tenTTVali)) {
            JOptionPane.showMessageDialog(this, " Vui lòng điền đúng định dạng tên thuộc tính");
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtTenMa = new javax.swing.JTextField();
        txtTenThuocTinh = new javax.swing.JTextField();
        rdConhang2 = new javax.swing.JRadioButton();
        rdHethang2 = new javax.swing.JRadioButton();
        btnSuaThuocTinh = new javax.swing.JButton();
        btnThemThuocTinh = new javax.swing.JButton();
        btnClearThuocTinh = new javax.swing.JButton();
        btnLuiTT = new javax.swing.JButton();
        btnTienTT = new javax.swing.JButton();
        lbSoTrang = new javax.swing.JLabel();
        pnlThuocTinh = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblChatLieu = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblMauSac = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblKichThuoc = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        cboMS = new javax.swing.JComboBox<>();
        cboCL = new javax.swing.JComboBox<>();
        cboKT = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnReset = new javax.swing.JButton();
        btnBackSp = new javax.swing.JButton();

        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Quản lý thuộc tính"));

        jLabel1.setText("Mã");

        jLabel2.setText("Tên");

        jLabel3.setText("Trạng thái");

        buttonGroup2.add(rdConhang2);
        rdConhang2.setText("Còn hàng");

        buttonGroup2.add(rdHethang2);
        rdHethang2.setText("Hết hàng");

        btnSuaThuocTinh.setBackground(new java.awt.Color(0, 153, 102));
        btnSuaThuocTinh.setForeground(new java.awt.Color(255, 255, 255));
        btnSuaThuocTinh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/sua.png"))); // NOI18N
        btnSuaThuocTinh.setText("Sửa Thuộc Tính");
        btnSuaThuocTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaThuocTinhActionPerformed(evt);
            }
        });

        btnThemThuocTinh.setBackground(new java.awt.Color(0, 153, 102));
        btnThemThuocTinh.setForeground(new java.awt.Color(255, 255, 255));
        btnThemThuocTinh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/them.png"))); // NOI18N
        btnThemThuocTinh.setText("Thêm Thuộc Tính");
        btnThemThuocTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemThuocTinhActionPerformed(evt);
            }
        });

        btnClearThuocTinh.setForeground(new java.awt.Color(255, 255, 255));
        btnClearThuocTinh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/undo.png"))); // NOI18N
        btnClearThuocTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearThuocTinhActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(txtTenThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(69, 69, 69))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(txtTenMa, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnClearThuocTinh)
                                .addContainerGap())))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(rdConhang2)
                        .addGap(52, 52, 52)
                        .addComponent(rdHethang2)
                        .addGap(126, 126, 126))))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(btnSuaThuocTinh)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThemThuocTinh)
                .addGap(79, 79, 79))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txtTenMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnClearThuocTinh))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtTenThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(rdConhang2)
                    .addComponent(rdHethang2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemThuocTinh)
                    .addComponent(btnSuaThuocTinh))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnLuiTT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/lui.png"))); // NOI18N
        btnLuiTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuiTTActionPerformed(evt);
            }
        });

        btnTienTT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/tien.png"))); // NOI18N
        btnTienTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTienTTActionPerformed(evt);
            }
        });

        lbSoTrang.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbSoTrang.setText("Số trang");

        pnlThuocTinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlThuocTinhMouseClicked(evt);
            }
        });

        tblChatLieu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã", "Tên", "Trạng thái"
            }
        ));
        tblChatLieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblChatLieuMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblChatLieu);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1059, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pnlThuocTinh.addTab("Chất liệu", jPanel2);

        tblMauSac.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã", "Tên", "Trạng thái"
            }
        ));
        tblMauSac.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMauSacMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblMauSac);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1007, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1007, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 326, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        pnlThuocTinh.addTab("Màu Sắc", jPanel3);

        tblKichThuoc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã", "Tên", "Trạng thái"
            }
        ));
        tblKichThuoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKichThuocMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblKichThuoc);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1007, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1001, Short.MAX_VALUE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 326, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        pnlThuocTinh.addTab("Kích Thước", jPanel4);

        cboMS.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboMS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboMSActionPerformed(evt);
            }
        });

        cboCL.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboCL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboCLMouseClicked(evt);
            }
        });
        cboCL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboCLActionPerformed(evt);
            }
        });

        cboKT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboKT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboKTActionPerformed(evt);
            }
        });

        jLabel4.setText("Lọc Chất Liệu");

        jLabel5.setText("Lọc Màu Sắc");

        jLabel6.setText("Lọc Kích Thước");

        btnReset.setForeground(new java.awt.Color(255, 255, 255));
        btnReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/undo.png"))); // NOI18N
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel4)
                        .addComponent(jLabel5))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(cboMS, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboCL, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboKT, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnReset)
                .addGap(7, 7, 7))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboCL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboMS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboKT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        btnBackSp.setBackground(new java.awt.Color(0, 153, 102));
        btnBackSp.setForeground(new java.awt.Color(255, 255, 255));
        btnBackSp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/return.png"))); // NOI18N
        btnBackSp.setText("Quay lại");
        btnBackSp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackSpActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(440, 440, 440)
                        .addComponent(btnLuiTT)
                        .addGap(18, 18, 18)
                        .addComponent(lbSoTrang)
                        .addGap(18, 18, 18)
                        .addComponent(btnTienTT)
                        .addGap(268, 268, 268)
                        .addComponent(btnBackSp)
                        .addGap(53, 53, 53))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(25, 25, 25)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(90, 90, 90)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(36, 36, 36)
                            .addComponent(pnlThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 1007, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(266, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(30, 30, 30))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(pnlThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnLuiTT)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbSoTrang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnTienTT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(btnBackSp))
                .addGap(488, 488, 488))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemThuocTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemThuocTinhActionPerformed
        // TODO add your handling code here:
        int indexs = pnlThuocTinh.getSelectedIndex();
        String tenTT = txtTenThuocTinh.getText().trim();
        if (indexs == 0) {
            ChatLieu cl = savesCL();
            if (validateTT()) {
                if (serviceCl.getOne(cl.getMaChatLieu()) != null) {
                    JOptionPane.showMessageDialog(this, "Mã chất liệu trùng");
                    return;
                }
                if (serviceCl.checkTrungCL(tenTT)) {
                    JOptionPane.showMessageDialog(this, "Chất liệu đã tồn tại, vui lòng kiểm tra lại", "Message", 2);
                    return;
                } else {
                    if (serviceCl.them(cl) > 0) {
                        JOptionPane.showMessageDialog(this, "Thêm chất liệu thành công");
                        loadPageCL();
                    } else {
                        JOptionPane.showMessageDialog(this, "Thêm chất liệu thất bại");
                    }
                }

            }
        } else if (indexs == 1) {
            MauSac ms = savesMS();
            if (validateTT()) {
                if (serviceMS.getOne(ms.getMaMauSac()) != null) {
                    JOptionPane.showMessageDialog(this, "Mã màu sắc trùng");
                    return;
                }
                if (serviceMS.checkTrungMS(tenTT)) {
                    JOptionPane.showMessageDialog(this, "Màu sắc đã tồn tại, vui lòng kiểm tra lại", "Message", 2);
                    return;
                } else {
                    if (serviceMS.them(ms) > 0) {
                        loadPageMS();
                        JOptionPane.showMessageDialog(this, "Thêm màu sắc thành công");
                    } else {
                        JOptionPane.showMessageDialog(this, "Thêm màu sắc thất bại");
                    }
                }

            }
        } else {
            KichThuoc kt = savesKT();
            if (validateTT()) {
                if (serviceKT.getOne(kt.getMaKichThuoc()) != null) {
                    JOptionPane.showMessageDialog(this, "Mã kích thước trùng");
                    return;
                }
                if (serviceKT.checkTrungKT(tenTT)) {
                    JOptionPane.showMessageDialog(this, "Kích thước đã tồn tại, vui lòng kiểm tra lại", "Message", 2);
                    return;
                } else {
                    if (serviceKT.them(kt) > 0) {
                        loadPageKT();
                        JOptionPane.showMessageDialog(this, "Thêm kích thước thành công");
                    } else {
                        JOptionPane.showMessageDialog(this, "Thêm kích thước thất bại");
                    }
                }
            }
        }
    }//GEN-LAST:event_btnThemThuocTinhActionPerformed

    private void btnSuaThuocTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaThuocTinhActionPerformed
        // TODO add your handling code here:
        int indexs = pnlThuocTinh.getSelectedIndex();
        String tenTT = txtTenThuocTinh.getText().trim();
        if (indexs == 0) {
            if (index < 0) {
                JOptionPane.showMessageDialog(this, "Bạn chưa chọn dòng dữ liệu nào");
                return;
            }
            if (serviceCl.checkTrungCL(tenTT)) {
                JOptionPane.showMessageDialog(this, "Chất liệu đã tồn tại, vui lòng kiểm tra lại", "Message", 2);
                return;
            } else {
                ChatLieu cl = savesCL();
                String ma = tblChatLieu.getValueAt(index, 0).toString();
                if (serviceCl.sua(cl, ma) > 0) {
                    loadPageCL();
                    JOptionPane.showMessageDialog(this, "Sửa thuộc tính thành công");
                } else {
                    JOptionPane.showMessageDialog(this, "Sửa thuộc tính thất bại");
                }
            }
        } else if (indexs == 1) {
            if (index < 0) {
                JOptionPane.showMessageDialog(this, "Bạn chưa chọn dòng dữ liệu nào");
                return;
            }
            if (serviceMS.checkTrungMS(tenTT)) {
                JOptionPane.showMessageDialog(this, "Màu sắc đã tồn tại, vui lòng kiểm tra lại", "Message", 2);
                return;
            } else {
                MauSac ms = savesMS();
                String ma = tblMauSac.getValueAt(index, 0).toString();
                if (serviceMS.sua(ms, ma) > 0) {
                    loadPageMS();
                    JOptionPane.showMessageDialog(this, "Sửa thuộc tính thành công");
                } else {
                    JOptionPane.showMessageDialog(this, "Sửa thuộc tính thất bại");
                }
            }
        } else {
            if (index < 0) {
                JOptionPane.showMessageDialog(this, "Bạn chưa chọn dòng dữ liệu nào");
                return;
            }
            if (serviceKT.checkTrungKT(tenTT)) {
                JOptionPane.showMessageDialog(this, "Kích thước đã tồn tại, vui lòng kiểm tra lại", "Message", 2);
                return;
            } else {
                KichThuoc kt = savesKT();
                String ma = tblKichThuoc.getValueAt(index, 0).toString();
                if (serviceKT.sua(kt, ma) > 0) {
                    loadPageKT();
                    JOptionPane.showMessageDialog(this, "Sửa thuộc tính thành công");
                } else {
                    JOptionPane.showMessageDialog(this, "Sửa thuộc tính thất bại");
                }
            }
        }
    }//GEN-LAST:event_btnSuaThuocTinhActionPerformed

    private void btnClearThuocTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearThuocTinhActionPerformed
        // TODO add your handling code here:
        int index = pnlThuocTinh.getSelectedIndex();
        if (index == 0) {
            loadPageCL();
        } else if (index == 1) {
            loadPageMS();
        } else {
            loadPageKT();
        }
        txtTenMa.setEnabled(true);
        txtTenMa.setText("");
        txtTenThuocTinh.setText("");
        btnThemThuocTinh.setEnabled(true);
        cboCL.setSelectedIndex(-1);
        cboMS.setSelectedIndex(-1);
        cboKT.setSelectedIndex(-1);
    }//GEN-LAST:event_btnClearThuocTinhActionPerformed

    private void btnLuiTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuiTTActionPerformed
        // TODO add your handling code here:
        int index = pnlThuocTinh.getSelectedIndex();
        if (index == 0) {
            if (trangCL > 1) {
                trangCL--;
                fillChatLieu(serviceCl.listPageCL(trangCL));
                lbSoTrang.setText(trangCL + " of " + soTrangCL);
            }
        } else if (index == 1) {
            if (trangMS > 1) {
                trangMS--;
                fillMauSac(serviceMS.listPageMS(trangMS));
                lbSoTrang.setText(trangMS + " of " + soTrangMS);
            }
        } else {
            if (trangKT > 1) {
                trangKT--;
                fillKichThuoc(serviceKT.listPageKT(trangKT));
                lbSoTrang.setText(trangKT + " of " + soTrangKT);
            }
        }
    }//GEN-LAST:event_btnLuiTTActionPerformed

    private void btnTienTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTienTTActionPerformed
        // TODO add your handling code here:
        int index = pnlThuocTinh.getSelectedIndex();
        if (index == 0) {
            if (trangCL < soTrangCL) {
                trangCL++;
                fillChatLieu(serviceCl.listPageCL(trangCL));
                lbSoTrang.setText(trangCL + " of " + soTrangCL);
            }

        } else if (index == 1) {
            if (trangMS < soTrangMS) {
                trangMS++;
                fillMauSac(serviceMS.listPageMS(trangMS));
                lbSoTrang.setText(trangMS + " of " + soTrangMS);
            }
        } else {
            if (trangKT < soTrangKT) {
                trangKT++;
                fillKichThuoc(serviceKT.listPageKT(trangKT));
                lbSoTrang.setText(trangKT + " of " + soTrangKT);
            }
        }
    }//GEN-LAST:event_btnTienTTActionPerformed

    private void tblChatLieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChatLieuMouseClicked
        // TODO add your handling code here:
        index = tblChatLieu.getSelectedRow();
        txtTenMa.setEnabled(false);
        detailThuocTinhCL(index);
        btnThemThuocTinh.setEnabled(false);
    }//GEN-LAST:event_tblChatLieuMouseClicked

    private void tblMauSacMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMauSacMouseClicked
        // TODO add your handling code here:
        index = tblMauSac.getSelectedRow();
        txtTenMa.setEnabled(false);
        btnThemThuocTinh.setEnabled(false);
        detailThuocTinhMS(index);
    }//GEN-LAST:event_tblMauSacMouseClicked

    private void tblKichThuocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKichThuocMouseClicked
        // TODO add your handling code here:
        index = tblKichThuoc.getSelectedRow();
        txtTenMa.setEnabled(false);
        btnThemThuocTinh.setEnabled(false);
        detailThuocTinhKT(index);
    }//GEN-LAST:event_tblKichThuocMouseClicked

    private void pnlThuocTinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlThuocTinhMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_pnlThuocTinhMouseClicked

    private void cboCLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboCLMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_cboCLMouseClicked

    private void cboCLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboCLActionPerformed
        // TODO add your handling code here:
        try {
            int indexs = pnlThuocTinh.getSelectedIndex();
            if (indexs == 0) {
                ChatLieu cl = (ChatLieu) cbxChatLieu.getSelectedItem();
                String cls = cl.toString();
                fillChatLieu(serviceCl.getList(cls));
            }
        } catch (Exception e) {

        }
    }//GEN-LAST:event_cboCLActionPerformed

    private void cboMSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboMSActionPerformed
        // TODO add your handling code here:
        try {
            int indexs = pnlThuocTinh.getSelectedIndex();
            if (indexs == 1) {
                MauSac ms = (MauSac) cbxMauSac.getSelectedItem();
                String mss = ms.toString();
                fillMauSac(serviceMS.getList(mss));
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_cboMSActionPerformed

    private void cboKTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboKTActionPerformed
        // TODO add your handling code here:
        try {
            int indexs = pnlThuocTinh.getSelectedIndex();
            if (indexs == 2) {
                KichThuoc kt = (KichThuoc) cbxKichThuoc.getSelectedItem();
                String kts = kt.toString();
                fillKichThuoc(serviceKT.getList(kts));
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_cboKTActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        int index = pnlThuocTinh.getSelectedIndex();
        if (index == 0) {
            cboMS.setSelectedIndex(-1);
            cboKT.setSelectedIndex(-1);
            loadCboChatLieu(serviceCl.getAll());
            loadPageCL();
        } else if (index == 1) {
            loadCboMauSac(serviceMS.getAll());
            cboCL.setSelectedIndex(-1);
            cboKT.setSelectedIndex(-1);
            loadPageMS();
        } else {
            loadCboxKichThuoc(serviceKT.getAll());
            cboMS.setSelectedIndex(-1);
            cboCL.setSelectedIndex(-1);
            loadPageKT();
        }
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnBackSpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackSpActionPerformed
        // TODO add your handling code here:
        jPanel1.removeAll();
        jPanel1.add(new SanPhamView());
        jPanel1.repaint();
        jPanel1.revalidate();
    }//GEN-LAST:event_btnBackSpActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBackSp;
    private javax.swing.JButton btnClearThuocTinh;
    private javax.swing.JButton btnLuiTT;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSuaThuocTinh;
    private javax.swing.JButton btnThemThuocTinh;
    private javax.swing.JButton btnTienTT;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cboCL;
    private javax.swing.JComboBox<String> cboKT;
    private javax.swing.JComboBox<String> cboMS;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lbSoTrang;
    private javax.swing.JTabbedPane pnlThuocTinh;
    private javax.swing.JRadioButton rdConhang2;
    private javax.swing.JRadioButton rdHethang2;
    private javax.swing.JTable tblChatLieu;
    private javax.swing.JTable tblKichThuoc;
    private javax.swing.JTable tblMauSac;
    private javax.swing.JTextField txtTenMa;
    private javax.swing.JTextField txtTenThuocTinh;
    // End of variables declaration//GEN-END:variables
}
