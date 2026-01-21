package com.zeeyeh.probsolve.question.service.impl;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.update.UpdateChain;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.zeeyeh.probsolve.common.exceptions.ResponseCode;
import com.zeeyeh.probsolve.common.exceptions.ServiceException;
import com.zeeyeh.probsolve.question.api.model.dto.RelationCreateDto;
import com.zeeyeh.probsolve.question.api.model.dto.RelationSearchByQuestionDto;
import com.zeeyeh.probsolve.question.api.model.dto.RelationSearchDto;
import com.zeeyeh.probsolve.question.api.model.dto.RelationUpdateDto;
import com.zeeyeh.probsolve.question.api.model.entity.Question;
import com.zeeyeh.probsolve.question.api.model.entity.QuestionCategoryRelation;
import com.zeeyeh.probsolve.question.api.model.vo.QuestionAnswerVo;
import com.zeeyeh.probsolve.question.api.model.vo.QuestionOptionsItemVo;
import com.zeeyeh.probsolve.question.api.model.vo.QuestionVo;
import com.zeeyeh.probsolve.question.mapper.QuestionCategoryRelationMapper;
import com.zeeyeh.probsolve.question.service.QuestionAnswerService;
import com.zeeyeh.probsolve.question.service.QuestionCategoryRelationService;
import com.zeeyeh.probsolve.question.service.QuestionCategoryService;
import com.zeeyeh.probsolve.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * QuestionCategoryRelationService 实现类
 *
 * @author Qinloren
 */
@Service
@RequiredArgsConstructor
public class QuestionCategoryRelationServiceImpl extends ServiceImpl<QuestionCategoryRelationMapper, QuestionCategoryRelation> implements QuestionCategoryRelationService {
    // private final QuestionApi questionApi;
    // private final QuestionCategoryApi questionCategoryApi;
    // private final QuestionAnswerApi questionAnswerApi;

    private final QuestionService questionService;
    private final QuestionCategoryService questionCategoryService;
    private final QuestionAnswerService questionAnswerService;

    @Override
    public com.zeeyeh.probsolve.question.api.model.vo.QuestionCategoryRelationVo create(RelationCreateDto createDto) {
        QueryWrapper queryWrapper = QueryWrapper.create()
                .where(QuestionCategoryRelation::getQuestionId).eq(createDto.getQuestionsId())
                .and(QuestionCategoryRelation::getCategoryId).eq(createDto.getCategoryId());
        if (this.exists(queryWrapper)) {
            throw new ServiceException(ResponseCode.BUSINESS_ERROR, "关系已存在");
        }
        QuestionCategoryRelation relation = new QuestionCategoryRelation();
        QueryWrapper questionQueryWrapper = QueryWrapper.create()
                .eq(Question::getId, createDto.getQuestionsId());
        if (!questionService.exists(questionQueryWrapper)) {
            throw new ServiceException(ResponseCode.PARAM_ERROR, "题目不存在");
        }
        relation.setQuestionId(createDto.getQuestionsId());
        QueryWrapper categoryQueryWrapper = QueryWrapper.create()
                .eq(QuestionCategoryRelation::getCategoryId, createDto.getCategoryId());
        if (!questionCategoryService.exists(categoryQueryWrapper)) {
            throw new ServiceException(ResponseCode.PARAM_ERROR, "分类不存在");
        }
        relation.setCategoryId(createDto.getCategoryId());
        if (!this.save(relation)) {
            throw new ServiceException(ResponseCode.BUSINESS_ERROR, "关系创建失败");
        }
        return com.zeeyeh.probsolve.question.api.model.vo.QuestionCategoryRelationVo.of(relation);
    }

