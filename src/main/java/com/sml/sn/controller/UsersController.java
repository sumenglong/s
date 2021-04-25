package com.sml.sn.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sml.sn.entity.Users;
import com.sml.sn.mapper.UsersMapper;
import com.sml.sn.util.CommonUtils;
import com.sml.sn.util.JwtToken;
import com.sml.sn.util.Token;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author sn
 * @since 2021-03-30
 */
@RestController
@RequestMapping("/users/")
public class UsersController {
    @Resource
    public UsersMapper usersMapper;


    @RequestMapping("login")
    public Map<String,Object> login(String loginName, String password, HttpSession session){
        Map<String,Object> map=new HashMap<>();
        try{
            if (loginName!=null&&password!=null){
                QueryWrapper qw=new QueryWrapper();
                qw.eq("loginName",loginName);
                Users users=usersMapper.selectOne(qw);
                if (users!=null){
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    if (users.isLockOut.equals("否")){
                        if (users.getPassword().equals(password)){
                            String tokenId = JwtToken.sign(new Token(users.getId(),
                                    loginName, password), 6*60 * 60 * 1000);
                            Date date=new Date();

                            users.setLastLoginTime(formatter.format(date));
                            users.setPsdWrongTime(0);
                            usersMapper.updateById(users);
                            map.put("code",200);
                            map.put("data",tokenId);
                        }else {
                            map.put("data","密码错误");
                            if (users.getPsdWrongTime()==2){
                                users.setIsLockOut("是");
                                Date date=new Date();
                                users.setLockTime(formatter.format(date));
                                map.put("data","密码错误三次，账号已锁定");
                            }
                            users.setPsdWrongTime(users.getPsdWrongTime()+1);
                            usersMapper.updateById(users);
                            map.put("code",204);
                        }
                    }else {
                        map.put("code",203);
                        map.put("data","该账号已被锁定，请先联系管理员解锁");
                    }
                }else {
                    map.put("code",202);
                    map.put("data","用户不存在");
                }
            }
        }catch (Exception ex){
            map.put("code",201);
            map.put("data","程序异常");
        }

        return map;
    }


    /**
     * 通过id获取
     * @param userid
     * @return
     */
    @RequestMapping("getUserByid")
    public Map<String,Object> getmodules (String userid){
        Map<String,Object> map=new HashMap<>();
        try{
            QueryWrapper qw=new QueryWrapper();
            qw.eq("id",userid);
            Users users= usersMapper.selectOne(qw);
            map.put("code",200);
            map.put("data",users);
        }catch (Exception ex){
            map.put("code",201);
            map.put("data",ex);
        }
        return  map;
    }

    /**
     * 获取所有管理用户信息
     * @return
     */
    @RequestMapping("getall")
    public Map<String,Object> getall(String tokenId){
        Map<String,Object> map=new HashMap<>();
        try{
            QueryWrapper qw=new QueryWrapper();
            Token token= JwtToken.unsign(tokenId, Token.class);
            if (!token.getUserId().equals("")){
                qw.select("id,imgPath,loginName,password,note,psdWrongTime,isLockOut,createTime");
                qw.orderByDesc("createTime");
                List<Users> users= usersMapper.selectList(qw);
                map.put("data",users);
                map.put("code",200);
            }else {
                map.put("data","无效token");
                map.put("code",202);
            }
        }catch (Exception ex){
            map.put("data",ex);
            map.put("code",201);
        }
        return  map;
    }

    /**
     * 添加管理员
     * @param users
     * @return
     */
    @RequestMapping("addadmin")
    public Map<String,Object> addadmin(Users users){
        Map<String,Object> map=new HashMap<>();
        try{
            users.setId(UUID.randomUUID().toString());
            if(users.getPassword()==null||users.getPassword().equals("")){
                users.setPassword("admin");
            }
            int i=usersMapper.insert(users);
            if (i>0){
                map.put("data","添加成功");
                map.put("code",200);
            }else {
                map.put("data","添加失败");
                map.put("code",202);
            }
        }catch (Exception e){
            map.put("data","参数异常,请检查账户是否存在。");
            map.put("code",201);
        }
        return  map;
    }

    /**
     * 删除
     * @param uid
     * @return
     */
    @RequestMapping("delectadmin")
    public Map<String,Object> delect (String uid,String tokenId){
        Map<String,Object> map=new HashMap<>();
        try{
            Token token= JwtToken.unsign(tokenId, Token.class);
            if (token.getUserId().equals(uid)){
                map.put("data","该账户为当前登录账户，不能删除！");
                map.put("code",202);
            }else {
                QueryWrapper qw=new QueryWrapper();
                qw.eq("id",uid);
                int i=usersMapper.delete(qw);
                if (i>0){
                    map.put("data","删除成功！");
                    map.put("code",200);
                }else {
                    map.put("data","删除失败！");
                    map.put("code",202);
                }
            }

        }catch (Exception ex){
            map.put("data","程序异常！");
            map.put("code",201);
        }
        return  map;
    }

    /**
     *修改
     * @param users
     * @return
     */
    @RequestMapping("updateusers")
    public  Map<String,Object> updateusers(Users users, MultipartFile file){
        Map<String,Object> map=new HashMap<>();
            if (file!=null){
                String path="/home/java/SN/static/img/";
                String name="static/img/"+CommonUtils.fileUpload(file,path);
                users.setImgPath(name);
            }
           int i= usersMapper.updateById(users);
            if (i>0){
                map.put("data","修改成功！");
                map.put("code",200);
            }else {
                map.put("data","修改失败！");
                map.put("code",201);
            }

        return  map;
    }






}
