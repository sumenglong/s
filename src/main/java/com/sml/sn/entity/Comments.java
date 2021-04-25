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
 * 评论
 * @author sn
 * @since 2021-03-23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comments  {

    private static final long serialVersionUID = 1L;

    @TableId(value = "comments_id", type = IdType.AUTO)
    private Integer commentsId;

    /**
     * 用户id
     */
    private String uesrId;

    /**
     * 是否展示
     */
    private String isShow;
    /**
     * 内容
     */
    private String commentsContent;
    /**
     * 展区id
     */
    private String areaId;
    /**
     * 公众号
     **/
    public String tencent;
    /**
     * 创建时间
     */
    private String createTime;


}
