package com.zeeyeh.probsolve.user.api.model.dto;

import com.zeeyeh.probsolve.common.dto.BaseSearchDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 用户搜索请求参数
 *
 * @author Qinloren
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSearchDto extends BaseSearchDto {
    /**
     * 用户名
     */
    private String username;

    /**
     * 邮箱
     */
    private String email;


    /**
     * 角色(0-普通用户,1-管理员)
     */
    private String role;

    /**
     * 状态(0-暂停,1-正常)
     */
    private String status;

    /**
     * 总积分
     */
    private String totalScore;

    /**
     * 用户等级
     */
    private String level;
}
