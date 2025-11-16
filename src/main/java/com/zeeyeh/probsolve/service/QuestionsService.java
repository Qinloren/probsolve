package com.zeeyeh.probsolve.service;

import com.mybatisflex.core.service.IService;
import com.zeeyeh.probsolve.dto.question.QuestionCreateDto;
import com.zeeyeh.probsolve.dto.question.QuestionSearchDto;
import com.zeeyeh.probsolve.dto.question.QuestionUpdateDto;
import com.zeeyeh.probsolve.entity.data.Questions;
import com.zeeyeh.probsolve.vo.basic.QuestionVo;
import com.zeeyeh.probsolve.vo.search.QuestionSearchVo;

/**
 * 题目表 服务层。
 *
 * @author Qinloren
 * @since 1.0.0
 */
public interface QuestionsService extends IService<Questions> {

    /**
     * 创建题目
     * @param createDto 创建参数
     * @return 创建结果
     */
    QuestionVo create(QuestionCreateDto createDto);

    /**
     * 修改题目
     * @param updateDto 修改参数
     * @return 修改结果
     */
    QuestionVo update(QuestionUpdateDto updateDto);

    /**
     * 获取题目详情
     * @param id 题目Id
     * @return 详情
     */
    QuestionVo detail(Long id);

    /**
     * 删除题目
     * @param id 删除的题目Id
     */
    void delete(Long id);

    /**
     * 搜索题目
     * @param searchDto 搜索参数
     * @return 搜索结果
     */
    QuestionSearchVo search(QuestionSearchDto searchDto);
}
