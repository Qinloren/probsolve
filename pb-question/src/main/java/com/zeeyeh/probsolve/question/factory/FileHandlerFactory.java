package com.zeeyeh.probsolve.question.factory;

import com.zeeyeh.probsolve.common.exceptions.ResponseCode;
import com.zeeyeh.probsolve.common.exceptions.ServiceException;
import com.zeeyeh.probsolve.question.QuestionLibHandler;
import com.zeeyeh.probsolve.question.imports.task.api.QuestionImportTaskApi;
import com.zeeyeh.probsolve.question.imports.task.api.model.enums.QuestionImportTaskStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 文件处理器工厂
 *
 * @author Qinloren
 */
@Slf4j
@Component
public class FileHandlerFactory {
    private final QuestionImportTaskApi questionImportTaskApi;

    @Value("${app.upload.path}")
    private String uploadPath;
    private final Map<String, QuestionLibHandler> handlers;

    public FileHandlerFactory(Set<QuestionLibHandler> handlers, QuestionImportTaskApi questionImportTaskApi) {
        this.questionImportTaskApi = questionImportTaskApi;
        this.handlers = new ConcurrentHashMap<>();
        for (QuestionLibHandler handler : handlers) {
            log.info("已注册题库处理器: {}", handler.getType());
            this.handlers.put(handler.getType(), handler);
        }
        log.info("题库处理器已注册完成， 共计{} 个", handlers.size());
    }

    /**
     * 导入题库
     * @param taskId 任务 ID
     * @param file 文件
     * @param type 题库类型
     * @param userId 用户 ID
     */
    @Async("questionImportExecutor")
    public void importQuestions(String taskId, File file, String type, Long userId) {
        log.info("开始导入题库， taskId={}，threadName={}", taskId, Thread.currentThread().getName());
        try (InputStream inputStream = new FileInputStream(file)) {
            questionImportTaskApi.createTask(taskId, userId);
            questionImportTaskApi.updateStatus(taskId, QuestionImportTaskStatus.RUNNING);
            QuestionLibHandler handler = this.getHandler(type);
            handler.handler(inputStream, taskId, userId);
            questionImportTaskApi.finish(taskId);
        } catch (Exception e) {
            questionImportTaskApi.error(taskId, e.getMessage());
            log.error("导入题库失败， taskId={}", taskId, e);
        }
    }

    /**
     * 获取题库处理器
     * @param type 题库类型
     * @return 题库处理器
     */
    public QuestionLibHandler getHandler(String type) {
        QuestionLibHandler handler = this.handlers.get(type);
        if (handler == null) {
            throw new ServiceException(ResponseCode.PARAM_ERROR, "题库类型不支持");
        }
        return handler;
    }

    /**
     * 获取上传文件夹
     * @return 上传文件夹
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public File getUploadFolder() {
        File folderFile = new File(System.getProperty("user.dir"), uploadPath);
        if (!uploadPath.startsWith("./") && !uploadPath.startsWith("/")) {
            folderFile = new File(uploadPath);
        }
        if (folderFile.exists()) {
            folderFile.mkdirs();
        }
        return folderFile;
    }

    /**
     * 获取题库任务文件夹
     * @return 题库任务文件夹
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public File getQuestionTaskFolder() {
        File questionTaskFolder = new File(getUploadFolder(), "question/import/task");
        if (!questionTaskFolder.exists()) {
            questionTaskFolder.mkdirs();
        }
        return questionTaskFolder;
    }

    /**
     * 是否支持该题库类型
      * @param type 题库类型
     * @return 是否支持该题库类型
     */
    public boolean supports(String type) {
        return this.handlers.containsKey(type);
    }

    /**
     * 获取所有支持的文件类型
     * @return 文件类型
     */
    public Set<String> getSupportedTypes() {
        return this.handlers.keySet();
    }
}
