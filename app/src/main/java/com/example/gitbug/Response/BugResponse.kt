package com.example.gitbug.Response;

public class BugResponse {
    /**
     * {
     * "url":"https://api.github.com/repos/square/okhttp/issues/6923",
     * "repository_url":"https://api.github.com/repos/square/okhttp",
     * "labels_url":"https://api.github.com/repos/square/okhttp/issues/6923/labels{/name}",
     * "comments_url":"https://api.github.com/repos/square/okhttp/issues/6923/comments",
     * "events_url":"https://api.github.com/repos/square/okhttp/issues/6923/events",
     * "html_url":"https://github.com/square/okhttp/pull/6923",
     * "id":1060170293,
     * "node_id":"PR_kwDOAE6eHc4u2lou",
     * "number":6923,
     * "title":"Code cleanups",
     * "user":{},
     * "labels":[
     * ],
     * "state":"open",
     * "locked":false,
     * "assignee":null,
     * "assignees":[
     * ],
     * "milestone":null,
     * "comments":0,
     * "created_at":"2021-11-22T13:40:01Z",
     * "updated_at":"2021-11-23T07:43:18Z",
     * "closed_at":null,
     * "author_association":"CONTRIBUTOR",
     * "active_lock_reason":null,
     * "draft":false,
     * "pull_request":{},
     * "body":"Tested [here](https://github.com/Goooler/okhttp/pull/19).",
     * "reactions":{},
     * "timeline_url":"https://api.github.com/repos/square/okhttp/issues/6923/timeline",
     * "performed_via_github_app":null
     * }
     *
     * "user":{
     * "login":"Goooler",
     * "id":10363352,
     * "node_id":"MDQ6VXNlcjEwMzYzMzUy",
     * "avatar_url":"https://avatars.githubusercontent.com/u/10363352?v=4",
     * "gravatar_id":"",
     * "url":"https://api.github.com/users/Goooler",
     * "html_url":"https://github.com/Goooler",
     * "followers_url":"https://api.github.com/users/Goooler/followers",
     * "following_url":"https://api.github.com/users/Goooler/following{/other_user}",
     * "gists_url":"https://api.github.com/users/Goooler/gists{/gist_id}",
     * "starred_url":"https://api.github.com/users/Goooler/starred{/owner}{/repo}",
     * "subscriptions_url":"https://api.github.com/users/Goooler/subscriptions",
     * "organizations_url":"https://api.github.com/users/Goooler/orgs",
     * "repos_url":"https://api.github.com/users/Goooler/repos",
     * "events_url":"https://api.github.com/users/Goooler/events{/privacy}",
     * "received_events_url":"https://api.github.com/users/Goooler/received_events",
     * "type":"User",
     * "site_admin":false
     * }
     * */

    private String url;
    private String comments_url;
    private String title;
    private String body;
    private String updated_at;
    private User user;
    private int number;
    private int comments;

    public int getComments() {
        return comments;
    }

    public int getNumber() {
        return number;
    }

    public String getUrl() {
        return url;
    }

    public String getComments_url() {
        return comments_url;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public User getUser() {
        return user;
    }

    public class User{
        private  String login;
        private  String avatar_url;
        public  String getLogin() {
            return login;
        }
        public  String getAvatar_url() {
            return avatar_url;
        }
    }
}
