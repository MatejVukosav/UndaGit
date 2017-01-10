package vuki.com.undagit.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by mvukosav on 9.1.2017..
 * Copyright by @ UndaGit
 */
public abstract class AbstractUser implements Serializable {

    @SerializedName("login")
    String username;
    @SerializedName("id")
    long id;
    @SerializedName("avatar_url")
    String avatarUrl;
    @SerializedName("gravatar_id")
    String gravatarId;
    @SerializedName("url")
    String profileUrl;
    @SerializedName("html_url")
    String htmlProfileUrl;
    @SerializedName("followers_url")
    String followersUrl;
    @SerializedName("following_url")
    String followingUrl;
    @SerializedName("gists_url")
    String gistsUrl;
    @SerializedName("starred_url")
    String starredUrl;
    @SerializedName("subscriptions_url")
    String subscriptionsUrl;
    @SerializedName("organizations_url")
    String organisationsUrl;
    @SerializedName("repos_url")
    String reposUrl;
    @SerializedName("events_url")
    String eventsUrl;
    @SerializedName("received_events_url")
    String receivedEventsUrl;
    @SerializedName("type")
    String accountType;
    @SerializedName("site_admin")
    boolean isSiteAdmin;

    public String getAccountType() {
        return accountType;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getEventsUrl() {
        return eventsUrl;
    }

    public String getFollowersUrl() {
        return followersUrl;
    }

    public String getFollowingUrl() {
        return followingUrl;
    }

    public String getGistsUrl() {
        return gistsUrl;
    }

    public String getGravatarId() {
        return gravatarId;
    }

    public String getHtmlProfileUrl() {
        return htmlProfileUrl;
    }

    public long getId() {
        return id;
    }

    public boolean isSiteAdmin() {
        return isSiteAdmin;
    }

    public String getOrganisationsUrl() {
        return organisationsUrl;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public String getReceivedEventsUrl() {
        return receivedEventsUrl;
    }

    public String getReposUrl() {
        return reposUrl;
    }

    public String getStarredUrl() {
        return starredUrl;
    }

    public String getSubscriptionsUrl() {
        return subscriptionsUrl;
    }

    public String getUsername() {
        return username;
    }
}
