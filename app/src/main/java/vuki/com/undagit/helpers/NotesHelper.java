package vuki.com.undagit.helpers;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import vuki.com.undagit.BuildConfig;

/**
 * Created by mvukosav on 9.1.2017..
 * Copyright by @ UndaGit
 */
public class NotesHelper {
    public static void logMessage( String tag, String body ) {
        if( BuildConfig.DEBUG ) {
            if( body != null ) {
                Log.d( tag, body );
            } else {
                Log.d( tag, "NULL" );
            }
        }

    }

    public static void toastMessage( Context context, String body ) {
        Toast toast = Toast.makeText( context, body, Toast.LENGTH_SHORT );
        toast.setGravity( Gravity.CENTER, 0, 0 );
        toast.show();
    }

    public static void snackbarMessageShort( View v, String message ) {
        try {
            Snackbar snackbar = Snackbar.make( v, message, Snackbar.LENGTH_SHORT );
            View sbView = snackbar.getView();
            TextView textView = (TextView) sbView.findViewById( android.support.design.R.id.snackbar_text );
            textView.setTextColor( Color.YELLOW );
            snackbar.show();
        } catch( NullPointerException n ) {
            n.printStackTrace();
        }
    }

    public static AlertDialog.Builder dialogMessage( Context context, String title, String message ) {
        return new AlertDialog.Builder( context )
                .setTitle( title )
                .setMessage( message )
                .setIcon( android.R.drawable.ic_dialog_alert );
    }

}
