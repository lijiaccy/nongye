package cn.com.nong.dao;

import cn.com.nong.bean.BuyGoods;

import java.util.List;

public interface IBuyGoods {
    boolean save(BuyGoods goods);

    BuyGoods findGoodsById(BuyGoods goods);

    List<BuyGoods> findAllGoods(BuyGoods goods, Integer pageNo, Integer pageSize);

    boolean deletegoods(String id);
}
