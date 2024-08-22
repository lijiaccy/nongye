package cn.com.nong.controller;

import cn.com.nong.bean.UserBean;
import cn.com.nong.dao.IUser;
import cn.com.nong.dao.impl.UserService;
import cn.com.nong.util.HttpUtil;
import com.alibaba.fastjson2.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController(value = "/user")
@Api(value = "用户管理", tags = "用户管理相关的接口", description = "用户测试接口")
public class UserController {
    private static final String APPID = "wxb7ef2de249c8ad63"; // 你的微信小程序的 AppID
    private static final String SECRET = "f0799c0748e8a26ea24af475de59a641"; // 你的微信小程序的 AppSecret
    @Autowired
    private IUser userService;


    @PostMapping(value = "/createUser")
    @ApiOperation(value = "添加用户")
    public boolean createUser(UserBean userBean){
        return  userService.createUser(userBean);
    }


    @PostMapping(value = "/getAccount")
    @ApiOperation(value = "获取手机号")
    public String addtest(@RequestBody DecryptPhoneRequest encryptedData){
        System.out.println(encryptedData.toString());
        String token = HttpUtil.httpGet("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+APPID+"&secret="+SECRET);
        String access_token = JSON.parseObject(token).getString("access_token");
        System.out.println(access_token);

        // 使用前端code获取手机号码 参数为json格式
        String url = "https://api.weixin.qq.com/wxa/business/getuserphonenumber?access_token=" + access_token;

        String jsonBody = "{\"code\":\"" + encryptedData.getCode() + "\"}";

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);

        // 设置请求头
        httpPost.setHeader("Content-type", "application/json");

        // 设置请求体
        StringEntity entity = new StringEntity(jsonBody, StandardCharsets.UTF_8);
        httpPost.setEntity(entity);
        String result="";
        try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                result = EntityUtils.toString(responseEntity, StandardCharsets.UTF_8);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    static class DecryptPhoneRequest {
        private String sessionKey;
        private String code;

        public String getSessionKey() {
            return sessionKey;
        }

        public void setSessionKey(String sessionKey) {
            this.sessionKey = sessionKey;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }



}


