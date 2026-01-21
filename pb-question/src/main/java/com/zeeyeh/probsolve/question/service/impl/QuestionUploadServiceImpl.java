package com.zeeyeh.probsolve.question.service.impl;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.mybatisflex.core.query.QueryWrapper;
import com.zeeyeh.probsolve.common.exceptions.ResponseCode;
import com.zeeyeh.probsolve.common.exceptions.ServiceException;
import com.zeeyeh.probsolve.common.provider.TokenProvider;
import com.zeeyeh.probsolve.question.api.model.dto.QuestionLibDeriveDto;
import com.zeeyeh.probsolve.question.api.model.dto.UploadQuestionImportDto;
import com.zeeyeh.probsolve.question.api.model.entity.QuestionAnswer;
import com.zeeyeh.probsolve.question.api.model.vo.QuestionAnswerVo;
import com.zeeyeh.probsolve.question.api.model.vo.QuestionCategoryVo;
import com.zeeyeh.probsolve.question.api.model.vo.QuestionVo;
import com.zeeyeh.probsolve.question.factory.FileHandlerFactory;
import com.zeeyeh.probsolve.question.service.QuestionAnswerService;
import com.zeeyeh.probsolve.question.service.QuestionCategoryRelationService;
import com.zeeyeh.probsolve.question.service.QuestionCategoryService;
import com.zeeyeh.probsolve.question.service.QuestionUploadService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

/**
 * QuestionUploadService 实现类
 *
 * @author Qinloren
 */
@Service
@RequiredArgsConstructor
public class QuestionUploadServiceImpl implements QuestionUploadService {
    private final FileHandlerFactory fileHandlerFactory;
    private final TokenProvider tokenProvider;
    private final QuestionCategoryService questionCategoryService;
    private final QuestionAnswerService questionAnswerService;
    private final QuestionCategoryRelationService questionCategoryRelationService;

    @Override
    public String upload(UploadQuestionImportDto importDto, HttpServletRequest request) {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        token = token.startsWith("Bearer ") ? token.substring(7).trim() : token;
        Long userId = tokenProvider.getClaim(token, "id").asLong();
        if (!fileHandlerFactory.supports(importDto.getType())) {
            throw new ServiceException(ResponseCode.PARAM_ERROR, "不支持的文件类型");
        }
        File questionTaskFolder = fileHandlerFactory.getQuestionTaskFolder();
        MultipartFile file = importDto.getFile();
        String originalFilename = file.getOriginalFilename();
        String extName = ".pb";
        if (originalFilename != null) {
            extName = originalFilename.substring(originalFilename.lastIndexOf("."));
        }
        String taskId = UUID.randomUUID().toString().replace("-", "");
        File targetFile = new File(questionTaskFolder, taskId + extName);
        try {
            importDto.getFile().transferTo(targetFile);
        } catch (IOException e) {
            throw new ServiceException(ResponseCode.BUSINESS_ERROR, "文件上传失败");
        }
        fileHandlerFactory.importQuestions(
                taskId,
                targetFile,
                importDto.getType(),
                userId
        );
        return taskId;
    }

    @Override
    public void derive(QuestionLibDeriveDto deriveDto, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        QuestionCategoryVo questionCategoryVo = questionCategoryService.detail(deriveDto.getId());
        jsonObject.put("lib_name", questionCategoryVo.getName());
        jsonObject.put("size", questionCategoryVo.getSize());
        jsonObject.put("signature", questionCategoryVo.getSignature());
        List<QuestionVo> questionVoList = questionCategoryRelationService.findByCategoryId(deriveDto.getId());
        JSONArray questionArray = new JSONArray();
        int errorCount = 0;
        int successCount = 0;
        JSONArray errorArray = new JSONArray();
        for (QuestionVo questionVo : questionVoList) {
            QueryWrapper queryWrapper = QueryWrapper.create().eq(QuestionAnswer::getQuestionId, questionVo.getId());
            if (!questionAnswerService.exists(queryWrapper)) {
                errorCount++;
                errorArray.add(questionVo.getId());
                continue;
            }
            QuestionAnswerVo answers = questionAnswerService.detailByQuestionId(questionVo.getId());
            JSONObject questionObject = new JSONObject();
            questionObject.put("tem", questionVo.getContent());
            questionObject.put("type", questionVo.getType());
            questionObject.put("difficulty", questionVo.getDifficulty());
            questionObject.put("score", questionVo.getScore());
            questionObject.put("analysis", questionVo.getAnalysis());
            switch (questionVo.getType().getCode()) {
                case 1:
                    JSONArray singleValue;
                    try {
                        singleValue = JSONArray.parseArray(answers.getContent());
                    } catch (Exception e) {
                        errorCount++;
                        errorArray.add(questionVo.getId());
                        continue;
                    }
                    questionObject.put("options", singleValue);
                    questionObject.put("answers", answers.getAnswers());
                    break;
                case 2:
                    JSONArray multiValue;
                    try {
                        multiValue = JSONArray.parseArray(answers.getContent());
                    } catch (Exception e) {
                        errorCount++;
                        errorArray.add(questionVo.getId());
                        continue;
                    }
                    questionObject.put("options", multiValue);
                    JSONArray multiAnswer;
                    try {
                        multiAnswer = JSONArray.parseArray(answers.getAnswers());
                    } catch (Exception e) {
                        errorCount++;
                        errorArray.add(questionVo.getId());
                        continue;
                    }
                    questionObject.put("answers", multiAnswer);
                    break;
                case 3:
                    questionObject.put("answers", Integer.parseInt(answers.getAnswers()));
                    break;
                case 4:
                    JSONArray gapFillingAnswers;
                    try {
                        gapFillingAnswers = JSONArray.parseArray(answers.getAnswers());
                    } catch (Exception e) {
                        errorCount++;
                        errorArray.add(questionVo.getId());
                        continue;
                    }
                    questionObject.put("answers", gapFillingAnswers);
                    break;
                case 5:
                    questionObject.put("answers", answers.getAnswers());
                    break;
            }
            // questionObject.put("answers", questionVo.getContent());
            questionObject.put("tips", answers.getTips());
            questionArray.add(questionObject);
            successCount++;
        }
        jsonObject.put("questions", questionArray);
        jsonObject.put("errors", errorArray);
        jsonObject.put("errorCount", errorCount);
        jsonObject.put("successCount", successCount);
        response.setCharacterEncoding("UTF-8");
        response.setHeader(HttpHeaders.CONTENT_TYPE, "application/octet-stream");
        response.setContentType("application/octet-stream;charset=UTF-8");
        String encode = URLEncoder.encode("questionBy" + deriveDto.getId() + ".pb", StandardCharsets.UTF_8);
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment:filename=" + encode);
        try (OutputStream outputStream = response.getOutputStream()) {
            String jsonString = jsonObject.toJSONString();
            outputStream.write(jsonString.getBytes());
        } catch (Exception e) {
            throw new ServiceException(ResponseCode.BUSINESS_ERROR, "导出失败");
        }
    }
}
