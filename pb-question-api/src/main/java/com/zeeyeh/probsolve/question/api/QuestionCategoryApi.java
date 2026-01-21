package com.zeeyeh.probsolve.question.api;

import com.zeeyeh.probsolve.question.api.model.entity.QuestionCategory;
import com.zeeyeh.probsolve.question.api.model.vo.QuestionCategoryVo;

/**
 * 题库接口 api
 *
 * @author Qinloren
 */
public interface QuestionCategoryApi {

    /**
     * 更新题库题目数量
     * @param categoryId 题库 id
     * @param size 题目数量
     */
    void updateSize(Long categoryId, int size);

    /**
     * 判断题库是否存在
      * @param id 题库 id
     * @return 是否存在
     */
    boolean exists(Long id);

    /**
     * 判断题库名称是否存在
     * @param name 题库名称
     * @param userId 用户 id
      * @return 是否存在
     */
    boolean exists(String name, Long userId);

    /**
     * 保存题库
     * @param questionCategory 题库
     * @return 是否保存成功
     */
    boolean save(QuestionCategory questionCategory);

    /**
     * 获取题目分类详情
     * @param id id
     * @return 详情
     */
    QuestionCategoryVo detail(Long id);
}
