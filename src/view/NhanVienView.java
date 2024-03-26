/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import java.awt.Component;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import model.NhanVien;
import model.TaiKhoan;
import service.servicImp.NhanVienServiceImp;


/**
 *
 * @author Admin
 */
public class NhanVienView extends javax.swing.JPanel {

    DefaultTableModel mol = new DefaultTableModel();
    DefaultTableModel mol1 = new DefaultTableModel();
    DefaultComboBoxModel<NhanVien> cbxNhanVien = new DefaultComboBoxModel<>();
    DefaultComboBoxModel<String> cbxModel = new DefaultComboBoxModel<>();
    NhanVienServiceImp serviceNV = new NhanVienServiceImp();
    String anhNV;
    int index = -1;
    int trang = 1, soTrang, tongBanGhi;
    int trang0 = 1, soTrang0, tongBanGhi0;

    /**
     * Creates new form NhanVienView
     */
    public NhanVienView() {
        initComponents();
        this.setSize(1300, 755);
        loadCboDiaChi(serviceNV.getAll());
        loadCboGioiTinh();
        sort(tblNV0);
        sort(tblNV1);
        loadPage0();
        loadPage();
        setForm(false);
        btnMoiNV.setEnabled(true);
    }

    public void sort(JTable table) {
        table.getTableHeader().addMouseListener(new MouseAdapter() {
            int sortType = 0; //Biến để theo dõi kiểu sắp xếp: 0 là tăng dần, 1 là giảm dần

            @Override
            public void mouseClicked(MouseEvent e) {
                int col = table.columnAtPoint(e.getPoint());
                table.setAutoCreateRowSorter(true);
                TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
                table.setRowSorter(sorter);
                // Kiểm tra kiểu sắp xếp hiện tại và cập nhật ngược lại
                if (sortType == 0) {
                    sorter.setSortKeys(Arrays.asList(new RowSorter.SortKey(col, SortOrder.DESCENDING)));
                    sortType = 1; // Cập nhật kiểu sắp xếp ngược lại
                } else {
                    sorter.setSortKeys(Arrays.asList(new RowSorter.SortKey(col, SortOrder.ASCENDING)));
                    sortType = 0; // Cập nhật kiểu sắp xếp ngược lại
                }
            }
        });
    }

