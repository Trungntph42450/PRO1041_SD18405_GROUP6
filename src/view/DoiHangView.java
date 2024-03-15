/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.ChatLieu;
import model.ChiTietSanPham;
import model.DoiHang;
import model.DoiHangChiTiet;
import model.HoaDon;
import model.HoaDonChiTiet;
import model.KhachHang;
import model.KichThuoc;
import model.LoaiSanPham;
import model.MauSac;
import model.NhanVien;
import model.SanPham;
import service.servicImp.ChiTietSanPhamServiceImp;
import service.servicImp.DoiHangChiTietServiceImp;
import service.servicImp.DoiHangServiceImp;
import service.servicImp.HoaDonChiTietServiceImp;
import service.servicImp.HoaDonServiceImp;
import service.servicImp.NhanVienServiceImp;
import util.PDFGene2;
import static view.ChonChiTietSanPhamView.maCTSPCu;
import static view.ChonChiTietSanPhamView.soLuongDoiHang;

/**
 *
 * @author Admin
 */
public class DoiHangView extends javax.swing.JPanel {
    
    DoiHangServiceImp serviceDH = new DoiHangServiceImp();
    HoaDonChiTietServiceImp serviceHDCT = new HoaDonChiTietServiceImp();
    HoaDonServiceImp serviceHD = new HoaDonServiceImp();
    NhanVienServiceImp serviceNV = new NhanVienServiceImp();
    DoiHangChiTietServiceImp serviceDHCT = new DoiHangChiTietServiceImp();
    ChiTietSanPhamServiceImp serviceCTSP = new ChiTietSanPhamServiceImp();
    HoaDonView hoaDonView = new HoaDonView();
    DefaultTableModel tblmolDSSPCu = new DefaultTableModel();
    DefaultTableModel tblmolHDCT = new DefaultTableModel();
    DefaultTableModel tblmolDSSPMoi = new DefaultTableModel();
    public static int indexHoaDonDoiHang = -1;
    public static int indexHoaDonChiTiet = -1;
    public static int indexDoiHangChiTiet = -1;
    public static String tenSP;
    public static String chatLieu;
    public static String kichThuoc;
    public static String mauSac;
    public static int soLuongSP;
    public static int soLuongDoiHang;
    public static String maDHCT;
    public static String maHDCT;
    public static String maCTSPCu;
    static String maHD = null;
    static String maDH = null;
    int so = serviceDHCT.countDoiHangChiTiet();
    Random random = new Random();
    PDFGene2 pdf = new PDFGene2();
    DangNhapView dangNhapView = new DangNhapView();
    String userName = dangNhapView.getTaiKhoan();

    /**
     * Creates new form DoiHangView
     */
    public DoiHangView() {
        initComponents();
        this.setSize(1300, 755);
        maHD = hoaDonView.getMaHD();
        maDH = hoaDonView.getMaDH();
        fillTableHoaDonChiTiet(serviceHDCT.getHDCTFromHoaDon(maHD));
        loadDSSPCu(serviceDHCT.getDHCTFromDH(maDH));
        loadDSSPMoi(serviceDHCT.getDHCTFromDH(maDH));
        setForm();
    }
    
    public String getTenSP() {
        return tenSP;
    }
    
    public void setTenSP(String tenSP) {
        DoiHangView.tenSP = tenSP;
    }
    
    public int getIndexHDDH() {
        return indexHoaDonDoiHang;
    }
    
    public void setIndexHDDH(int indexHoaDonDoiHang) {
        DoiHangView.indexHoaDonDoiHang = indexHoaDonDoiHang;
    }
    
    public String getMaCTSPCu() {
        return maCTSPCu;
    }
    
    public void setMaCTSPCu(String maCTSPCu) {
        DoiHangView.maCTSPCu = maCTSPCu;
    }
    
    public String getMaDHCT() {
        return maDHCT;
    }
    
    public void setMaDHCT(String maDHCT) {
        DoiHangView.maDHCT = maDHCT;
    }
    
    public String getChatLieu() {
        return chatLieu;
    }
    
    public void setChatLieu(String chatLieu) {
        DoiHangView.chatLieu = chatLieu;
    }
    
    public String getKichThuoc() {
        return kichThuoc;
    }
    
    public void setKichThuoc(String kichThuoc) {
        DoiHangView.kichThuoc = kichThuoc;
    }
    
