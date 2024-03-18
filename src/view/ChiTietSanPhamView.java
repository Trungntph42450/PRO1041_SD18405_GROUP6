/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import model.ChatLieu;
import model.ChiTietSanPham;
import model.KichThuoc;
import model.LoaiSanPham;
import model.MauSac;
import model.SanPham;
import service.servicImp.ChatLieuServiceImp;
import service.servicImp.ChiTietSanPhamServiceImp;
import service.servicImp.KichThuocServiceImp;
import service.servicImp.MauSacServiceImp;
import service.servicImp.SanPhamServiceImp;
//
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.IndexedColors;

/**
 *
 * @author Admin
 */
public class ChiTietSanPhamView extends javax.swing.JPanel {

    DefaultTableModel mol = new DefaultTableModel();
    ChiTietSanPhamServiceImp serviceCTSP = new ChiTietSanPhamServiceImp();
    DefaultComboBoxModel<LoaiSanPham> cbxLoaiSanPham = new DefaultComboBoxModel<>();
    DefaultComboBoxModel<MauSac> cbxMauSac = new DefaultComboBoxModel<>();
    DefaultComboBoxModel<MauSac> cbxMauSacLoc = new DefaultComboBoxModel<>();
    DefaultComboBoxModel<ChatLieu> cbxChatLieu = new DefaultComboBoxModel<>();
    DefaultComboBoxModel<ChatLieu> cbxChatLieuLoc = new DefaultComboBoxModel<>();
    DefaultComboBoxModel<KichThuoc> cbxKichThuoc = new DefaultComboBoxModel<>();
    DefaultComboBoxModel<KichThuoc> cbxKichThuocLoc = new DefaultComboBoxModel<>();
    DefaultComboBoxModel<SanPham> cbxSanPham = new DefaultComboBoxModel<>();
    MauSacServiceImp serviceMS = new MauSacServiceImp();
    ChatLieuServiceImp serviceCl = new ChatLieuServiceImp();
    KichThuocServiceImp serviceKT = new KichThuocServiceImp();
    SanPhamServiceImp serviceSP = new SanPhamServiceImp();
    int trangCTSP = 1, soTrangCTSP, tongBanGhiCTSP, index = -1;
    public List<String> list = new ArrayList<>();

    /**
     * Creates new form CTSPView
     */
    public ChiTietSanPhamView() {
        initComponents();
        this.setSize(1300, 755);
        loadCbxSanPham(serviceSP.getAll());
        loadPageCTSP();
        cboMaSP.setEnabled(false);
        loadCbxKichThuoc(serviceKT.getAll());
        loadCbxMauSac(serviceMS.getAll());
        loadCbxChatLieu(serviceCl.getAll());
        rdConhang1.setSelected(true);
        loadLocChatLieu(serviceCl.getAll());
        loadLocMauSac(serviceMS.getAll());
        loadLocKichThuoc(serviceKT.getAll());

    }

