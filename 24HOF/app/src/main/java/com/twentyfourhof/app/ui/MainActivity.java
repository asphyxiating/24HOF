package com.twentyfourhof.app.ui;


import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import com.google.inject.Inject;
import com.parse.Parse;
import com.twentyfourhof.app.R;
import com.twentyfourhof.app.coms.ServerComClass;
import com.twentyfourhof.app.ui.events.ItemEventResult;
import roboguice.activity.RoboFragmentActivity;
import roboguice.event.Observes;


public class MainActivity extends RoboFragmentActivity implements ActionBar.TabListener {

    @Inject
    private ServerComClass serverComs;
    private MediaItemAdapter itemAdapter;

    protected void handleItems(@Observes ItemEventResult itemEventResult) {
        itemAdapter.clear();
        itemAdapter.addAll(itemEventResult.getItems());
        itemAdapter.notifyDataSetChanged();
    }

	private ViewPager viewPager;
	private TabsPagerAdapter mAdapter;
	private ActionBar actionBar;

	// Tab titles
	private String[] tabs = { "Home", "Browse", "Profile" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "43P0a4QuZcCnmepEPYAnmSBXtWo7Gt01oLfSOSmw", "K4CBhkRPuBqXUsjaZMHP5HyEP67QSj45WGZduOPY");
        if (getIntent().getBooleanExtra("isUser", false)) {
            serverComs.saveUser();
        }

		// Initilization
		viewPager = (ViewPager) findViewById(R.id.pager);
		actionBar = getActionBar();
		mAdapter = new TabsPagerAdapter(getSupportFragmentManager());

		viewPager.setAdapter(mAdapter);
		actionBar.setHomeButtonEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayHomeAsUpEnabled(true);

		// Adding Tabs
		for (String tab_name : tabs) {
			actionBar.addTab(actionBar.newTab().setText(tab_name)
					.setTabListener(this));
		}

		/**
		 * on swiping the viewpager make respective tab selected
		 * */
		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// on changing the page
				// make respected tab selected
				actionBar.setSelectedNavigationItem(position);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
        serverComs.getAllItems();
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// on tab selected
		// show respected fragment view
		viewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        super.onOptionsItemSelected(item);

        switch(item.getItemId()){

            case R.id.uploadpic:
                Toast.makeText(getBaseContext(), "You selected Picture", Toast.LENGTH_SHORT).show();
                break;

            case R.id.uploadvideo:
                Toast.makeText(getBaseContext(), "You selected Video", Toast.LENGTH_SHORT).show();
                break;

            case R.id.uploadtext:
                Intent intObj = new Intent(MainActivity.this,
                        AddItemActivity.class);
                startActivity(intObj);
                break;

            case R.id.uploadsong:
                Toast.makeText(getBaseContext(), "You selected Song", Toast.LENGTH_SHORT).show();
                break;

        }
        return true;

    }
}
