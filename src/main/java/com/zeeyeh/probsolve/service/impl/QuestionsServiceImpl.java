package com.zeeyeh.probsolve.service.impl;

import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.update.UpdateChain;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.zeeyeh.probsolve.dto.question.QuestionCreateDto;
import com.zeeyeh.probsolve.dto.question.QuestionSearchDto;
import com.zeeyeh.probsolve.dto.question.QuestionUpdateDto;
import com.zeeyeh.probsolve.dto.question.QuestionValidationDto;
import com.zeeyeh.probsolve.entity.ImportRow;
import com.zeeyeh.probsolve.entity.data.QuestionAnswers;
import com.zeeyeh.probsolve.entity.data.QuestionCategoryRelation;
import com.zeeyeh.probsolve.entity.data.Questions;
import com.zeeyeh.probsolve.entity.data.Users;
import com.zeeyeh.probsolve.exceptions.GlobalError;
import com.zeeyeh.probsolve.exceptions.ServiceException;
import com.zeeyeh.probsolve.mapper.QuestionsMapper;
import com.zeeyeh.probsolve.questions.QuestionValidatorManager;
import com.zeeyeh.probsolve.service.QuestionAnswersService;
import com.zeeyeh.probsolve.service.QuestionCategoryRelationService;
import com.zeeyeh.probsolve.service.QuestionsService;
import com.zeeyeh.probsolve.service.UsersService;
import com.zeeyeh.probsolve.vo.basic.QuestionVo;
import com.zeeyeh.probsolve.vo.search.QuestionSearchVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 题目表 服务层实现。
 *
 * @author Qinloren
 * @since 1.0.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class QuestionsServiceImpl extends ServiceImpl<QuestionsMapper, Questions>  implements QuestionsService {

    private final UsersService usersService;
    private final QuestionValidatorManager questionValidatorManager;

    public QuestionsServiceImpl(UsersService usersService, QuestionValidatorManager questionValidatorManager) {
        this.usersService = usersService;
        this.questionValidatorManager = questionValidatorManager;
    }

    @Override
    public QuestionVo create(QuestionCreateDto createDto) {
        if (this.exists(QueryWrapper.create().eq(Questions::getContent, createDto.getContent()))) {
            throw new ServiceException(GlobalError.QUESTION_ALREADY_FOUND);
        }
        if (!usersService.exists(QueryWrapper.create().eq(Users::getId, createDto.getUserId()))) {
            throw new ServiceException(GlobalError.USER_NOT_FOUND);
        }
        Questions questions = new Questions();
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
            throw new ServiceException(GlobalError.QUESTION_CREATE_FAILED);
        }
        questions = this.getOne(QueryWrapper.create().eq(Questions::getContent, createDto.getContent()));
        return QuestionVo.of(questions);
    }

    @Override
    public QuestionVo update(QuestionUpdateDto updateDto) {
        if (!this.exists(QueryWrapper.create().eq(Questions::getId, updateDto.getId()))) {
            throw new ServiceException(GlobalError.QUESTION_NOT_FOUND);
        }
        UpdateChain<Questions> updatedChain = this.updateChain();
        Optional.ofNullable(updateDto.getContent())
                .ifPresent(content -> updatedChain.set(Questions::getContent, content));
        Optional.ofNullable(updateDto.getType())
                .ifPresent(type -> updatedChain.set(Questions::getType, type));
        Optional.ofNullable(updateDto.getDifficulty())
                .ifPresent(difficulty -> updatedChain.set(Questions::getDifficulty, difficulty));
        Optional.ofNullable(updateDto.getScore())
                .ifPresent(score -> updatedChain.set(Questions::getScore, score));
        Optional.ofNullable(updateDto.getAnalysis())
                .ifPresent(analysis -> updatedChain.set(Questions::getAnalysis, analysis));
        Optional.ofNullable(updateDto.getSource())
                .ifPresent(source -> updatedChain.set(Questions::getSource, source));
        Optional.ofNullable(updateDto.getStatus())
                .ifPresent(status -> updatedChain.set(Questions::getStatus, status));
        Optional.ofNullable(updateDto.getUserId())
                .ifPresent(userId -> {
                    if (!usersService.exists(QueryWrapper.create().eq(Users::getId, updateDto.getUserId()))) {
                        throw new ServiceException(GlobalError.USER_NOT_FOUND);
                    }
                    updatedChain.set(Questions::getUserId, userId);
                });
        updatedChain.set(Questions::getUpdateTime, LocalDateTime.now());
        if (!updatedChain.eq(Questions::getId, updateDto.getId()).update()) {
            throw new ServiceException(GlobalError.QUESTION_UPDATE_FAILED);
        }
        boolean updated = updatedChain.where(Questions::getId)
                .eq(updateDto.getId())
                .update();
        if (!updated) {
            throw new ServiceException(GlobalError.QUESTION_UPDATE_FAILED);
        }
        Questions questions = this.getOne(QueryWrapper.create().eq(Questions::getId, updateDto.getId()));
        return QuestionVo.of(questions);
    }

    @Override
    public QuestionVo detail(Long id) {
        if (!this.exists(QueryWrapper.create().eq(Questions::getId, id))) {
            throw new ServiceException(GlobalError.QUESTION_NOT_FOUND);
        }
        Questions questions = this.getOne(QueryWrapper.create().eq(Questions::getId, id));
        return QuestionVo.of(questions);
    }

    @Override
    public void delete(Long id) {
        if (!this.exists(QueryWrapper.create().eq(Questions::getId, id))) {
            throw new ServiceException(GlobalError.QUESTION_NOT_FOUND);
        }
        if (!this.remove(QueryWrapper.create().eq(Questions::getId, id))) {
            throw new ServiceException(GlobalError.QUESTION_DELETE_FAILED);
        }
    }

    @Override
    public QuestionSearchVo search(QuestionSearchDto searchDto) {
        if (!this.exists(QueryWrapper.create().eq(Questions::getId, searchDto.getId()))) {
            throw new ServiceException(GlobalError.QUESTION_NOT_FOUND);
        }
        QueryWrapper queryWrapper = QueryWrapper.create();
        Optional.ofNullable(searchDto.getId())
                .ifPresent(id -> queryWrapper.eq(Questions::getId, id));
        Optional.ofNullable(searchDto.getContent())
                .ifPresent(content -> queryWrapper.eq(Questions::getContent, content));
        Optional.ofNullable(searchDto.getType())
                .ifPresent(type -> queryWrapper.eq(Questions::getType, type));
        Optional.ofNullable(searchDto.getDifficulty())
                .ifPresent(difficulty -> queryWrapper.eq(Questions::getDifficulty, difficulty));
        Optional.ofNullable(searchDto.getScore())
                .ifPresent(score -> queryWrapper.eq(Questions::getScore, score));
        Optional.ofNullable(searchDto.getAnalysis())
                .ifPresent(analysis -> queryWrapper.eq(Questions::getAnalysis, analysis));
        Optional.ofNullable(searchDto.getSource())
                .ifPresent(source -> queryWrapper.eq(Questions::getSource, source));
        Optional.ofNullable(searchDto.getStatus())
                .ifPresent(status -> queryWrapper.eq(Questions::getStatus, status));
        Optional.ofNullable(searchDto.getUserId())
                .ifPresent(userId -> queryWrapper.eq(Questions::getUserId, userId));
        Page<Questions> page = new Page<>(searchDto.getPage(), searchDto.getPageSize());
        Page<Questions> questionsPage = this.page(page, queryWrapper);
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
        QueryWrapper queryWrapper = QueryWrapper.create().eq(Questions::getId, validationDto.getQuestionId());
        if (!this.exists(queryWrapper)) {
            throw new ServiceException(GlobalError.QUESTION_NOT_FOUND);
        }
        Questions questions = this.getOne(queryWrapper);
        Integer type = questions.getType();
        if (!questionValidatorManager.supports(type)) {
            throw new ServiceException(GlobalError.QUESTION_TYPE_NOT_SUPPORTED);
        }
        return questionValidatorManager.getValidator(type).validate(questions, validationDto.getAnswer());
    }
}
