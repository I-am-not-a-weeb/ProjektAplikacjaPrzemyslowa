package neo4j.neo4jMappings;

import org.springframework.data.neo4j.core.schema.*;

import java.util.HashSet;
import java.util.Set;

@Node
public class Comment {
    @Id @GeneratedValue private Long id;

    private String content;

    @Relationship(type = "LIKED_COMMENT", direction = Relationship.Direction.INCOMING)
    public Account author;



    public Comment() {
    }
    public Comment(String content) {
        this.content = content;
    }
    public Long getId() {
        return id;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public void setAuthor(Account author) {
        this.author = author;
    }
    public Account getAuthor() {
        return author;
    }
}
