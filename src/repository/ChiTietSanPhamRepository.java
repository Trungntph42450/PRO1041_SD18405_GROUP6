/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.ChatLieu;
import model.ChiTietSanPham;
import model.HoaDon;
import model.HoaDonChiTiet;
import model.KichThuoc;
import model.MauSac;
import model.SanPham;
import util.DBConnect;

/**
 *
 * @author user
 */
public class ChiTietSanPhamRepository {

    PreparedStatement pst = null;
    Connection conn = null;
    ResultSet rs = null;
    String sql = null;
    List<ChiTietSanPham> listChiTietSanPham = new ArrayList<>();

    public List<ChiTietSanPham> getAll() {
        listChiTietSanPham.clear();
        try {
            conn = DBConnect.getConnection();
            sql = "SELECT CTSP.MaCTSP,CTSP.MaSanPham,SP.TenSanPham,MS.MaMauSac,MS.TenMauSac,CL.MaChatLieu,CL.TenChatLieu,\n"
                    + "KT.MaKichThuoc,KT.TenKichThuoc,CTSP.SoLuong,CTSP.Gia,CTSP.TrangThai\n"
                    + "FROM ChiTietSanPham CTSP INNER JOIN ChatLieu CL On CL.MaChatLieu=CTSP.MaChatLieu\n"
                    + "INNER JOIn MauSac MS ON MS.MaMauSac=CTSP.MaMauSac\n"
                    + "INNER JOIN KichThuoc KT ON KT.MaKichThuoc=CTSP.MaKichThuoc \n"
                    + "INNER JOIN SanPham SP ON CTSP.MaSanPham=SP.MaSanPham order by CTSP.SoLuong DESC";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham(rs.getString(2), rs.getString(3));
                MauSac ms = new MauSac(rs.getString(4), rs.getString(5));
                ChatLieu cl = new ChatLieu(rs.getString(6), rs.getString(7));
                KichThuoc kt = new KichThuoc(rs.getString(8), rs.getString(9));
                ChiTietSanPham ctsp = new ChiTietSanPham(rs.getString(1), sp,
                        ms, cl, kt, rs.getInt(10), rs.getDouble(11), rs.getBoolean(12));
                listChiTietSanPham.add(ctsp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listChiTietSanPham;
    }

    public ChiTietSanPham getOne(String ma) {
        ChiTietSanPham ctsp = null;
        try {
            conn = DBConnect.getConnection();
            sql = "SELECT CTSP.MaCTSP,CTSP.MaSanPham,SP.TenSanPham,MS.MaMauSac,MS.TenMauSac,CL.MaChatLieu,CL.TenChatLieu,\n"
                    + " KT.MaKichThuoc,KT.TenKichThuoc,CTSP.SoLuong,CTSP.Gia,CTSP.TrangThai\n"
                    + " FROM ChiTietSanPham CTSP INNER JOIN ChatLieu CL On CL.MaChatLieu=CTSP.MaChatLieu\n"
                    + " INNER JOIn MauSac MS ON MS.MaMauSac=CTSP.MaMauSac\n"
                    + " INNER JOIN KichThuoc KT ON KT.MaKichThuoc=CTSP.MaKichThuoc \n"
                    + "	INNER JOIN SanPham SP ON CTSP.MaSanPham=SP.MaSanPham where CTSP.MaCTSP=? order by CTSP.TrangThai DESC";
            pst = conn.prepareStatement(sql);
            pst.setObject(1, ma);
            rs = pst.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham(rs.getString(2), rs.getString(3));
                MauSac ms = new MauSac(rs.getString(4), rs.getString(5));
                ChatLieu cl = new ChatLieu(rs.getString(6), rs.getString(7));
                KichThuoc kt = new KichThuoc(rs.getString(8), rs.getString(9));
                ctsp = new ChiTietSanPham(rs.getString(1), sp,
                        ms, cl, kt, rs.getInt(10), rs.getDouble(11), rs.getBoolean(12));
                listChiTietSanPham.add(ctsp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return ctsp;
    }

    public int them(ChiTietSanPham ctsp) {
        try {
            conn = DBConnect.getConnection();
            sql = "INSERT INTO ChiTietSanPham(MaSanPham,MaMauSac,MaChatLieu,MaKichThuoc,SoLuong,Gia,TrangThai) VALUES(?,?,?,?,?,?,?)";
            pst = conn.prepareStatement(sql);
            pst.setObject(1, ctsp.getSanPham().getMaSanPham());
            pst.setObject(2, ctsp.getMauSac().getMaMauSac());
            pst.setObject(3, ctsp.getChatLieu().getMaChatLieu());
            pst.setObject(4, ctsp.getKichThuoc().getMaKichThuoc());
            pst.setObject(5, ctsp.getSoLuong());
            pst.setObject(6, ctsp.getGia());
            pst.setObject(7, ctsp.isTrangThai());
            return pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int suas(ChiTietSanPham ctsp, String ma) {
        try {
            conn = DBConnect.getConnection();
            sql = "UPDATE ChiTietSanPham set MaSanPham=?,MaMauSac=?,MaChatLieu=?,MaKichThuoc=?,SoLuong=?,Gia=?,TrangThai=?\n"
                    + "where MaCTSP=?";
            pst = conn.prepareStatement(sql);
            pst.setObject(1, ctsp.getSanPham().getMaSanPham());
            pst.setObject(2, ctsp.getMauSac().getMaMauSac());
            pst.setObject(3, ctsp.getChatLieu().getMaChatLieu());
            pst.setObject(4, ctsp.getKichThuoc().getMaKichThuoc());
            pst.setObject(5, ctsp.getSoLuong());
            pst.setObject(6, ctsp.getGia());
            pst.setObject(7, ctsp.isTrangThai());
            pst.setObject(8, ma);
            return pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public List<ChiTietSanPham> getList(String name) {
        List<ChiTietSanPham> listChiTietSanPham3 = new ArrayList<>();
        try {
            conn = DBConnect.getConnection();
            sql = "SELECT CTSP.MaCTSP,CTSP.MaSanPham,SP.TenSanPham,MS.MaMauSac,MS.TenMauSac,CL.MaChatLieu,CL.TenChatLieu,\n"
                    + "KT.MaKichThuoc,KT.TenKichThuoc,CTSP.SoLuong,CTSP.Gia,CTSP.TrangThai\n"
                    + "FROM ChiTietSanPham CTSP INNER JOIN ChatLieu CL On CL.MaChatLieu=CTSP.MaChatLieu\n"
                    + "INNER JOIn MauSac MS ON MS.MaMauSac=CTSP.MaMauSac\n"
                    + "INNER JOIN KichThuoc KT ON KT.MaKichThuoc=CTSP.MaKichThuoc \n"
                    + "INNER JOIN SanPham SP ON CTSP.MaSanPham=SP.MaSanPham where SP.TenSanPham like ? or CTSP.MaCTSP=? order by CTSP.TrangThai DESC ";
            pst = conn.prepareStatement(sql);
            pst.setObject(1, '%' + name + '%');
            pst.setObject(2, name);
            rs = pst.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham(rs.getString(2), rs.getString(3));
                MauSac ms = new MauSac(rs.getString(4), rs.getString(5));
                ChatLieu cl = new ChatLieu(rs.getString(6), rs.getString(7));
                KichThuoc kt = new KichThuoc(rs.getString(8), rs.getString(9));
                ChiTietSanPham ctsp = new ChiTietSanPham(rs.getString(1), sp,
                        ms, cl, kt, rs.getInt(10), rs.getDouble(11), rs.getBoolean(12));
                listChiTietSanPham3.add(ctsp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listChiTietSanPham3;
    }

    public List<ChiTietSanPham> getListLoc(String name1, String name2, String name3, String name4) {
        List<ChiTietSanPham> listChiTietSanPham4 = new ArrayList<>();
        try {
            conn = DBConnect.getConnection();
            sql = "SELECT CTSP.MaCTSP,CTSP.MaSanPham,SP.TenSanPham,MS.MaMauSac,MS.TenMauSac,CL.MaChatLieu,CL.TenChatLieu,\n"
                    + "KT.MaKichThuoc,KT.TenKichThuoc,CTSP.SoLuong,CTSP.Gia,CTSP.TrangThai\n"
                    + "FROM ChiTietSanPham CTSP INNER JOIN ChatLieu CL On CL.MaChatLieu=CTSP.MaChatLieu\n"
                    + "INNER JOIn MauSac MS ON MS.MaMauSac=CTSP.MaMauSac\n"
                    + "INNER JOIN KichThuoc KT ON KT.MaKichThuoc=CTSP.MaKichThuoc \n"
                    + "INNER JOIN SanPham SP ON CTSP.MaSanPham=SP.MaSanPham"
                    + " where SP.TenSanPham like ? and CL.TenChatLieu like ? and MS.TenMauSac like ? and KT.TenKichThuoc like ?";
            pst = conn.prepareStatement(sql);
            pst.setObject(1, '%' + name1 + '%');
            pst.setObject(2, name2);
            pst.setObject(3, name3);
            pst.setObject(4, name4);
            rs = pst.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham(rs.getString(2), rs.getString(3));
                MauSac ms = new MauSac(rs.getString(4), rs.getString(5));
                ChatLieu cl = new ChatLieu(rs.getString(6), rs.getString(7));
                KichThuoc kt = new KichThuoc(rs.getString(8), rs.getString(9));
                ChiTietSanPham ctsp = new ChiTietSanPham(rs.getString(1), sp,
                        ms, cl, kt, rs.getInt(10), rs.getDouble(11), rs.getBoolean(12));
                listChiTietSanPham4.add(ctsp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listChiTietSanPham4;
    }

    public List<ChiTietSanPham> getListLocCL(String name1, String name2) {
        List<ChiTietSanPham> listChiTietSanPham4 = new ArrayList<>();
        try {
            conn = DBConnect.getConnection();
            sql = "SELECT CTSP.MaCTSP,CTSP.MaSanPham,SP.TenSanPham,MS.MaMauSac,MS.TenMauSac,CL.MaChatLieu,CL.TenChatLieu,\n"
                    + "KT.MaKichThuoc,KT.TenKichThuoc,CTSP.SoLuong,CTSP.Gia,CTSP.TrangThai\n"
                    + "FROM ChiTietSanPham CTSP INNER JOIN ChatLieu CL On CL.MaChatLieu=CTSP.MaChatLieu\n"
                    + "INNER JOIn MauSac MS ON MS.MaMauSac=CTSP.MaMauSac\n"
                    + "INNER JOIN KichThuoc KT ON KT.MaKichThuoc=CTSP.MaKichThuoc \n"
                    + "INNER JOIN SanPham SP ON CTSP.MaSanPham=SP.MaSanPham"
                    + " where SP.TenSanPham like ? and CL.TenChatLieu like ?";
            pst = conn.prepareStatement(sql);
            pst.setObject(1, '%' + name1 + '%');
            pst.setObject(2, name2);
            rs = pst.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham(rs.getString(2), rs.getString(3));
                MauSac ms = new MauSac(rs.getString(4), rs.getString(5));
                ChatLieu cl = new ChatLieu(rs.getString(6), rs.getString(7));
                KichThuoc kt = new KichThuoc(rs.getString(8), rs.getString(9));
                ChiTietSanPham ctsp = new ChiTietSanPham(rs.getString(1), sp,
                        ms, cl, kt, rs.getInt(10), rs.getDouble(11), rs.getBoolean(12));
                listChiTietSanPham4.add(ctsp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listChiTietSanPham4;
    }

    public List<ChiTietSanPham> getListLocMS(String name1, String name2) {
        List<ChiTietSanPham> listChiTietSanPham4 = new ArrayList<>();
        try {
            conn = DBConnect.getConnection();
            sql = "SELECT CTSP.MaCTSP,CTSP.MaSanPham,SP.TenSanPham,MS.MaMauSac,MS.TenMauSac,CL.MaChatLieu,CL.TenChatLieu,\n"
                    + "KT.MaKichThuoc,KT.TenKichThuoc,CTSP.SoLuong,CTSP.Gia,CTSP.TrangThai\n"
                    + "FROM ChiTietSanPham CTSP INNER JOIN ChatLieu CL On CL.MaChatLieu=CTSP.MaChatLieu\n"
                    + "INNER JOIn MauSac MS ON MS.MaMauSac=CTSP.MaMauSac\n"
                    + "INNER JOIN KichThuoc KT ON KT.MaKichThuoc=CTSP.MaKichThuoc \n"
                    + "INNER JOIN SanPham SP ON CTSP.MaSanPham=SP.MaSanPham"
                    + " where SP.TenSanPham like ? and MS.TenMauSac like ?";
            pst = conn.prepareStatement(sql);
            pst.setObject(1, '%' + name1 + '%');
            pst.setObject(2, name2);
            rs = pst.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham(rs.getString(2), rs.getString(3));
                MauSac ms = new MauSac(rs.getString(4), rs.getString(5));
                ChatLieu cl = new ChatLieu(rs.getString(6), rs.getString(7));
                KichThuoc kt = new KichThuoc(rs.getString(8), rs.getString(9));
                ChiTietSanPham ctsp = new ChiTietSanPham(rs.getString(1), sp,
                        ms, cl, kt, rs.getInt(10), rs.getDouble(11), rs.getBoolean(12));
                listChiTietSanPham4.add(ctsp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listChiTietSanPham4;
    }

    public List<ChiTietSanPham> getListLocKT(String name1, String name2) {
        List<ChiTietSanPham> listChiTietSanPham4 = new ArrayList<>();
        try {
            conn = DBConnect.getConnection();
            sql = "SELECT CTSP.MaCTSP,CTSP.MaSanPham,SP.TenSanPham,MS.MaMauSac,MS.TenMauSac,CL.MaChatLieu,CL.TenChatLieu,\n"
                    + "KT.MaKichThuoc,KT.TenKichThuoc,CTSP.SoLuong,CTSP.Gia,CTSP.TrangThai\n"
                    + "FROM ChiTietSanPham CTSP INNER JOIN ChatLieu CL On CL.MaChatLieu=CTSP.MaChatLieu\n"
                    + "INNER JOIn MauSac MS ON MS.MaMauSac=CTSP.MaMauSac\n"
                    + "INNER JOIN KichThuoc KT ON KT.MaKichThuoc=CTSP.MaKichThuoc \n"
                    + "INNER JOIN SanPham SP ON CTSP.MaSanPham=SP.MaSanPham"
                    + " where SP.TenSanPham like ? and KT.TenKichThuoc like ?";
            pst = conn.prepareStatement(sql);
            pst.setObject(1, '%' + name1 + '%');
            pst.setObject(2, name2);
            rs = pst.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham(rs.getString(2), rs.getString(3));
                MauSac ms = new MauSac(rs.getString(4), rs.getString(5));
                ChatLieu cl = new ChatLieu(rs.getString(6), rs.getString(7));
                KichThuoc kt = new KichThuoc(rs.getString(8), rs.getString(9));
                ChiTietSanPham ctsp = new ChiTietSanPham(rs.getString(1), sp,
                        ms, cl, kt, rs.getInt(10), rs.getDouble(11), rs.getBoolean(12));
                listChiTietSanPham4.add(ctsp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listChiTietSanPham4;
    }

    public List<ChiTietSanPham> getListLocCLMS(String name1, String name2, String name3) {
        List<ChiTietSanPham> listChiTietSanPham4 = new ArrayList<>();
        try {
            conn = DBConnect.getConnection();
            sql = "SELECT CTSP.MaCTSP,CTSP.MaSanPham,SP.TenSanPham,MS.MaMauSac,MS.TenMauSac,CL.MaChatLieu,CL.TenChatLieu,\n"
                    + "KT.MaKichThuoc,KT.TenKichThuoc,CTSP.SoLuong,CTSP.Gia,CTSP.TrangThai\n"
                    + "FROM ChiTietSanPham CTSP INNER JOIN ChatLieu CL On CL.MaChatLieu=CTSP.MaChatLieu\n"
                    + "INNER JOIn MauSac MS ON MS.MaMauSac=CTSP.MaMauSac\n"
                    + "INNER JOIN KichThuoc KT ON KT.MaKichThuoc=CTSP.MaKichThuoc \n"
                    + "INNER JOIN SanPham SP ON CTSP.MaSanPham=SP.MaSanPham"
                    + " where SP.TenSanPham like ? and CL.TenChatLieu like ? and MS.TenMauSac like ?";
            pst = conn.prepareStatement(sql);
            pst.setObject(1, '%' + name1 + '%');
            pst.setObject(2, name2);
            pst.setObject(3, name3);
            rs = pst.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham(rs.getString(2), rs.getString(3));
                MauSac ms = new MauSac(rs.getString(4), rs.getString(5));
                ChatLieu cl = new ChatLieu(rs.getString(6), rs.getString(7));
                KichThuoc kt = new KichThuoc(rs.getString(8), rs.getString(9));
                ChiTietSanPham ctsp = new ChiTietSanPham(rs.getString(1), sp,
                        ms, cl, kt, rs.getInt(10), rs.getDouble(11), rs.getBoolean(12));
                listChiTietSanPham4.add(ctsp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listChiTietSanPham4;
    }

    public List<ChiTietSanPham> getListLocMSKT(String name1, String name2, String name3) {
        List<ChiTietSanPham> listChiTietSanPham4 = new ArrayList<>();
        try {
            conn = DBConnect.getConnection();
            sql = "SELECT CTSP.MaCTSP,CTSP.MaSanPham,SP.TenSanPham,MS.MaMauSac,MS.TenMauSac,CL.MaChatLieu,CL.TenChatLieu,\n"
                    + "KT.MaKichThuoc,KT.TenKichThuoc,CTSP.SoLuong,CTSP.Gia,CTSP.TrangThai\n"
                    + "FROM ChiTietSanPham CTSP INNER JOIN ChatLieu CL On CL.MaChatLieu=CTSP.MaChatLieu\n"
                    + "INNER JOIn MauSac MS ON MS.MaMauSac=CTSP.MaMauSac\n"
                    + "INNER JOIN KichThuoc KT ON KT.MaKichThuoc=CTSP.MaKichThuoc \n"
                    + "INNER JOIN SanPham SP ON CTSP.MaSanPham=SP.MaSanPham"
                    + " where SP.TenSanPham like ?  and MS.TenMauSac like ? and KT.TenKichThuoc like ? ";
            pst = conn.prepareStatement(sql);
            pst.setObject(1, '%' + name1 + '%');
            pst.setObject(2, name2);
            pst.setObject(3, name3);
            rs = pst.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham(rs.getString(2), rs.getString(3));
                MauSac ms = new MauSac(rs.getString(4), rs.getString(5));
                ChatLieu cl = new ChatLieu(rs.getString(6), rs.getString(7));
                KichThuoc kt = new KichThuoc(rs.getString(8), rs.getString(9));
                ChiTietSanPham ctsp = new ChiTietSanPham(rs.getString(1), sp,
                        ms, cl, kt, rs.getInt(10), rs.getDouble(11), rs.getBoolean(12));
                listChiTietSanPham4.add(ctsp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listChiTietSanPham4;
    }

    public List<ChiTietSanPham> getListLocCLKT(String name1, String name2, String name3) {
        List<ChiTietSanPham> listChiTietSanPham4 = new ArrayList<>();
        try {
            conn = DBConnect.getConnection();
            sql = "SELECT CTSP.MaCTSP,CTSP.MaSanPham,SP.TenSanPham,MS.MaMauSac,MS.TenMauSac,CL.MaChatLieu,CL.TenChatLieu,\n"
                    + "KT.MaKichThuoc,KT.TenKichThuoc,CTSP.SoLuong,CTSP.Gia,CTSP.TrangThai\n"
                    + "FROM ChiTietSanPham CTSP INNER JOIN ChatLieu CL On CL.MaChatLieu=CTSP.MaChatLieu\n"
                    + "INNER JOIn MauSac MS ON MS.MaMauSac=CTSP.MaMauSac\n"
                    + "INNER JOIN KichThuoc KT ON KT.MaKichThuoc=CTSP.MaKichThuoc \n"
                    + "INNER JOIN SanPham SP ON CTSP.MaSanPham=SP.MaSanPham"
                    + " where SP.TenSanPham like ?  and CL.TenChatLieu like ? and KT.TenKichThuoc like ? ";
            pst = conn.prepareStatement(sql);
            pst.setObject(1, '%' + name1 + '%');
            pst.setObject(2, name2);
            pst.setObject(3, name3);
            rs = pst.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham(rs.getString(2), rs.getString(3));
                MauSac ms = new MauSac(rs.getString(4), rs.getString(5));
                ChatLieu cl = new ChatLieu(rs.getString(6), rs.getString(7));
                KichThuoc kt = new KichThuoc(rs.getString(8), rs.getString(9));
                ChiTietSanPham ctsp = new ChiTietSanPham(rs.getString(1), sp,
                        ms, cl, kt, rs.getInt(10), rs.getDouble(11), rs.getBoolean(12));
                listChiTietSanPham4.add(ctsp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listChiTietSanPham4;
    }

    public List<ChiTietSanPham> getListGia(double giaMin, double giaMax, String name) {
        List<ChiTietSanPham> listChiTietSanPham2 = new ArrayList<>();
        try {
            conn = DBConnect.getConnection();
            sql = "SELECT CTSP.MaCTSP,CTSP.MaSanPham,SP.TenSanPham,MS.MaMauSac,MS.TenMauSac,CL.MaChatLieu,CL.TenChatLieu,\n"
                    + "KT.MaKichThuoc,KT.TenKichThuoc,CTSP.SoLuong,CTSP.Gia,CTSP.TrangThai\n"
                    + "FROM ChiTietSanPham CTSP INNER JOIN ChatLieu CL On CL.MaChatLieu=CTSP.MaChatLieu\n"
                    + "INNER JOIn MauSac MS ON MS.MaMauSac=CTSP.MaMauSac\n"
                    + "INNER JOIN KichThuoc KT ON KT.MaKichThuoc=CTSP.MaKichThuoc \n"
                    + "INNER JOIN SanPham SP ON CTSP.MaSanPham=SP.MaSanPham where Gia between ? and ? and SP.TenSanPham like ? order by CTSP.TrangThai DESC";
            pst = conn.prepareStatement(sql);
            pst.setObject(1, giaMin);
            pst.setObject(2, giaMax);
            pst.setObject(3, name);
            rs = pst.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham(rs.getString(2), rs.getString(3));
                MauSac ms = new MauSac(rs.getString(4), rs.getString(5));
                ChatLieu cl = new ChatLieu(rs.getString(6), rs.getString(7));
                KichThuoc kt = new KichThuoc(rs.getString(8), rs.getString(9));
                ChiTietSanPham ctsp = new ChiTietSanPham(rs.getString(1), sp,
                        ms, cl, kt, rs.getInt(10), rs.getDouble(11), rs.getBoolean(12));
                listChiTietSanPham2.add(ctsp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listChiTietSanPham2;
    }

    public List<ChiTietSanPham> listPageCTSP(int index, String name) {
        List<ChiTietSanPham> listChiTietSanPham3 = new ArrayList<>();
        try {
            conn = DBConnect.getConnection();
            sql = "SELECT CTSP.MaCTSP,CTSP.MaSanPham,SP.TenSanPham,MS.MaMauSac,MS.TenMauSac,CL.MaChatLieu,CL.TenChatLieu,\n"
                    + "KT.MaKichThuoc,KT.TenKichThuoc,CTSP.SoLuong,CTSP.Gia,CTSP.TrangThai\n"
                    + "FROM ChiTietSanPham CTSP INNER JOIN ChatLieu CL On CL.MaChatLieu=CTSP.MaChatLieu\n"
                    + "INNER JOIn MauSac MS ON MS.MaMauSac=CTSP.MaMauSac\n"
                    + "INNER JOIN KichThuoc KT ON KT.MaKichThuoc=CTSP.MaKichThuoc \n"
                    + "INNER JOIN SanPham SP ON CTSP.MaSanPham=SP.MaSanPham where SP.TenSanPham like ?\n"
                    + "order by CTSP.TrangThai DESC\n"
                    + "offset ? rows fetch next 5 rows only";
            pst = conn.prepareStatement(sql);
            pst.setString(1, name);
            pst.setInt(2, (index - 1) * 5);

            rs = pst.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham(rs.getString(2), rs.getString(3));
                MauSac ms = new MauSac(rs.getString(4), rs.getString(5));
                ChatLieu cl = new ChatLieu(rs.getString(6), rs.getString(7));
                KichThuoc kt = new KichThuoc(rs.getString(8), rs.getString(9));
                ChiTietSanPham ctsp = new ChiTietSanPham(rs.getString(1), sp,
                        ms, cl, kt, rs.getInt(10), rs.getDouble(11), rs.getBoolean(12));
                listChiTietSanPham3.add(ctsp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listChiTietSanPham3;
    }

    public List<ChiTietSanPham> listPageCTSP2(int index) {
        List<ChiTietSanPham> listChiTietSanPham5 = new ArrayList<>();
        try {
            conn = DBConnect.getConnection();
            sql = "SELECT CTSP.MaCTSP,CTSP.MaSanPham,SP.TenSanPham,MS.MaMauSac,MS.TenMauSac,CL.MaChatLieu,CL.TenChatLieu,\n"
                    + "KT.MaKichThuoc,KT.TenKichThuoc,CTSP.SoLuong,CTSP.Gia,CTSP.TrangThai\n"
                    + "FROM ChiTietSanPham CTSP INNER JOIN ChatLieu CL On CL.MaChatLieu=CTSP.MaChatLieu\n"
                    + "INNER JOIn MauSac MS ON MS.MaMauSac=CTSP.MaMauSac\n"
                    + "INNER JOIN KichThuoc KT ON KT.MaKichThuoc=CTSP.MaKichThuoc \n"
                    + "INNER JOIN SanPham SP ON CTSP.MaSanPham=SP.MaSanPham \n"
                    + "order by CTSP.TrangThai DESC\n"
                    + "offset ? rows fetch next 5 rows only";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, (index - 1) * 5);
            rs = pst.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham(rs.getString(2), rs.getString(3));
                MauSac ms = new MauSac(rs.getString(4), rs.getString(5));
                ChatLieu cl = new ChatLieu(rs.getString(6), rs.getString(7));
                KichThuoc kt = new KichThuoc(rs.getString(8), rs.getString(9));
                ChiTietSanPham ctsp = new ChiTietSanPham(rs.getString(1), sp,
                        ms, cl, kt, rs.getInt(10), rs.getDouble(11), rs.getBoolean(12));
                listChiTietSanPham5.add(ctsp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listChiTietSanPham5;
    }

    public List<ChiTietSanPham> getDanhSachSPCT(String maHoadon) {
        List<ChiTietSanPham> listCTSP = new ArrayList<>();
        try {
            sql = "SELECT CTSP.MaCTSP, CTSP.MASANPHAM, SP.TenSanPham, HD.MAHOADON, HDCT.SOLUONG, CTSP.GIA, CTSP.TRANGTHAI \n"
                    + "FROM ChiTietSanPham CTSP JOIN HoaDonChiTiet HDCT \n"
                    + "ON CTSP.MaCTSP = HDCT.MaCTSP JOIN HoaDon HD ON HD.MaHoaDon = HDCT.MaHoaDon\n"
                    + "JOIN SanPham SP ON CTSP.MaSanPham = SP.MaSanPham WHERE HD.MaHoaDon = ?";
            conn = DBConnect.getConnection();
            pst = conn.prepareStatement(sql);
            pst.setObject(1, maHoadon);
            rs = pst.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon(rs.getString(4));
                SanPham sp = new SanPham(rs.getString(2), rs.getString(3));
                HoaDonChiTiet hdct = new HoaDonChiTiet(4);
                ChiTietSanPham ctsp = new ChiTietSanPham(rs.getString(1), sp, Integer.parseInt(rs.getString(5)), rs.getDouble(6), rs.getBoolean(7));
                listCTSP.add(ctsp);
            }
            return listCTSP;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int tongBanGhi(String name) {
        int tong = 0;
        try {
            conn = DBConnect.getConnection();
            sql = "SELECT COUNT(*) FROM ChiTietSanPham CTSP\n"
                    + "INNER JOIN SanPham SP ON SP.MaSanPham=CTSP.MaSanPham\n"
                    + "where SP.TenSanPham like ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, name);
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

    public int tongBanGhi2() {
        int tong = 0;
        try {
            conn = DBConnect.getConnection();
            sql = "SELECT COUNT(*) FROM ChiTietSanPham";
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

    public int updateTrangThaiSoLuong() {
        try {
            conn = DBConnect.getConnection();
            sql = "UPDATE ChiTietSanPham\n"
                    + "set TrangThai=0\n"
                    + "where SoLuong=0\n"
                    + "UPDATE ChiTietSanPham\n"
                    + "set TrangThai=1\n"
                    + "where SoLuong>0";
            pst = conn.prepareStatement(sql);
            return pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }




    public boolean checkTrungCTSP(String name1, String name2, String name3, String name4) {
        try {
            Connection conn = DBConnect.getConnection();
            String sql = "SELECT CTSP.MaChatLieu, CTSP.MaMauSac, CTSP.MaKichThuoc\n"
                    + "FROM ChiTietSanPham CTSP INNER JOIN SanPham SP ON SP.MaSanPham = CTSP.MaSanPham\n"
                    + "INNER JOIN ChatLieu CL ON CTSP.MaChatLieu = CL.MaChatLieu\n"
                    + "INNER JOIN MauSac MS ON CTSP.MaMauSac = MS.MaMauSac\n"
                    + "INNER JOIN KichThuoc KT ON CTSP.MaKichThuoc = KT.MaKichThuoc\n"
                    + "WHERE CL.TenChatLieu LIKE ? AND MS.TenMauSac LIKE ? AND KT.TenKichThuoc LIKE ? and SP.TenSanPham like ?\n"
                    + "GROUP BY CTSP.MaChatLieu, CTSP.MaMauSac, CTSP.MaKichThuoc\n"
                    + "HAVING COUNT(*) >= 1";
            pst = conn.prepareStatement(sql);
            pst.setObject(1, name1);
            pst.setObject(2, name2);
            pst.setObject(3, name3);
            pst.setObject(4, name4);
            rs = pst.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<ChiTietSanPham> listPageCTSPDoiHang(int index, String name, String tenCL) {
        List<ChiTietSanPham> listChiTietSanPham6 = new ArrayList<>();
        try {
            conn = DBConnect.getConnection();
            sql = "SELECT CTSP.MaCTSP,CTSP.MaSanPham,SP.TenSanPham,MS.MaMauSac,MS.TenMauSac,CL.MaChatLieu,CL.TenChatLieu,\n"
                    + "KT.MaKichThuoc,KT.TenKichThuoc,CTSP.SoLuong,CTSP.Gia,CTSP.TrangThai\n"
                    + "FROM ChiTietSanPham CTSP INNER JOIN ChatLieu CL On CL.MaChatLieu=CTSP.MaChatLieu\n"
                    + "INNER JOIn MauSac MS ON MS.MaMauSac=CTSP.MaMauSac\n"
                    + "INNER JOIN KichThuoc KT ON KT.MaKichThuoc=CTSP.MaKichThuoc \n"
                    + "INNER JOIN SanPham SP ON CTSP.MaSanPham=SP.MaSanPham where SP.TenSanPham like ? and CL.TenChatLieu like ?\n"
                    + "order by CTSP.TrangThai DESC\n"
                    + "offset ? rows fetch next 20 rows only";
            pst = conn.prepareStatement(sql);
            pst.setString(1, '%' + name + '%');
            pst.setString(2, tenCL);
            pst.setInt(3, (index - 1) * 20);
            rs = pst.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham(rs.getString(2), rs.getString(3));
                MauSac ms = new MauSac(rs.getString(4), rs.getString(5));
                ChatLieu cl = new ChatLieu(rs.getString(6), rs.getString(7));
                KichThuoc kt = new KichThuoc(rs.getString(8), rs.getString(9));
                ChiTietSanPham ctsp = new ChiTietSanPham(rs.getString(1), sp,
                        ms, cl, kt, rs.getInt(10), rs.getDouble(11), rs.getBoolean(12));
                listChiTietSanPham6.add(ctsp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listChiTietSanPham6;
    }

    public List<ChiTietSanPham> getListLocMauSacOrKichThuoc(String name1, String name2, String name3, String name4) {
        List<ChiTietSanPham> listChiTietSanPham7 = new ArrayList<>();
        try {
            conn = DBConnect.getConnection();
            sql = "  SELECT CTSP.MaCTSP,CTSP.MaSanPham,SP.TenSanPham,MS.MaMauSac,MS.TenMauSac,CL.MaChatLieu,CL.TenChatLieu,\n"
                    + "                   KT.MaKichThuoc,KT.TenKichThuoc,CTSP.SoLuong,CTSP.Gia,CTSP.TrangThai\n"
                    + "                   FROM ChiTietSanPham CTSP INNER JOIN ChatLieu CL On CL.MaChatLieu=CTSP.MaChatLieu\n"
                    + "                    INNER JOIn MauSac MS ON MS.MaMauSac=CTSP.MaMauSac\n"
                    + "                    INNER JOIN KichThuoc KT ON KT.MaKichThuoc=CTSP.MaKichThuoc \n"
                    + "                   INNER JOIN SanPham SP ON CTSP.MaSanPham=SP.MaSanPham"
                    + " where  SP.TenSanPham like ? and CL.TenChatLieu like ? and " + name3 + " like ?";
            pst = conn.prepareStatement(sql);
            pst.setObject(1, name1);
            pst.setObject(2, name2);
            pst.setObject(3, name4);
            rs = pst.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham(rs.getString(2), rs.getString(3));
                MauSac ms = new MauSac(rs.getString(4), rs.getString(5));
                ChatLieu cl = new ChatLieu(rs.getString(6), rs.getString(7));
                KichThuoc kt = new KichThuoc(rs.getString(8), rs.getString(9));
                ChiTietSanPham ctsp = new ChiTietSanPham(rs.getString(1), sp,
                        ms, cl, kt, rs.getInt(10), rs.getDouble(11), rs.getBoolean(12));
                listChiTietSanPham7.add(ctsp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listChiTietSanPham7;
    }

    public String getTenCL(String maCTSP) {
        String maCL = null;
        try {
            sql = "Select CL.TenChatLieu From ChiTietSanPham CTSP join ChatLieu CL\n"
                    + "ON CTSP.MaChatLieu=CL.MaChatLieu where CTSP.MaCTSP = ?";
            conn = DBConnect.getConnection();
            pst = conn.prepareStatement(sql);
            pst.setObject(1, maCTSP);
            rs = pst.executeQuery();
            while (rs.next()) {
                maCL = rs.getString(1);
            }
            return maCL;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int tangSoLuong(int soLuong, String maCTSP) {
        try {
            conn = DBConnect.getConnection();
            sql = "UPDATE ChiTietSanPham set SoLuong=SoLuong+?\n"
                    + "where MaCTSP=?";
            pst = conn.prepareStatement(sql);
            pst.setObject(1, soLuong);
            pst.setObject(2, maCTSP);
            return pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int giamSoLuong(int soLuong, String maCTSP) {
        try {
            conn = DBConnect.getConnection();
            sql = "UPDATE ChiTietSanPham set SoLuong=SoLuong-?\n"
                    + "where MaCTSP=?";
            pst = conn.prepareStatement(sql);
            pst.setObject(1, soLuong);
            pst.setObject(2, maCTSP);
            return pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int capNhatSoLuongThanhToanCong(int soLuong, String ma) {
        int so = 0;
        try {
            conn = DBConnect.getConnection();
            sql = "UPDATE ChiTietSanPham set SoLuong=SoLuong-?\n"
                    + "where MaCTSP=?";
            pst = conn.prepareStatement(sql);
            pst.setObject(1, soLuong);
            pst.setObject(2, ma);
            so += pst.executeUpdate();
            return so;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int capNhatSoLuongThanhToanTru(int soLuong, String ma) {
        int so = 0;
        try {
            conn = DBConnect.getConnection();
            sql = "UPDATE ChiTietSanPham set SoLuong=SoLuong+?\n"
                    + "where MaCTSP=?";
            pst = conn.prepareStatement(sql);
            pst.setObject(1, soLuong);
            pst.setObject(2, ma);
            so += pst.executeUpdate();
            return so;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int capNhatSoLuongThanhToan(int soLuong, String ma) {
        try {
            conn = DBConnect.getConnection();
            sql = "UPDATE ChiTietSanPham set SoLuong=? where MaCTSP=?";
            pst = conn.prepareStatement(sql);
            pst.setObject(1, soLuong);
            pst.setObject(2, ma);
            return pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public List<ChiTietSanPham> getThuocTinh(String maCTSP) {
        List<ChiTietSanPham> listChiTietSanPham8 = new ArrayList<>();
        try {
            conn = DBConnect.getConnection();
            sql = "SELECT CTSP.MaCTSP,CTSP.MaSanPham,SP.TenSanPham,MS.MaMauSac,MS.TenMauSac,CL.MaChatLieu,CL.TenChatLieu,\n"
                    + "KT.MaKichThuoc,KT.TenKichThuoc,CTSP.SoLuong,CTSP.Gia,CTSP.TrangThai\n"
                    + "FROM ChiTietSanPham CTSP INNER JOIN ChatLieu CL On CL.MaChatLieu=CTSP.MaChatLieu\n"
                    + "INNER JOIn MauSac MS ON MS.MaMauSac=CTSP.MaMauSac\n"
                    + "INNER JOIN KichThuoc KT ON KT.MaKichThuoc=CTSP.MaKichThuoc\n"
                    + "INNER JOIN SanPham SP ON CTSP.MaSanPham=SP.MaSanPham where CTSP.MaCTSP = ?";
            pst = conn.prepareStatement(sql);
            pst.setObject(1, maCTSP);
            rs = pst.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham(rs.getString(2), rs.getString(3));
                MauSac ms = new MauSac(rs.getString(4), rs.getString(5));
                ChatLieu cl = new ChatLieu(rs.getString(6), rs.getString(7));
                KichThuoc kt = new KichThuoc(rs.getString(8), rs.getString(9));
                ChiTietSanPham ctsp = new ChiTietSanPham(rs.getString(1), sp,
                        ms, cl, kt, rs.getInt(10), rs.getDouble(11), rs.getBoolean(12));
                listChiTietSanPham8.add(ctsp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listChiTietSanPham8;
    }
}
