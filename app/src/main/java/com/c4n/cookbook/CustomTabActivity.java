package com.c4n.cookbook;
/*package com.example.cookbook;

import android.app.Activity;
import android.app.LocalActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.widget.Toast;

public class CustomTabActivity extends Activity {

	public static TabHost mTabHost;
	public static View tablayout;

	private void setupTabHost() {
		mTabHost = (TabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup();

	}

	*//** Called when the activity is first created. *//*
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// construct the tabhost
		setContentView(R.layout.main);

		//tablayout = findViewById(R.id.);

		setupTabHost();
	//	mTabHost.getTabWidget().setDividerDrawable(R.drawable.tab_divider);

		LocalActivityManager mLocalActivityManager = new LocalActivityManager(
				this, false);
		mLocalActivityManager.dispatchCreate(savedInstanceState);
		mTabHost.setup(mLocalActivityManager);

		Intent intent;
		intent = new Intent().setClass(this, GroupHome.class);

		setupTab(new TextView(this), getResources().getString(R.string.home),
				intent, R.drawable.tab_home);

		intent = new Intent().setClass(this, GroupSearch.class);
		setupTab(new TextView(this), getResources().getString(R.string.search),
				intent, R.drawable.tab_bg_selector_search);

		intent = new Intent().setClass(this, GroupCart.class);
		setupTab(new TextView(this), getResources().getString(R.string.cart),
				intent, R.drawable.tab_bg_selector_cart);

		intent = new Intent().setClass(this, GroupAccount.class);
		setupTab(new TextView(this),
				getResources().getString(R.string.account), intent,
				R.drawable.tab_bg_selector_account);

		intent = new Intent().setClass(this, GroupOrder.class);
		setupTab(new TextView(this), getResources().getString(R.string.orders),
				intent, R.drawable.tab_bg_selector_more);

		mTabHost.setCurrentTab(0);

		mTabHost.setOnTabChangedListener(new OnTabChangeListener() {

			@Override
			public void onTabChanged(String tabId) {
				// TODO Auto-generated method stub

				if (getResources().getString(R.string.home).equals(tabId)) {

					CategoryListScreen bActivity = (CategoryListScreen) GroupHome.group
							.getLocalActivityManager().getActivity(
									"CategoryListActivity");

					Activity currentactivity = GroupHome.group
							.getCurrentActivity();

					if (currentactivity.getLocalClassName().equalsIgnoreCase(
							"CategoryListScreen")) {

						bActivity.onResume();

					}

					if (bActivity == null) {
						Toast.makeText(getApplicationContext(),
								"CategoryListActivity NULL", Toast.LENGTH_LONG)
								.show();
						return;
					}

				}

				if (getResources().getString(R.string.cart).equals(tabId)) {

					CartChechoutScreen bActivity = (CartChechoutScreen) GroupCart.group
							.getLocalActivityManager().getActivity(
									"CartChechoutActivity");

					if (bActivity == null) {

						Toast.makeText(getApplicationContext(), "NUll",
								Toast.LENGTH_LONG);
						return;
					} else {
						bActivity.onResume();
					}

					Activity currentactivity = GroupCart.group
							.getCurrentActivity();

					if (currentactivity.getLocalClassName().equalsIgnoreCase(
							"CartChechoutScreen")
							&& bActivity != null) {

					}

				}
				if (getResources().getString(R.string.account).equals(tabId)) {

					// Start the root activity withing the group and get its
					// view

					AccountInfoScreen bActivity = (AccountInfoScreen) GroupAccount.group
							.getLocalActivityManager().getActivity(
									"AccountInfoActivity");

					if (bActivity == null) {

						return;
					}

					Activity currentactivity = GroupAccount.group
							.getCurrentActivity();

					if (currentactivity.getLocalClassName().equalsIgnoreCase(
							"AccountInfoActivity")
							&& bActivity != null) {

						bActivity.onResume();

					}

				}

				if (getResources().getString(R.string.orders).equals(tabId)) {

					if (new CommonData(getApplicationContext()).get_UserID()
							.equals("0")) {

						

						GroupOrder.history.clear();

						Intent intent = new Intent(CustomTabActivity.this,
								CartLoginScreen.class);
						intent.putExtra("from", "order");

						View view = GroupOrder.group
								.getLocalActivityManager()
								.startActivity(
										"CartLoginActivity",
										intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
								.getDecorView();

						// Again, replace the view
						GroupOrder.group.replaceView(view);

					} else {

						MyAccountInfoOrder bActivity = (MyAccountInfoOrder) GroupOrder.group
								.getLocalActivityManager().getActivity(
										"MyAccountInfoOrder");

					

						if (bActivity != null) {
							bActivity.onResume();

						} else {

							View view = GroupOrder.group
									.getLocalActivityManager()
									.startActivity(
											"MyAccountInfoOrder",
											new Intent(CustomTabActivity.this,
													MyAccountInfoOrder.class)
													.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
									.getDecorView();

							// Replace the view of this ActivityGroup
							GroupOrder.group.replaceView(view);
						}
					}

				}

			}
		});

	}

	private void setupTab(final View view, final String tag, Intent intent,
			int id) {

		View tabview = createTabView(mTabHost.getContext(), tag, id);

		TabSpec setContent = mTabHost.newTabSpec(tag).setIndicator(tabview)
				.setContent(intent);
		mTabHost.addTab(setContent);

	}

	public static TextView tv_counter;

	private static View createTabView(final Context context, final String text,
			final int id) {

		View view = LayoutInflater.from(context).inflate(R.layout.tab_bg, null);
		TextView tv = (TextView) view.findViewById(R.id.txt_tab_name);
		ImageView img = (ImageView) view.findViewById(R.id.img_tab_image);

		tv.setText(text);
		img.setBackgroundResource(id);

		if (R.drawable.tab_bg_selector_cart == id) {
			tv_counter = (TextView) view.findViewById(R.id.txtredcoupon);
			tv_counter.setVisibility(View.VISIBLE);

			int cnt = new DatabaseHelper(
					CustomTabActivity.mTabHost.getContext()).getCartItems()
					.size();
			CustomTabActivity.tv_counter.setText(String.valueOf(cnt));

			if (cnt > 0) {
				CustomTabActivity.tv_counter.setVisibility(View.VISIBLE);
			} else {
				CustomTabActivity.tv_counter.setVisibility(View.GONE);
			}

		}

		return view;
	}
}*/