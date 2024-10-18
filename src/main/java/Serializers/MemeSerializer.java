package Serializers;

import Database.Account;
import Database.Meme;

import Database.Tag;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

public class MemeSerializer extends StdSerializer<Meme> {

    public MemeSerializer() {
        this(null);
    }
    public MemeSerializer(Class<Meme> t) {
        super(t);
    }

    @Override
    public void serialize(Meme meme, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", meme.getId());
        jsonGenerator.writeStringField("title", meme.getTitle());
        jsonGenerator.writeStringField("url", meme.getUrl());

        jsonGenerator.writeStringField("author", meme.getAuthorMeme().getUsername());

        jsonGenerator.writeNumberField("likes", meme.getLikingAccounts().size());

        Set<String> tags = meme.getTags().stream()
                .map(Tag::getName)
                .collect(Collectors.toSet());
        jsonGenerator.writeObjectField("tags", tags);


        jsonGenerator.writeEndObject();
    }

}
