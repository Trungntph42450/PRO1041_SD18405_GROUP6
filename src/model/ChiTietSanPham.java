/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Nguyen Thanh Trung
 */
public class ChiTietSanPham {

    private String maChiTietSanPham;
    private SanPham sanPham;
    private MauSac mauSac;
    private ChatLieu chatLieu;
    private KichThuoc kichThuoc;
    private int soLuong;
    private double gia;
    private boolean trangThai;

    public ChiTietSanPham() {
    }

    public ChiTietSanPham(String maChiTietSanPham, SanPham sanPham, int soLuong, double gia, boolean trangThai) {
        this.maChiTietSanPham = maChiTietSanPham;
        this.sanPham = sanPham;
        this.soLuong = soLuong;
        this.gia = gia;
        this.trangThai = trangThai;
    }

    public ChiTietSanPham(String maChiTietSanPham, SanPham sanPham, MauSac mauSac, ChatLieu chatLieu, KichThuoc kichThuoc, double gia) {
        this.maChiTietSanPham = maChiTietSanPham;
        this.sanPham = sanPham;
        this.mauSac = mauSac;
        this.chatLieu = chatLieu;
        this.kichThuoc = kichThuoc;
        this.gia = gia;
    }

    public ChiTietSanPham(SanPham sanPham, int soLuong) {
        this.sanPham = sanPham;
        this.soLuong = soLuong;
    }

    public ChiTietSanPham(String maChiTietSanPham, double gia) {
        this.maChiTietSanPham = maChiTietSanPham;
        this.gia = gia;
    }

    public ChiTietSanPham(String maChiTietSanPham, SanPham sanPham, MauSac mauSac, ChatLieu chatLieu, KichThuoc kichThuoc, int soLuong, double gia, boolean trangThai) {
        this.maChiTietSanPham = maChiTietSanPham;
        this.sanPham = sanPham;
        this.mauSac = mauSac;
        this.chatLieu = chatLieu;
        this.kichThuoc = kichThuoc;
        this.soLuong = soLuong;
        this.gia = gia;
        this.trangThai = trangThai;
    }

    public ChiTietSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }

    public ChiTietSanPham(String maChiTietSanPham, SanPham sanPham) {

        this.maChiTietSanPham = maChiTietSanPham;
        this.sanPham = sanPham;
    }

    public ChiTietSanPham(SanPham sanPham, MauSac mauSac, ChatLieu chatLieu, KichThuoc kichThuoc, int soLuong, double gia, boolean trangThai) {
        this.sanPham = sanPham;
        this.mauSac = mauSac;
        this.chatLieu = chatLieu;
        this.kichThuoc = kichThuoc;
        this.soLuong = soLuong;
        this.gia = gia;
        this.trangThai = trangThai;

    }


    public ChiTietSanPham(String maChiTietSanPham) {
        this.maChiTietSanPham = maChiTietSanPham;
    }

    public String getMaChiTietSanPham() {
        return maChiTietSanPham;
    }

    public void setMaChiTietSanPham(String maChiTietSanPham) {
        this.maChiTietSanPham = maChiTietSanPham;
    }

    public SanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }

    public MauSac getMauSac() {
        return mauSac;
    }

    public void setMauSac(MauSac mauSac) {
        this.mauSac = mauSac;
    }

    public ChatLieu getChatLieu() {
        return chatLieu;
    }

    public void setChatLieu(ChatLieu chatLieu) {
        this.chatLieu = chatLieu;
    }

    public KichThuoc getKichThuoc() {
        return kichThuoc;
    }

    public void setKichThuoc(KichThuoc kichThuoc) {
        this.kichThuoc = kichThuoc;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }


    @Override
    public String toString() {
        return sanPham.getTenSanPham();
    }

    public String toString2() {
        return sanPham.getTenSanPham() + " (" + kichThuoc + "/" + chatLieu + "/" + mauSac + ")";
    }
}
