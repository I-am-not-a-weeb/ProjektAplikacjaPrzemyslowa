package Database;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;


@Entity
@JsonSerialize(using = Serializers.CommentSerializer.class)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    private Account authorComment;

    @ManyToMany(mappedBy = "likedComments")
    private Set<Account> likingAccounts = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "meme_commented_id")
    private Meme memeCommented;

    @ManyToOne
    private Comment parentComment;

    public Set<Comment> getChildComments() {
        return childComments;
    }

    @OneToMany(mappedBy = "parentComment")
    private Set<Comment> childComments = new HashSet<>();

    public Comment(String content){}

    public Comment(Account authorComment, String content) {
        this.content = content;
        this.authorComment = authorComment;
        }
    public Comment() {
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

    public Account getAuthorComment() {
        return authorComment;
    }

    public void setAuthorComment(Account authorComment) {
        this.authorComment = authorComment;
    }

    public Set<Account> getLikingAccounts() {
        return likingAccounts;
    }

    public void addLikedAccounts(Account account) {
        this.likingAccounts.add(account);
    }
}
