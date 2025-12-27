package com.zeeyeh.probsolve.controller;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.mybatisflex.core.query.QueryWrapper;
import com.zeeyeh.probsolve.dto.question.QuestionLibDeriveDto;
import com.zeeyeh.probsolve.dto.upload.UploadQuestionImportDto;
import com.zeeyeh.probsolve.entity.R;
import com.zeeyeh.probsolve.entity.data.QuestionAnswers;
import com.zeeyeh.probsolve.entity.data.QuestionImportTask;
import com.zeeyeh.probsolve.exceptions.GlobalError;
import com.zeeyeh.probsolve.exceptions.ServiceException;
import com.zeeyeh.probsolve.provider.TokenProvider;
import com.zeeyeh.probsolve.questions.FileHandlerFactory;
import com.zeeyeh.probsolve.questions.QuestionLibFileHandler;
import com.zeeyeh.probsolve.service.QuestionAnswersService;
import com.zeeyeh.probsolve.service.QuestionCategoriesService;
import com.zeeyeh.probsolve.service.QuestionCategoryRelationService;
import com.zeeyeh.probsolve.service.QuestionImportTaskService;
import com.zeeyeh.probsolve.vo.basic.QuestionCategoryVo;
import com.zeeyeh.probsolve.vo.basic.QuestionImportTaskStatusVo;
import com.zeeyeh.probsolve.vo.basic.QuestionVo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("sys/attachment/upload")
public class UploadController {

    private final FileHandlerFactory fileHandlerFactory;
    private final TokenProvider tokenProvider;
    private final QuestionCategoriesService questionCategoriesService;
    private final QuestionCategoryRelationService questionCategoryRelationService;
    private final QuestionAnswersService questionAnswersService;
    private final QuestionImportTaskService questionImportTaskService;

    public UploadController(FileHandlerFactory fileHandlerFactory, TokenProvider tokenProvider, QuestionCategoriesService questionCategoriesService, QuestionCategoryRelationService questionCategoryRelationService, QuestionAnswersService questionAnswersService, QuestionImportTaskService questionImportTaskService) {
        this.fileHandlerFactory = fileHandlerFactory;
        this.tokenProvider = tokenProvider;
        this.questionCategoriesService = questionCategoriesService;
        this.questionCategoryRelationService = questionCategoryRelationService;
        this.questionAnswersService = questionAnswersService;
        this.questionImportTaskService = questionImportTaskService;
    }

    @GetMapping("question/import/status/{taskId}")
    @ResponseBody
    public Integer getStatus(@PathVariable String taskId) {
        QuestionImportTask task = questionImportTaskService.getByTaskId(taskId);
        return task.getStatus();
    }

    @GetMapping("question/import/status")
    @ResponseBody
    public List<QuestionImportTaskStatusVo> getStatusBatch(@RequestParam List<String> ids) {
        return questionImportTaskService.getStatusBatch(ids);
    }

    @PostMapping("question")
    @ResponseBody
    public R<String> uploadQuestion(UploadQuestionImportDto importDto, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        Long userId = tokenProvider.getClaim(token, "id").asLong();
        if (!fileHandlerFactory.supports(importDto.getType())) {
            // 不支持的文件格式类型
            throw new ServiceException(GlobalError.FORMAT_HANDLER_NOT_FOUND);
        }
        File questionTaskFolder = fileHandlerFactory.getQuestionTaskFolder();
        MultipartFile file = importDto.getFile();
        String filename = file.getOriginalFilename();
        String extName = ".pb";
        if (filename != null) {
            extName = filename.substring(filename.lastIndexOf("."));
        }
        String taskId = UUID.randomUUID().toString().replace("-", "");
        File targetFile = new File(questionTaskFolder, taskId + extName);
        try {
            importDto.getFile().transferTo(targetFile);
        } catch (IOException e) {
            throw new ServiceException(GlobalError.QUESTION_UPLOAD_ERROR);
        }
        fileHandlerFactory.importQuestions(
                taskId,
                targetFile,
                importDto.getType(),
                userId);
        return R.success(taskId);
    }


    /**
     * 导出题库
     * @param deriveDto 导出参数
     */
    @PostMapping("question/derive")
    public void derive(@RequestBody QuestionLibDeriveDto deriveDto, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        QuestionCategoryVo questionCategoryVo = questionCategoriesService.detail(deriveDto.getId());
        jsonObject.put("lib_name", questionCategoryVo.getName());
        jsonObject.put("size", questionCategoryVo.getSize());
        jsonObject.put("signature", questionCategoryVo.getSignature());
        List<QuestionVo> questionVoList = questionCategoryRelationService.findByCategoryId(deriveDto.getId());
        JSONArray questionArray = new JSONArray();
        int errorCount = 0;
        int successCount = 0;
        JSONArray errorArray = new JSONArray();
        for (QuestionVo questionVo : questionVoList) {
            QueryWrapper queryWrapper = QueryWrapper.create().eq(QuestionAnswers::getQuestionId, questionVo.getId());
            if (!questionAnswersService.exists(queryWrapper)) {
                errorCount++;
                errorArray.add(questionVo.getId());
                continue;
            }
            QuestionAnswers answers = questionAnswersService.getOne(queryWrapper);
            JSONObject questionObject = new JSONObject();
            questionObject.put("tem", questionVo.getContent());
            questionObject.put("type", questionVo.getType());
            questionObject.put("difficulty", questionVo.getDifficulty());
            questionObject.put("score", questionVo.getScore());
            questionObject.put("analysis", questionVo.getAnalysis());
            switch (questionVo.getType()) {
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
            throw new ServiceException(GlobalError.QUESTION_DERIVE_FAILED);
        }
    }
}
