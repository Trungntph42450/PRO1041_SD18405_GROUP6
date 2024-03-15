/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import model.Events;
import service.servicImp.KhuyenMaiServiceImp;

/**
 *
 * @author Admin
 */
public class KhuyenMaiView extends javax.swing.JPanel {

    DefaultTableModel defaultTableModel = new DefaultTableModel();
    DefaultComboBoxModel<Events> cboEvents = new DefaultComboBoxModel<>();
    KhuyenMaiServiceImp service = new KhuyenMaiServiceImp();
    int trangSP = 1, soTrangSP, tongBanGhiSP;
    int index;

    /**
     * Creates new form KhuyenMaiView
     */
    public KhuyenMaiView() {
        initComponents();
        this.setSize(1300, 755);
        fillTable(service.getAll());
        rdKhongDK.setSelected(true);
        rdoDangKichHoat.setSelected(true);
        txtDieuKienTT.setVisible(false);
        if (rdKhongDK.isSelected()) {
            txtDieuKienTT.setText(" ");
        }
        lblVnd.setVisible(false);
        service.suaTrangThai();
        loadPageKm();
    }

    public void fillTable(List<Events> listKm) {
        defaultTableModel = (DefaultTableModel) tblKM.getModel();
        defaultTableModel.setRowCount(0);
        for (Events khuyenMai : listKm) {
            defaultTableModel.addRow(new Object[]{
                khuyenMai.getMaEventa(), khuyenMai.getTenEvent(), khuyenMai.isHinhThuc() ? "Giảm theo số tiền" : "Giảm theo %",
                khuyenMai.getMucGiamGia(), khuyenMai.isDieuKienApDung() ? "Tổng tiền hóa đơn lớn hơn hoặc bằng" : "Không yêu cầu điều kiện",
                khuyenMai.getDieuKienTongTien(), khuyenMai.getThoiGianBatDau(), khuyenMai.getThoiGianKetThuc(),
                khuyenMai.isTrangThai() ? "Đang kích hoạt" : "Hết hiệu lực sử dụng", khuyenMai.getMoTa(),});
        }
    }

