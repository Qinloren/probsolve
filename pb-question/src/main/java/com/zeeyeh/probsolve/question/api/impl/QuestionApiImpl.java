package com.zeeyeh.probsolve.question.api.impl;

import com.mybatisflex.core.query.QueryWrapper;
import com.zeeyeh.probsolve.question.api.QuestionApi;
import com.zeeyeh.probsolve.question.api.model.entity.Question;
import com.zeeyeh.probsolve.question.api.model.enums.QuestionType;
import com.zeeyeh.probsolve.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * QuestionApi 实现类
 *
 * @author Qinloren
 */
@Service
@RequiredArgsConstructor
public class QuestionApiImpl implements QuestionApi {

    private final QuestionService questionService;

    @Override
    public boolean exists(Long questionId) {
        QueryWrapper queryWrapper = QueryWrapper.create()
                .eq(Question::getId, questionId);
        return questionService.exists(queryWrapper);
    }

    @Override
    public boolean exists(String content, QuestionType type) {
        QueryWrapper queryWrapper = QueryWrapper.create()
                .eq(Question::getContent, content)
                .eq(Question::getType, type);
        return questionService.exists(queryWrapper);
    }

    @Override
    public Question detail(String content, QuestionType type) {
        QueryWrapper queryWrapper = QueryWrapper.create()
                .eq(Question::getContent, content)
                .eq(Question::getType, type);
        return questionService.getOne(queryWrapper);
    }

    @Override
    public boolean save(Question question) {
        return questionService.save(question);
    }

    @Override
    public void saveBatch(List<Question> questions) {
        questionService.saveBatch(questions);
    }

    @Override
    public List<Question> list(QueryWrapper queryWrapper) {
        return questionService.list(queryWrapper);
    }
}
