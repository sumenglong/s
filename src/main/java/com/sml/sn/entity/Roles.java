package com.sml.sn.entity;

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
public class Roles  {

    private static final long serialVersionUID = 1L;

    /**
     * 角色(用户组)名称
     */
    public String name;

    /**
     * 预留Int
     */
    public Integer int0;

    /**
     * 预留字符串
     */
    public String string0;


}
