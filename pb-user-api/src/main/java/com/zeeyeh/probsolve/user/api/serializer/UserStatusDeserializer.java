package com.zeeyeh.probsolve.user.api.serializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.zeeyeh.probsolve.user.api.model.enums.UserStatus;

import java.io.IOException;

/**
 * UserStatus 反序列化器
 *
 * @author Qinloren
 */
public class UserStatusDeserializer extends JsonDeserializer<UserStatus> {
    @Override
    public UserStatus deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        int intValue = jsonParser.getIntValue();
        return UserStatus.of(intValue);
    }
}
