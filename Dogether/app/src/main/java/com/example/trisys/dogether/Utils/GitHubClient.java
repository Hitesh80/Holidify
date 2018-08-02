package com.example.trisys.dogether.Utils;

import android.support.annotation.NonNull;

import com.example.trisys.dogether.BuildConfig;
import com.example.trisys.dogether.Interface.ApiService;
import com.example.trisys.dogether.Model.GitHubRepoIssue;
import com.example.trisys.dogether.Model.GitHubRepository;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by Hitesh on 3/4/18.
 */

public class GitHubClient {

    private static final String GITHUB_BASE_URL = "https://api.github.com/";

    private static GitHubClient instance;

    private ApiService apiService;

    private GitHubClient() {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            clientBuilder.addInterceptor(interceptor);
        }
        OkHttpClient client = clientBuilder.build();

        final Gson gson =
                new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
        final Retrofit retrofit = new Retrofit.Builder().baseUrl(GITHUB_BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    public static GitHubClient getInstance() {
        if (instance == null) {
            instance = new GitHubClient();
        }
        return instance;
    }

    public Observable<List<GitHubRepoIssue>> getStarredRepos(@NonNull String userName) {
        return apiService.getStarredRepositories(userName);
    }

    public Observable<GitHubRepository> getSingleRepo(@NonNull String repoName) {
        String owner=repoName.trim().substring(0,repoName.indexOf('/'));
        String rName=repoName.substring(repoName.indexOf('/')+1);

        return apiService.getSingleRepo(owner,rName);
    }
}
