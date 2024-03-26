/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import model.HoaDon;
import model.KhachHang;
import model.NhanVien;
import util.DBConnect;

/**
 *
 * @author Nguyen Thanh Trung
 */
public class HoaDonRepository {

    PreparedStatement ps = null;
    Connection con = null;
    ResultSet rs = null;
    String sql = null;

    public String giuSo(String x) {
        String so = x.replaceAll("[^0-9]", "");
        return so;
    }

    public List<HoaDon> getHoaDonCho() {
        List<HoaDon> listHoaDon = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            sql = "Select HD.MaHoaDon,NV.MaNV,NV.HoTen,HD.NgayTao,HD.TrangThai\n"
                    + "From HoaDon HD Join NhanVien NV ON HD.MaNV=NV.MaNV where HD.TrangThai = N'Chờ thanh toán'";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien(rs.getString(2), rs.getString(3));
                HoaDon hd = new HoaDon(rs.getString(1), nv, rs.getDate(4), rs.getString(5));
                listHoaDon.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listHoaDon;
    }

    public HoaDon get1HoaDonCho(String maHD) {
        HoaDon hd = new HoaDon();
        try {
            con = DBConnect.getConnection();
            sql = "Select MaHoaDon,MaKH From HoaDon where MaHoaDon = ?";
            ps = con.prepareStatement(sql);
            ps.setObject(1, maHD);
            rs = ps.executeQuery();
            while (rs.next()) {
                KhachHang kh = new KhachHang(rs.getString(2));
                hd = new HoaDon(rs.getString(1), kh);
            }
            return hd;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int countHoaDon() {
        int tongHoaDon = 0;
        try {
            sql = "Select COUNT(*) From HoaDon";
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                tongHoaDon = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return tongHoaDon;
    }

    public List<HoaDon> getLSHoaDon() {
        List<HoaDon> listHD = new ArrayList<>();
        try {
            sql = "Select HD.MaHoaDon,NV.MaNV,KH.MaKH,HD.NgayTao,HD.TongTien, HD.TrangThai,HD.GhiChu\n"
                    + "From HoaDon HD Join NhanVien NV ON HD.MaNV=NV.MaNV Join KhachHang KH ON HD.MaKH = KH.MaKH\n"
                    + "where HD.trangThai like N'Đã thanh toán' or HD.trangThai like N'Đang đổi hàng' order by HD.TrangThai";
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien(rs.getString(2));
                KhachHang kh = new KhachHang(rs.getString(3));
                HoaDon hd = new HoaDon(rs.getString(1), nv, kh, rs.getDate(4), rs.getDouble(5),
                        rs.getString(6), rs.getString(7));
                listHD.add(hd);
            }
            return listHD;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<HoaDon> getList(String maHD, String maNV, String maKH) {
        List<HoaDon> listHD = new ArrayList<>();
        try {
            sql = "Select HD.MaHoaDon,NV.MaNV,KH.MaKH,HD.NgayTao,HD.TongTien, HD.TrangThai,HD.GhiChu\n"
                    + "From HoaDon HD Join NhanVien NV ON HD.MaNV=NV.MaNV Join KhachHang KH ON HD.MaKH = KH.MaKH "
                    + "Where HD.MaHoaDon LIKE ? or NV.MaNV LIKE ? or KH.MaKH LIKE ? ";
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, '%' + maHD + '%');
            ps.setObject(2, '%' + maNV + '%');
            ps.setObject(3, '%' + maKH + '%');
            rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien(rs.getString(2));
                KhachHang kh = new KhachHang(rs.getString(3));
                HoaDon hd = new HoaDon(rs.getString(1), nv, kh, rs.getDate(4), rs.getDouble(5),
                        rs.getString(6), rs.getString(7));
                listHD.add(hd);
            }
            return listHD;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int themHoaDon(HoaDon hd) {
        try {
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            sql = "UPDATE HoaDon SET MaNV = ?,MaKH = ?,NgayTao = ?,TongTien = ?,TongTienKM = ?,TongTienSauKM = ?,TrangThai = ?,GhiChu = ?,MaEV = ? where MaHoaDon = ?";
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(10, hd.getMaHoaDon());
            ps.setObject(1, hd.getNhanVien().getMaNhanVien());
            ps.setObject(2, hd.getKhachHang().getMaKhachHang());
            ps.setTimestamp(3, currentTime);
            Double mucGiam;
            if (hd.getVoucher() == null) {
                mucGiam = 0.0;
                ps.setObject(9, null);
                ps.setObject(4, hd.getTongTien());
                ps.setObject(5, 0);
                ps.setObject(6, hd.getTongTien());
            } else {
                ps.setObject(9, hd.getVoucher().getMaEventa());
                if (!hd.getVoucher().isHinhThuc()) {
                    mucGiam = Double.parseDouble(giuSo(hd.getVoucher().getMucGiamGia())) / 100;
                    ps.setObject(4, hd.getTongTien() / (1 - mucGiam));
                    ps.setObject(5, (hd.getTongTien() / (1 - mucGiam)) - hd.getTongTien());
                    ps.setObject(6, hd.getTongTien());
                } else {
                    mucGiam = Double.parseDouble(giuSo(hd.getVoucher().getMucGiamGia()));
                    ps.setObject(4, hd.getTongTien() + mucGiam);
                    ps.setObject(5, mucGiam);
                    ps.setObject(6, hd.getTongTien());
                }
            }
            ps.setObject(7, hd.getTrangThai());
            ps.setObject(8, hd.getGhiChu());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int chuyenSangDoiHang(String maHD) {
        try {
            sql = "Update HoaDon set TrangThai = N'Đã đổi hàng' where MaHoaDon = ?";
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, maHD);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public List<HoaDon> getList2(String MaHDorMaKH) {
        List<HoaDon> listHD = new ArrayList<>();
        try {
            sql = "Select HD.MaHoaDon,NV.MaNV,KH.MaKH,HD.NgayTao,HD.TongTien, HD.TrangThai,HD.GhiChu\n"
                    + "From HoaDon HD Join NhanVien NV ON HD.MaNV=NV.MaNV Join KhachHang KH ON HD.MaKH = KH.MaKH\n"
                    + "where HD.MaHoaDon like ? or KH.MaKH like ? order by HD.TrangThai";
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, '%' + MaHDorMaKH + '%');
            ps.setObject(2, '%' + MaHDorMaKH + '%');
            rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien(rs.getString(2));
                KhachHang kh = new KhachHang(rs.getString(3));
                HoaDon hd = new HoaDon(rs.getString(1), nv, kh, rs.getDate(4), rs.getDouble(5),
                        rs.getString(6), rs.getString(7));
                listHD.add(hd);
            }
            return listHD;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int themHoaDonCho(HoaDon hd) {
        try {
            sql = "Insert into HoaDon(MaHoaDon,MaNV,MaKH,NgayTao,TrangThai) Values(?,?,?,GETDATE(),N'Chờ thanh toán')";
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, hd.getMaHoaDon());
            ps.setObject(2, hd.getNhanVien().getMaNhanVien());
            ps.setObject(3, "KHNE");
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public String maHoaDonDuocDoiHang(String maHd) {
        String maDuDK = null;
        try {
            sql = "Select HD.MaHoaDon\n"
                    + "From HoaDon HD Join NhanVien NV ON HD.MaNV=NV.MaNV Join KhachHang KH ON HD.MaKH = KH.MaKH\n"
                    + "where DATEDIFF(DAY,HD.NgayTao,GETDATE()) <= 7 \n"
                    + "and (HD.TrangThai like N'Đã thanh toán')\n"
                    + "and HD.MaHoaDon = ?\n";
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, maHd);
            rs = ps.executeQuery();
            while (rs.next()) {
                maDuDK = rs.getString(1);
            }
            return maDuDK;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<HoaDon> getListDuocDoiHang(String MaHDorMaKH) {
        List<HoaDon> listHD = new ArrayList<>();
        try {
            sql = "Select HD.MaHoaDon,NV.MaNV,KH.MaKH,HD.NgayTao,HD.TongTien, HD.TrangThai,HD.GhiChu\n"
                    + "From HoaDon HD Join NhanVien NV ON HD.MaNV=NV.MaNV Join KhachHang KH ON HD.MaKH = KH.MaKH\n"
                    + "where (HD.MaHoaDon like N'%1%' or KH.MaKH like N'%1%') and DATEDIFF(DAY,HD.NgayTao,GETDATE()) < 7\n"
                    + "and HD.TrangThai like N'Đã thanh toán' order by HD.TrangThai";
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, '%' + MaHDorMaKH + '%');
            ps.setObject(2, '%' + MaHDorMaKH + '%');
            rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien(rs.getString(2));
                KhachHang kh = new KhachHang(rs.getString(3));
                HoaDon hd = new HoaDon(rs.getString(1), nv, kh, rs.getDate(4), rs.getDouble(5),
                        rs.getString(6), rs.getString(7));
                listHD.add(hd);
            }
            return listHD;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<HoaDon> listPageHD(int index) {
        List<HoaDon> listHoaDon3 = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            sql = "Select HD.MaHoaDon,NV.MaNV,KH.MaKH,HD.NgayTao,HD.TongTien, HD.TrangThai,HD.GhiChu\n"
                    + "From HoaDon HD Join NhanVien NV ON HD.MaNV=NV.MaNV Join KhachHang KH ON HD.MaKH = KH.MaKH\n"
                    + "where HD.MaHoaDon not in (Select MaHoaDon From HoaDon where TrangThai = N'Chờ thanh toán')\n"
                    + "order by MaHoaDon DESC\n"
                    + "OFFSET ? rows fetch next 15 rows only";
            ps = con.prepareStatement(sql);
            ps.setInt(1, (index - 1) * 15);
            rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien(rs.getString(2));
                KhachHang kh = new KhachHang(rs.getString(3));
                HoaDon hd = new HoaDon(rs.getString(1), nv, kh, rs.getDate(4), rs.getDouble(5),
                        rs.getString(6), rs.getString(7));
                listHoaDon3.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listHoaDon3;
    }

    public int tongBanGhi() {
        int tong = 0;
        try {
            con = DBConnect.getConnection();
            sql = "SELECT COUNT(*) FROM HoaDon";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                tong = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return tong;
    }

    public HoaDon getOne(String maHD) {
        HoaDon hd = null;
        try {
            con = DBConnect.getConnection();
            sql = "SELECT hd.MaKH, kh.HoTen, kh.SoDienThoai FROM HoaDon hd join KhachHang kh on hd.MaKH = kh.MaKH where MaHoaDon = ?";
            ps = con.prepareStatement(sql);
            ps.setObject(1, maHD);
            rs = ps.executeQuery();
            while (rs.next()) {
                KhachHang kh = new KhachHang(rs.getString(1), rs.getString(2), rs.getString(3));
                hd = new HoaDon(maHD, kh);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return hd;
        }
        return hd;
    }

    public int capNhatTrangThai(String maHD) {
        try {
            sql = "Update HoaDon set TrangThai = N'Đã thanh toán' Where MaHoaDon = ?";
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, maHD);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int huyHoaDonCho(String maHD) {
        try {
            sql = "DELETE HoaDon where MaHoaDon = ?";
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, maHD);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