    public void mtam2() {
        if (cboLocChat.getSelectedIndex() != -1 && cboLocMau.getSelectedIndex() == -1 && cboLocKich.getSelectedIndex() == -1) {
            ChatLieu cl = (ChatLieu) cbxChatLieuLoc.getSelectedItem();
            String tenTimCL = cl.toString();
            String tenList = new SanPhamView().getTenSPs(null, new SanPhamView().getTenSanPham());
            fillTableChiTietSanPham(serviceCTSP.getListLocCL(tenList, tenTimCL));
        } else if (cboLocChat.getSelectedIndex() == -1 && cboLocMau.getSelectedIndex() != -1 && cboLocKich.getSelectedIndex() == -1) {
            MauSac ms = (MauSac) cbxMauSacLoc.getSelectedItem();
            String tenTimMS = ms.toString();
            String tenList = new SanPhamView().getTenSPs(null, new SanPhamView().getTenSanPham());
            fillTableChiTietSanPham(serviceCTSP.getListLocMS(tenList, tenTimMS));
        } else if (cboLocChat.getSelectedIndex() == -1 && cboLocMau.getSelectedIndex() == -1 && cboLocKich.getSelectedIndex() != -1) {
            KichThuoc kt = (KichThuoc) cbxKichThuocLoc.getSelectedItem();
            String tenTimKT = kt.toString();
            String tenList = new SanPhamView().getTenSPs(null, new SanPhamView().getTenSanPham());
            fillTableChiTietSanPham(serviceCTSP.getListLocKT(tenList, tenTimKT));
        } else if (cboLocChat.getSelectedIndex() != -1 && cboLocMau.getSelectedIndex() != -1 && cboLocKich.getSelectedIndex() == -1) {
            ChatLieu cl = (ChatLieu) cbxChatLieuLoc.getSelectedItem();
            String tenTimCL = cl.toString();
            MauSac ms = (MauSac) cbxMauSacLoc.getSelectedItem();
            String tenTimMS = ms.toString();
            String tenList = new SanPhamView().getTenSPs(null, new SanPhamView().getTenSanPham());
            fillTableChiTietSanPham(serviceCTSP.getListLocCLMS(tenList, tenTimCL, tenTimMS));
        } else if (cboLocChat.getSelectedIndex() == -1 && cboLocMau.getSelectedIndex() != -1 && cboLocKich.getSelectedIndex() != -1) {
            MauSac ms = (MauSac) cbxMauSacLoc.getSelectedItem();
            String tenTimMS = ms.toString();
            KichThuoc kt = (KichThuoc) cbxKichThuocLoc.getSelectedItem();
            String tenTimKT = kt.toString();
            String tenList = new SanPhamView().getTenSPs(null, new SanPhamView().getTenSanPham());
            fillTableChiTietSanPham(serviceCTSP.getListLocMSKT(tenList, tenTimMS, tenTimKT));
        } else if (cboLocChat.getSelectedIndex() != -1 && cboLocMau.getSelectedIndex() == -1 && cboLocKich.getSelectedIndex() != -1) {
            ChatLieu cl = (ChatLieu) cbxChatLieuLoc.getSelectedItem();
            String tenTimCL = cl.toString();
            KichThuoc kt = (KichThuoc) cbxKichThuocLoc.getSelectedItem();
            String tenTimKT = kt.toString();
            String tenList = new SanPhamView().getTenSPs(null, new SanPhamView().getTenSanPham());
            fillTableChiTietSanPham(serviceCTSP.getListLocCLKT(tenList, tenTimCL, tenTimKT));
        } else if (cboLocChat.getSelectedIndex() != -1 && cboLocMau.getSelectedIndex() != -1 && cboLocKich.getSelectedIndex() != -1) {
            ChatLieu cl = (ChatLieu) cbxChatLieuLoc.getSelectedItem();
            String tenTimCL = cl.toString();
            MauSac ms = (MauSac) cbxMauSacLoc.getSelectedItem();
            String tenTimMS = ms.toString();
            KichThuoc kt = (KichThuoc) cbxKichThuocLoc.getSelectedItem();
            String tenTimKT = kt.toString();
            String tenList = new SanPhamView().getTenSPs(null, new SanPhamView().getTenSanPham());
            fillTableChiTietSanPham(serviceCTSP.getListLoc(tenList, tenTimCL, tenTimMS, tenTimKT));
        }

    }

    public void fillTableChiTietSanPham(List<ChiTietSanPham> list) {
        mol = (DefaultTableModel) tblChiTietSanPham.getModel();
        mol.setRowCount(0);
        for (ChiTietSanPham chiTietSanPham : list) {
            mol.addRow(new Object[]{
                chiTietSanPham.getMaChiTietSanPham(),
                chiTietSanPham.getSoLuong(), chiTietSanPham.getGia(),
                chiTietSanPham.getSanPham(),
                chiTietSanPham.getChatLieu(), chiTietSanPham.getMauSac(),
                chiTietSanPham.getKichThuoc(), chiTietSanPham.isTrangThai() ? "Còn hàng" : "Hết hàng",});
        }
    }

    public ChiTietSanPham savesCTSP() {
        int soLuong;
        double gia;
        boolean trangThai;
        try {
            soLuong = Integer.parseInt(txtSoLuong.getText());
            gia = Double.parseDouble(txtGia.getText());
        } catch (Exception e) {
            return null;
        }
        SanPham sp = (SanPham) cbxSanPham.getSelectedItem();
        ChatLieu cl = (ChatLieu) cbxChatLieu.getSelectedItem();
        MauSac ms = (MauSac) cbxMauSac.getSelectedItem();
        KichThuoc kt = (KichThuoc) cbxKichThuoc.getSelectedItem();
        if (rdConhang1.isSelected()) {
            trangThai = true;
        } else {
            trangThai = false;
        }
//       String qr= qrCode(txtMaCTSP.getText());
        return new ChiTietSanPham(sp, ms, cl, kt, soLuong, gia, trangThai);

    }

