package com.zeeyeh.probsolve.question.service;

import com.zeeyeh.probsolve.question.api.model.dto.QuestionLibDeriveDto;
import com.zeeyeh.probsolve.question.api.model.dto.UploadQuestionImportDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 题目上传服务
 *
 * @author Qinloren
 */
public interface QuestionUploadService {

    /**
     * 导入题目
     * @param importDto 导入参数
     * @param request 请求
     * @return 导入结果
     */
    String upload(UploadQuestionImportDto importDto, HttpServletRequest request);

    /**
     * 导出题目
     * @param deriveDto 导出参数
     * @param response 响应
     */
    void derive(@RequestBody QuestionLibDeriveDto deriveDto, HttpServletResponse response);
}
