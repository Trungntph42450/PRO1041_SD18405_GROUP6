/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import static com.itextpdf.kernel.pdf.PdfName.N;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import static java.awt.AWTEventMulticaster.add;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import model.HoaDonChiTiet;
import model.SanPham;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.data.xy.XYSeries;
import repository.ThongKeKhacRepository;
import repository.ThongKeSoLuongRepository;
import service.servicImp.ThongKeKhacServiceImp;
import util.DBConnect;
import static util.PDFGene2.getBillingandCustomCell;
import static util.PDFGene2.getHeaderTextCell;
import static util.PDFGene2.getHeaderTextCellValue;

/**
 *
 * @author Admin
 */
public class ThongKeKhacView extends javax.swing.JPanel {

    ThongKeKhacServiceImp service = new ThongKeKhacServiceImp();
    public static String tenSp;
    DefaultComboBoxModel<SanPham> cboSP = new DefaultComboBoxModel<>();
    private static final long serialVersionUID = 1L;

    /**
     * Creates new form ThongKeKhacView
     */
    public ThongKeKhacView() {
        initComponents();
        setDataToChart(panel1);
        loadCbo(service.getTenSP(tenSp));
        setDataToChart2(panel2);
    }

    public String getTenSP() {
        return tenSp;
    }

    public void loadCbo(List<SanPham> listTen) {
        cboSP.removeAllElements();
        for (SanPham sanPham : listTen) {
            cboSP.addElement(sanPham);
        }
        cboTenSP.setModel((ComboBoxModel) cboSP);
    }

    public void setDataToChart(JPanel panelTopSP) {
        List<HoaDonChiTiet> list1 = service.getList1();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        if (list1 != null) {
            for (HoaDonChiTiet chiTietHoaDon : list1) {
                dataset.addValue(chiTietHoaDon.getSoLuong() + chiTietHoaDon.getCtsp().getSoLuong(), "Tổng",
                        chiTietHoaDon.getCtsp().getSanPham().getTenSanPham());
                dataset.addValue(chiTietHoaDon.getSoLuong(), "Số bán được",
                        chiTietHoaDon.getCtsp().getSanPham().getTenSanPham());
                dataset.addValue(chiTietHoaDon.getCtsp().getSoLuong(), "Sản phẩm còn lại",
                        chiTietHoaDon.getCtsp().getSanPham().getTenSanPham());
            }
        }
        JFreeChart barChart = ChartFactory.createBarChart("Doanh thu sản phẩm".toUpperCase(),
                "Sản phẩm", "Số lượng", dataset, PlotOrientation.VERTICAL,
                false, true, false);
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(859, 236));
        panelTopSP.removeAll();
        panelTopSP.setLayout(new CardLayout());
        panelTopSP.add(chartPanel);
        panelTopSP.validate();
        panelTopSP.repaint();

