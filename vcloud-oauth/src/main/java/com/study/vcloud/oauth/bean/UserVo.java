package com.study.vcloud.oauth.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Pan Weilong
 * @date 2019/9/3 16:29
 * @description: 接口.
 */
@Data
@AllArgsConstructor
public class UserVo implements Serializable {

    private Long userId;
    private String username;
    private String password;
}
