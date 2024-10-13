package Database;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;


@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    private Account authorComment;

    @ManyToMany(mappedBy = "likedComments")
    private Set<Account> likedAccounts = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "meme_commented_id")
    private Meme memeCommented;

    @ManyToOne
    private Comment parentComment;

    @OneToMany(mappedBy = "parentComment")
    private Set<Comment> childComments = new HashSet<>();

    public Comment(String content) {
        this.content = content;
    }
    public Comment() {
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

    public Set<Account> getLikedAccounts() {
        return likedAccounts;
    }

    public void addLikedAccounts(Account account) {
        this.likedAccounts.add(account);
    }
}
