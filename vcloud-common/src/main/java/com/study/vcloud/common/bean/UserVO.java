package com.study.vcloud.common.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Pan Weilong
 * @date 2019/10/29 10:04
 * @description: 接口.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVO implements Serializable {

    /**
     * 主键ID
     */
    private Integer userId;

}
