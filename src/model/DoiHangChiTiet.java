/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Nguyen Thanh Trung
 */
public class DoiHangChiTiet {

    private String maDHCT;
    private DoiHang doiHang;
    private HoaDonChiTiet hoaDonChiTiet;
    private ChiTietSanPham chiTietSanPham;
    private int soLuong;
    private boolean trangThai;
    private String moTa;

    public DoiHangChiTiet() {
    }

    public DoiHangChiTiet(String maDHCT, DoiHang doiHang, HoaDonChiTiet hoaDonChiTiet, ChiTietSanPham chiTietSanPham, int soLuong) {
        this.maDHCT = maDHCT;
        this.doiHang = doiHang;
        this.hoaDonChiTiet = hoaDonChiTiet;
        this.chiTietSanPham = chiTietSanPham;
        this.soLuong = soLuong;
    }

    public DoiHangChiTiet(ChiTietSanPham chiTietSanPham, boolean trangThai, String moTa) {
        this.chiTietSanPham = chiTietSanPham;
        this.trangThai = trangThai;
        this.moTa = moTa;
    }

    public DoiHangChiTiet(String maDHCT, DoiHang doiHang, HoaDonChiTiet hoaDonChiTiet, ChiTietSanPham chiTietSanPham, boolean trangThai, String moTa) {
        this.maDHCT = maDHCT;
        this.doiHang = doiHang;
        this.hoaDonChiTiet = hoaDonChiTiet;
        this.chiTietSanPham = chiTietSanPham;
        this.trangThai = trangThai;
        this.moTa = moTa;
    }

    public DoiHangChiTiet(String maDHCT, DoiHang doiHang, HoaDonChiTiet hoaDonChiTiet) {
        this.maDHCT = maDHCT;
        this.doiHang = doiHang;
        this.hoaDonChiTiet = hoaDonChiTiet;
    }

    public DoiHangChiTiet(String maDHCT, DoiHang doiHang, HoaDonChiTiet hoaDonChiTiet, ChiTietSanPham chiTietSanPham, int soLuong, String moTa) {
        this.maDHCT = maDHCT;
        this.doiHang = doiHang;
        this.hoaDonChiTiet = hoaDonChiTiet;
        this.chiTietSanPham = chiTietSanPham;
        this.soLuong = soLuong;
        this.moTa = moTa;
    }

    public DoiHangChiTiet(String maDHCT, ChiTietSanPham chiTietSanPham, int soLuong, String moTa) {
        this.maDHCT = maDHCT;
        this.chiTietSanPham = chiTietSanPham;
        this.soLuong = soLuong;
        this.moTa = moTa;
    }

    public DoiHangChiTiet(String maDHCT, DoiHang doiHang, HoaDonChiTiet hoaDonChiTiet, ChiTietSanPham chiTietSanPham, int soLuong, boolean trangThai, String moTa) {
        this.maDHCT = maDHCT;
        this.doiHang = doiHang;
        this.hoaDonChiTiet = hoaDonChiTiet;
        this.chiTietSanPham = chiTietSanPham;
        this.soLuong = soLuong;
        this.trangThai = trangThai;
        this.moTa = moTa;
    }

    public DoiHangChiTiet(HoaDonChiTiet hoaDonChiTiet, ChiTietSanPham chiTietSanPham, int soLuong, String moTa) {
        this.hoaDonChiTiet = hoaDonChiTiet;
        this.chiTietSanPham = chiTietSanPham;
        this.soLuong = soLuong;
        this.moTa = moTa;
    }

    public String getMaDHCT() {
        return maDHCT;
    }

    public void setMaDHCT(String maDHCT) {
        this.maDHCT = maDHCT;
    }

    public DoiHang getDoiHang() {
        return doiHang;
    }

    public void setDoiHang(DoiHang doiHang) {
        this.doiHang = doiHang;
    }

    public HoaDonChiTiet getHoaDonChiTiet() {
        return hoaDonChiTiet;
    }

    public void setHoaDonChiTiet(HoaDonChiTiet hoaDonChiTiet) {
        this.hoaDonChiTiet = hoaDonChiTiet;
    }

    public ChiTietSanPham getChiTietSanPham() {
        return chiTietSanPham;
    }

    public void setChiTietSanPham(ChiTietSanPham chiTietSanPham) {
        this.chiTietSanPham = chiTietSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
}
