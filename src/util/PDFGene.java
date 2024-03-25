/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

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
import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import model.HoaDon;
import model.HoaDonChiTiet;

/**
 *
 * @author ADMIN
 */
public class PDFGene {
     public void genPDF(List<HoaDonChiTiet> list, HoaDon hd, Double tong, Double tongCuoi, Double dua, Double tra) throws FileNotFoundException, IOException {
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Định dạng ngày giờ theo ý muốn
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd\nHH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        DecimalFormat df = new DecimalFormat("#,###");

        String path = "D:\\PRO1041_DuAn1\\PDF\\" + hd.getMaHoaDon() + ".pdf";
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
        document.setFont(font);

        Double sum = 0.0;
        float threecol = 190f;
        float twocol = 285f;
        float twocol150 = twocol + 150f;
        float twocolumnWidth[] = {twocol150, twocol};
        float onecolumnWidth[] = {twocol150};
        float fullwidth[] = {threecol * 3};
        float threecolWidth[] = {threecol, threecol, threecol};
        Paragraph onesp = new Paragraph("\n");

        Table table = new Table(twocolumnWidth);

        Barcode128 barcode = new Barcode128(pdfDocument);
        barcode.setCode(hd.getMaHoaDon());
        com.itextpdf.layout.element.Image img = new com.itextpdf.layout.element.Image(barcode.createFormXObject(Color.BLACK, Color.BLACK, pdfDocument)).setBorder(Border.NO_BORDER);

        table.addCell(new Cell().add("Invoice").setFontSize(20f).setBold().setBorder(Border.NO_BORDER));
        Table nestedtable = new Table(new float[]{twocol / 2, twocol / 2});
        nestedtable.addCell(getHeaderTextCell("Số hóa đơn: "));
        nestedtable.addCell(new Cell().add(img).setBorder(Border.NO_BORDER));
        nestedtable.addCell(getHeaderTextCell("Ngày: "));
        nestedtable.addCell(getHeaderTextCellValue(formattedDateTime));

        table.addCell(new Cell().add(nestedtable).setBorder(Border.NO_BORDER));

        Border gb = new SolidBorder(Color.GRAY, 1f);
        Table divider = new Table(fullwidth);
        divider.setBorder(gb);

        document.add(table);
        document.add(onesp);
        document.add(divider);
        document.add(onesp);

        Table twoColTable = new Table(twocolumnWidth);
        twoColTable.addCell(getBillingandCustomCell("Thông tin hóa đơn").setHeight(25f));
        twoColTable.addCell(getBillingandCustomCell("Thông tin khách hàng").setHeight(25f));
        twoColTable.addCell(getCell10fLeft("Cửa hàng", true));
        twoColTable.addCell(getCell10fLeft("Họ tên", true));
        twoColTable.addCell(getCell10fLeft("Adam Store", false));
        twoColTable.addCell(getCell10fLeft(hd.getKhachHang().getHoTen(), false));
        twoColTable.addCell(getCell10fLeft("Nhân viên", true));
        twoColTable.addCell(getCell10fLeft("Số điện thoại", true));
        twoColTable.addCell(getCell10fLeft(hd.getNhanVien().getHoTen(), false));
        twoColTable.addCell(getCell10fLeft(hd.getKhachHang().getSoDienThoai(), false));
        document.add(twoColTable);

        Table oneColTable = new Table(onecolumnWidth);
        oneColTable.addCell(getCell10fLeft("Địa chỉ", true));
        oneColTable.addCell(getCell10fLeft("Số 10 Láng Hạ, Quận Đống Đa, Thành phố Hà Nội", false));
        oneColTable.addCell(getCell10fLeft("Email", true));
        oneColTable.addCell(getCell10fLeft("AdamStores5ACE@gmail.com", false));
        document.add(oneColTable.setMarginBottom(10f));

        Table tableDevider = new Table(fullwidth);
        Border dgb = new DashedBorder(Color.GRAY, 0.5f);
        document.add(tableDevider.setBorder(dgb));

        Paragraph productPara = new Paragraph("Sản phẩm");
        document.add(productPara.setFont(font).setBold());

        Table threeColTable = new Table(threecolWidth);
        threeColTable.setBackgroundColor(Color.BLACK, 0.7f);
        threeColTable.addCell(new Cell().add("Tên").setBold().setFontColor(Color.WHITE).setBorder(Border.NO_BORDER));
        threeColTable.addCell(new Cell().add("Số lượng").setBold().setTextAlignment(TextAlignment.CENTER).setFontColor(Color.WHITE).setBorder(Border.NO_BORDER));
        threeColTable.addCell(new Cell().add("Giá").setBold().setTextAlignment(TextAlignment.RIGHT).setFontColor(Color.WHITE).setBorder(Border.NO_BORDER)).setMarginRight(10f);
        document.add(threeColTable);

        Table threeColTable2 = new Table(threecolWidth);
        threeColTable2.setFont(font);

        for (HoaDonChiTiet hdct : list) {
            Double total = hdct.getSoLuong() * hdct.getDonGia();
            sum += total;
            threeColTable2.addCell(new Cell().add(hdct.getCtsp().toString2()).setBorder(Border.NO_BORDER)).setMarginLeft(10f);
            threeColTable2.addCell(new Cell().add(String.valueOf(hdct.getSoLuong())).setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER));
            threeColTable2.addCell(new Cell().add(df.format(total) + " VNĐ").setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER)).setMarginRight(10f);
        }
        document.add(threeColTable2.setFontSize(10f).setMarginBottom(20f));

        float onetwo[] = {threecol + 125f, threecol * 2 + 35f};
        Table threeColTable3 = new Table(onetwo);
        threeColTable3.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
        threeColTable3.addCell(new Cell().add("").setBorder(Border.NO_BORDER).setBorderTop(dgb));
        document.add(threeColTable3.setBorder(Border.NO_BORDER));

        Table threeColTable4 = new Table(threecolWidth);
