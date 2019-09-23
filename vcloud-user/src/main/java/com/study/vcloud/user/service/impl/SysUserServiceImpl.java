package com.study.vcloud.user.service.impl;

import com.study.vcloud.user.entity.SysUser;
import com.study.vcloud.user.mapper.SysUserMapper;
import com.study.vcloud.user.service.SysUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author pwl
 * @since 2019-09-03
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * @Description 检验权限
     **/
    @Override
    public Boolean hasAuth(String url, Long userId, String method) {
        //获取用户的所有权限
        List<String> menus=sysUserMapper.selectResourceByUserId(userId);
        if(menus==null||menus.size()==0){
            return Boolean.FALSE;
        }
        url = method.toUpperCase()+"|"+url;
        if(menus.contains(url)){
            return Boolean.TRUE;
        }

        int index = url.lastIndexOf("/");

        if (index <= -1) {
            return Boolean.FALSE;
        }

        String tUrl = url.substring(0 , index)+"/**";
        if(menus.contains(tUrl)){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
