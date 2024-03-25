/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.servicImp;

import java.util.List;
import model.TaiKhoan;
import repository.DoiMatKhauRepository;
import service.OnePieceFashtion;

/**
 *
 * @author Nguyen Thanh Trung
 */
public class TaiKhoanServiceImp implements OnePieceFashtion<TaiKhoan, String> {

    DoiMatKhauRepository repo = new DoiMatKhauRepository();

    @Override
    public List<TaiKhoan> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int them(TaiKhoan k) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int sua(TaiKhoan k, String e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int xoa(String e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public TaiKhoan getOne(String e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<TaiKhoan> getList(String e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int updatePass(String userName, String passWord) {
        return repo.updatePass(userName, passWord);
    }

    public boolean checkMaTK(String MaTK) {
        return repo.checkMaTK(MaTK);
    }

    public boolean checkPassword(String maTK, String enterPassWord) {
        return repo.checkPassword(maTK, enterPassWord);
    }
}
