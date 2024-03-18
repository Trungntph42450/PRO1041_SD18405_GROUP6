/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import java.util.List;
import java.util.Random;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.ChatLieu;
import model.ChiTietSanPham;
import model.DoiHang;
import model.DoiHangChiTiet;
import model.HoaDonChiTiet;
import model.KichThuoc;
import model.MauSac;
import service.servicImp.ChatLieuServiceImp;
import service.servicImp.ChiTietSanPhamServiceImp;
import service.servicImp.DoiHangChiTietServiceImp;
import service.servicImp.HoaDonChiTietServiceImp;
import service.servicImp.KichThuocServiceImp;
import service.servicImp.MauSacServiceImp;

/**
 *
 * @author Admin
 */
public class ChonChiTietSanPhamView extends javax.swing.JPanel {

    DefaultTableModel tblmol = new DefaultTableModel();
    DefaultComboBoxModel<KichThuoc> cbxKichThuocLoc = new DefaultComboBoxModel<>();
    DefaultComboBoxModel<MauSac> cbxMauSacLoc = new DefaultComboBoxModel<>();
    DefaultComboBoxModel<ChatLieu> cbxChatLieuLoc = new DefaultComboBoxModel<>();
    KichThuocServiceImp serviceKT = new KichThuocServiceImp();
    HoaDonChiTietServiceImp serviceHDCT = new HoaDonChiTietServiceImp();
    ChatLieuServiceImp serviceCl = new ChatLieuServiceImp();
    DoiHangChiTietServiceImp serviceDHCT = new DoiHangChiTietServiceImp();
    DoiHangView doiHangView = new DoiHangView();
    HoaDonView hoaDonView = new HoaDonView();
    MauSacServiceImp serviceMS = new MauSacServiceImp();
    ChiTietSanPhamServiceImp serviceCTSP = new ChiTietSanPhamServiceImp();
    static int indexCTSP = -1;
    static String tenSP = null;
    String chatLieu,kichThuoc,mauSac;
    static int soLuongSP = 0;
    static String maHDCT = null;
    static int soLuongDoiHang = 0;
    static String maCTSPCu = null;
    static String maDH = null;
    int trangCTSP = 1, soTrangCTSP, tongBanGhiCTSP, index = 0;
    Random random = new Random();

    /**
     * Creates new form ChonCTSP
     */
    public ChonChiTietSanPhamView() {
        initComponents();
        this.setSize(1300, 755);
        loadPageCTSP();
        loadLocKichThuoc(serviceKT.getAll());
        loadLocMauSac(serviceMS.getAll());
        loadLocChatLieu(serviceCl.getAll());
        tenSP = doiHangView.getTenSP();
        soLuongSP = doiHangView.getSoLuongSP();
        chatLieu = doiHangView.getChatLieu();
        mauSac = doiHangView.getMauSac();
        kichThuoc = doiHangView.getKichThuoc();
        maHDCT = doiHangView.getMaHDCT();
        maCTSPCu = doiHangView.getMaCTSPCu();
        soLuongDoiHang = doiHangView.getSoLuongDoiHang();
        maDH = hoaDonView.getMaDH();
        doiHangView.setIndexHDDH(doiHangView.getIndexHDDH());
        txtChatLieu.setText(chatLieu);
        txtKichThuoc.setText(kichThuoc);
        txtMauSac.setText(mauSac);
        txtSoLuongDoiHang.setText(soLuongDoiHang+"");
    }

    public void quayLaiDoiHang() {
        pnlTong.removeAll();
        pnlTong.add(new DoiHangView());
        pnlTong.repaint();
        pnlTong.revalidate();
    }

    public void fillTableChiTietSanPham(List<ChiTietSanPham> list) {
        tblmol = (DefaultTableModel) tblChiTietSanPham.getModel();
        tblmol.setRowCount(0);
        for (ChiTietSanPham chiTietSanPham : list) {
            tblmol.addRow(new Object[]{
                chiTietSanPham.getMaChiTietSanPham(),
                chiTietSanPham.getSoLuong(), chiTietSanPham.getGia(),
                chiTietSanPham.getSanPham(),
                chiTietSanPham.getChatLieu(), chiTietSanPham.getMauSac(),
                chiTietSanPham.getKichThuoc(), chiTietSanPham.isTrangThai() ? "Còn hàng" : "Hết hàng",});
        }
    }

