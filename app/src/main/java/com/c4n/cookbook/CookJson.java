package com.c4n.cookbook;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActivityGroup;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.mayank.pojo.ImageLoader;

public class CookJson extends ActivityGroup {

//	Albumartloader imaAlbumartloader;
	ImageLoader imgImageLoader;

	private ProgressDialog pDialog;
	ArrayList<HashMap<String, String>> contactList;

	GridView list;
	private static final String TAG_NAME = "cat_name";
	private static final String image = "cat_image_url";
	private static final String ID = "cat_id";

	// private static final String urlanimation = "animation_url";

	private static String url = "http://thecompleteafricanrecipe.com/test.php";
	JSONArray contacts = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.gridmain);
		isNetworkConnected();
		list = (GridView) findViewById(R.id.list);
		new task().execute();
	}

	private boolean isNetworkConnected() {
		  ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		  NetworkInfo ni = cm.getActiveNetworkInfo();
		  if (ni == null) {
		   // There are no active networks.
		   return false;
		  } else
		   return true;
		 }
	class task extends AsyncTask<String, String, Void> {
		int success;

		protected void onPreExecute() {
			super.onPreExecute();
			
			pDialog = ProgressDialog.show(getParent(),"", "Please wait...",true);
		//	pDialog = new ProgressDialog(CookJson.this);
		//	pDialog.setIndeterminate(false);
		//	pDialog.setCancelable(true);
			pDialog.show();

		}

		@Override
		protected Void doInBackground(String... params) {
			// TODO Auto-generated method stub
			imgImageLoader = new ImageLoader(CookJson.this);
			JSOnParser jParser = new JSOnParser();
			contactList = new ArrayList<HashMap<String, String>>();
			JSONArray json = jParser.getJsonFromURL(url);

			try {

				for (int i = 0; i < json.length(); i++) {

					JSONObject c = json.getJSONObject(i);

					String name = c.getString("cat_name");
					String img = c.getString("cat_image_url");
					String id = c.getString("cat_id");

					// String urlanimation = c.getString("animation_url");

					HashMap<String, String> map = new HashMap<String, String>();

					map.put("cat_name", name);
					map.put("cat_image_url", img);
					map.put("cat_id", id);

					// map.put("animation_url", urlanimation);

					contactList.add(map);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
		protected void onPostExecute(Void v) {
			list.setAdapter(new ListAdapter(CookJson.this));
			pDialog.dismiss();
		}
	}

	// ==================new tab code
	public void onResume() {
		super.onResume();

		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		// getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		// WindowManager.LayoutParams.FLAG_FULLSCREEN);
		// setContentView(R.layout.gridmain);
		//
		// list = (GridView) findViewById(R.id.list);
		// new task().execute();

	}

	// ==================new tab code
	class ListAdapter extends BaseAdapter {
		LayoutInflater inflater;
		ViewHolder viewHolder;

		public ListAdapter(Context context) {
			inflater = LayoutInflater.from(context);

		}

		public int getCount() {
			// TODO Auto-generated method stub
			return contactList.size();
		}

		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {

				convertView = inflater.inflate(R.layout.list_item, null);

				viewHolder = new ViewHolder();

				viewHolder.title = (TextView) convertView
						.findViewById(R.id.name);

				viewHolder.imageview = (ImageView) convertView
						.findViewById(R.id.imgview);

				convertView.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder) convertView.getTag();
			}
			try {

				viewHolder.title.setText(Html.fromHtml(contactList
						.get(position).get("cat_name")));

				viewHolder.imageview.setTag(contactList.get(position).get(
						"cat_image_url"));

				imgImageLoader.DisplayImage(
						contactList.get(position).get("cat_image_url"),
						CookJson.this, viewHolder.imageview);

				convertView
						.setOnClickListener(new OnItemClickListener(position));
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			return convertView;
		}

		private class ViewHolder {
			TextView title;

			ImageView imageview;

			// Dynamic view

		}
	}
	private class OnItemClickListener implements OnClickListener {
		private int mPosition;

		OnItemClickListener(int position) {
			mPosition = position;
		}

		public void onClick(View v) {

			if (contactList == null)
				return;
			if (contactList.size() < mPosition + 1)
				return;

			String id = String.valueOf((Html.fromHtml(contactList
					.get(mPosition).get(ID))));

			String catname = String.valueOf((Html.fromHtml(contactList.get(
					mPosition).get("cat_name"))));

			// Intent i = new Intent(getParent(), ListItemName.class);
			// Bundle bundle = new Bundle();
			// bundle.putString("id", id);
			// bundle.putString("catname", catname);
			// i.putExtras(bundle);
			//
			//
			// WorkoutTabGroupActivity parantActivity =
			// (WorkoutTabGroupActivity) getParent();
			// parantActivity.startChildActivity("ListItemName", i);

			Intent inn = new Intent(getParent(), ListItemName.class);
			Bundle bundle = new Bundle();
			bundle.putString("id", id);
			bundle.putString("catname", catname);
			inn.putExtras(bundle);
			
			WorkoutTabGroupActivity parantActivity = (WorkoutTabGroupActivity) getParent();
			parantActivity.startChildActivity("ListItemName", inn);

			// WorkoutTabGroupActivity parantActivity =
			// (WorkoutTabGroupActivity) getParent();
			// parantActivity.startChildActivity("ListItemName", inn);

		}
	}
}
