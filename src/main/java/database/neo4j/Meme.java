package database.neo4j;

import org.springframework.data.neo4j.core.schema.*;

@Node
public class Meme {
    @Id @GeneratedValue private Long id;

    private String title;
    private String content;

    @Relationship(type="COMMENTED", direction = Relationship.Direction.INCOMING)
    public Account author;


}
