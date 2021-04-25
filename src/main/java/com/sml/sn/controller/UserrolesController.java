package com.sml.sn.controller;


import com.sml.sn.mapper.UserrolesMapper;
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
@RequestMapping("/userroles/")
public class UserrolesController {
    @Resource
    public UserrolesMapper userrolesMapper;

    @RequestMapping("getrolesbyuserid")
    public Map<String,Object> getrolesbyuserid(){
        Map<String,Object> map=new HashMap<>();

        return map;
    }

}
