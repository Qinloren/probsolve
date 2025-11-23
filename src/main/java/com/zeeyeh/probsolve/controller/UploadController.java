package com.zeeyeh.probsolve.controller;

import com.zeeyeh.probsolve.dto.upload.UploadQuestionImportDto;
import com.zeeyeh.probsolve.exceptions.GlobalError;
import com.zeeyeh.probsolve.exceptions.ServiceException;
import com.zeeyeh.probsolve.provider.TokenProvider;
import com.zeeyeh.probsolve.questions.FileHandlerFactory;
import com.zeeyeh.probsolve.questions.QuestionLibFileHandler;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

@RestController
@RequestMapping("sys/attachment/upload")
public class UploadController {

    private final FileHandlerFactory fileHandlerFactory;
    private final TokenProvider tokenProvider;

    public UploadController(FileHandlerFactory fileHandlerFactory, TokenProvider tokenProvider) {
        this.fileHandlerFactory = fileHandlerFactory;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("question")
    @ResponseBody
    private void uploadQuestion(UploadQuestionImportDto importDto, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        Long userId = tokenProvider.getClaim(token, "id").asLong();
        if (!fileHandlerFactory.supports(importDto.getType())) {
            // 不支持的文件格式类型
            throw new ServiceException(GlobalError.FORMAT_HANDLER_NOT_FOUND);
        }
        File questionTaskFolder = fileHandlerFactory.getQuestionTaskFolder();
        String filename = importDto.getFile().getOriginalFilename();
        String targetFilename = UUID.randomUUID().toString().replace("-", "");
        String extName = ".pb";
        if (filename != null) {
            extName = filename.substring(filename.lastIndexOf("."));
        }
        targetFilename += extName;
        File targetFile = new File(questionTaskFolder, targetFilename);
        try {
            importDto.getFile().transferTo(targetFile);
        } catch (IOException e) {
            throw new ServiceException(GlobalError.QUESTION_UPLOAD_ERROR);
        }
        QuestionLibFileHandler handler = fileHandlerFactory.getHandler(importDto.getType());
        try {
            byte[] bytes = Files.readAllBytes(targetFile.toPath());
            handler.handler(bytes, userId);
        } catch (IOException e) {
            throw new ServiceException(GlobalError.QUESTION_IMPORT_FAILED);
        }
    }
}
