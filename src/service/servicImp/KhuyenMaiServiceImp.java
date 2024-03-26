/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.servicImp;

import java.util.List;
import model.Events;
import repository.KhuyenMaiRepository;
import service.OnePieceFashtion;

/**
 *
 * @author an
 */
public class KhuyenMaiServiceImp implements OnePieceFashtion<Events, String>{
     KhuyenMaiRepository repo = new KhuyenMaiRepository();

    @Override
    public List<Events> getAll() {
        return repo.getAll();
<<<<<<< HEAD

=======
>>>>>>> f0403480112930d4ccfd00af4ad6695cceeab2e2
    }

    @Override
    public int them(Events k) {
        return repo.themEvents(k);
    }

    @Override
    public int sua(Events k, String e) {
        return repo.suaEvents(k, e);
    }

    @Override
    public int xoa(String e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Events getOne(String e) {
        return repo.getOne(e);
    }

    @Override
    public List<Events> getList(String e) {
        return repo.getList(e);
    }

    public List<Events> getList2(boolean tt) {
        return repo.searchTheoTrangThai(tt);
    }

    public int suaTrangThai() {
        return repo.updateTrangThai();
    }

    public List<Events> listPageKm(int index) {
        return repo.listPageKm(index);
    }

    public int tongBanGhi() {
        return repo.tongBanGhi();
    }
    public Events getActive(Double so) {
        return repo.getActive(so);
    }
    public Events getActive2(Double so) {
        return repo.getActive2(so);
<<<<<<< HEAD

=======
>>>>>>> f0403480112930d4ccfd00af4ad6695cceeab2e2
    }
    public List<Events> getActive3(Double so) {
        return repo.getActive3(so);
    }
    public Events searchTen(String ten) {
        return repo.searchTen(ten);
    }
}
