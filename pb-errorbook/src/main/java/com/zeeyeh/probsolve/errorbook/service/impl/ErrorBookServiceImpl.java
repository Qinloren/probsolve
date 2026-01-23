package com.zeeyeh.probsolve.errorbook.service.impl;

import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.update.UpdateChain;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.zeeyeh.probsolve.common.exceptions.ResponseCode;
import com.zeeyeh.probsolve.common.exceptions.ServiceException;
import com.zeeyeh.probsolve.errorbook.mapper.ErrorBookMapper;
import com.zeeyeh.probsolve.errorbook.service.ErrorBookService;
import com.zeeyeh.probsolve.model.dto.ErrorBookCreateDto;
import com.zeeyeh.probsolve.model.dto.ErrorBookSearchDto;
import com.zeeyeh.probsolve.model.dto.ErrorBookUpdateDto;
import com.zeeyeh.probsolve.model.entity.ErrorBook;
import com.zeeyeh.probsolve.model.vo.ErrorBookSearchVo;
import com.zeeyeh.probsolve.model.vo.ErrorBookVo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ErrorBookServiceImpl extends ServiceImpl<ErrorBookMapper, ErrorBook> implements ErrorBookService {

    @Override
    public ErrorBookVo create(ErrorBookCreateDto createDto) {
        ErrorBook errorBook = new ErrorBook();
        errorBook.setUserId(createDto.getUserId());
        errorBook.setQuestionId(createDto.getQuestionId());
        errorBook.setReason(createDto.getReason());
        errorBook.setCreateTime(LocalDateTime.now());
        if (!this.save(errorBook)) {
            throw new ServiceException(ResponseCode.BUSINESS_ERROR, "添加错题失败");
        }
        return ErrorBookVo.of(errorBook);
    }

    @Override
    public ErrorBookVo update(ErrorBookUpdateDto updateDto) {
        QueryWrapper queryWrapper = QueryWrapper.create()
                .eq(ErrorBook::getId, updateDto.getId());
        if (!this.exists(queryWrapper)) {
            throw new ServiceException(ResponseCode.PARAM_ERROR, "错题不存在");
        }
        UpdateChain<ErrorBook> updateChain = this.updateChain();
        Optional.ofNullable(updateDto.getReason())
                .ifPresent(reason -> updateChain.set(ErrorBook::getReason, reason));
        if (!updateChain.eq(ErrorBook::getId, updateDto.getId()).update()) {
            throw new ServiceException(ResponseCode.BUSINESS_ERROR, "修改错题失败");
        }
        ErrorBook errorBook = this.getOne(queryWrapper);
        return ErrorBookVo.of(errorBook);
    }

    @Override
    public ErrorBookVo detail(Long id) {
        QueryWrapper queryWrapper = QueryWrapper.create()
                .eq(ErrorBook::getId, id);
        if (!this.exists(queryWrapper)) {
            throw new ServiceException(ResponseCode.PARAM_ERROR, "错题不存在");
        }
        ErrorBook errorBook = this.getOne(queryWrapper);
        return ErrorBookVo.of(errorBook);
    }

    @Override
    public ErrorBookSearchVo search(ErrorBookSearchDto searchDto) {
        if (!this.exists(QueryWrapper.create()
                .eq(ErrorBook::getId, searchDto.getId()))) {
            throw new ServiceException(ResponseCode.PARAM_ERROR, "错题不存在");
        }
        QueryWrapper queryWrapper = QueryWrapper.create();
        Optional.ofNullable(searchDto.getId())
                .ifPresent(id -> queryWrapper.eq(ErrorBook::getId, id));
        Optional.ofNullable(searchDto.getUserId())
                .ifPresent(userId -> queryWrapper.eq(ErrorBook::getUserId, userId));
        Optional.ofNullable(searchDto.getQuestionId())
                .ifPresent(questionId -> queryWrapper.eq(ErrorBook::getQuestionId, questionId));
        Optional.ofNullable(searchDto.getReason())
                .ifPresent(reason -> queryWrapper.like(ErrorBook::getReason, reason));
        Page<ErrorBook> page = new Page<>(searchDto.getPage(), searchDto.getPageSize());
        Page<ErrorBook> errorBookPage = this.page(page, queryWrapper);
        List<ErrorBookVo> list = errorBookPage.getRecords()
                .stream()
                .map(ErrorBookVo::of)
                .toList();
        return new ErrorBookSearchVo(
                list,
                errorBookPage.getTotalPage(),
                errorBookPage.getPageNumber(),
                errorBookPage.getPageSize()
        );
    }

    @Override
    public void delete(Long id) {
        QueryWrapper queryWrapper = QueryWrapper.create()
                .eq(ErrorBook::getId, id);
        if (!this.exists(queryWrapper)) {
            throw new ServiceException(ResponseCode.PARAM_ERROR, "错题不存在");
        }
        int deleted = this.mapper.deleteById(id);
        if (deleted == 0) {
            throw new ServiceException(ResponseCode.BUSINESS_ERROR, "删除错题失败");
        }
    }
}
