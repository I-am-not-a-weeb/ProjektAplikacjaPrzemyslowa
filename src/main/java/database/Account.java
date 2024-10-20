package Database;


import api.Serializers.AccountSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@JsonSerialize(using = AccountSerializer.class)
public class Account {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;
    @Column(unique = true, nullable = false)
    private String email;
    private int permissions=0;


    @ManyToMany(mappedBy = "likedAccounts",
            cascade=CascadeType.ALL)
    private Set<Account> likingAccounts = new HashSet<>();
    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(
            name = "liked_accounts",
            joinColumns = @JoinColumn(name = "liking_account_id"),
            inverseJoinColumns = @JoinColumn(name = "liked_account_id")
    )
    private Set<Account> likedAccounts = new HashSet<>();



    @OneToMany(mappedBy = "authorMeme",
            cascade=CascadeType.ALL)
    private Set<Meme> authoredMemes = new HashSet<>();
    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(
            name = "liked_memes",
            joinColumns = @JoinColumn(name = "liking_account_id"),
            inverseJoinColumns = @JoinColumn(name = "liked_meme_id")
    )
    private Set<Meme> likedMemes = new HashSet<>();



    @OneToMany(mappedBy = "authorComment",
            cascade=CascadeType.ALL)
    private Set<Comment> authoredComments = new HashSet<>();
    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(
            name = "liked_comments",
            joinColumns = @JoinColumn(name = "liking_account_id"),
            inverseJoinColumns = @JoinColumn(name = "liked_comment_id")
    )
    private Set<Comment> likedComments = new HashSet<>();

    public Account(String username, String email) {
        this.username = username;
        this.email = email;
    }
    public Account(String username, String email, int permissions) {
        this.username = username;
        this.email = email;
        this.permissions = permissions;
    }
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
    public int getPermissions() {
        return permissions;
    }
    public void setPermissions(int permissions) {
        this.permissions = permissions;
    }
    public Set<Account> getLikedAccounts() {
        return likedAccounts;
    }

    public Set<String> getLikedAccountsUsernames() {
        return likedAccounts.stream().map(Account::getUsername).collect(Collectors.toSet());
    }
    public void addLikedAccount(Account account) {
        likedAccounts.add(account);
        account.likingAccounts.add(this);
    }
    public Set<Account> getLikingAccounts() {
        return likingAccounts;
    }
    public void addLikingAccount(Account account) {
        likingAccounts.add(account);
        account.likedAccounts.add(this);
    }
    public Set<Meme> getAuthoredMemes() {
        return authoredMemes;
    }
    public void addAuthoredMeme(Meme meme) {
        authoredMemes.add(meme);
    }
    public Set<Comment> getAuthoredComments() {
        return authoredComments;
    }

    public Set<Comment> getLikedComments() {
        return likedComments;
    }

    public Set<Meme> getLikedMemes() {
        return likedMemes;
    }
    public void addLikedMeme(Meme meme) {
        likedMemes.add(meme);
    }
}