    public void deltailChiTietSanPham(int index) {
        txtSoLuong.setText(tblChiTietSanPham.getValueAt(index, 1).toString());
        txtGia.setText(tblChiTietSanPham.getValueAt(index, 2).toString());
        SanPham sp = (SanPham) tblChiTietSanPham.getValueAt(index, 3);
        cbxSanPham.setSelectedItem(sp);
        ChatLieu cl = (ChatLieu) tblChiTietSanPham.getValueAt(index, 4);
        cbxChatLieu.setSelectedItem(cl);
        MauSac ms = (MauSac) tblChiTietSanPham.getValueAt(index, 5);
        cbxMauSac.setSelectedItem(ms);
        KichThuoc kt = (KichThuoc) tblChiTietSanPham.getValueAt(index, 6);
        cbxKichThuoc.setSelectedItem(kt);
        if (tblChiTietSanPham.getValueAt(index, 7).toString().equals("Còn hàng")) {
            rdConhang1.setSelected(true);
        } else {
            rdHethang1.setSelected(true);
        }

    }

    public void loadPageCTSP() {
        String tenPage = new SanPhamView().getTenSPs(null, new SanPhamView().getTenSanPham());
        tongBanGhiCTSP = serviceCTSP.tongBanGhi(tenPage);
        if (tongBanGhiCTSP % 5 == 0) {
            soTrangCTSP = tongBanGhiCTSP / 5;
        } else {
            soTrangCTSP = tongBanGhiCTSP / 5 + 1;
        }
        lbSoTrang2.setText(trangCTSP + " of " + soTrangCTSP);
        fillTableChiTietSanPham(serviceCTSP.listPageCTSP(trangCTSP, tenPage));
    }

    public void loadCbxSanPham(List<SanPham> list) {
        cbxSanPham.removeAllElements();
        String ma = new SanPhamView().getMaSanPham();
        String ten = new SanPhamView().getTenSanPham();
        for (SanPham sanPham : list) {
            SanPham sps = new SanPham(ma, ten);
            cbxSanPham.addElement(sps);
        }
        cboMaSP.setModel((ComboBoxModel) cbxSanPham);
    }

    public void loadLocChatLieu(List<ChatLieu> list) {
        cbxChatLieuLoc.removeAllElements();
        for (ChatLieu chatLieu : list) {
            cbxChatLieuLoc.addElement(chatLieu);
        }
        cboLocChat.setModel((ComboBoxModel) cbxChatLieuLoc);
        cboLocChat.setSelectedIndex(-1);
    }

    public void loadLocMauSac(List<MauSac> list) {
        cbxMauSacLoc.removeAllElements();
        for (MauSac mauSac : list) {
            cbxMauSacLoc.addElement(mauSac);
        }
        cboLocMau.setModel((ComboBoxModel) cbxMauSacLoc);
        cboLocMau.setSelectedIndex(-1);
    }

    public void loadLocKichThuoc(List<KichThuoc> list) {
        cbxKichThuocLoc.removeAllElements();
        for (KichThuoc kichThuoc : list) {
            cbxKichThuocLoc.addElement(kichThuoc);
        }
        cboLocKich.setModel((ComboBoxModel) cbxKichThuocLoc);
        cboLocKich.setSelectedIndex(-1);
    }

    public void loadCbxMauSac(List<MauSac> list) {
        cbxMauSac.removeAllElements();
        for (MauSac mauSac : list) {
            cbxMauSac.addElement(mauSac);
        }
        cboMauSac.setModel((ComboBoxModel) cbxMauSac);
    }

    public void loadCbxKichThuoc(List<KichThuoc> list) {
        cbxKichThuoc.removeAllElements();
        for (KichThuoc kichThuoc : list) {
            cbxKichThuoc.addElement(kichThuoc);
        }
        cboKichThuoc.setModel((ComboBoxModel) cbxKichThuoc);
    }

