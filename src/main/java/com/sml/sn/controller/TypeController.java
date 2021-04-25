package com.sml.sn.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sml.sn.entity.Type;
import com.sml.sn.mapper.TypeMapper;
import com.sml.sn.util.JwtToken;
import com.sml.sn.util.Token;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  活动类别
 * </p>
 *
 * @author sn
 * @since 2021-03-29
 */
@RestController
@RequestMapping("/type/")
public class TypeController {

    @Resource
    public TypeMapper typeMapper;

    /** 
     * 活动类别集合
     * @return
     */
    @RequestMapping("listtype")
    public List<Type> listtyype(String tid){
        QueryWrapper qw=new QueryWrapper();
        qw.eq("tencent",tid);
       return typeMapper.selectList(qw);
    }

    /**
     * 查询
     * @param tokenId
     * @return
     */
    @RequestMapping("listtypebytoken")
    public Map<String,Object> listtypebytoken(String tokenId){
        Map<String,Object> map =new HashMap<>();
        try {
            Token token = JwtToken.unsign(tokenId, Token.class);
            QueryWrapper qw=new QueryWrapper();
            qw.select("type.type_id,type_name,type.create_time, tencent.string0 as tencent ");
            qw.last(", tencent WHERE type.tencent IN (SELECT ut_tencent FROM userstencent where ut_users='"+token.getUserId()+"') AND type.tencent=tencent.tencent_id");
            map.put("code",200);
            map.put("data",typeMapper.selectList(qw));
        }catch (Exception ex){
            map.put("code",201);
            map.put("data",ex);
        }
        return  map;
    }

    /**
     * 添加
     * @param type
     *
     * @return
     */
    @RequestMapping("addtype")
    public Map<String,Object> addtype(Type type){
        Map<String,Object> map=new HashMap<>();
        try{
            int a=typeMapper.insert(type);
            if (a>0){
                map.put("code",200);
                map.put("data","添加成功！");
            }
           else {
                map.put("code",202);
                map.put("data","添加失败！");
            }
        }catch (Exception ex){
            map.put("code",201);
            map.put("data",ex);
        }
        return map;
    }

    /**
     * 删除
     * @param typeId
     * @return
     */
    @RequestMapping("deltype")
    public Map<String,Object> deltype(Integer typeId){
        Map<String,Object> map=new HashMap<>();
        if (typeId!=null){
            int s=typeMapper.deleteById(typeId);
          if (s>0){
              map.put("code",200);
              map.put("data","删除成功！");
          }else {
              map.put("code",202);
              map.put("data","删除失败！");
          }
        }
        return map;
    }

    /**
     * 修改
     * @param type
     * @return
     */
    @RequestMapping("uptype")
    public  Map<String,Object> uptype(Type type){
        Map<String,Object> map =new HashMap<>();
        try{
           Integer d=typeMapper.updateById(type);
           if (d>0){
               map.put("code",200);
               map.put("data","修改成功！");
           }else {
               map.put("code",202);
               map.put("data","修改失败！");
           }
        }catch (Exception ex){
            map.put("code",201);
            map.put("data",ex);
        }

        return map;
    }

    /**
     * 查询修改信息
     * @param typeId
     * @return
     */
    @RequestMapping("getone")
    public  Map<String,Object> getone(Integer typeId){
        Map<String,Object> map =new HashMap<>();
        if(typeId!=null){
            map.put("code",200);
            map.put("data",typeMapper.selectById(typeId));
        }else {
            map.put("code",201);
            map.put("data","参数异常");
        }
        return  map;
    }

}
