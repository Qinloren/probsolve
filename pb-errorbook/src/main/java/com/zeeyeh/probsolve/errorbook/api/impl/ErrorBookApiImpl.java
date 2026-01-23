package com.zeeyeh.probsolve.errorbook.api.impl;

import com.zeeyeh.probsolve.ErrorBookApi;
import com.zeeyeh.probsolve.errorbook.service.ErrorBookService;
import com.zeeyeh.probsolve.model.dto.ErrorBookCreateDto;
import com.zeeyeh.probsolve.model.dto.ErrorBookSearchDto;
import com.zeeyeh.probsolve.model.dto.ErrorBookUpdateDto;
import com.zeeyeh.probsolve.model.vo.ErrorBookSearchVo;
import com.zeeyeh.probsolve.model.vo.ErrorBookVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ErrorBookApiImpl implements ErrorBookApi {

    private final ErrorBookService errorBookService;

    @Override
    public ErrorBookVo create(ErrorBookCreateDto createDto) {
        return errorBookService.create(createDto);
    }

    @Override
    public ErrorBookVo update(ErrorBookUpdateDto updateDto) {
        return errorBookService.update(updateDto);
    }

    @Override
    public ErrorBookVo detail(Long id) {
        return errorBookService.detail(id);
    }

    @Override
    public ErrorBookSearchVo search(ErrorBookSearchDto searchDto) {
        return errorBookService.search(searchDto);
    }

    @Override
    public void delete(Long id) {
        errorBookService.delete(id);
    }
}
