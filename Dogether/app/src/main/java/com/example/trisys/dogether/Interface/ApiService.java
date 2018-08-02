package com.example.trisys.dogether.Interface;

import com.example.trisys.dogether.Model.GitHubRepoIssue;
import com.example.trisys.dogether.Model.GitHubRepository;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Hitesh on 3/4/18.
 */

public interface ApiService {
    @GET("/users/{user}/repos")
    Observable<List<GitHubRepoIssue>> getStarredRepositories(@Path("user") String userName);

    @GET("/repos/{owner}/{repoName}")
    Observable<GitHubRepository> getSingleRepo(@Path("owner") String owner,
                                               @Path("repoName") String repoName );
}
