package com.wangtao.mybatisplus.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author wangtao
 * Created at 2023-09-16
 */
public class BaseModel implements Serializable {

    private static final long serialVersionUID = 4998006182663556267L;

    /**
     * 创建时间
     * updateStrategy = FieldStrategy.NEVER: 更新方法会忽略createTime
     */
    @TableField(fill = FieldFill.INSERT, updateStrategy = FieldStrategy.NEVER)
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "BaseModel{" +
                "createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
