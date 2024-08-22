package cn.com.nong.controller;

import cn.com.nong.bean.BuyGoods;
import cn.com.nong.dao.IBuyGoods;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "/buy")
@Api(value = "采购商品",  description = "采购商品")
public class BuyGoodsController {
   @Autowired
    private IBuyGoods buyGoodsService;

    @PostMapping(value = "/addbuygoods")
    @ApiOperation(value = "添加商品")
    public boolean addbuygoods(@RequestBody BuyGoods goods){
       boolean flag = buyGoodsService.save(goods);
        return  flag;
    }


    @PutMapping(value = "/findBuyGoodsById")
    @ApiOperation(value = "根据id查询商品")
    public BuyGoods findGoodsById(@RequestBody BuyGoods goods){
        return  buyGoodsService.findGoodsById(goods);
    }


    @GetMapping(value = "/findAllBuyGoods")
    @ApiOperation(value = "查询全部商品")
    public List<BuyGoods> findAllGoods(@RequestBody BuyGoods goods, Integer pageNo, Integer pageSize){
        return  buyGoodsService.findAllGoods(goods,pageNo,pageSize);
    }

    @DeleteMapping(value = "/deleteBuyDoods")
    @ApiOperation(value = "删除商品")
    public boolean deletegoods(@RequestBody String id){
       return buyGoodsService.deletegoods(id);
    }


}
