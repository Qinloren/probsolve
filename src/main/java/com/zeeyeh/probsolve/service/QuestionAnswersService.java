package com.zeeyeh.probsolve.service;

import com.mybatisflex.core.service.IService;
import com.zeeyeh.probsolve.dto.question.answer.QuestionAnswerCreateDto;
import com.zeeyeh.probsolve.dto.question.answer.QuestionAnswerSearchDto;
import com.zeeyeh.probsolve.dto.question.answer.QuestionAnswerUpdateDto;
import com.zeeyeh.probsolve.entity.data.QuestionAnswers;
import com.zeeyeh.probsolve.vo.basic.QuestionAnswerVo;
import com.zeeyeh.probsolve.vo.search.QuestionAnswerSearchVo;

/**
 * 答案表 服务层。
 *
 * @author Qinloren
 * @since 1.0.0
 */
public interface QuestionAnswersService extends IService<QuestionAnswers> {

    /**
     * 创建答案
     * @param createDto 创建参数
     * @return 创建后的答案
     */
    QuestionAnswerVo create(QuestionAnswerCreateDto createDto);

    /**
     * 修改答案
     * @param updateDto 修改参数
     * @return 修改后的答案
     */
    QuestionAnswerVo update(QuestionAnswerUpdateDto updateDto);

    /**
     * 查询答案详情
     * @param id 答案Id
     * @return 答案详情
     */
    QuestionAnswerVo detail(Long id);

    /**
     * 删除答案
     * @param id 答案Id
     */
    void delete(Long id);

    /**
     * 查询答案
     * @param searchDto 查询参数
     * @return 查询结果
     */
    QuestionAnswerSearchVo search(QuestionAnswerSearchDto searchDto);
}
