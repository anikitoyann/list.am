package com.example.listam.controller;

import com.example.listam.entity.Comment;
import com.example.listam.entity.Item;
import com.example.listam.repository.CommentRepository;
import com.example.listam.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;


@Controller
public class CommentController {
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    ItemRepository itemRepository;

    @GetMapping("/comments")
    public String commentPage(ModelMap modelMap) {
        List<Comment> all = commentRepository.findAll();
        modelMap.addAttribute("comments", all);
        return "comments";
    }

    @GetMapping("/comments/add")
    public String commentAddPage(ModelMap modelMap) {
        List<Item> all = itemRepository.findAll();
        modelMap.addAttribute("items", all);
        return "singleItem";
    }

    @PostMapping("/comments/add")
    public String commentAdd(@RequestParam("comment") String comment, @RequestParam("item_id") int itemId) {
        Optional<Item> item = itemRepository.findById(itemId);
        if (item.isPresent()) {
            Item item1 = item.get();
            Comment comment1 = new Comment();
            comment1.setComment(comment);
            comment1.setItem(item1);
            commentRepository.save(comment1);
            return "redirect:/comments";
        } else {
            return "redirect:/items";
        }
    }

    @GetMapping("/comments/remove")
    public String removeComments(@RequestParam("id") int id) {
        commentRepository.deleteById(id);
        return "redirect:/comments";
    }
}