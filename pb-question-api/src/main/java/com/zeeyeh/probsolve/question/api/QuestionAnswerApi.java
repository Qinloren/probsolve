package com.zeeyeh.probsolve.question.api;

import com.zeeyeh.probsolve.question.api.model.entity.QuestionAnswer;
import com.zeeyeh.probsolve.question.api.model.vo.QuestionAnswerVo;

import java.util.List;

/**
 * 题目答案 api
 *
 * @author Qinloren
 */
public interface QuestionAnswerApi {

    /**
     * 根据 id 查询
     * @param id 答案 id
     * @return 题目答案
     */
    QuestionAnswerVo detail(Long id);

    /**
     * 根据题目 id 查询
      * @param questionId 题目 id
     * @return 题目答案
     */
    QuestionAnswerVo detailByQuestionId(Long questionId);

    /**
     * 根据题目 id 判断是否存在答案
     * @param questionId 题目 id
     * @return 是否存在答案
     */
    boolean exists(Long questionId);

    /**
     * 保存题目答案
     * @param questionAnswer 题目答案
     * @return 是否保存成功
     */
    boolean save(QuestionAnswer questionAnswer);

    /**
     * 批量保存题目答案
     *
     * @param answers 题目答案列表
     */
    void saveBatch(List<QuestionAnswer> answers);
}