    public void loadLocMauSac(List<MauSac> list) {
        cbxMauSacLoc.removeAllElements();
        for (MauSac mauSac : list) {
            cbxMauSacLoc.addElement(mauSac);
        }
        cboLocMau.setModel((ComboBoxModel) cbxMauSacLoc);
        cboLocMau.setSelectedIndex(-1);
    }

    public void loadLocChatLieu(List<ChatLieu> list) {
        cbxChatLieuLoc.removeAllElements();
        for (ChatLieu chatLieu : list) {
            cbxChatLieuLoc.addElement(chatLieu);
        }
        cboLocChat.setModel((ComboBoxModel) cbxChatLieuLoc);
        cboLocChat.setSelectedIndex(-1);
    }

    public void loadLocKichThuoc(List<KichThuoc> list) {
        cbxKichThuocLoc.removeAllElements();
        for (KichThuoc kichThuoc : list) {
            cbxKichThuocLoc.addElement(kichThuoc);
        }
        cboLocKich.setModel((ComboBoxModel) cbxKichThuocLoc);
        cboLocKich.setSelectedIndex(-1);
    }

    public void loadPageCTSP() {
        tongBanGhiCTSP = serviceCTSP.tongBanGhi2();
        if (tongBanGhiCTSP % 20 == 0) {
            soTrangCTSP = tongBanGhiCTSP / 20;
        } else {
            soTrangCTSP = tongBanGhiCTSP / 20 + 1;
        }
        lbSoTrang.setText(trangCTSP + " of " + soTrangCTSP);
        fillTableChiTietSanPham(serviceCTSP.getAll());
    }

    public String maTangTuDong(String DHCT) {
        int so = serviceDHCT.countDoiHangChiTiet();
        so++;
        String maTuDong = "";
        String chuHoa = "QWERTYUIOPASDFGHJKLZXCVBNM";
        char[] kyTu = new char[2];
        for (int i = 0; i < 2; i++) {
            kyTu[i] = chuHoa.charAt(random.nextInt(chuHoa.length()));
            maTuDong += kyTu[i];
        }
        String maDHCT = DHCT + String.format("%04d", so) + maTuDong;
        return maDHCT;
    }

