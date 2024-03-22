/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.util.List;

/**
 *
 * @author Nguyen Thanh Trung
 */
public interface OnePieceFashtion<K,E>  {

    public List<K> getAll();

    public int them(K k);

    public int sua(K k, E e);

    public int xoa(E e);

    public K getOne(E e);

    public List<K> getList(E e);
}
