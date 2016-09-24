package com.friendzy.app.friendzy;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;

import com.flipkart.chatheads.ui.ChatHead;
import com.flipkart.chatheads.ui.ChatHeadViewAdapter;

/**
 * Created by ashleyn on 9/24/16.
 */

public class AppOverlay implements ChatHeadViewAdapter {
    private Activity activity;
    private FragmentManager fragmentManager;

    public AppOverlay(Activity activity, FragmentManager fragmentManager){
        this.activity = activity;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public FragmentManager getFragmentManager() {
//        return activity.getSupportFragmentManager();
        return fragmentManager;
    }

    @Override
    public Fragment instantiateFragment(Object key, ChatHead chatHead) {
        // return the fragment which should be shown when the arrangment switches to maximized (on clicking a chat head)
        // you can use the key parameter to get back the object you passed in the addChatHead method.
        // this key should be used to decide which fragment to show.
        return new Fragment();
    }

    @Override
    public View getTitleView(Object key, ChatHead chatHead) {
        return null;
    }

    @Override
    public Drawable getChatHeadDrawable(Object key) {
        // this is where you return a drawable for the chat head itself based on the key. Typically you return a circular shape
        // you may want to checkout circular image library https://github.com/flipkart-incubator/circular-image
        return activity.getResources().getDrawable(R.color.colorPrimary);
    }

    @Override
    public Drawable getPointerDrawable() {
        return activity.getResources().getDrawable(R.drawable.chat_top_arrow);
    }
}