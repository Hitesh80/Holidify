package com.example.trisys.dogether.Adapter;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.trisys.dogether.Model.GitHubRepoIssue;
import com.example.trisys.dogether.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by trisys on 3/4/18.
 */

public class RepoIssueAdapter extends BaseAdapter {

    private List<GitHubRepoIssue> gitHubRepoIssues = new ArrayList<>();

    @Override public int getCount() {
        return gitHubRepoIssues.size();
    }

    @Override public GitHubRepoIssue getItem(int position) {
        if (position < 0 || position >= gitHubRepoIssues.size()) {
            return null;
        } else {
            return gitHubRepoIssues.get(position);
        }
    }

    @Override public long getItemId(int position) {
        return position;
    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {
        final View view = (convertView != null ? convertView : createView(parent));
        final GitHubRepoViewHolder viewHolder = (GitHubRepoViewHolder) view.getTag();
        viewHolder.setGitHubRepo(getItem(position));
        return view;
    }

    public void setGitHubRepoIssues(@Nullable List<GitHubRepoIssue> repos) {
        if (repos == null) {
            return;
        }
        gitHubRepoIssues.clear();
        gitHubRepoIssues.addAll(repos);
        notifyDataSetChanged();
    }

    private View createView(ViewGroup parent) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View view = inflater.inflate(R.layout.item_github_repo, parent, false);
        final GitHubRepoViewHolder viewHolder = new GitHubRepoViewHolder(view);
        view.setTag(viewHolder);
        return view;
    }

    private static class GitHubRepoViewHolder {

        private TextView textRepoIssueName;
        private TextView textRepoIssueCommentUrl;
        private TextView textIssueState;
        private TextView textIssueCreatedAt;

        public GitHubRepoViewHolder(View view) {
            textRepoIssueName = (TextView) view.findViewById(R.id.text_repo_issue_name);
            textRepoIssueCommentUrl = (TextView) view.findViewById(R.id.text_repo_issue_comments_url);
            textIssueState = (TextView) view.findViewById(R.id.text_state);
            textIssueCreatedAt = (TextView) view.findViewById(R.id.text_created_at);
        }

        public void setGitHubRepo(GitHubRepoIssue gitHubRepoIssue) {
            textRepoIssueName.setText(gitHubRepoIssue.title);
            textRepoIssueCommentUrl.setText("Comments Url"+gitHubRepoIssue.comments_url);
            textIssueState.setText("State: " + gitHubRepoIssue.state);
            textIssueCreatedAt.setText("Created at: " + gitHubRepoIssue.created_at);
        }
    }
}
