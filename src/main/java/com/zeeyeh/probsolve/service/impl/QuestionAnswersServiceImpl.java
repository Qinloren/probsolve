package com.zeeyeh.probsolve.service.impl;

import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.update.UpdateChain;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.zeeyeh.probsolve.dto.question.answer.QuestionAnswerCreateDto;
import com.zeeyeh.probsolve.dto.question.answer.QuestionAnswerSearchDto;
import com.zeeyeh.probsolve.dto.question.answer.QuestionAnswerUpdateDto;
import com.zeeyeh.probsolve.entity.ImportRow;
import com.zeeyeh.probsolve.entity.data.QuestionAnswers;
import com.zeeyeh.probsolve.entity.data.QuestionCategoryRelation;
import com.zeeyeh.probsolve.entity.data.Questions;
import com.zeeyeh.probsolve.exceptions.GlobalError;
import com.zeeyeh.probsolve.exceptions.ServiceException;
import com.zeeyeh.probsolve.mapper.QuestionAnswersMapper;
import com.zeeyeh.probsolve.service.QuestionAnswersService;
import com.zeeyeh.probsolve.service.QuestionsService;
import com.zeeyeh.probsolve.vo.basic.QuestionAnswerVo;
import com.zeeyeh.probsolve.vo.search.QuestionAnswerSearchVo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 标准答案表 服务层实现。
 *
 * @author Qinloren
 * @since 1.0.0
 */
@Service
public class QuestionAnswersServiceImpl extends ServiceImpl<QuestionAnswersMapper, QuestionAnswers>  implements QuestionAnswersService{
    private final QuestionsService questionsService;

    public QuestionAnswersServiceImpl(QuestionsService questionsService) {
        this.questionsService = questionsService;
    }

    @Override
    public QuestionAnswerVo create(QuestionAnswerCreateDto createDto) {
        if (this.exists(QueryWrapper.create().eq(QuestionAnswers::getQuestionId, createDto.getQuestionId()))) {
            throw new ServiceException(GlobalError.QUESTION_ALREADY_FOUND);
        }
        QuestionAnswers questionAnswers = new QuestionAnswers();
        if (!questionsService.exists(QueryWrapper.create().eq(Questions::getId, createDto.getQuestionId()))) {
            throw new ServiceException(GlobalError.QUESTION_NOT_FOUND);
        }
        questionAnswers.setQuestionId(createDto.getQuestionId());
        questionAnswers.setContent(createDto.getContent());
        questionAnswers.setTips(createDto.getTips());
        if (!this.save(questionAnswers)) {
            throw new ServiceException(GlobalError.QUESTION_CREATE_FAILED);
        }
        QuestionAnswers answers = this.getOne(QueryWrapper.create().eq(QuestionAnswers::getQuestionId, createDto.getQuestionId()));
        return QuestionAnswerVo.of(answers);
    }

    @Override
    public QuestionAnswerVo update(QuestionAnswerUpdateDto updateDto) {
        if (!this.exists(QueryWrapper.create().eq(QuestionAnswers::getId, updateDto.getId()))) {
            throw new ServiceException(GlobalError.QUESTION_NOT_FOUND);
        }
        UpdateChain<QuestionAnswers> updatedChain = this.updateChain();
        Optional.ofNullable(updateDto.getContent())
                .ifPresent(content -> updatedChain.set(QuestionAnswers::getContent, content));
        Optional.ofNullable(updateDto.getAnswers())
                .ifPresent(answers -> updatedChain.set(QuestionAnswers::getAnswers, answers));
        Optional.ofNullable(updateDto.getTips())
                .ifPresent(tips -> updatedChain.set(QuestionAnswers::getTips, tips));
        boolean updated = updatedChain.where(QuestionAnswers::getId)
                .eq(updateDto.getId())
                .update();
        if (!updated) {
            throw new ServiceException(GlobalError.QUESTION_UPDATE_FAILED);
        }
        QuestionAnswers answers = this.getOne(QueryWrapper.create().eq(QuestionAnswers::getId, updateDto.getId()));
        return QuestionAnswerVo.of(answers);
    }

    @Override
    public QuestionAnswerVo detail(Long id) {
        if (!this.exists(QueryWrapper.create().eq(QuestionAnswers::getId, id))) {
            throw new ServiceException(GlobalError.QUESTION_NOT_FOUND);
        }
        QuestionAnswers answers = this.getOne(QueryWrapper.create().eq(QuestionAnswers::getId, id));
        return QuestionAnswerVo.of(answers);
    }

    @Override
    public void delete(Long id) {
        if (!this.exists(QueryWrapper.create().eq(QuestionAnswers::getId, id))) {
            throw new ServiceException(GlobalError.QUESTION_NOT_FOUND);
        }
        if (!this.remove(QueryWrapper.create().eq(QuestionAnswers::getId, id))) {
            throw new ServiceException(GlobalError.QUESTION_DELETE_FAILED);
        }
    }

    @Override
    public QuestionAnswerSearchVo search(QuestionAnswerSearchDto searchDto) {
        QueryWrapper queryWrapper = QueryWrapper.create();
        Optional.ofNullable(searchDto.getQuestionId())
                .ifPresent(questionId -> queryWrapper.eq(QuestionAnswers::getQuestionId, questionId));
        Optional.ofNullable(searchDto.getContent())
                .ifPresent(content -> queryWrapper.eq(QuestionAnswers::getContent, content));
        Optional.ofNullable(searchDto.getAnswers())
                .ifPresent(answers -> queryWrapper.eq(QuestionAnswers::getAnswers, answers));
        Optional.ofNullable(searchDto.getTips())
                .ifPresent(tips -> queryWrapper.eq(QuestionAnswers::getTips, tips));
        Page<QuestionAnswers> page = new Page<>(searchDto.getPage(), searchDto.getPageSize());
        Page<QuestionAnswers> answersPage = this.page(page, queryWrapper);
        List<QuestionAnswerVo> list = answersPage.getRecords()
                .stream()
                .map(QuestionAnswerVo::of)
                .toList();
        return new QuestionAnswerSearchVo(
                list,
                answersPage.getTotalPage(),
                answersPage.getPageNumber(),
                answersPage.getPageSize());
    }
}
