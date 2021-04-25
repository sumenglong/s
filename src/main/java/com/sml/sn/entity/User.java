package com.sml.sn.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author sn
 * @since 2021-03-23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    public static final long serialVersionUID = 1L;

    @TableId(value = "user_id", type = IdType.AUTO)
    public Integer userId;
    /**
     * 头像
     */
    public String headimgurl;

    /**
     * 微信唯一标识
     */
    public String openid;

    /**
     * 昵称
     */
    public String nickname;

    /**
     * 性别 1：男、2：女、0：未知
     */
    public String sex;

    /**
     * 省份
     */
    public String province;

    /**
     * 城市
     */
    public String city;



    /**
     * 电话
     */
    public Integer phone;

    /**
     * 创建时间
     */
    public LocalDateTime createTime;

    /**
     * 重新获取token有效期30天
     */
    public String refreshToken;

    public String tencent;

}
