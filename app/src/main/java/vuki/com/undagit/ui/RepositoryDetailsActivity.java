package vuki.com.undagit.ui;

import android.content.Intent;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableRow;
import android.widget.TextView;

import vuki.com.undagit.R;
import vuki.com.undagit.databinding.ActivityRepositoryDetailsBinding;
import vuki.com.undagit.databinding.ModelItemRowBinding;
import vuki.com.undagit.helpers.ImageLoaderHelper;
import vuki.com.undagit.models.Repository;

/**
 * Created by mvukosav on 9.1.2017..
 * Copyright by @ UndaGit
 */
public class RepositoryDetailsActivity extends AppCompatActivity {

    private ActivityRepositoryDetailsBinding binding;
    private Resources resources;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        binding = DataBindingUtil.setContentView( this, R.layout.activity_repository_details );
        resources = getResources();

        Bundle b = getIntent().getExtras();
        if( b != null ) {
            Repository repository = (Repository) b.getSerializable( "repo" );
            populateData( repository );
            binding.toolbar.setTitle( repository.getName() );
            init( repository );
        }
        setSupportActionBar( binding.toolbar );
        final ActionBar actionBar = getSupportActionBar();
        if( actionBar != null ) {
            actionBar.setDisplayHomeAsUpEnabled( true );
        }
    }

    private void init( final Repository repository ) {
        binding.openInBrowserBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                startActivity( new Intent( Intent.ACTION_VIEW, Uri.parse( repository.getHtml_url() ) ) );
            }
        } );
    }

    private void populateData( Repository repository ) {
        ImageLoaderHelper.setPicture( binding.userProfileImg, repository.getOwner().getAvatarUrl(), this );

        addDataToTable( resources.getString( R.string.owner ), repository.getOwner().getUsername() );
        addDataToTable( resources.getString( R.string.repository_name ), repository.getName() );
        addDataToTable( resources.getString( R.string.created_at ), repository.getCreatedAt() );
        addDataToTable( resources.getString( R.string.pushed_at ), repository.getPushedAt() );
        addDataToTable( resources.getString( R.string.last_update_at ), repository.getUpdatedAt() );
        addDataToTable( resources.getString( R.string.followers ), String.valueOf( repository.getNumOfForks() ) );
        addDataToTable( resources.getString( R.string.num_of_start ), String.valueOf( repository.getNumOfStars() ) );
        addDataToTable( resources.getString( R.string.num_of_watchers ), String.valueOf( repository.getNumOfWatchers() ) );
        addDataToTable( resources.getString( R.string.num_of_open_issues ), String.valueOf( repository.getNumOfOpenIssues() ) );

    }

    private void addDataToTable( String key, Object object ) {
        if( object != null ) {
            addDataToTable( key, object.toString() );
        }
    }

    private void addDataToTable( String key, String value ) {
        if( !value.isEmpty() ) {
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
