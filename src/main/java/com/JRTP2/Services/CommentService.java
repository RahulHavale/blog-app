package com.JRTP2.Services;

import com.JRTP2.BindingClasses.CommentForm;

public interface CommentService {
    void addComment(CommentForm form, Integer userId);

    void deleteComment(Integer commentId, Integer userId);
}
