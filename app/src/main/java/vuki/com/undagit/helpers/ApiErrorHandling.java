package vuki.com.undagit.helpers;

import android.content.Context;

import java.util.Locale;

/**
 * Created by mvukosav on 9.1.2017..
 * Copyright by @ UndaGit
 */
public class ApiErrorHandling {

    private static final String TAG = ApiErrorHandling.class.getCanonicalName();

    public static void handleApiFailure( Throwable t, Context context ) {
        String errorMsg = String.format( Locale.getDefault(), "Ok Cause: %s ,message: %s , localized message: %s", t.getCause(), t.getMessage(), t.getLocalizedMessage() );
        NotesHelper.logMessage( TAG, errorMsg );
        t.printStackTrace();
    }

    public static void handleApiError( int code, String msg, Context context ) {
        String errorMsg = String.format( Locale.getDefault(), "Ok Cause code: %d ,message: %s ", code, msg );
        NotesHelper.logMessage( TAG, errorMsg );
    }

}
