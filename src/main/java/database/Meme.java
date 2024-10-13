package Database;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Meme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String url;

    @ManyToOne(cascade=CascadeType.ALL)
    private Account authorMeme;

    @ManyToMany(mappedBy = "likedMemes",
            cascade=CascadeType.ALL)
    private Set<Account> likedAccounts = new HashSet<>();

    @OneToMany(mappedBy = "memeCommented",
            cascade=CascadeType.ALL)
    private Set<Comment> comments = new HashSet<>();

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(
            name = "meme_tags",
            joinColumns = @JoinColumn(name = "meme_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags = new HashSet<>();

    public Meme() {
    }
    public Meme(String title, String url, Account authorMeme) {
        this.title = title;
        this.url = url;
        this.authorMeme = authorMeme;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public Account getAuthorMeme() {
        return authorMeme;
    }
    public void setAuthorMeme(Account authorMeme) {
        this.authorMeme = authorMeme;
    }

    public Set<Account> getLikedAccounts() {
        return likedAccounts;
    }

    public void addLikedAccounts(Account account) {
        this.likedAccounts.add(account);
    }

    public Set<Comment> getComments() {
        return comments;
    }
    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    public Set<Tag> getTags() {
        return tags;
    }
    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }
    public void addTag(Tag tag) {
        this.tags.add(tag);
    }
}