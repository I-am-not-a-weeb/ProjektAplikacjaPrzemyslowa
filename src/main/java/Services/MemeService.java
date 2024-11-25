package Services;

import Database.Account;
import Database.Meme;
import Repos.AccountRepo;
import Repos.MemeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.Set;

@Service
public class MemeService {
    @Autowired
    private final MemeRepo memeRepo;
    @Autowired
    private final AccountRepo accountRepo;


    public MemeService() {
        this.memeRepo = null;
        this.accountRepo = null;
    }
    public MemeService(MemeRepo memeRepo) {
        this.memeRepo = memeRepo;
        this.accountRepo = null;
    }

    public void addMeme(Meme meme) {
        try{
            memeRepo.save(meme);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public Page<Meme> getAllMemesByPage(PageRequest page) {
        return memeRepo.findAll(page);
    }
    public Page<Meme> getAllMemesByCreationDateDescByPage(PageRequest page) {
        return memeRepo.findAllByOrderByCreationDateDesc(page);
    }

    public void addMemeFile(MultipartFile MPfile, Meme meme)
    {
        memeRepo.save(meme);

        String fileType = MPfile.getContentType();
        fileType = fileType.substring(fileType.indexOf('/') + 1);
        String filepath = "images/"+meme.getId().toString() +"."+ fileType;
        File file = new File(filepath);
        try {
            file.createNewFile();
            meme.setUrl(filepath);
            memeRepo.save(meme);
            MPfile.transferTo(Paths.get(file.getAbsolutePath()));
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

    @Transactional
    public void accountLikeMeme(Meme meme, Account account) {
        meme.addLikingAccount(account);
        account.addLikedMeme(meme);
    }

    public void save(Meme meme) {
        memeRepo.save(meme);
    }
}
