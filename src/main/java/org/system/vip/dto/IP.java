package org.system.vip.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author lz
 * @Date 2022/3/12 11:02
 * @Version 1.0
 */
@Data
public class IP implements Serializable {
    private int code;
    private List<org.system.vip.dto.Data> data;
    private String msg;
    private boolean success;
}
