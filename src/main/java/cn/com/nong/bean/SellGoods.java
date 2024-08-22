package cn.com.nong.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SellGoods {
    private String id;
    private String userid;
    private int type;
    private double price;
    private String title;
    private double goodnum;
    private double x;
    private double y;
    private String province;
    private String pics;
    private String city;
    private String area;
    private String address;
    private String account;
    private String context;
    private String createtime;
}
