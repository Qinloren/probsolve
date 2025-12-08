package com.zeeyeh.probsolve.controller;

import com.zeeyeh.probsolve.dto.question.relation.RelationCreateDto;
import com.zeeyeh.probsolve.dto.question.relation.RelationSearchByQuestionDto;
import com.zeeyeh.probsolve.dto.question.relation.RelationSearchDto;
import com.zeeyeh.probsolve.dto.question.relation.RelationUpdateDto;
import com.zeeyeh.probsolve.service.QuestionCategoryRelationService;
import com.zeeyeh.probsolve.vo.basic.QuestionCategoryRelationVo;
import com.zeeyeh.probsolve.vo.basic.QuestionByRelationVo;
import com.zeeyeh.probsolve.vo.search.QuestionCategoryRelationSearchVo;
import com.zeeyeh.probsolve.vo.search.QuestionSearchByRelationVo;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("sys/question/category/relation")
public class QuestionCategoryRelationController {

    private final QuestionCategoryRelationService questionCategoryRelationService;

    public QuestionCategoryRelationController(QuestionCategoryRelationService questionCategoryRelationService) {
        this.questionCategoryRelationService = questionCategoryRelationService;
    }

    /**
     * 创建题目-分类关系
     * @param createDto 创建参数
     * @return 创建结果
     */
    @PostMapping("create")
    @ResponseBody
    public QuestionCategoryRelationVo create(@RequestBody RelationCreateDto createDto) {
        return questionCategoryRelationService.create(createDto);
    }

    /**
     * 修改题目-分类关系
     * @param updateDto 修改参数
     * @return 修改结果
     */
    @PostMapping("update")
    @ResponseBody
    public QuestionCategoryRelationVo update(@RequestBody RelationUpdateDto updateDto) {
        return questionCategoryRelationService.update(updateDto);
    }

    /**
     * 获取题目-分类关系详情
     * @param id 题目-分类关系Id
     * @return 详情
     */
    @GetMapping("detail")
    @ResponseBody
    public QuestionCategoryRelationVo detail(Long id) {
        return questionCategoryRelationService.detail(id);
    }

    /**
     * 删除题目-分类关系
     * @param id 题目-分类关系Id
     */
    @PostMapping("delete")
    @ResponseBody
    public void delete(Long id) {
        questionCategoryRelationService.delete(id);
    }

    /**
     * 搜索题目-分类关系
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
