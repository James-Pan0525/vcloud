package com.study.vcloud.user.mapper;

import com.study.vcloud.user.entity.SysUser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author pwl
 * @since 2019-09-03
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * @Author Pan Weilong
     * @Description 用户权限
     * @Date 21:26 2019/9/23
     * @Param [userId]
     * @return java.util.List<java.lang.String>
     **/
    List<String> selectResourceByUserId(@Param("userId") Long userId);
}
