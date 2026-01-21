package com.zeeyeh.probsolve.question.imports.task.api.model.enums;

import com.mybatisflex.annotation.EnumValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 题目导入任务状态
 *
 * @author Qinloren
 */
@Getter
@RequiredArgsConstructor
public enum QuestionImportTaskStatus {

    PENDING(0, "已上传"),
    RUNNING(0, "运行中"),
    SUCCESS(0, "导入成功"),
    PARTIAL_SUCCESS(0, "部分成功"),
    ERROR(0, "导入失败"),
    ;
    @EnumValue
    private final Integer value;
    private final String text;

    public static QuestionImportTaskStatus of(Integer value) {
        for (QuestionImportTaskStatus status : QuestionImportTaskStatus.values()) {
            if (status.getValue().equals(value)) {
                return status;
            }
        }
        return null;
    }
}
