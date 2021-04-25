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
 * @since 2021-03-22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Area  {

    public static final long serialVersionUID = 1L;

    @TableId(value = "area_id", type = IdType.AUTO)
    public Integer areaId;

    public String areaName;

    public String areaUrl;

    public String createTime;
    /**
     * 公众号
     **/
    public String tencent;

}
