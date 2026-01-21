package com.zeeyeh.probsolve.question.service.impl;

import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.update.UpdateChain;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.zeeyeh.probsolve.common.exceptions.ResponseCode;
import com.zeeyeh.probsolve.common.exceptions.ServiceException;
import com.zeeyeh.probsolve.question.api.model.dto.QuestionAnswerCreateDto;
import com.zeeyeh.probsolve.question.api.model.dto.QuestionAnswerSearchDto;
import com.zeeyeh.probsolve.question.api.model.dto.QuestionAnswerUpdateDto;
import com.zeeyeh.probsolve.question.api.model.entity.Question;
import com.zeeyeh.probsolve.question.api.model.entity.QuestionAnswer;
import com.zeeyeh.probsolve.question.api.model.vo.QuestionAnswerSearchVo;
import com.zeeyeh.probsolve.question.api.model.vo.QuestionAnswerVo;
import com.zeeyeh.probsolve.question.mapper.QuestionAnswerMapper;
import com.zeeyeh.probsolve.question.service.QuestionAnswerService;
import com.zeeyeh.probsolve.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * QuestionAnswerService 实现类
 *
 * @author Qinloren
 */
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class QuestionAnswerServiceImpl extends ServiceImpl<QuestionAnswerMapper, QuestionAnswer> implements QuestionAnswerService {

    private final QuestionService questionService;

    @Override
    public QuestionAnswerVo create(QuestionAnswerCreateDto createDto) {
        if (this.exists(QueryWrapper.create().eq(QuestionAnswer::getQuestionId, createDto.getQuestionId()))) {
            throw new ServiceException(ResponseCode.PARAM_ERROR, "题目已存在");
        }
        QuestionAnswer questionAnswers = new QuestionAnswer();
        if (!questionService.exists(QueryWrapper.create().eq(Question::getId, createDto.getQuestionId()))) {
            throw new ServiceException(ResponseCode.PARAM_ERROR, "题目不存在");
        }
        questionAnswers.setQuestionId(createDto.getQuestionId());
        questionAnswers.setContent(createDto.getContent());
        questionAnswers.setTips(createDto.getTips());
        if (!this.save(questionAnswers)) {
            throw new ServiceException(ResponseCode.PARAM_ERROR, "题目创建失败");
        }
        QuestionAnswer answers = this.getOne(QueryWrapper.create().eq(QuestionAnswer::getQuestionId, createDto.getQuestionId()));
        return QuestionAnswerVo.of(answers);
    }

    @Override
    public QuestionAnswerVo update(QuestionAnswerUpdateDto updateDto) {
        if (!this.exists(QueryWrapper.create().eq(QuestionAnswer::getId, updateDto.getId()))) {
            throw new ServiceException(ResponseCode.PARAM_ERROR, "题目不存在");
        }
        UpdateChain<QuestionAnswer> updatedChain = this.updateChain();
        Optional.ofNullable(updateDto.getContent())
                .ifPresent(content -> updatedChain.set(QuestionAnswer::getContent, content));
        Optional.ofNullable(updateDto.getAnswers())
                .ifPresent(answers -> updatedChain.set(QuestionAnswer::getAnswers, answers));
        Optional.ofNullable(updateDto.getTips())
                .ifPresent(tips -> updatedChain.set(QuestionAnswer::getTips, tips));
        boolean updated = updatedChain.where(QuestionAnswer::getId)
                .eq(updateDto.getId())
                .update();
        if (!updated) {
            throw new ServiceException(ResponseCode.PARAM_ERROR, "题目更新失败");
        }
        QuestionAnswer answers = this.getOne(QueryWrapper.create().eq(QuestionAnswer::getId, updateDto.getId()));
        return QuestionAnswerVo.of(answers);
    }

    @Override
    public QuestionAnswerVo detail(Long id) {
        if (!this.exists(QueryWrapper.create().eq(QuestionAnswer::getId, id))) {
            throw new ServiceException(ResponseCode.PARAM_ERROR, "题目不存在");
        }
        QuestionAnswer answers = this.getOne(QueryWrapper.create().eq(QuestionAnswer::getId, id));
        return QuestionAnswerVo.of(answers);
    }

    @Override
    public QuestionAnswerVo detailByQuestionId(Long questionId) {
        if (!this.exists(QueryWrapper.create().eq(QuestionAnswer::getQuestionId, questionId))) {
            throw new ServiceException(ResponseCode.PARAM_ERROR, "题目不存在");
        }
        QuestionAnswer answer = this.getOne(QueryWrapper.create().eq(QuestionAnswer::getQuestionId, questionId));
        return QuestionAnswerVo.of(answer);
    }

    @Override
    public void delete(Long id) {
        if (!this.exists(QueryWrapper.create().eq(QuestionAnswer::getId, id))) {
            throw new ServiceException(ResponseCode.PARAM_ERROR, "题目不存在");
        }
        if (!this.remove(QueryWrapper.create().eq(QuestionAnswer::getId, id))) {
            throw new ServiceException(ResponseCode.PARAM_ERROR, "题目删除失败");
        }
    }

    @Override
    public QuestionAnswerSearchVo search(QuestionAnswerSearchDto searchDto) {
        QueryWrapper queryWrapper = QueryWrapper.create();
        Optional.ofNullable(searchDto.getQuestionId())
                .ifPresent(questionId -> queryWrapper.eq(QuestionAnswer::getQuestionId, questionId));
        Optional.ofNullable(searchDto.getContent())
                .ifPresent(content -> queryWrapper.eq(QuestionAnswer::getContent, content));
        Optional.ofNullable(searchDto.getAnswers())
                .ifPresent(answers -> queryWrapper.eq(QuestionAnswer::getAnswers, answers));
        Optional.ofNullable(searchDto.getTips())
                .ifPresent(tips -> queryWrapper.eq(QuestionAnswer::getTips, tips));
        Page<QuestionAnswer> page = new Page<>(searchDto.getPage(), searchDto.getPageSize());
        Page<QuestionAnswer> answersPage = this.page(page, queryWrapper);
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
