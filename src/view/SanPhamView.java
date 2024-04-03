/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import java.awt.CardLayout;
import java.awt.Component;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import model.ChatLieu;
import model.ChiTietSanPham;
import model.KichThuoc;
import model.LoaiSanPham;
import model.MauSac;
import model.SanPham;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import service.servicImp.LoaiSanPhamServiceImp;
import service.servicImp.SanPhamServiceImp;
import static view.ChiTietSanPhamView.createStyleForHeader;

/**
 *
 * @author Admin
 */
public class SanPhamView extends javax.swing.JPanel {

    DefaultTableModel mol = new DefaultTableModel();
    LoaiSanPhamServiceImp serviceLSP = new LoaiSanPhamServiceImp();
    SanPhamServiceImp serviceSP = new SanPhamServiceImp();
    DefaultComboBoxModel<SanPham> cbxSanPham = new DefaultComboBoxModel<>();
    DefaultComboBoxModel<LoaiSanPham> cbxLoaiSanPham = new DefaultComboBoxModel<>();
    DefaultComboBoxModel<LoaiSanPham> cbxLoaiSanPhamLoc = new DefaultComboBoxModel<>();
    int index = -1;
    int trangSP = 1, soTrangSP, tongBanGhiSP;
    public static String tenSanPham;
    public static String maSanPham;

    /**
     * Creates new form SanPhamView
     */
    public SanPhamView() {
        initComponents();
        this.setSize(1300, 755);
        loadPageSP();
        loadCbxLoaiSanPham(serviceLSP.getAll());
        loadCboTimLoaiSP(serviceLSP.getAll());
        rdConHang.setSelected(true);
    }

    public String getTenSPs(String mas, String tens) {
        if (mas != null && tens != null) {
            return tenSanPham + "-" + maSanPham;
        } else if (tens != null) {
            return tenSanPham;
        } else {
            return maSanPham;
        }
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void fillTableSanPham(List<SanPham> list) {
        mol = (DefaultTableModel) tblSanPham.getModel();
        mol.setRowCount(0);
        for (SanPham sanPham : list) {
            mol.addRow(new Object[]{
                sanPham.getMaSanPham(), sanPham.getTenSanPham(),
                sanPham.isTrangThai() ? "Còn hàng" : "Hết hàng", sanPham.getXuatXu(),
                sanPham.getLoaiSanPham()
            });
        }
    }

    public SanPham savesSP() {
        String maSP, tenSP, xuatXu;
        boolean trangThai;
        maSP = txtMaSanPham.getText();
        tenSP = txtTenSanPham.getText();
        xuatXu = txtXuatXu.getText();
        if (rdConHang.isSelected()) {
            trangThai = true;
        } else {
            trangThai = false;
        }
        LoaiSanPham lsp = (LoaiSanPham) cbxLoaiSanPham.getSelectedItem();
        return new SanPham(maSP, tenSP, trangThai, lsp, xuatXu);
    }

    public void detailSP(int index) {
        txtMaSanPham.setText(tblSanPham.getValueAt(index, 0).toString());
        txtTenSanPham.setText(tblSanPham.getValueAt(index, 1).toString());
        if (tblSanPham.getValueAt(index, 2).toString().equals("Còn hàng")) {
            rdConHang.setSelected(true);
        } else {
            rdHetHang.setSelected(true);
        }
        txtXuatXu.setText(tblSanPham.getValueAt(index, 3).toString());
        LoaiSanPham lsp = (LoaiSanPham) tblSanPham.getValueAt(index, 4);
        cbxLoaiSanPham.setSelectedItem(lsp);
    }

    public void loadPageSP() {
        tongBanGhiSP = serviceSP.tongBanGhi();
        if (tongBanGhiSP % 5 == 0) {
            soTrangSP = tongBanGhiSP / 5;
        } else {
            soTrangSP = tongBanGhiSP / 5 + 1;
        }
        lbSoTrang.setText(trangSP + " of " + soTrangSP);
        fillTableSanPham(serviceSP.listPageSP(trangSP));
    }

    public void loadCbxLoaiSanPham(List<LoaiSanPham> list) {
        cbxLoaiSanPham.removeAllElements();
        for (LoaiSanPham loaiSanPham : list) {
            cbxLoaiSanPham.addElement(loaiSanPham);
        }
        cboLoaiSanPham.setModel((ComboBoxModel) cbxLoaiSanPham);
    }

    public void loadCboTimLoaiSP(List<LoaiSanPham> list) {
        cbxLoaiSanPhamLoc.removeAllElements();
        for (LoaiSanPham loaiSanPham : list) {
            cbxLoaiSanPhamLoc.addElement(loaiSanPham);
        }
        cboLocLSP.setModel((ComboBoxModel) cbxLoaiSanPhamLoc);
    }

    public boolean validateSP() {
        if (txtMaSanPham.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã trống");
            return false;
        }
        if (txtTenSanPham.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên trống");
            return false;
        }
        if (txtXuatXu.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Xuất xứ trống");
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
        pnlTong = new javax.swing.JPanel();
        pnlSanPham = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txtMaSanPham = new javax.swing.JTextField();
        txtTenSanPham = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtXuatXu = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        cboLoaiSanPham = new javax.swing.JComboBox<>();
        jLabel26 = new javax.swing.JLabel();
        rdConHang = new javax.swing.JRadioButton();
        rdHetHang = new javax.swing.JRadioButton();
        btnClearSP = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        txtTimKiem = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        cboLocLSP = new javax.swing.JComboBox<>();
        btnThemSP = new javax.swing.JButton();
        btnSuaSP = new javax.swing.JButton();
        btnQuayLai = new javax.swing.JButton();
        btnXuatFile = new javax.swing.JButton();
        pnlSP = new javax.swing.JTabbedPane();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        btnLui1 = new javax.swing.JButton();
        lbSoTrang = new javax.swing.JLabel();
        btnTien1 = new javax.swing.JButton();
        btnCTSP = new javax.swing.JButton();
        pnlSPCT = new javax.swing.JPanel();

        pnlTong.setLayout(new java.awt.CardLayout());

        pnlSanPham.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Quản lý sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jLabel22.setText("Mã sản phẩm");

        jLabel23.setText("Tên sản phẩm");

        jLabel24.setText("Xuất xứ");

        jLabel25.setText("Loại Sản Phẩm");

        cboLoaiSanPham.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboLoaiSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLoaiSanPhamActionPerformed(evt);
            }
        });

