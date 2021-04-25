package com.sml.sn.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sml.sn.entity.Comments;
import com.sml.sn.mapper.CommentsMapper;
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
 *评论
 * @author sn
 * @since 2021-03-23
 */
@RestController
@RequestMapping("/comments/")
public class CommentsController {

    @Resource
    public CommentsMapper commentsMapper;

    /**
     *添加评论
     * @param comments
     * @return
     */
    @RequestMapping("addcomm")
    public Map<String,Object> addcomm (Comments comments){
        Map<String,Object> map =new HashMap<>();
        if(comments.getIsShow()==null){
            comments.setIsShow("否");
        }
        int s=commentsMapper.insert(comments);
        if (s>0){
            map.put("code",200);
            map.put("data","评论成功");
        }else{
            map.put("code",201);
            map.put("data","评论失败");
        }
        return map;
    }

    /**
     * 查询
     * @param tokenId
     * @return
     */
    @RequestMapping("getcommentsList")
    public  Map<String,Object> getcommentsList (String tokenId){
        Map<String,Object> map =new HashMap<>();
        try{
            Token token= JwtToken.unsign(tokenId, Token.class);
            QueryWrapper qw=new QueryWrapper();
            qw.select("comments.comments_id,comments.comments_content,comments.create_time,`user`.nickname as uesr_id,area.area_name as area_id,tencent.string0 as tencent");
            qw.last(", area ,`user` ,tencent where comments.tencent IN (SELECT ut_tencent FROM userstencent where ut_users='"+token.getUserId()+"') and comments.area_id=area.area_id and comments.uesr_id=`user`.openid and comments.tencent=tencent.tencent_id");
            map.put("code",200);
            map.put("data",commentsMapper.selectList(qw));
        }catch (Exception e){
            map.put("code",201);
            map.put("data",e);
        }
        return  map;
    }

    /**
     * 删除
     * @param commentsId
     * @return
     */
    @RequestMapping("delcomments")
    public  Map<String,Object> delcomments (Integer commentsId){
        Map<String,Object> map =new HashMap<>();
        try{
            if (commentsId!=null){
                int a=commentsMapper.deleteById(commentsId);
                if (a>0){
                    map.put("code",200);
                    map.put("data","删除成功！");
                }else {
                    map.put("code",203);
                    map.put("data","删除失败！");
                }
            }else {
                map.put("code",202);
                map.put("data","参数为null！");
            }
        }catch (Exception ex){
            map.put("code",201);
            map.put("data","程序异常！");
        }
        return  map;
    }


}
