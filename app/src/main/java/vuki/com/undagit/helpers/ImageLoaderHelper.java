package vuki.com.undagit.helpers;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import vuki.com.undagit.R;

/**
 * Created by mvukosav on 9.1.2017..
 * Copyright by @ UndaGit
 */
public class ImageLoaderHelper {

    public static void setPicture( final ImageView holder, String url, Context context ) {
        if( holder == null || url == null || url.isEmpty() ) {
            return;
        }
        setWithGlide( holder, url, context );
    }

    private static void setWithGlide( final ImageView holder, String url, Context context ) {
        try {
            Glide.with( context )
                    .load( url )
                    .error( ContextCompat.getDrawable( context, R.drawable.gith_spinner ) )
                    .diskCacheStrategy( DiskCacheStrategy.RESULT )
                    .sizeMultiplier( 0.5f )
                    .into( holder );
        } catch( IllegalArgumentException ila ) {
            ila.printStackTrace();
        }
    }
}
