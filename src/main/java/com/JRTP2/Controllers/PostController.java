package com.JRTP2.Controllers;

import com.JRTP2.BindingClasses.PostForm;
import com.JRTP2.Services.PostService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/posts")
    public String posts(Model model, HttpSession session) {

        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) return "redirect:/login";

        model.addAttribute("posts", postService.getAllPosts());
        return "posts_page";
    }

    @GetMapping("/post/new")
    public String newPost(Model model, HttpSession session) {

        if (session.getAttribute("userId") == null)
            return "redirect:/login";

        model.addAttribute("postForm", new PostForm());
        return "new_post";
    }

    @PostMapping("/post/save")
    public String savePost(
            @ModelAttribute PostForm postForm,
            HttpSession session) {

        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) return "redirect:/login";

        postService.createPost(postForm, userId);
        return "redirect:/posts";
    }

    @GetMapping("/post/delete/{id}")
    public String deletePost(
            @PathVariable Integer id,
            HttpSession session) {

        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) return "redirect:/login";

        postService.deletePost(id, userId);
        return "redirect:/posts";
    }
}

