package com.zeeyeh.probsolve.question.api.impl;

import com.mybatisflex.core.query.QueryWrapper;
import com.zeeyeh.probsolve.question.api.QuestionAnswerApi;
import com.zeeyeh.probsolve.question.api.model.entity.QuestionAnswer;
import com.zeeyeh.probsolve.question.api.model.vo.QuestionAnswerVo;
import com.zeeyeh.probsolve.question.service.QuestionAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * QuestionAnswerApi 实现类
 *
 * @author Qinloren
 */
@Service
@RequiredArgsConstructor
public class QuestionAnswerApiImpl implements QuestionAnswerApi {

    private final QuestionAnswerService questionAnswerService;

    @Override
    public QuestionAnswerVo detail(Long id) {
        return questionAnswerService.detail(id);
    }

    @Override
    public QuestionAnswerVo detailByQuestionId(Long questionId) {
        return questionAnswerService.detailByQuestionId(questionId);
    }

    @Override
    public boolean exists(Long questionId) {
        QueryWrapper queryWrapper = QueryWrapper.create()
                .eq(QuestionAnswer::getQuestionId, questionId);
        return questionAnswerService.exists(queryWrapper);
    }

    @Override
    public boolean save(QuestionAnswer questionAnswer) {
        return questionAnswerService.save(questionAnswer);
    }

    @Override
    public void saveBatch(List<QuestionAnswer> answers) {
        questionAnswerService.saveBatch(answers);
    }
}
