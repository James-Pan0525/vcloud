package com.study.vcloud.user.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Pan Weilong
 * @date 2019/8/14 17:40
 * @description: 接口.
 */
@Data
@AllArgsConstructor
public class UserVo {

    private Long userId;
    private String username;
    private String password;

}
