package com.zeeyeh.probsolve.question.controller;

import com.zeeyeh.probsolve.question.api.model.dto.RelationCreateDto;
import com.zeeyeh.probsolve.question.api.model.dto.RelationSearchByQuestionDto;
import com.zeeyeh.probsolve.question.api.model.dto.RelationSearchDto;
import com.zeeyeh.probsolve.question.api.model.dto.RelationUpdateDto;
import com.zeeyeh.probsolve.question.api.model.vo.QuestionCategoryRelationSearchVo;
import com.zeeyeh.probsolve.question.api.model.vo.QuestionCategoryRelationVo;
import com.zeeyeh.probsolve.question.api.model.vo.QuestionSearchByRelationVo;
import com.zeeyeh.probsolve.question.service.QuestionCategoryRelationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 题库关系控制器
 *
 * @author Qinloren
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("sys/question/category/relation")
public class QuestionCategoryRelationController {

    private final QuestionCategoryRelationService questionCategoryRelationService;

    /**
     * 创建题库关系
     * @param createDto 创建参数
     * @return 创建结果
     */
    @PostMapping("create")
    @ResponseBody
    public QuestionCategoryRelationVo create(@RequestBody RelationCreateDto createDto) {
        return questionCategoryRelationService.create(createDto);
    }

    /**
     * 修改题库关系
     * @param updateDto 修改参数
     * @return 修改结果
     */
    @PostMapping("update")
    @ResponseBody
    public QuestionCategoryRelationVo update(@RequestBody RelationUpdateDto updateDto) {
        return questionCategoryRelationService.update(updateDto);
    }

    /**
     * 获取题库关系详情
     * @param id 题库关系Id
     * @return 详情
     */
    @GetMapping("detail")
    @ResponseBody
    public QuestionCategoryRelationVo detail(Long id) {
        return questionCategoryRelationService.detail(id);
    }

    /**
     * 删除题库关系
     * @param id 题库关系Id
     */
    @PostMapping("delete")
    @ResponseBody
    public void delete(Long id) {
        questionCategoryRelationService.delete(id);
    }

    /**
     * 搜索题库关系
     * @param searchDto 搜索条件
     * @return 搜索结果
     */
    @GetMapping("search")
    @ResponseBody
    public QuestionCategoryRelationSearchVo search(RelationSearchDto searchDto) {
        return questionCategoryRelationService.search(searchDto);
    }

    /**
     * 搜索题目
     * @param searchByQuestionDto 搜索条件
     * @return 搜索结果
     */
    @GetMapping("searchByQuestion")
    @ResponseBody
    public QuestionSearchByRelationVo searchByQuestion(RelationSearchByQuestionDto searchByQuestionDto) {
        return questionCategoryRelationService.searchByQuestion(searchByQuestionDto);
    }
}
