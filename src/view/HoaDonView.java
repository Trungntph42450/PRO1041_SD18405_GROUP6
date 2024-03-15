/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.ChiTietSanPham;
import model.DoiHang;
import model.HoaDon;
import model.NhanVien;
import service.servicImp.ChiTietSanPhamServiceImp;
import service.servicImp.DoiHangServiceImp;
import service.servicImp.HoaDonServiceImp;
import static view.SanPhamView.maSanPham;
import static view.SanPhamView.tenSanPham;

/**
 *
 * @author Admin
 */
public class HoaDonView extends javax.swing.JPanel {

    private HoaDonServiceImp serviceHD = new HoaDonServiceImp();
    DoiHangServiceImp serviceDH = new DoiHangServiceImp();
    DangNhapView dangNhapView = new DangNhapView();
    private ChiTietSanPhamServiceImp serviceCTSP = new ChiTietSanPhamServiceImp();
    private DefaultTableModel tblmHoaDon = new DefaultTableModel();
    private DefaultTableModel tblmSanPhamHDChiTiet = new DefaultTableModel();
    int trangHD = 1, soTrangHD, tongBanGhiHD;
    private int index = -1;
    private int indexHoaDon = -1;
    public static String maHD = null;
    public static String maDH = null;
    Random random = new Random();

    /**
     * Creates new form HoaDonView
     */
    public HoaDonView() {
        initComponents();
        this.setSize(1300, 755);
        loadPageSP();
    }

    public void fillTableHoaDon(List<HoaDon> list) {
        tblmHoaDon = (DefaultTableModel) tblHoaDon.getModel();
        tblmHoaDon.setRowCount(0);
        for (HoaDon hoaDon : list) {
            tblmHoaDon.addRow(new Object[]{
                tblHoaDon.getRowCount() + 1, hoaDon.getMaHoaDon(), hoaDon.getNhanVien().getMaNhanVien(),
                hoaDon.getKhachHang().getMaKhachHang(), hoaDon.getNgayTao(), hoaDon.getTongTien(), hoaDon.getTrangThai(),
                hoaDon.getGhiChu()
            });
        }
    }

    public void fillTableSanPhamHDChiTiet(List<ChiTietSanPham> list, String maHoadon) {
        tblmSanPhamHDChiTiet = (DefaultTableModel) tblLoaiSanPham.getModel();
        tblmSanPhamHDChiTiet.setRowCount(0);
        for (ChiTietSanPham ctsp : list) {
            tblmSanPhamHDChiTiet.addRow(new Object[]{tblmSanPhamHDChiTiet.getRowCount() + 1,
                ctsp.getMaChiTietSanPham(), ctsp.getSanPham().getMaSanPham(),
                ctsp.getSanPham().getTenSanPham(),
                maHoadon, ctsp.getSoLuong(), ctsp.getGia(), ctsp.isTrangThai() ? "Thành công" : "Thất bại"
            });
        }
    }
    
    public String phanCach(Double x) {
        NumberFormat fm = NumberFormat.getNumberInstance(Locale.US);
        return fm.format(x);
    }

    public void showData(int index) {
        txtMaHoaDon.setText(tblHoaDon.getValueAt(index, 1).toString());
        txtMaNhanVien.setText(tblHoaDon.getValueAt(index, 2).toString());
        txtMaKhachHang.setText(tblHoaDon.getValueAt(index, 3).toString());
        txtNgayTao.setText(tblHoaDon.getValueAt(index, 4).toString());
        txtTongTien.setText(phanCach(Double.valueOf(tblHoaDon.getValueAt(index, 5).toString())));
        txtTrangThai.setText(tblHoaDon.getValueAt(index, 6).toString());
        txtGhiChu.setText(tblHoaDon.getValueAt(index, 7).toString());
    }

    public void showSanPhamHDChiTiet(int index) {
        String maHoadon = (String) tblmHoaDon.getValueAt(index, 1);
        fillTableSanPhamHDChiTiet(serviceCTSP.getDanhSachSPCT(maHoadon), maHoadon);
    }

    public boolean doiHang(int index) {
        String maHd = tblHoaDon.getValueAt(index, 1).toString();
        if (serviceHD.maHoaDonDuocDoiHang(maHd) == null) {
            JOptionPane.showMessageDialog(this, "Hóa đơn không đủ điều kiện đổi hàng");
            return false;
        }
        return true;
    }

