package vuki.com.undagit.network;

import android.support.annotation.Nullable;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import vuki.com.undagit.annotations.OrderType;
import vuki.com.undagit.annotations.SortType;
import vuki.com.undagit.models.User;
import vuki.com.undagit.models.response.SearchRepositoriesResponse;
import vuki.com.undagit.models.response.SearchUsersResponse;

/**
 * Created by mvukosav on 9.1.2017..
 * Copyright by @ UndaGit
 */
public interface ApiManagerService {

    /* API APPENDIX */
    String basePath = "/v2.0";

    /*  USER API */
    String userApi = "/users";

    @GET(userApi + "/{username}")
    Call<User> getUserData( @Path("username") String username );

    /*  SEARCH API */
    String searchApi = "/search";
    String repositories = "/repositories";
    String users = "/users";

    @GET(searchApi + repositories)
    Call<SearchRepositoriesResponse> searchRepositories( @Query("q") @Nullable String query, @Query("sort") @SortType @Nullable String sortType, @Query("order") @OrderType String orderType );

    @GET(searchApi + users)
    Call<SearchUsersResponse> searchUsers( @Query("q") String query, @Query("sort") SortType sortType, @Query("order") OrderType orderType );

    /*  REPOSITORY API */
    String reposApi = "/repos";

    @GET(reposApi + "/{owner}/{repo}")
    Call<User> getUserRepository( @Path("owner") String owner, @Path("repo") String repoName );

}