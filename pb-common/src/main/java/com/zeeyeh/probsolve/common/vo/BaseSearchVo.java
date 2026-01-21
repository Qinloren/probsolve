package com.zeeyeh.probsolve.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 分页查询结果
 * @param <T> 数据列表类型
 *
 * @author Qinloren
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class BaseSearchVo<T> {

    /**
     * 数据列表
     */
    private List<T> content;

    /**
     * 总条数
     */
    private Long total;

    /**
     * 当前页码
     */
    private Long number;

    /**
     * 每页大小
     */
    private Long size;
}
