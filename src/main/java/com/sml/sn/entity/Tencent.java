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
 * @since 2021-04-02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tencent  {

    private static final long serialVersionUID = 1L;

    public String tencentId;

    /**
     * appid
     */
    public String tencentAppid;

    /**
     * secret
     */
    public String tencentSecret;

    public String string0;

    public Integer int0;


}
