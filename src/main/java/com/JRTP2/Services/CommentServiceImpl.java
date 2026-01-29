package com.JRTP2.Services;

import com.JRTP2.BindingClasses.CommentForm;
import com.JRTP2.Entity.CommentEntity;
import com.JRTP2.Repositories.CommentRepo;
import com.JRTP2.Repositories.PostRepo;
import com.JRTP2.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public void addComment(CommentForm form, Integer userId) {

        CommentEntity comment = new CommentEntity();
        comment.setContent(form.getContent());
        comment.setPost(postRepo.findById(form.getPostId()).get());
        comment.setCreatedOn(LocalDate.from(LocalDateTime.now()));

        commentRepo.save(comment);
    }

    @Override
    public void deleteComment(Integer commentId, Integer userId) {

        CommentEntity comment = commentRepo.findById(commentId).get();

//        boolean allowed =
//                comment.getAuthor().getUserId().equals(userId) ||
//                        comment.getPost().getAuthor().getUserId().equals(userId);
//
//        if (!allowed) throw new RuntimeException("Unauthorized");

        commentRepo.delete(comment);
    }
}

