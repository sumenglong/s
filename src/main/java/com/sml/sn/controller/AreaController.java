package com.sml.sn.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sml.sn.entity.Area;
import com.sml.sn.mapper.AreaMapper;
import com.sml.sn.mapper.ItemMapper;
import com.sml.sn.util.CommonUtils;
import com.sml.sn.util.JwtToken;
import com.sml.sn.util.Token;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *展区
 * @author sn
 * @since 2021-03-22
 */
@RestController
@RequestMapping("/area/")
public class AreaController  {
    @Resource
    public AreaMapper areaMapper;
    @Resource
    public ItemMapper itemMapper;

    /**
     * 通过id获取信息及孩子
     * @param areaId
     * @return
     */
    @RequestMapping("areaitem")
    public Map<String,Object> areaitem(Integer areaId,String tid){
        Map<String,Object> map=new HashMap<>();
        QueryWrapper qw=new QueryWrapper();
        qw.eq("item_type",areaId);
        qw.eq("tencent",tid);
        qw.select("item_id,item_name,item_img");
        map.put("item", itemMapper.selectList(qw));
        map.put("area",areaMapper.selectById(areaId));
        map.put("success",200);
        return map;
    }

    /**
     * 通过管理员查询展区
     * @return
     */
    @RequestMapping("getAteaByAdmin")
    public Map<String,Object> getReleaseByAdmin(String tokenId){
        Map<String,Object> map =new HashMap<>();
        try{
            QueryWrapper qw=new QueryWrapper();
            Token token= JwtToken.unsign(tokenId, Token.class);
            qw.select("area_id,area_name,area_url,create_time,tencent.string0 as tencent");
            qw.last(",tencent where area.tencent in(SELECT ut_tencent FROM userstencent WHERE ut_users='"+token.getUserId()+"')  and tencent.tencent_id =area.tencent");
            map.put("code",200);
            map.put("data",areaMapper.selectList(qw));
        }catch (Exception ex){
            map.put("code",201);
            map.put("data",ex);
        }
        return map;
    }

    /**
     * 添加
     * @param area
     * @return
     */
    @RequestMapping("addarea")
    public  Map<String,Object> addarea(Area area){
        Map<String,Object> map =new HashMap<>();
            try{
                int a=areaMapper.insert(area);
                if (a>0){
                    map.put("code",200);
                    map.put("data","添加成功");
                }else {
                    map.put("code",202);
                    map.put("data","添加失败");
                }
            }catch (Exception ex){
                map.put("code",201);
                map.put("data",ex);
            }
        return  map;
    }

    /**
     * 根据id获取信息
     * @param areaId
     * @return
     */
    @RequestMapping("getareabyid")
    public  Map<String,Object> getareabyid(Integer areaId){
        Map<String,Object> map =new HashMap<>();
        try{
            QueryWrapper qw=new QueryWrapper();
            qw.select("area_id,area_name,area_url,tencent");
            qw.eq("area_id",areaId);
            map.put("code",200);
            map.put("data",areaMapper.selectOne(qw));
        }catch (Exception ex){
            map.put("code",201);
            map.put("data",ex);
        }
        return  map;
    }

    /**
     *修改
     * @return
     */
    @RequestMapping("uparea")
    public Map<String,Object> uparea(Area area , MultipartFile file){
        Map<String,Object> map =new HashMap<>();
        try{
            if (file!=null){
                String path="/home/java/SN/static/img/";
                String name="static/img/0/"+ CommonUtils.fileUpload(file,path);
                area.setAreaUrl(name);
            }
            int i=areaMapper.updateById(area);
            if (i>0){
                map.put("data","修改成功");
                map.put("code",200);
            }else {
                map.put("data","修改失败");
                map.put("code",201);
            }
        }catch (Exception ex){
            map.put("data",ex);
            map.put("code",201);
        }
        return map;
    }

    /**
     * 删除
     * @param areaId
     * @return
     */
    @RequestMapping("delarea")
    public Map<String,Object> delarea(Integer areaId){
        Map<String,Object> map =new HashMap<>();
        try{
            areaMapper.deleteById(areaId);
            map.put("data","删除成功");
            map.put("code",200);
        }catch (Exception ex){
            map.put("data",ex);
            map.put("code",201);
        }
        return  map;
    }

    /**
     * 展区
     * @param tokenId
     * @return
     */
    @RequestMapping("getareaname")
    public  Map<String ,Object> getareaname(String tokenId){
        Map<String,Object> map=new HashMap<>();
        try{
            Token token= JwtToken.unsign(tokenId, Token.class);
            QueryWrapper qw =new QueryWrapper();
            qw.select("area_id,area_name");
            qw.last("where area.tencent IN (SELECT ut_tencent FROM userstencent where ut_users='"+token.getUserId()+"'");
            map.put("data",areaMapper.selectList(qw));
            map.put("code",201);
        }catch (Exception ex){
            map.put("data",ex);
            map.put("code",201);
        }
        return  map;
    }

    /**
     * 通过展馆id查询展区
     * @param tencent
     * @return
     */
    @RequestMapping("getAreaByTencent")
    public  Map<String,Object> getAreaByTencent (String tencent){
        Map<String,Object> map =new HashMap<>();
        try{
            QueryWrapper qw=new QueryWrapper();
            qw.select("area_id,area_name");
            qw.eq("tencent",tencent);
            map.put("data",areaMapper.selectList(qw));
            map.put("code",200);
        }catch (Exception ex){
            map.put("data",ex);
            map.put("code",201);
        }
        return  map;
    }

}
