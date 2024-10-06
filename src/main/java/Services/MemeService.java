package Services;

import database.mysql.Meme;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import database.repositories.MemeRepo;
@Service
public class MemeService {
    @Autowired private MemeRepo memeRepo;

    @Transactional
    public void deleteMeme(Long id) {
        memeRepo.deleteById(id);
    }

    @Transactional
    public void addMeme(Meme meme) {
        memeRepo.save(meme);
    }
}
