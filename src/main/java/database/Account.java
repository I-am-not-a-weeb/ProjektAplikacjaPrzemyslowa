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

    @Column(unique = true, nullable = false,length = 30)
    private String username;
    @Column(unique = true, nullable = false, length = 50)
    private String email;
    @Column
    private String email2;
    @Column
    private String imageType;
    private int admin =0;
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

    @OneToMany(cascade=CascadeType.ALL, mappedBy = "recipient")
    private Set<Notification> notifications;

    public Account(String username, String email) {
        this.username = username;
        this.email = email;
    }
    public Account(String username, String email, int admin) {
        this.username = username;
        this.email = email;
        this.admin = admin;
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
    public int getAdmin() {
        return admin;
    }
    public void setAdmin(int permissions) {
        this.admin = permissions;
    }
    public Set<Account> getLikedAccounts() {
        return likedAccounts;
    }
    public Set<String> getLikedAccountsUsernames() {
        return likedAccounts.stream().map(Account::getUsername).collect(Collectors.toSet());
    }
    public void addLikedAccount(Account account) {
        likedAccounts.add(account);
        //account.likingAccounts.add(this);
    }
    public Set<Account> getLikingAccounts() {
        return likingAccounts;
    }
    public void addLikingAccount(Account account) {
        likingAccounts.add(account);
        //account.likedAccounts.add(this);
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

    public void addLikedComment(Comment comment) {
        likedComments.add(comment);
    }
    public Set<Comment> getLikedCommentsUsernames() {
        return likedComments;
    }
    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public boolean isAdmin() {
        return admin == 1;
    }
    public void setAdmin(boolean admin) {
        this.admin = admin ? 1 : 0;
    }
    public String getEmail2() {
        return email2;
    }
    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    public Set<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(Set<Notification> notifications) {
        this.notifications = notifications;
    }
    public void addNotification(Notification notification) {
        this.notifications.add(notification);
    }
}
