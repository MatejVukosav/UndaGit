package vuki.com.undagit.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mvukosav on 9.1.2017..
 * Copyright by @ UndaGit
 */
public class SearchRepositoriesResponse implements Serializable {
    @SerializedName("total_count")
    int totalCount;
    @SerializedName("incomplete_results")
    boolean isResultComplete;
    @SerializedName("items")
    List<Repository> searchUserModels;

    public boolean isResultComplete() {
        return isResultComplete;
    }

    public List<Repository> getSearchUserModels() {
        return searchUserModels;
    }

    public int getTotalCount() {
        return totalCount;
    }
}
