package com.hillel.auto.model;

import java.lang.reflect.Array;

public class CommentResponce {
    private CommentApi[] commentResponce;

    public CommentResponce() {
    }

    public CommentResponce(CommentApi[] commentResponce) {
        this.commentResponce = commentResponce;
    }

    public CommentApi[] getCommentApi() {
        return commentResponce;
    }

    public void getComment(CommentApi[] commentResponce) {
        this.commentResponce = commentResponce;
    }
}
