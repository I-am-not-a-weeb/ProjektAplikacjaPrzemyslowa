package database.neo4j;

import org.springframework.data.neo4j.core.schema.*;

import java.util.HashSet;
import java.util.Set;

@Node
public class Account {
    @Id @GeneratedValue private Long id;
    private String username;
    private String email;
    @Relationship(type = "LIKED_ACCOUNT", direction = Relationship.Direction.OUTGOING)
    public Set<Account> likedAccounts;


    @Relationship(type = "LIKED_MEME", direction = Relationship.Direction.OUTGOING)
    public Set<Meme> likedMemes;

    @Relationship(type = "LIKED_COMMENT", direction = Relationship.Direction.OUTGOING)
    public Set<Comment> likedComments;

    @Relationship(type = "COMMENTED", direction = Relationship.Direction.OUTGOING)
    public Set<Comment> commented;

    @Relationship(type = "POSTED", direction = Relationship.Direction.OUTGOING)
    public Set<Meme> postedMemes;

    public Account() {
    }
    public Account(String username) {
        this.username = username;
    }
    public Long getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public void likedAccount(Account likedAcc)
    {
        if(this.likedAccounts == null)
        {
            this.likedAccounts = new HashSet<>();
        }
        this.likedAccounts.add(likedAcc);
    }

    public void likedComment(Comment likedComment)
    {
        if(this.likedComments == null)
        {
            this.likedComments = new HashSet<>();
        }
        this.likedComments.add(likedComment);
    }
    public void commented(Comment comment)
    {
        if(this.commented == null)
        {
            this.commented = new HashSet<>();
        }
        comment.author = this;
        this.commented.add(comment);
    }

    public void postedMeme(Meme meme)
    {
        if(this.postedMemes == null)
        {
            this.postedMemes = new HashSet<>();
        }
        meme.author = this;
        this.postedMemes.add(meme);
    }
}
