package com.sml.sn.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sml.sn.entity.ActivityUser;
import com.sml.sn.entity.User;
import com.sml.sn.mapper.ActivityMapper;
import com.sml.sn.mapper.ActivityUserMapper;
import com.sml.sn.mapper.UserMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/activityUser/")
public class ActivityUserController {

    @Resource
    public ActivityUserMapper activityUserMapper;
    @Resource
    public ActivityMapper activityMapper;
    @Resource
    public UserMapper userMapper;
    /**
     *
     * @param activityId
     * @return
     */
    @RequestMapping("allAU")
    public Map<String ,Object> all(Integer activityId){
        Map<String,Object> map=new HashMap<>();
        if (activityId!=null){
            QueryWrapper qw=new QueryWrapper();
            qw.eq("activity_id",activityId);
            List<ActivityUser> activityuser=activityUserMapper.selectList(qw);
            map.put("code",200);
            map.put("data",activityuser);
        }else {
            map.put("code",201);
            map.put("data","参数异常");
        }
        return  map;
    }

    /**
     * 活动报名
     * @param activityUser
     * @return
     */
    @RequestMapping("addactivity")
    public Map<String,Object> addactivity(ActivityUser activityUser,String openid){
        Map<String,Object> map=new HashMap<>();
        try{
            if (activityMapper.selectById(activityUser.activityId).activityRelease.equals("是")){
                QueryWrapper qw=new QueryWrapper();
                qw.eq("openid",openid);
               User user= userMapper.selectOne(qw);
               if (user!=null){
                   activityUser.userId=user.userId;
                   QueryWrapper qws=new QueryWrapper();
                   qws.eq("user_id", activityUser.userId);
                   qws.eq("activity_id",activityUser.activityId);
                   if(activityUserMapper.selectList(qws).size()==0){
                       int i=activityUserMapper.insert(activityUser);
                       if(i>0){
                           map.put("data","报名成功");
                           map.put("code",200);
                       }  else {
                           map.put("data","报名失败");
                           map.put("code",203);
                       }
                   }else {
                       map.put("data","用户已参加过该活动");
                       map.put("code",204);
                   }
               }else {
                    map.put("data","用户角色不存在");
                    map.put("code",203);
                }
            }else {
                map.put("code",202);
                map.put("data","该活动不能报名");
            }
        }catch (Exception e){
            map.put("code",201);
            map.put("data","报名失败");
            map.put("data1",e);
        }
        return  map;
    }

}
