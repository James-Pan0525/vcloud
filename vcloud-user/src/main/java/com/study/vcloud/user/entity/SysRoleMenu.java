package com.study.vcloud.user.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
public class SysRoleMenu extends Model<SysRoleMenu> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 角色id
     */
    private Integer roleId;
    /**
     * 资源id
     */
    private Integer menuId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
