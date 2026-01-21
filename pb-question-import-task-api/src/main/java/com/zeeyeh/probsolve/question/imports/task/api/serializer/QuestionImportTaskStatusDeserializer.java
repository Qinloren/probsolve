package com.zeeyeh.probsolve.question.imports.task.api.serializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.zeeyeh.probsolve.question.imports.task.api.model.enums.QuestionImportTaskStatus;

import java.io.IOException;

/**
 * QuestionImportTaskStatus 反序列化器
 *
 * @author Qinloren
 */
public class QuestionImportTaskStatusDeserializer extends JsonDeserializer<QuestionImportTaskStatus> {

    @Override
    public QuestionImportTaskStatus deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        int intValue = jsonParser.getIntValue();
        return QuestionImportTaskStatus.of(intValue);
    }
}
