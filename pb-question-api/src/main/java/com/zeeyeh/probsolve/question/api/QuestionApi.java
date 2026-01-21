package com.zeeyeh.probsolve.question.api;

import com.mybatisflex.core.query.QueryWrapper;
import com.zeeyeh.probsolve.question.api.model.entity.Question;
import com.zeeyeh.probsolve.question.api.model.enums.QuestionType;

import java.util.List;

/**
 * 题目相关 api
 *
 * @author Qinloren
 */
public interface QuestionApi {

    /**
     * 题目是否存在
     * @param questionId 题目id
     * @return 是否存在
     */
    boolean exists(Long questionId);

    /**
     * 题目是否存在
     * @param content 题目内容
     * @param type 题目类型
     * @return 是否存在
     */
    boolean exists(String content, QuestionType type);

    /**
     * 获取题目详情
     * @param content 题目内容
     * @param type 题目类型
     * @return 题目详情
     */
    Question detail(String content, QuestionType type);

    /**
     * 保存题目
     * @param question 题目
     * @return 是否保存成功
     */
    boolean save(Question question);

    /**
     * 批量保存题目
     *
     * @param questions 题目列表
     */
    void saveBatch(List<Question> questions);

    /**
     * 根据条件获取题目列表
      * @param queryWrapper 查询条件
     * @return 题目列表
     */
    List<Question> list(QueryWrapper queryWrapper);
}
