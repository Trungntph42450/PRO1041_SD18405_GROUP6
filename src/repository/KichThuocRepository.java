/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.KichThuoc;
import util.DBConnect;
/**
 *
 * @author ADMIN
 */
public class KichThuocRepository {
    PreparedStatement pst = null;
    Connection conn = null;
    ResultSet rs = null;
    String sql = null;
    List<KichThuoc> listKichThuoc = new ArrayList<>();
    
    public List<KichThuoc> getAll(){
        listKichThuoc.clear();
        try {
            conn = DBConnect.getConnection();
            sql  = "SELECT*FROM KichThuoc where TrangThai=1";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {                
                KichThuoc kt = new KichThuoc(rs.getString(1),
                        rs.getString(2), rs.getBoolean(3));
                listKichThuoc.add(kt);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listKichThuoc;
    }
    
    public KichThuoc getOne(String ma){
        KichThuoc kt = null;
        try {
            conn = DBConnect.getConnection();
            sql = "SELECT*FROM KichThuoc where MaKichThuoc=?";
            pst = conn.prepareStatement(sql);
            pst.setObject(1, ma);
            rs = pst.executeQuery();
            while (rs.next()) {                
                 kt = new KichThuoc(rs.getString(1),
                        rs.getString(2), rs.getBoolean(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return kt;
    }
    
    public int them(KichThuoc kt){
        try {
            conn = DBConnect.getConnection();
            sql = "INSERT INTO KichThuoc VALUES(?,?,?)";
            pst = conn.prepareStatement(sql);
            pst.setObject(1, kt.getMaKichThuoc());
            pst.setObject(2, kt.getTenKichThuoc());
            pst.setObject(3, kt.isTrangThai());
            return  pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public int sua(KichThuoc kt, String ma){
        try {
             conn = DBConnect.getConnection();
             sql = "UPDATE KichThuoc set TenKichThuoc=?,TrangThai=? where MaKichThuoc=?";
             pst = conn.prepareStatement(sql);
             pst.setObject(1, kt.getTenKichThuoc());
             pst.setObject(2, kt.isTrangThai());
             pst.setObject(3, ma);
             return  pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public List<KichThuoc> getList(String name){
        List<KichThuoc> listKichThuoc4 = new ArrayList<>();
        try {
            conn = DBConnect.getConnection();
            sql = "SELECT*FROM KichThuoc where tenKichThuoc like ?";
            pst = conn.prepareStatement(sql);
            pst.setObject(1, name);
            rs = pst.executeQuery();
            while (rs.next()) {                
                 KichThuoc kt = new KichThuoc(rs.getString(1),
                        rs.getString(2), rs.getBoolean(3));
                listKichThuoc4.add(kt);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listKichThuoc4;
    }
    
    public List<KichThuoc> listPageKT(int index){
        List<KichThuoc> listKichThuoc3 = new ArrayList<>();
        try {
            conn = DBConnect.getConnection();
            sql = "SELECT*FROM KichThuoc\n"
                    + "order by MaKichThuoc\n"
                    + "OFFSET ? rows fetch next 5 rows only";
            pst = conn.prepareStatement(sql);
            pst.setObject(1, (index - 1) * 5);
            rs = pst.executeQuery();
            while (rs.next()) {                
                 KichThuoc kt = new KichThuoc(rs.getString(1),
                        rs.getString(2), rs.getBoolean(3));
                listKichThuoc3.add(kt);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listKichThuoc3;
    }
    
    public int tongBanGhi(){
        int tong = 0;
        try {
            conn = DBConnect.getConnection();
            sql = "SELECT COUNT(*) FROM KichThuoc";
            pst =conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if(rs.next()){
                tong = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return tong;
    }
    
    public boolean checkTrungKT(String name1){
        try {
            Connection conn = DBConnect.getConnection();
            String sql = "SELECT KT.MaKichThuoc FROM KichThuoc KT\n"
                    + "where KT.TenKichThuoc=?\n";
            pst =conn.prepareStatement(sql);
            pst.setObject(1, name1);
            rs = pst.executeQuery();
            while (rs.next()) {                
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  false;
    }
}
