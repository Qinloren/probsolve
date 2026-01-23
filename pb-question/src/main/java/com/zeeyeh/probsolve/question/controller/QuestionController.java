package com.zeeyeh.probsolve.question.controller;

import com.zeeyeh.probsolve.common.entity.Result;
import com.zeeyeh.probsolve.question.api.model.dto.*;
import com.zeeyeh.probsolve.question.api.model.vo.QuestionSearchVo;
import com.zeeyeh.probsolve.question.api.model.vo.QuestionVo;
import com.zeeyeh.probsolve.question.service.QuestionService;
import com.zeeyeh.probsolve.question.service.QuestionUploadService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 题目管理控制器
 *
 * @author Qinloren
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("sys/question")
public class QuestionController {

    private final QuestionService questionService;
    private final QuestionUploadService questionUploadService;

    /**
     * 创建题目接口
     * @param createDto 创建题目参数
     * @return 创建题目结果
     */
    @PostMapping("create")
    @ResponseBody
    public QuestionVo create(@RequestBody QuestionCreateDto createDto) {
        return questionService.create(createDto);
    }

    /**
     * 修改题目接口
     * @param updateDto 修改题目参数
     * @return 修改题目结果
     */
    @PostMapping("update")
    @ResponseBody
    public QuestionVo update(@RequestBody QuestionUpdateDto updateDto) {
        return questionService.update(updateDto);
    }

    /**
     * 获取题目详情接口
     * @param id 题目 id
     * @return 题目详情
     */
    @GetMapping("detail")
    @ResponseBody
    public QuestionVo detail(@RequestParam Long id) {
        return questionService.detail(id);
    }

    /**
     * 删除题目接口
     * @param id 删除题目参数
     */
    @PostMapping("delete")
    @ResponseBody
    public void delete(@RequestParam Long id) {
        questionService.delete(id);
    }

    /**
     * 搜索接口
     * @param searchDto 搜索参数
     * @return 搜索结果
     */
    @GetMapping("search")
    @ResponseBody
    public QuestionSearchVo search(QuestionSearchDto searchDto) {
        return questionService.search(searchDto);
    }

    /**
     * 验证答案
     */
    @PostMapping("validate")
    @ResponseBody
    public boolean validate(@RequestBody QuestionValidationDto validationDto, HttpServletRequest request) {
        return questionService.validate(validationDto, request);
    }

    /**
     * 导入题库
     * @param importDto 导入参数
     * @param request HTTP 请求
     * @return 导入结果
     */
    @PostMapping("import")
    @ResponseBody
    public Result<String> upload(UploadQuestionImportDto importDto, HttpServletRequest request) {
        String result = questionUploadService.upload(importDto, request);
        return Result.success(result);
    }

    /**
     * 导出题库
      * @param deriveDto 导出参数
     * @param response HTTP 响应
     */
    @PostMapping("derive")
    public void derive(@RequestBody QuestionLibDeriveDto deriveDto, HttpServletResponse response) {
        questionUploadService.derive(deriveDto, response);
    }
}
