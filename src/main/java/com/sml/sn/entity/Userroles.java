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
@AllArgsConstructor
@NoArgsConstructor
public class Userroles  {

    private static final long serialVersionUID = 1L;

    @TableField("userId")
    public String userId;

    @TableField("roleId")
    public String roleId;


}