    public void showChiTiet(int index) {
        try {
            txtMaKM.setText(tblKM.getValueAt(index, 0).toString());
            txtTenKM.setText(tblKM.getValueAt(index, 1).toString());
            cbbHinhThucGG.setSelectedItem(tblKM.getValueAt(index, 2).toString());
            txtMucGiam.setText(tblKM.getValueAt(index, 3).toString());
            if (tblKM.getValueAt(index, 4).toString().equals("Không yêu cầu điều kiện")) {
                rdKhongDK.setSelected(true);
                txtDieuKienTT.setVisible(false);
                lblVnd.setVisible(false);
            } else {
                rdDieuKien.setSelected(true);
                txtDieuKienTT.setVisible(true);
                lblVnd.setVisible(true);
            }
            txtDieuKienTT.setText(tblKM.getValueAt(index, 5).toString());
            Date ngayBatDau = new SimpleDateFormat("yyyy-MM-dd").parse(tblKM.getValueAt(index, 6).toString());
            txtNgayBatDau.setDate(ngayBatDau);
            Date ngayKetThuc = new SimpleDateFormat("yyyy-MM-dd").parse(tblKM.getValueAt(index, 7).toString());
            txtNgayKetThuc.setDate(ngayKetThuc);

            if (tblKM.getValueAt(index, 8).toString().equals("Đang kích hoạt")) {
                rdoDangKichHoat.setSelected(true);
            } else {
                rdoHetHieuLuc.setSelected(true);
            }
            txtMoTa.setText(tblKM.getValueAt(index, 9).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Events readForm() {
        String maKm, tenKm, mucGiam, dieuKienTT, moTa;
        maKm = txtMaKM.getText();
        tenKm = txtTenKM.getText();
        mucGiam = txtMucGiam.getText();
        dieuKienTT = txtDieuKienTT.getText();
        moTa = txtMoTa.getText();
        boolean hinhThucGG, dieuKien, trangThai;
        if (cbbHinhThucGG.getSelectedItem().toString().equals("Giảm theo số tiền")) {
            hinhThucGG = true;
            mucGiam = txtMucGiam.getText() + "VND";
        } else {
            hinhThucGG = false;
            mucGiam = txtMucGiam.getText() + "%";
        }
        if (rdKhongDK.isSelected()) {
            txtDieuKienTT.setText(" ");
            dieuKien = false;
        } else {
            dieuKien = true;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date thoiGianBatDau = txtNgayBatDau.getDate();
        Date thoiGianKetThuc = txtNgayKetThuc.getDate();
        if (rdoDangKichHoat.isSelected()) {
            trangThai = true;
        } else {
            trangThai = false;
        }
        return new Events(maKm, tenKm, hinhThucGG, mucGiam,
                thoiGianBatDau, thoiGianKetThuc, moTa, trangThai, dieuKien, dieuKienTT);
    }

    public boolean validateForm() {
        if (txtMaKM.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chưa điền mã khuyến mãi");
            return false;
        }
        if (txtTenKM.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chưa điền tên khuyến mãi");
            return false;
        }
        if (txtMucGiam.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chưa điền mức giảm khuyến mãi");
            return false;
        }
        try {
            Double mucGiam = Double.parseDouble(txtMucGiam.getText());
            if (mucGiam <= 0) {
                JOptionPane.showMessageDialog(this, "Mức giảm phải là số dương");
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Mức giảm phải là số");
            return false;
        }
        if (rdDieuKien.isSelected()) {
            if (txtDieuKienTT.getText().isBlank()) {
                JOptionPane.showMessageDialog(this, "Chưa điền điều kiện giảm giá");
                return false;
            } else {
                try {
                    Double dieuKienTT = Double.parseDouble(txtDieuKienTT.getText());
                    if (dieuKienTT <= 0) {
                        JOptionPane.showMessageDialog(this, "Điều kiện tổng tiền phải là số dương");
                        return false;
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Điều kiện tổng tiền phải là số");
                    return false;
                }
            }
        }

        if (txtNgayBatDau.getCalendar() == null) {
            JOptionPane.showMessageDialog(this, "Chưa chọn ngày bắt đầu");
            return false;
        }
        if (txtNgayKetThuc.getCalendar() == null) {
            JOptionPane.showMessageDialog(this, "Chưa chọn ngày kết thúc");
            return false;
        }
        if (txtNgayKetThuc.getCalendar().before(txtNgayBatDau.getCalendar())) {
            JOptionPane.showMessageDialog(this, "Ngày kết thúc không hợp lệ");
            return false;
        }
        return true;
    }

    public void loadPageKm() {
        tongBanGhiSP = service.tongBanGhi();
        if (tongBanGhiSP % 4 == 0) {
            soTrangSP = tongBanGhiSP / 4;
        } else {
            soTrangSP = tongBanGhiSP / 4 + 1;
        }
        lbSoTrang.setText(trangSP + " of " + soTrangSP);
        fillTable(service.listPageKm(trangSP));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btgDKApDung = new javax.swing.ButtonGroup();
        btgTrangThai = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblKM = new javax.swing.JTable();
        txtSearchEvent = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        cboSearchTT = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        cbbHinhThucGG = new javax.swing.JComboBox<>();
        txtTenKM = new javax.swing.JTextField();
        txtMucGiam = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtMenhGia = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        rdKhongDK = new javax.swing.JRadioButton();
        rdDieuKien = new javax.swing.JRadioButton();
        txtDieuKienTT = new javax.swing.JTextField();
        txtMaKM = new javax.swing.JTextField();
        lblVnd = new javax.swing.JLabel();
        btnClearEvent = new javax.swing.JButton();
        btnTaoEvent = new javax.swing.JButton();
        btnSuaEvent = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        rdoDangKichHoat = new javax.swing.JRadioButton();
        rdoHetHieuLuc = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMoTa = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        txtNgayBatDau = new com.toedter.calendar.JDateChooser();
        txtNgayKetThuc = new com.toedter.calendar.JDateChooser();
        btnDau1 = new javax.swing.JButton();
        btnLui1 = new javax.swing.JButton();
        lbSoTrang = new javax.swing.JLabel();
        btnTien1 = new javax.swing.JButton();
        btnCuoi1 = new javax.swing.JButton();

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh Sách Khuyến Mãi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 3, 14), new java.awt.Color(0, 51, 255))); // NOI18N

        tblKM.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã", "Tên", "Hình thức", "Mức giảm", "Điều kiện áp dụng", "Tổng hóa đơn", "Ngày bắt đầu", "Ngày kết thúc", "Trạng thái", "Mô tả"
            }
        ));
        tblKM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKMMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblKM);

