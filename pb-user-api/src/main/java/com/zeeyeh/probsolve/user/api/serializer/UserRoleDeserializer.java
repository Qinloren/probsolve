package com.zeeyeh.probsolve.user.api.serializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.zeeyeh.probsolve.user.api.model.enums.UserRole;

import java.io.IOException;

/**
 * UserRole 反序列化器
 *
 * @author Qinloren
 */
public class UserRoleDeserializer extends JsonDeserializer<UserRole> {
    @Override
    public UserRole deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        int intValue = jsonParser.getIntValue();
        return UserRole.of(intValue);
    }
}
