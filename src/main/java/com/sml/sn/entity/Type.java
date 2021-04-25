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
public class Type  {

    private static final long serialVersionUID = 1L;

    @TableId(value = "type_id", type = IdType.AUTO)
    public Integer typeId;

    public String typeName;

    public String createTime;
    /**
     * 公众号
     **/
    public String tencent;

}
