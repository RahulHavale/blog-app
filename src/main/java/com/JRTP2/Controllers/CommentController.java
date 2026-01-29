package com.JRTP2.Controllers;

import com.JRTP2.BindingClasses.CommentForm;
import com.JRTP2.Services.CommentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/comment/add")
    public String addComment(
            @ModelAttribute CommentForm form,
            HttpSession session) {

        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) return "redirect:/login";

        commentService.addComment(form, userId);
        return "redirect:/posts";
    }

    @GetMapping("/comment/delete/{id}")
    public String deleteComment(
            @PathVariable Integer id,
            HttpSession session) {

        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) return "redirect:/login";

        commentService.deleteComment(id, userId);
        return "redirect:/posts";
    }
}

