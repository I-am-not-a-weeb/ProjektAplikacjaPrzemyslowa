package database.mysql;

import jakarta.persistence.*;
import java.util.HashSet;

@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String email;

    @ManyToMany
    @JoinTable(
            name = "liked_accounts",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "liked_account_id"))
    private HashSet<Account> likedAccounts = new HashSet<>();


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

}
