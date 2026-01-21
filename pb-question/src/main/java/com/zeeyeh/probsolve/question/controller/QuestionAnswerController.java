package com.zeeyeh.probsolve.question.controller;

import com.zeeyeh.probsolve.question.api.model.dto.QuestionAnswerCreateDto;
import com.zeeyeh.probsolve.question.api.model.dto.QuestionAnswerSearchDto;
import com.zeeyeh.probsolve.question.api.model.dto.QuestionAnswerUpdateDto;
import com.zeeyeh.probsolve.question.api.model.vo.QuestionAnswerSearchVo;
import com.zeeyeh.probsolve.question.api.model.vo.QuestionAnswerVo;
import com.zeeyeh.probsolve.question.service.QuestionAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 答案管理控制器
 *
 * @author Qinloren
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("sys/question/answer")
public class QuestionAnswerController {

    private final QuestionAnswerService questionAnswersService;

    /**
     * 创建答案
     * @param createDto 创建参数
     * @return 创建后的答案
     */
    @PostMapping("create")
    @ResponseBody
    public QuestionAnswerVo create(@RequestBody QuestionAnswerCreateDto createDto) {
        return questionAnswersService.create(createDto);
    }

    /**
     * 修改答案
     * @param updateDto 修改参数
     * @return 修改后的答案
     */
    @PostMapping("update")
    @ResponseBody
    public QuestionAnswerVo update(@RequestBody QuestionAnswerUpdateDto updateDto) {
        return questionAnswersService.update(updateDto);
    }

    /**
     * 查询答案详情
     * @param id 答案 Id
     * @return 答案详情
     */
    @GetMapping("detail")
    @ResponseBody
    public QuestionAnswerVo detail(@RequestParam Long id) {
        return questionAnswersService.detail(id);
    }

    /**
     * 删除答案
     * @param id 答案 Id
     */
    @PostMapping("delete")
    @ResponseBody
    public void delete(@RequestParam Long id) {
        questionAnswersService.delete(id);
    }

    /**
     * 查询答案
     * @param searchDto 查询参数
     * @return 查询结果
     */
    @GetMapping("search")
    @ResponseBody
    public QuestionAnswerSearchVo search(QuestionAnswerSearchDto searchDto) {
        return questionAnswersService.search(searchDto);
    }
}
