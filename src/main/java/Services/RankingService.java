package Services;


import Database.Account;
import Database.Comment;
import Database.Meme;
import Repos.AccountRepo;
import Repos.CommentRepo;
import Repos.MemeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RankingService {
    @Autowired
    private final AccountRepo accountRepo;
    @Autowired
    private final MemeRepo memeRepo;
    @Autowired
    private final CommentRepo commentRepo;

    public RankingService() {
        this.accountRepo = null;
        this.memeRepo = null;
        this.commentRepo = null;
    }
    public RankingService(
            AccountRepo accountRepo,
            MemeRepo memeRepo,
            CommentRepo commentRepo
    ) {
        this.accountRepo = accountRepo;
        this.memeRepo = memeRepo;
        this.commentRepo = commentRepo;
    }


    public Set<Account> rankAccounts() {
        return accountRepo.findAllByOrderByLikingAccountsDesc();
    }
    public Set<Meme> rankMemes() {
        return memeRepo.findAllByOrderByLikingAccountsDesc();
    }

    public Set<Comment> rankComments() {
        return commentRepo.findAllByOrderByLikingAccountsDesc();
    }


}
