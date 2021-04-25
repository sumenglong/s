package com.sml.sn.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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
@NoArgsConstructor
@AllArgsConstructor
public class Rolemodules {

    private static final long serialVersionUID = 1L;

    @TableField("roleId")
    public String roleId;

    @TableField("moduleId")
    public Integer moduleId;


}
