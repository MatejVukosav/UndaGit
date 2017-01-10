package vuki.com.undagit.annotations;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static vuki.com.undagit.annotations.OrderType.ASC;
import static vuki.com.undagit.annotations.OrderType.DESC;

/**
 * Created by mvukosav on 9.1.2017..
 * Copyright by @ UndaGit
 */

@StringDef({
        ASC,
        DESC
})

@Retention(RetentionPolicy.SOURCE)
public @interface OrderType {
    String ASC = "asc";
    String DESC = "desc";
}
