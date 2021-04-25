package com.sml.sn.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 *
 * </p>
 *
 * @author sn
 * @since 2021-03-30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users  {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    public String id;
    /**
     * 用户头像
     */
    @TableField("imgPath")
    public String imgPath;
    /**
     * 登录名
     */
    @TableField("loginName")
    public String loginName;

    /**
     * 密码
     */
    public String password;

    /**
     * 是否锁定
     */
    @TableField("isLockOut")
    public String isLockOut;

    /**
     * 最后一次登录时间
     */
    @TableField("lastLoginTime")
    public String lastLoginTime;

    /**
     * 密码错误次数
     */
    @TableField("psdWrongTime")
    public Integer psdWrongTime;

    /**
     * 被锁定时间
     */
    @TableField("lockTime")
    public String lockTime;

    /**
     * 备注
     */
    @TableField("note")
    public String note;

    /**
     * 密保手机号
     */
    @TableField("protectMTel")
    public String protectMTel;

    /**
     * 账户创立时间
     */
    @TableField("createTime")
    public String createTime;



    /**
     * 解锁时间
     */
    @TableField("unLockTime")
    public String unLockTime;


}
