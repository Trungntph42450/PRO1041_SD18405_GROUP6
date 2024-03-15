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
        if (txtTenMa.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã trống");
            return false;
        }

        if (txtTenThuocTinh.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên trống");
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
        btnThemThuocTinh = new javax.swing.JButton();
        btnSuaThuocTinh = new javax.swing.JButton();
        btnClearThuocTinh = new javax.swing.JButton();
        btnDauTT = new javax.swing.JButton();
        btnCuoiTT = new javax.swing.JButton();
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
        cboCL = new javax.swing.JComboBox<>();
        cboMS = new javax.swing.JComboBox<>();
        cboKT = new javax.swing.JComboBox<>();
        btnReset = new javax.swing.JButton();

        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Quản lý thuộc tính"));

        jLabel1.setText("Mã");

        jLabel2.setText("Tên");

        jLabel3.setText("Trạng thái");

        buttonGroup2.add(rdConhang2);
        rdConhang2.setText("Còn hàng");

        buttonGroup2.add(rdHethang2);
        rdHethang2.setText("Hết hàng");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(rdConhang2)
                        .addGap(52, 52, 52)
                        .addComponent(rdHethang2))
                    .addComponent(txtTenThuocTinh, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
                    .addComponent(txtTenMa))
                .addContainerGap(173, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtTenMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtTenThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(rdConhang2)
                    .addComponent(rdHethang2))
                .addContainerGap(41, Short.MAX_VALUE))
        );

        btnThemThuocTinh.setText("Thêm");
        btnThemThuocTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemThuocTinhActionPerformed(evt);
            }
        });

        btnSuaThuocTinh.setText("Sửa");
        btnSuaThuocTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaThuocTinhActionPerformed(evt);
            }
        });

        btnClearThuocTinh.setText("Clear");
        btnClearThuocTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearThuocTinhActionPerformed(evt);
            }
        });

        btnDauTT.setText("Đầu ");
        btnDauTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDauTTActionPerformed(evt);
            }
        });

        btnCuoiTT.setText("Cuối");
        btnCuoiTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCuoiTTActionPerformed(evt);
            }
        });

        btnLuiTT.setText("Lùi");
        btnLuiTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuiTTActionPerformed(evt);
            }
        });

        btnTienTT.setText("Tiến");
        btnTienTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTienTTActionPerformed(evt);
            }
        });

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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 911, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 19, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                .addContainerGap())
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
            .addGap(0, 930, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 924, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 250, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
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
            .addGap(0, 930, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 924, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 250, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE))
        );

        pnlThuocTinh.addTab("Kích Thước", jPanel4);

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

        cboMS.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboMS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboMSActionPerformed(evt);
            }
        });

        cboKT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboKT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboKTActionPerformed(evt);
            }
        });

        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(181, 181, 181)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
                                .addComponent(btnThemThuocTinh)
                                .addGap(33, 33, 33)
                                .addComponent(btnSuaThuocTinh)
                                .addGap(53, 53, 53)
                                .addComponent(btnClearThuocTinh)
                                .addGap(187, 187, 187))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cboMS, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboCL, javax.swing.GroupLayout.Alignment.LEADING, 0, 237, Short.MAX_VALUE)
                            .addComponent(cboKT, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(103, 103, 103))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(pnlThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 930, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnDauTT)
                .addGap(18, 18, 18)
                .addComponent(btnLuiTT)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbSoTrang)
                .addGap(22, 22, 22)
                .addComponent(btnTienTT)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCuoiTT)
                .addGap(382, 382, 382))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboCL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(cboMS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cboKT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnThemThuocTinh)
                        .addComponent(btnSuaThuocTinh))
                    .addComponent(btnClearThuocTinh))
                .addGap(38, 38, 38)
                .addComponent(pnlThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDauTT)
                    .addComponent(btnCuoiTT)
                    .addComponent(btnLuiTT)
                    .addComponent(btnTienTT)
                    .addComponent(lbSoTrang))
                .addContainerGap(128, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void btnDauTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDauTTActionPerformed
        // TODO add your handling code here:
        int index = pnlThuocTinh.getSelectedIndex();
        if (index == 0) {
            trangCL = 1;
            fillChatLieu(serviceCl.listPageCL(trangCL));
            lbSoTrang.setText(trangCL + " of " + soTrangCL);
        } else if (index == 1) {
            trangMS = 1;
            fillMauSac(serviceMS.listPageMS(trangMS));
            lbSoTrang.setText(trangMS + " of " + soTrangMS);
        } else {
            trangKT = 1;
            fillKichThuoc(serviceKT.listPageKT(trangKT));
            lbSoTrang.setText(trangKT + " of " + soTrangKT);
        }
    }//GEN-LAST:event_btnDauTTActionPerformed

    private void btnCuoiTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCuoiTTActionPerformed
        // TODO add your handling code here:
        int index = pnlThuocTinh.getSelectedIndex();
        if (index == 0) {
            trangCL = soTrangCL;
            fillChatLieu(serviceCl.listPageCL(trangCL));
            lbSoTrang.setText(trangCL + " of " + soTrangCL);
        } else if (index == 1) {
            trangMS = soTrangMS;
            fillMauSac(serviceMS.listPageMS(trangMS));
            lbSoTrang.setText(trangMS + " of " + soTrangMS);
        } else {
            trangKT = soTrangKT;
            fillKichThuoc(serviceKT.listPageKT(trangKT));
            lbSoTrang.setText(trangKT + " of " + soTrangKT);
        }
    }//GEN-LAST:event_btnCuoiTTActionPerformed

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClearThuocTinh;
    private javax.swing.JButton btnCuoiTT;
    private javax.swing.JButton btnDauTT;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
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
