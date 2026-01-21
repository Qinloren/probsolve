package com.zeeyeh.probsolve.question.controller;

import com.zeeyeh.probsolve.question.api.model.dto.QuestionCategoryCreateDto;
import com.zeeyeh.probsolve.question.api.model.dto.QuestionCategorySearchDto;
import com.zeeyeh.probsolve.question.api.model.dto.QuestionCategoryUpdateDto;
import com.zeeyeh.probsolve.question.api.model.vo.QuestionCategorySearchVo;
import com.zeeyeh.probsolve.question.api.model.vo.QuestionCategoryVo;
import com.zeeyeh.probsolve.question.service.QuestionCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 题库分类管理接口
 *
 * @author Qinloren
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("sys/question/category")
public class QuestionCategoryController {

    private final QuestionCategoryService questionCategoriesService;

    /**
     * 创建题库分类接口
     * @param createDto 创建题库分类参数
     * @return 创建题库分类结果
     */
    @PostMapping("create")
    @ResponseBody
    public QuestionCategoryVo create(@RequestBody QuestionCategoryCreateDto createDto) {
        return questionCategoriesService.create(createDto);
    }

    /**
     * 修改题库分类接口
     * @param updateDto 修改题库分类参数
     * @return 修改题库分类结果
     */
    @PostMapping("update")
    @ResponseBody
    public QuestionCategoryVo update(@RequestBody QuestionCategoryUpdateDto updateDto) {
        return questionCategoriesService.update(updateDto);
    }

    /**
     * 获取题库分类详情接口
     * @param id 题库分类id
     * @return 题库分类详情
     */
    @GetMapping("detail")
    @ResponseBody
    public QuestionCategoryVo detail(@RequestParam Long id) {
        return questionCategoriesService.detail(id);
    }

    /**
     * 删除题库分类接口
     * @param id 删除题库分类参数
     */
    @PostMapping("delete")
    @ResponseBody
    public void delete(@RequestParam Long id) {
        questionCategoriesService.delete(id);
    }

    /**
     * 搜索接口
     * @param searchDto 搜索参数
     * @return 搜索结果
     */
    @GetMapping("search")
    @ResponseBody
    public QuestionCategorySearchVo search(QuestionCategorySearchDto searchDto) {
        return questionCategoriesService.search(searchDto);
    }
}