    public void loadCbxChatLieu(List<ChatLieu> list) {
        cbxChatLieu.removeAllElements();
        for (ChatLieu chatLieu : list) {
            cbxChatLieu.addElement(chatLieu);
        }
        cboChatLieu.setModel((ComboBoxModel) cbxChatLieu);
    }

    public String qrCode(String qrcode) {
        try {
            String qrCodeData = qrcode;
            String filePath = "D:\\PRO1041_DuAn1\\PRO1041_DuAn1\\src\\qr\\" + qrcode + ".png";
            String charset = "UTF-8";
            Map<EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            BitMatrix matrix = new MultiFormatWriter().encode(
                    new String(qrCodeData.getBytes(charset), charset),
                    BarcodeFormat.QR_CODE, 200, 200, hintMap);
            MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath.lastIndexOf(".") + 1), new File(filePath));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return qrcode;
    }

    public static CellStyle createStyleForHeader(XSSFSheet sheet) {
        // Create font
        XSSFFont font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman");
        font.setBold(true);
        font.setFontHeightInPoints((short) 14); // font size
        font.setColor(IndexedColors.BLACK.getIndex()); // text color

        // Create CellStyle
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setFillForegroundColor(IndexedColors.BLUE_GREY.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        return cellStyle;
    }

    public boolean validateCTSPs() {
        if (txtSoLuong.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Số lượng trống");
            return false;
        } else {
            try {
                int sl = Integer.parseInt(txtSoLuong.getText());
                if (sl < 0) {
                    JOptionPane.showMessageDialog(this, "Số lượng phải lơn hơn 0");
                    return false;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Số lượng phải là số nguyên");
                return false;
            }
        }

        if (txtGia.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Giá trống");
            return false;
        } else {
            try {
                double gia = Double.parseDouble(txtGia.getText());
                if (gia < 0) {
                    JOptionPane.showMessageDialog(this, "Giá phải lơn hơn 0");
                    return false;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Giá phải là số");
                return false;
            }
        }
        return true;
    }

    public boolean validateCTSPGia() {
        if (txtTienMin.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tiền min trống");
            return false;
        } else {
            try {
                double gia = Double.parseDouble(txtTienMin.getText());
                if (gia < 0) {
                    JOptionPane.showMessageDialog(this, "Tiền min phải lơn hơn 0");
                    return false;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Tiền min phải là số");
                return false;
            }
        }

        if (txtTienMax.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tiền max trống");
            return false;
        } else {
            try {
                double gia = Double.parseDouble(txtTienMax.getText());
                if (gia < 0) {
                    JOptionPane.showMessageDialog(this, "Tiền max phải lơn hơn 0");
                    return false;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Tiền max phải là số");
                return false;
            }
        }
        if (Double.parseDouble(txtTienMin.getText()) > Double.parseDouble(txtTienMax.getText())) {
            JOptionPane.showMessageDialog(this, "Tiền max phải phải lơn hơn tiền min");
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
        jPanel2 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        cboChatLieu = new javax.swing.JComboBox<>();
        cboKichThuoc = new javax.swing.JComboBox<>();
        cboMauSac = new javax.swing.JComboBox<>();
        txtSoLuong = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        txtGia = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        rdConhang1 = new javax.swing.JRadioButton();
        rdHethang1 = new javax.swing.JRadioButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblChiTietSanPham = new javax.swing.JTable();
        cboMaSP = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnQuayLai = new javax.swing.JButton();
        btnThemCTSP = new javax.swing.JButton();
        btnSuaCTSP = new javax.swing.JButton();
        btnClearCTSP = new javax.swing.JButton();
        btnLui2 = new javax.swing.JButton();
        btnTien2 = new javax.swing.JButton();
        lbSoTrang2 = new javax.swing.JLabel();
        btnXuatfile = new javax.swing.JButton();
        btnBackSP = new javax.swing.JButton();
        jPanel18 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtTienMin = new javax.swing.JTextField();
        txtTienMax = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        btnSearchGia = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        cboLocMau = new javax.swing.JComboBox<>();
        cboLocKich = new javax.swing.JComboBox<>();
        cboLocChat = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnReset = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();

        setLayout(new java.awt.CardLayout());

        jPanel16.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.disabledSelectedBackground"));
        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin sản phẩm"));

        jLabel46.setText("Tên SP");

        jLabel47.setText("Màu sắc");

        jLabel48.setText("Chất liệu");

        jLabel49.setText("Kích thước");

        cboChatLieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cboKichThuoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cboMauSac.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboMauSac.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboMauSacMouseClicked(evt);
            }
        });

        jLabel50.setText("Số lượng");

        jLabel51.setText("Giá");

        jLabel52.setText("Trạng thái");

        buttonGroup1.add(rdConhang1);
        rdConhang1.setText("Còn hàng");

        buttonGroup1.add(rdHethang1);
        rdHethang1.setText("Hết hàng");

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
        if (tblChiTietSanPham.getColumnModel().getColumnCount() > 0) {
            tblChiTietSanPham.getColumnModel().getColumn(0).setResizable(false);
            tblChiTietSanPham.getColumnModel().getColumn(1).setResizable(false);
            tblChiTietSanPham.getColumnModel().getColumn(2).setResizable(false);
            tblChiTietSanPham.getColumnModel().getColumn(3).setResizable(false);
            tblChiTietSanPham.getColumnModel().getColumn(4).setResizable(false);
            tblChiTietSanPham.getColumnModel().getColumn(5).setResizable(false);
            tblChiTietSanPham.getColumnModel().getColumn(6).setResizable(false);
            tblChiTietSanPham.getColumnModel().getColumn(7).setResizable(false);
        }

        cboMaSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setText("VND");

        jLabel6.setText("Cái");

        btnQuayLai.setBackground(new java.awt.Color(0, 153, 102));
        btnQuayLai.setForeground(new java.awt.Color(255, 255, 255));
        btnQuayLai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/return.png"))); // NOI18N
        btnQuayLai.setText("Quay lại");
        btnQuayLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuayLaiActionPerformed(evt);
            }
        });

        btnThemCTSP.setBackground(new java.awt.Color(0, 153, 102));
        btnThemCTSP.setForeground(new java.awt.Color(255, 255, 255));
        btnThemCTSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/them.png"))); // NOI18N
        btnThemCTSP.setText("Thêm");
        btnThemCTSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemCTSPActionPerformed(evt);
            }
        });

        btnSuaCTSP.setBackground(new java.awt.Color(0, 153, 102));
        btnSuaCTSP.setForeground(new java.awt.Color(255, 255, 255));
        btnSuaCTSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/sua.png"))); // NOI18N
        btnSuaCTSP.setText("Sửa");
        btnSuaCTSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaCTSPActionPerformed(evt);
            }
        });

        btnClearCTSP.setBackground(new java.awt.Color(0, 153, 102));
        btnClearCTSP.setForeground(new java.awt.Color(255, 255, 255));
        btnClearCTSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/xoa.png"))); // NOI18N
        btnClearCTSP.setText("Xóa");
        btnClearCTSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearCTSPActionPerformed(evt);
            }
        });

        btnLui2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/lui.png"))); // NOI18N
        btnLui2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLui2ActionPerformed(evt);
            }
        });

        btnTien2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/tien.png"))); // NOI18N
        btnTien2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTien2ActionPerformed(evt);
            }
        });

        lbSoTrang2.setText("Số trang");

        btnXuatfile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/z4883861072449_7ae13d74ca98dc15bf13cc3275ca5686.jpg"))); // NOI18N
        btnXuatfile.setText("Xuất file");
        btnXuatfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatfileActionPerformed(evt);
            }
        });

        btnBackSP.setBackground(new java.awt.Color(0, 153, 102));
        btnBackSP.setForeground(new java.awt.Color(255, 255, 255));
        btnBackSP.setText("Back to sản phẩm");
        btnBackSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackSPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtGia, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel16Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(btnQuayLai)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(btnThemCTSP, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(cboMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel16Layout.createSequentialGroup()
                            .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel47))
                            .addGap(20, 20, 20)
                            .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cboMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cboChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel16Layout.createSequentialGroup()
                            .addComponent(jLabel49)
                            .addGap(18, 18, 18)
                            .addComponent(cboKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                        .addComponent(btnSuaCTSP)
                        .addGap(68, 68, 68)))
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(btnClearCTSP)
                        .addGap(85, 85, 85)
                        .addComponent(btnXuatfile))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdConhang1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdHethang1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addComponent(btnBackSP, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(320, 320, 320))
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(471, 471, 471)
                        .addComponent(btnLui2)
                        .addGap(38, 38, 38)
                        .addComponent(lbSoTrang2)
                        .addGap(43, 43, 43)
                        .addComponent(btnTien2))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 1074, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(rdConhang1)
                                    .addComponent(jLabel52))
                                .addGap(11, 11, 11)
                                .addComponent(rdHethang1))
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel48)
                                    .addComponent(cboChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel47))
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cboMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel46))
                                .addGap(14, 14, 14)
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel50)
                                    .addComponent(jLabel6)
                                    .addComponent(cboMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel51)
                                    .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel49)
                                    .addComponent(cboKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(btnBackSP, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSuaCTSP)
                    .addComponent(btnThemCTSP)
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnClearCTSP)
                        .addComponent(btnXuatfile, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnQuayLai))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(lbSoTrang2)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnLui2)
                            .addComponent(btnTien2))
                        .addContainerGap(12, Short.MAX_VALUE))))
        );

        jPanel18.setBackground(new java.awt.Color(204, 204, 204));
        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder("Lọc sản phẩm"));

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Giá tiền"));
        jPanel10.setToolTipText("");

        jLabel4.setText("Từ");

        txtTienMin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTienMinKeyReleased(evt);
            }
        });

        txtTienMax.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTienMaxKeyReleased(evt);
            }
        });

        jLabel7.setText("Đến");

        btnSearchGia.setBackground(new java.awt.Color(0, 153, 102));
        btnSearchGia.setForeground(new java.awt.Color(255, 255, 255));
        btnSearchGia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search.png"))); // NOI18N
        btnSearchGia.setText("Search");
        btnSearchGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchGiaActionPerformed(evt);
            }
        });

        jLabel8.setText("VND");

        jLabel9.setText("VND");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTienMin, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTienMax, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSearchGia, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtTienMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTienMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(btnSearchGia))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm kiếm"));
        jPanel11.setToolTipText("");

        cboLocMau.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
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

        cboLocKich.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
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

        cboLocChat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
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

        jLabel1.setText("Chất liệu");

        jLabel2.setText("Màu sắc");

        jLabel3.setText("Kích thước");

        btnReset.setBackground(new java.awt.Color(0, 153, 102));
        btnReset.setForeground(new java.awt.Color(255, 255, 255));
        btnReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/reset.png"))); // NOI18N
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboLocChat, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboLocMau, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboLocKich, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnReset)
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cboLocMau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cboLocKich, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                .addComponent(cboLocChat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addComponent(btnReset)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 1126, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(67, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(81, Short.MAX_VALUE))
        );

        add(jPanel2, "card2");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1213, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 778, Short.MAX_VALUE)
        );

        add(jPanel1, "card3");
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchGiaActionPerformed
        // TODO add your handling code here:
        if (validateCTSPGia()) {
            String ten = new SanPhamView().getTenSanPham();
            double giaMin = Double.parseDouble(txtTienMin.getText());
            double giaMax = Double.parseDouble(txtTienMax.getText());
            fillTableChiTietSanPham(serviceCTSP.getListGia(giaMin, giaMax, ten));
        }
    }//GEN-LAST:event_btnSearchGiaActionPerformed

    private void tblChiTietSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChiTietSanPhamMouseClicked
        // TODO add your handling code here:
        index = tblChiTietSanPham.getSelectedRow();
        deltailChiTietSanPham(index);
        cboChatLieu.setEnabled(false);
        cboMauSac.setEnabled(false);
        cboKichThuoc.setEnabled(false);
        btnThemCTSP.setEnabled(false);
    }//GEN-LAST:event_tblChiTietSanPhamMouseClicked

    private void btnQuayLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuayLaiActionPerformed
        // TODO add your handling code here:
        loadPageCTSP();
    }//GEN-LAST:event_btnQuayLaiActionPerformed

    private void btnThemCTSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemCTSPActionPerformed
        // TODO add your handling code here:      
        ChiTietSanPham ctsp = savesCTSP();
        String ten = new SanPhamView().getTenSanPham();
        ChatLieu cl = (ChatLieu) cbxChatLieu.getSelectedItem();
        String tenTimCL = cl.toString();
        MauSac ms = (MauSac) cbxMauSac.getSelectedItem();
        String tenTimMS = ms.toString();
        KichThuoc kt = (KichThuoc) cbxKichThuoc.getSelectedItem();
        String tenTimKT = kt.toString();
        if (validateCTSPs()) {
            if (serviceCTSP.checkTrungCTSP(tenTimCL, tenTimMS, tenTimKT, ten)) {
                JOptionPane.showMessageDialog(this, "Chi tiết sản phẩm đã tồn tại, vui lòng kiểm tra lại", "Message", 2);
                return;
            } else {
                if (serviceCTSP.them(ctsp) > 0) {
                    loadPageCTSP();
                    JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công");
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm sản phẩm thất bại");
                }
            }
        }
    }//GEN-LAST:event_btnThemCTSPActionPerformed

    private void btnSuaCTSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaCTSPActionPerformed
        // TODO add your handling code here:
        index = tblChiTietSanPham.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn dòng dữ liệu nào");
            return;
        } else {
            ChiTietSanPham ctsp = savesCTSP();
            String ma = tblChiTietSanPham.getValueAt(index, 0).toString();
            if (serviceCTSP.sua(ctsp, ma) > 0) {
                serviceCTSP.updateTrangThaiSoLuong();
                loadPageCTSP();
                JOptionPane.showMessageDialog(this, "Sửa chi tiết sản phẩm thành công");

            } else {
                JOptionPane.showMessageDialog(this, "Sửa chi tiết sản phẩm thất bại");
            }
        }
