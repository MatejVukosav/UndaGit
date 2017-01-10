package vuki.com.undagit.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by mvukosav on 9.1.2017..
 * Copyright by @ UndaGit
 */
public class Repository implements Serializable {

    @SerializedName("id")
    long id;
    @SerializedName("name")
    String name;
    @SerializedName("full_name")
    String fullName;
    @SerializedName("owner")
    SearchUserModel owner;
    @SerializedName("private")
    boolean isPrivate;
    @SerializedName("html_url")
    String html_url;
    @SerializedName("description")
    String description;
    @SerializedName("fork")
    boolean isForked;
    @SerializedName("url")
    String url;
    //etc..
    @SerializedName("created_at")
    Date createdAt;
    @SerializedName("updated_at")
    Date updatedAt;
    @SerializedName("pushed_at")
    Date pushedAt;
    @SerializedName("language")
    String language;
    @SerializedName("watchers_count")
    int numOfWatchers;
    @SerializedName("forks_count")
    int numOfForks;
    @SerializedName("stargazers_count")
    int numOfStars;
    @SerializedName("open_issues")
    int numOfOpenIssues;

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getPushedAt() {
        return pushedAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public String getDescription() {
        return description;
    }

    public String getFullName() {
        return fullName;
    }

    public String getHtml_url() {
        return html_url;
    }

    public long getId() {
        return id;
    }

    public boolean isForked() {
        return isForked;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public String getLanguage() {
        return language;
    }

    public String getName() {
        return name;
    }

    public int getNumOfForks() {
        return numOfForks;
    }

    public int getNumOfWatchers() {
        return numOfWatchers;
    }

    public int getNumOfOpenIssues() {
        return numOfOpenIssues;
    }

    public SearchUserModel getOwner() {
        return owner;
    }

    public String getUrl() {
        return url;
    }

    public int getNumOfStars() {
        return numOfStars;
    }
}
