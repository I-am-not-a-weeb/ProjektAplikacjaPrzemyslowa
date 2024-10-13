package Services;

import Database.Meme;
import Repos.MemeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class MemeService {
    @Autowired
    private final MemeRepo memeRepo;

    public MemeService() {
        this.memeRepo = null;
    }
    public MemeService(MemeRepo memeRepo) {
        this.memeRepo = memeRepo;
    }

    public void addMeme(Meme meme) {
        try{
            memeRepo.save(meme);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Meme getMemeById(long id) {
        return memeRepo.findById(id);
    }

    public Optional<Meme> getMemesByWordInTitle(String title) {
        return memeRepo.findByWordInTitle(title);
    }
}
