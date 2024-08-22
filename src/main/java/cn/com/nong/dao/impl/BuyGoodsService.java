package cn.com.nong.dao.impl;

import cn.com.nong.bean.BuyGoods;
import cn.com.nong.dao.IBuyGoods;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class BuyGoodsService implements IBuyGoods {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public boolean save(BuyGoods goods) {
        String sql = "insert into buygoods(id,userid,title,type,price,goodnum,x,y,province,city,area,address,account,context,createtime) values (0,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        Object[] params = {goods.getUserid(),goods.getTitle(),goods.getType(),goods.getPrice(),goods.getGoodnum(),goods.getX(),goods.getY(),goods.getProvince(),goods.getCity(),goods.getArea(),goods.getAddress(),goods.getAccount(),goods.getContext(),time};
        return jdbcTemplate.update(sql, params)>0;
    }

    @Override
    public BuyGoods findGoodsById(BuyGoods goods) {
        String sql = "select * from buygoods where id=?";
        Object[] params = new Object[]{goods.getId()};
        return jdbcTemplate.queryForObject(
                sql,
                params,
                new BeanPropertyRowMapper<>(BuyGoods.class));
    }

    @Override
    public List<BuyGoods> findAllGoods(BuyGoods goods, Integer pageNo, Integer pageSize) {
        String sql = "select * from buygoods where 1=1";
        if (!Strings.isNullOrEmpty(goods.getArea())){
            sql = sql +" and area ="+goods.getArea();
        }

        if (goods.getType() != 0){
            sql = sql +" and goodtype ="+goods.getType();
        }

        if (!Strings.isNullOrEmpty(goods.getUserid())){
            sql = sql +" and userid ="+goods.getUserid();
        }
        sql = sql+" and status =1 order by createtime desc limit "+pageNo+ ","+pageSize;
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(BuyGoods.class));
    }

    @Override
    public boolean deletegoods(String id) {
        String sql = "delete from goods where id=?";
        int update = jdbcTemplate.update(sql, id);
        if (update>0){
            return true;
        }
        return false;
    }

}