    @Override
    public com.zeeyeh.probsolve.question.api.model.vo.QuestionCategoryRelationVo update(RelationUpdateDto updateDto) {
        if (!this.exists(QueryWrapper.create().eq(QuestionCategoryRelation::getId, updateDto.getId()))) {
            throw new ServiceException(ResponseCode.PARAM_ERROR, "关系不存在");
        }
        UpdateChain<QuestionCategoryRelation> updatedChain = this.updateChain();
        Optional.ofNullable(updateDto.getQuestionsId())
                .ifPresent(questionsId -> {
                    QueryWrapper queryWrapper = QueryWrapper.create()
                            .eq(QuestionCategoryRelation::getQuestionId, updateDto.getQuestionsId());
                    if (!questionService.exists(queryWrapper)) {
                        throw new ServiceException(ResponseCode.PARAM_ERROR, "题目不存在");
                    }
                    updatedChain.set(QuestionCategoryRelation::getQuestionId, questionsId);
                });
        Optional.ofNullable(updateDto.getCategoryId())
                .ifPresent(categoryId -> {
                    QueryWrapper queryWrapper = QueryWrapper.create()
                            .eq(QuestionCategoryRelation::getCategoryId, updateDto.getCategoryId());
                    if (!questionCategoryService.exists(queryWrapper)) {
                        throw new ServiceException(ResponseCode.PARAM_ERROR, "分类不存在");
                    }
                    updatedChain.set(QuestionCategoryRelation::getCategoryId, categoryId);
                });
        boolean updated = updatedChain.where(QuestionCategoryRelation::getId)
                .eq(updateDto.getId())
                .update();
        if (!updated) {
            throw new ServiceException(ResponseCode.BUSINESS_ERROR, "关系更新失败");
        }
        QuestionCategoryRelation relation = this.getOne(QueryWrapper.create().eq(QuestionCategoryRelation::getId, updateDto.getId()));
        return com.zeeyeh.probsolve.question.api.model.vo.QuestionCategoryRelationVo.of(relation);
    }

    @Override
    public com.zeeyeh.probsolve.question.api.model.vo.QuestionCategoryRelationVo detail(Long id) {
        if (!this.exists(QueryWrapper.create().eq(QuestionCategoryRelation::getId, id))) {
            throw new ServiceException(ResponseCode.PARAM_ERROR, "关系不存在");
        }
        QuestionCategoryRelation relation = this.getOne(QueryWrapper.create().eq(QuestionCategoryRelation::getId, id));
        return com.zeeyeh.probsolve.question.api.model.vo.QuestionCategoryRelationVo.of(relation);
    }

    @Override
    public void delete(Long id) {
        if (!this.exists(QueryWrapper.create().eq(QuestionCategoryRelation::getId, id))) {
            throw new ServiceException(ResponseCode.PARAM_ERROR, "关系不存在");
        }
        if (!this.remove(QueryWrapper.create().eq(QuestionCategoryRelation::getId, id))) {
            throw new ServiceException(ResponseCode.BUSINESS_ERROR, "关系删除失败");
        }
    }

    @Override
    public com.zeeyeh.probsolve.question.api.model.vo.QuestionCategoryRelationSearchVo search(RelationSearchDto searchDto) {
        QueryWrapper queryWrapper = QueryWrapper.create();
        Optional.ofNullable(searchDto.getId())
                .ifPresent(id -> queryWrapper.eq(QuestionCategoryRelation::getId, id));
        Optional.ofNullable(searchDto.getQuestionsId())
                .ifPresent(questionsId -> queryWrapper.eq(QuestionCategoryRelation::getQuestionId, questionsId));
        Optional.ofNullable(searchDto.getCategoryId())
                .ifPresent(categoryId -> queryWrapper.eq(QuestionCategoryRelation::getCategoryId, categoryId));
        Page<QuestionCategoryRelation> page = new Page<>(searchDto.getPage(), searchDto.getPageSize());
        Page<QuestionCategoryRelation> relationPage = this.page(page, queryWrapper);
        List<com.zeeyeh.probsolve.question.api.model.vo.QuestionCategoryRelationVo> list = relationPage.getRecords()
                .stream()
                .map(com.zeeyeh.probsolve.question.api.model.vo.QuestionCategoryRelationVo::of)
                .toList();
        return new com.zeeyeh.probsolve.question.api.model.vo.QuestionCategoryRelationSearchVo(
                list,
                relationPage.getTotalPage(),
                relationPage.getPageNumber(),
                relationPage.getPageSize());
    }

