package vuki.com.undagit.annotations;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static vuki.com.undagit.annotations.SortType.BEST_MATCH;
import static vuki.com.undagit.annotations.SortType.FORKS;
import static vuki.com.undagit.annotations.SortType.STARS;
import static vuki.com.undagit.annotations.SortType.UPDATED;

/**
 * Created by mvukosav on 9.1.2017..
 * Copyright by @ UndaGit
 */
@StringDef({
        STARS,
        FORKS,
        UPDATED,
        BEST_MATCH
})

@Retention(RetentionPolicy.SOURCE)
public @interface SortType {
    String STARS = "start";
    String FORKS = "forks";
    String UPDATED = "updated";
    String BEST_MATCH = "";
}
