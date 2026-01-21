package com.zeeyeh.probsolve.question.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

/**
 * 上传题目导入请求参数
 *
 * @author Qinloren
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UploadQuestionImportDto {
    /**
     * 解析格式
     */
    private String type;
    /**
     * 文件
     */
    private MultipartFile file;
}
