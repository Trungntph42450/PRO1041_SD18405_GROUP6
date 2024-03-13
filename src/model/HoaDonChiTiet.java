/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Nguyen Thanh Trung
 */
public class HoaDonChiTiet {

    private String maHDCT;
    private ChiTietSanPham ctsp;
    private HoaDon HD;
    private int soLuong;
    private Double donGia;
    private String ghiChu;
    private Boolean trangThai;

    public HoaDonChiTiet(int soLuong) {
        this.soLuong = soLuong;
    }

    public HoaDonChiTiet(ChiTietSanPham ctsp, int soLuong) {
        this.ctsp = ctsp;
        this.soLuong = soLuong;
    }

    public HoaDonChiTiet(ChiTietSanPham ctsp) {
        this.ctsp = ctsp;
    }

    public HoaDonChiTiet(String maHDCT) {
        this.maHDCT = maHDCT;
    }

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(ChiTietSanPham ctsp, int soLuong, Boolean trangThai) {
        this.ctsp = ctsp;
        this.soLuong = soLuong;
        this.trangThai = trangThai;
    }

    public HoaDonChiTiet(String maHDCT, ChiTietSanPham ctsp, int soLuong) {
        this.maHDCT = maHDCT;
        this.ctsp = ctsp;
        this.soLuong = soLuong;
    }

    public HoaDonChiTiet(ChiTietSanPham ctsp, int soLuong, Double donGia) {
        this.ctsp = ctsp;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public HoaDonChiTiet(String maHDCT, ChiTietSanPham ctsp, HoaDon HD, int soLuong, Double donGia) {
        this.maHDCT = maHDCT;
        this.ctsp = ctsp;
        this.HD = HD;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public HoaDonChiTiet(String maHDCT, ChiTietSanPham ctsp, HoaDon HD, int soLuong, Double donGia, String ghiChu, Boolean trangThai) {
        this.maHDCT = maHDCT;
        this.ctsp = ctsp;
        this.HD = HD;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.ghiChu = ghiChu;
        this.trangThai = trangThai;
    }

    public HoaDonChiTiet(String maHDCT, ChiTietSanPham ctsp) {
        this.maHDCT = maHDCT;
        this.ctsp = ctsp;
    }

    public HoaDonChiTiet(ChiTietSanPham ctsp, HoaDon HD, int soLuong) {
        this.ctsp = ctsp;
        this.HD = HD;
        this.soLuong = soLuong;
    }

    public HoaDonChiTiet(HoaDon HD, ChiTietSanPham ctsp, int soLuong, double donGia) {
        this.ctsp = ctsp;
        this.HD = HD;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public HoaDonChiTiet(HoaDon HD) {
        this.HD = HD;
    }

    public String getMaHDCT() {
        return maHDCT;
    }

    public void setMaHDCT(String maHDCT) {
        this.maHDCT = maHDCT;
    }

    public ChiTietSanPham getCtsp() {
        return ctsp;
    }

    public void setCtsp(ChiTietSanPham ctsp) {
        this.ctsp = ctsp;
    }

    public HoaDon getHD() {
        return HD;
    }

    public void setHD(HoaDon HD) {
        this.HD = HD;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public Double getDonGia() {
        return donGia;
    }

    public void setDonGia(Double donGia) {
        this.donGia = donGia;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public Boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Boolean trangThai) {
        this.trangThai = trangThai;
    }
}