        txtSearchEvent.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchEventKeyReleased(evt);
            }
        });

        jLabel11.setText("Tìm Kiếm Mã Giảm Giá:");

        jLabel13.setText("Trạng thái: ");

        cboSearchTT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đang kích hoạt", "Hết hiệu lực sử dụng" }));
        cboSearchTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboSearchTTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtSearchEvent, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboSearchTT, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(179, 179, 179))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1033, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(txtSearchEvent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13)
                        .addComponent(cboSearchTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1))
                .addGap(15, 15, 15)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tạo mã khuyến mãi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jLabel3.setText("Chương Trình Khuyến Mãi: ");

        cbbHinhThucGG.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Giảm theo số tiền", "Giảm theo %" }));
        cbbHinhThucGG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbHinhThucGGActionPerformed(evt);
            }
        });

        jLabel4.setText("Hình Thức Giảm Giá:");

        jLabel5.setText("Mức Giảm Giá: ");

        jLabel9.setText("Mã Event: ");

        txtMenhGia.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        txtMenhGia.setText("VND");

        jLabel12.setText("Điều kiện áp dụng");

        btgDKApDung.add(rdKhongDK);
        rdKhongDK.setText("Không yêu cầu điều kiện");
        rdKhongDK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdKhongDKMouseClicked(evt);
            }
        });

        btgDKApDung.add(rdDieuKien);
        rdDieuKien.setText("Tổng tiền hóa đơn lớn hơn hoặc bằng");
        rdDieuKien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdDieuKienMouseClicked(evt);
            }
        });
        rdDieuKien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdDieuKienActionPerformed(evt);
            }
        });

        lblVnd.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        lblVnd.setText("VND");

        btnClearEvent.setText("Clear form");
        btnClearEvent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearEventActionPerformed(evt);
            }
        });

        btnTaoEvent.setText("Tạo Event");
        btnTaoEvent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoEventActionPerformed(evt);
            }
        });

        btnSuaEvent.setText("Update Events");
        btnSuaEvent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaEventActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtMaKM)
                        .addGap(12, 12, 12))
                    .addComponent(jLabel12)
                    .addComponent(rdKhongDK, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtTenKM, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(cbbHinhThucGG, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdDieuKien)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(txtDieuKienTT, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblVnd, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(txtMucGiam, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtMenhGia, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(btnSuaEvent)
                                .addGap(60, 60, 60))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(269, 269, 269)
                        .addComponent(btnClearEvent)
                        .addGap(26, 26, 26)
                        .addComponent(btnTaoEvent, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtMaKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTenKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbbHinhThucGG, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMucGiam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMenhGia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(rdKhongDK)
                            .addComponent(btnTaoEvent))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rdDieuKien)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDieuKienTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblVnd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnClearEvent, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSuaEvent)
                        .addGap(43, 43, 43))))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thời gian", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jLabel6.setText("Thời Gian Bắt Đầu:");

        jLabel7.setText("Thời Gian Kết Thúc:");

        jLabel10.setText("Trạng Thái: ");

        btgTrangThai.add(rdoDangKichHoat);
        rdoDangKichHoat.setText("Đang kích hoạt");

        btgTrangThai.add(rdoHetHieuLuc);
        rdoHetHieuLuc.setText("Hết hiệu lực sử dụng");

        txtMoTa.setColumns(20);
        txtMoTa.setRows(5);
        jScrollPane1.setViewportView(txtMoTa);

        jLabel8.setText("Mô Tả: ");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(rdoDangKichHoat, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel10))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                            .addComponent(rdoHetHieuLuc))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel6)
                        .addComponent(jLabel7)
                        .addComponent(jLabel8))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtNgayKetThuc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                        .addComponent(txtNgayBatDau, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(14, 14, 14)
                .addComponent(txtNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addGap(9, 9, 9)
                .addComponent(txtNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10)
                .addGap(10, 10, 10)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoDangKichHoat)
                    .addComponent(rdoHetHieuLuc))
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        btnDau1.setText("Pre");
        btnDau1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDau1ActionPerformed(evt);
            }
        });

        btnLui1.setText("Lùi");
        btnLui1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLui1ActionPerformed(evt);
            }
        });

        lbSoTrang.setText("Số Trang");

        btnTien1.setText("Tiến");
        btnTien1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTien1ActionPerformed(evt);
            }
        });

        btnCuoi1.setText("Next");
        btnCuoi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCuoi1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnDau1)
                .addGap(18, 18, 18)
                .addComponent(btnLui1)
                .addGap(44, 44, 44)
                .addComponent(lbSoTrang)
                .addGap(42, 42, 42)
                .addComponent(btnTien1)
                .addGap(18, 18, 18)
                .addComponent(btnCuoi1)
                .addGap(307, 307, 307))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDau1)
                    .addComponent(btnLui1)
                    .addComponent(lbSoTrang)
                    .addComponent(btnTien1)
                    .addComponent(btnCuoi1))
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1073, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 633, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblKMMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKMMouseClicked
        int index = tblKM.getSelectedRow();
        showChiTiet(index);
        txtMaKM.setEnabled(false);
    }//GEN-LAST:event_tblKMMouseClicked

    private void txtSearchEventKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchEventKeyReleased
        if (txtSearchEvent.getText().isEmpty()) {
            loadPageKm();
        } else {
            List<Events> searchEvent = service.getList("%" + txtSearchEvent.getText() + "%");
            fillTable(searchEvent);
        }
    }//GEN-LAST:event_txtSearchEventKeyReleased

    private void cboSearchTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSearchTTActionPerformed
        if (cboSearchTT.getSelectedItem().equals("All")) {
            fillTable(service.getAll());
        } else {
            String timTrangThai = cboSearchTT.getSelectedItem().toString();
            boolean trangThai;
            if (timTrangThai.equalsIgnoreCase("Đang kích hoạt")) {
                trangThai = true;
            } else {
                trangThai = false;
            }
            List<Events> searchTheoTrangThai = service.getList2(trangThai);
            fillTable(searchTheoTrangThai);
        }
    }//GEN-LAST:event_cboSearchTTActionPerformed

    private void cbbHinhThucGGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbHinhThucGGActionPerformed
        if (cbbHinhThucGG.getSelectedItem().toString().equalsIgnoreCase("Giảm theo số tiền")) {
            txtMenhGia.setText("VND");
        } else {
            txtMenhGia.setText("%");
        }
    }//GEN-LAST:event_cbbHinhThucGGActionPerformed

    private void rdKhongDKMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdKhongDKMouseClicked
        // TODO add your handling code here:
        txtDieuKienTT.setText("");
        txtDieuKienTT.setVisible(false);
        lblVnd.setVisible(false);
    }//GEN-LAST:event_rdKhongDKMouseClicked

    private void rdDieuKienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdDieuKienMouseClicked
        // TODO add your handling code here:
        txtDieuKienTT.setVisible(true);
        lblVnd.setVisible(true);
    }//GEN-LAST:event_rdDieuKienMouseClicked

    private void rdDieuKienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdDieuKienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdDieuKienActionPerformed

    private void btnClearEventActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearEventActionPerformed
        txtMaKM.setText("");
        txtMoTa.setText("");
        txtMucGiam.setText("");
        txtTenKM.setText("");
        cbbHinhThucGG.setSelectedIndex(0);
        txtDieuKienTT.setText("");
        rdoDangKichHoat.setSelected(true);
        rdoHetHieuLuc.setSelected(false);
        rdKhongDK.setSelected(true);
        txtDieuKienTT.setVisible(false);
        lblVnd.setVisible(false);
        txtMaKM.setEnabled(true);
        txtNgayBatDau.setCalendar(null);
        txtNgayKetThuc.setCalendar(null);
        fillTable(service.getAll());
    }//GEN-LAST:event_btnClearEventActionPerformed

    private void btnTaoEventActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoEventActionPerformed
        // TODO add your handling code here:
        if (validateForm()) {
            Events ev = readForm();
            if (service.getOne(ev.getMaEventa()) != null) {
                JOptionPane.showMessageDialog(this, "Mã Event trùng");
                return;
            } else {
                if (service.them(ev) > 0) {
                    JOptionPane.showMessageDialog(this, "Thêm event thành công");
//                    fillTable(service.getAll());
                    loadPageKm();
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm event không thành công");
                }
            }
        }
    }//GEN-LAST:event_btnTaoEventActionPerformed

    private void btnSuaEventActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaEventActionPerformed
        // TODO add your handling code here:
        index = tblKM.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn dòng dữ liệu nào");
            return;
        } else {
            if (validateForm()) {
                Events ev = readForm();
                String ma = tblKM.getValueAt(index, 0).toString();
                if (service.sua(ev, ma) > 0) {
                    txtMaKM.setEnabled(false);
                    service.suaTrangThai();
//                    fillTable(service.getAll());
                    loadPageKm();
                    JOptionPane.showMessageDialog(this, "Sửa event thành công");
                } else {
                    JOptionPane.showMessageDialog(this, "Sửa event thất bại");
                }
            }
        }
    }//GEN-LAST:event_btnSuaEventActionPerformed

    private void btnDau1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDau1ActionPerformed
        // TODO add your handling code here:
        trangSP = 1;
        fillTable(service.listPageKm(trangSP));
        lbSoTrang.setText(trangSP + " of " + soTrangSP);
    }//GEN-LAST:event_btnDau1ActionPerformed

    private void btnLui1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLui1ActionPerformed
        // TODO add your handling code here:
        if (trangSP > 1) {
            trangSP--;
            fillTable(service.listPageKm(trangSP));
            lbSoTrang.setText(trangSP + " of " + soTrangSP);
        }
    }//GEN-LAST:event_btnLui1ActionPerformed

    private void btnTien1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTien1ActionPerformed
        // TODO add your handling code here:
        if (trangSP < soTrangSP) {
            trangSP++;
            fillTable(service.listPageKm(trangSP));
            lbSoTrang.setText(trangSP + " of " + soTrangSP);
        }
    }//GEN-LAST:event_btnTien1ActionPerformed

    private void btnCuoi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCuoi1ActionPerformed
        // TODO add your handling code here:
        trangSP = soTrangSP;
        fillTable(service.listPageKm(trangSP));
        lbSoTrang.setText(trangSP + " of " + soTrangSP);
    }//GEN-LAST:event_btnCuoi1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btgDKApDung;
    private javax.swing.ButtonGroup btgTrangThai;
    private javax.swing.JButton btnClearEvent;
    private javax.swing.JButton btnCuoi1;
    private javax.swing.JButton btnDau1;
    private javax.swing.JButton btnLui1;
    private javax.swing.JButton btnSuaEvent;
    private javax.swing.JButton btnTaoEvent;
    private javax.swing.JButton btnTien1;
    private javax.swing.JComboBox<String> cbbHinhThucGG;
    private javax.swing.JComboBox<String> cboSearchTT;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbSoTrang;
    private javax.swing.JLabel lblVnd;
    private javax.swing.JRadioButton rdDieuKien;
    private javax.swing.JRadioButton rdKhongDK;
    private javax.swing.JRadioButton rdoDangKichHoat;
    private javax.swing.JRadioButton rdoHetHieuLuc;
    private javax.swing.JTable tblKM;
    private javax.swing.JTextField txtDieuKienTT;
    private javax.swing.JTextField txtMaKM;
    private javax.swing.JLabel txtMenhGia;
    private javax.swing.JTextArea txtMoTa;
    private javax.swing.JTextField txtMucGiam;
    private com.toedter.calendar.JDateChooser txtNgayBatDau;
    private com.toedter.calendar.JDateChooser txtNgayKetThuc;
    private javax.swing.JTextField txtSearchEvent;
    private javax.swing.JTextField txtTenKM;
    // End of variables declaration//GEN-END:variables
}