//        Double mucGiam;
//        if (hd.getVoucher() == null) {
//            mucGiam = 0.0;
//        } else {
//            mucGiam = Double.parseDouble(hd.getVoucher().getMucGiamGia()) / 100;
//        }
        threeColTable4.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
        threeColTable4.addCell(new Cell().add("Tiền hàng").setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER));
        threeColTable4.addCell(new Cell().add(df.format(tong) + " VNĐ").setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER));

        if (tong - tongCuoi != 0.0) {
            threeColTable4.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
            threeColTable4.addCell(new Cell().add("Giảm giá").setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER));
            threeColTable4.addCell(new Cell().add(df.format(tong - tongCuoi) + " VNĐ").setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER));
            threeColTable4.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
            threeColTable4.addCell(new Cell().add("Tổng tiền").setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER));
            threeColTable4.addCell(new Cell().add(df.format(tongCuoi) + " VNĐ").setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER));
        }
//        Double sumSauKM = sum * (1 - mucGiam);
        document.add(threeColTable4.setMargin(10f));

        Table threeColTable5 = new Table(onetwo);
        threeColTable5.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
        threeColTable5.addCell(new Cell().add("").setBorder(Border.NO_BORDER).setBorderTop(dgb));
        document.add(threeColTable5.setBorder(Border.NO_BORDER));

        Table threeColTable6 = new Table(threecolWidth);
        threeColTable6.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
        threeColTable6.addCell(new Cell().add("Tiền khách đưa").setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER));
        threeColTable6.addCell(new Cell().add(df.format(dua) + " VNĐ").setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER));

//        Double sumSauKM = sum * (1 - mucGiam);
        threeColTable6.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
        threeColTable6.addCell(new Cell().add("Tiền thừa").setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER));
        threeColTable6.addCell(new Cell().add(df.format(tra) + " VNĐ").setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER));
        document.add(threeColTable6.setMargin(10f));

        document.add(tableDevider.setBorder(dgb));
        document.add(new Paragraph("\n"));
        document.add(divider.setBorder(new SolidBorder(Color.GRAY, 1)).setMarginBottom(15f));
        document.add(new Paragraph("Điều khoản và dịch vụ").setBold().setFont(font).setFontSize(15f));
        document.add(new Paragraph("1. Người bán không chịu trách nhiệm với bất kỳ tổn thất trực tiếp hay gián tiếp nào do người mua gây ra.").setFont(font));
        document.add(new Paragraph("2. Thời hạn đổi sản phẩm do lỗi bên phía người bán là bảy (7) ngày kể từ ngày mua.").setFont(font));

        document.close();

        try {
            File file = new File(path);

            if (file.exists()) {
                Desktop desktop = Desktop.getDesktop();
                desktop.open(file);
                try {
                    Robot robot = new Robot();

                    robot.delay(3000);

                    robot.keyPress(KeyEvent.VK_CONTROL);

                    robot.keyPress(KeyEvent.VK_P);

                    robot.keyRelease(KeyEvent.VK_CONTROL);

                    robot.keyRelease(KeyEvent.VK_P);
                } catch (AWTException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("File không tồn tại");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String giuSo(String x) {
        String so = x.replaceAll("[^0-9]", "");
        return so;
    }

    public static Cell getHeaderTextCell(String textValue) {
        return new Cell().add(textValue).setBold().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT);
    }

    public static Cell getHeaderTextCellValue(String textValue) {
        return new Cell().add(textValue).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT);
    }

    public static Cell getBillingandCustomCell(String textValue) {
        return new Cell().add(textValue).setFontSize(12f).setBold().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT);
    }

    public static Cell getCell10fLeft(String textValue, Boolean isBold) {
        Cell myCell = new Cell().add(textValue).setFontSize(10f).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT);
        return isBold ? myCell.setBold() : myCell;
    }
}
