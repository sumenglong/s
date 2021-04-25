package com.sml.sn.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sml.sn.mapper.ModulesMapper;
import com.sml.sn.util.JwtToken;
import com.sml.sn.util.Token;
import com.sml.sn.util.UITreeFactory;
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
 * @since 2021-03-30
 */
@RestController
@RequestMapping("/modules/")
public class ModulesController {
@Resource
    public  ModulesMapper modulesMapper;
@RequestMapping("getmodules")
    public  Map<String,Object> getmodules(String tokenId){
    Map<String,Object> map=new HashMap<>();
    try{
        QueryWrapper qw=new QueryWrapper();
        Token token= JwtToken.unsign(tokenId, Token.class);
        qw.apply(" id IN (SELECT moduleId FROM rolemodules WHERE roleId IN (SELECT roleId FROM userroles WHERE userId ='"+token.getUserId()+"')) ORDER BY weight DESC");
        map.put("data", UITreeFactory.toJsonTree(modulesMapper.selectList(qw)));
        map.put("code",200);
    }catch (Exception ex){
        map.put("data", ex);
        map.put("code",201);
    }
    return  map;
}


}
