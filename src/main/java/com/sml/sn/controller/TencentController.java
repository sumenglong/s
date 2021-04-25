package com.sml.sn.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sml.sn.mapper.TencentMapper;
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
 *
 * @author sn
 * @since 2021-04-02
 */
@RestController
@RequestMapping("/tencent/")
public class TencentController  {

    @Resource
    public TencentMapper tencentMapper;

    @RequestMapping("getTencentName")
    public Map<String,Object> getTencentName(String tokenId){
        Map<String,Object> map =new HashMap<>();
        try{
            Token token= JwtToken.unsign(tokenId, Token.class);
            QueryWrapper qw=new QueryWrapper();
            qw.select("tencent_id,string0");
            qw.last(" WHERE tencent_id in (SELECT userstencent.ut_tencent FROM userstencent where\n" +
                    "userstencent.ut_users='"+token.getUserId()+"' )");
            map.put("code",200);
            map.put("data",tencentMapper.selectList(qw));
        }catch (Exception ex){
            map.put("code",201);
            map.put("data",ex);
        }
        return map;
    }

}