    public void loadPageSP() {
        tongBanGhiHD = serviceHD.tongBanGhi();
        if (tongBanGhiHD % 15 == 0) {
            soTrangHD = tongBanGhiHD / 15;
        } else {
            soTrangHD = tongBanGhiHD / 15 + 1;
        }
        lbSoTrang.setText(trangHD + " of " + soTrangHD);
        fillTableHoaDon(serviceHD.listPageHD(trangHD));
    }

    public String getThongTinSP(String kichThuoc, String mauSac, String chatLieu) {
        if (kichThuoc != null && mauSac != null && chatLieu != null) {
            return chatLieu + "-" + mauSac + "-" + chatLieu;
        } else if (kichThuoc != null) {
            return kichThuoc;
        } else if (mauSac != null) {
            return mauSac;
        } else {
            return chatLieu;
        }
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        HoaDonView.maHD = maHD;
    }

    public String getMaDH() {
        return maDH;
    }

    public void setMaDH(String maDH) {
        HoaDonView.maDH = maDH;
    }

    public String maTangTuDong(String tenMa) {
        int so = serviceDH.countDoiHang();
        so++;
        String maTuDong = "";
        String chuHoa = "QWERTYUIOPASDFGHJKLZXCVBNM";
        char[] kyTu = new char[2];
        for (int i = 0; i < 2; i++) {
            kyTu[i] = chuHoa.charAt(random.nextInt(chuHoa.length()));
            maTuDong += kyTu[i];
        }
        String maHD = tenMa + String.format("%04d", so) + maTuDong;
        return maHD;
    }

