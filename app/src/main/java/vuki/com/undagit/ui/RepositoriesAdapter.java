package vuki.com.undagit.ui;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import vuki.com.undagit.R;
import vuki.com.undagit.databinding.ModelRepoItemBinding;
import vuki.com.undagit.helpers.ImageLoaderHelper;
import vuki.com.undagit.models.Repository;
import vuki.com.undagit.models.SearchUserModel;

/**
 * Created by mvukosav on 9.1.2017..
 * Copyright by @ UndaGit
 */
public class RepositoriesAdapter extends RecyclerView.Adapter<RepositoriesAdapter.ViewHolder> {

    private List<Repository> data;
    private Context context;

    public RepositoriesAdapter( Context context, List<Repository> data ) {
        this.data = data;
        this.context = context;
    }

    @Override
    public RepositoriesAdapter.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType ) {
        final ModelRepoItemBinding binding = DataBindingUtil.inflate( LayoutInflater.from( parent.getContext() ), R.layout.model_repo_item, parent, false );
        final ViewHolder holder = new ViewHolder( binding );
        View.OnClickListener repoDetailsClickListener = new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                Intent repoDetails = new Intent( context, RepositoryDetailsActivity.class );
                Bundle bundle = new Bundle( 1 );
                bundle.putSerializable( "repo", data.get( holder.getAdapterPosition() ) );
                repoDetails.putExtras( bundle );
                context.startActivity( repoDetails );
            }
        };

        View.OnClickListener owneDetailsrClickListener = new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                Intent ownerDetails = new Intent( context, UserDetailsActivity.class );
                Bundle bundle = new Bundle( 1 );
                bundle.putSerializable( "owner", data.get( holder.getAdapterPosition() ).getOwner().getUsername() );
                ownerDetails.putExtras( bundle );
                context.startActivity( ownerDetails );
            }
        };

        binding.repoName.setOnClickListener( repoDetailsClickListener );
        binding.numOfForksTxt.setOnClickListener( repoDetailsClickListener );
        binding.numOfIssuesTxt.setOnClickListener( repoDetailsClickListener );
        binding.numOfWatchersTxt.setOnClickListener( repoDetailsClickListener );
        binding.authorName.setOnClickListener( owneDetailsrClickListener );
        binding.authorProfileImg.setOnClickListener( owneDetailsrClickListener );

        return holder;
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position ) {
        Repository repository = data.get( position );
        SearchUserModel owner = repository.getOwner();
        holder.binding.repoName.setText( repository.getName() );
        holder.binding.authorName.setText( owner.getUsername() );
        holder.binding.numOfForksTxt.setText( String.valueOf( repository.getNumOfForks() ) );
        holder.binding.numOfWatchersTxt.setText( String.valueOf( repository.getNumOfWatchers() ) );
        holder.binding.numOfIssuesTxt.setText( String.valueOf( repository.getNumOfOpenIssues() ) );
        ImageLoaderHelper.setPicture( holder.binding.authorProfileImg, owner.getAvatarUrl(), context );
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ModelRepoItemBinding binding;

        public ViewHolder( ModelRepoItemBinding binding ) {
            super( binding.getRoot() );
            this.binding = binding;
        }
    }

    public void addMoreItems( List<Repository> moreData ) {
        data.addAll( moreData );
        notifyDataSetChanged();
    }

    public void switchAllData( List<Repository> moreData ) {
        data.clear();
        data.addAll( moreData );
        notifyDataSetChanged();
    }

    public void removeAll(  ) {
        data.clear();
        notifyDataSetChanged();
    }

}
