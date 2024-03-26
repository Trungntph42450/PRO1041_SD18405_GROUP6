/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.ChiTietSanPham;
import model.HoaDon;
import model.HoaDonChiTiet;
import model.SanPham;
import util.DBConnect;

/**
 *
 * @author lavuo
 */
public class ThongKeKhacRepository {
    PreparedStatement ps = null;
    Connection con = null;
    ResultSet rs = null;
    String sql = null;
    
    public List<HoaDonChiTiet> getList1(){
        List<HoaDonChiTiet> list1 = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            sql =  "SELECT sp.MaSanPham, sp.TenSanPham, ctsp.SoLuong, SUM(hdct.SoLuong)\n"
                    + "FROM HoaDonChiTiet hdct\n"
                    + "INNER JOIN HoaDon hd ON hdct.MaHoaDon = hd.MaHoaDon\n"
                    + "INNER JOIN ChiTietSanPham ctsp ON hdct.MaCTSP = ctsp.MaCTSP\n"
                    + "INNER JOIN SanPham sp ON ctsp.MaSanPham = sp.MaSanPham\n"
                    + "GROUP BY sp.MaSanPham, sp.TenSanPham, ctsp.SoLuong";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham(rs.getString(1), rs.getString(2));
                ChiTietSanPham ctsp = new ChiTietSanPham(sp, rs.getInt(3));
                HoaDonChiTiet cthd = new HoaDonChiTiet(ctsp, rs.getInt(4));
                list1.add(cthd);
                
            }
        } catch (Exception e) {
               e.printStackTrace();
               return null;
        }
        return list1;
        
    }
   public List<HoaDonChiTiet> getListTK1 (java.util.Date ngayBd ,java.util.Date ngayKt){
   
   List<HoaDonChiTiet> listTK1 = new ArrayList<>();
       try {
           con = DBConnect.getConnection();
           sql = "SELECT sp.MaSanPham, sp.TenSanPham, ctsp.SoLuong, SUM(hdct.SoLuong)\n"
                    + "FROM HoaDonChiTiet hdct\n"
                    + "INNER JOIN HoaDon hd ON hdct.MaHoaDon = hd.MaHoaDon\n"
                    + "INNER JOIN ChiTietSanPham ctsp ON hdct.MaCTSP = ctsp.MaCTSP\n"
                    + "INNER JOIN SanPham sp ON ctsp.MaSanPham = sp.MaSanPham\n"
                    + "WHERE hd.NgayTao BETWEEN CONCAT(?, ' ', '00:00:00')AND CONCAT(?, ' ', '23:59:59') "
                    + "GROUP BY sp.MaSanPham, sp.TenSanPham, ctsp.SoLuong";
           ps = con.prepareStatement(sql);
           ps.setDate(1, new java.sql.Date(ngayBd.getTime()));
           ps.setDate(2, new java.sql.Date(ngayKt.getTime()));
           rs = ps.executeQuery();
           while (rs.next()) {

           SanPham sp = new SanPham(rs.getString(1), rs.getString(2));
                ChiTietSanPham ctsp = new ChiTietSanPham(sp, rs.getInt(3));
                HoaDonChiTiet cthd = new HoaDonChiTiet(ctsp, rs.getInt(4));
                listTK1.add(cthd);
                
           }
       } catch (Exception e) {
       e.printStackTrace();
       return null;
       }
       return listTK1;
           }
   public List<SanPham> getTenSP(String tenSp){
   List<SanPham> listTenSP = new ArrayList<>();
       try {
           con = DBConnect.getConnection();
           sql =  "SELECT  MaSanPham,TenSanPham FROM SanPham";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
              SanPham sp = new SanPham(rs.getString(1), rs.getString(2));
            listTenSP.add(sp);
            }
       } catch (Exception e) {
           e.printStackTrace();
           return null;
       }
       return listTenSP;
   }
    public List<HoaDonChiTiet> getList2(String tenSP){
    List<HoaDonChiTiet> list2 = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            sql = "SELECT sp.MaSanPham, sp.TenSanPham, ctsp.SoLuong,hd.NgayTao, SUM(hdct.SoLuong)\n"
                    + "FROM HoaDonChiTiet hdct\n"
                    + "INNER JOIN HoaDon hd ON hdct.MaHoaDon = hd.MaHoaDon\n"
                    + "INNER JOIN ChiTietSanPham ctsp ON hdct.MaCTSP = ctsp.MaCTSP\n"
                    + "INNER JOIN SanPham sp ON ctsp.MaSanPham = sp.MaSanPham\n"
                    + "WHERE sp.TenSanPham like ?  \n"
                    + "GROUP BY sp.MaSanPham, sp.TenSanPham, ctsp.SoLuong,hd.NgayTao";
            ps = con.prepareStatement(sql);
            ps.setObject(1, '%' + tenSP +'%');
            rs = ps.executeQuery();
            while (rs.next()) {
                 SanPham sp = new SanPham(rs.getString(1), rs.getString(2));
                ChiTietSanPham ctsp = new ChiTietSanPham(sp, rs.getInt(3));
                HoaDon hd = new HoaDon(rs.getDate(4));
                HoaDonChiTiet cthd = new HoaDonChiTiet(ctsp, hd, rs.getInt(5));
                list2.add(cthd);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
            
        }
        return list2;
    }
    public List<HoaDonChiTiet> getListTK2(String tenSP,java.util.Date ngayBd ,java.util.Date ngayKt){
    List<HoaDonChiTiet> listTK2 = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            sql =  "SELECT sp.MaSanPham, sp.TenSanPham, ctsp.SoLuong,hd.NgayTao, SUM(hdct.SoLuong)\n"
                    + "FROM HoaDonChiTiet hdct\n"
                    + "INNER JOIN HoaDon hd ON hdct.MaHoaDon = hd.MaHoaDon\n"
                    + "INNER JOIN ChiTietSanPham ctsp ON hdct.MaCTSP = ctsp.MaCTSP\n"
                    + "INNER JOIN SanPham sp ON ctsp.MaSanPham = sp.MaSanPham\n"
                    + "WHERE sp.TenSanPham like ?  \n"
                    + "AND hd.NgayTao BETWEEN CONCAT(?, ' ', '00:00:00')AND CONCAT(?, ' ', '23:59:59')   \n"
                    + "GROUP BY sp.MaSanPham, sp.TenSanPham, ctsp.SoLuong,hd.NgayTao";
            ps = con.prepareStatement(sql);
            ps.setObject(1, '%' + tenSP +'%');
            ps.setDate(2, new java.sql.Date(ngayBd.getTime()));
            ps.setDate(3, new java.sql.Date(ngayKt.getTime()));
            rs = ps.executeQuery();
            while (rs.next()) {

               SanPham sp = new SanPham(rs.getString(1), rs.getString(2));
                ChiTietSanPham ctsp = new ChiTietSanPham(sp, rs.getInt(3));
                HoaDon hd = new HoaDon(rs.getDate(4));
                HoaDonChiTiet cthd = new HoaDonChiTiet(ctsp, hd, rs.getInt(5));
                listTK2.add(cthd);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listTK2;
    }
    
}
