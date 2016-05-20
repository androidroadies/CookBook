
package com.c4n.cookbook;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.c4n.cookbook.tabsample.Albumartloader;

public class ItemDetail extends Activity {

		Albumartloader _albumLoader;

		ArrayList<HashMap<String, String>> contactList;
		ListView list;
		private static final String TAG_NAME = "food_title";
		private static final String image = "food_image_url";
		private static final String desc = "food_desc";

		private static String url = "http://care4nature.org/food/test.php";
		JSONArray contacts = null;

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
					WindowManager.LayoutParams.FLAG_FULLSCREEN);
			setContentView(R.layout.about);

			list = (ListView) findViewById(R.id.list1);

			_albumLoader = new Albumartloader(ItemDetail.this, 100);
			JSOnParser jParser = new JSOnParser();
			contactList = new ArrayList<HashMap<String, String>>();
			JSONArray json = jParser.getJsonFromURL(url);

			try {
				for (int i = 0; i < json.length(); i++) {

					JSONObject c = json.getJSONObject(i);

					String name = c.getString("food_title");
					String img = c.getString("food_image_url");
					String desc = c.getString("food_desc");

					HashMap<String, String> map = new HashMap<String, String>();

					map.put("food_title", name);
					map.put("food_image_url", img);
					map.put("food_state", desc);
					
					contactList.add(map);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			list.setAdapter(new ListAdapter(ItemDetail.this));
		}

		private class ListAdapter extends BaseAdapter {
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

					convertView = inflater.inflate(R.layout.itemdetail, null);

					viewHolder = new ViewHolder();

					viewHolder.title1 = (TextView) convertView
							.findViewById(R.id.txtdetail);
					viewHolder.title = (TextView) convertView
							.findViewById(R.id.txtdescs);

					viewHolder.imageview = (ImageView) convertView
							.findViewById(R.id.detailimg);

					convertView.setTag(viewHolder);
				} else {
					viewHolder = (ViewHolder) convertView.getTag();
				}
				try {

					viewHolder.title1.setText(Html.fromHtml(contactList
							.get(position).get("food_title")));
					
					viewHolder.title.setText(Html.fromHtml(contactList
							.get(position).get("food_desc")));

					viewHolder.imageview.setTag(contactList.get(position).get(
							"food_image_url"));

					_albumLoader.DisplayImage(
							contactList.get(position).get("food_image_url"),
							ItemDetail.this, viewHolder.imageview);


					convertView
							.setOnClickListener(new OnItemClickListener(position));
				} catch (Exception e) {
					// TODO: handle exception
				}
				return convertView;
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
				/*Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(contactList
						.get(mPosition).get("animation_url")));
				startActivity(i);*/
				
				Intent i = new Intent().putExtra(null, contactList.get(mPosition).getClass());
				startActivity(i);
			
			}
		}
		private class ViewHolder {
			TextView title,title1;

			ImageView imageview;

			// Dynamic view

		}

	}
