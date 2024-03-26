/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.LoaiSanPham;
import model.SanPham;
import util.DBConnect;

/**
 *
 * @author user
 */
public class LoaiSanPhamRepository {

    PreparedStatement pst = null;
    Connection conn = null;
    ResultSet rs = null;
    String sql = null;
    List<LoaiSanPham> listLoaiSanPham = new ArrayList<>();

    public List<LoaiSanPham> getAll() {
        listLoaiSanPham.clear();
        try {
            conn = DBConnect.getConnection();
            sql = "SELECT * FROM LoaiSanPham where trangThai=1";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                LoaiSanPham lsp = new LoaiSanPham(rs.getString(1), rs.getString(2), rs.getBoolean(3), rs.getString(4));
                listLoaiSanPham.add(lsp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listLoaiSanPham;
    }
}
