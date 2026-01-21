package com.zeeyeh.probsolve.question.api.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.zeeyeh.probsolve.question.api.model.enums.QuestionStatus;

import java.io.IOException;

/**
 * QuestionStatus 序列化器
 *
 * @author Qinloren
 */
public class QuestionStatusSerializer extends JsonSerializer<QuestionStatus> {
    @Override
    public void serialize(QuestionStatus questionStatus, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (questionStatus == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeNumber(questionStatus.getCode());
    }
}
