/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.KhachHang;
import util.DBConnect;

/**
 *
 * @author Nguyen Thanh Trung
 */
public class KhachHangRepository {
    
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = null;
    List<KhachHang> listKhachHang = new ArrayList<>();

    public List<KhachHang> getAll() {
        listKhachHang.clear();
        try {
            con = DBConnect.getConnection();
            sql = "Select MaKH,HoTen,NgaySinh,SoDienThoai,Email,GioiTInh,DiaChi From KhachHang \n"
                    + "where MaKH not in (Select MaKH From KhachHang where MaKH like N'KHNE')";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                KhachHang kh = new KhachHang(rs.getString(1),
                        rs.getString(2),
                        rs.getDate(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getBoolean(6),
                        rs.getString(7));
                listKhachHang.add(kh);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listKhachHang;
    }

    public int Them(KhachHang kh) {
        try {
            con = DBConnect.getConnection();
            sql = "INSERT INTO KhachHang(MaKH,HoTen,NgaySinh,SoDienThoai,Email,GioiTInh,DiaChi) \n"
                    + "Values (?,?,?,?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setObject(1, kh.getMaKhachHang());
            ps.setObject(2, kh.getHoTen());
            ps.setObject(3, kh.getNgaySinh());
            ps.setObject(4, kh.getSoDienThoai());
            ps.setObject(5, kh.getEmail());
            ps.setObject(6, kh.isGioiTinh());
            ps.setObject(7, kh.getDiaChi());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int CapNhat(KhachHang kh, String maKH) {
        try {
            con = DBConnect.getConnection();
            sql = "Update KhachHang set HoTen = ?, NgaySinh = ?,SoDienThoai = ?,Email = ?,\n"
                    + "GioiTInh = ?,DiaChi = ? where MaKH = ?";
            ps = con.prepareStatement(sql);
            ps.setObject(7, maKH);
            ps.setObject(1, kh.getHoTen());
            ps.setObject(2, kh.getNgaySinh());
            ps.setObject(3, kh.getSoDienThoai());
            ps.setObject(4, kh.getEmail());
            ps.setObject(5, kh.isGioiTinh());
            ps.setObject(6, kh.getDiaChi());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public KhachHang getOne(String maKH) {
        KhachHang kh = null;
        try {
            con = DBConnect.getConnection();
            sql = "Select MaKH,HoTen,NgaySinh,SoDienThoai,Email,GioiTInh,DiaChi From KhachHang where MaKH = ?";
            ps = con.prepareStatement(sql);
            ps.setObject(1, maKH);
            rs = ps.executeQuery();
            while (rs.next()) {
                kh = new KhachHang(rs.getString(1),
                        rs.getString(2),
                        rs.getDate(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getBoolean(6),
                        rs.getString(7));
            }
            return kh;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<KhachHang> getList(String ten) {
        List<KhachHang> listKhachHang2 = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            sql = "Select MaKH,HoTen,NgaySinh,SoDienThoai,Email,GioiTInh,DiaChi "
                    + "From KhachHang where HoTen like ? or SoDienThoai like ?";
            ps = con.prepareStatement(sql);
            ps.setObject(1, '%' + ten + '%');
            ps.setObject(2, '%' + ten + '%');
            rs = ps.executeQuery();
            while (rs.next()) {
                KhachHang kh = new KhachHang(rs.getString(1),
                        rs.getString(2),
                        rs.getDate(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getBoolean(6),
                        rs.getString(7));
                listKhachHang2.add(kh);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listKhachHang2;
    }

    public List<KhachHang> listPageKH(int index) {
        List<KhachHang> listKhachHang3 = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            sql = " Select MaKH,HoTen,NgaySinh,SoDienThoai,Email,GioiTInh,DiaChi From KhachHang\n"
                    + "where MaKH not in (Select MaKH From KhachHang where MaKH like N'KHNE')\n"
                    + "order by MaKH\n"
                    + "offset ? rows fetch next 5 rows only ";
            ps = con.prepareStatement(sql);
            ps.setInt(1, (index - 1) * 5);
            rs = ps.executeQuery();
            while (rs.next()) {
                KhachHang kh = new KhachHang(rs.getString(1),
                        rs.getString(2),
                        rs.getDate(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getBoolean(6),
                        rs.getString(7));
                listKhachHang3.add(kh);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listKhachHang3;
    }

    public int tongBanGhi() {
        int tong = 0;
        try {
            con = DBConnect.getConnection();
            sql = "SELECT COUNT(*) FROM KhachHang";
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

    public List<KhachHang> getListLocGioiTinhAndDiaChi(String name1, String name2) {
        listKhachHang.clear();
        try {
            con = DBConnect.getConnection();
            sql = "Select MaKH,HoTen,NgaySinh,SoDienThoai,Email,GioiTInh,DiaChi From KhachHang \n"
                    + "where MaKH not in (Select MaKH From KhachHang where MaKH like N'KHNE')\n"
                    + " and GioiTinh = ? and DiaChi = ?";
            ps = con.prepareStatement(sql);
            ps.setObject(1, name1);
            ps.setObject(2, name2);
            rs = ps.executeQuery();
            while (rs.next()) {
                KhachHang kh = new KhachHang(rs.getString(1),
                        rs.getString(2),
                        rs.getDate(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getBoolean(6),
                        rs.getString(7));
                listKhachHang.add(kh);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listKhachHang;
    }

    public List<KhachHang> getListLocGioiTinhOrDiaChi(String name1, String name2) {
        listKhachHang.clear();
        try {
            con = DBConnect.getConnection();
            sql = "Select MaKH,HoTen,NgaySinh,SoDienThoai,Email,GioiTInh,DiaChi From KhachHang \n"
                    + "where MaKH not in (Select MaKH From KhachHang where MaKH like N'KHNE')\n"
                    + " and " + name1 + " like ?";
            ps = con.prepareStatement(sql);
            ps.setObject(1, "%" + name2 + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                KhachHang kh = new KhachHang(rs.getString(1),
                        rs.getString(2),
                        rs.getDate(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getBoolean(6),
                        rs.getString(7));
                listKhachHang.add(kh);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listKhachHang;
    }
}
