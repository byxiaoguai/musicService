package org.system.vip.tools;

import com.github.crab2died.annotation.ExcelField;
import lombok.Data;

import java.sql.Date;

/**
 * @author lz
 * @date 2022/10/10 13:00
 */
@Data
public class JsonRootBean {
    @ExcelField(title = "头像")
    private String avatar;
    @ExcelField(title = "出身日期")
    private String birthday;
    @ExcelField(title = "创建时间")
    private String create_time;
    private int education;
    private int experience;
    @ExcelField(title = "哪里人")
    private String hometown;
    @ExcelField(title = "保险结束时间")
    private String insurance_end_time;
    @ExcelField(title = "保险开始时间")
    private String insurance_start_time;
    @ExcelField(title = "个人介绍")
    private String introduce;
    @ExcelField(title = "手机号")
    private String mobile;
    @ExcelField(title = "名称")
    private String name;
    @ExcelField(title = "编号")
    private long number;
    @ExcelField(title = "性别")
    private int sex;

    private int status;
    @ExcelField(title = "类型")
    private String type;
    private int user_id;
    private String uuid;
    @ExcelField(title = "年龄")
    private String age;
    @ExcelField(title = "日期")
    private String create_time_format;
    @ExcelField(title = "学历")
    private String education_name;
    @ExcelField(title = "工作经验")
    private String experience_name;
    @ExcelField(title = "工作状态")
    private String status_name;
    @ExcelField(title = "身份证号码")
    private String id_number_xx;
    private int is_collect;
    @ExcelField(title = "公司地址")
    private String store_name;
    private String store_uuid;
    private int is_lock;
    private String share_aunt_url;

    //新添加的。
    @ExcelField(title = "星座")
    private String zodiac;
    @ExcelField(title = "属")
    private String chinese_zodiac;
}
