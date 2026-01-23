package com.zeeyeh.probsolve;

import com.zeeyeh.probsolve.model.dto.ErrorBookCreateDto;
import com.zeeyeh.probsolve.model.dto.ErrorBookSearchDto;
import com.zeeyeh.probsolve.model.dto.ErrorBookUpdateDto;
import com.zeeyeh.probsolve.model.vo.ErrorBookSearchVo;
import com.zeeyeh.probsolve.model.vo.ErrorBookVo;

/**
 * 错题本 Api
 *
 * @author Qinloren
 */
public interface ErrorBookApi {

    /**
     * 添加题目到错题本
     * @param createDto 创建参数
     * @return 创建结果
     */
    ErrorBookVo create(ErrorBookCreateDto createDto);

    /**
     * 更新错题
     * @param updateDto 更新参数
     * @return 更新结果
     */
    ErrorBookVo update(ErrorBookUpdateDto updateDto);

    /**
     * 获取错题详情
     * @param id 错题 id
     * @return 错题详情
     */
    ErrorBookVo detail(Long id);

    /**
     * 搜索错题
     * @param searchDto 查询参数
     * @return 搜索结果
     */
    ErrorBookSearchVo search(ErrorBookSearchDto searchDto);

    /**
     * 删除错题
     * @param id 错题 id
     */
    void delete(Long id);
}
