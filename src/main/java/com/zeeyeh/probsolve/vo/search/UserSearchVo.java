package com.zeeyeh.probsolve.vo.search;

import com.zeeyeh.probsolve.vo.BaseSearchVo;
import com.zeeyeh.probsolve.vo.basic.UserVo;

import java.util.List;

/**
 * 用户搜索参数
 */
public class UserSearchVo extends BaseSearchVo<UserVo> {
    public UserSearchVo() {
    }

    public UserSearchVo(List<UserVo> content, Long total, Long number, Long size) {
        super(content, total, number, size);
    }
}
