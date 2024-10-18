package Serializers;

import Database.Account;
import Database.Comment;
import Database.Meme;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

public class AccountSerializer extends StdSerializer<Account> {

    public AccountSerializer() {
        this(null);
    }

    public AccountSerializer(Class<Account> t) {
        super(t);
    }

    @Override
    public void serialize(Account account, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", account.getId());
        jsonGenerator.writeStringField("username", account.getUsername());
        jsonGenerator.writeStringField("email", account.getEmail());
        jsonGenerator.writeNumberField("permissions", account.getPermissions());

        jsonGenerator.writeNumberField("likes", account.getLikingAccounts().size());
        // Serialize likedAccounts as a set of IDs
        Set<String> likedAccountUsernames = account.getLikedAccounts().stream()
                .map(Account::getUsername)
                .collect(Collectors.toSet());
        jsonGenerator.writeObjectField("likedAccounts", likedAccountUsernames);

        // Serialize likingAccounts as a set of IDs
        Set<String> likingAccountUsernames = account.getLikingAccounts().stream()
                .map(Account::getUsername)
                .collect(Collectors.toSet());
        jsonGenerator.writeObjectField("likingAccounts", likingAccountUsernames);

        Set<Long> authoredMemeIds = account.getAuthoredMemes().stream()
                .map(Meme::getId)
                .collect(Collectors.toSet());
        jsonGenerator.writeObjectField("authoredMemes", authoredMemeIds);

        Set<Long> likedMemeIds = account.getLikedMemes().stream()
                .map(Meme::getId)
                .collect(Collectors.toSet());
        jsonGenerator.writeObjectField("likedMemes", likedMemeIds);

        Set<Long> authoredCommentIds = account.getAuthoredComments().stream()
                .map(Comment::getId)
                .collect(Collectors.toSet());
        jsonGenerator.writeObjectField("authoredComments", authoredCommentIds);

        Set<Long> likedCommentIds = account.getLikedComments().stream()
                .map(Comment::getId)
                .collect(Collectors.toSet());
        jsonGenerator.writeObjectField("likedComments", likedCommentIds);




        jsonGenerator.writeEndObject();
    }
}