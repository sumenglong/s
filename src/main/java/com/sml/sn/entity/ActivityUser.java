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
 * @since 2021-03-29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityUser {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ua_id", type = IdType.AUTO)
    public Integer uaId;

    public Integer userId;

    public Integer activityId;

    public String uName;

    public String uPhone;

    public String uAge;

    public String uBz;

    public LocalDateTime createTime;
    /**
     * 公众号
     **/
    public String tencent;

}
