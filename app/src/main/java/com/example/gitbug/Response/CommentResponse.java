package com.example.gitbug.Response;

public class CommentResponse {
    private String body;
    private BugResponse.User user;
    private String created_at;

    public String getCreated_at() {
        return created_at;
    }

    public String getBody() {
        return body;
    }

    public BugResponse.User getUser() {
        return user;
    }
}
