package com.zeeyeh.probsolve.question.api.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.zeeyeh.probsolve.question.api.model.enums.QuestionType;

import java.io.IOException;

/**
 * QuestionType 序列化器
 *
 * @author Qinloren
 */
public class QuestionTypeSerializer extends JsonSerializer<QuestionType> {
    @Override
    public void serialize(QuestionType questionType, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (questionType == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeNumber(questionType.getCode());
    }
}
