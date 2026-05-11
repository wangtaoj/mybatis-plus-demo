package com.wangtao.mybatisplus.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

/**
 * @author wangtao
 * Created at 2019/11/3 19:59
 */
@TableName("user")
@ToString
@Getter
@Setter
public class User extends BaseModel {

    private static final long serialVersionUID = 8461232670755871446L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String username;

    private String password;

    private Integer age;

    private Integer gender;

    private LocalDate birthday;

    @TableLogic(value = "0", delval = "1")
    private Integer delFlg;

}
