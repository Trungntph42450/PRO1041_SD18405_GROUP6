/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.servicImp;

import java.util.Date;
import java.util.List;
import model.HoaDonChiTiet;
import model.HoaDon;
import repository.ThongKeSoLuongRepository;
import service.OnePieceFashtion;

/**
 *
 * @author lavuo
 */
public class ThongKeSoLuongServiceImp implements OnePieceFashtion<HoaDonChiTiet,String>{
        ThongKeSoLuongRepository repo = new ThongKeSoLuongRepository();
    @Override
    public List<HoaDonChiTiet> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int them(HoaDonChiTiet k) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int sua(HoaDonChiTiet k, String e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int xoa(String e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public HoaDonChiTiet getOne(String e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<HoaDonChiTiet> getList(String e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public List<HoaDonChiTiet> getListCTHD() {
        return repo.getListCTHD();
    }

    public List<HoaDonChiTiet> getListTk(Date ngayBd, Date ngayKt) {
        return repo.getListTk(ngayBd, ngayKt);
    }

    public List<HoaDonChiTiet> getListBieuDoHD() {
        return repo.getListBieuDoHD();
    }

    public List<HoaDonChiTiet> getListTKBieuDoHD(Date ngayBd, Date ngayKt) {
        return repo.getListTKBieuDoHD(ngayBd, ngayKt);
    }

    public List<HoaDonChiTiet> getListBieuDoTopSP() {
        return repo.getListBieuDoTopSP();
    }
}