    public void mtam2() {
        if (cboLocChat.getSelectedIndex() != -1 && cboLocMau.getSelectedIndex() == -1 && cboLocKich.getSelectedIndex() == -1) {
            ChatLieu cl = (ChatLieu) cbxChatLieuLoc.getSelectedItem();
            String tenTimCL = cl.toString();
            String tenList = "";
            fillTableChiTietSanPham(serviceCTSP.getListLocCL(tenList, tenTimCL));
        } else if (cboLocChat.getSelectedIndex() == -1 && cboLocMau.getSelectedIndex() != -1 && cboLocKich.getSelectedIndex() == -1) {
            MauSac ms = (MauSac) cbxMauSacLoc.getSelectedItem();
            String tenTimMS = ms.toString();
            String tenList = "";
            fillTableChiTietSanPham(serviceCTSP.getListLocMS(tenList, tenTimMS));
        } else if (cboLocChat.getSelectedIndex() == -1 && cboLocMau.getSelectedIndex() == -1 && cboLocKich.getSelectedIndex() != -1) {
            KichThuoc kt = (KichThuoc) cbxKichThuocLoc.getSelectedItem();
            String tenTimKT = kt.toString();
            String tenList = "";
            fillTableChiTietSanPham(serviceCTSP.getListLocKT(tenList, tenTimKT));
        } else if (cboLocChat.getSelectedIndex() != -1 && cboLocMau.getSelectedIndex() != -1 && cboLocKich.getSelectedIndex() == -1) {
            ChatLieu cl = (ChatLieu) cbxChatLieuLoc.getSelectedItem();
            String tenTimCL = cl.toString();
            MauSac ms = (MauSac) cbxMauSacLoc.getSelectedItem();
            String tenTimMS = ms.toString();
            String tenList = "";
            fillTableChiTietSanPham(serviceCTSP.getListLocCLMS(tenList, tenTimCL, tenTimMS));
        } else if (cboLocChat.getSelectedIndex() == -1 && cboLocMau.getSelectedIndex() != -1 && cboLocKich.getSelectedIndex() != -1) {
            MauSac ms = (MauSac) cbxMauSacLoc.getSelectedItem();
            String tenTimMS = ms.toString();
            KichThuoc kt = (KichThuoc) cbxKichThuocLoc.getSelectedItem();
            String tenTimKT = kt.toString();
            System.out.println(tenTimMS + " " + tenTimKT);
            String tenList = "";
            fillTableChiTietSanPham(serviceCTSP.getListLocMSKT(tenList, tenTimMS, tenTimKT));
        } else if (cboLocChat.getSelectedIndex() != -1 && cboLocMau.getSelectedIndex() == -1 && cboLocKich.getSelectedIndex() != -1) {
            ChatLieu cl = (ChatLieu) cbxChatLieuLoc.getSelectedItem();
            String tenTimCL = cl.toString();
            KichThuoc kt = (KichThuoc) cbxKichThuocLoc.getSelectedItem();
            String tenTimKT = kt.toString();
            String tenList = "";
            fillTableChiTietSanPham(serviceCTSP.getListLocCLKT(tenList, tenTimCL, tenTimKT));
        } else if (cboLocChat.getSelectedIndex() != -1 && cboLocMau.getSelectedIndex() != -1 && cboLocKich.getSelectedIndex() != -1) {
            ChatLieu cl = (ChatLieu) cbxChatLieuLoc.getSelectedItem();
            String tenTimCL = cl.toString();
            MauSac ms = (MauSac) cbxMauSacLoc.getSelectedItem();
            String tenTimMS = ms.toString();
            KichThuoc kt = (KichThuoc) cbxKichThuocLoc.getSelectedItem();
            String tenTimKT = kt.toString();
            String tenList = "";
            fillTableChiTietSanPham(serviceCTSP.getListLoc(tenList, tenTimCL, tenTimMS, tenTimKT));
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTong = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnChonCTSP = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnQuayLai = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblChiTietSanPham = new javax.swing.JTable();
        btnLui = new javax.swing.JButton();
        lbSoTrang = new javax.swing.JLabel();
        btnTien = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        cboLocKich = new javax.swing.JComboBox<>();
        cboLocMau = new javax.swing.JComboBox<>();
        btnReset = new javax.swing.JButton();
        cboLocChat = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMoTa = new javax.swing.JTextArea();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtChatLieu = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtKichThuoc = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtMauSac = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtSoLuongDoiHang = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();

        pnlTong.setLayout(new java.awt.CardLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnChonCTSP.setBackground(new java.awt.Color(0, 153, 102));
        btnChonCTSP.setForeground(new java.awt.Color(255, 255, 255));
        btnChonCTSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
        btnChonCTSP.setText("Chọn");
        btnChonCTSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnChonCTSPMouseClicked(evt);
            }
        });
        btnChonCTSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonCTSPActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Danh sách chi tiết sản phẩm");