        jLabel26.setText("Trạng thái sản phẩm");

        buttonGroup1.add(rdConHang);
        rdConHang.setText("Còn hàng");

        buttonGroup1.add(rdHetHang);
        rdHetHang.setText("Hết hàng");

        btnClearSP.setForeground(new java.awt.Color(255, 255, 255));
        btnClearSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/undo.png"))); // NOI18N
        btnClearSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearSPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22)
                    .addComponent(jLabel23))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtMaSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                    .addComponent(txtTenSanPham))
                .addGap(90, 90, 90)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25))
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtXuatXu, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboLoaiSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(118, 118, 118)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(rdConHang, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(rdHetHang, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnClearSP, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel26)
                                .addComponent(txtXuatXu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel24))
                            .addComponent(btnClearSP))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdConHang)
                            .addComponent(rdHetHang)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25)
                            .addComponent(cboLoaiSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23)
                            .addComponent(txtTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Lọc sản phẩm"));

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm kiếm tên sản phẩm"));

        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(56, Short.MAX_VALUE)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Loại sản phẩm"));

        cboLocLSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboLocLSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboLocLSPMouseClicked(evt);
            }
        });
        cboLocLSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLocLSPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(81, Short.MAX_VALUE)
                .addComponent(cboLocLSP, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cboLocLSP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 178, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(86, 86, 86))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 12, Short.MAX_VALUE))
        );

        btnThemSP.setBackground(new java.awt.Color(0, 153, 102));
        btnThemSP.setForeground(new java.awt.Color(255, 255, 255));
        btnThemSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/them.png"))); // NOI18N
        btnThemSP.setText("Thêm");
        btnThemSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSPActionPerformed(evt);
            }
        });

        btnSuaSP.setBackground(new java.awt.Color(0, 153, 102));
        btnSuaSP.setForeground(new java.awt.Color(255, 255, 255));
        btnSuaSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/sua.png"))); // NOI18N
        btnSuaSP.setText("Sửa");
        btnSuaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaSPActionPerformed(evt);
            }
        });

        btnQuayLai.setBackground(new java.awt.Color(0, 153, 102));
        btnQuayLai.setForeground(new java.awt.Color(255, 255, 255));
        btnQuayLai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/return.png"))); // NOI18N
        btnQuayLai.setText("Quay lại");
        btnQuayLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuayLaiActionPerformed(evt);
            }
        });

        btnXuatFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/sheets.png"))); // NOI18N
        btnXuatFile.setText("Xuất file");
        btnXuatFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatFileActionPerformed(evt);
            }
        });

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã SP", "Tên SP", "Trạng thái", "Xuất xứ", "Tên loại SP"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblSanPham);
        if (tblSanPham.getColumnModel().getColumnCount() > 0) {
            tblSanPham.getColumnModel().getColumn(0).setResizable(false);
            tblSanPham.getColumnModel().getColumn(1).setResizable(false);
            tblSanPham.getColumnModel().getColumn(2).setResizable(false);
            tblSanPham.getColumnModel().getColumn(3).setResizable(false);
            tblSanPham.getColumnModel().getColumn(4).setResizable(false);
        }

        btnLui1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/lui.png"))); // NOI18N
        btnLui1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLui1ActionPerformed(evt);
            }
        });

        lbSoTrang.setText("Số Trang");

        btnTien1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/tien.png"))); // NOI18N
        btnTien1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTien1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(439, 439, 439)
                        .addComponent(btnLui1)
                        .addGap(47, 47, 47)
                        .addComponent(lbSoTrang)
                        .addGap(47, 47, 47)
                        .addComponent(btnTien1))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1067, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnTien1)
                        .addGroup(jPanel8Layout.createSequentialGroup()
                            .addComponent(lbSoTrang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(5, 5, 5)))
                    .addComponent(btnLui1))
                .addContainerGap(98, Short.MAX_VALUE))
        );

        pnlSP.addTab("Sản Phẩm", jPanel8);

        btnCTSP.setBackground(new java.awt.Color(0, 153, 102));
        btnCTSP.setForeground(new java.awt.Color(255, 255, 255));
        btnCTSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/xemchitiet.png"))); // NOI18N
        btnCTSP.setText("Chi tiết sản phẩm");
        btnCTSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCTSPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlSanPhamLayout = new javax.swing.GroupLayout(pnlSanPham);
        pnlSanPham.setLayout(pnlSanPhamLayout);
        pnlSanPhamLayout.setHorizontalGroup(
            pnlSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSanPhamLayout.createSequentialGroup()
                .addGroup(pnlSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSanPhamLayout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlSanPhamLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pnlSP, javax.swing.GroupLayout.PREFERRED_SIZE, 1098, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(68, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSanPhamLayout.createSequentialGroup()
                .addGroup(pnlSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlSanPhamLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlSanPhamLayout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addComponent(btnThemSP)
                        .addGap(130, 130, 130)
                        .addComponent(btnSuaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(121, 121, 121)
                        .addComponent(btnCTSP)
                        .addGap(135, 135, 135)
                        .addComponent(btnQuayLai)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnXuatFile)))
                .addGap(82, 82, 82))
        );
        pnlSanPhamLayout.setVerticalGroup(
            pnlSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSanPhamLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(pnlSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemSP)
                    .addComponent(btnSuaSP)
                    .addComponent(btnQuayLai)
                    .addComponent(btnCTSP)
                    .addComponent(btnXuatFile, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addComponent(pnlSP, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(116, Short.MAX_VALUE))
        );

        pnlTong.add(pnlSanPham, "pnlSanPham");

        javax.swing.GroupLayout pnlSPCTLayout = new javax.swing.GroupLayout(pnlSPCT);
        pnlSPCT.setLayout(pnlSPCTLayout);
        pnlSPCTLayout.setHorizontalGroup(
            pnlSPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1182, Short.MAX_VALUE)
        );
        pnlSPCTLayout.setVerticalGroup(
            pnlSPCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 869, Short.MAX_VALUE)
        );

        pnlTong.add(pnlSPCT, "pnlSPCT");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlTong, javax.swing.GroupLayout.PREFERRED_SIZE, 1182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 31, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(pnlTong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cboLoaiSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLoaiSanPhamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboLoaiSanPhamActionPerformed

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        // TODO add your handling code here:
        if (!txtTimKiem.getText().equals("")) {
            String name = txtTimKiem.getText();
            fillTableSanPham(serviceSP.getList(name));
        } else {
            loadPageSP();
        }
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void cboLocLSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboLocLSPMouseClicked
        // TODO add your handling code here:

//

    }//GEN-LAST:event_cboLocLSPMouseClicked

    private void cboLocLSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLocLSPActionPerformed
        // TODO add your handling code here:
        LoaiSanPham lsp = (LoaiSanPham) cbxLoaiSanPhamLoc.getSelectedItem();
        lbSoTrang.setText(trangSP + " of " + soTrangSP);
        String name = lsp.toString();
        loadPageSP();
        fillTableSanPham(serviceSP.getList(name));

    }//GEN-LAST:event_cboLocLSPActionPerformed

    private void btnThemSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSPActionPerformed
        // TODO add your handling code here:
        SanPham sp = savesSP();
        if (validateSP()) {
            if (serviceSP.getOne(sp.getMaSanPham()) != null) {
                JOptionPane.showMessageDialog(this, "Mã sản phẩm trùng");
                return;
            } else if (txtMaSanPham.getText().trim().isEmpty() || txtTenSanPham.getText().trim().isEmpty() || txtXuatXu.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "KHông được nhấp khoảng trống");
                return;
            } else {
                if (serviceSP.them(sp) > 0) {
                    loadPageSP();
                    JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công");
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm sản phẩm thất bại");
                }
            }
        }
    }//GEN-LAST:event_btnThemSPActionPerformed

    private void btnSuaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaSPActionPerformed
        // TODO add your handling code here:
        index = tblSanPham.getSelectedRow();
        if (validateSP()) {
            if (index < 0) {
                JOptionPane.showMessageDialog(this, "Bạn chưa chọn dòng dữ liệu nào");
                return;
            } else if (txtMaSanPham.getText().trim().isEmpty() || txtTenSanPham.getText().trim().isEmpty() || txtXuatXu.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "KHông được nhấp khoảng trống");
                return;
            } else {
                SanPham sp = savesSP();
                String ma = tblSanPham.getValueAt(index, 0).toString();
                if (serviceSP.sua(sp, ma) > 0) {
                    loadPageSP();
                    JOptionPane.showMessageDialog(this, "Sửa sản phẩm thành công");
                } else {
                    JOptionPane.showMessageDialog(this, "Sửa sản phẩm thất bại");
                }
            }
        }
    }//GEN-LAST:event_btnSuaSPActionPerformed

    private void btnQuayLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuayLaiActionPerformed
        // TODO add your handling code here:
        loadPageSP();
    }//GEN-LAST:event_btnQuayLaiActionPerformed

    private void btnClearSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearSPActionPerformed
        // TODO add your handling code here:
        txtMaSanPham.setEnabled(true);
        txtMaSanPham.setText("");
        cboLoaiSanPham.setSelectedIndex(0);
        txtXuatXu.setText("");
        txtTenSanPham.setText("");
        btnThemSP.setEnabled(true);

    }//GEN-LAST:event_btnClearSPActionPerformed

    private void btnCTSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCTSPActionPerformed
        // TODO add your handling code here:
        index = tblSanPham.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn sản phẩm để hiển thị");
        } else {
            maSanPham = tblSanPham.getValueAt(index, 0).toString();
            tenSanPham = tblSanPham.getValueAt(index, 1).toString();
            pnlTong.removeAll();
            pnlTong.add(new ChiTietSanPhamView());
            pnlTong.repaint();
            pnlTong.revalidate();
        }

    }//GEN-LAST:event_btnCTSPActionPerformed

    private void btnXuatFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatFileActionPerformed
        // TODO add your handling code here:
        fillTableSanPham(serviceSP.getAll());
        FileOutputStream excelFOU = null;
        BufferedOutputStream excelBOU = null;
        XSSFWorkbook excelJtableExporter;

        JFileChooser excel = new JFileChooser("D:\\PRO1041_DuAn1\\Excel\\");
        excel.setDialogTitle("Save as");
        FileNameExtensionFilter file = new FileNameExtensionFilter("EXCEL FILE", "xls", "xlsx", "xlsm");
        excel.setFileFilter(file);

        int excelChooser = excel.showSaveDialog(null);
        if (excelChooser == JFileChooser.APPROVE_OPTION) {
            excelJtableExporter = new XSSFWorkbook();
            XSSFSheet excelsheet = excelJtableExporter.createSheet("Hoa Don");
            CellStyle style = createStyleForHeader(excelsheet);
            XSSFRow a = excelsheet.createRow(0);
            XSSFCell cell1 = a.createCell(0);
            cell1.setCellStyle(style);
            cell1.setCellValue("MSP");

            XSSFCell cell2 = a.createCell(1);
            cell2.setCellStyle(style);
            cell2.setCellValue("TÊN SP");

            XSSFCell cell3 = a.createCell(2);
            cell3.setCellStyle(style);
            cell3.setCellValue("Trạng thái");

            XSSFCell cell4 = a.createCell(3);
            cell4.setCellStyle(style);
            cell4.setCellValue("Xuất Xứ");

            XSSFCell cell5 = a.createCell(4);
            cell5.setCellStyle(style);
            cell5.setCellValue("Loại SP");

            for (int i = 0; i < tblSanPham.getRowCount(); i++) {
                try {
                    XSSFRow excelRow = excelsheet.createRow(i + 1);

                    for (int j = 0; j < tblSanPham.getColumnCount(); j++) {
                        XSSFCell excelCell = excelRow.createCell(j);
                        excelCell.setCellValue(tblSanPham.getValueAt(i, j).toString());
                    }

                    excelFOU = new FileOutputStream(excel.getSelectedFile() + ".xlsx");
                    excelBOU = new BufferedOutputStream(excelFOU);
                    try {
                        excelJtableExporter.write(excelBOU);

                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } finally {
                    try {
                        if (excelBOU != null) {
                            excelBOU.close();
                        }
                        if (excelFOU != null) {
                            excelFOU.close();
                        }

                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
            JOptionPane.showMessageDialog(this, "Xuất file  thành công: ");
            loadPageSP();
        }
    }//GEN-LAST:event_btnXuatFileActionPerformed

    private void btnTien1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTien1ActionPerformed
        // TODO add your handling code here:
        if (trangSP < soTrangSP) {
            trangSP++;
            fillTableSanPham(serviceSP.listPageSP(trangSP));
            lbSoTrang.setText(trangSP + " of " + soTrangSP);
        }
    }//GEN-LAST:event_btnTien1ActionPerformed

    private void btnLui1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLui1ActionPerformed
        // TODO add your handling code here:
        if (trangSP > 1) {
            trangSP--;
            fillTableSanPham(serviceSP.listPageSP(trangSP));
            lbSoTrang.setText(trangSP + " of " + soTrangSP);
        }
    }//GEN-LAST:event_btnLui1ActionPerformed

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        // TODO add your handling code here:
        index = tblSanPham.getSelectedRow();
        detailSP(index);
        txtMaSanPham.setEnabled(false);
        btnThemSP.setEnabled(false);
    }//GEN-LAST:event_tblSanPhamMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCTSP;
    private javax.swing.JButton btnClearSP;
    private javax.swing.JButton btnLui1;
    private javax.swing.JButton btnQuayLai;
    private javax.swing.JButton btnSuaSP;
    private javax.swing.JButton btnThemSP;
    private javax.swing.JButton btnTien1;
    private javax.swing.JButton btnXuatFile;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboLoaiSanPham;
    private javax.swing.JComboBox<String> cboLocLSP;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbSoTrang;
    private javax.swing.JTabbedPane pnlSP;
    private javax.swing.JPanel pnlSPCT;
    private javax.swing.JPanel pnlSanPham;
    private javax.swing.JPanel pnlTong;
    private javax.swing.JRadioButton rdConHang;
    private javax.swing.JRadioButton rdHetHang;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtMaSanPham;
    private javax.swing.JTextField txtTenSanPham;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txtXuatXu;
    // End of variables declaration//GEN-END:variables
}
