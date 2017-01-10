package vuki.com.undagit.ui;

import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.TableRow;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vuki.com.undagit.R;
import vuki.com.undagit.databinding.ActivityUserDetailsBinding;
import vuki.com.undagit.databinding.ModelItemRowBinding;
import vuki.com.undagit.helpers.ApiErrorHandling;
import vuki.com.undagit.helpers.ImageLoaderHelper;
import vuki.com.undagit.models.User;
import vuki.com.undagit.network.ApiManager;

/**
 * Created by mvukosav on 9.1.2017..
 * Copyright by @ UndaGit
 */
public class UserDetailsActivity extends AppCompatActivity {

    private ActivityUserDetailsBinding binding;
    private Resources resources;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        binding = DataBindingUtil.setContentView( this, R.layout.activity_user_details );
        resources = getResources();

        Bundle b = getIntent().getExtras();
        if( b != null ) {
            String owner = b.getString( "owner" );
            getData( owner );

            binding.toolbar.setTitle( owner );
        }
        setSupportActionBar( binding.toolbar );
        final ActionBar actionBar = getSupportActionBar();
        if( actionBar != null ) {
            actionBar.setDisplayHomeAsUpEnabled( true );
        }
    }

    private void getData( String owner ) {

        Call<User> searchRepositoriesResponseCall = ApiManager.getInstance().getService().getUserData( owner );
        searchRepositoriesResponseCall.enqueue( new Callback<User>() {
            @Override
            public void onResponse( Call<User> call, Response<User> response ) {
                if( response != null ) {
                    if( response.isSuccessful() ) {
                        if( response.body() != null ) {
                            User user = response.body();
                            populateData( user );
                        } else {
                            ApiErrorHandling.handleApiError( response.code(), getString( R.string.error_empty_response ), UserDetailsActivity.this );
                        }
                    } else {
                        ApiErrorHandling.handleApiError( response.code(), response.message(), UserDetailsActivity.this );
                    }
                }
            }

            @Override
            public void onFailure( Call<User> call, Throwable t ) {
                ApiErrorHandling.handleApiFailure( t, UserDetailsActivity.this );
            }
        } );
    }

    private void populateData( User user ) {
        ImageLoaderHelper.setPicture( binding.userProfileImg, user.getAvatarUrl(), this );

        addDataToTable( resources.getString( R.string.username ), user.getUsername() );
        addDataToTable( resources.getString( R.string.email ), user.getEmail() );
        addDataToTable( resources.getString( R.string.biography ), user.getBio() );
        addDataToTable( resources.getString( R.string.location ), user.getLocation() );
        addDataToTable( resources.getString( R.string.company ), user.getCompany() );
        addDataToTable( resources.getString( R.string.followers ), String.valueOf( user.getNumOfFollowers() ) );
        addDataToTable( resources.getString( R.string.following ), String.valueOf( user.getNumOfFollowing() ) );
        addDataToTable( resources.getString( R.string.num_of_public_repos ), String.valueOf( user.getNumOfPublicRepos() ) );
        addDataToTable( resources.getString( R.string.num_of_public_gists ), String.valueOf( user.getNumOfPublicGists() ) );

    }

    private void addDataToTable( String key, Object object ) {
        if( object != null ) {
            addDataToTable( key, object.toString() );
        }
    }

    private void addDataToTable( String key, String value ) {

        if( !TextUtils.isEmpty( value ) ) {
            ModelItemRowBinding tableRowBinding = DataBindingUtil.inflate( this.getLayoutInflater(), R.layout.model_item_row, binding.table, false );
            TableRow tableRow = tableRowBinding.tableRow;
            ( (TextView) tableRow.findViewById( R.id.table_key ) ).setText( key );
            ( (TextView) tableRow.findViewById( R.id.table_value ) ).setText( value );
            binding.table.addView( tableRow );
        }
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item ) {
        switch( item.getItemId() ) {
            case android.R.id.home:
                finish();
                getBack();
                return true;
            default:
                return super.onOptionsItemSelected( item );
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        getBack();
    }

    private void getBack() {
        overridePendingTransition( R.anim.animation_enter, R.anim.animation_leave );
    }
}
