/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.HoaDon;
import model.HoaDonChiTiet;
import util.DBConnect;

/**
 *
 * @author lavuo
 */
public class ThongKeDoanhThuRepository {

    PreparedStatement ps = null;
    Connection con = null;
    ResultSet rs = null;
    String sql = null;

    public List<HoaDonChiTiet> getListThongKeDT() {
        List<HoaDonChiTiet> listTKDT = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            sql = "select NgayTao,TongTien from HoaDon";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon(rs.getDate(1), rs.getDouble(2));
                HoaDonChiTiet cthd = new HoaDonChiTiet(hd);
                listTKDT.add(cthd);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listTKDT;
    }

    public List<HoaDonChiTiet> getListTKDT(java.util.Date ngayBd, java.util.Date ngayKt) {
        List<HoaDonChiTiet> listTimKiem = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            sql = "select NgayTao,TongTien from HoaDon\n"
                    + "WHERE NgayTao BETWEEN CONCAT(?, ' ', '00:00:00')AND CONCAT(?, ' ', '23:59:59')\n";
            ps = con.prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(ngayBd.getTime()));
            ps.setDate(2, new java.sql.Date(ngayKt.getTime()));
            rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon(rs.getDate(1), rs.getDouble(2));
                HoaDonChiTiet cthd = new HoaDonChiTiet(hd);
                listTimKiem.add(cthd);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listTimKiem;
    }

}
