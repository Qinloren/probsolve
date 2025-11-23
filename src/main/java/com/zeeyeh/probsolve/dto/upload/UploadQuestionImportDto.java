package com.zeeyeh.probsolve.dto.upload;

import org.springframework.web.multipart.MultipartFile;

/**
 * 上传导入题库参数
 */
public class UploadQuestionImportDto {
    /**
     * 解析格式
     */
    private String type;
    /**
     * 文件
     */
    private MultipartFile file;

    public UploadQuestionImportDto() {
    }

    public UploadQuestionImportDto(String type, MultipartFile file) {
        this.type = type;
        this.file = file;
    }

    public String getType() {
        return type;
    }

    public UploadQuestionImportDto setType(String type) {
        this.type = type;
        return this;
    }

    public MultipartFile getFile() {
        return file;
    }

    public UploadQuestionImportDto setFile(MultipartFile file) {
        this.file = file;
        return this;
    }
}
