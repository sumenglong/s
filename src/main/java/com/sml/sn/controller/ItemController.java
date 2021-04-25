package com.sml.sn.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sml.sn.entity.Item;
import com.sml.sn.mapper.ItemMapper;
import com.sml.sn.util.CommonUtils;
import com.sml.sn.util.JwtToken;
import com.sml.sn.util.Token;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>
 *  展项
 * </p>
 * @author sn
 * @since 2021-03-22
 */
@RestController
@RequestMapping("/item/")
public class ItemController  {
@Resource
    public ItemMapper itemMapper;

    /**
     * 通过id获取展项信息
     * @param itemId
     * @return
     */
    @RequestMapping("getallbyid")
    public Map<String,Object> getallbyid(Integer itemId){
    Map<String,Object> map =new HashMap<>();
    map.put("message",itemMapper.selectById(itemId));
    map.put("success",200);
    return  map;
    }

    /**
     * 获取该token用户管理的所有
     * @param tokenId
     * @return
     */
    @RequestMapping("addAll")
    public  Map<String,Object> addall(String tokenId){
        Map<String,Object> map =new HashMap<>();
        try{
            Token token= JwtToken.unsign(tokenId, Token.class);
            QueryWrapper qw=new QueryWrapper();
            qw.select("item_id,item_name,item_img,item_mp3,item.create_time,area.area_name as item_content,tencent.string0 as tencent ");
            qw.last(",area,tencent where item.tencent in (SELECT ut_tencent FROM userstencent where ut_users ='"+token.getUserId()+"') and area.area_id=item_type and tencent.tencent_id=item.tencent");
            map.put("code",200);
            map.put("data",itemMapper.selectList(qw));
        }catch (Exception ex){
            map.put("code",201);
            map.put("data",ex);
        }
        return  map;
    }

    /**
     * 添加
     * @param item
     * @return
     */
    @RequestMapping("addItrm")
    public Map<String,Object> addItrm(Item item){
        Map<String,Object> map =new HashMap<>();
        try{
            QueryWrapper qw=new QueryWrapper();
            int items=itemMapper.insert(item);
            map.put("code",200);
            map.put("data","添加成功");
        }catch (Exception e){
            map.put("code",201);
            map.put("data",e);
        }
        return  map;
    }

    /**
     * 修改
     * @param item
     * @param file
     * @param filemp3
     * @return
     */
    @RequestMapping("upItembyId")
    public  Map<String,Object> upItembyId(Item item, MultipartFile file,MultipartFile filemp3){
        Map<String,Object> map =new HashMap<>();
        try{
            if (file!=null){
                String path="/home/java/SN/static/img/";
                String name="static/img/"+CommonUtils.fileUpload(file,path);
                item.setItemImg(name);
            }
            if (filemp3!=null){
                String path="/home/java/SN/static/voice/";
                String name="static/voice/"+ CommonUtils.fileUpload(filemp3,path);
                item.setItemMp3(name);
            }
            itemMapper.updateById(item);
            map.put("code",200);
            map.put("data","修改成功");
        }catch (Exception ex){
            map.put("code",201);
            map.put("data","修改失败");
        }
        return map;
    }

    /**
     * 添加内容图片
     * @param upload
     * @return
     */
    @RequestMapping("itemImg")
    public Map<String ,Object> itemImg (MultipartFile upload){
        Map<String ,Object> map =new HashMap<>();
        try{
            String path="D:\\work\\idea-work\\SN\\SN\\";
            String name=CommonUtils.fileUpload(upload,path);
            map.put("uploaded",true);
            map.put("url","http://localhost:40001/SN/"+name);
        }catch (Exception ex){
            map.put("uploaded",false);
        }
        return  map;
    }

    /**
     * 修改内容
     * @param itemContent
     * @param itemId
     * @return
     */
    @RequestMapping("itemcontent")
    public  Map<String,Object> itemcontent(String itemContent,Integer itemId){
        Map<String,Object> map =new HashMap<>();
        try{
            QueryWrapper qw=new QueryWrapper();
            Item it=new Item();
            it.setItemContent(itemContent);
            it.setItemId(itemId);
            int a=itemMapper.updateById(it);
            if (a>0){
                map.put("code",200);
                map.put("data","修改成功");
            }else {
                map.put("code",202);
                map.put("data","修改失败");
            }
        }catch (Exception ex){
            map.put("code",201);
            map.put("data","修改异常");
        }
        return map;
    }

    /**
     * 删除
     * @param itemId
     * @return
     */
    @RequestMapping("delItrm")
    public  Map<String,Object> delItem(Integer itemId){
        Map<String,Object> map =new HashMap<>();
        try{
            if (itemId!=null){
                int a=itemMapper.deleteById(itemId);
                if (a>0){
                    map.put("code",200);
                    map.put("data","删除成功");
                }
                else {
                    map.put("code",203);
                    map.put("data","删除失败");
                }
            }else {
                map.put("code",202);
                map.put("data","参数为空");
            }
        }catch (Exception ex){
            map.put("code",201);
            map.put("data","删除异常");
        }
        return  map;
    }


    /** 类别
     * 根基list集合id获取数据
     * @param list
     * @return
     */
    @RequestMapping("getitembylistid")
    public Map<String,Object> getitembylistid (String list){
        Map<String,Object> map =new HashMap<>();
        try {
            String[] s=list.split(",");
            QueryWrapper qw=new QueryWrapper();
            qw.in("item_id",s);
            List<Item> it=itemMapper.selectList(qw);
            map.put("code",200);
            map.put("data",it);
        }catch (Exception ex){
            map.put("code",201);
            map.put("data",ex);
        }
        return map;
    }


}
