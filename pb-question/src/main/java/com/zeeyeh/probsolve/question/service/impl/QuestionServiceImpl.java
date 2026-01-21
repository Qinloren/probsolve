package com.zeeyeh.probsolve.question.service.impl;

import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.update.UpdateChain;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.zeeyeh.probsolve.common.exceptions.ResponseCode;
import com.zeeyeh.probsolve.common.exceptions.ServiceException;
import com.zeeyeh.probsolve.question.api.model.dto.QuestionCreateDto;
import com.zeeyeh.probsolve.question.api.model.dto.QuestionSearchDto;
import com.zeeyeh.probsolve.question.api.model.dto.QuestionUpdateDto;
import com.zeeyeh.probsolve.question.api.model.dto.QuestionValidationDto;
import com.zeeyeh.probsolve.question.api.model.entity.Question;
import com.zeeyeh.probsolve.question.api.model.vo.QuestionSearchVo;
import com.zeeyeh.probsolve.question.api.model.vo.QuestionVo;
import com.zeeyeh.probsolve.question.manager.QuestionValidatorManager;
import com.zeeyeh.probsolve.question.mapper.QuestionMapper;
import com.zeeyeh.probsolve.question.service.QuestionService;
import com.zeeyeh.probsolve.user.api.UserApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * QuestionService 实现类
 *
 * @author Qinloren
 */
