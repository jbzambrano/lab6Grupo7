package sw2.lab6.teletok.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sw2.lab6.teletok.entity.Post;
import sw2.lab6.teletok.entity.PostComment;
import sw2.lab6.teletok.entity.User;
import sw2.lab6.teletok.repository.PostCommentRepository;
import sw2.lab6.teletok.repository.PostLikeRepository;
import sw2.lab6.teletok.repository.PostRepository;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDate;

@Controller
public class PostController {

    @Autowired
    PostCommentRepository postCommentRepository;

    @Autowired
    PostLikeRepository postLikeRepository;

    @Autowired
    PostRepository postRepository;




    @GetMapping(value = {"", "/"})
    public String listPost(){
        return "post/list";
    }

    @GetMapping("/post/new")
    public String newPost(@ModelAttribute("post") Post post){
        return "post/new";
    }

    @PostMapping("/post/save")
    public String savePost(@ModelAttribute("post") @Valid Post post, BindingResult bindingResult, HttpSession session, Model model) {

        if(bindingResult.hasErrors()){


            return "redirect:/post/new";
        }
        else {
            User user = (User) session.getAttribute("user");
            post.setCreationDate(LocalDate.now());
            post.setUser(user);

            if (user.getId()==0){
                return "/post/view";
            } else {
                postRepository.save(post);
                return "redirect:";
            }

        }

    }

    @GetMapping("/post/file/{media_url}")
    public String getFile() {
        return "";
    }

    @GetMapping("/post/{id}")
    public String viewPost() {
        return "post/view";
    }

    @PostMapping("/post/comment")
    public String postComment(@ModelAttribute("postComment") PostComment postComment ) {



        return "";
    }

    @PostMapping("/post/like")
    public String postLike() {
        return "";
    }
}
