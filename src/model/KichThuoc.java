/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Nguyen Thanh Trung
 */
public class KichThuoc {

    private String maKichThuoc;
    private String tenKichThuoc;
    private boolean trangThai;

    public KichThuoc() {
    }

    public KichThuoc(String maKichThuoc) {
        this.maKichThuoc = maKichThuoc;
    }

    public KichThuoc(String maKichThuoc, String tenKichThuoc) {
        this.maKichThuoc = maKichThuoc;
        this.tenKichThuoc = tenKichThuoc;
    }

    public KichThuoc(String maKichThuoc, String tenKichThuoc, boolean trangThai) {
        this.maKichThuoc = maKichThuoc;
        this.tenKichThuoc = tenKichThuoc;
        this.trangThai = trangThai;
    }

    public String getMaKichThuoc() {
        return maKichThuoc;
    }

    public void setMaKichThuoc(String maKichThuoc) {
        this.maKichThuoc = maKichThuoc;
    }

    public String getTenKichThuoc() {
        return tenKichThuoc;
    }

    public void setTenKichThuoc(String tenKichThuoc) {
        this.tenKichThuoc = tenKichThuoc;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return tenKichThuoc;
    }
}
