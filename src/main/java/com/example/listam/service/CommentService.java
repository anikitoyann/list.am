package com.example.listam.service;

import com.example.listam.entity.Comment;
import com.example.listam.entity.User;

import java.util.List;

public interface CommentService {
    List<Comment> findAllByItem_id(int id);

    void save(Comment comment, User currentUser);
}
