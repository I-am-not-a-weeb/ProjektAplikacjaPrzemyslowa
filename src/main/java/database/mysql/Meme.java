package database.mysql;

import database.mysql.Account;
import jakarta.persistence.*;

@Entity
@Table(name = "meme")
public class Meme {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    public Account author;

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public void setAuthor(database.mysql.Account author) {
        this.author = author;
    }
    public Account getAuthor() {
        return author;
    }
    
}