        try {
            final ChartRenderingInfo info2 = new ChartRenderingInfo(new StandardEntityCollection());
            final File file2 = new File("C:\\ChartImage\\ChartDTSPchung.png");
            ChartUtilities.saveChartAsPNG(file2, barChart, 859, 236, info2);
        } catch (Exception e) {
        }
    }

    public void setDataToChart2(JPanel panel2) {
        SanPham sp = (SanPham) cboSP.getSelectedItem();
        tenSp = sp.toString();
        List<HoaDonChiTiet> list2 = service.getList2(tenSp);
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        if (list2 != null) {
            for (HoaDonChiTiet chiTietHoaDon : list2) {
                dataset.addValue(chiTietHoaDon.getSoLuong(), "Bán được",
                        chiTietHoaDon.getHD().getNgayTao());
                dataset.addValue(chiTietHoaDon.getCtsp().getSoLuong(), "Còn lại",
                        chiTietHoaDon.getHD().getNgayTao());
            }
        }
        JFreeChart lineChart = ChartFactory.createLineChart("Thống kê doanh thu từng sản phẩm".toUpperCase(),
                "Ngày", "Số lượng", dataset, PlotOrientation.VERTICAL,
                true, true, false);
        ChartPanel chartPanel = new ChartPanel(lineChart);
        chartPanel.setPreferredSize(new Dimension(859, 236));
        panel2.removeAll();
        panel2.setLayout(new CardLayout());
        panel2.add(chartPanel);
        panel2.validate();
        panel2.repaint();
    }

    public void genPDF() throws FileNotFoundException, IOException {
        setDataToChart(panel1);
        setDataToChart2(panel2);
        LocalDateTime currentDateTime = LocalDateTime.now();
        // Định dạng ngày giờ theo ý muốn
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd\nHH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        DecimalFormat df = new DecimalFormat("#,###");

        float twocol = 285f;
        Double sum = 0.0;
        float threecol = 190f;
        float twocol150 = twocol + 150f;
        float twocolumnWidth[] = {twocol150, twocol};
        float onecolumnWidth[] = {twocol150};
        float fullwidth[] = {threecol * 3};
        float threecolWidth[] = {threecol, threecol, threecol};
        Paragraph onesp = new Paragraph("\n");

        String path = "C:\\PDF\\" + "Thongkekhac" + ".pdf";
        PdfWriter pdfWriter = new PdfWriter(path);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        pdfDocument.setDefaultPageSize(PageSize.A4);
        Document document = new Document(pdfDocument);

        String imgPath = "src\\img\\logo.png";
        ImageData imgData = ImageDataFactory.create(imgPath);
        Image img2 = new Image(imgData);
        img2.setFixedPosition(10, 710);
        img2.setOpacity(0.2f);
        document.add(img2);
        String fontPath = "C:\\Windows\\Fonts\\Arial.ttf";
        PdfFont font = PdfFontFactory.createFont(fontPath, PdfEncodings.IDENTITY_H);

        Table table = new Table(twocolumnWidth);
        table.addCell(new Cell().add("").setFontSize(20f).setBold().setBorder(Border.NO_BORDER));

        Table nestedtable = new Table(new float[]{twocol / 2, twocol / 2});
        nestedtable.addCell(getHeaderTextCell("Ngày: "));
        nestedtable.addCell(getHeaderTextCellValue(formattedDateTime));

        table.addCell(new Cell().add(nestedtable).setBorder(Border.NO_BORDER));

        Border gb = new SolidBorder(com.itextpdf.kernel.color.Color.GRAY, 1f);
        Table divider = new Table(fullwidth);
        divider.setBorder(gb);

        document.add(table);
        document.add(onesp);
        document.add(onesp);
        document.add(onesp);
        document.add(divider);
        document.add(onesp);

        java.util.Date ngayBD = txtNgayBD.getDate();
        java.util.Date ngayKT = txtNgayKT.getDate();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        if (ngayBD != null && ngayKT != null) {
            String ngayBDDinhDang = dateFormat.format(ngayBD);
            String ngayKTDinhDang = dateFormat.format(ngayKT);

            Table twoColTable = new Table(twocolumnWidth);
            twoColTable.addCell(getBillingandCustomCell("Ngày bắt đầu: " + ngayBDDinhDang).setHeight(20f));
            twoColTable.addCell(getBillingandCustomCell("Ngày kết thúc: " + ngayKTDinhDang).setHeight(20f));
            twoColTable.setFont(font);
            document.add(twoColTable);
        }

        String imgPath2 = "C:\\ChartImage\\ChartDTSPchung.png";
        ImageData imgData2 = ImageDataFactory.create(imgPath2);
        Image img3 = new Image(imgData2);
        img3.setHeight(250);
        img3.setWidth(500);
        img3.setFixedPosition(60, 300f);
        document.add(img3);

        document.setFont(font);

        document.close();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        panel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnTimKiem = new javax.swing.JToggleButton();
        jPanel7 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        btnGmail = new javax.swing.JButton();
        jToggleButton1 = new javax.swing.JToggleButton();
        jPanel2 = new javax.swing.JPanel();
        panel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnTimKiem2 = new javax.swing.JToggleButton();
        cboTenSP = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(255, 204, 204));

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 782, Short.MAX_VALUE)
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 250, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Tìm kiếm:");

        jLabel2.setText("Từ");

        jLabel3.setText("Đến");

        btnTimKiem.setBackground(new java.awt.Color(0, 153, 102));
        btnTimKiem.setForeground(new java.awt.Color(255, 255, 255));
        btnTimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search.png"))); // NOI18N
        btnTimKiem.setText("Tìm kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 0, 0));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 29, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 16, Short.MAX_VALUE)
        );

        jLabel7.setText("Tổng số lượng");

        jLabel8.setText("Số sản phẩm bán được");

        jPanel4.setBackground(new java.awt.Color(0, 51, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel4.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 29, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 16, Short.MAX_VALUE)
        );

        jPanel6.setBackground(new java.awt.Color(0, 255, 0));
        jPanel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel6.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 29, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 16, Short.MAX_VALUE)
        );

        jLabel9.setText("Số sản phẩm còn lại");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addGap(173, 173, 173)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        btnGmail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/new (1).png"))); // NOI18N
        btnGmail.setText("Gửi Email");
        btnGmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGmailActionPerformed(evt);
            }
        });

        jToggleButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/pdf.png"))); // NOI18N
        jToggleButton1.setText("Xuất PDF");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addGap(146, 146, 146)
                        .addComponent(jLabel3)
                        .addGap(157, 157, 157)
                        .addComponent(btnTimKiem)
                        .addGap(21, 21, 21)
                        .addComponent(btnGmail)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jToggleButton1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2))
                    .addComponent(jLabel3)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnGmail)
                        .addComponent(jToggleButton1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
        panel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 782, Short.MAX_VALUE)
        );
        panel2Layout.setVerticalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 291, Short.MAX_VALUE)
        );

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Tìm kiếm:");

        jLabel5.setText("Từ");

        jLabel6.setText("Đến");

        btnTimKiem2.setBackground(new java.awt.Color(0, 153, 102));
        btnTimKiem2.setForeground(new java.awt.Color(255, 255, 255));
        btnTimKiem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search.png"))); // NOI18N
        btnTimKiem2.setText("Tìm kiếm");
        btnTimKiem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiem2ActionPerformed(evt);
            }
        });

        cboTenSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboTenSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTenSPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(panel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(cboTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addGap(146, 146, 146)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnTimKiem2)
                        .addGap(73, 73, 73))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(jLabel5)
                        .addComponent(cboTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(btnTimKiem2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        // TODO add your handling code here:
        if (txtNgayBD.getCalendar() == null || txtNgayKT.getCalendar() == null) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn ngày cần tìm kiếm");
            return;
        } else {
            if (txtNgayKT.getCalendar().before(txtNgayBD.getCalendar())) {
                JOptionPane.showMessageDialog(this, "Ngày kết thúc không hợp lệ");
                return;
            } else {
                java.util.Date ngayBd = txtNgayBD.getDate();
                java.util.Date ngayKt = txtNgayKT.getDate();
                DefaultCategoryDataset dataset = new DefaultCategoryDataset();
                List<HoaDonChiTiet> listTK1 = service.getListTK1(txtNgayBD.getDate(), txtNgayKT.getDate());
                if (listTK1 != null) {
                    for (HoaDonChiTiet chiTietHoaDon : listTK1) {
                        dataset.addValue(chiTietHoaDon.getSoLuong() + chiTietHoaDon.getCtsp().getSoLuong(), "Tổng sản phẩm",
                                chiTietHoaDon.getCtsp().getSanPham().getTenSanPham());
                        dataset.addValue(chiTietHoaDon.getSoLuong(), "Số bán được",
                                chiTietHoaDon.getCtsp().getSanPham().getTenSanPham());
                        dataset.addValue(chiTietHoaDon.getCtsp().getSoLuong(), "Sản phẩm còn lại",
                                chiTietHoaDon.getCtsp().getSanPham().getTenSanPham());
                    }
                }
                JFreeChart barChart = ChartFactory.createBarChart("Doanh thu sản phẩm".toUpperCase(),
                        "Sản phẩm", "Số lượng", dataset, PlotOrientation.VERTICAL,
                        false, true, false);
                ChartPanel chartPanel = new ChartPanel(barChart);
                chartPanel.setPreferredSize(new Dimension(650, 236));
                panel1.removeAll();
                panel1.setLayout(new CardLayout());
                panel1.add(chartPanel);
                panel1.validate();
                panel1.repaint();
                try {
                    final ChartRenderingInfo info2 = new ChartRenderingInfo(new StandardEntityCollection());
                    final File file2 = new File("C:\\ChartImage\\ChartDTSPchung.png");
                    ChartUtilities.saveChartAsPNG(file2, barChart, 859, 236, info2);
                } catch (Exception e) {
                }
            }
        }
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void btnTimKiem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiem2ActionPerformed
        // TODO add your handling code here:
        if (txtNgayBD.getCalendar() == null || txtNgayKT.getCalendar() == null) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn ngày cần tìm kiếm");
            return;
        } else {
            if (txtNgayKT2.getCalendar().before(txtNgayBD2.getCalendar())) {
                JOptionPane.showMessageDialog(this, "Ngày kết thúc không hợp lệ");
                return;
            } else {
                java.util.Date ngayBd = txtNgayBD2.getDate();
                java.util.Date ngayKt = txtNgayKT2.getDate();
                DefaultCategoryDataset dataset = new DefaultCategoryDataset();
                List<HoaDonChiTiet> listTK2 = service.getListTK2(tenSp, ngayBd, ngayKt);
                if (listTK2 != null) {
                    for (HoaDonChiTiet chiTietHoaDon : listTK2) {
                        dataset.addValue(chiTietHoaDon.getSoLuong(), "Bán được",
                                chiTietHoaDon.getHD().getNgayTao());
                        dataset.addValue(chiTietHoaDon.getCtsp().getSoLuong(), "Còn lại",
                                chiTietHoaDon.getHD().getNgayTao());

                    }
                }
                JFreeChart lineChart = ChartFactory.createLineChart("Thống kê doanh thu từng sản phẩm".toUpperCase(),
                        "Ngày", "Số lượng", dataset, PlotOrientation.VERTICAL,
                        true, true, false);
                ChartPanel chartPanel = new ChartPanel(lineChart);
                chartPanel.setPreferredSize(new Dimension(859, 236));
                panel2.removeAll();
                panel2.setLayout(new CardLayout());
                panel2.add(chartPanel);
                panel2.validate();
                panel2.repaint();
            }
        }
    }//GEN-LAST:event_btnTimKiem2ActionPerformed

    private void cboTenSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTenSPActionPerformed
        // TODO add your handling code here:
        setDataToChart2(panel2);
    }//GEN-LAST:event_cboTenSPActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        try {
            // TODO add your handling code here:
            genPDF();
            JOptionPane.showMessageDialog(this, "Xuất PDF thành công");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Xuất PDF không thành công");
            Logger.getLogger(ThongKeSoLuongView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void btnGmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGmailActionPerformed
        // TODO add your handling code here:
        SendEmailView sendEmail = new SendEmailView();
        sendEmail.setVisible(true);
    }//GEN-LAST:event_btnGmailActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGmail;
    private javax.swing.JToggleButton btnTimKiem;
    private javax.swing.JToggleButton btnTimKiem2;
    private javax.swing.JComboBox<String> cboTenSP;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JPanel panel1;
    private javax.swing.JPanel panel2;
    // End of variables declaration//GEN-END:variables
}
