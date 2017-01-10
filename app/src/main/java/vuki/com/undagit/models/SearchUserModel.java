package vuki.com.undagit.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mvukosav on 9.1.2017..
 * Copyright by @ UndaGit
 */
public class SearchUserModel extends AbstractUser {

    @SerializedName("score")
    float score;

    public float getScore() {
        return score;
    }
}
