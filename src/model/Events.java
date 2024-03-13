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
public class Events {

    private String maEventa;
    private String tenEvent;
    private boolean hinhThuc;
    private String mucGiamGia;
    private Date thoiGianBatDau;
    private Date thoiGianKetThuc;
    private String moTa;
    private boolean trangThai;
    private boolean dieuKienApDung;
    private String dieuKienTongTien;

    public Events() {
    }

    public Events(String maEventa, String tenEvent, boolean hinhThuc, String mucGiamGia, Date thoiGianBatDau, Date thoiGianKetThuc, String moTa, boolean trangThai, boolean dieuKienApDung, String dieuKienTongTien) {
        this.maEventa = maEventa;
        this.tenEvent = tenEvent;
        this.hinhThuc = hinhThuc;
        this.mucGiamGia = mucGiamGia;
        this.thoiGianBatDau = thoiGianBatDau;
        this.thoiGianKetThuc = thoiGianKetThuc;
        this.moTa = moTa;
        this.trangThai = trangThai;
        this.dieuKienApDung = dieuKienApDung;
        this.dieuKienTongTien = dieuKienTongTien;
    }

    public Events(String maEventa, String tenEvent, boolean hinhThuc, String mucGiamGia) {
        this.maEventa = maEventa;
        this.tenEvent = tenEvent;
        this.hinhThuc = hinhThuc;
        this.mucGiamGia = mucGiamGia;
    }

    public String getMaEventa() {
        return maEventa;
    }

    public void setMaEventa(String maEventa) {
        this.maEventa = maEventa;
    }

    public String getTenEvent() {
        return tenEvent;
    }

    public void setTenEvent(String tenEvent) {
        this.tenEvent = tenEvent;
    }

    public boolean isHinhThuc() {
        return hinhThuc;
    }

    public void setHinhThuc(boolean hinhThuc) {
        this.hinhThuc = hinhThuc;
    }

    public String getMucGiamGia() {
        return mucGiamGia;
    }

    public void setMucGiamGia(String mucGiamGia) {
        this.mucGiamGia = mucGiamGia;
    }

    public Date getThoiGianBatDau() {
        return thoiGianBatDau;
    }

    public void setThoiGianBatDau(Date thoiGianBatDau) {
        this.thoiGianBatDau = thoiGianBatDau;
    }

    public Date getThoiGianKetThuc() {
        return thoiGianKetThuc;
    }

    public void setThoiGianKetThuc(Date thoiGianKetThuc) {
        this.thoiGianKetThuc = thoiGianKetThuc;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public boolean isDieuKienApDung() {
        return dieuKienApDung;
    }

    public void setDieuKienApDung(boolean dieuKienApDung) {
        this.dieuKienApDung = dieuKienApDung;
    }

    public String getDieuKienTongTien() {
        return dieuKienTongTien;
    }

    public void setDieuKienTongTien(String dieuKienTongTien) {
        this.dieuKienTongTien = dieuKienTongTien;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return tenEvent;
    }
}
