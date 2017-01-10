package vuki.com.undagit.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by mvukosav on 9.1.2017..
 * Copyright by @ UndaGit
 */
public class User extends AbstractUser{

    @SerializedName("name")
    String name;
    @SerializedName("company")
    String company;
    @SerializedName("blog")
    String blog;
    @SerializedName("location")
    String location;
    @SerializedName("email")
    String email;
    @SerializedName("hireable")
    boolean isHireable;
    @SerializedName("bio")
    String bio;
    @SerializedName("public_repos")
    int numOfPublicRepos;
    @SerializedName("public_gists")
    int numOfPublicGists;
    @SerializedName("followers")
    int numOfFollowers;
    @SerializedName("following")
    int numOfFollowing;
    @SerializedName("created_at")
    Date createdAt;
    @SerializedName("updated_at")
    Date updatedAt;

    public String getBio() {
        return bio;
    }

    public String getBlog() {
        return blog;
    }

    public String getCompany() {
        return company;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getEmail() {
        return email;
    }

    public boolean isHireable() {
        return isHireable;
    }

    public String getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public int getNumOfFollowers() {
        return numOfFollowers;
    }

    public int getNumOfFollowing() {
        return numOfFollowing;
    }

    public int getNumOfPublicGists() {
        return numOfPublicGists;
    }

    public int getNumOfPublicRepos() {
        return numOfPublicRepos;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }
}
