/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.servicImp;

import java.util.List;
import model.HoaDon;
import repository.HoaDonRepository;
import service.OnePieceFashtion;

/**
 *
 * @author Nguyen Thanh Trung
 */
public class HoaDonServiceImp implements OnePieceFashtion<HoaDon, String> {

    HoaDonRepository repo = new HoaDonRepository();

    @Override
    public List<HoaDon> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int them(HoaDon k) {
        return repo.themHoaDon(k);
    }

    @Override
    public int sua(HoaDon k, String e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int xoa(String e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public HoaDon getOne(String e) {
        return repo.getOne(e);
    }

    @Override
    public List<HoaDon> getList(String e) {
        return repo.getList(e, e, e);

    }

    public List<HoaDon> getHoaDonCho() {
        return repo.getHoaDonCho();
    }

    public int countHoaDon() {
        return repo.countHoaDon();
    }

    public List<HoaDon> getLSHoaDon() {
        return repo.getLSHoaDon();
    }

    public int chuyenSangDoiHang(String maHD) {
        return repo.chuyenSangDoiHang(maHD);
    }

    public String maHoaDonDuocDoiHang(String maHd) {
        return repo.maHoaDonDuocDoiHang(maHd);
    }

    public int themHoaDonCho(HoaDon hd) {
        return repo.themHoaDonCho(hd);
    }

    public HoaDon get1HoaDonCho(String maHD) {
        return repo.get1HoaDonCho(maHD);
    }

    public List<HoaDon> getList2(String MaHDorMaKH) {
        return repo.getList2(MaHDorMaKH);
    }

    public List<HoaDon> listPageHD(int index) {
        return repo.listPageHD(index);
    }

    public int tongBanGhi() {
        return repo.tongBanGhi();
    }

    public int capNhatTrangThai(String maHD) {
        return repo.capNhatTrangThai(maHD);
    }

    public int huyHoaDonCho(String maHD) {
        return repo.huyHoaDonCho(maHD);
    }
}
