package com.JRTP2.Services;

import com.JRTP2.BindingClasses.PostForm;
import com.JRTP2.Entity.PostEntity;

import java.util.List;

public interface PostService {
    void createPost(PostForm form, Integer userId);
    List<PostEntity> getAllPosts();
    void deletePost(Integer postId, Integer userId);

}
