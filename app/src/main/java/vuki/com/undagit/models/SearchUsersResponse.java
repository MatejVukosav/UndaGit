package vuki.com.undagit.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mvukosav on 9.1.2017..
 * Copyright by @ UndaGit
 */
public class SearchUsersResponse implements Serializable {

    @SerializedName("total_count")
    int totalCount;
    @SerializedName("incomplete_results")
    boolean isResultComplete; //valjda
    @SerializedName("items")
    List<SearchUserModel> searchUserModels;

    public boolean isResultComplete() {
        return isResultComplete;
    }

    public List<SearchUserModel> getSearchUserModels() {
        return searchUserModels;
    }

    public int getTotalCount() {
        return totalCount;
    }
}
