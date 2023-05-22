package com.example.listam.service.impl;

import com.example.listam.entity.Comment;
import com.example.listam.entity.User;
import com.example.listam.repository.CommentRepository;
import com.example.listam.repository.ItemRepository;
import com.example.listam.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {
private  final CommentRepository commentRepository;

    @Override
    public List<Comment> findAllByItem_id(int id) {
        List<Comment> comments;
        comments=commentRepository.findAllByItem_id(id);
        return comments;
    }


    @Override
    public void save(Comment comment, User currentUser) {
        comment.setCommentDate(new Date());
        comment.setUser(currentUser);
        commentRepository.save(comment);
    }
}
