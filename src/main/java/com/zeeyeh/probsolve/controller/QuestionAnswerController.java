package com.zeeyeh.probsolve.controller;

import com.zeeyeh.probsolve.dto.question.answer.QuestionAnswerCreateDto;
import com.zeeyeh.probsolve.dto.question.answer.QuestionAnswerSearchDto;
import com.zeeyeh.probsolve.dto.question.answer.QuestionAnswerUpdateDto;
import com.zeeyeh.probsolve.service.QuestionAnswersService;
import com.zeeyeh.probsolve.vo.basic.QuestionAnswerVo;
import com.zeeyeh.probsolve.vo.search.QuestionAnswerSearchVo;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("sys/question/answer")
public class QuestionAnswerController {

    private final QuestionAnswersService questionAnswersService;

    public QuestionAnswerController(QuestionAnswersService questionAnswersService) {
        this.questionAnswersService = questionAnswersService;
    }

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
     * @param id 答案Id
     * @return 答案详情
     */
    @GetMapping("detail")
    @ResponseBody
    public QuestionAnswerVo detail(@RequestParam Long id) {
        return questionAnswersService.detail(id);
    }

    /**
     * 删除答案
     * @param id 答案Id
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
