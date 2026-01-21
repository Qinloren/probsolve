package com.zeeyeh.probsolve.user.api.model.vo;

import com.zeeyeh.probsolve.common.vo.BaseSearchVo;

import java.util.List;

/**
 * 用户搜索结果视图对象
 *
 * @author Qinloren
 */
public class UserSearchVo extends BaseSearchVo<UserVo> {
    public UserSearchVo() {
    }

    public UserSearchVo(List<UserVo> content, Long total, Long number, Long size) {
        super(content, total, number, size);
    }
}
