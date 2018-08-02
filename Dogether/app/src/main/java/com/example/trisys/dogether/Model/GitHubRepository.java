package com.example.trisys.dogether.Model;

/**
 * Created by trisys on 3/4/18.
 */

public class GitHubRepository {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String name;

    public String getIssues_url() {
        return issues_url;
    }
    public void setIssues_url(String issues_url) {
        this.issues_url = issues_url;
    }
    public String issues_url;

}