    public String getMauSac() {
        return mauSac;
    }
    
    public void setMauSac(String mauSac) {
        DoiHangView.mauSac = mauSac;
    }
    
    public String getMaHDCT() {
        return maHDCT;
    }
    
    public void setMaHDCT(String maHDCT) {
        DoiHangView.maHDCT = maHDCT;
    }
    
    public int getSoLuongSP() {
        return soLuongSP;
    }
    
    public int getSoLuongDoiHang() {
        return soLuongDoiHang;
    }
    
    public void setMaCL(int soLuongSP) {
        DoiHangView.soLuongSP = soLuongSP;
    }
    
    public void fillTableHoaDonChiTiet(List<HoaDonChiTiet> list) {
        tblmolHDCT = (DefaultTableModel) tblChiTietHoaDon.getModel();
        tblmolHDCT.setRowCount(0);
        for (HoaDonChiTiet item : list) {
            tblmolHDCT.addRow(new Object[]{tblChiTietHoaDon.getRowCount() + 1,
                item.getMaHDCT(), item.getCtsp().getSanPham().getTenSanPham(),
                item.getCtsp().getMaChiTietSanPham(), item.getSoLuong(),
                item.getDonGia()
            });
        }
    }
    
    public void deltailChiTietSanPham(int index) {
        String maCTSP = tblChiTietHoaDon.getValueAt(index, 3).toString();
        ChiTietSanPham ctsp = serviceCTSP.getThuocTinh(maCTSP).get(0);
        txtChatLieu.setText(ctsp.getChatLieu().getTenChatLieu());
        txtKichThuoc.setText(ctsp.getKichThuoc().getTenKichThuoc());
        txtMauSac.setText(ctsp.getMauSac().getTenMauSac());
        chatLieu = txtChatLieu.getText();
        kichThuoc = txtKichThuoc.getText();
        mauSac = txtMauSac.getText();
    }
    
