package cn.com.nong.controller;

import cn.com.nong.bean.SellGoods;
import cn.com.nong.dao.ISellGoods;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SellGoodsController {

    @Autowired
    ISellGoods sellGoodService;

    @PostMapping(value = "/addSellgoods")
    @ApiOperation(value = "添加商品")
    public boolean addSellgoods(SellGoods goods){
        boolean flag = sellGoodService.save(goods);
        return  flag;
    }


    @GetMapping(value = "/findSellGoodsById")
    @ApiOperation(value = "根据id查询商品详情")
    public SellGoods findGoodsById( SellGoods goods){
        return  sellGoodService.findGoodsById(goods);
    }


    @GetMapping(value = "/findAllSellGoods")
    @ApiOperation(value = "查询全部商品")
    public List<SellGoods> findAllGoods(SellGoods goods, Integer pageNo, Integer pageSize){
        return  sellGoodService.findAllGoods(goods,pageNo,pageSize);
    }

    @DeleteMapping(value = "/deleteSellDoods")
    @ApiOperation(value = "删除商品")
    public boolean deletegoods(@RequestBody String id){
        return sellGoodService.deletegoods(id);
    }

}
