package database.mysql;


import jakarta.persistence.*;

import java.util.HashSet;


@Entity
@Table(name="tags")
public class Tags {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "tags")
    private HashSet<Meme> memes = new HashSet<>();

    public Tags() {
    }
    public Tags(String name)
    {
        this.name = name;
    }
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setMemes(HashSet<Meme> memes) {
        this.memes = memes;
    }
    public HashSet<Meme>  addMeme(Meme meme) {
        memes.add(meme);
        return memes;
    }
    public HashSet<Meme> getMemes() {
        return memes;
    }
}
