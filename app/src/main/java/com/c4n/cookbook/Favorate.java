package com.c4n.cookbook;

import java.util.ArrayList;

import android.app.ActivityGroup;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Favorate extends ActivityGroup {

	DatabaseHelper db;
	ListAdapter adapter;
	ListView list_favorite;
	String id, name, state, desc, direction, url;

	// String id, name, desc, direction,url;

	ArrayList<Item> _items = new ArrayList<Item>();

	/*@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}*/

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.favorate);

		db = new DatabaseHelper(Favorate.this);

		_items = db.getCartItems();

		list_favorite = (ListView) findViewById(R.id.list_favorite);

		adapter = new ListAdapter(Favorate.this);
		list_favorite.setAdapter(adapter);

		Toast.makeText(Favorate.this, "Total favorites :" + _items.size(),
				Toast.LENGTH_LONG).show();

		for (int i = 0; i < _items.size(); i++) {

			
		}

	}

	class ListAdapter extends BaseAdapter {
		LayoutInflater inflater;
		ViewHolder viewHolder;

		public ListAdapter(Context context) {
			inflater = LayoutInflater.from(context);

		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return _items.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return arg0;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return arg0;
		}

		@Override
		public View getView(final int position, View convertView,
				ViewGroup parent) {
			if (convertView == null) {

				convertView = inflater.inflate(R.layout.favorite_row, null);

				viewHolder = new ViewHolder();

				viewHolder.title = (TextView) convertView
						.findViewById(R.id.txt_favview);

				viewHolder.btn = (Button) convertView
						.findViewById(R.id.btndelete);

				convertView.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder) convertView.getTag();
			}
			try {

				// Log.i(">>name>>", ">>>>" + _items.get(position).name);
				viewHolder.title.setText(_items.get(position).name);

				viewHolder.btn.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub

						int id = Integer.parseInt(_items.get(position).id);
						db.removeProductFromCart(id);
						my_oncreate();
						db.close();
					}
				});

				convertView.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						System.out.println("in id click >>>");

						/*
						 * String name, desc, direction, state, url, id;
						 * 
						 * id = Favorate.this.id; name = Favorate.this.name;
						 * state = Favorate.this.state; desc =
						 * Favorate.this.desc; direction =
						 * Favorate.this.direction; url = Favorate.this.url;
						 */

						id = _items.get(position).id;
						name = _items.get(position).name;
						desc = _items.get(position).desc;
						state = _items.get(position).state;
						direction = _items.get(position).direction;
						url = _items.get(position).url;
						System.out.println("direction>>>" + direction);

						Intent i = new Intent(getParent(), Details.class);
						Bundle bundle = new Bundle();

						//
						bundle.putString("food_id", id);
						bundle.putString("food_title", name);
						bundle.putString("food_desc", desc);

						bundle.putString("direction", direction);
						bundle.putString("food_image_url", url);

						i.putExtras(bundle);
						// startActivity(i);

						// Intent inn = new Intent(getParent(),
						// ListItemName.class);
						/*
						 * Bundle bundle = new Bundle(); bundle.putString("id",
						 * id); bundle.putString("catname", catname);
						 * inn.putExtras(bundle);
						 */
						//
						WorkoutTabGroupActivity parantActivity = (WorkoutTabGroupActivity) getParent();
						parantActivity.startChildActivity("Details", i);

					}
					
				});
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			return convertView;
		}

		private class ViewHolder {
			TextView title, title1;
			Button btn;

			// Dynamic view

		}

	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		finish();
		super.onBackPressed();
		
	}

	public void my_oncreate() {

		setContentView(R.layout.favorate);

		db = new DatabaseHelper(Favorate.this);

		_items = db.getCartItems();

		list_favorite = (ListView) findViewById(R.id.list_favorite);

		adapter = new ListAdapter(Favorate.this);
		list_favorite.setAdapter(adapter);

		Toast.makeText(Favorate.this, "Total favorites :" + _items.size(),
				Toast.LENGTH_LONG).show();

		for (int i = 0; i < _items.size(); i++) {
			Log.i(">> Item :" + i, ">>" + _items.get(i).name);
		}
	}

}
