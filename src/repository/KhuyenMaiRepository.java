
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
import model.Events;
import util.DBConnect;

/**
 *
 * @author an
 */
public class KhuyenMaiRepository {

    PreparedStatement ps = null;
    Connection con = null;
    ResultSet rs = null;
    String sql = null;
    List<Events> listKm = new ArrayList<>();

    public List<Events> getAll() {
        listKm.clear();
        try {
            con = DBConnect.getConnection();
            sql = "select * from Events";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Events ev = new Events(rs.getString(1), rs.getString(2), rs.getBoolean(3), rs.getString(4),
                        rs.getDate(5), rs.getDate(6), rs.getString(7), rs.getBoolean(8),
                        rs.getBoolean(9), rs.getString(10));
                listKm.add(ev);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listKm;
    }

    public int themEvents(Events ev) {
        try {
            con = DBConnect.getConnection();
            sql = "insert into Events values (?,?,?,?,?,?,?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setObject(1, ev.getMaEventa());
            ps.setObject(2, ev.getTenEvent());
            ps.setObject(3, ev.isHinhThuc());
            ps.setObject(4, ev.getMucGiamGia());
            ps.setObject(5, ev.getThoiGianBatDau());
            ps.setObject(6, ev.getThoiGianKetThuc());
            ps.setObject(7, ev.getMoTa());
            ps.setObject(8, ev.isTrangThai());
            ps.setObject(9, ev.isDieuKienApDung());
            ps.setObject(10, ev.getDieuKienTongTien());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public Events getOne(String ma) {
        Events ev = null;
        try {
            con = DBConnect.getConnection();
            sql = "SELECT*FROM Events where MaEV = ?";
            ps = con.prepareStatement(sql);
            ps.setObject(1, ma);
            rs = ps.executeQuery();
            while (rs.next()) {
                ev = new Events(rs.getString(1), rs.getString(2), rs.getBoolean(3),
                        rs.getString(4), rs.getDate(5), rs.getDate(6),
                        rs.getString(7), rs.getBoolean(8),
                        rs.getBoolean(9), rs.getString(10));
                listKm.add(ev);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return ev;
    }

    public int suaEvents(Events ev, String ma) {
        try {
            con = DBConnect.getConnection();
            sql = "update Events set MaEV = ? , TenEV = ? , HinhThuc=?, MucGiamGia=?, ThoiGianBatDau=?,\n"
                    + "ThoiGianKetThuc=?,MoTa=?,TrangThai=?,DieuKienApDung=?,DieuKienTongTien=? where MaEV=?";
            ps = con.prepareStatement(sql);
            ps.setObject(1, ev.getMaEventa());
            ps.setObject(2, ev.getTenEvent());
            ps.setObject(3, ev.isHinhThuc());
            ps.setObject(4, ev.getMucGiamGia());
            ps.setObject(5, ev.getThoiGianBatDau());
            ps.setObject(6, ev.getThoiGianKetThuc());
            ps.setObject(7, ev.getMoTa());
            ps.setObject(8, ev.isTrangThai());
            ps.setObject(9, ev.isDieuKienApDung());
            ps.setObject(10, ev.getDieuKienTongTien());
            ps.setObject(11, ma);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public List<Events> getList(String ma) {
        List<Events> listKm2 = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            sql = "select * from Events where MaEV like ? or TenEV like ?";
            ps = con.prepareStatement(sql);
            ps.setObject(1, '%' + ma + '%');
            ps.setObject(2, '%' + ma + '%');
            rs = ps.executeQuery();
            while (rs.next()) {
                Events ev = new Events(rs.getString(1), rs.getString(2), rs.getBoolean(3), rs.getString(4),
                        rs.getDate(5), rs.getDate(6), rs.getString(7), rs.getBoolean(8),
                        rs.getBoolean(9), rs.getString(10));
                listKm2.add(ev);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listKm2;
    }

    public List<Events> searchTheoTrangThai(boolean tt) {
        List<Events> listKm3 = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            sql = "select * from Events where TrangThai like ?";
            ps = con.prepareStatement(sql);
            ps.setBoolean(1, tt);
            rs = ps.executeQuery();
            while (rs.next()) {
                Events ev = new Events(rs.getString(1), rs.getString(2), rs.getBoolean(3), rs.getString(4),
                        rs.getDate(5), rs.getDate(6), rs.getString(7), rs.getBoolean(8),
                        rs.getBoolean(9), rs.getString(10));
                listKm3.add(ev);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listKm3;
    }

    public int updateTrangThai() {
        try {
            con = DBConnect.getConnection();
            sql = "update Events set TrangThai = 0 \n"
                    + "where DATEDIFF(DAY,ThoiGianKetThuc,GETDATE()) >0\n"
                    + "update Events set TrangThai = 1 \n"
                    + "where DATEDIFF(DAY,ThoiGianKetThuc,GETDATE()) <= 0 ";
            ps = con.prepareStatement(sql);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public List<Events> listPageKm(int index) {
        List<Events> listKm4 = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            sql = "select * from Events \n"
                    + "order by MaEV\n"
                    + "offset ? rows fetch next 4 rows only ";
            ps = con.prepareStatement(sql);
            ps.setInt(1, (index - 1) * 4);
            rs = ps.executeQuery();
            while (rs.next()) {
                Events ev = new Events(rs.getString(1), rs.getString(2), rs.getBoolean(3), rs.getString(4),
                        rs.getDate(5), rs.getDate(6), rs.getString(7), rs.getBoolean(8),
                        rs.getBoolean(9), rs.getString(10));
                listKm4.add(ev);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listKm4;
    }

    public int tongBanGhi() {
        int tong = 0;
        try {
            con = DBConnect.getConnection();
            sql = "SELECT COUNT(*) FROM Events";
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

    public Events getActive(Double so) {
        Events ev = null;
        try {
            con = DBConnect.getConnection();
            sql = "SELECT*FROM Events where TrangThai = 1 and CAST(DieuKienTongTien AS INT) < ?";
            ps = con.prepareStatement(sql);
            ps.setObject(1, so);
            rs = ps.executeQuery();
            while (rs.next()) {
                ev = new Events(rs.getString(1), rs.getString(2), rs.getBoolean(3),
                        rs.getString(4), rs.getDate(5), rs.getDate(6),
                        rs.getString(7), rs.getBoolean(8),
                        rs.getBoolean(9), rs.getString(10));
                listKm.add(ev);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return ev;
    }

    public Events getActive2(Double so) {
        List<Events> list = new ArrayList<>();
        Double luongGia = 1.0;
        String maEV = "";
        Events newEV = null;
        try {
            con = DBConnect.getConnection();
            sql = "SELECT*FROM Events where TrangThai = 1 and CAST(DieuKienTongTien AS INT) < ? order by MucGiamGia desc";
            ps = con.prepareStatement(sql);
            ps.setObject(1, so);
            rs = ps.executeQuery();
            while (rs.next()) {
                Events ev = new Events(rs.getString(1), rs.getString(2), rs.getBoolean(3),
                        rs.getString(4), rs.getDate(5), rs.getDate(6),
                        rs.getString(7), rs.getBoolean(8),
                        rs.getBoolean(9), rs.getString(10));
                list.add(ev);
                maEV += ev.getMaEventa();
                Double giam = Double.valueOf(ev.getMucGiamGia()) / 100;
                luongGia *= (1 - giam);
            }

            if (list.isEmpty()) {
                return null;
            }
            String giamGia = String.valueOf(Math.round((1 - luongGia) * 100));
            newEV = new Events(maEV, null, true, giamGia, null, null, null, true, true, null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return newEV;
    }

    public List<Events> getActive3(Double so) {
        List<Events> list = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            sql = "SELECT * FROM ( SELECT Top 1 * FROM Events WHERE HinhThuc = 0 AND TrangThai = 1 AND CAST(DieuKienTongTien AS INT) < ? ORDER BY MucGiamGia DESC) AS A \n"
                    + "                UNION \n"
                    + "                SELECT * FROM ( \n"
                    + "                SELECT TOP 1 * FROM Events WHERE HinhThuc = 1 AND TrangThai = 1 AND CAST(DieuKienTongTien AS INT) < ? \n"
                    + "                ORDER BY MucGiamGia DESC\n"
                    + "                ) AS B";
            ps = con.prepareStatement(sql);
            ps.setObject(1, so);
            ps.setObject(2, so);
            rs = ps.executeQuery();
            while (rs.next()) {
                Events ev = new Events(rs.getString(1), rs.getString(2), rs.getBoolean(3),
                        rs.getString(4), rs.getDate(5), rs.getDate(6),
                        rs.getString(7), rs.getBoolean(8),
                        rs.getBoolean(9), rs.getString(10));
                list.add(ev);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    public Events searchTen(String ten) {
        Events ev = null;
        try {
            con = DBConnect.getConnection();
            sql = "SELECT*FROM Events where TenEV = ?";
            ps = con.prepareStatement(sql);
            ps.setObject(1, ten);
            rs = ps.executeQuery();
            while (rs.next()) {
                ev = new Events(rs.getString(1), rs.getString(2), rs.getBoolean(3),
                        rs.getString(4), rs.getDate(5), rs.getDate(6),
                        rs.getString(7), rs.getBoolean(8),
                        rs.getBoolean(9), rs.getString(10));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return ev;
    }
}
