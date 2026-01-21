package com.zeeyeh.probsolve.question.api.model.dto;

import com.zeeyeh.probsolve.common.dto.BaseSearchDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 关系搜索请求参数
 *
 * @author Qinloren
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RelationSearchDto extends BaseSearchDto {

    /**
     * 关联 Id
     */
    private Long id;

    /**
     * 题目 Id
     */
    private Long questionsId;

    /**
     * 分类 Id
     */
    private Long categoryId;
}
