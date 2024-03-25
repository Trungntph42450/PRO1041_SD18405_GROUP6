/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.servicImp;

import java.util.List;
import model.HoaDonChiTiet;
import model.SanPham;
import repository.ThongKeSoLuongRepository;
import service.OnePieceFashtion;



/**
 *
 * @author lavuo
 */
public class ThongKeKhacServiceImp implements OnePieceFashtion<HoaDonChiTiet, String>{
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
   public List<HoaDonChiTiet> getList1() {
        return repo.getList1();
    }

    public List<HoaDonChiTiet> getListTK1(java.util.Date ngayBd, java.util.Date ngayKt) {
        return getListTK1(ngayBd, ngayKt);
    }

    public List<SanPham> getTenSP(String tenSp) {
        return repo.getTenSP(tenSp);
    }

    public List<HoaDonChiTiet> getList2(String tenSP) {
        return repo.getList2(tenSP);
    }

    public List<HoaDonChiTiet> getListTK2(String tenSP, java.util.Date ngayBd, java.util.Date ngayKt) {
        return repo.getListTK2(tenSP, ngayBd, ngayKt);
    }
}
