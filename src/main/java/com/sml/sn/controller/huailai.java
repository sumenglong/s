package com.sml.sn.controller;

import com.sml.sn.entity.HuaiLai;
import com.sml.sn.util.DB;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/huailai/")
public class huailai {

    /**
     * 总条数
     * @return
     * @throws SQLException
     */
    @RequestMapping("selsql")
    public Map<String,Object> srlsql() throws SQLException {
        Map<String,Object> map=new HashMap<>();
        ResultSet rs= DB.executeQuery("SELECT * FROM mini_message");
        List<HuaiLai> huaiLais=new ArrayList<>();
        while(rs.next()){
            int id = rs.getInt(1);
            String uuid = rs.getString(2);
            String name = rs.getString(3);
            String img_url = rs.getString(4);
            String message = rs.getString(5);
            HuaiLai hl=new HuaiLai(id,uuid,name,img_url,message);
            huaiLais.add(hl);
        }
        rs.last(); // 将光标移动到最后一行
        int rowCount = rs.getRow();
        map.put("message", rowCount);
        map.put("data", huaiLais);
        return map;
    }

    /**
     * 
     * 清空
     * @return
     * @throws SQLException
     */
    @RequestMapping("delsql")
    public  Map<String,Object> delsql() throws SQLException {
        Map<String,Object> map=new HashMap<>();
        boolean rs= DB.executeUpdate("DELETE FROM mini_message");
        map.put("message", rs);
        return map;
    }

    /**
     * 通过id删除
     * @param id
     * @return
     * @throws SQLException
     */
    @RequestMapping("delbyid")
    public Map<String ,Object> delbyid(Integer id) throws SQLException{
        Map<String,Object> map=new HashMap<>();
        boolean rs= DB.executeUpdate("DELETE FROM mini_message where id="+id);
        map.put("message", rs);
        return map;
    }
}
