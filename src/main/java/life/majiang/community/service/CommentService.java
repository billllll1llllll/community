package life.majiang.community.service;

import life.majiang.community.exception.CustomizeErrorCode;
import life.majiang.community.exception.CustomizeException;
import life.majiang.community.mapper.CommentMapper;
import life.majiang.community.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired(required = false)
    CommentMapper commentMapper;

    public void insert(Comment comment) {
        if (comment.getParentId() == null || comment.getParentId() == 0) {
            new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        commentMapper.insert(comment);
    }
}