    @Override
    public List<QuestionVo> findByCategoryId(Long categoryId) {
        QueryWrapper queryWrapper = QueryWrapper.create()
                .where(QuestionCategoryRelation::getCategoryId).eq(categoryId);
        // 验证分类是否存在
        if (!questionCategoryService.exists(queryWrapper)) {
            throw new ServiceException(ResponseCode.PARAM_ERROR, "分类不存在");
        }

        // 查询该分类下的所有关联关系
        List<QuestionCategoryRelation> relations = this.list(queryWrapper);

        // 提取题目 ID 列表
        List<Long> questionIds = relations.stream()
                .map(QuestionCategoryRelation::getQuestionId)
                .toList();

        // 如果没有关联的题目，返回空列表
        if (questionIds.isEmpty()) {
            return List.of();
        }

        // 根据题目 ID 查询题目详情
        QueryWrapper questionQueryWrapper = QueryWrapper.create()
                .where(Question::getId).in(questionIds);
        List<Question> questions = questionService.list(questionQueryWrapper);

        // 转换为 VO 并返回
        return questions.stream()
                .map(QuestionVo::of)
                .toList();
    }

    @Override
    public com.zeeyeh.probsolve.question.api.model.vo.QuestionSearchByRelationVo searchByQuestion(RelationSearchByQuestionDto searchByQuestionDto) {
        QueryWrapper relationQueryWrapper = QueryWrapper.create()
                .eq(QuestionCategoryRelation::getCategoryId, searchByQuestionDto.getCategoryId());
        if (!this.exists(relationQueryWrapper)) {
            throw new ServiceException(ResponseCode.PARAM_ERROR, "题库不存在");
        }
        List<com.zeeyeh.probsolve.question.api.model.vo.QuestionCategoryRelationVo> relationVos = this.listAs(relationQueryWrapper, com.zeeyeh.probsolve.question.api.model.vo.QuestionCategoryRelationVo.class);
        List<Long> questionIds = relationVos.stream()
                .map(com.zeeyeh.probsolve.question.api.model.vo.QuestionCategoryRelationVo::getQuestionId)
                .distinct()
                .toList();
        if (questionIds.isEmpty()) {
            return new com.zeeyeh.probsolve.question.api.model.vo.QuestionSearchByRelationVo(
                    Collections.emptyList(),
                    0L,
                    1L,
                    0L
            );
        }
        QueryWrapper queryWrapper = QueryWrapper.create()
                .in(Question::getId, questionIds);
        Optional.ofNullable(searchByQuestionDto.getDifficulty())
                .ifPresent(difficulty -> queryWrapper.eq(Question::getDifficulty, difficulty));
        Optional.ofNullable(searchByQuestionDto.getType())
                .ifPresent(type -> queryWrapper.eq(Question::getType, type));
        Optional.ofNullable(searchByQuestionDto.getSize())
                .ifPresent(queryWrapper::limit);
        List<QuestionVo> list = questionService.list(queryWrapper)
                .stream()
                .map(QuestionVo::of)
                .toList();
        List<com.zeeyeh.probsolve.question.api.model.vo.QuestionByRelationVo> byRelationVos = new ArrayList<>(list.stream()
                .filter(questionVo -> questionVo.getType().getCode() == 1 || questionVo.getType().getCode() == 2)
                .map(questionVo -> {
                    QuestionAnswerVo questionAnswerVo = questionAnswerService.detail(questionVo.getId());
                    String content = questionAnswerVo.getContent();
                    JSONArray jsonArray = JSONArray.parseArray(content);
                    List<QuestionOptionsItemVo> options = new ArrayList<>();
                    for (Object o : jsonArray) {
                        JSONObject jsonObject = (JSONObject) o;
                        Integer index = jsonObject.getInteger("index");
                        String value = jsonObject.getString("value");
                        options.add(new QuestionOptionsItemVo(index, value));
                    }
                    return com.zeeyeh.probsolve.question.api.model.vo.QuestionByRelationVo.of(questionVo, options);
                })
                .toList());
        byRelationVos.addAll(list.stream()
                .filter(questionVo -> questionVo.getType().getCode() > 2)
                .map(questionVo -> com.zeeyeh.probsolve.question.api.model.vo.QuestionByRelationVo.of(questionVo, Collections.emptyList()))
                .toList());
        return new com.zeeyeh.probsolve.question.api.model.vo.QuestionSearchByRelationVo(
                byRelationVos,
                ((Integer) list.size()).longValue(),
                1L,
                ((Integer) list.size()).longValue()
        );
    }
}
