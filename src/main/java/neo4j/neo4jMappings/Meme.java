package neo4j.neo4jMappings;

import org.springframework.data.neo4j.core.schema.*;

import java.util.HashSet;
import java.util.Set;

@Node
public class Meme {
    @Id @GeneratedValue private Long id;

    private String title;
    private String content;

    @Relationship(type="COMMENTED", direction = Relationship.Direction.INCOMING)
    public Account author;


}
