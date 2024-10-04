package database.mysql;

import database.mysql.Account;
import jakarta.persistence.*;


@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    public Account author;



    public Comment() {
    }
    public Comment(String content) {
        this.content = content;
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
    public void setAuthor(database.mysql.Account author) {
        this.author = author;
    }
    public Account getAuthor() {
        return author;
    }
}
