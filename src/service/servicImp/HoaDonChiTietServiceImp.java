/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.servicImp;

import java.util.List;
import javax.swing.JTable;
import model.HoaDon;
import model.HoaDonChiTiet;
import repository.HoaDonChiTietRepository;

/**
 *
 * @author ADMIN
 */
public class HoaDonChiTietServiceImp {

    HoaDonChiTietRepository repo = new HoaDonChiTietRepository();

    public List<HoaDonChiTiet> getJoHang(JTable table) {
        return repo.getJoHang(table);
    }

    public List<HoaDonChiTiet> getJoHang2(JTable table, HoaDon hd) {
        return repo.getJoHang2(table, hd);
    }

    public int insert(List<HoaDonChiTiet> list) {
        return repo.insert(list);
    }

    public int capNhatSoLuongThanhToan(List<HoaDonChiTiet> list) {
        return repo.capNhatSoLuongThanhToan(list);
    }

    public List<HoaDonChiTiet> getHDCTFromHoaDon(String maHD) {
        return repo.getHDCTFromHoaDon(maHD);
    }

    public int updateTrangThaiSangDoiHang(String MaHDCT) {
        return repo.updateTrangThaiSangDoiHang(MaHDCT);
    }

    public int updateTrangThaiSangDaThanhToan(String MaHD) {
        return repo.updateTrangThaiSangDaThanhToan(MaHD);
    }

    public int getSoLuongFromHDCT(String maHDCT) {
        return repo.getSoLuongFromHDCT(maHDCT);
    }

}
