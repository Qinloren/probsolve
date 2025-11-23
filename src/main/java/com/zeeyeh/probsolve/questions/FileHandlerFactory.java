package com.zeeyeh.probsolve.questions;

import com.zeeyeh.probsolve.exceptions.GlobalError;
import com.zeeyeh.probsolve.exceptions.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 文件处理器工厂
 */
@Component
public class FileHandlerFactory {

    @Value("${app.upload.path}")
    private String uploadPath;

    private static final Logger log = LoggerFactory.getLogger(FileHandlerFactory.class);
    private final Map<String, QuestionLibFileHandler> handlers;

    public FileHandlerFactory(Set<QuestionLibFileHandler> handlers) {
        this.handlers = new ConcurrentHashMap<>();
        for (QuestionLibFileHandler handler : handlers) {
            log.info("已注册文件处理器: {}", handler.getType());
            this.handlers.put(handler.getType(), handler);
        }
        log.info("文件处理器已注册完成，共计 {} 个", handlers.size());
    }

    /**
     * 获取文件处理器
     * @param type 文件类型
     * @return 文件处理器
     */
    public QuestionLibFileHandler getHandler(String type) {
        QuestionLibFileHandler fileHandler = handlers.get(type);
        if (fileHandler == null) {
            throw new ServiceException(GlobalError.FORMAT_HANDLER_NOT_FOUND);
        }
        return fileHandler;
    }

    /**
     * 获取上传文件夹
     * @return 文件夹
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public File getUploadFolder() {
        File folderFile = new File(System.getProperty("user.dir"), uploadPath);
        if (!uploadPath.startsWith("./") && !uploadPath.startsWith("/"))
            folderFile = new File(uploadPath);
        if (!folderFile.exists()) {
            folderFile.mkdirs();
        }
        return folderFile;
    }

    /**
     * 获取题目导入任务文件夹
     * @return 文件夹
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
     * 判断是否支持该文件类型
     * @param type 文件类型
     * @return 是否支持
     */
    public boolean supports(String type) {
        return handlers.containsKey(type);
    }

    /**
     * 获取所有支持的文件类型
     * @return 文件类型
     */
    public Set<String> getSupportedTypes() {
        return handlers.keySet();
    }
}
