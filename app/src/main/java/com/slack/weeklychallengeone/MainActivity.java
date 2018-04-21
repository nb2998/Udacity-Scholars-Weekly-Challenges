package com.slack.weeklychallengeone;

import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

import com.slack.weeklychallengeone.Utils.Model;
import com.slack.weeklychallengeone.Utils.MyAdapter;
import com.slack.weeklychallengeone.Utils.SwipeController;
import com.slack.weeklychallengeone.Utils.SwipeControllerActions;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Model> mArrayList;
    private MyAdapter mAdapter;
    private RecyclerView mRecyclerView;
    SwipeController swipeController = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        loadData();

    }

    private void initViews() {
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        swipeController = new SwipeController(new SwipeControllerActions(getApplicationContext()) {
            @Override
            public void onRightClicked(int position) {
                Log.d("TAG", "onRightClicked: ");
                String url;
                switch (position) {

                    case 0:
                        url = "https://developer.android.com/reference/android/support/constraint/ConstraintLayout.html";
                        break;
                    case 1:
                        url = "https://developer.android.com/reference/android/widget/LinearLayout.html";
                        break;
                    case 2:
                        url = "https://developer.android.com/reference/android/widget/RelativeLayout.html";
                        break;
                    case 3:
                        url = "https://developer.android.com/reference/android/support/v7/widget/CardView.html";
                        break;
                    case 4:
                        url = "https://developer.android.com/reference/android/widget/ScrollView.html";
                        break;
                    case 5:
                        url = "https://developer.android.com/reference/android/widget/GridView.html";
                        break;
                    default:
                        url = "://developer.android.com";
                }
                redirectUsingCustomTab(url);
            }
        });
        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeController);
        itemTouchhelper.attachToRecyclerView(mRecyclerView);
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                swipeController.onDraw(c);
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
    }

    public void redirectUsingCustomTab(String url) {
        Log.d("TAG", "redirectUsingCustomTab: "+url);
        Uri uri = Uri.parse(url);
        CustomTabsIntent.Builder intentBuilder = new CustomTabsIntent.Builder();

        intentBuilder.setToolbarColor(getResources().getColor(R.color.colorPrimary));
        intentBuilder.setSecondaryToolbarColor(getResources().getColor(R.color.colorPrimaryDark));

        intentBuilder.setStartAnimations(this, android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        intentBuilder.setExitAnimations(this, android.R.anim.slide_in_left,
                android.R.anim.slide_out_right);

        CustomTabsIntent customTabsIntent = intentBuilder.build();
        customTabsIntent.launchUrl(this, uri);
    }

    private void loadData() {

        mArrayList = new ArrayList<>();

        mArrayList.add(new Model("Constraint Layout", getString(R.string.info_constraint_layout)));
        mArrayList.add(new Model("Linear Layout", getString(R.string.info_linear_layout)));
        mArrayList.add(new Model("Relative Layout", getString(R.string.info_relative_layout)));
        mArrayList.add(new Model("Card View", getString(R.string.info_card_view)));
        mArrayList.add(new Model("Scroll View", getString(R.string.info_scroll_view)));
        mArrayList.add(new Model("Grid View", getString(R.string.info_grid_view)));

        mAdapter = new MyAdapter(mArrayList, this);
        mRecyclerView.setAdapter(mAdapter);

    }


}