@Service
@RequiredArgsConstructor
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements QuestionService {

    private final UserApi userApi;
    private final QuestionValidatorManager questionValidatorManager;

    @Override
    public QuestionVo create(QuestionCreateDto createDto) {
        if (this.exists(QueryWrapper.create().eq(Question::getContent, createDto.getContent()))) {
            throw new ServiceException(ResponseCode.PARAM_ERROR, "题目已存在");
        }
        if (!userApi.exists(createDto.getUserId())) {
            throw new ServiceException(ResponseCode.PARAM_ERROR, "用户不存在");
        }
        Question questions = new Question();
        questions.setContent(createDto.getContent());
        questions.setType(createDto.getType());
        questions.setDifficulty(createDto.getDifficulty());
        questions.setScore(createDto.getScore());
        questions.setAnalysis(createDto.getAnalysis());
        questions.setSource(createDto.getSource());
        questions.setStatus(createDto.getStatus());
        questions.setUserId(createDto.getUserId());
        questions.setCreateTime(LocalDateTime.now());
        questions.setUpdateTime(LocalDateTime.now());
        if (!this.save(questions)) {
            throw new ServiceException(ResponseCode.BUSINESS_ERROR, "题目创建失败");
        }
        questions = this.getOne(QueryWrapper.create().eq(Question::getContent, createDto.getContent()));
        return QuestionVo.of(questions);
    }

    @Override
    public QuestionVo update(QuestionUpdateDto updateDto) {
        if (!this.exists(QueryWrapper.create().eq(Question::getId, updateDto.getId()))) {
            throw new ServiceException(ResponseCode.PARAM_ERROR, "题目不存在");
        }
        UpdateChain<Question> updatedChain = this.updateChain();
        Optional.ofNullable(updateDto.getContent())
                .ifPresent(content -> updatedChain.set(Question::getContent, content));
        Optional.ofNullable(updateDto.getType())
                .ifPresent(type -> updatedChain.set(Question::getType, type));
        Optional.ofNullable(updateDto.getDifficulty())
                .ifPresent(difficulty -> updatedChain.set(Question::getDifficulty, difficulty));
        Optional.ofNullable(updateDto.getScore())
                .ifPresent(score -> updatedChain.set(Question::getScore, score));
        Optional.ofNullable(updateDto.getAnalysis())
                .ifPresent(analysis -> updatedChain.set(Question::getAnalysis, analysis));
        Optional.ofNullable(updateDto.getSource())
                .ifPresent(source -> updatedChain.set(Question::getSource, source));
        Optional.ofNullable(updateDto.getStatus())
                .ifPresent(status -> updatedChain.set(Question::getStatus, status));
        Optional.ofNullable(updateDto.getUserId())
                .ifPresent(userId -> {
                    if (!userApi.exists(updateDto.getUserId())) {
                        throw new ServiceException(ResponseCode.PARAM_ERROR, "用户不存在");
                    }
                    updatedChain.set(Question::getUserId, userId);
                });
        updatedChain.set(Question::getUpdateTime, LocalDateTime.now());
        if (!updatedChain.eq(Question::getId, updateDto.getId()).update()) {
            throw new ServiceException(ResponseCode.BUSINESS_ERROR, "题目更新失败");
        }
        boolean updated = updatedChain.where(Question::getId)
                .eq(updateDto.getId())
                .update();
        if (!updated) {
            throw new ServiceException(ResponseCode.BUSINESS_ERROR, "题目更新失败");
        }
        Question questions = this.getOne(QueryWrapper.create().eq(Question::getId, updateDto.getId()));
        return QuestionVo.of(questions);
    }

    @Override
    public QuestionVo detail(Long id) {
        if (!this.exists(QueryWrapper.create().eq(Question::getId, id))) {
            throw new ServiceException(ResponseCode.PARAM_ERROR, "题目不存在");
        }
        Question questions = this.getOne(QueryWrapper.create().eq(Question::getId, id));
        return QuestionVo.of(questions);
    }

    @Override
    public void delete(Long id) {
        if (!this.exists(QueryWrapper.create().eq(Question::getId, id))) {
            throw new ServiceException(ResponseCode.PARAM_ERROR, "题目不存在");
        }
        if (!this.remove(QueryWrapper.create().eq(Question::getId, id))) {
            throw new ServiceException(ResponseCode.BUSINESS_ERROR, "题目删除失败");
        }
    }

    @Override
    public QuestionSearchVo search(QuestionSearchDto searchDto) {
        if (!this.exists(QueryWrapper.create().eq(Question::getId, searchDto.getId()))) {
            throw new ServiceException(ResponseCode.PARAM_ERROR, "题目不存在");
        }
        QueryWrapper queryWrapper = QueryWrapper.create();
        Optional.ofNullable(searchDto.getId())
                .ifPresent(id -> queryWrapper.eq(Question::getId, id));
        Optional.ofNullable(searchDto.getContent())
                .ifPresent(content -> queryWrapper.eq(Question::getContent, content));
        Optional.ofNullable(searchDto.getType())
                .ifPresent(type -> queryWrapper.eq(Question::getType, type));
        Optional.ofNullable(searchDto.getDifficulty())
                .ifPresent(difficulty -> queryWrapper.eq(Question::getDifficulty, difficulty));
        Optional.ofNullable(searchDto.getScore())
                .ifPresent(score -> queryWrapper.eq(Question::getScore, score));
        Optional.ofNullable(searchDto.getAnalysis())
                .ifPresent(analysis -> queryWrapper.eq(Question::getAnalysis, analysis));
        Optional.ofNullable(searchDto.getSource())
                .ifPresent(source -> queryWrapper.eq(Question::getSource, source));
        Optional.ofNullable(searchDto.getStatus())
                .ifPresent(status -> queryWrapper.eq(Question::getStatus, status));
        Optional.ofNullable(searchDto.getUserId())
                .ifPresent(userId -> queryWrapper.eq(Question::getUserId, userId));
        Page<Question> page = new Page<>(searchDto.getPage(), searchDto.getPageSize());
        Page<Question> questionsPage = this.page(page, queryWrapper);
        List<QuestionVo> list = questionsPage.getRecords()
                .stream()
                .map(QuestionVo::of)
                .toList();
        return new QuestionSearchVo(
                list,
                page.getTotalPage(),
                page.getPageNumber(),
                page.getPageSize()
        );
    }

    @Override
    public boolean validate(QuestionValidationDto validationDto) {
        QueryWrapper queryWrapper = QueryWrapper.create().eq(Question::getId, validationDto.getQuestionId());
        if (!this.exists(queryWrapper)) {
            throw new ServiceException(ResponseCode.PARAM_ERROR, "题目不存在");
        }
        Question questions = this.getOne(queryWrapper);
        Integer type = questions.getType().getCode();
        if (!questionValidatorManager.supports(type)) {
            throw new ServiceException(ResponseCode.PARAM_ERROR, "题目类型不支持");
        }
        return questionValidatorManager.getValidator(type).validate(questions, validationDto.getAnswer());
    }
}