    public void noText(java.awt.event.KeyEvent evt, JTextField x) {
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_DELETE))) {
            String txt = x.getText().replaceAll("[^\\d]", "");
            x.setText(txt);
        }
    }

    public String maTangTuDong(String x) {
        int so = serviceNV.tongBanGhi(-1);
        String maHD = x + String.format("%02d", so);;
        return maHD;
    }

    public void loadPage() {
        tongBanGhi = serviceNV.tongBanGhi(1);
        if (tongBanGhi % 4 == 0) {
            soTrang = tongBanGhi / 4;
        } else {
            soTrang = tongBanGhi / 4 + 1;
        }
        if (trang > soTrang) {
            trang = soTrang;
        }
        lblSoTrang.setText(trang + " of " + soTrang);
        try {
            fillTableNVPage(serviceNV.listPageNV(1, trang), tblNV1);
        } catch (Exception e) {
            return;
        }
    }

    public void loadPage0() {
        tongBanGhi0 = serviceNV.tongBanGhi(0);
        if (tongBanGhi0 % 4 == 0) {
            soTrang0 = tongBanGhi0 / 4;
        } else {
            soTrang0 = tongBanGhi0 / 4 + 1;
        }
        if (trang0 > soTrang0) {
            trang0 = soTrang0;
        }
        lblSoTrang.setText(trang0 + " of " + soTrang0);
        try {
            fillTableNVPage(serviceNV.listPageNV(0, trang0), tblNV0);
        } catch (Exception e) {
            return;
        }
    }

    public void setForm(Boolean x) {
        Component[] cpn = jPanel61.getComponents();
        for (Component cp : cpn) {
            cp.setEnabled(x);
        }
    }

    public Boolean valid() {
        String SDTVali = "^[0-9]{10}$";
        String CCCDVali = "^[0-9]{12}$";
        if (txtMaNV.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Mã NV trống");
            return false;
        }
        if (txtMaTK.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Mã TK trống");
            return false;
        }
        if (txtHoTen.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Họ tên trống");
            return false;
        }
        if (txtDiaChi.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Địa chỉ trống");
            return false;
        }
        if (txtSDT.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Số điện thoại trống");
            return false;
        } else if (!txtSDT.getText().trim().matches(SDTVali)) {
            JOptionPane.showMessageDialog(this, "Định dạng số điện thoại");
            return false;
        }
        if (txtCCCD.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Số căn cước nhân dân trống");
            return false;
        } else if (!txtCCCD.getText().trim().matches(CCCDVali)) {
            JOptionPane.showMessageDialog(this, "Định dạng số căn cước nhân dân");
            return false;
        }
        if (txtNgayVaoLam.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Ngày vào làm trống");
            return false;
        }
        if (!rdoNam.isSelected() && !rdoNu.isSelected()) {
            JOptionPane.showMessageDialog(this, "Chưa chọn giới tính");
            return false;
        }
        if (!rdoDangLamViec.isSelected() && !rdoNghiViec.isSelected()) {
            JOptionPane.showMessageDialog(this, "Chưa chọn trạng thái");
            return false;
        }
//        if (!rdoQL.isSelected() && !rdoNV.isSelected()) {
//            JOptionPane.showMessageDialog(this, "Chưa chọn chức vụ");
//            return false;
//        }
        return true;
    }

    public void detailNhanVien(JTable table, int index, boolean dangLamViec) {
        try {
            txtMaNV.setText(table.getValueAt(index, 0).toString());
            txtMaTK.setText(table.getValueAt(index, 1).toString());
            txtHoTen.setText(table.getValueAt(index, 2).toString());
            if (table.getValueAt(index, 3).toString().equals("Nam")) {
                rdoNam.setSelected(true);
            } else {
                rdoNu.setSelected(true);
            }
            txtDiaChi.setText(table.getValueAt(index, 4).toString());
            txtSDT.setText(table.getValueAt(index, 5).toString());
            txtCCCD.setText(table.getValueAt(index, 6).toString());
            Date ngay = new SimpleDateFormat("yyyy-MM-dd").parse(table.getValueAt(index, 7).toString());
            txtNgayVaoLam.setDate(ngay);
            if (dangLamViec) {
                rdoDangLamViec.setSelected(true);
            } else {
                rdoNghiViec.setSelected(true);
            }
            anhNV = table.getValueAt(index, 8).toString();
            ImageIcon icon = new ImageIcon(anhNV);
            Image image = icon.getImage().getScaledInstance(172, 231, Image.SCALE_SMOOTH);
            lblAnhNV.setIcon(new ImageIcon(image));
//            if (serviceNV.getOne(table.getValueAt(index, 0).toString()).getTaiKhoan().getRole().equals("Admin")) {
//                rdoQL.setSelected(true);
//            } else {
//                rdoNV.setSelected(true);
//            }
            txtUserName.setText(serviceNV.getOne(table.getValueAt(index, 0).toString()).getTaiKhoan().getUserName());
        } catch (Exception e) {
        }
    }

    public void fillTableNV(List<NhanVien> list) {
        mol = (DefaultTableModel) tblNV0.getModel();
        mol1 = (DefaultTableModel) tblNV1.getModel();
        mol.setRowCount(0);
        mol1.setRowCount(0);
        for (NhanVien nv : list) {
            if (!nv.isTrangThai()) {
                mol.addRow(new Object[]{
                    nv.getMaNhanVien(), nv.getTaiKhoan().getMaTaiKhoan(), nv.getHoTen(),
                    nv.isGioiTinh() ? "Nam" : "Nữ", nv.getDiaChi(), nv.getSoDienThoai(),
                    nv.getCCCD(), nv.getNgayVaoLam(), nv.getAnh()
                });
            } else {
                mol1.addRow(new Object[]{
                    nv.getMaNhanVien(), nv.getTaiKhoan().getMaTaiKhoan(), nv.getHoTen(),
                    nv.isGioiTinh() ? "Nam" : "Nữ", nv.getDiaChi(), nv.getSoDienThoai(),
                    nv.getCCCD(), nv.getNgayVaoLam(), nv.getAnh()
                });
            }
        }
    }

    public void fillTableNVPage(List<NhanVien> list, JTable tbl) {
        mol = (DefaultTableModel) tbl.getModel();
        mol.setRowCount(0);
        for (NhanVien nv : list) {
            mol.addRow(new Object[]{
                nv.getMaNhanVien(), nv.getTaiKhoan().getMaTaiKhoan(), nv.getHoTen(),
                nv.isGioiTinh() ? "Nam" : "Nữ", nv.getDiaChi(), nv.getSoDienThoai(),
                nv.getCCCD(), nv.getNgayVaoLam(), nv.getAnh()
            });

        }
    }

    public void loadCboDiaChi(List<NhanVien> list) {
        cbxNhanVien.removeAllElements();
        List<String> addedValues = new ArrayList<>();
        List<NhanVien> tempList = new ArrayList<>();

        for (NhanVien nv : list) {
            if (!addedValues.contains(nv.getDiaChi())) {
                tempList.add(nv);
                addedValues.add(nv.getDiaChi());
            }
        }

        for (NhanVien nv : tempList) {
            cbxNhanVien.addElement(nv);
        }

        cboDiaChi.setModel((ComboBoxModel) cbxNhanVien);
        cboDiaChi.setSelectedIndex(-1);
    }

    public void loadCboGioiTinh() {
        cbxModel.removeAllElements();
        cbxModel.addElement("Nam");
        cbxModel.addElement("Nữ");
        cboGioiTinh.setModel((ComboBoxModel) cbxModel);
        cboGioiTinh.setSelectedIndex(-1);
    }

    public NhanVien getFormNV() {
        String maNV = txtMaNV.getText();
        String maTK = txtMaTK.getText();
        TaiKhoan tk = serviceNV.getTK(maTK);
        String hoTen = txtHoTen.getText();
        Boolean gt = rdoNam.isSelected() ? true : false;
        String diaChi = txtDiaChi.getText();
        String sdt = txtSDT.getText();
        String cccd = txtCCCD.getText();
        Date ngayVaoLam = txtNgayVaoLam.getDate();
        Boolean trangThai = rdoDangLamViec.isSelected() ? true : false;
        String anh = anhNV;
        if (anh == null) {
            anh = "None";
        }
        return new NhanVien(maNV, tk, hoTen, gt, diaChi, sdt, cccd, ngayVaoLam, trangThai, anh);
    }

    public TaiKhoan getFormTK() {
        String maTK = txtMaTK.getText();
        String userName = util.GetUserName.gene(txtHoTen.getText());
        while (serviceNV.timTheoUserName(userName) != null) {
            userName = util.GetUserName.gene(txtHoTen.getText());
        }
        String pass = "123456";
        String role = "Staff";
        
        Boolean trangThai = rdoDangLamViec.isSelected() ? true : false;
        return new TaiKhoan(maTK, userName, pass, role, trangThai);
    }

    public void themNV() {
        TaiKhoan tk = this.getFormTK();
        serviceNV.insertTK(tk);
        NhanVien nv = this.getFormNV();
        if (serviceNV.getOne(nv.getMaNhanVien()) != null) {
            JOptionPane.showMessageDialog(this, "Trùng mã nhân viên");
            return;
        } else {
            if (serviceNV.them(nv) > 0) {
                loadCboDiaChi(serviceNV.getAll());
                loadPage0();
                loadPage();
                JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công");
                int x = rdoDangLamViec.isSelected() == true ? 0 : 1;
                tabs.setSelectedIndex(x);
            } else {
                JOptionPane.showMessageDialog(this, "Thêm nhân viên thất bại");
            }
        }
    }

    public void sua(JTable tbl) {
        String ma = tbl.getValueAt(index, 0).toString();
        System.out.println(ma);
        NhanVien nv = this.getFormNV();
        System.out.println("1");
        if (serviceNV.sua(nv, ma) > 0) {
            loadCboDiaChi(serviceNV.getAll());
            if (nv.isTrangThai() == true) {
                loadPage0();
                loadPage();
            } else {
                loadPage();
                loadPage0();
            }
            int x = rdoDangLamViec.isSelected() == true ? 0 : 1;
            tabs.setSelectedIndex(x);
            JOptionPane.showMessageDialog(this, "Sửa thành công");
        } else {
            JOptionPane.showMessageDialog(this, "Sửa thất bại");
        }
    }

    public String getCboTimKiem() {
        if (cboTimKiemNV.getSelectedIndex() == 0) {
            return "MaNV";
        } else if (cboTimKiemNV.getSelectedIndex() == 1) {
            return "HoTen";
        } else if (cboTimKiemNV.getSelectedIndex() == 2) {
            return "SoDienThoai";
        } else {
            return "CCCD";
        }
    }

    public void moi() {
        txtNgayVaoLam.setDate(null);
        txtMaNV.setText(maTangTuDong("NV"));
        txtMaTK.setText(maTangTuDong("TK"));
        txtHoTen.setText("");
        btgGioiTinhNV.clearSelection();
        txtDiaChi.setText("");
        txtSDT.setText("");
        txtCCCD.setText("");
        rdoDangLamViec.setSelected(true);
        lblAnhNV.setIcon(null);
        setForm(true);
        tblNV1.clearSelection();
        tblNV0.clearSelection();
        buttonGroup1.clearSelection();
        txtMaNV.setEnabled(false);
        txtMaTK.setEnabled(false);
        txtUserName.setText("");
        txtUserName.setEnabled(false);
        anhNV = "None";
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btgGioiTinhNV = new javax.swing.ButtonGroup();
        btgTrangThaiNV = new javax.swing.ButtonGroup();
        buttonGroup1 = new javax.swing.ButtonGroup();
        pnlChiTietNhanVien = new javax.swing.JPanel();
        jPanel61 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        txtMaTK = new javax.swing.JTextField();
        txtHoTen = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        txtDiaChi = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        txtCCCD = new javax.swing.JTextField();
        rdoDangLamViec = new javax.swing.JRadioButton();
        rdoNghiViec = new javax.swing.JRadioButton();
        btnThemNV = new javax.swing.JButton();
        btnSuaNV = new javax.swing.JButton();
        btnMoiNV = new javax.swing.JButton();
        lblAnhNV = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        txtUserName = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        txtNgayVaoLam = new com.toedter.calendar.JDateChooser();
        jPanel8 = new javax.swing.JPanel();
        cboGioiTinh = new javax.swing.JComboBox<>();
        cboDiaChi = new javax.swing.JComboBox<>();
        btnRSDiaChiNV = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        txtTimNV = new javax.swing.JTextField();
        btnRSTimNV = new javax.swing.JButton();
        cboTimKiemNV = new javax.swing.JComboBox<>();
        tabs = new javax.swing.JTabbedPane();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblNV1 = new javax.swing.JTable();
        jPanel14 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblNV0 = new javax.swing.JTable();
        btnPrev = new javax.swing.JButton();
        lblSoTrang = new javax.swing.JLabel();
        btnNext = new javax.swing.JButton();

        jPanel61.setBorder(javax.swing.BorderFactory.createTitledBorder("Thiết lập thông tin"));

        jLabel27.setText("Mã NV");

        jLabel28.setText("Mã TK");

        jLabel29.setText("Họ tên");

        jLabel30.setText("Giới tính");

        jLabel31.setText("Địa chỉ");

        btgGioiTinhNV.add(rdoNam);
        rdoNam.setText("Nam");

        btgGioiTinhNV.add(rdoNu);
        rdoNu.setText("Nữ");

        jLabel32.setText("SĐT");

        jLabel33.setText("CCCD");

        jLabel34.setText("Ngày vào làm");

        jLabel35.setText("Trạng thái");

        jLabel36.setText("Ảnh");

        txtSDT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSDTKeyReleased(evt);
            }
        });

        txtCCCD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCCCDKeyReleased(evt);
            }
        });

        btgTrangThaiNV.add(rdoDangLamViec);
        rdoDangLamViec.setText("Đang làm việc");

        btgTrangThaiNV.add(rdoNghiViec);
        rdoNghiViec.setText("Nghỉ việc");

        btnThemNV.setBackground(new java.awt.Color(0, 153, 102));
        btnThemNV.setForeground(new java.awt.Color(255, 255, 255));
        btnThemNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/them.png"))); // NOI18N
        btnThemNV.setText("Thêm");
        btnThemNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNVActionPerformed(evt);
            }
        });

        btnSuaNV.setBackground(new java.awt.Color(0, 153, 102));
        btnSuaNV.setForeground(new java.awt.Color(255, 255, 255));
        btnSuaNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/sua.png"))); // NOI18N
        btnSuaNV.setText("Sửa");
        btnSuaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaNVActionPerformed(evt);
            }
        });

        btnMoiNV.setBackground(new java.awt.Color(0, 153, 102));
        btnMoiNV.setForeground(new java.awt.Color(255, 255, 255));
        btnMoiNV.setText("Mới");
        btnMoiNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiNVActionPerformed(evt);
            }
        });

        lblAnhNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAnhNVMouseClicked(evt);
            }
        });

        jLabel37.setText("User Name");

        javax.swing.GroupLayout jPanel61Layout = new javax.swing.GroupLayout(jPanel61);
        jPanel61.setLayout(jPanel61Layout);
        jPanel61Layout.setHorizontalGroup(
            jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel61Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel61Layout.createSequentialGroup()
                        .addGroup(jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel61Layout.createSequentialGroup()
                                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(21, 21, 21)
                                .addComponent(txtMaNV))
                            .addGroup(jPanel61Layout.createSequentialGroup()
                                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(21, 21, 21)
                                .addComponent(txtMaTK, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(48, 48, 48)
                        .addGroup(jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel61Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel61Layout.createSequentialGroup()
                                .addGroup(jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel61Layout.createSequentialGroup()
                                        .addComponent(jLabel30)
                                        .addGap(13, 13, 13)))
                                .addGroup(jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel61Layout.createSequentialGroup()
                                        .addGap(9, 9, 9)
                                        .addComponent(rdoNam)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(rdoNu)
                                        .addGap(0, 161, Short.MAX_VALUE))
                                    .addGroup(jPanel61Layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addComponent(txtDiaChi))))
                            .addGroup(jPanel61Layout.createSequentialGroup()
                                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(39, 39, 39)
                        .addGroup(jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel35, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel34, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                            .addComponent(jLabel37))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtSDT, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                    .addComponent(txtCCCD, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                    .addGroup(jPanel61Layout.createSequentialGroup()
                        .addComponent(rdoDangLamViec)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdoNghiViec))
                    .addComponent(txtUserName)
                    .addComponent(txtNgayVaoLam, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel61Layout.createSequentialGroup()
                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(193, 193, 193))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel61Layout.createSequentialGroup()
                        .addComponent(lblAnhNV, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64))))
            .addGroup(jPanel61Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(btnThemNV)
                .addGap(45, 45, 45)
                .addComponent(btnSuaNV)
                .addGap(45, 45, 45)
                .addComponent(btnMoiNV)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel61Layout.setVerticalGroup(
            jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel61Layout.createSequentialGroup()
                .addGroup(jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel61Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel61Layout.createSequentialGroup()
                                .addGroup(jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel27)
                                        .addComponent(jLabel32))
                                    .addGroup(jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel36)))
                                .addGap(12, 12, 12)
                                .addComponent(txtCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel61Layout.createSequentialGroup()
                                .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtMaTK, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel28)
                                    .addComponent(jLabel33))))
                        .addGroup(jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel29)
                                .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel34))
                            .addGroup(jPanel61Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNgayVaoLam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel61Layout.createSequentialGroup()
                                .addGroup(jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel30)
                                    .addComponent(rdoNam)
                                    .addComponent(rdoNu))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel31)
                                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel37)
                                    .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel61Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(rdoDangLamViec)
                                    .addComponent(rdoNghiViec)
                                    .addComponent(jLabel35)))))
                    .addGroup(jPanel61Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(lblAnhNV, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemNV)
                    .addComponent(btnSuaNV)
                    .addComponent(btnMoiNV))
                .addGap(22, 22, 22))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Bộ lọc"));

        cboGioiTinh.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Giới tính"));
        cboGioiTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboGioiTinhActionPerformed(evt);
            }
        });

        cboDiaChi.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Địa chỉ"));
        cboDiaChi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboDiaChiActionPerformed(evt);
            }
        });

        btnRSDiaChiNV.setBackground(new java.awt.Color(0, 153, 102));
        btnRSDiaChiNV.setForeground(new java.awt.Color(255, 255, 255));
        btnRSDiaChiNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/reset.png"))); // NOI18N
        btnRSDiaChiNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRSDiaChiNVActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cboGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                .addComponent(btnRSDiaChiNV, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRSDiaChiNV)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cboDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm kiếm"));

        txtTimNV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimNVKeyReleased(evt);
            }
        });

        btnRSTimNV.setBackground(new java.awt.Color(0, 153, 102));
        btnRSTimNV.setForeground(new java.awt.Color(255, 255, 255));
        btnRSTimNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/reset.png"))); // NOI18N
        btnRSTimNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRSTimNVActionPerformed(evt);
            }
        });

        cboTimKiemNV.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã NV", "Họ tên", "SĐT", "CCCD" }));
        cboTimKiemNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTimKiemNVActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cboTimKiemNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTimNV)
                .addGap(28, 28, 28)
                .addComponent(btnRSTimNV, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboTimKiemNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTimNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnRSTimNV)))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        tabs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabsMouseClicked(evt);
            }
        });

        tblNV1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã NV", "Mã TK", "Họ tên", "Giới tính", "Địa chỉ", "SĐT", "CCCD", "Ngày vào làm", "Ảnh"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNV1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNV1MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblNV1);
        if (tblNV1.getColumnModel().getColumnCount() > 0) {
            tblNV1.getColumnModel().getColumn(0).setResizable(false);
            tblNV1.getColumnModel().getColumn(1).setResizable(false);
            tblNV1.getColumnModel().getColumn(2).setResizable(false);
            tblNV1.getColumnModel().getColumn(3).setResizable(false);
            tblNV1.getColumnModel().getColumn(4).setResizable(false);
            tblNV1.getColumnModel().getColumn(5).setResizable(false);
            tblNV1.getColumnModel().getColumn(6).setResizable(false);
            tblNV1.getColumnModel().getColumn(7).setResizable(false);
            tblNV1.getColumnModel().getColumn(8).setResizable(false);
        }

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1099, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabs.addTab("Đang làm việc", jPanel11);

        tblNV0.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã NV", "Mã TK", "Họ tên", "Giới tính", "Địa chỉ", "SĐT", "CCCD", "Ngày vào làm", "Ảnh"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNV0.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNV0MouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblNV0);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 1099, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabs.addTab("Nghỉ việc", jPanel14);

        btnPrev.setBackground(new java.awt.Color(0, 153, 102));
        btnPrev.setForeground(new java.awt.Color(255, 255, 255));
        btnPrev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/lui.png"))); // NOI18N
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });

        lblSoTrang.setText("Về số trang");

        btnNext.setBackground(new java.awt.Color(0, 153, 102));
        btnNext.setForeground(new java.awt.Color(255, 255, 255));
        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/tien.png"))); // NOI18N
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlChiTietNhanVienLayout = new javax.swing.GroupLayout(pnlChiTietNhanVien);
        pnlChiTietNhanVien.setLayout(pnlChiTietNhanVienLayout);
        pnlChiTietNhanVienLayout.setHorizontalGroup(
            pnlChiTietNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChiTietNhanVienLayout.createSequentialGroup()
                .addGroup(pnlChiTietNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlChiTietNhanVienLayout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel61, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tabs))
                .addContainerGap())
            .addGroup(pnlChiTietNhanVienLayout.createSequentialGroup()
                .addGap(364, 364, 364)
                .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(108, 108, 108)
                .addComponent(lblSoTrang)
                .addGap(102, 102, 102)
                .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlChiTietNhanVienLayout.setVerticalGroup(
            pnlChiTietNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChiTietNhanVienLayout.createSequentialGroup()
                .addComponent(jPanel61, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlChiTietNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlChiTietNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPrev)
                    .addComponent(btnNext)
                    .addGroup(pnlChiTietNhanVienLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(lblSoTrang)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1105, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlChiTietNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 703, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlChiTietNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNVActionPerformed
        if (valid()) {
            themNV();
            moi();
        }
    }//GEN-LAST:event_btnThemNVActionPerformed

    private void btnSuaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaNVActionPerformed
        if (valid()) {
            if (tblNV0.getSelectedRow() == 0 && tblNV1.getSelectedRow() == 0) {
                JOptionPane.showMessageDialog(this, "Bạn đang không chọn dòng nào");
            }
            index = tblNV0.getSelectedRow();
            if (index != -1) {
                sua(tblNV0);
                setForm(true);
                txtMaNV.setEnabled(false);
                btnThemNV.setEnabled(false);
            } else {
                index = tblNV1.getSelectedRow();
                sua(tblNV1);
                setForm(true);
                txtMaNV.setEnabled(false);
                btnThemNV.setEnabled(false);
            }
        }
    }//GEN-LAST:event_btnSuaNVActionPerformed

    private void btnMoiNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiNVActionPerformed
        moi();
    }//GEN-LAST:event_btnMoiNVActionPerformed

    private void lblAnhNVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAnhNVMouseClicked
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                ImageIcon originalIcon = new ImageIcon(selectedFile.getAbsolutePath());
                Image originalImage = originalIcon.getImage();
                Image resizedImage = originalImage.getScaledInstance(146, 206, Image.SCALE_SMOOTH);
                ImageIcon resizedIcon = new ImageIcon(resizedImage);
                lblAnhNV.setIcon(resizedIcon);
                anhNV = selectedFile.getAbsolutePath();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_lblAnhNVMouseClicked

    private void cboGioiTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboGioiTinhActionPerformed
        String mot;
        String hai = null;
        if (cboGioiTinh.getSelectedIndex() == -1) {
            loadPage0();
            loadPage();
            return;
        } else {
            mot = cboGioiTinh.getSelectedIndex() == 0 ? "1" : "0";
        }
        if (cboDiaChi.getSelectedIndex() == -1) {
            fillTableNV(serviceNV.getList3("GioiTinh", mot));
        } else {
            hai = cboDiaChi.getSelectedItem().toString();
            fillTableNV(serviceNV.getList2(mot, hai));
        }
    }//GEN-LAST:event_cboGioiTinhActionPerformed

    private void cboDiaChiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboDiaChiActionPerformed
        String mot;
        String hai;
        if (cboDiaChi.getSelectedIndex() != -1) {
            mot = cboDiaChi.getSelectedItem().toString();
            if (cboGioiTinh.getSelectedIndex() == -1) {
                fillTableNV(serviceNV.getList3("DiaChi", mot));
            } else {
                hai = cboGioiTinh.getSelectedIndex() == 1 ? "0" : "1";
                fillTableNV(serviceNV.getList2(hai, mot));
            }
        } else {
            loadPage0();
            loadPage();
        }
    }//GEN-LAST:event_cboDiaChiActionPerformed

    private void btnRSDiaChiNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRSDiaChiNVActionPerformed
        //        fillTableNV(serviceNV.getAll());
        if (cboDiaChi.getSelectedIndex() == -1 && cboGioiTinh.getSelectedIndex() == -1) {
            return;
        }
        cboDiaChi.setSelectedIndex(-1);
        cboGioiTinh.setSelectedIndex(-1);
    }//GEN-LAST:event_btnRSDiaChiNVActionPerformed

    private void txtTimNVKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimNVKeyReleased
        if (!txtTimNV.getText().equals("")) {
            cboDiaChi.setSelectedIndex(-1);
            cboGioiTinh.setSelectedIndex(-1);
            String hai = txtTimNV.getText();
            String mot = getCboTimKiem();
            fillTableNV(serviceNV.getList3(mot, hai));
        } else {
            loadPage0();
            loadPage();
        }
    }//GEN-LAST:event_txtTimNVKeyReleased

    private void btnRSTimNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRSTimNVActionPerformed
        cboTimKiemNV.setSelectedIndex(0);
        txtTimNV.setText("");
        loadPage0();
        loadPage();
    }//GEN-LAST:event_btnRSTimNVActionPerformed

    private void cboTimKiemNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTimKiemNVActionPerformed
        txtTimNV.setText("");
        loadPage0();
        loadPage();
    }//GEN-LAST:event_cboTimKiemNVActionPerformed

    private void tblNV1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNV1MouseClicked
        index = tblNV1.getSelectedRow();
        detailNhanVien(tblNV1, index, true);
        setForm(true);
        btnThemNV.setEnabled(false);
        txtMaNV.setEnabled(false);
        txtMaTK.setEnabled(false);
        txtUserName.setEnabled(false);
        tblNV0.clearSelection();
    }//GEN-LAST:event_tblNV1MouseClicked

    private void tblNV0MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNV0MouseClicked
        index = tblNV0.getSelectedRow();
        detailNhanVien(tblNV0, index, false);
        setForm(false);
        btnSuaNV.setEnabled(true);
        btnMoiNV.setEnabled(true);
        rdoDangLamViec.setEnabled(true);
        rdoNghiViec.setEnabled(true);
        tblNV1.clearSelection();
    }//GEN-LAST:event_tblNV0MouseClicked

    private void tabsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabsMouseClicked
        if (tabs.getSelectedIndex() == 1) {
            loadPage0();
        }
        if (tabs.getSelectedIndex() == 0) {
            loadPage();
        }
    }//GEN-LAST:event_tabsMouseClicked

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        if (tabs.getSelectedIndex() == 1) {
            index = 0;
            if (trang0 > 1) {
                trang0--;
                fillTableNV(serviceNV.listPageNV(index, trang0));
                lblSoTrang.setText(trang0 + " of " + soTrang0);
            }
        } else {
            index = 1;
            if (trang > 1) {
                trang--;
                fillTableNV(serviceNV.listPageNV(index, trang));
                lblSoTrang.setText(trang + " of " + soTrang);
            }
        }
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        if (tabs.getSelectedIndex() == 1) {
            index = 0;
            if (trang0 < soTrang0) {
                trang0++;
                fillTableNV(serviceNV.listPageNV(index, trang0));
                lblSoTrang.setText(trang0 + " of " + soTrang0);
            }
        } else {
            index = 1;
            if (trang < soTrang) {
                trang++;
                fillTableNV(serviceNV.listPageNV(index, trang));
                lblSoTrang.setText(trang + " of " + soTrang);
            }
        }
    }//GEN-LAST:event_btnNextActionPerformed

    private void txtSDTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSDTKeyReleased
        noText(evt, txtSDT);
    }//GEN-LAST:event_txtSDTKeyReleased

    private void txtCCCDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCCCDKeyReleased
        noText(evt, txtCCCD);
    }//GEN-LAST:event_txtCCCDKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btgGioiTinhNV;
    private javax.swing.ButtonGroup btgTrangThaiNV;
    private javax.swing.JButton btnMoiNV;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnRSDiaChiNV;
    private javax.swing.JButton btnRSTimNV;
    private javax.swing.JButton btnSuaNV;
    private javax.swing.JButton btnThemNV;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboDiaChi;
    private javax.swing.JComboBox<String> cboGioiTinh;
    private javax.swing.JComboBox<String> cboTimKiemNV;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel61;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lblAnhNV;
    private javax.swing.JLabel lblSoTrang;
    private javax.swing.JPanel pnlChiTietNhanVien;
    private javax.swing.JRadioButton rdoDangLamViec;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNghiViec;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tblNV0;
    private javax.swing.JTable tblNV1;
    private javax.swing.JTextField txtCCCD;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtMaTK;
    private com.toedter.calendar.JDateChooser txtNgayVaoLam;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTimNV;
    private javax.swing.JTextField txtUserName;
    // End of variables declaration//GEN-END:variables
}
