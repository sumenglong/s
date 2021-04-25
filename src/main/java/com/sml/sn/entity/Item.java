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
 * @since 2021-03-22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item  {

    public static final long serialVersionUID = 1L;

    @TableId(value = "item_id", type = IdType.AUTO)
    public Integer itemId;

    public String itemName;

    public String itemImg;

    public String itemMp3;

    public Integer itemType;

    public String itemContent;

    public LocalDateTime createTime;
    /**
     * 公众号
     **/
    public String tencent;
}
