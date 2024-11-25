package api.Serializers;


import Database.Comment;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.Set;

public class CommentSerializer extends StdSerializer<Comment>
{
    public CommentSerializer()
    {
        this(null);
    }

    public CommentSerializer(Class<Comment> t)
    {
        super(t);
    }

    @Override
    public void serialize(Comment comment, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException
    {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", comment.getId());
        jsonGenerator.writeStringField("content", comment.getContent());
        jsonGenerator.writeStringField("authorComment", comment.getAuthorComment().getUsername());
        jsonGenerator.writeNumberField("likes", comment.getLikingAccounts().size());

        Set<Long> subComments = comment.getChildComments().stream()
                .map(Comment::getId)
                .collect(java.util.stream.Collectors.toSet());
        jsonGenerator.writeObjectField("subComments", subComments);

        jsonGenerator.writeEndObject();
    }
}
