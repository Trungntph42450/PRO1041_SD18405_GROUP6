/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.MauSac;
import util.DBConnect;

/**
 *
 * @author ADMIN
 */
public class MauSacRepository {

    PreparedStatement pst = null;
    Connection conn = null;
    ResultSet rs = null;
    String sql = null;
    List<MauSac> listMauSac = new ArrayList<>();

    public List<MauSac> getAll() {
        listMauSac.clear();
        try {
            conn = DBConnect.getConnection();
            sql = "SELECT*FROM MauSac where TrangThai=1";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                MauSac ms = new MauSac(rs.getString(1),
                        rs.getString(2), rs.getBoolean(3));
                listMauSac.add(ms);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listMauSac;
    }

    public MauSac getOne(String ma) {
        MauSac ms = null;
        try {
            conn = DBConnect.getConnection();
            sql = "SELECT*FROM MauSac where MaMauSac=?";
            pst = conn.prepareStatement(sql);
            pst.setObject(1, ma);
            rs = pst.executeQuery();
            while (rs.next()) {
                ms = new MauSac(rs.getString(1),
                        rs.getString(2), rs.getBoolean(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return ms;
    }
    
    public int them(MauSac ms) {
        try {
            conn = DBConnect.getConnection();
            sql = "INSERT INTO MauSac VALUES(?,?,?)";
            pst = conn.prepareStatement(sql);
            pst.setObject(1, ms.getMaMauSac());
            pst.setObject(2, ms.getTenMauSac());
            pst.setObject(3, ms.isTrangThai());
            return pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }


    public int sua(MauSac ms, String ma) {
        try {
            conn = DBConnect.getConnection();
            sql = "UPDATE MauSac set TenMauSac=?,TrangThai=? where MaMauSac=?";
            pst = conn.prepareStatement(sql);
            pst.setObject(1, ms.getTenMauSac());
            pst.setObject(2, ms.isTrangThai());
            pst.setObject(3, ma);
            return pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public List<MauSac> getList(String name) {
        List<MauSac> listMauSac4 = new ArrayList<>();
        try {
            conn = DBConnect.getConnection();
            sql = "SELECT * FROM MauSac where tenMauSac like ?";
            pst = conn.prepareStatement(sql);
            pst.setObject(1, '%' + name + '%');
            rs = pst.executeQuery();
            while (rs.next()) {
                MauSac ms = new MauSac(rs.getString(1),
                        rs.getString(2), rs.getBoolean(3));
                listMauSac4.add(ms);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listMauSac4;
    }

    public List<MauSac> listPageMS(int index) {
        List<MauSac> listMauSac3 = new ArrayList<>();
        try {
            conn = DBConnect.getConnection();
            sql = "SELECT * FROM MauSac\n"
                    + "order by MaMauSac\n"
                    + "OFFSET ? rows fetch next 5 rows only";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, (index - 1) * 5);
            rs = pst.executeQuery();
            while (rs.next()) {
                MauSac ms = new MauSac(rs.getString(1),
                        rs.getString(2), rs.getBoolean(3));
                listMauSac3.add(ms);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listMauSac3;
    }

    public int tongBanGhi() {
        int tong = 0;
        try {
            conn = DBConnect.getConnection();
            sql = "SELECT COUNT(*) FROM MauSac";
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

    public boolean checkTrungMS(String name1) {
        try {
            Connection conn = DBConnect.getConnection();
            String sql = "SELECT MS.MaMauSac FROM MauSac MS\n"
                    + "where MS.TenMauSac=?\n";
            pst = conn.prepareStatement(sql);
            pst.setObject(1, name1);
            rs = pst.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