        btnQuayLai.setBackground(new java.awt.Color(0, 153, 102));
        btnQuayLai.setForeground(new java.awt.Color(255, 255, 255));
        btnQuayLai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/quayLai.png"))); // NOI18N
        btnQuayLai.setText("Quay lại");
        btnQuayLai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnQuayLaiMouseClicked(evt);
            }
        });

        tblChiTietSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã SPCT", "Số lượng tồn", "Giá", "Tên sản phẩm", "Chất liệu", "Màu sắc ", "Kích thước", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblChiTietSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblChiTietSanPhamMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tblChiTietSanPham);

        btnLui.setText("Lùi");
        btnLui.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuiActionPerformed(evt);
            }
        });

        lbSoTrang.setText("Số trang");

        btnTien.setText("Tiến");
        btnTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTienActionPerformed(evt);
            }
        });

        jPanel1.setBackground(javax.swing.UIManager.getDefaults().getColor("CheckBox.icon.pressedBackground"));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        cboLocKich.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboLocKich.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Kích thước", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N
        cboLocKich.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboLocKichMouseClicked(evt);
            }
        });
        cboLocKich.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLocKichActionPerformed(evt);
            }
        });

        cboLocMau.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboLocMau.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Màu sắc", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N
        cboLocMau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboLocMauMouseClicked(evt);
            }
        });
        cboLocMau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLocMauActionPerformed(evt);
            }
        });

        btnReset.setText("Reset");
        btnReset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnResetMouseClicked(evt);
            }
        });

        cboLocChat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboLocChat.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chất liệu", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N
        cboLocChat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboLocChatMouseClicked(evt);
            }
        });
        cboLocChat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLocChatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(cboLocMau, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(cboLocKich, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addComponent(cboLocChat, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addComponent(btnReset)
                .addGap(17, 17, 17))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnReset)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cboLocKich, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                                .addComponent(cboLocChat, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE))
                            .addComponent(cboLocMau, javax.swing.GroupLayout.Alignment.LEADING))
                        .addContainerGap(18, Short.MAX_VALUE))))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Lý do đổi hàng"));

        txtMoTa.setColumns(20);
        txtMoTa.setRows(5);
        jScrollPane1.setViewportView(txtMoTa);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel1.setText("Chất liệu: ");

        txtChatLieu.setEnabled(false);

        jLabel3.setText("Kích thước: ");

        txtKichThuoc.setEnabled(false);

        jLabel4.setText("Màu sắc: ");

        txtMauSac.setEnabled(false);

        jLabel5.setText("Số lượng đổi hàng:");

        txtSoLuongDoiHang.setEnabled(false);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(63, 63, 63)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(28, 28, 28))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtSoLuongDoiHang, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                    .addComponent(txtKichThuoc))
                .addContainerGap(88, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtSoLuongDoiHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnQuayLai, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(73, 73, 73)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(73, 73, 73)
                        .addComponent(btnChonCTSP, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 122, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 1031, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(370, 370, 370)
                        .addComponent(btnLui)
                        .addGap(48, 48, 48)
                        .addComponent(lbSoTrang)
                        .addGap(60, 60, 60)
                        .addComponent(btnTien)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(btnQuayLai, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(btnChonCTSP, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(btnLui))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbSoTrang)
                        .addComponent(btnTien)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlTong.add(jPanel2, "card2");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1106, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 717, Short.MAX_VALUE)
        );

        pnlTong.add(jPanel4, "card3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnChonCTSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnChonCTSPMouseClicked
        // TODO add your handling code here:
        if (indexCTSP != -1) {
            int soLuongTon = Integer.parseInt(tblChiTietSanPham.getValueAt(indexCTSP, 1).toString());
            String trangThai = tblChiTietSanPham.getValueAt(indexCTSP, 7).toString();
            if (soLuongDoiHang > soLuongTon) {
                JOptionPane.showMessageDialog(this, "Số lượng không hợp lệ, vui lòng chọn sản phẩm khác");
                return;
            }
            if (maCTSPCu.equals(tblChiTietSanPham.getValueAt(indexCTSP, 0).toString())) {
                JOptionPane.showMessageDialog(this, "Không thể đổi lại hàng khách đã mua, vui lòng kiểm tra lại");
                return;
            }else if(trangThai.equalsIgnoreCase("Hết Hàng")){
                JOptionPane.showMessageDialog(this, "Sản phẩm đã hết hàng");
                return;
            } else {
                String maCTSP = tblChiTietSanPham.getValueAt(indexCTSP, 0).toString();
                if (serviceDHCT.checkTrung(maDH,maCTSPCu,maCTSP) != null) {
                    serviceDHCT.congSoLuong(maHDCT, soLuongDoiHang);
                    serviceCTSP.tangSoLuong(soLuongDoiHang, maCTSPCu);
                    serviceCTSP.giamSoLuong(soLuongDoiHang, maCTSP);
                    serviceHDCT.capNhatSoLuongDoiHang(soLuongDoiHang, maHDCT);
                    quayLaiDoiHang();
                    return;
                } else {
                    ChiTietSanPham ctsp = new ChiTietSanPham(maCTSP);
                    String moTa = txtMoTa.getText();
                    DoiHang dh = new DoiHang(maDH);
                    HoaDonChiTiet hdct = new HoaDonChiTiet(maHDCT);
                    String maDHCT = maTangTuDong("DHCT");
                    DoiHangChiTiet dhct = new DoiHangChiTiet(maDHCT, dh, hdct, ctsp, soLuongDoiHang, moTa);
                    serviceDHCT.them(dhct);
                    serviceCTSP.tangSoLuong(soLuongDoiHang, maCTSPCu);
                    serviceCTSP.giamSoLuong(soLuongDoiHang, maCTSP);
                    serviceHDCT.capNhatSoLuongDoiHang(soLuongDoiHang, maHDCT);
                    quayLaiDoiHang();
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn sản phẩm để đổi");
            return;
        }
    }//GEN-LAST:event_btnChonCTSPMouseClicked

    private void tblChiTietSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChiTietSanPhamMouseClicked
        // TODO add your handling code here:
        indexCTSP = tblChiTietSanPham.getSelectedRow();
    }//GEN-LAST:event_tblChiTietSanPhamMouseClicked

    private void btnLuiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuiActionPerformed
        // TODO add your handling code here:
        if (trangCTSP > 1) {
            trangCTSP--;
            fillTableChiTietSanPham(serviceCTSP.listPageCTSPDoiHang(trangCTSP, tenSP, chatLieu));
            lbSoTrang.setText(trangCTSP + " of " + soTrangCTSP);
        }
    }//GEN-LAST:event_btnLuiActionPerformed

    private void btnTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTienActionPerformed
        // TODO add your handling code here:
        if (trangCTSP < soTrangCTSP) {
            trangCTSP++;
            fillTableChiTietSanPham(serviceCTSP.listPageCTSPDoiHang(trangCTSP, tenSP, chatLieu));
            lbSoTrang.setText(trangCTSP + " of " + soTrangCTSP);
        }
    }//GEN-LAST:event_btnTienActionPerformed

    private void cboLocKichMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboLocKichMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cboLocKichMouseClicked

    private void cboLocMauMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboLocMauMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cboLocMauMouseClicked

    private void cboLocKichActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLocKichActionPerformed
        // TODO add your handling code here:
        if (cboLocKich.getSelectedIndex() != -1) {
            if (cboLocChat.getSelectedIndex() != -1 && cboLocMau.getSelectedIndex() == -1 && cboLocKich.getSelectedIndex() == -1) {
                mtam2();
            } else if (cboLocChat.getSelectedIndex() == -1 && cboLocMau.getSelectedIndex() != -1 && cboLocKich.getSelectedIndex() == -1) {
                mtam2();
            } else if (cboLocChat.getSelectedIndex() == -1 && cboLocMau.getSelectedIndex() == -1 && cboLocKich.getSelectedIndex() != -1) {
                mtam2();
            } else if (cboLocChat.getSelectedIndex() != -1 && cboLocMau.getSelectedIndex() != -1 && cboLocKich.getSelectedIndex() == -1) {
                mtam2();
            } else if (cboLocChat.getSelectedIndex() == -1 && cboLocMau.getSelectedIndex() != -1 && cboLocKich.getSelectedIndex() != -1) {
                mtam2();
            } else if (cboLocChat.getSelectedIndex() != -1 && cboLocMau.getSelectedIndex() == -1 && cboLocKich.getSelectedIndex() != -1) {
                mtam2();
            } else if (cboLocChat.getSelectedIndex() != -1 && cboLocMau.getSelectedIndex() != -1 && cboLocKich.getSelectedIndex() != -1) {
                mtam2();
            }
        }
    }//GEN-LAST:event_cboLocKichActionPerformed

    private void cboLocMauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLocMauActionPerformed
        // TODO add your handling code here:

        if (cboLocMau.getSelectedIndex() != -1) {
            if (cboLocChat.getSelectedIndex() != -1 && cboLocMau.getSelectedIndex() == -1 && cboLocKich.getSelectedIndex() == -1) {
                mtam2();
            } else if (cboLocChat.getSelectedIndex() == -1 && cboLocMau.getSelectedIndex() != -1 && cboLocKich.getSelectedIndex() == -1) {
                mtam2();
            } else if (cboLocChat.getSelectedIndex() == -1 && cboLocMau.getSelectedIndex() == -1 && cboLocKich.getSelectedIndex() != -1) {
                mtam2();
            } else if (cboLocChat.getSelectedIndex() != -1 && cboLocMau.getSelectedIndex() != -1 && cboLocKich.getSelectedIndex() == -1) {
                mtam2();
            } else if (cboLocChat.getSelectedIndex() == -1 && cboLocMau.getSelectedIndex() != -1 && cboLocKich.getSelectedIndex() != -1) {
                mtam2();
            } else if (cboLocChat.getSelectedIndex() != -1 && cboLocMau.getSelectedIndex() == -1 && cboLocKich.getSelectedIndex() != -1) {
                mtam2();
            } else if (cboLocChat.getSelectedIndex() != -1 && cboLocMau.getSelectedIndex() != -1 && cboLocKich.getSelectedIndex() != -1) {
                mtam2();
            }

        }
    }//GEN-LAST:event_cboLocMauActionPerformed

    private void btnResetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResetMouseClicked
        // TODO add your handling code here:
        cboLocKich.setSelectedIndex(-1);
        cboLocMau.setSelectedIndex(-1);
        cboLocChat.setSelectedIndex(-1);
        loadPageCTSP();
    }//GEN-LAST:event_btnResetMouseClicked

    private void btnChonCTSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonCTSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnChonCTSPActionPerformed

    private void cboLocChatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboLocChatMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cboLocChatMouseClicked

    private void cboLocChatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLocChatActionPerformed
        // TODO add your handling code here:
        if (cboLocChat.getSelectedIndex() != -1) {
            if (cboLocChat.getSelectedIndex() != -1 && cboLocMau.getSelectedIndex() == -1 && cboLocKich.getSelectedIndex() == -1) {
                mtam2();
            } else if (cboLocChat.getSelectedIndex() == -1 && cboLocMau.getSelectedIndex() != -1 && cboLocKich.getSelectedIndex() == -1) {
                mtam2();
            } else if (cboLocChat.getSelectedIndex() == -1 && cboLocMau.getSelectedIndex() == -1 && cboLocKich.getSelectedIndex() != -1) {
                mtam2();
            } else if (cboLocChat.getSelectedIndex() != -1 && cboLocMau.getSelectedIndex() != -1 && cboLocKich.getSelectedIndex() == -1) {
                mtam2();
            } else if (cboLocChat.getSelectedIndex() == -1 && cboLocMau.getSelectedIndex() != -1 && cboLocKich.getSelectedIndex() != -1) {
                mtam2();
            } else if (cboLocChat.getSelectedIndex() != -1 && cboLocMau.getSelectedIndex() == -1 && cboLocKich.getSelectedIndex() != -1) {
                mtam2();
            } else if (cboLocChat.getSelectedIndex() != -1 && cboLocMau.getSelectedIndex() != -1 && cboLocKich.getSelectedIndex() != -1) {
                mtam2();
            }
        }
    }//GEN-LAST:event_cboLocChatActionPerformed

    private void btnQuayLaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnQuayLaiMouseClicked
        // TODO add your handling code here:
        quayLaiDoiHang();
    }//GEN-LAST:event_btnQuayLaiMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChonCTSP;
    private javax.swing.JButton btnLui;
    private javax.swing.JButton btnQuayLai;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnTien;
    private javax.swing.JComboBox<String> cboLocChat;
    private javax.swing.JComboBox<String> cboLocKich;
    private javax.swing.JComboBox<String> cboLocMau;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JLabel lbSoTrang;
    private javax.swing.JPanel pnlTong;
    private javax.swing.JTable tblChiTietSanPham;
    private javax.swing.JTextField txtChatLieu;
    private javax.swing.JTextField txtKichThuoc;
    private javax.swing.JTextField txtMauSac;
    private javax.swing.JTextArea txtMoTa;
    private javax.swing.JTextField txtSoLuongDoiHang;
    // End of variables declaration//GEN-END:variables
}
