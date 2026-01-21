package com.zeeyeh.probsolve.question.api.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.zeeyeh.probsolve.question.api.model.enums.QuestionCategoryStatus;

import java.io.IOException;

/**
 * QuestionCategoryStatus 序列化器
 *
 * @author Qinloren
 */
public class QuestionCategoryStatusSerializer extends JsonSerializer<QuestionCategoryStatus> {
    @Override
    public void serialize(QuestionCategoryStatus questionCategoryStatus, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (questionCategoryStatus == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeNumber(questionCategoryStatus.getCode());
    }
}
