package com.slack.weeklychallengeone.Utils;

import android.content.Context;
import android.content.ContextWrapper;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.util.Log;
import android.view.View;

import com.slack.weeklychallengeone.R;

public class SwipeControllerActions {

    private Context context;

    protected SwipeControllerActions(Context context) {
        this.context = context;
    }

    public void onLeftClicked(int position) {}

    public void onRightClicked(int position){}

}
