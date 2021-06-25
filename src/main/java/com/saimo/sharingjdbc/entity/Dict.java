package com.saimo.sharingjdbc.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author jlz
 * @className: Dict
 * @date 2021/6/25 13:51
 * @description todo
 **/
@Data
@TableName("t_dict")
public class Dict {
    private Long dictId;
    private String status;
    private String value;
}