package com.zeeyeh.probsolve.controller;


import com.zeeyeh.probsolve.dto.question.category.QuestionCategoryCreateDto;
import com.zeeyeh.probsolve.dto.question.category.QuestionCategorySearchDto;
import com.zeeyeh.probsolve.dto.question.category.QuestionCategoryUpdateDto;
import com.zeeyeh.probsolve.service.QuestionCategoriesService;
import com.zeeyeh.probsolve.vo.basic.QuestionCategoryVo;
import com.zeeyeh.probsolve.vo.search.QuestionCategorySearchVo;
import org.springframework.web.bind.annotation.*;

/**
 * 题目分类接口
 */
@RestController
@RequestMapping("sys/question/category")
public class QuestionCategoryController {

    private final QuestionCategoriesService questionCategoriesService;

    public QuestionCategoryController(QuestionCategoriesService questionCategoriesService) {
        this.questionCategoriesService = questionCategoriesService;
    }

    /**
     * 创建题目分类接口
     * @param createDto 创建题目分类参数
     * @return 创建题目分类结果
     */
    @PostMapping("create")
    @ResponseBody
    public QuestionCategoryVo create(@RequestBody QuestionCategoryCreateDto createDto) {
        return questionCategoriesService.create(createDto);
    }

    /**
     * 修改题目分类接口
     * @param updateDto 修改题目分类参数
     * @return 修改题目分类结果
     */
    @PostMapping("update")
    @ResponseBody
    public QuestionCategoryVo update(@RequestBody QuestionCategoryUpdateDto updateDto) {
        return questionCategoriesService.update(updateDto);
    }

    /**
     * 获取题目分类详情接口
     * @param id 题目分类id
     * @return 题目分类详情
     */
    @GetMapping("detail")
    @ResponseBody
    public QuestionCategoryVo detail(@RequestParam Long id) {
        return questionCategoriesService.detail(id);
    }

    /**
     * 删除题目分类接口
     * @param id 删除题目分类参数
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
