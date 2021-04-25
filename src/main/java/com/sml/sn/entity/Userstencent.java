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
 * @since 2021-04-02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Userstencent  {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ut_id", type = IdType.AUTO)
    public Integer utId;

    /**
     * 管理员
     */
    public String utUsers;

    /**
     * 公众号
     */
    public String utTencent;

    public String string0;

    public Integer int0;


}
