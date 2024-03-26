/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.ChatLieu;
import util.DBConnect;

/**
 *
 * @author Nguyen Thanh Trung
 */
public class ChatLieuRepository {
      
    PreparedStatement pst = null;
    ResultSet rs = null;
    String sql = null;
    Connection conn = null;
    List<ChatLieu> listChatLieu = new ArrayList<>();
    
    public List<ChatLieu> getAll(){
        listChatLieu.clear();
        try {
            conn = DBConnect.getConnection();
            sql = "SELECT*FROM ChatLieu where TrangThai=1";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {                
                 ChatLieu cl = new ChatLieu(rs.getString(1),
                        rs.getString(2), rs.getBoolean(3));
                listChatLieu.add(cl);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listChatLieu;
    }
    
    public ChatLieu getOne(String ma){
        ChatLieu cl = null;
        try {
            conn = DBConnect.getConnection();
            sql = "SELECT*FROM ChatLieu where MaChatLieu=?";
            pst = conn.prepareStatement(sql);
            pst.setObject(1, ma);
            rs = pst.executeQuery();
            while (rs.next()) {                
                cl = new ChatLieu(rs.getString(1),
                        rs.getString(2), rs.getBoolean(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return cl;
    }
    
    public int them(ChatLieu cl){
        try {
            conn = DBConnect.getConnection();
            sql = "INSERT INTO ChatLieu VALUES(?,?,?)";
            pst = conn.prepareStatement(sql);
            pst.setObject(1, cl.getMaChatLieu());
            pst.setObject(2, cl.getTenChatLieu());
            pst.setObject(3, cl.isTrangThai());
            return  pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public int sua(ChatLieu cl, String ma){
        try {
            conn = DBConnect.getConnection();
            sql = "UPDATE ChatLieu set TenChatLieu=?,TrangThai=? where MaChatLieu=?";
            pst = conn.prepareStatement(sql);
            pst.setObject(1, cl.getTenChatLieu());
            pst.setObject(2, cl.isTrangThai());
            pst.setObject(3, ma);
            return  pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public List<ChatLieu> getList(String name){
        List<ChatLieu> listChatLieu4 = new ArrayList<>();
        try {
            conn = DBConnect.getConnection();
            sql = "SELECT*FROM ChatLieu where tenChatLieu like ?";
            pst = conn.prepareStatement(sql);
            pst.setObject(1, '%' + name + '%');
            rs = pst.executeQuery();
            while (rs.next()) {                
                ChatLieu cl = new ChatLieu(rs.getString(1),
                        rs.getString(2), rs.getBoolean(3));
                listChatLieu4.add(cl);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listChatLieu4;
    }
    
    public List<ChatLieu> listPageCL(int index){
        List<ChatLieu> listChatLieu3 = new ArrayList<>();
        try {
            conn = DBConnect.getConnection();
            sql = "SELECT*FROM ChatLieu\n"
                    + "order by MaChatLieu\n"
                    + "OFFSET ? rows fetch next 5 rows only";
            pst = conn.prepareStatement(sql);
            pst.setObject(1, (index - 1) * 5);
            rs = pst.executeQuery();
            while (rs.next()) {                
                 ChatLieu cl = new ChatLieu(rs.getString(1),
                        rs.getString(2), rs.getBoolean(3));
                listChatLieu3.add(cl);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listChatLieu3;
    }
    
    public int tongBanGhi(){
        int tong = 0;
        try {
            conn = DBConnect.getConnection();
            sql = "SELECT COUNT(*) FROM ChatLieu";
            pst = conn.prepareStatement(sql);
            rs  = pst.executeQuery();
            if(rs.next()){
                tong = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return tong;
    }
    
    public boolean checkTrungCL(String name1){
        try {
            conn = DBConnect.getConnection();
            sql = "SELECT CL.MaChatLieu FROM ChatLieu CL\n"
                    + "where CL.TenChatLieu =?";
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
