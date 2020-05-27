package com.hillel.auto.model;

public class CommentApi {
    private Comment comment;

    public CommentApi() {
    }

    public CommentApi(Comment comment) {
        this.comment = comment;
    }

    public Comment getComment() {
        return comment;
    }

    public void getComment(Comment comment) {
        this.comment = comment;
    }
}
