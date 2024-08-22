package cn.com.nong.dao.impl;

import cn.com.nong.bean.UserBean;
import cn.com.nong.dao.IUser;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class UserService implements IUser {

    /**
     *
     */
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean createUser(UserBean userBean) {
        String sql = "insert into userinfo(id,userid,username,createtime) values (0,?,?,?)";
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        Object[] params = {userBean.getUserid(),userBean.getUsername(),time};
        return jdbcTemplate.update(sql, params)>0;
    }

 /*   @Override
    public boolean addgoods(Goods goods) {
        String sql = "insert into goods(id,userid,goodtype,usertype,price,goodnum,lag1,lon1,province,city,area,address,createtime) values (0,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        Object[] params = {goods.getUserid(),goods.getGoodtype(),goods.getUsertype(),goods.getPrice(),goods.getGoodnum(),1,goods.getLag(),goods.getLon(),goods.getProvince(),goods.getCity(),goods.getArea(),goods.getAddress(),time};
        return jdbcTemplate.update(sql, params)>0;
    }

    @Override
    public Goods findGoodsById(Goods goods) {
        String sql = "select * from goods where id=?";
        Object[] params = new Object[]{goods.getId()};
        return jdbcTemplate.queryForObject(
                sql,
                params,
                new BeanPropertyRowMapper<>(Goods.class));
    }*/

//    @Override
//    public List<Goods> findAllGoodsByUser(Goods goods) {
//        String sql = "select * from goods where userid=? and status=1";
//        Object[] params = new Object[]{goods.getUserid()};
//        return jdbcTemplate.query(sql,params,new BeanPropertyRowMapper<>(Goods.class));
//    }
//
//    @Override
//    public List<Goods> findAllGoodsByArea(Goods goods) {
//        String sql = "select * from goods where area=? and status=1";
//        if (goods.getGoodtype()!= 0){
//            sql = sql +" and goodtype ="+goods.getGoodtype();
//        }
//        Object[] params = new Object[]{goods.getUserid()};
//        return jdbcTemplate.query(sql,params,new BeanPropertyRowMapper<>(Goods.class));
//    }
  /*  @Override
    public List<Goods> findAllGoods(Goods goods, Integer pageNo, Integer pageSize){
        String sql = "select * from goods where 1=1";
        if (!Strings.isNullOrEmpty(goods.getArea())){
            sql = sql +" and area ="+goods.getArea();
        }

        if (goods.getGoodtype() != 0){
            sql = sql +" and goodtype ="+goods.getGoodtype();
        }

        if (!Strings.isNullOrEmpty(goods.getUserid())){
            sql = sql +" and userid ="+goods.getUserid();
        }
        sql = sql+" and status =1 order by createtime desc limit "+pageNo+ ","+pageSize;
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Goods.class));
    }

    @Override
    public boolean changegoodsstatus(Goods goods) {
        String sql = "update goods set status = ?  where id=?";
        Object[] params = new Object[]{goods.getStatus(),goods.getId()};
        return jdbcTemplate.update(sql, params)>0;
    }

    @Override
    public boolean addPics(String goodid, String filename) {
        String sql = "insert into pics (id,goodid,picname,createtime) values (0,?,?,?)";
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        Object[] params = new Object[]{goodid,filename,time};
        return jdbcTemplate.update(sql, params)>0;
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
*/
}
