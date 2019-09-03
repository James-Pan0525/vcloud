package com.study.vcloud.user.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author pwl
 * @since 2019-09-03
 */
@Data
@Accessors(chain = true)
public class SysRole extends Model<SysRole> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "role_id", type = IdType.AUTO)
    private Integer roleId;
    private String roleName;
    private String roleDesc;
    private Date createTime;
    private Date updateTime;
    /**
     * 创建用户
     */
    private Integer createUser;
    /**
     * 更新用户
     */
    private Integer updateUser;


    @Override
    protected Serializable pkVal() {
        return this.roleId;
    }

}
