package com.zeeyeh.probsolve.user.api.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.zeeyeh.probsolve.user.api.model.enums.UserRole;

import java.io.IOException;

/**
 * UserRole 序列化器
 *
 * @author Qinloren
 */
public class UserRoleSerializer extends JsonSerializer<UserRole> {

    @Override
    public void serialize(UserRole userRole, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (userRole == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeNumber(userRole.getValue());
    }
}
