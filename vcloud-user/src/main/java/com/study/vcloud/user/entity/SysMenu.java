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
 * 菜单权限表
 * </p>
 *
 * @author pwl
 * @since 2019-09-03
 */
@Data
@Accessors(chain = true)
public class SysMenu extends Model<SysMenu> {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单ID
     */
    @TableId(value = "menu_id", type = IdType.AUTO)
    private Integer menuId;
    /**
     * 名称
     */
    private String name;
    /**
     * url
     */
    private String url;
    /**
     * 请求方法
     */
    private String method;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
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
        return this.menuId;
    }

}
