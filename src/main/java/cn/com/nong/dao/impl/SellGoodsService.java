package cn.com.nong.dao.impl;

import cn.com.nong.bean.SellGoods;
import cn.com.nong.dao.ISellGoods;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class SellGoodsService implements ISellGoods {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public boolean save(SellGoods goods) {
        String sql = "insert into sellgoods(id,userid,title,type,price,goodnum,x,y,province,city,area,address,pics,account,context,createtime) values (0,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        Object[] params = {goods.getUserid(),goods.getTitle(),goods.getType(),goods.getPrice(),goods.getGoodnum(),goods.getX(),goods.getY(),goods.getProvince(),goods.getCity(),goods.getArea(),goods.getAddress(),goods.getPics(),goods.getAccount(),goods.getContext(),time};
        return jdbcTemplate.update(sql, params)>0;
    }

    @Override
    public SellGoods findGoodsById(SellGoods goods) {
        String sql = "select * from sellgoods where id=?";
        Object[] params = new Object[]{goods.getId()};
        return jdbcTemplate.queryForObject(
                sql,
                params,
                new BeanPropertyRowMapper<>(SellGoods.class));
    }

    @Override
    public List<SellGoods> findAllGoods(SellGoods goods, Integer pageNo, Integer pageSize) {
        String sql = "select * from sellgoods where 1=1";
        if (!Strings.isNullOrEmpty(goods.getAddress())){
            sql = sql +" and address like '%"+goods.getAddress()+"%'";
        }

        if (goods.getType() != 0){
            sql = sql +" and type ="+goods.getType();
        }

        if (!Strings.isNullOrEmpty(goods.getUserid())){
            sql = sql +" and userid ="+goods.getUserid();
        }
        pageNo = pageNo*pageSize;
        sql = sql+" order by createtime desc limit "+pageNo+ ","+pageSize;

        System.out.println(sql);
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(SellGoods.class));
    }

    @Override
    public boolean deletegoods(String id) {
        String sql = "delete from sellgoods where id=?";
        int update = jdbcTemplate.update(sql, id);
        if (update>0){
            return true;
        }
        return false;
    }

}
