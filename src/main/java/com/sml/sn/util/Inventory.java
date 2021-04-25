package com.sml.sn.util;

import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 *
 * </p>
 *
 * @author XLSX
 * @since 2021-02-24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {

    private static final long serialVersionUID = 1L;
    public Integer id;
    /**
     * 种类
     */
    public String sort;
    /**
     * 名称
     */
    public String name;

    /**
     * 参数
     */
    public String parameter;

    /**
     * 品牌
     */
    public String brand;

    /**
     * 类别
     */
    public Integer type;

    /**
     * 单位
     */
    public String unit;

    /**
     * 采购价
     */
    public String purchasePrice;

    /**
     * 单价
     */
    public String unitPric;



}
