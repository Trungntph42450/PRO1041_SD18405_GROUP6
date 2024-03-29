/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import com.itextpdf.barcodes.Barcode128;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.DashedBorder;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.PasswordAuthentication;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.HoaDon;
import service.servicImp.ThongKeSoLuongServiceImp;

import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.HoaDonChiTiet;
import model.HoaDonChiTiet;
import org.apache.poi.examples.xssf.usermodel.LineChart;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.data.time.SimpleTimePeriod;
import repository.ThongKeDoanhThuRepository;
import repository.ThongKeSoLuongRepository;
import util.PDFGene;
import static util.PDFGene2.getBillingandCustomCell;
import static util.PDFGene2.getHeaderTextCell;
import static util.PDFGene2.getHeaderTextCellValue;
import static view.ChiTietSanPhamView.createStyleForHeader;

/**
 *
 * @author Admin
 */
public class ThongKeSoLuongView extends javax.swing.JPanel {

    DefaultTableModel defaultTableModel = new DefaultTableModel();
    ThongKeSoLuongServiceImp service = new ThongKeSoLuongServiceImp();
    ThongKeSoLuongRepository repo = new ThongKeSoLuongRepository();

    /**
     * Creates new form ThongKeView
     */
    public ThongKeSoLuongView() {
        initComponents();
        this.setSize(1300, 755);
        setDataToChart(panelChartHoaDon);
        setDataToChart2(panelTopSP);
    }

    public void setDataToChart(JPanel panelHoaDon) {
        List<HoaDonChiTiet> listCthd = service.getListBieuDoHD();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        if (txtNgayBd.getCalendar() == null || txtNgayKT.getCalendar() == null) {
            if (listCthd != null) {
                for (HoaDonChiTiet chiTietHoaDon : listCthd) {
                    dataset.addValue(chiTietHoaDon.getHD().getSoLuongHoaDon(), "Hóa đơn",
                            chiTietHoaDon.getHD().getNgayTao());
                }
            }
            JFreeChart lineChart = ChartFactory.createLineChart("Biểu đồ thống kê tổng số hóa đơn".toUpperCase(),
                    "Ngày", "Hóa đơn", dataset, PlotOrientation.VERTICAL,
                    true, true, false);
            ChartPanel chartPanel = new ChartPanel(lineChart);
            chartPanel.setPreferredSize(new Dimension(859, 236));
            panelHoaDon.removeAll();
            panelHoaDon.setLayout(new CardLayout());
            panelHoaDon.add(chartPanel);
            panelHoaDon.validate();
            panelHoaDon.repaint();
            try {
                final ChartRenderingInfo info1 = new ChartRenderingInfo(new StandardEntityCollection());
                final File file1 = new File("C:\\ChartImage\\ChartHD.png");
                ChartUtilities.saveChartAsPNG(file1, lineChart, 859, 236, info1);
            } catch (Exception e) {
            }
        }
    }

    public void setDataToChart2(JPanel panelTopSP) {
        List<HoaDonChiTiet> listTop = service.getListBieuDoTopSP();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        if (listTop != null) {
            for (HoaDonChiTiet chiTietHoaDon : listTop) {
                dataset.addValue(chiTietHoaDon.getCtsp().getSanPham().getTongSP(), "Tổng sản phẩm",
                        chiTietHoaDon.getCtsp().getSanPham().getTenSanPham());
            }
        }
        JFreeChart barChart = ChartFactory.createBarChart("Top sản phẩm bán chạy nhất".toUpperCase(),
                "Sản phẩm", "Số lượng bán", dataset, PlotOrientation.VERTICAL,
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
            final File file2 = new File("C:\\ChartImage\\ChartTop.png");
            ChartUtilities.saveChartAsPNG(file2, barChart, 859, 236, info2);
        } catch (Exception e) {
        }
    }