    public boolean validSoLuong(int index) {
        try {
            if (txtSoLuongDoiHang.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Số lượng không được để trống");
                return false;
            }
            soLuongDoiHang = Integer.parseInt(txtSoLuongDoiHang.getText());
            int soLuongConLai = Integer.parseInt(tblChiTietHoaDon.getValueAt(index, 4).toString());
            if ((soLuongDoiHang > soLuongConLai)) {
                JOptionPane.showMessageDialog(this, "Số lượng sản phẩm không đủ");
                return false;
            } else if (soLuongDoiHang < 1) {
                JOptionPane.showMessageDialog(this, "Số lượng tối thiểu đổi là 1");
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Số lượng sai định dạng");
            return false;
        }
        return true;
    }
    
    public void loadDSSPCu(List<DoiHangChiTiet> list) {
        tblmolDSSPCu = (DefaultTableModel) tblDanhSachSPCu.getModel();
        tblmolDSSPCu.setRowCount(0);
        for (DoiHangChiTiet item : list) {
            tblmolDSSPCu.addRow(new Object[]{
                item.getHoaDonChiTiet().getCtsp().getMaChiTietSanPham(),
                item.getHoaDonChiTiet().getCtsp().getSanPham().getTenSanPham(),
                item.getHoaDonChiTiet().getCtsp().getChatLieu().getTenChatLieu(),
                item.getHoaDonChiTiet().getCtsp().getKichThuoc().getTenKichThuoc(),
                item.getHoaDonChiTiet().getCtsp().getMauSac().getTenMauSac(),
                item.getSoLuong(), item.getHoaDonChiTiet().getCtsp().getGia(),
                item.getHoaDonChiTiet().getMaHDCT()
            });
        }
    }
    
    public void loadDSSPMoi(List<DoiHangChiTiet> list) {
        tblmolDSSPMoi = (DefaultTableModel) tblDanhSachSPMoi.getModel();
        tblmolDSSPMoi.setRowCount(0);
        for (DoiHangChiTiet item : list) {
            tblmolDSSPMoi.addRow(new Object[]{
                item.getChiTietSanPham().getMaChiTietSanPham(),
                item.getChiTietSanPham().getSanPham().getTenSanPham(),
                item.getChiTietSanPham().getChatLieu().getTenChatLieu(),
                item.getChiTietSanPham().getKichThuoc().getTenKichThuoc(),
                item.getChiTietSanPham().getMauSac().getTenMauSac(),
                item.getSoLuong(), item.getChiTietSanPham().getGia(),
                item.getMoTa()
            });
        }
    }
    
    public String phanCach(Double x) {
        NumberFormat fm = NumberFormat.getNumberInstance(Locale.US);
        return fm.format(x);
    }
    
    public String boPhanCach(String x) {
        String so = x.replace(",", "");
        if (so.isBlank()) {
            return "0";
        }
        try {
            Double temp = Double.valueOf(so);
            return String.format("%.0f", temp);
        } catch (NumberFormatException e) {
            return "0";
        }
    }
    
    public void tinhThua() {
        Double tongTien = Double.valueOf(boPhanCach(txtTienTra.getText()));
        txtTienDua.setText(phanCach(Double.valueOf(boPhanCach(txtTienDua.getText()))));
        Double tienKhach = Double.valueOf(boPhanCach(txtTienDua.getText()));
        Double tienThua = tienKhach - tongTien;
        txtTienThua.setText(phanCach(tienThua));
    }
    
    public void setForm() {
        txtMaHD.setText(maHD);
        txtMaKH.setText(serviceHD.getOne(maHD).getKhachHang().getMaKhachHang());
        int soCot = tblDanhSachSPMoi.getRowCount();
        int soCot2 = tblChiTietHoaDon.getRowCount();
        int soCot3 = tblDanhSachSPCu.getRowCount();
        Double sum = 0.0;
        Double sum2 = 0.0;
        Double sum3 = 0.0;
        for (int i = 0; i < soCot; i++) {
            sum += Double.valueOf(tblDanhSachSPMoi.getValueAt(i, 5).toString()) * Double.valueOf(tblDanhSachSPMoi.getValueAt(i, 6).toString());
        }
        for (int i = 0; i < soCot2; i++) {
            sum2 += Double.valueOf(tblChiTietHoaDon.getValueAt(i, 5).toString()) * Double.valueOf(tblChiTietHoaDon.getValueAt(i, 4).toString());
        }
        for (int i = 0; i < soCot3; i++) {
            sum3 += Double.valueOf(tblDanhSachSPCu.getValueAt(i, 5).toString()) * Double.valueOf(tblDanhSachSPCu.getValueAt(i, 6).toString());
        }
        txtTongTienHoaDon.setText(phanCach(sum2 + sum3));
        txtTongTienHangDoi.setText(phanCach(sum2 + sum));
        Double tra = sum - sum3;
        if (tra < 0) {
            tra = 0.0;
        }
        txtTienTra.setText(phanCach(tra));
        txtTienDua.setEnabled(true);
        tinhThua();
    }
    
    public List<DoiHangChiTiet> getJoHang(JTable table, JTable table2) {
        List<DoiHangChiTiet> list = new ArrayList<>();
        int soCot = table.getRowCount();
        try {
            for (int i = 0; i < soCot; i++) {
                String maCTSP = table.getValueAt(i, 0).toString();
                String maCTSP2 = table2.getValueAt(i, 0).toString();
                int soLuong = Integer.parseInt(table2.getValueAt(i, 5).toString());
                Double gia = Double.parseDouble(table2.getValueAt(i, 6).toString());
                String ten = table2.getValueAt(i, 1).toString();
                String cl = table2.getValueAt(i, 2).toString();
                String ms = table2.getValueAt(i, 3).toString();
                String kt = table2.getValueAt(i, 4).toString();
                System.out.println(kt);
                String mota = table2.getValueAt(i, 7).toString();
                System.out.println(mota);
                
                ChiTietSanPham ctspCu = new ChiTietSanPham(maCTSP);
                
                SanPham sp = new SanPham(null, ten);
                ChatLieu cl2 = new ChatLieu(null, cl);
                MauSac cl3 = new MauSac(null, ms);
                KichThuoc cl4 = new KichThuoc(null, kt);
                ChiTietSanPham ctspMoi = new ChiTietSanPham(maCTSP2, sp, cl3, cl2, cl4, gia);
//                ChiTietSanPham ctspMoi = new ChiTietSanPham(maCTSP2, sp, soLuong, gia, true);
//                ChiTietSanPham ctspMoi = new ChiTietSanPham(maCTSP2, gia);
                HoaDonChiTiet hdct = new HoaDonChiTiet(ctspCu);
                DoiHangChiTiet dhct = new DoiHangChiTiet(hdct, ctspMoi, soLuong, mota);
                list.add(dhct);
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    public List<HoaDonChiTiet> getJoHang2(JTable table) {
        List<HoaDonChiTiet> list = new ArrayList<>();
        int soCot = table.getRowCount();
        try {
            for (int i = 0; i < soCot; i++) {
                String maCTSP = table.getValueAt(i, 0).toString();
                int soLuong = Integer.parseInt(table.getValueAt(i, 5).toString());
                Double gia = Double.parseDouble(table.getValueAt(i, 6).toString());
                
                ChiTietSanPham ctsp = new ChiTietSanPham(maCTSP);
//                ChiTietSanPham ctspMoi = new ChiTietSanPham(maCTSP2, gia);
                HoaDonChiTiet hdct = new HoaDonChiTiet(ctsp, soLuong, gia);
                list.add(hdct);
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    public void lamMoi() {
        int checkXoaGH = JOptionPane.showConfirmDialog(this, "Bạn có chắc mắc muốn xoá tất cả Các sản phẩm đang được đổi hàng");
        if (checkXoaGH == JOptionPane.YES_NO_OPTION) {
            for (int i = 0; i < tblDanhSachSPCu.getRowCount(); i++) {
                String productID = tblDanhSachSPCu.getValueAt(i, 0).toString();
                String MaHDCT = tblDanhSachSPCu.getValueAt(i, 7).toString();
                int quantity = Integer.parseInt(tblDanhSachSPCu.getValueAt(i, 5).toString());
                int indexHDCT = -1;
                for (int j = 0; j < tblChiTietHoaDon.getRowCount(); j++) {
                    if (tblChiTietHoaDon.getValueAt(j, 3).toString().equals(productID)) {
                        indexHDCT = j;
                        break;
                    }
                }
                if (indexHDCT != -1) {
                    serviceCTSP.capNhatSoLuongThanhToanCong(quantity, productID);
                    serviceHDCT.capNhatSoLuongDoiHang2(quantity, MaHDCT);
                }
            }
            for (int i = 0; i < tblDanhSachSPMoi.getRowCount(); i++) {
                String productID = tblDanhSachSPMoi.getValueAt(i, 0).toString();
                int quantity = Integer.parseInt(tblDanhSachSPMoi.getValueAt(i, 5).toString());
                serviceCTSP.tangSoLuong(quantity, productID);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Xoá sản phẩm không thành công");
        }
        fillTableHoaDonChiTiet(serviceHDCT.getHDCTFromHoaDon(maHD));
        serviceDHCT.xoaDHCT(maDH);
        loadDSSPCu(serviceDHCT.getDHCTFromDH(maDH));
        loadDSSPMoi(serviceDHCT.getDHCTFromDH(maDH));
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
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        btnXacNhanDoiHang = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtMaHD = new javax.swing.JTextField();
        txtMaKH = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtTongTienHoaDon = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txtTongTienHangDoi = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        txtTienDua = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        txtTienThua = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        txtTienTra = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblChiTietHoaDon = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblDanhSachSPCu = new javax.swing.JTable();
        btnChonHang = new javax.swing.JButton();
        btnLamMoiDSDH = new javax.swing.JButton();
        btnQuayLai = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtChatLieu = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtKichThuoc = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtMauSac = new javax.swing.JTextField();
        txtSoLuongDoiHang = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblDanhSachSPMoi = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();

        pnlTong.setLayout(new java.awt.CardLayout());

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Đơn đổi hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        btnXacNhanDoiHang.setText("Xác nhận đổi hàng");
        btnXacNhanDoiHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXacNhanDoiHangActionPerformed(evt);
            }
        });

        jLabel18.setText("Mã HĐ");

        jLabel20.setText("Mã KH");

        txtMaHD.setEnabled(false);

        txtMaKH.setEnabled(false);

        jLabel24.setText("Tổng tiền hóa đơn:");

        txtTongTienHoaDon.setEnabled(false);

        jLabel25.setText("Tổng tiền hàng đổi:");

        txtTongTienHangDoi.setEnabled(false);

        jLabel26.setText("Tiền khách đưa");

        txtTienDua.setEnabled(false);
        txtTienDua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTienDuaKeyReleased(evt);
            }
        });

        jLabel27.setText("Tiền thừa");

        txtTienThua.setEnabled(false);

        jLabel28.setText("Tiền phải trả");

        txtTienTra.setEnabled(false);
        txtTienTra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTienTraActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(jLabel20))
                        .addGap(83, 83, 83)
                        .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnXacNhanDoiHang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtTienDua, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel25)
                                .addComponent(jLabel24))
                            .addComponent(jLabel28))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtMaHD, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                                .addComponent(txtTongTienHoaDon, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtTongTienHangDoi))
                            .addComponent(txtTienTra, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(txtTongTienHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(txtTongTienHangDoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(txtTienTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTienDua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnXacNhanDoiHang, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chi tiết hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tblChiTietHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã HĐCT", "Tên SP", "Mã CTSP", "Số lượng", "Đơn giá"
            }
        ));
        tblChiTietHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblChiTietHoaDonMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblChiTietHoaDon);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 827, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách sản phẩm cũ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tblDanhSachSPCu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã CTSP", "Tên SP", "Chất liệu", "Kích thước", "Màu sắc", "Số lượng", "Đơn giá", "Mã HĐCT"
            }
        ));
        tblDanhSachSPCu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDanhSachSPCuMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblDanhSachSPCu);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 553, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnChonHang.setText("Chọn sản phẩm đổi");
        btnChonHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnChonHangMouseClicked(evt);
            }
        });

        btnLamMoiDSDH.setText("Làm mới");
        btnLamMoiDSDH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLamMoiDSDHMouseClicked(evt);
            }
        });
        btnLamMoiDSDH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiDSDHActionPerformed(evt);
            }
        });

        btnQuayLai.setText("Hủy");
        btnQuayLai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnQuayLaiMouseClicked(evt);
            }
        });
        btnQuayLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuayLaiActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel1.setText("Chất liệu: ");

        txtChatLieu.setEnabled(false);

        jLabel2.setText("Kích thước: ");

        txtKichThuoc.setEnabled(false);

        jLabel3.setText("Màu sắc: ");

        txtMauSac.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(75, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(txtMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txtChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(txtKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách sản phẩm mới", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tblDanhSachSPMoi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã CTSP", "Tên SP", "Chất liệu", "Kích thước", "Màu sắc", "Số lượng", "Đơn giá", "Mô tả"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDanhSachSPMoi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDanhSachSPMoiMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblDanhSachSPMoi);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 565, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel4.setText("Số lượng đổi hàng:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnQuayLai, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnLamMoiDSDH, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtSoLuongDoiHang, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(44, 44, 44)
                                        .addComponent(btnChonHang)))))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnQuayLai, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnLamMoiDSDH, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(txtSoLuongDoiHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnChonHang, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(461, 461, 461))
        );

        pnlTong.add(jPanel4, "card2");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1335, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 720, Short.MAX_VALUE)
        );

        pnlTong.add(jPanel7, "card3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTong, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnLamMoiDSDHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiDSDHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLamMoiDSDHActionPerformed

    private void tblDanhSachSPCuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDanhSachSPCuMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblDanhSachSPCuMouseClicked

    private void tblChiTietHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChiTietHoaDonMouseClicked
        // TODO add your handling code here:
        indexHoaDonChiTiet = tblChiTietHoaDon.getSelectedRow();
        maCTSPCu = tblChiTietHoaDon.getValueAt(indexHoaDonChiTiet, 3).toString();
        maHDCT = tblChiTietHoaDon.getValueAt(indexHoaDonChiTiet, 1).toString();
        deltailChiTietSanPham(indexHoaDonChiTiet);
    }//GEN-LAST:event_tblChiTietHoaDonMouseClicked

    private void btnXacNhanDoiHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXacNhanDoiHangActionPerformed
        if (txtTienThua.getText().contains("-")) {
            JOptionPane.showMessageDialog(this, "Số tiền được nhập không đủ để thực hiện giao dịch");
            return;
        }
        if (tblDanhSachSPCu.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Chưa có sản phẩm đổi");
            return;
        }
        List<HoaDonChiTiet> listHDCT = serviceDHCT.getJoHang(tblChiTietHoaDon);
        List<HoaDonChiTiet> listHDCT3 = serviceDHCT.getJoHang(tblChiTietHoaDon);
        List<HoaDonChiTiet> listHDCT2 = getJoHang2(tblDanhSachSPCu);
        List<DoiHangChiTiet> listDHCT = getJoHang(tblDanhSachSPCu, tblDanhSachSPMoi);
        if (listHDCT == null || listHDCT2 == null) {
            return;
        }
        for (HoaDonChiTiet hdct3 : listHDCT) {
            for (HoaDonChiTiet hdct2 : listHDCT2) {
                if (hdct3.getCtsp().getMaChiTietSanPham().equals(hdct2.getCtsp().getMaChiTietSanPham())) {
                    hdct3.setSoLuong(hdct3.getSoLuong() + hdct2.getSoLuong());
                }
            }
        }
        
        HoaDon hd = serviceHD.getOne(maHD);
        NhanVien nv = serviceNV.timTheoUserName(userName);
        DoiHang dh = new DoiHang(hoaDonView.getMaDH(), hd, nv);
        try {
            String tra = txtTienTra.getText();
            String dua = txtTienDua.getText();
            String thua = txtTienThua.getText();
            pdf.genPDF(listDHCT, listHDCT, listHDCT3, dh, 0.0, tra, dua, thua);
            serviceDH.capNhatTrangThai(maDH);
            serviceDHCT.capNhatTrangThai(maDH);
            pnlTong.removeAll();
            pnlTong.add(new LichSuDoiHangView());
            pnlTong.repaint();
            pnlTong.revalidate();
        } catch (IOException ex) {
            Logger.getLogger(DoiHangView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnXacNhanDoiHangActionPerformed

    private void btnChonHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnChonHangMouseClicked
        // TODO add your handling code here:
        if (indexHoaDonChiTiet == -1) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn sản phẩm");
        } else {
            if (validSoLuong(indexHoaDonChiTiet)) {
                pnlTong.removeAll();
                pnlTong.add(new ChonChiTietSanPhamView());
                pnlTong.repaint();
                pnlTong.revalidate();
            }
        }
    }//GEN-LAST:event_btnChonHangMouseClicked

    private void btnLamMoiDSDHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLamMoiDSDHMouseClicked
        // TODO add your handling code here:
        lamMoi();
    }//GEN-LAST:event_btnLamMoiDSDHMouseClicked

    private void btnQuayLaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnQuayLaiMouseClicked
        // TODO add your handling code here:
        //        quayLaiDoiHang();
        int check = JOptionPane.showConfirmDialog(this, "Xác nhận hủy đơn hàng?");
        if (check == 0) {
            lamMoi();
            serviceHD.capNhatTrangThai(maHD);
            pnlTong.removeAll();
            pnlTong.add(new HoaDonView());
            pnlTong.repaint();
            pnlTong.revalidate();
        }
    }//GEN-LAST:event_btnQuayLaiMouseClicked

    private void tblDanhSachSPMoiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDanhSachSPMoiMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblDanhSachSPMoiMouseClicked

    private void btnQuayLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuayLaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnQuayLaiActionPerformed

    private void txtTienTraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTienTraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTienTraActionPerformed

    private void txtTienDuaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienDuaKeyReleased
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_DELETE))) {
            String txt = txtTienDua.getText().replaceAll("[^\\d]", "");
            txtTienDua.setText(txt);
        }
        tinhThua();
    }//GEN-LAST:event_txtTienDuaKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChonHang;
    private javax.swing.JButton btnLamMoiDSDH;
    private javax.swing.JButton btnQuayLai;
    private javax.swing.JButton btnXacNhanDoiHang;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JPanel pnlTong;
    private javax.swing.JTable tblChiTietHoaDon;
    private javax.swing.JTable tblDanhSachSPCu;
    private javax.swing.JTable tblDanhSachSPMoi;
    private javax.swing.JTextField txtChatLieu;
    private javax.swing.JTextField txtKichThuoc;
    private javax.swing.JTextField txtMaHD;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtMauSac;
    private javax.swing.JTextField txtSoLuongDoiHang;
    private javax.swing.JTextField txtTienDua;
    private javax.swing.JTextField txtTienThua;
    private javax.swing.JTextField txtTienTra;
    private javax.swing.JTextField txtTongTienHangDoi;
    private javax.swing.JTextField txtTongTienHoaDon;
    // End of variables declaration//GEN-END:variables
}
