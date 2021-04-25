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
public class Modules  {

    private static final long serialVersionUID = 1L;
    public Integer id;
    /**
     * 模块名称
     */
    public String name;

    /**
     * 父模块编号
     */
    @TableField("parentId")
    public Integer parentId;

    /**
     * 模块路径
     */
    public String path;

    /**
     * 权重
     */
    public String weight;

    /**
     * 预留字符串字段
     */
    public String string0;

    /**
     * 预留整数字段
     */
    public Integer int0;


}
