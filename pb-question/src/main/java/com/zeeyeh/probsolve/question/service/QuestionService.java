package com.zeeyeh.probsolve.question.service;

import com.mybatisflex.core.service.IService;
import com.zeeyeh.probsolve.question.api.model.dto.QuestionCreateDto;
import com.zeeyeh.probsolve.question.api.model.dto.QuestionSearchDto;
import com.zeeyeh.probsolve.question.api.model.dto.QuestionUpdateDto;
import com.zeeyeh.probsolve.question.api.model.dto.QuestionValidationDto;
import com.zeeyeh.probsolve.question.api.model.entity.Question;
import com.zeeyeh.probsolve.question.api.model.vo.QuestionSearchVo;
import com.zeeyeh.probsolve.question.api.model.vo.QuestionVo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.transaction.annotation.Transactional;

/**
 * 题目表 服务层
 *
 * @author Qinloren
 */
@Transactional(rollbackFor = Exception.class)
public interface QuestionService extends IService<Question> {

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
     * @param id 题目 Id
     * @return 详情
     */
    QuestionVo detail(Long id);

    /**
     * 删除题目
     * @param id 删除的题目 Id
     */
    void delete(Long id);

    /**
     * 搜索题目
     * @param searchDto 搜索参数
     * @return 搜索结果
     */
    QuestionSearchVo search(QuestionSearchDto searchDto);

    /**
     * 验证答案
     * @param validationDto 验证参数
     * @return 验证结果
     */
    boolean validate(QuestionValidationDto validationDto, HttpServletRequest request);
}
