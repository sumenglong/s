package com.sml.sn.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * @since 2021-03-29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Activity  {

    private static final long serialVersionUID = 1L;

    @TableId(value = "activity_id", type = IdType.AUTO)
    public Integer activityId;

    /**
     * 活动名
     */
    public String activityName;
    /**
     * 封面
     */
    public String releaseImg;
    /**
     * 发布时间
     */
    public String activityReleaseTime;

    /**
     * 是否可报名
     */
    public String activityRelease;

    /**
     * 内容
     */
    public String activityContent;



    /**
     * 类别
     */
    public String releaseType;

    /**
     * 创建时间
     */
    public String createTime;

    /**
    * 公众号
    **/
    public String tencent;
}
