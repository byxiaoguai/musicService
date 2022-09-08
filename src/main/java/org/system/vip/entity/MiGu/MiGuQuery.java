/**
  * Copyright 2021 json.cn 
  */
package org.system.vip.entity.MiGu;

import lombok.Data;

/**
 * 咪咕搜索对象
 */
@Data
public class MiGuQuery {

    private String code;
    private String msg;
    private Search data;


}