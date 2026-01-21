package com.zeeyeh.probsolve.question.api.serializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.zeeyeh.probsolve.question.api.model.enums.QuestionCategoryStatus;

import java.io.IOException;

/**
 * QuestionCategoryStatus 反序列化器
 *
 * @author Qinloren
 */
public class QuestionCategoryStatusDeserializer extends JsonDeserializer<QuestionCategoryStatus> {

    @Override
    public QuestionCategoryStatus deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        int intValue = jsonParser.getIntValue();
        return QuestionCategoryStatus.of(intValue);
    }
}
