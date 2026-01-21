package com.zeeyeh.probsolve.question.imports.task.api.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.zeeyeh.probsolve.question.imports.task.api.model.enums.QuestionImportTaskStatus;

import java.io.IOException;

/**
 * QuestionImportTaskStatus 序列化器
 *
 * @author Qinloren
 */
public class QuestionImportTaskStatusSerializer extends JsonSerializer<QuestionImportTaskStatus> {
    @Override
    public void serialize(QuestionImportTaskStatus questionImportTaskStatus, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (questionImportTaskStatus == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeNumber(questionImportTaskStatus.getValue());
    }
}
