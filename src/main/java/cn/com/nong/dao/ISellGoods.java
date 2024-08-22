package cn.com.nong.dao;

import cn.com.nong.bean.SellGoods;

import java.util.List;

public interface ISellGoods {
    boolean save(SellGoods goods);

    SellGoods findGoodsById(SellGoods goods);

    List<SellGoods> findAllGoods(SellGoods goods, Integer pageNo, Integer pageSize);

    boolean deletegoods(String id);
}
