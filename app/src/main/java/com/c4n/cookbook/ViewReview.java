package com.c4n.cookbook;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ActivityGroup;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

public class ViewReview extends ActivityGroup {
	
	TextView productname;
	//String name;

	private ProgressDialog pDialog;
	ArrayList<HashMap<String, String>> contactList;

	ListView list;
	private static final String ID = "id";
	private static final String COMMENT = "comment";
	private static final String RATING = "rating";
	private static final String NAME = "name";

	private static final String Food_title = "food_title";
	
	// private static final String urlanimation = "animation_url";

	private static String url = "http://thecompleteafricanrecipe.com/viewreview.php";
	JSONArray contacts = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

//		requestWindowFeature(Window.FEATURE_NO_TITLE);
//		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.viewreview);
		
		//Intent getdIntent = getIntent();
		
		
		productname=(TextView) findViewById(R.id.txtproductnameview);

		//name = getIntent().getExtras().getString("food_title");
	//	System.out.println("nam>>>>"+name);
//		productname.setText(name);
		
		list = (ListView) findViewById(R.id.reviewlist);
		new task().execute();
	}

	class task extends AsyncTask<String, String, Void> {
		int success;

		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(ViewReview.this);
			pDialog.setMessage("Loading...");
			pDialog.setIndeterminate(false);
			//pDialog.setCancelable(true);
			pDialog.show();
		}

		@Override
		protected Void doInBackground(String... params) {
			// TODO Auto-generated method stub
			JSOnParser jParser = new JSOnParser();
			contactList = new ArrayList<HashMap<String, String>>();
			JSONArray json = jParser.getJsonFromURL(url);

			try {

				for (int i = 0; i < json.length(); i++) {

					JSONObject c = json.getJSONObject(i);

					String id = c.getString(ID);
					String rating = c.getString(RATING);
					String name = c.getString(NAME);
					String comment = c.getString(COMMENT);
					String foodtitle = c.getString(Food_title);

					/*String r = rating.toString();
					Float f = Float.parseFloat(r);*/

					HashMap<String, String> map = new HashMap<String, String>();

					map.put(ID, id);
					map.put(RATING, rating);
					map.put(NAME, name);
					map.put(COMMENT, comment);
					map.put(Food_title,foodtitle);
					
					// map.put("animation_url", urlanimation);

				
					contactList.add(map);
					//System.out.println(">>>>>>>>>>>>>>>>>>>>>>"+contactList);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		protected void onPostExecute(Void v) {
			list.setAdapter(new ListAdapter(ViewReview.this));
			pDialog.dismiss();
		}
	}

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

				convertView = inflater.inflate(R.layout.view_row, null);

				viewHolder = new ViewHolder();

				viewHolder.title = (TextView) convertView
						.findViewById(R.id.txtviewname);
				viewHolder.title1 = (TextView) convertView
						.findViewById(R.id.txtviewcomment);

				viewHolder.title2 = (TextView) convertView
						.findViewById(R.id.txtproductnameview);

				viewHolder.rate = (RatingBar) convertView
						.findViewById(R.id.viewrating);

				/*
				 * viewHolder.imageview = (ImageView) convertView
				 * .findViewById(R.id.imgview);
				 */

				convertView.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder) convertView.getTag();
			}
			try {

				viewHolder.title.setText(Html.fromHtml(contactList
						.get(position).get(NAME)));
				viewHolder.title1.setText(Html.fromHtml(contactList.get(
						position).get(COMMENT)));
				viewHolder.title2.setText(Html.fromHtml(contactList.get(
						position).get(Food_title)));


				/*
				 * viewHolder.rate.setTag(Html.fromHtml(contactList
				 * .get(position).get(RATING)));
				 */

				//Log.i(">>>>>", ">>>Rating list>>" + RATING);
				viewHolder.rate.setRating(Float.parseFloat(contactList.get(
						position).get(RATING)));

				/*
				 * viewHolder.rate.setRating)Tag(Html.fromHtml(contactList
				 * .get(position).get(RATING)));
				 */
				/*
				 * viewHolder.imageview.setTag(contactList.get(position).get(
				 * "cat_image"));
				 */

				/*
				 * _albumLoader.DisplayImage(
				 * contactList.get(position).get("cat_image"), ViewReview.this,
				 * viewHolder.imageview);
				 */
				convertView
						.setOnClickListener(new OnItemClickListener(position));
			} catch (Exception e) {
				// TODO: handle exception
			}
			return convertView;
		}

		private class ViewHolder {
			TextView title, title1,title2;
			RatingBar rate;

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

			/*
			 * String id = String.valueOf((Html.fromHtml(contactList
			 * .get(mPosition).get(ID))));
			 * 
			 * String catname = String.valueOf((Html.fromHtml(contactList.get(
			 * mPosition).get("cat_name"))));
			 * 
			 * Intent i = new Intent(getApplicationContext(),
			 * ListItemName.class); Bundle bundle = new Bundle();
			 * bundle.putString("id", id); bundle.putString("catname", catname);
			 * i.putExtras(bundle);
			 */

			/*
			 * View view = GroupHome.group .getLocalActivityManager()
			 * .startActivity("ProductListActivity",
			 * i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)) .getDecorView();
			 * 
			 * // Again, replace the view GroupHome.group.replaceView(view);
			 */

			// startActivity(i);

		}
	}
}
