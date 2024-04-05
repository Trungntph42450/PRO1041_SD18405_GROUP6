/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.servicImp;

import java.util.Date;
import java.util.List;
import model.HoaDonChiTiet;
import repository.ThongKeDoanhThuRepository;
import service.OnePieceFashtion;

/**
 *
 * @author lavuo
 */
public class ThongKeDoanhThuServiceImp implements OnePieceFashtion<HoaDonChiTiet, String> {

    ThongKeDoanhThuRepository repo = new ThongKeDoanhThuRepository();

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

    public List<HoaDonChiTiet> getListThongKeDT() {
        return repo.getListThongKeDT();
    }

    public List<HoaDonChiTiet> getListTKDT(Date ngayBd, Date ngayKt) {
        return repo.getListTKDT(ngayBd, ngayKt);
    }

}
