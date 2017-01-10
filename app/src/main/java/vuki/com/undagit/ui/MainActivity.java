package vuki.com.undagit.ui;

import android.databinding.DataBindingUtil;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vuki.com.undagit.R;
import vuki.com.undagit.annotations.OrderType;
import vuki.com.undagit.annotations.SortType;
import vuki.com.undagit.databinding.ActivityMainBinding;
import vuki.com.undagit.helpers.ApiErrorHandling;
import vuki.com.undagit.models.Repository;
import vuki.com.undagit.models.response.SearchRepositoriesResponse;
import vuki.com.undagit.network.ApiManager;

/**
 * Created by mvukosav on 9.1.2017..
 * Copyright by @ UndaGit
 */
public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    RepositoriesAdapter repositoriesAdapter;
    List<Repository> repositoryList;

    @SortType
    String sortType = SortType.BEST_MATCH;
    @OrderType
    String orderType = OrderType.ASC;

    String lastQuery = "";

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        binding = DataBindingUtil.setContentView( this, R.layout.activity_main );
        repositoryList = new ArrayList<>();
        repositoriesAdapter = new RepositoriesAdapter( this, repositoryList );

        binding.recyclerView.setAdapter( repositoriesAdapter );
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager( this );
        binding.recyclerView.setLayoutManager( linearLayoutManager );
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration( binding.recyclerView.getContext(), linearLayoutManager.getOrientation() );
        binding.recyclerView.addItemDecoration( dividerItemDecoration );
        binding.recyclerView.addItemDecoration( dividerItemDecoration );

        binding.orderAsc.setTypeface( null, Typeface.BOLD );
        binding.sortByBestMatch.setTypeface( null, Typeface.BOLD );

        setSortListeners();
        sertOrderListeners();

        binding.searchView.setOnQueryTextListener( new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit( String query ) {
                if( !TextUtils.isEmpty( query ) ) {
                    lastQuery = query;
                    getData( query, sortType, orderType );
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange( String newText ) {
                return false;
            }
        } );

        binding.searchView.setOnCloseListener( new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                repositoriesAdapter.removeAll();
                return true;
            }
        } );

        binding.searchView.setQuery( "UndaGit", false );
        getData( "UndaGit", sortType, orderType );
    }

    private void sertOrderListeners() {
        View.OnClickListener onOrderByClickListener = new View.OnClickListener() {
            @Override
            public void onClick( View view ) {

                String type = ( (TextView) view ).getText().toString();
                switch( type ) {
                    case OrderType.DESC:
                        orderType = OrderType.DESC;
                        break;
                    case OrderType.ASC:
                    default:
                        orderType = OrderType.ASC;
                        break;
                }
                binding.orderAsc.setTypeface( null, Typeface.NORMAL );
                binding.orderDesc.setTypeface( null, Typeface.NORMAL );

                ( (TextView) view ).setTypeface( null, Typeface.BOLD );

                getData( lastQuery, sortType, orderType );
            }
        };

        binding.orderDesc.setOnClickListener( onOrderByClickListener );
        binding.orderAsc.setOnClickListener( onOrderByClickListener );
    }

    private void setSortListeners() {
        View.OnClickListener onSortByClickListener = new View.OnClickListener() {
            @Override
            public void onClick( View view ) {

                String type = ( (TextView) view ).getText().toString();
                switch( type ) {
                    case SortType.FORKS:
                        sortType = SortType.FORKS;
                        break;
                    case SortType.STARS:
                        sortType = SortType.STARS;
                        break;
                    case SortType.UPDATED:
                        sortType = SortType.UPDATED;
                        break;
                    case "best match":
                    default:
                        sortType = SortType.BEST_MATCH;
                        break;
                }

                binding.sortByBestMatch.setTypeface( null, Typeface.NORMAL );
                binding.sortByForks.setTypeface( null, Typeface.NORMAL );
                binding.sortByStars.setTypeface( null, Typeface.NORMAL );
                binding.sortByUpdated.setTypeface( null, Typeface.NORMAL );

                ( (TextView) view ).setTypeface( null, Typeface.BOLD );
                getData( lastQuery, sortType, orderType );
            }
        };

        binding.sortByBestMatch.setOnClickListener( onSortByClickListener );
        binding.sortByForks.setOnClickListener( onSortByClickListener );
        binding.sortByStars.setOnClickListener( onSortByClickListener );
        binding.sortByUpdated.setOnClickListener( onSortByClickListener );
    }

    private void getData( String query, @SortType String sortType, @OrderType String orderType ) {

        Call<SearchRepositoriesResponse> searchRepositoriesResponseCall = ApiManager.getInstance().getService().searchRepositories( query, sortType, orderType );
        searchRepositoriesResponseCall.enqueue( new Callback<SearchRepositoriesResponse>() {
            @Override
            public void onResponse( Call<SearchRepositoriesResponse> call, Response<SearchRepositoriesResponse> response ) {
                if( response != null ) {
                    if( response.isSuccessful() ) {
                        if( response.body() != null ) {
                            SearchRepositoriesResponse data = response.body();
                            populateData( data );
                        } else {
                            ApiErrorHandling.handleApiError( response.code(), getString( R.string.error_empty_response ), MainActivity.this );
                        }
                    } else {
                        ApiErrorHandling.handleApiError( response.code(), response.message(), MainActivity.this );
                    }
                }
            }

            @Override
            public void onFailure( Call<SearchRepositoriesResponse> call, Throwable t ) {
                ApiErrorHandling.handleApiFailure( t, MainActivity.this );
            }
        } );
    }

    private void populateData( SearchRepositoriesResponse data ) {
        repositoriesAdapter.switchAllData( data.getSearchUserModels() );
    }

}
