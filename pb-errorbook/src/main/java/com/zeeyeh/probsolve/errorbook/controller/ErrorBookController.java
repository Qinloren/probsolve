package com.zeeyeh.probsolve.errorbook.controller;

import com.zeeyeh.probsolve.common.annotations.ResponseWrapper;
import com.zeeyeh.probsolve.errorbook.service.ErrorBookService;
import com.zeeyeh.probsolve.model.dto.ErrorBookCreateDto;
import com.zeeyeh.probsolve.model.dto.ErrorBookSearchDto;
import com.zeeyeh.probsolve.model.dto.ErrorBookUpdateDto;
import com.zeeyeh.probsolve.model.vo.ErrorBookSearchVo;
import com.zeeyeh.probsolve.model.vo.ErrorBookVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 错误本接口
 *
 * @author Qinloren
 */
@RestController
@RequestMapping("sys/errorbook")
@ResponseWrapper
@RequiredArgsConstructor
public class ErrorBookController {

    private final ErrorBookService errorBookService;

    /**
     * 添加错题到错题本
     * @param createDto 创建参数
     * @return 错题本详情
     */
    @PostMapping("create")
    @ResponseBody
    public ErrorBookVo create(@RequestBody ErrorBookCreateDto createDto) {
        return errorBookService.create(createDto);
    }

    /**
     * 更新错题本
     * @param updateDto 更新参数
     * @return 错题本详情
     */
    @PostMapping("update")
    @ResponseBody
    public ErrorBookVo update(@RequestBody ErrorBookUpdateDto updateDto) {
        return errorBookService.update(updateDto);
    }

    /**
     * 获取错题本详情
     * @param id 错题本id
     * @return 错题本详情
     */
    @GetMapping("detail")
    @ResponseBody
    public ErrorBookVo detail(@RequestParam Long id) {
        return errorBookService.detail(id);
    }

    /**
     * 删除错题本
     * @param id 错题本 id
     */
    @PostMapping("delete")
    public void delete(@RequestParam Long id) {
        errorBookService.delete(id);
    }

    /**
     * 搜索错题本
     * @param searchDto 搜索参数
     * @return 错题本列表
     */
    @PostMapping("search")
    @ResponseBody
    public ErrorBookSearchVo search(@RequestBody ErrorBookSearchDto searchDto) {
        return errorBookService.search(searchDto);
    }
}