//        String ten = new SanPhamView().getTenSanPham();
//        ChatLieu cl = (ChatLieu) cbxChatLieu.getSelectedItem();
//        String tenTimCL = cl.toString();
//        MauSac ms = (MauSac) cbxMauSac.getSelectedItem();
//        String tenTimMS = ms.toString();
//        KichThuoc kt = (KichThuoc) cbxKichThuoc.getSelectedItem();
//        String tenTimKT = kt.toString();
//        if (serviceCTSP.checkTrungCTSP(tenTimCL, tenTimMS, tenTimKT, ten)) {
//            JOptionPane.showMessageDialog(this, "Chi tiết sản phẩm đã tồn tại, vui lòng sửa lại", "Message", 2);
//            return;
//        } 


    }//GEN-LAST:event_btnSuaCTSPActionPerformed

    private void btnClearCTSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearCTSPActionPerformed
        // TODO add your handling code here:
        String tenPage = new SanPhamView().getTenSPs(null, new SanPhamView().getTenSanPham());
        fillTableChiTietSanPham(serviceCTSP.listPageCTSP(trangCTSP, tenPage));
        btnThemCTSP.setEnabled(true);
        cboChatLieu.setEnabled(true);
        cboMauSac.setEnabled(true);
        cboKichThuoc.setEnabled(true);
        txtSoLuong.setText("");
        txtGia.setText("");
        cboChatLieu.setSelectedIndex(0);
        cboMauSac.setSelectedIndex(0);
        cboKichThuoc.setSelectedIndex(0);
    }//GEN-LAST:event_btnClearCTSPActionPerformed

    private void btnLui2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLui2ActionPerformed
        // TODO add your handling code here:
        if (trangCTSP > 1) {
            trangCTSP--;
            String tenPage = new SanPhamView().getTenSPs(null, new SanPhamView().getTenSanPham());
            fillTableChiTietSanPham(serviceCTSP.listPageCTSP(trangCTSP, tenPage));
            lbSoTrang2.setText(trangCTSP + " of " + soTrangCTSP);
        }
    }//GEN-LAST:event_btnLui2ActionPerformed

    private void btnTien2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTien2ActionPerformed
        // TODO add your handling code here:
        if (trangCTSP < soTrangCTSP) {
            trangCTSP++;
            String tenPage = new SanPhamView().getTenSPs(null, new SanPhamView().getTenSanPham());
            fillTableChiTietSanPham(serviceCTSP.listPageCTSP(trangCTSP, tenPage));
            lbSoTrang2.setText(trangCTSP + " of " + soTrangCTSP);
        }
    }//GEN-LAST:event_btnTien2ActionPerformed

    private void txtTienMinKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienMinKeyReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_txtTienMinKeyReleased

    private void txtTienMaxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienMaxKeyReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_txtTienMaxKeyReleased

    private void btnXuatfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatfileActionPerformed
        // TODO add your handling code here:
        fillTableChiTietSanPham(serviceCTSP.getAll());
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
            cell1.setCellValue("Mã chi tiết SP");

            XSSFCell cell2 = a.createCell(1);
            cell2.setCellStyle(style);
            cell2.setCellValue("Số lượng");

            XSSFCell cell3 = a.createCell(2);
            cell3.setCellStyle(style);
            cell3.setCellValue("Giá");

            XSSFCell cell4 = a.createCell(3);
            cell4.setCellStyle(style);
            cell4.setCellValue("Tên SP");

            XSSFCell cell5 = a.createCell(4);
            cell5.setCellStyle(style);
            cell5.setCellValue("Chất liệu");

            XSSFCell cell6 = a.createCell(5);
            cell6.setCellStyle(style);
            cell6.setCellValue("Màu sắc");

            XSSFCell cell7 = a.createCell(6);
            cell7.setCellStyle(style);
            cell7.setCellValue("Kích thước");

            XSSFCell cell8 = a.createCell(7);
            cell8.setCellStyle(style);
            cell8.setCellValue("Trạng thái");

            for (int i = 0; i < tblChiTietSanPham.getRowCount(); i++) {
                try {
                    XSSFRow excelRow = excelsheet.createRow(i + 1);
                    for (int j = 0; j < tblChiTietSanPham.getColumnCount(); j++) {
                        XSSFCell excelCell = excelRow.createCell(j);
                        excelCell.setCellValue(tblChiTietSanPham.getValueAt(i, j).toString());
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
            loadPageCTSP();
        }
    }//GEN-LAST:event_btnXuatfileActionPerformed

    private void cboMauSacMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboMauSacMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cboMauSacMouseClicked

    private void btnBackSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackSPActionPerformed
        // TODO add your handling code here:
        jPanel2.removeAll();
        jPanel2.add(new SanPhamView());
        jPanel2.repaint();
        jPanel2.revalidate();
    }//GEN-LAST:event_btnBackSPActionPerformed

    private void cboLocMauMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboLocMauMouseClicked
        // TODO add your handling code here: 
    }//GEN-LAST:event_cboLocMauMouseClicked

    private void cboLocKichMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboLocKichMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cboLocKichMouseClicked

    private void cboLocChatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboLocChatMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cboLocChatMouseClicked

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

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
//        if (cboLocChat.getSelectedIndex() != -1 && cboLocMau.getSelectedIndex() != -1 && cboLocKich.getSelectedIndex() != -1) {
//            return;
//        }
        cboLocChat.setSelectedIndex(-1);
        cboLocMau.setSelectedIndex(-1);
        cboLocKich.setSelectedIndex(-1);
        cboLocChat.setSelectedItem("Tất cả");
        cboLocMau.setSelectedItem("Tất cả");
        cboLocKich.setSelectedItem("Tất cả");
        loadPageCTSP();

    }//GEN-LAST:event_btnResetActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBackSP;
    private javax.swing.JButton btnClearCTSP;
    private javax.swing.JButton btnLui2;
    private javax.swing.JButton btnQuayLai;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSearchGia;
    private javax.swing.JButton btnSuaCTSP;
    private javax.swing.JButton btnThemCTSP;
    private javax.swing.JButton btnTien2;
    private javax.swing.JButton btnXuatfile;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboChatLieu;
    private javax.swing.JComboBox<String> cboKichThuoc;
    private javax.swing.JComboBox<String> cboLocChat;
    private javax.swing.JComboBox<String> cboLocKich;
    private javax.swing.JComboBox<String> cboLocMau;
    private javax.swing.JComboBox<String> cboMaSP;
    private javax.swing.JComboBox<String> cboMauSac;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JLabel lbSoTrang2;
    private javax.swing.JRadioButton rdConhang1;
    private javax.swing.JRadioButton rdHethang1;
    private javax.swing.JTable tblChiTietSanPham;
    private javax.swing.JTextField txtGia;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTienMax;
    private javax.swing.JTextField txtTienMin;
    // End of variables declaration//GEN-END:variables
}
