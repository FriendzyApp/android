package com.friendzy.app.friendzy;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import com.flipkart.chatheads.ui.ChatHead;
import com.flipkart.chatheads.ui.ChatHeadContainer;
import com.flipkart.chatheads.ui.ChatHeadViewAdapter;
import com.flipkart.chatheads.ui.MinimizedArrangement;

import static android.R.attr.key;

public class FriendActivity extends AppCompatActivity {

    private ChatHeadContainer chatContainer;
    private WindowManager wm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_friend);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        setContentView(R.layout.content_friend);
        chatContainer = (ChatHeadContainer) findViewById(R.id.chat_container);
        chatContainer.setViewAdapter(new AppOverlay(this, getSupportFragmentManager()));
        chatContainer.setArrangement(MinimizedArrangement.class, null);


        wm = (WindowManager) getSystemService(WINDOW_SERVICE);

        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                500,
                200,
                WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY,
                WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
                PixelFormat.TRANSLUCENT); //or set to transparent

        params.x = 0;
        params.y = 0;

        params.flags = WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
                | WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED;


        wm.addView(chatContainer, params);

        addChatHead();
    }

    public void addChatHead() {
        chatContainer.addChatHead("head0", false, true);
    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_friend, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    */
}