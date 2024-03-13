/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author Nguyen Thanh Trung
 */
public class DoiHang {

    private String maDoiHang;
    private HoaDon hoaDon;
    private NhanVien nhanVien;
    private Date ngayDoiTra;
    private String trangThai;

    public DoiHang() {
    }

    public DoiHang(String maDoiHang) {
        this.maDoiHang = maDoiHang;
    }

    public DoiHang(String maDoiHang, HoaDon hoaDon) {
        this.maDoiHang = maDoiHang;
        this.hoaDon = hoaDon;
    }

    public DoiHang(String maDoiHang, HoaDon hoaDon, NhanVien nhanVien) {
        this.maDoiHang = maDoiHang;
        this.hoaDon = hoaDon;
        this.nhanVien = nhanVien;
    }

    public DoiHang(String maDoiHang, HoaDon hoaDon, NhanVien nhanVien, Date ngayDoiTra, String trangThai) {
        this.maDoiHang = maDoiHang;
        this.hoaDon = hoaDon;
        this.nhanVien = nhanVien;
        this.ngayDoiTra = ngayDoiTra;
        this.trangThai = trangThai;
    }

    public String getMaDoiHang() {
        return maDoiHang;
    }

    public void setMaDoiHang(String maDoiHang) {
        this.maDoiHang = maDoiHang;
    }

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public Date getNgayDoiTra() {
        return ngayDoiTra;
    }

    public void setNgayDoiTra(Date ngayDoiTra) {
        this.ngayDoiTra = ngayDoiTra;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

}
