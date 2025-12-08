package com.zeeyeh.probsolve.controller;

import com.zeeyeh.probsolve.dto.question.QuestionCreateDto;
import com.zeeyeh.probsolve.dto.question.QuestionSearchDto;
import com.zeeyeh.probsolve.dto.question.QuestionUpdateDto;
import com.zeeyeh.probsolve.dto.question.QuestionValidationDto;
import com.zeeyeh.probsolve.service.QuestionsService;
import com.zeeyeh.probsolve.vo.basic.QuestionVo;
import com.zeeyeh.probsolve.vo.search.QuestionSearchVo;
import org.springframework.web.bind.annotation.*;

/**
 * 题目接口
 */
@RestController
@RequestMapping("sys/question")
public class QuestionController {

    private final QuestionsService questionsService;

    public QuestionController(QuestionsService questionsService) {
        this.questionsService = questionsService;
    }

    /**
     * 创建题目接口
     * @param createDto 创建题目参数
     * @return 创建题目结果
     */
    @PostMapping("create")
    @ResponseBody
    public QuestionVo create(@RequestBody QuestionCreateDto createDto) {
        return questionsService.create(createDto);
    }

    /**
     * 修改题目接口
     * @param updateDto 修改题目参数
     * @return 修改题目结果
     */
    @PostMapping("update")
    @ResponseBody
    public QuestionVo update(@RequestBody QuestionUpdateDto updateDto) {
        return questionsService.update(updateDto);
    }

    /**
     * 获取题目详情接口
     * @param id 题目id
     * @return 题目详情
     */
    @GetMapping("detail")
    @ResponseBody
    public QuestionVo detail(@RequestParam Long id) {
        return questionsService.detail(id);
    }

    /**
     * 删除题目接口
     * @param id 删除题目参数
     */
    @PostMapping("delete")
    @ResponseBody
    public void delete(@RequestParam Long id) {
        questionsService.delete(id);
    }

    /**
     * 搜索接口
     * @param searchDto 搜索参数
     * @return 搜索结果
     */
    @GetMapping("search")
    @ResponseBody
    public QuestionSearchVo search(QuestionSearchDto searchDto) {
        return questionsService.search(searchDto);
    }

    /**
     * 验证答案
     */
    @PostMapping("validate")
    @ResponseBody
    public boolean validate(@RequestBody QuestionValidationDto validationDto) {
        return questionsService.validate(validationDto);
    }
}
