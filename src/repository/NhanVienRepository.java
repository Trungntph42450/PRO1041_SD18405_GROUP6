/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import model.NhanVien;
import model.TaiKhoan;
import util.DBConnect;

/**
 *
 * @author ADMIN
 */
public class NhanVienRepository {

    PreparedStatement pst = null;
    Connection conn = null;
    ResultSet rs = null;
    String sql = null;
    List<NhanVien> listNhanVien = new ArrayList<>();

    public List<NhanVien> getAll() {
        listNhanVien.clear();
        try {
            conn = DBConnect.getConnection();
            sql = "select nv.MaNV,nv.MaTK,nv.HoTen,nv.GioiTinh,nv.DiaChi,nv.SoDienThoai"
                    + ",nv.CCCD,nv.NgayVaoLam,nv.TrangThai,nv.Anh,tk.UserName,tk.PassWord,tk.Role,tk.TrangThai \n"
                    + "from NhanVien nv join TaiKhoan tk on tk.MaTK = nv.MaTK";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                TaiKhoan tk = new TaiKhoan(rs.getString(2), rs.getString(11),
                        rs.getString(12), rs.getString(13), rs.getBoolean(14));
                NhanVien nv = new NhanVien(rs.getString(1), tk,
                        rs.getString(3), rs.getBoolean(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getDate(8),
                        rs.getBoolean(9), rs.getString(10));
                listNhanVien.add(nv);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listNhanVien;
    }

    public int insert(NhanVien nv) {
        try {
            conn = DBConnect.getConnection();
            sql = "INSERT INTO NhanVien (MaNV,MaTK,HoTen,GioiTinh,DiaChi,SoDienThoai,CCCD,NgayVaoLam,TrangThai,Anh) VALUES (?,?,?,?,?,?,?,?,?,?)";
            pst = conn.prepareStatement(sql);
            pst.setObject(1, nv.getMaNhanVien());
            pst.setObject(2, nv.getTaiKhoan().getMaTaiKhoan());
            pst.setObject(3, nv.getHoTen());
            pst.setObject(4, nv.isGioiTinh());
            pst.setObject(5, nv.getDiaChi());
            pst.setObject(6, nv.getSoDienThoai());
            pst.setObject(7, nv.getCCCD());
            pst.setObject(8, nv.getNgayVaoLam());
            pst.setObject(9, nv.isTrangThai());
            pst.setObject(10, nv.getAnh());
            return pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int update(NhanVien nv, String maNV) {
        try {
            conn = DBConnect.getConnection();
            sql = "UPDATE NhanVien SET MaTK=?, HoTen=?, GioiTinh=?, DiaChi=?, SoDienThoai=?, CCCD=?, NgayVaoLam=?, TrangThai=?, Anh=? WHERE MaNV=?";
            pst = conn.prepareStatement(sql);
            pst.setObject(10, nv.getMaNhanVien());
            pst.setObject(1, nv.getTaiKhoan().getMaTaiKhoan());
            pst.setObject(2, nv.getHoTen());
            pst.setObject(3, nv.isGioiTinh());
            pst.setObject(4, nv.getDiaChi());
            pst.setObject(5, nv.getSoDienThoai());
            pst.setObject(6, nv.getCCCD());
            pst.setObject(7, nv.getNgayVaoLam());
            pst.setObject(8, nv.isTrangThai());
            pst.setObject(9, nv.getAnh());
            return pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int updateTK(TaiKhoan tk) {
        try {
            conn = DBConnect.getConnection();
            sql = "UPDATE TaiKhoan SET Role=?, TrangThai=? WHERE MaTK=?";
            pst = conn.prepareStatement(sql);
            pst.setObject(1, tk.getRole());
            pst.setObject(2, tk.getTrangThai());
            pst.setObject(3, tk.getMaTaiKhoan());
            return pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public List<NhanVien> getList2(String mot, String hai) {
        listNhanVien.clear();
        try {
            conn = DBConnect.getConnection();
            sql = "select nv.MaNV,nv.MaTK,nv.HoTen,nv.GioiTinh,nv.DiaChi,nv.SoDienThoai"
                    + ",nv.CCCD,nv.NgayVaoLam,nv.TrangThai,nv.Anh,tk.UserName,tk.PassWord,tk.Role,tk.TrangThai \n"
                    + "from NhanVien nv join TaiKhoan tk on tk.MaTK = nv.MaTK where GioiTinh = ? and DiaChi = ?";
            pst = conn.prepareStatement(sql);
            pst.setObject(1, mot);
            pst.setObject(2, hai);
            rs = pst.executeQuery();
            while (rs.next()) {
                TaiKhoan tk = new TaiKhoan(rs.getString(2), rs.getString(11),
                        rs.getString(12), rs.getString(13), rs.getBoolean(14));
                NhanVien nv = new NhanVien(rs.getString(1), tk,
                        rs.getString(3), rs.getBoolean(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getDate(8),
                        rs.getBoolean(9), rs.getString(10));
                listNhanVien.add(nv);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listNhanVien;
    }

    public List<NhanVien> getList3(String mot, String hai) {
        listNhanVien.clear();
        try {
            conn = DBConnect.getConnection();
            sql = "select nv.MaNV,nv.MaTK,nv.HoTen,nv.GioiTinh,nv.DiaChi,nv.SoDienThoai"
                    + ",nv.CCCD,nv.NgayVaoLam,nv.TrangThai,nv.Anh,tk.UserName,tk.PassWord,tk.Role,tk.TrangThai \n"
                    + "from NhanVien nv join TaiKhoan tk on tk.MaTK = nv.MaTK where " + mot + " like ? ";
            pst = conn.prepareStatement(sql);
            pst.setObject(1, "%" + hai + "%");
            rs = pst.executeQuery();
            while (rs.next()) {
                TaiKhoan tk = new TaiKhoan(rs.getString(2), rs.getString(11),
                        rs.getString(12), rs.getString(13), rs.getBoolean(14));
                NhanVien nv = new NhanVien(rs.getString(1), tk,
                        rs.getString(3), rs.getBoolean(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getDate(8),
                        rs.getBoolean(9), rs.getString(10));
                listNhanVien.add(nv);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listNhanVien;
    }

    public NhanVien getOne(String ma) {
        NhanVien nv = null;
        listNhanVien.clear();
        try {
            conn = DBConnect.getConnection();
            sql = "select nv.MaNV,nv.MaTK,nv.HoTen,nv.GioiTinh,nv.DiaChi,nv.SoDienThoai"
                    + ",nv.CCCD,nv.NgayVaoLam,nv.TrangThai,nv.Anh,tk.UserName,tk.PassWord,tk.Role,tk.TrangThai \n"
                    + "from NhanVien nv join TaiKhoan tk on tk.MaTK = nv.MaTK where MaNV = ?";
            pst = conn.prepareStatement(sql);
            pst.setObject(1, ma);
            rs = pst.executeQuery();
            while (rs.next()) {
                TaiKhoan tk = new TaiKhoan(rs.getString(2), rs.getString(11),
                        rs.getString(12), rs.getString(13), rs.getBoolean(14));
                nv = new NhanVien(rs.getString(1), tk,
                        rs.getString(3), rs.getBoolean(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getDate(8),
                        rs.getBoolean(9), rs.getString(10));
            }
            return nv;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<NhanVien> listPageNV(int tt, int index) {
        List<NhanVien> list = new ArrayList<>();
        try {
            conn = DBConnect.getConnection();
            sql = "select nv.MaNV,nv.MaTK,nv.HoTen,nv.GioiTinh,nv.DiaChi,nv.SoDienThoai, "
                    + "nv.CCCD,nv.NgayVaoLam,nv.TrangThai,nv.Anh,tk.UserName,tk.PassWord, "
                    + "tk.Role,tk.TrangThai \n"
                    + "from NhanVien nv join TaiKhoan tk on tk.MaTK = nv.MaTK where nv.TrangThai = ? "
                    + "order by nv.MaNV "
                    + "offset ? rows fetch next 4 rows only ";
            pst = conn.prepareStatement(sql);
            pst.setObject(1, tt);
            pst.setObject(2, (index - 1) * 4);
            rs = pst.executeQuery();
            while (rs.next()) {
                TaiKhoan tk = new TaiKhoan(rs.getString(2), rs.getString(11),
                        rs.getString(12), rs.getString(13), rs.getBoolean(14));
                NhanVien nv = new NhanVien(rs.getString(1), tk,
                        rs.getString(3), rs.getBoolean(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getDate(8),
                        rs.getBoolean(9), rs.getString(10));
                list.add(nv);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    public int tongBanGhi(int index) {
        int tong = 0;
        try {
            conn = DBConnect.getConnection();
            if (index == -1) {
                sql = "SELECT COUNT(*) FROM NhanVien";
                pst = conn.prepareStatement(sql);
            } else {
                sql = "SELECT COUNT(*) FROM NhanVien where TrangThai = ?";
                pst = conn.prepareStatement(sql);
                pst.setObject(1, index);
            }
            rs = pst.executeQuery();
            if (rs.next()) {
                tong = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return tong;
    }

    public NhanVien timTheoUserName(String ma) {
        NhanVien nv = null;
        listNhanVien.clear();
        try {
            conn = DBConnect.getConnection();
            sql = "select nv.MaNV,nv.MaTK,nv.HoTen,nv.GioiTinh,nv.DiaChi,nv.SoDienThoai"
                    + ",nv.CCCD,nv.NgayVaoLam,nv.TrangThai,nv.Anh,tk.UserName,tk.PassWord,tk.Role,tk.TrangThai \n"
                    + "from NhanVien nv join TaiKhoan tk on tk.MaTK = nv.MaTK where tk.UserName = ?";
            pst = conn.prepareStatement(sql);
            pst.setObject(1, ma);
            rs = pst.executeQuery();
            while (rs.next()) {
                TaiKhoan tk = new TaiKhoan(rs.getString(2), rs.getString(11),
                        rs.getString(12), rs.getString(13), rs.getBoolean(14));
                nv = new NhanVien(rs.getString(1), tk,
                        rs.getString(3), rs.getBoolean(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getDate(8),
                        rs.getBoolean(9), rs.getString(10));
            }
            return nv;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public TaiKhoan getTK(String ma) {
        TaiKhoan tk = null;
        try {
            conn = DBConnect.getConnection();
            sql = "select MaTK,UserName,PassWord,Role,TrangThai from TaiKhoan where MaTK = ?";
            pst = conn.prepareStatement(sql);
            pst.setObject(1, ma);
            rs = pst.executeQuery();
            while (rs.next()) {                
                 tk = new TaiKhoan(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getBoolean(5));
            }
            return tk;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public int insertTK(TaiKhoan tk){
        try {
            conn = DBConnect.getConnection();
            sql = "INSERT INTO TaiKhoan (MaTK,UserName,PassWord,Role,TrangThai) VALUES (?,?,?,?,?)";
            pst = conn.prepareStatement(sql);
            pst.setObject(1, tk.getMaTaiKhoan());
            pst.setObject(2, tk.getUserName());
            pst.setObject(3, tk.getPassWord());
            pst.setObject(4, tk.getRole());
            pst.setObject(5, tk.getTrangThai());
            return pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
