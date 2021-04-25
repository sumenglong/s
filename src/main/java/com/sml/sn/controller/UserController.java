package com.sml.sn.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.gson.Gson;
import com.sml.sn.entity.User;
import com.sml.sn.mapper.TencentMapper;
import com.sml.sn.mapper.UserMapper;
import com.sml.sn.util.HttpClient;
import com.sml.sn.util.JwtToken;
import com.sml.sn.util.Token;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *用户
 * @author sn
 * @since 2021-03-23
 */
@RestController
@RequestMapping("/user/")
public class UserController {
    @Resource
    public UserMapper userMapper;
    @Resource
    public TencentMapper tencentMapper;

    /**
     *判断游客是否为已有用户
     * 是：返回openid
     * 否：添加并返回openid
     * @param code
     * @return
     */
    @RequestMapping("test")
    public Map<String,Object> test(String code,String tid){
        Map<String,Object>  map=new HashMap<>();
        try{

        String message= HttpClient.doGet("https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx995f8d79ad30ca6b&secret=94516bd85b82da735812be1673a070d9&code="+code+"&grant_type=authorization_code");
        Gson gson = new Gson();
        Map<String, Object> maps = new HashMap<String, Object>();
        maps = gson.fromJson(message, maps.getClass());
        if(maps.containsKey("access_token")){
            QueryWrapper qw=new QueryWrapper();
            qw.eq("openid",maps.get("openid"));
            User user= userMapper.selectOne(qw);
            if(user!=null){
                map.put("data",maps.get("openid"));
                map.put("code",200);
                return map;
            }else {
                String messages= HttpClient.doGet("https://api.weixin.qq.com/sns/userinfo?access_token="+maps.get("access_token")+"&openid="+maps.get("openid")+"&lang=zh_CN");
                Gson gsons = new Gson();
                Map<String, Object> maps1 = new HashMap<String, Object>();
                maps1 = gsons.fromJson(messages, maps1.getClass());
                map.put("data",maps1.get("openid"));
                map.put("code",200);
                if (maps1.containsKey("openid")){
                    User u=new User();
                    u.openid=maps1.get("openid").toString();
                    u.nickname=maps1.get("nickname").toString();
                    u.sex=maps1.get("sex").toString();
                    u.headimgurl=maps1.get("headimgurl").toString();
                    u.province=maps1.get("province").toString();
                    u.city=maps1.get("city").toString();
                    u.tencent=tid;
                    userMapper.insert(u);
                    map.put("data",maps1.get("openid"));
                    map.put("code",200);
                }else {
                    map.put("code",201);
                    map.put("data","请求失败");
                }
            }
        }
        else {
            map.put("code",201);
            map.put("data","请求失败");
        }
        }catch (Exception ex){
            map.put("code",202);
            map.put("data",ex);
        }
        return map;
    }

    @RequestMapping("getByAdmin")
    public  Map<String,Object> getByAdmin(String tokenId){
        Map<String,Object> map=new HashMap<>();
        try{
            QueryWrapper queryWrapper=new QueryWrapper();
            Token token= JwtToken.unsign(tokenId, Token.class);
            queryWrapper.select("openid,nickname,sex,headimgurl,create_time,tencent.string0 as tencent");
            queryWrapper.last(",tencent where tencent in(SELECT ut_tencent FROM userstencent WHERE ut_users='"+token.getUserId()+"') and tencent.tencent_id =user.tencent");
            map.put("code",200);
            map.put("data",userMapper.selectList(queryWrapper));
        }catch (Exception ex){
            map.put("code",201);
            map.put("data",ex);
        }
        return  map;
    }

}
