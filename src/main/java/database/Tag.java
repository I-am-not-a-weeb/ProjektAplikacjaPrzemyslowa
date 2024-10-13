package Database;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Tag {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToMany(mappedBy = "tags",
            cascade=CascadeType.ALL)
    private Set<Meme> memes = new HashSet<>();

    @Column(unique = true, nullable = false)
    private String name;

    public Tag() {
    }
    public Tag(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Set<Meme> getMemes() {
        return memes;
    }
    public void addMeme(Meme meme) {
        this.memes.add(meme);
    }
}
