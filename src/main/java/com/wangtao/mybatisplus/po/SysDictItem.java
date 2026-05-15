package com.wangtao.mybatisplus.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author wangtao
 * Created at 2026-05-16
 */
@TableName("sys_dict_item")
@ToString
@Getter
@Setter
public class SysDictItem extends BaseModel {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String dictCode;

    private String dictItemValue;

    private String dictItemName;

    private Integer delFlg;

}