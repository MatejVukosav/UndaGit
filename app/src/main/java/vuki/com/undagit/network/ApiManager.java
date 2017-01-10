package vuki.com.undagit.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import vuki.com.undagit.BuildConfig;
import vuki.com.undagit.models.User;

/**
 * Created by mvukosav on 9.1.2017..
 * Copyright by @ UndaGit
 */
public final class ApiManager implements ApiManagerInterface {

    private static final String TAG = "network";
    public static final String GITHUB_API_URL = BuildConfig.GITHUB_API_URL;
    private static ApiManager apiManagerInstance;
    private ApiManagerService apiManagerService;

    private User user;
    private static Gson gson = new GsonBuilder().create();

    private ApiManager() {

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor( new LoggingInterceptor2( LoggingInterceptor2.LogLevel.FULL ) )
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory( GsonConverterFactory.create( gson ) )
                .baseUrl( GITHUB_API_URL )
                .client( client )
                .build();

        apiManagerService = retrofit.create( ApiManagerService.class );
    }

    public User getUser() {
        return user;
    }

    public void setUser( User user ) {
        this.user = user;
    }

    public static Gson getGson() {
        return gson;
    }

    public synchronized static ApiManager getInstance() {
        if( apiManagerInstance == null ) {
            apiManagerInstance = new ApiManager();
        }
        return apiManagerInstance;
    }

    @Override
    public ApiManagerService getService() {
        return apiManagerService;
    }

}
