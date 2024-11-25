package api.Serializers;

import Database.Notification;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class NotificationSerializer extends StdSerializer<Notification> {
    public NotificationSerializer() {
        this(null);
    }
    public NotificationSerializer(Class<Notification> t) {
            super(t);
        }

    @Override
    public void serialize(Notification notification, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", notification.getId());
        jsonGenerator.writeStringField("recipient", notification.getRecipient().getUsername());
        jsonGenerator.writeStringField("message", notification.getMessage());
        jsonGenerator.writeBooleanField("read", notification.isIsRead());
        jsonGenerator.writeStringField("relatedUrl", notification.getRelatedUrl());
        jsonGenerator.writeEndObject();
    }
}
