package com.zeeyeh.probsolve.question.api.serializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.zeeyeh.probsolve.question.api.model.enums.QuestionStatus;

import java.io.IOException;

/**
 * QuestionStatus 反序列化器
 *
 * @author Qinloren
 */
public class QuestionStatusDeserializer extends JsonDeserializer<QuestionStatus> {

    @Override
    public QuestionStatus deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        int intValue = jsonParser.getIntValue();
        return QuestionStatus.of(intValue);
    }
}
