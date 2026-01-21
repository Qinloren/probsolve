package com.zeeyeh.probsolve.question.api.serializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.zeeyeh.probsolve.question.api.model.enums.QuestionType;

import java.io.IOException;

/**
 * QuestionType 反序列化器
 *
 * @author Qinloren
 */
public class QuestionTypeDeserializer extends JsonDeserializer<QuestionType> {

    @Override
    public QuestionType deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        int intValue = jsonParser.getIntValue();
        return QuestionType.of(intValue);
    }
}
