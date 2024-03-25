/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.LoaiSanPham;
import model.SanPham;
import util.DBConnect;

/**
 *
 * @author user
 */
public class SanPhamRepository {

    PreparedStatement pst = null;
    Connection conn = null;
    ResultSet rs = null;
    String sql = null;
    List<SanPham> listSanPham = new ArrayList<>();

    public List<SanPham> getAll() {
        listSanPham.clear();
        try {
            conn = DBConnect.getConnection();
            sql = "SELECT SP.MaSanPham,SP.TenSanPham,SP.TrangThai,LSP.MaLSP,LSP.TenLSP,SP.XuatXU \n"
                    + "FROm SanPham SP INNER JOIN LoaiSanPham LSP ON SP.MaLSP=LSP.MaLSP where SP.TrangThai=1";

            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                LoaiSanPham lsp = new LoaiSanPham(rs.getString(4),
                        rs.getString(5));
                SanPham sp = new SanPham(rs.getString(1), rs.getString(2),
                        rs.getBoolean(3), lsp, rs.getString(6));
                listSanPham.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listSanPham;
    }

    public int them(SanPham sp) {
        try {
            conn = DBConnect.getConnection();
            sql = "INSERT INTO SanPham(MaSanPham,TenSanPham,TrangThai,MaLSP,XuatXU) VALUES(?,?,?,?,?)";
            pst = conn.prepareStatement(sql);
            pst.setObject(1, sp.getMaSanPham());
            pst.setObject(2, sp.getTenSanPham());
            pst.setObject(3, sp.isTrangThai());
            pst.setObject(4, sp.getLoaiSanPham().getMaLoaiSanPham());
            pst.setObject(5, sp.getXuatXu());
            return pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int sua(SanPham sp, String ma) {
        try {
            conn = DBConnect.getConnection();
            sql = "UPDATE SanPham set TenSanPham=?,TrangThai=?,MaLSP=?,XuatXU=? where MaSanPham=?";
            pst = conn.prepareStatement(sql);
            pst.setObject(1, sp.getTenSanPham());
            pst.setObject(2, sp.isTrangThai());
            pst.setObject(3, sp.getLoaiSanPham().getMaLoaiSanPham());
            pst.setObject(4, sp.getXuatXu());
            pst.setObject(5, ma);
            return pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int xoa(String ma) {
        try {
            conn = DBConnect.getConnection();
            sql = "";
            pst = conn.prepareStatement(sql);
            pst.setObject(1, ma);
            return pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public SanPham getOne(String ma) {
        SanPham sp = null;
        try {
            conn = DBConnect.getConnection();
            sql = "SELECT SP.MaSanPham,SP.TenSanPham,SP.TrangThai,LSP.MaLSP,LSP.TenLSP,SP.XuatXU \n"
                    + "FROm SanPham SP INNER JOIN LoaiSanPham LSP ON SP.MaLSP=LSP.MaLSP where SP.MaSanPham=? "
                    + "order by SP.TrangThai DESC";
            pst = conn.prepareStatement(sql);
            pst.setObject(1, ma);
            rs = pst.executeQuery();
            while (rs.next()) {
                LoaiSanPham lsp = new LoaiSanPham(rs.getString(4),
                        rs.getString(5));
                sp = new SanPham(rs.getString(1), rs.getString(2),
                        rs.getBoolean(3), lsp, rs.getString(6));
                listSanPham.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return sp;
    }
     public List<SanPham> getList(String name) {
        List<SanPham> listSanPham2 = new ArrayList<>();
        try {
            conn = DBConnect.getConnection();
            sql = "SELECT SP.MaSanPham,SP.TenSanPham,SP.TrangThai,LSP.MaLSP,LSP.TenLSP,SP.XuatXU \n"
                    + "FROm SanPham SP INNER JOIN LoaiSanPham LSP ON SP.MaLSP=LSP.MaLSP where SP.TenSanPham like ? or LSP.TenLSP like ? order by SP.TrangThai DESC";
            pst = conn.prepareStatement(sql);
            pst.setObject(1, '%' + name + '%');
            pst.setObject(2, '%' + name + '%');
            rs = pst.executeQuery();
            while (rs.next()) {
                LoaiSanPham lsp = new LoaiSanPham(rs.getString(4),
                        rs.getString(5));
                SanPham sp = new SanPham(rs.getString(1), rs.getString(2),
                        rs.getBoolean(3), lsp, rs.getString(6));
                listSanPham2.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listSanPham2;
    }
     public boolean checkExitSP(String maSP) throws SQLException {
        conn = DBConnect.getConnection();
        sql = "SELECT*FROM SanPham where MaSanPham=?";
        pst = conn.prepareStatement(sql);
        pst.setString(1, maSP);
        rs = pst.executeQuery();
        if (rs.next()) {
            return true;
        } else {
            return false;
        }
    }
     public List<SanPham> listPageSP(int index) {
        List<SanPham> listSanPham3 = new ArrayList<>();
        try {
            conn = DBConnect.getConnection();
            sql = "SELECT SP.MaSanPham,SP.TenSanPham,SP.TrangThai,LSP.MaLSP,LSP.TenLSP,SP.XuatXU \n"
                    + "FROm SanPham SP INNER JOIN LoaiSanPham LSP ON SP.MaLSP=LSP.MaLSP\n"
                    + "order by SP.TrangThai DESC\n"
                    + "offset ? rows fetch next 5 rows only ";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, (index - 1) * 5);
            rs = pst.executeQuery();
            while (rs.next()) {
                LoaiSanPham lsp = new LoaiSanPham(rs.getString(4),
                        rs.getString(5));
                SanPham sp = new SanPham(rs.getString(1), rs.getString(2),
                        rs.getBoolean(3), lsp, rs.getString(6));
                listSanPham3.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listSanPham3;
    }
      public int tongBanGhi() {
        int tong = 0;
        try {
            conn = DBConnect.getConnection();
            sql = "SELECT COUNT(*) FROM SanPham";
            pst = conn.prepareStatement(sql);
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

}
