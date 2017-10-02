package com.example.chirag.networkdemo;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

/**
 * Created by Chirag on 01-10-2017.
 */

public class CustomAdapter extends ArrayAdapter<Post> {
    public CustomAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }
}