    public void genPDF() throws FileNotFoundException, IOException {
        setDataToChart(panelChartHoaDon);
        setDataToChart2(panelTopSP);
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

        String path = "C:\\PDF\\" + "Thongkesoluong" + ".pdf";
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

        Border gb = new SolidBorder(Color.GRAY, 1f);
        Table divider = new Table(fullwidth);
        divider.setBorder(gb);

        document.add(table);
        document.add(onesp);
        document.add(onesp);
        document.add(onesp);
        document.add(divider);
        document.add(onesp);

        Date ngayBD = txtNgayBd.getDate();
        Date ngayKT = txtNgayKT.getDate();
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

        String imgPath2 = "C:\\ChartImage\\ChartHD.png";
        ImageData imgData2 = ImageDataFactory.create(imgPath2);
        Image img3 = new Image(imgData2);
        img3.setHeight(250);
        img3.setWidth(500);
        img3.setFixedPosition(60, 300f);
        document.add(img3);

        String imgPath3 = "C:\\ChartImage\\ChartTop.png";
        ImageData imgData3 = ImageDataFactory.create(imgPath3);
        Image img4 = new Image(imgData3);
        img4.setHeight(250);
        img4.setWidth(500);
        img4.setFixedPosition(60, 0f);
        document.add(img4);

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

        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNgayBd = new org.netbeans.modules.form.InvalidComponent();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNgayKT = new org.netbeans.modules.form.InvalidComponent();
        btnXuatPDF = new javax.swing.JToggleButton();
        btnTimKiem = new javax.swing.JToggleButton();
        btnGuiEmail = new javax.swing.JToggleButton();
        panelChartHoaDon = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        panelTopSP = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("THỐNG KÊ SỐ ĐƠN HÀNG");

        jLabel2.setText("Từ ");

        jLabel3.setText("Đến");

        btnXuatPDF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/pdf.png"))); // NOI18N
        btnXuatPDF.setText("Xuất PDF");
        btnXuatPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatPDFActionPerformed(evt);
            }
        });

        btnTimKiem.setBackground(new java.awt.Color(0, 153, 102));
        btnTimKiem.setForeground(new java.awt.Color(255, 255, 255));
        btnTimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search.png"))); // NOI18N
        btnTimKiem.setText("Tìm Kiếm");
        btnTimKiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTimKiemMouseClicked(evt);
            }
        });
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        btnGuiEmail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/new (1).png"))); // NOI18N
        btnGuiEmail.setText("Gửi Email");
        btnGuiEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuiEmailActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addGap(76, 76, 76)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNgayBd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(txtNgayKT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnTimKiem)
                .addGap(15, 15, 15)
                .addComponent(btnGuiEmail)
                .addGap(15, 15, 15)
                .addComponent(btnXuatPDF)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnXuatPDF)
                        .addComponent(btnTimKiem)
                        .addComponent(btnGuiEmail))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txtNgayBd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtNgayKT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        panelChartHoaDon.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelChartHoaDon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                panelChartHoaDonKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout panelChartHoaDonLayout = new javax.swing.GroupLayout(panelChartHoaDon);
        panelChartHoaDon.setLayout(panelChartHoaDonLayout);
        panelChartHoaDonLayout.setHorizontalGroup(
            panelChartHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelChartHoaDonLayout.setVerticalGroup(
            panelChartHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 283, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelChartHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelChartHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        panelTopSP.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout panelTopSPLayout = new javax.swing.GroupLayout(panelTopSP);
        panelTopSP.setLayout(panelTopSPLayout);
        panelTopSPLayout.setHorizontalGroup(
            panelTopSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelTopSPLayout.setVerticalGroup(
            panelTopSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 269, Short.MAX_VALUE)
        );

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("TOP 5 SẢN PHẨM BÁN CHẠY NHẤT");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel5)
                .addContainerGap(518, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(panelTopSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(panelTopSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnXuatPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatPDFActionPerformed
        try {
            // TODO add your handling code here:
            genPDF();
            JOptionPane.showMessageDialog(this, "Xuất PDF thành công");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Xuất PDF không thành công");
            Logger.getLogger(ThongKeSoLuongView.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnXuatPDFActionPerformed

    private void btnTimKiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTimKiemMouseClicked
        // TODO add your handling code here:
        ThongKeSoLuongRepository repo = new ThongKeSoLuongRepository();
        Date ngayBd = txtNgayBd.getDate();
        Date ngayKt = txtNgayKT.getDate();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        if (ngayBd == null || ngayKt == null) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn ngày cần tìm kiếm");
            return;
        } else {
            if (txtNgayKT.getCalendar().before(txtNgayBd.getCalendar())) {
                JOptionPane.showMessageDialog(this, "Ngày kết thúc không hợp lệ");
                return;
            } else {
                List<HoaDonChiTiet> listTKBD = service.getListTKBieuDoHD(txtNgayBd.getDate(), txtNgayKT.getDate());
                if (listTKBD != null) {
                    for (HoaDonChiTiet chiTietHoaDon : listTKBD) {
                        dataset.addValue(chiTietHoaDon.getHD().getSoLuongHoaDon(), "Hóa đơn",
                                chiTietHoaDon.getHD().getNgayTao());
                    }
                    JFreeChart lineChart = ChartFactory.createLineChart("Biểu đồ thống kê tổng số hóa đơn".toUpperCase(),
                            "Ngày", "Hóa đơn", dataset, PlotOrientation.VERTICAL,
                            true, true, false);
                    ChartPanel chartPanel = new ChartPanel(lineChart);
                    chartPanel.setPreferredSize(new Dimension(650, 236));
                    panelChartHoaDon.removeAll();
                    panelChartHoaDon.setLayout(new CardLayout());
                    panelChartHoaDon.add(chartPanel);
                    panelChartHoaDon.validate();
                    panelChartHoaDon.repaint();
                    try {
                        final ChartRenderingInfo info1 = new ChartRenderingInfo(new StandardEntityCollection());
                        final File file1 = new File("F:\\FPT Polytechnic\\DA1\\PRO1041_DuAn1\\ChartImage\\ChartHD.png");
                        ChartUtilities.saveChartAsPNG(file1, lineChart, 859, 236, info1);
                    } catch (Exception e) {
                    }
                }
            }
        }
    }//GEN-LAST:event_btnTimKiemMouseClicked

    private void panelChartHoaDonKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_panelChartHoaDonKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_panelChartHoaDonKeyReleased

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void btnGuiEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuiEmailActionPerformed
        // TODO add your handling code here:
        SendEmailView sendEmail = new SendEmailView();
        sendEmail.setVisible(true);

    }//GEN-LAST:event_btnGuiEmailActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btnGuiEmail;
    private javax.swing.JToggleButton btnTimKiem;
    private javax.swing.JToggleButton btnXuatPDF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel panelChartHoaDon;
    private javax.swing.JPanel panelTopSP;
    private org.netbeans.modules.form.InvalidComponent txtNgayBd;
    private org.netbeans.modules.form.InvalidComponent txtNgayKT;
    // End of variables declaration//GEN-END:variables
}
