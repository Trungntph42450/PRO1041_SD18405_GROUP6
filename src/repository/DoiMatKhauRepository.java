/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import util.DBConnect;
/**
 *
 * @author Nguyen Thanh Trung
 */
public class DoiMatKhauRepository {

    PreparedStatement pst = null;
    Connection conn = null;
    ResultSet rs = null;
    String sql = null;

    public boolean checkMaTK(String maTK) {
        try {
            conn = DBConnect.getConnection();
            sql = "Select MaTK from TaiKhoan where MaTK=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, maTK);
            rs = pst.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public boolean checkPassword(String maTK, String passWord) {
        sql = "SELECT PassWord FROM TaiKhoan WHERE MaTK = ?";
        try {
            conn = DBConnect.getConnection();
            pst = conn.prepareStatement(sql);
            pst.setString(1, maTK);
            rs = pst.executeQuery();
            if (rs.next()) {
                String currentPassword = rs.getString("PassWord");
                System.out.println("isCurrentPasswordValid : " + currentPassword);
                if (currentPassword.equals(passWord)) {
                    return true;
                }
            }
        } catch (Exception ex) {
            System.out.println("isCurrentPasswordValid(): " + ex.toString());
            return false;
        }
        return false;
    }

    public int updatePass(String userName, String passWord) {
        sql = "update TaiKhoan set Password=? where MaTK=?";
        try {
            conn = DBConnect.getConnection();
            pst = conn.prepareStatement(sql);
            pst.setString(1, passWord);
            pst.setString(2, userName);
            return pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
