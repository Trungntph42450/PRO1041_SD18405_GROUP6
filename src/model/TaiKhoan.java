/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Nguyen Thanh Trung
 */
public class TaiKhoan {

    private String maTaiKhoan;
    private String UserName;
    private String PassWord;
    private String role;
    private boolean trangThai;

    public TaiKhoan() {
    }

    public TaiKhoan(String PassWord) {
        this.PassWord = PassWord;
    }

    public TaiKhoan(String maTaiKhoan, String UserName, String PassWord, String role, boolean trangThai) {
        this.maTaiKhoan = maTaiKhoan;
        this.UserName = UserName;
        this.PassWord = PassWord;
        this.role = role;
        this.trangThai = trangThai;
    }

    public String getMaTaiKhoan() {
        return maTaiKhoan;
    }

    public void setMaTaiKhoan(String maTaiKhoan) {
        this.maTaiKhoan = maTaiKhoan;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getPassWord() {
        return PassWord;
    }

    public void setPassWord(String PassWord) {
        this.PassWord = PassWord;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }
}
