package com.JRTP2.Services;

import com.JRTP2.BindingClasses.PostForm;
import com.JRTP2.Entity.PostEntity;
import com.JRTP2.Entity.UserEntity;
import com.JRTP2.Repositories.PostRepo;
import com.JRTP2.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public void createPost(PostForm form, Integer userId) {

        UserEntity user = userRepo.findById(userId).get();

        PostEntity post = new PostEntity();
        post.setTitle(form.getTitle());
        post.setDescription(form.getDescription());
        post.setContent(form.getContent());
        post.setUser(user);
        post.setCreatedDate(LocalDate.from(LocalDateTime.now()));

        postRepo.save(post);
    }

    @Override
    public List<PostEntity> getAllPosts() {
        return postRepo.findAll();
    }

    @Override
    public void deletePost(Integer postId, Integer userId) {

        PostEntity post = postRepo.findById(postId).get();

        if (!post.getUser().getUserId().equals(userId)) {
            throw new RuntimeException("Unauthorized");
        }

        postRepo.delete(post);
    }
}

