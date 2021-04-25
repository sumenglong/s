package com.sml.sn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HuaiLai {
    public  Integer id;
    public  String uuid;
    public  String name;
    public  String img_url;
    public  String message;

}
