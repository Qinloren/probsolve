package com.zeeyeh.probsolve.user.api.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.zeeyeh.probsolve.user.api.model.enums.UserStatus;

import java.io.IOException;

/**
 * UserStatus 序列化器
 *
 * @author Qinloren
 */
public class UserStatusSerializer extends JsonSerializer<UserStatus> {

    @Override
    public void serialize(UserStatus userStatus, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (userStatus == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeNumber(userStatus.getCode());
    }
}
