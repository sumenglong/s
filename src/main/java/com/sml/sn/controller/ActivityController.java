package com.sml.sn.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sml.sn.entity.Activity;
import com.sml.sn.mapper.ActivityMapper;
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
 *  前端控制器
 * </p>
 *
 * @author sn
 * @since 2021-03-29
 */
@RestController
@RequestMapping("/activity/")
public class ActivityController {
@Resource
    public ActivityMapper activityMapper;

    /**
     * 通过类别查询
     * @param typeId
     * @return
     */
    @RequestMapping("getAcByType")
    public Map<String,Object> getAcByType(Integer typeId){
    Map<String,Object> map =new HashMap<>();
    if (typeId!=null){
        QueryWrapper qw=new QueryWrapper();
        qw.eq("release_type",typeId);
        List<Activity> activity=activityMapper.selectList(qw);
        map.put("code",200);
        map.put("data",activity);
    }else {
        map.put("code",201);
        map.put("data","参数异常");
    }
    return  map;
}

    /**
     * 同过id查询
     * @param activityId
     * @return
     */
    @RequestMapping("getAcByid")
    public Map<String,Object> getAcByid(Integer activityId){
    Map<String,Object> map =new HashMap<>();
    if (activityId!=null){
        Activity activity=activityMapper.selectById(activityId);
        map.put("code",200);
        map.put("data",activity);
    }else {
        map.put("code",201);
        map.put("data","参数异常");
    }
    return  map;
    }

    /**
     * 查询是|否可报名的活动
     * @param whether
     * @return
     */
    @RequestMapping("getAcByRelease")
    public Map<String,Object> getAcByRelease(String whether){
    Map<String,Object> map =new HashMap<>();
    if(whether!=null&&(whether.equals("是")||whether.equals("否"))){
        QueryWrapper qw=new QueryWrapper();
        qw.eq("activity_release",whether);
        List<Activity> activity=activityMapper.selectList(qw);
        map.put("code",200);
        map.put("data",activity);
    }else {
        map.put("code",200);
        map.put("data","参数异常");
    }
    return map;
    }

    /**
     * 通过用户获取权限下活动
     * @param tokenId
     * @return
     */
    @RequestMapping("getReleaseByAdmin")
    public Map<String,Object> getReleaseByAdmin(String tokenId){
        Map<String,Object> map =new HashMap<>();
        try{
            Token token= JwtToken.unsign(tokenId, Token.class);
            QueryWrapper qw=new QueryWrapper();
            qw.select("activity.activity_id,activity.activity_name,activity.activity_release,activity.activity_release_time,activity.create_time,activity.release_img ,type.type_name as release_type,tencent.string0 as tencent");
            qw.last(", tencent,type WHERE activity.tencent IN (SELECT ut_tencent FROM userstencent WHERE ut_users='"+token.getUserId()+"' ) AND activity.tencent=tencent.tencent_id AND release_type=type.type_id");
            map.put("code",200);
            map.put("data",activityMapper.selectList(qw));
        }catch (Exception ex){
            map.put("code",201);
            map.put("data","获取失败");
        }
        return map;
    }

    /**
     *删除
     */
    @RequestMapping("delactivity")
    public Map<String,Object> delactivity(Integer activityId){
        Map<String,Object> map =new HashMap<>();
        try{
            int d= activityMapper.deleteById(activityId);
            if (d>0){
                map.put("code",200);
                map.put("data","删除成功");
            }else {
                map.put("code",202);
                map.put("data","删除失败");
            }
        }catch (Exception ex){
            map.put("code",201);
            map.put("data","删除失败");
        }
        return map;
    }

    /**
     * 修改
     * @param activity
     * @param file
     * @return
     */
    @RequestMapping("upactivity")
    public Map<String,Object> upactivity(Activity  activity,MultipartFile file){
        Map<String,Object> map=new HashMap<>();
        try{
            if (activity.activityRelease.equals("是")){
                QueryWrapper qw=new QueryWrapper();
                qw.last("WHERE tencent=(SELECT tencent FROM activity WHERE activity_id="+activity.activityId+") AND activity_release='是'");
                List<Activity> a= activityMapper.selectList(qw);
                if(a.size()==0||(a.size()==1&&a.get(0).activityId==activity.activityId )){

                }else {
                    map.put("code",202);
                    map.put("data","当前活动‘"+a.get(0).activityName+"’正在报名，请先取消改活动报名");
                    return map;
                }
            }

            if (file!=null){
                String path="/home/java/SN/static/img/";
                String name="static/img/"+ CommonUtils.fileUpload(file,path);
                activity.releaseImg=name;
            }
            if(activityMapper.updateById(activity)>0){
                map.put("code",200);
                map.put("data","修改成功");
                return map;
            }
            else {
                map.put("code",203);
                map.put("data","修改失败");
                return map;
            }
        }catch (Exception ex){
            map.put("code",201);
            map.put("data",ex);
            return map;
        }
    }

    /**
     * 添加
     * @param activity
     * @return
     */
    @RequestMapping("addactivity")
    public Map<String,Object> addactivity(Activity activity){
        Map<String,Object> map=new HashMap<>();
        try{
           int i= activityMapper.insert(activity);
           if (i>0){
               map.put("code",200);
               map.put("data","添加成功!");
           }else {
               map.put("code",202);
               map.put("data","添加失败");
           }
        }catch (Exception e){
            map.put("code",201);
            map.put("data","添加异常！");
        }
        return  map;
    }

    /**
     * 修改内容
     * @param activityContent
     * @param activityId
     * @return
     */
    @RequestMapping("upactivityContent")
    public  Map<String,Object> upactivityContent(String activityContent,Integer activityId){
        Map<String, Object> map=new HashMap<>();
        try {
            Activity activity=new Activity();
            activity.activityId=activityId;
            activity.activityContent=activityContent;
            if (activityMapper.updateById(activity)>0){
                map.put("code",200);
                map.put("data","修改成功");
            }else {
                map.put("code",202);
                map.put("data","修改失败");
            }
        }catch (Exception ex){
            map.put("code",200);
            map.put("data","修改失败");
            map.put("data1",ex);
        }
        return map;
        }


}
