package com.zeeyeh.probsolve.question.service;

import com.mybatisflex.core.service.IService;
import com.zeeyeh.probsolve.question.api.model.dto.QuestionAnswerCreateDto;
import com.zeeyeh.probsolve.question.api.model.dto.QuestionAnswerSearchDto;
import com.zeeyeh.probsolve.question.api.model.dto.QuestionAnswerUpdateDto;
import com.zeeyeh.probsolve.question.api.model.entity.QuestionAnswer;
import com.zeeyeh.probsolve.question.api.model.vo.QuestionAnswerSearchVo;
import com.zeeyeh.probsolve.question.api.model.vo.QuestionAnswerVo;

/**
 * 题目答案表 服务层
 *
 * @author Qinloren
 */
public interface QuestionAnswerService extends IService<QuestionAnswer> {

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
     * @param id 答案 Id
     * @return 答案详情
     */
    QuestionAnswerVo detail(Long id);

    /**
     * 根据问题 Id 查询答案详情
      * @param questionId 问题 Id
     * @return 答案详情
     */
    QuestionAnswerVo detailByQuestionId(Long questionId);

    /**
     * 删除答案
     * @param id 答案 Id
     */
    void delete(Long id);

    /**
     * 查询答案
     * @param searchDto 查询参数
     * @return 查询结果
     */
    QuestionAnswerSearchVo search(QuestionAnswerSearchDto searchDto);
}