    public void chuyenSangDoiHang() {
        panelTong.removeAll();
        panelTong.add(new DoiHangView());
        panelTong.repaint();
        panelTong.revalidate();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelTong = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        lbSoTrang = new javax.swing.JLabel();
        btnDau1 = new javax.swing.JButton();
        btnLui1 = new javax.swing.JButton();
        btnTien1 = new javax.swing.JButton();
        btnCuoi1 = new javax.swing.JButton();
        pnlTong = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtMaNhanVien = new javax.swing.JTextField();
        txtMaHoaDon = new javax.swing.JTextField();
        txtMaKhachHang = new javax.swing.JTextField();
        txtTongTien = new javax.swing.JTextField();
        txtTrangThai = new javax.swing.JTextField();
        txtGhiChu = new javax.swing.JTextField();
        txtNgayTao = new javax.swing.JTextField();
        btnDoiHang = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblLoaiSanPham = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();

        panelTong.setLayout(new java.awt.CardLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách hóa đơn"));

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã hóa đơn", "Mã nhân viên", "Mã khách hàng", "Ngày tạo", "Tổng tiền", "Trạng Thái ", "Ghi chú "
            }
        ));
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHoaDon);

        lbSoTrang.setText("Số trang");

        btnDau1.setText("Đầu");
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

        btnTien1.setText("Tiến");
        btnTien1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTien1ActionPerformed(evt);
            }
        });

        btnCuoi1.setText("Cuối");
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 624, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(btnDau1)
                        .addGap(18, 18, 18)
                        .addComponent(btnLui1)
                        .addGap(44, 44, 44)
                        .addComponent(lbSoTrang)
                        .addGap(44, 44, 44)
                        .addComponent(btnTien1)
                        .addGap(18, 18, 18)
                        .addComponent(btnCuoi1)))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnDau1)
                        .addComponent(btnLui1)
                        .addComponent(btnTien1)
                        .addComponent(btnCuoi1))
                    .addComponent(lbSoTrang))
                .addContainerGap())
        );

        pnlTong.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa đơn", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jLabel1.setText("Mã hóa đơn");

        jLabel2.setText("Mã khách hàng");

        jLabel3.setText("Ngày tạo");

        jLabel4.setText("Mã nhân viên");

        jLabel5.setText("Tổng tiền");

        jLabel6.setText("Trạng thái ");

        jLabel7.setText("Ghi chú");

        txtMaKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaKhachHangActionPerformed(evt);
            }
        });

        btnDoiHang.setText("Đổi hàng");
        btnDoiHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoiHangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlTongLayout = new javax.swing.GroupLayout(pnlTong);
        pnlTong.setLayout(pnlTongLayout);
        pnlTongLayout.setHorizontalGroup(
            pnlTongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTongLayout.createSequentialGroup()
                .addGroup(pnlTongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTongLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(pnlTongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlTongLayout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtMaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlTongLayout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(pnlTongLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(pnlTongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDoiHang, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlTongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtGhiChu, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        pnlTongLayout.setVerticalGroup(
            pnlTongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTongLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(pnlTongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtMaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlTongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlTongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlTongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(12, 12, 12)
                .addGroup(pnlTongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(12, 12, 12)
                .addGroup(pnlTongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(pnlTongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTongLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnDoiHang))
                    .addGroup(pnlTongLayout.createSequentialGroup()
                        .addComponent(txtGhiChu, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 18, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin sản phẩm"));

        tblLoaiSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã chi tiết SP", "Mã sản phẩm ", "Tên sản phẩm", "Mã hóa đơn", "Số lượng", "Giá", "Trạng thái"
            }
        ));
        tblLoaiSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblLoaiSanPhamMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblLoaiSanPham);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1044, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jLabel8.setText("Tìm kiếm:");

        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(35, 35, 35)
                                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(38, 38, 38)
                        .addComponent(pnlTong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(pnlTong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelTong.add(jPanel2, "card2");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1117, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 698, Short.MAX_VALUE)
        );

        panelTong.add(jPanel3, "card3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelTong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelTong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        // TODO add your handling code here:
        index = tblHoaDon.getSelectedRow();
        maHD = tblHoaDon.getValueAt(index, 1).toString();
        this.showData(index);
        this.showSanPhamHDChiTiet(index);
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void txtMaKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaKhachHangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaKhachHangActionPerformed

    private void tblLoaiSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLoaiSanPhamMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblLoaiSanPhamMouseClicked

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        if (!txtTimKiem.getText().equals("")) {
            String maHD = txtTimKiem.getText();
            fillTableHoaDon(serviceHD.getList(maHD));
        } else {
            loadPageSP();
        }
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void btnDoiHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoiHangActionPerformed
        // TODO add your handling code here:
        indexHoaDon = tblHoaDon.getSelectedRow();
        if (indexHoaDon == -1) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn hóa đơn cần đổi hàng");
        } else {
            if (doiHang(indexHoaDon)) {
                serviceHD.chuyenSangDoiHang(maHD);
                maDH = maTangTuDong("DH");
                HoaDon hd = new HoaDon(maHD);
                NhanVien nv = new NhanVien(dangNhapView.getMaNV());
                DoiHang dh = new DoiHang(maDH, hd, nv);
                serviceDH.them(dh);
                panelTong.removeAll();
                panelTong.add(new DoiHangView());
                panelTong.repaint();
                panelTong.revalidate();
            }
        }
    }//GEN-LAST:event_btnDoiHangActionPerformed

    private void btnDau1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDau1ActionPerformed
        // TODO add your handling code here:
        trangHD = 1;
        fillTableHoaDon(serviceHD.listPageHD(trangHD));
        lbSoTrang.setText(trangHD + " of " + soTrangHD);
    }//GEN-LAST:event_btnDau1ActionPerformed

    private void btnLui1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLui1ActionPerformed
        // TODO add your handling code here:
        if (trangHD > 1) {
            trangHD--;
            fillTableHoaDon(serviceHD.listPageHD(trangHD));
            lbSoTrang.setText(trangHD + " of " + soTrangHD);
        }
    }//GEN-LAST:event_btnLui1ActionPerformed

    private void btnTien1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTien1ActionPerformed
        // TODO add your handling code here:
        if (trangHD < soTrangHD) {
            trangHD++;
            fillTableHoaDon(serviceHD.listPageHD(trangHD));
            lbSoTrang.setText(trangHD + " of " + soTrangHD);
        }
    }//GEN-LAST:event_btnTien1ActionPerformed

    private void btnCuoi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCuoi1ActionPerformed
        // TODO add your handling code here:
        trangHD = soTrangHD;
        fillTableHoaDon(serviceHD.listPageHD(trangHD));
        lbSoTrang.setText(trangHD + " of " + soTrangHD);
    }//GEN-LAST:event_btnCuoi1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCuoi1;
    private javax.swing.JButton btnDau1;
    private javax.swing.JButton btnDoiHang;
    private javax.swing.JButton btnLui1;
    private javax.swing.JButton btnTien1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbSoTrang;
    private javax.swing.JPanel panelTong;
    private javax.swing.JPanel pnlTong;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTable tblLoaiSanPham;
    private javax.swing.JTextField txtGhiChu;
    private javax.swing.JTextField txtMaHoaDon;
    private javax.swing.JTextField txtMaKhachHang;
    private javax.swing.JTextField txtMaNhanVien;
    private javax.swing.JTextField txtNgayTao;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txtTongTien;
    private javax.swing.JTextField txtTrangThai;
    // End of variables declaration//GEN-END:variables
}
