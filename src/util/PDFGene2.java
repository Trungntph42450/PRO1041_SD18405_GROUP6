///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package util;
//
//import com.itextpdf.barcodes.Barcode128;
//import com.itextpdf.io.font.PdfEncodings;
//import com.itextpdf.io.image.ImageData;
//import com.itextpdf.io.image.ImageDataFactory;
//import com.itextpdf.kernel.color.Color;
//import com.itextpdf.kernel.font.PdfFont;
//import com.itextpdf.kernel.font.PdfFontFactory;
//import com.itextpdf.kernel.geom.PageSize;
//import com.itextpdf.kernel.pdf.PdfDocument;
//import com.itextpdf.kernel.pdf.PdfWriter;
//import com.itextpdf.layout.Document;
//import com.itextpdf.layout.border.Border;
//import com.itextpdf.layout.border.DashedBorder;
//import com.itextpdf.layout.border.SolidBorder;
//import com.itextpdf.layout.element.Cell;
//import com.itextpdf.layout.element.Image;
//import com.itextpdf.layout.element.Paragraph;
//import com.itextpdf.layout.element.Table;
//import com.itextpdf.layout.element.Text;
//import com.itextpdf.layout.property.TextAlignment;
//import java.awt.AWTException;
//import java.awt.Desktop;
//import java.awt.Robot;
//import java.awt.event.KeyEvent;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.text.DecimalFormat;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.List;
//import model.HoaDonChiTiet;
//
///**
// *
// * @author ADMIN
// */
//public class PDFGene2 {
//     public void genPDF(List<DoiHangChiTiet> listDH, List<HoaDonChiTiet> listHD1, List<HoaDonChiTiet> listHD2, DoiHang dh, Double tong, String tra, String dua, String thua) throws FileNotFoundException, IOException {
//        LocalDateTime currentDateTime = LocalDateTime.now();
//
//        // Định dạng ngày giờ theo ý muốn
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd\nHH:mm:ss");
//        String formattedDateTime = currentDateTime.format(formatter);
//        DecimalFormat df = new DecimalFormat("#,###");
//
//        String path = "D:\\PRO1041_DuAn1\\PDF\\" + dh.getMaDoiHang() + ".pdf";
//        PdfWriter pdfWriter = new PdfWriter(path);
//        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
//        pdfDocument.setDefaultPageSize(PageSize.A4);
//        try (Document document = new Document(pdfDocument)) {
//            String imgPath = "src\\img\\logo.png";
//            ImageData imgData = ImageDataFactory.create(imgPath);
//            Image img2 = new Image(imgData);
//            img2.setFixedPosition(10, 710);
//            img2.setOpacity(0.2f);
//            document.add(img2);
//
//            String fontPath = "C:\\Windows\\Fonts\\Arial.ttf";
//            PdfFont font = PdfFontFactory.createFont(fontPath, PdfEncodings.IDENTITY_H);
//            document.setFont(font);
//
//            Double sum = 0.0;
//            float he = (190f * 2f / 3f);
//            float threecol = 190f;
//            float twocol = 285f;
//            float twocol150 = twocol + 150f;
//            float twocolumnWidth[] = {twocol150, twocol};
//            float onecolumnWidth[] = {twocol150};
//            float fullwidth[] = {threecol * 3};
//            float threecolWidth[] = {threecol, threecol, threecol};
//            float fourcolWidth[] = {he + 60f, he - 60f, threecol, he};
//            Paragraph onesp = new Paragraph("\n");
//
//            Table table = new Table(twocolumnWidth);
//
//            Barcode128 barcode = new Barcode128(pdfDocument);
//            barcode.setCode(dh.getMaDoiHang());
//            com.itextpdf.layout.element.Image img = new com.itextpdf.layout.element.Image(barcode.createFormXObject(Color.BLACK, Color.BLACK, pdfDocument)).setBorder(Border.NO_BORDER);
//
//            table.addCell(new Cell().add("Exchange Receipt").setFontSize(20f).setBold().setBorder(Border.NO_BORDER));
//            Table nestedtable = new Table(new float[]{twocol / 2, twocol / 2});
//            nestedtable.addCell(getHeaderTextCell("Số phiếu đổi hàng: "));
//            nestedtable.addCell(new Cell().add(img).setBorder(Border.NO_BORDER));
//            nestedtable.addCell(getHeaderTextCell("Ngày: "));
//            nestedtable.addCell(getHeaderTextCellValue(formattedDateTime));
//
//            table.addCell(new Cell().add(nestedtable).setBorder(Border.NO_BORDER));
//
//            Border gb = new SolidBorder(Color.GRAY, 1f);
//            Table divider = new Table(fullwidth);
//            divider.setBorder(gb);
//
//            document.add(table);
//            document.add(onesp);
//            document.add(divider);
//            document.add(onesp);
//
//            Table twoColTable = new Table(twocolumnWidth);
//            twoColTable.addCell(getBillingandCustomCell("Thông tin hóa đơn").setHeight(25f));
//            twoColTable.addCell(getBillingandCustomCell("Thông tin khách hàng").setHeight(25f));
//            twoColTable.addCell(getCell10fLeft("Cửa hàng", true));
//            twoColTable.addCell(getCell10fLeft("Họ tên", true));
//            twoColTable.addCell(getCell10fLeft("Adam Store", false));
//            twoColTable.addCell(getCell10fLeft(dh.getHoaDon().getKhachHang().getHoTen(), false));
//            twoColTable.addCell(getCell10fLeft("Nhân viên", true));
//            twoColTable.addCell(getCell10fLeft("Số điện thoại", true));
//            twoColTable.addCell(getCell10fLeft(dh.getNhanVien().getHoTen(), false));
//            twoColTable.addCell(getCell10fLeft(dh.getHoaDon().getKhachHang().getSoDienThoai(), false));
//            document.add(twoColTable);
//
//            Table oneColTable = new Table(onecolumnWidth);
//            oneColTable.addCell(getCell10fLeft("Địa chỉ", true));
//            oneColTable.addCell(getCell10fLeft("Số 10 Láng Hạ, Quận Đống Đa, Thành phố Hà Nội", false));
//            oneColTable.addCell(getCell10fLeft("Email", true));
//            oneColTable.addCell(getCell10fLeft("AdamStores5ACE@gmail.com", false));
//            document.add(oneColTable.setMarginBottom(10f));
//
//            Table tableDevider = new Table(fullwidth);
//            Border dgb = new DashedBorder(Color.GRAY, 0.5f);
//            document.add(tableDevider.setBorder(dgb));
//
//            Paragraph productPara = new Paragraph("Sản phẩm");
//            document.add(productPara.setFont(font).setBold());
//
//            Table threeColTable = new Table(fourcolWidth);
//            threeColTable.setBackgroundColor(Color.BLACK, 0.7f);
//            threeColTable.addCell(new Cell().add("Tên").setBold().setFontColor(Color.WHITE).setBorder(Border.NO_BORDER));
//            threeColTable.addCell(new Cell().add("Số lượng").setBold().setTextAlignment(TextAlignment.CENTER).setFontColor(Color.WHITE).setBorder(Border.NO_BORDER));
//            threeColTable.addCell(new Cell().add("Lý do").setBold().setTextAlignment(TextAlignment.RIGHT).setFontColor(Color.WHITE).setBorder(Border.NO_BORDER));
//            threeColTable.addCell(new Cell().add("Giá").setBold().setTextAlignment(TextAlignment.RIGHT).setFontColor(Color.WHITE).setBorder(Border.NO_BORDER)).setMarginRight(10f);
//            document.add(threeColTable);
//
//            Table threeColTable2 = new Table(fourcolWidth);
//            threeColTable2.setFont(font);
//
//            for (int i = 0; i < listHD1.size(); i++) {
//                HoaDonChiTiet x = listHD1.get(i);
//                HoaDonChiTiet y = listHD2.get(i);
//                Double total;
//                if (x.getCtsp().getMaChiTietSanPham().equals(y.getCtsp().getMaChiTietSanPham()) && x.getSoLuong() == y.getSoLuong()) {
//                    total = x.getSoLuong() * x.getDonGia();
//                    sum += total;
//                    threeColTable2.addCell(new Cell().add(x.getCtsp().toString2()).setBorder(Border.NO_BORDER)).setMarginLeft(10f);
//                    threeColTable2.addCell(new Cell().add(String.valueOf(x.getSoLuong())).setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER));
//                    threeColTable2.addCell(new Cell().add("").setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER));
//                    threeColTable2.addCell(new Cell().add(df.format(total) + " VNĐ").setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER)).setMarginRight(10f);
//                } else {
//                    total = y.getSoLuong() * x.getDonGia();
//                    sum += total;
//                    threeColTable2.addCell(new Cell().add(x.getCtsp().toString2()).setBorder(Border.NO_BORDER)).setMarginLeft(10f);
//                    threeColTable2.addCell(new Cell().add(String.valueOf(y.getSoLuong())).setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER));
//                    threeColTable2.addCell(new Cell().add("").setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER));
//                    threeColTable2.addCell(new Cell().add(df.format(total) + " VNĐ").setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER)).setMarginRight(10f);
//                    for (DoiHangChiTiet list : listDH) {
//                        System.out.println(x.getSoLuong());
//                        System.out.println(y.getSoLuong());
//                        if (list.getHoaDonChiTiet().getCtsp().getMaChiTietSanPham().equals(x.getCtsp().getMaChiTietSanPham())) {
//                            Paragraph p = new Paragraph();
//                            Text txt1 = new Text(x.getCtsp().toString2()).setUnderline(1.5f, 3.5f);
//                            Text txt2 = new Text(list.getChiTietSanPham().toString2());
//                            p.add(txt1);
//                            p.add(" ");
//                            p.add(txt2);
//                            threeColTable2.addCell(new Cell().add(p).setBorder(Border.NO_BORDER)).setMarginLeft(10f);
//
//                            Paragraph p2 = new Paragraph();
//                            Text txt3 = new Text(String.valueOf(x.getSoLuong())).setUnderline(1.5f, 3.5f);
//                            Text txt4 = new Text(String.valueOf(list.getSoLuong()));
//                            p2.add(txt3);
//                            p2.add(" ");
//                            p2.add(txt4);
//                            threeColTable2.addCell(new Cell().add(p2).setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER));
//                            total = list.getSoLuong() * list.getChiTietSanPham().getGia();
//                            sum += total;
//                            String mota = list.getMoTa();
//                            System.out.println(mota);
//                            threeColTable2.addCell(new Cell().add(mota).setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER));
//                            threeColTable2.addCell(new Cell().add(df.format(total) + " VNĐ").setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER)).setMarginRight(10f);
//                        }
//                    }
//                }
//            }
//
////            for (HoaDonChiTiet hdct : listHD1) {
////                Double total = hdct.getSoLuong() * hdct.getDonGia();
////                sum += total;
////                for (HoaDonChiTiet hdct2 : listHD2) {
////                    if (hdct.getCtsp() == hdct2.getCtsp() && hdct.getSoLuong() == hdct2.getSoLuong()) {
////                        threeColTable2.addCell(new Cell().add(hdct.getCtsp().toString()).setBorder(Border.NO_BORDER)).setMarginLeft(10f);
////                        threeColTable2.addCell(new Cell().add(String.valueOf(hdct.getSoLuong())).setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER));
////                        threeColTable2.addCell(new Cell().add(df.format(total) + " VNĐ").setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER)).setMarginRight(10f);
////                    } else {
////                        threeColTable2.addCell(new Cell().add(hdct.getCtsp().toString()).setBorder(Border.NO_BORDER)).setMarginLeft(10f).setUnderline(1.5f, 3.5f);
////                        threeColTable2.addCell(new Cell().add(String.valueOf(hdct.getSoLuong())).setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER));
////                        threeColTable2.addCell(new Cell().add(df.format(total) + " VNĐ").setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER)).setMarginRight(10f);
////                        break;
////                    }
////                }
////            }
//            document.add(threeColTable2.setFontSize(10f).setMarginBottom(20f));
//
//            float onetwo[] = {threecol + 125f, threecol * 2 + 35f};
//            Table threeColTable3 = new Table(onetwo);
//            threeColTable3.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
//            threeColTable3.addCell(new Cell().add("").setBorder(Border.NO_BORDER).setBorderTop(dgb));
//            document.add(threeColTable3.setBorder(Border.NO_BORDER));
//
//            Table threeColTable4 = new Table(threecolWidth);
////        Double mucGiam;
////        if (hd.getVoucher() == null) {
////            mucGiam = 0.0;
////        } else {
////            mucGiam = Double.parseDouble(hd.getVoucher().getMucGiamGia()) / 100;
////        }
//            threeColTable4.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
//            threeColTable4.addCell(new Cell().add("Tiền hàng").setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER));
//            threeColTable4.addCell(new Cell().add(df.format(sum) + " VNĐ").setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER));
//
////        Double sumSauKM = sum * (1 - mucGiam);
//            threeColTable4.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
//            threeColTable4.addCell(new Cell().add("Khách trả thêm").setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER));
//            threeColTable4.addCell(new Cell().add(tra + " VNĐ").setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER));
//            document.add(threeColTable4.setMargin(10f));
//
//            if (!tra.equals("0")) {
//
//                Table threeColTable5 = new Table(onetwo);
//                threeColTable5.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
//                threeColTable5.addCell(new Cell().add("").setBorder(Border.NO_BORDER).setBorderTop(dgb));
//                document.add(threeColTable5.setBorder(Border.NO_BORDER));
//                
//                Table threeColTable6 = new Table(threecolWidth);
//                threeColTable6.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
//                threeColTable6.addCell(new Cell().add("Tiền khách đưa").setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER));
//                threeColTable6.addCell(new Cell().add(dua + " VNĐ").setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER));
//
//                threeColTable6.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
//                threeColTable6.addCell(new Cell().add("Tiền thừa").setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER));
//                threeColTable6.addCell(new Cell().add(thua + " VNĐ").setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER));
//                document.add(threeColTable6.setMargin(10f));
//            }
////        Double sumSauKM = sum * (1 - mucGiam);
//
//            document.add(tableDevider.setBorder(dgb));
//            document.add(new Paragraph("\n"));
//            document.add(divider.setBorder(new SolidBorder(Color.GRAY, 1)).setMarginBottom(15f));
//            document.add(new Paragraph("Điều khoản và dịch vụ").setBold().setFont(font).setFontSize(15f));
//            document.add(new Paragraph("1. Người bán không chịu trách nhiệm với bất kỳ tổn thất trực tiếp hay gián tiếp nào do người mua gây ra.").setFont(font));
//            document.add(new Paragraph("2. Đơn hàng chỉ được đổi 1 lần").setFont(font));
//        }
//
//        try {
//            File file = new File(path);
//
//            if (file.exists()) {
//                Desktop desktop = Desktop.getDesktop();
//                desktop.open(file); 
//                try {
//                    Robot robot = new Robot();
//
//                    robot.delay(1000);
//
//                    robot.keyPress(KeyEvent.VK_CONTROL);
//
//                    robot.keyPress(KeyEvent.VK_P);
//
//                    robot.keyRelease(KeyEvent.VK_CONTROL);
//
//                    robot.keyRelease(KeyEvent.VK_P);
//                } catch (AWTException e) {
//                }
//            } else {
//                System.out.println("File không tồn tại");
//            }
//        } catch (IOException e) {
//        }
//    }
//
//    public String giuSo(String x) {
//        String so = x.replaceAll("[^0-9]", "");
//        return so;
//    }
//
//    public static Cell getHeaderTextCell(String textValue) {
//        return new Cell().add(textValue).setBold().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT);
//    }
//
//    public static Cell getHeaderTextCellValue(String textValue) {
//        return new Cell().add(textValue).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT);
//    }
//
//    public static Cell getBillingandCustomCell(String textValue) {
//        return new Cell().add(textValue).setFontSize(12f).setBold().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT);
//    }
//
//    public static Cell getCell10fLeft(String textValue, Boolean isBold) {
//        Cell myCell = new Cell().add(textValue).setFontSize(10f).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT);
//        return isBold ? myCell.setBold() : myCell;
//    }
//}
