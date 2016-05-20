package com.c4n.cookbook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActivityGroup;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.mayank.jsonparsar.JSONParser;
import com.mayank.pojo.ArticlePojo;

public class ListItemName extends ActivityGroup {

	// Albumartloader _albumLoader;

	static String id;
	static String catname;
	// String catname;
	private ProgressDialog pDialog;
	ArrayList<HashMap<String, String>> contactList;

	// private ArrayList<HashMap<String, String>> contactStrings = contactList;
	ListView list;
	ListAdapter adapter;

	public ArrayList<ArticlePojo> _userInfo = new ArrayList<ArticlePojo>();

	JSONArray user = null;
	JSONParser jsonParser = new JSONParser();

	List<String> listtitle = new ArrayList<String>();
	List<String> listdetail = new ArrayList<String>();
	List<String> listdeSC = new ArrayList<String>();
	List<String> listdirection = new ArrayList<String>();
	List<String> listIMGURL = new ArrayList<String>();
	List<String> listId = new ArrayList<String>();

	public int titlestring1;
	
	public String[] titlestring;
	public String[] detistring;
	public String[] descstring;

	ArrayList<String> searchNames = new ArrayList<String>();
	ArrayList<String> searchcity = new ArrayList<String>();
	ArrayList<String> stateNames = new ArrayList<String>();
	
	public String[] directionstring;
	public String[] imgstring;
	public String[] foodid;

	private static final String TAG_ARTICLE = "article";

	private static final String TAG_NAME = "food_title";
	private static final String TAG_URL = "food_image_url";
	private static final String TAG_ID = "food_id";
	private static final String TAG_DESC = "food_desc";

	private static final String TAG_DIRECTION = "direction";

	private static final String TAG_STATE = "food_state";

	JSONArray contacts = null;

	String u_mid;
	String u_title;
	String u_desc;
	String u_direction;
	String u_imgurl;
	String u_foodid;

	private EditText et;
	// private ArrayList listview_array ;

	// private String listview_array[] = { u_title };
	private ArrayList<String> array_sort = new ArrayList<String>();
	private ArrayList<String> array_sortname = new ArrayList<String>();

	int textlength = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.categorylist);

		list = (ListView) findViewById(R.id.list1);

		Intent getdata = getIntent();

		id = getIntent().getExtras().getString("id");
		catname = getIntent().getExtras().getString("catname");

		TextView txtheading = (TextView) findViewById(R.id.headre);

		txtheading.setText(catname);
		new task().execute();
		// listview_array.add(catname);

		et = (EditText) findViewById(R.id.EditText01);
		// list.setAdapter(new ArrayAdapter<String>(this,
		// android.R.layout.simple_list_item_1, listview_array));

		et.addTextChangedListener(new TextWatcher() {

			public void afterTextChanged(Editable s) {
				// Abstract Method of TextWatcher Interface.
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// Abstract Method of TextWatcher Interface.
			}

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				textlength = et.getText().length();
				array_sort.clear();


				for (int i = 0; i < searchNames.size(); i++) {
					if (textlength <= searchNames.get(i).length()) {
						if (et.getText()
								.toString()
								.equalsIgnoreCase(
										(String) searchNames.get(i)
												.subSequence(0, textlength))) {
							array_sort.add(searchNames.get(i));
							array_sortname.add(searchcity.get(i));

						}
					}
				}
				// list.setAdapter(new ArrayAdapter<String>(ListItemName.this,
				// android.R.layout.simple_list_item_1, array_sort));

				list.setAdapter(new MyListAdapter(ListItemName.this,
						array_sort, array_sortname));

			}
		});
	}

	class task extends AsyncTask<String, String, Void> {
		int success;

		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = ProgressDialog.show(getParent(), "", "Please wait...",true);
			pDialog.show();
		}

		@Override
		protected Void doInBackground(String... params) {

			try {
				String a = "'";
				String url = "http://thecompleteafricanrecipe.com/json_artical1.php?pid="
						+ id;
				JSONObject json = JSONParser.getJSONfromURL(url);

				user = json.getJSONArray(TAG_ARTICLE);
				ArticlePojo getdata = new ArticlePojo();

				for (int i = 0; i < user.length(); i++) {

					JSONObject c = user.getJSONObject(i);

					// Storing each json item in variable
					u_mid = c.getString(TAG_NAME);
					u_title = c.getString(TAG_STATE);
					u_desc = c.getString(TAG_DESC);
					u_direction = c.getString(TAG_DIRECTION);

					u_imgurl = c.getString(TAG_URL);

					u_foodid = c.getString(TAG_ID);

					listdetail.add(u_mid);
					listtitle.add(u_title);
					listdeSC.add(u_desc);

					listdirection.add(u_direction);
					listIMGURL.add(u_imgurl);

					listId.add(u_foodid);

					getdata.setFOOD_NAME(u_mid);
					getdata.setFOOD_STATE(u_title);
					getdata.setFOOD_DESC(u_desc);

					getdata.setFOOD_DIRECTION(u_direction);
					getdata.setFOOD_IMGURL(u_imgurl);
					getdata.setFOOD_ID(u_foodid);

					searchNames.add(u_mid);
					searchcity.add(u_title);

					ArticlePojo setdata = new ArticlePojo();

					setdata.setFOOD_NAME(getdata.getFOOD_NAME());
					setdata.setFOOD_STATE(getdata.getFOOD_STATE());
					setdata.setFOOD_DESC(getdata.getFOOD_DESC());
					setdata.setFOOD_DIRECTION(getdata.getFOOD_DIRECTION());

					setdata.setFOOD_IMGURL(getdata.getFOOD_IMGURL());

					setdata.setFOOD_ID(getdata.getFOOD_ID());

					_userInfo.add(setdata);

				}

				for (int i = 0; i < listtitle.size(); i++) {

					titlestring = listtitle.toArray(new String[i]);
				}
				for (int i = 0; i < listdetail.size(); i++) {

					detistring = listdetail.toArray(new String[i]);
				}
				for (int i = 0; i < listdeSC.size(); i++) {

					descstring = listdeSC.toArray(new String[i]);
				}
				for (int i = 0; i < listdirection.size(); i++) {

					directionstring = listdirection.toArray(new String[i]);
				}
				for (int i = 0; i < listIMGURL.size(); i++) {

					imgstring = listIMGURL.toArray(new String[i]);
				}
				for (int i = 0; i < listId.size(); i++) {

					foodid = listId.toArray(new String[i]);
				}

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			//
			return null;
		}

		protected void onPostExecute(Void v) {
			pDialog.dismiss();
			adapter = new ListAdapter(ListItemName.this);
			list.setAdapter(adapter);

		}
	}

	class MyListAdapter extends BaseAdapter {
		LayoutInflater inflater;
		ViewHolder viewHolder;

		ArrayList<String> _array;
		ArrayList<String> _array1;

		public MyListAdapter(Context context, ArrayList<String> array,
				ArrayList<String> arrayname) {
			inflater = LayoutInflater.from(context);

			this._array = array;

			this._array1 = arrayname;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return _array.size();
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

				convertView = inflater.inflate(R.layout.soups, null);

				viewHolder = new ViewHolder();

				viewHolder.title = (TextView) convertView
						.findViewById(R.id.soupstate);

				viewHolder.title1 = (TextView) convertView
						.findViewById(R.id.soupdesc);
				
				// viewHolder.title1.setVisibility(View.GONE);
				/*
				 * viewHolder.imageview = (ImageView) convertView
				 * .findViewById(R.id.imgview);
				 */

				convertView.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder) convertView.getTag();
			}
			try {

				viewHolder.title.setText(Html.fromHtml(_array.get(position)));
				viewHolder.title1.setText(Html.fromHtml(_array1.get(position)));

				viewHolder.title1.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub

						// Log.i(">>>click>>", ">>>");
						String name, desc, direction, state, url, id;

						name = detistring[position];
						state = titlestring[position];
						desc = descstring[position];
						direction = directionstring[position];
						url = imgstring[position];
						id = foodid[position];

						System.out.println("direction>>>" + desc);

						Intent i = new Intent(getParent(), Details.class);
						Bundle bundle = new Bundle();
						bundle.putString(TAG_ID, id);
						bundle.putString(TAG_NAME, name);

						Log.i("DESC >>", ">>" + desc);

						bundle.putString(TAG_DESC, desc);

						bundle.putString(TAG_DIRECTION, direction);
						bundle.putString(TAG_STATE, state);
						bundle.putString(TAG_URL, url);

						i.putExtras(bundle);
						// startActivity(i);

						// Intent inn = new Intent(getParent(),
						// ListItemName.class);
						/*
						 * Bundle bundle = new Bundle(); bundle.putString("id",
						 * id); bundle.putString("catname", catname);
						 * inn.putExtras(bundle);
						 */
						WorkoutTabGroupActivity parantActivity = (WorkoutTabGroupActivity) getParent();
						parantActivity.startChildActivity("Details", i);

					}
				});

				// convertView.setOnClickListener(new
				// OnItemClickListener(position));
			} catch (Exception e) {
				// TODO: handle exception
			}
			return convertView;

		}

		private class ViewHolder {
			TextView title, title1;

			// Dynamic view

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
			return _userInfo.size();
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

				convertView = inflater.inflate(R.layout.soups, null);

				viewHolder = new ViewHolder();

				viewHolder.title = (TextView) convertView
						.findViewById(R.id.soupstate);

				viewHolder.title1 = (TextView) convertView
						.findViewById(R.id.soupdesc);

				/*
				 * viewHolder.imageview = (ImageView) convertView
				 * .findViewById(R.id.imgview);
				 */

				convertView.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder) convertView.getTag();
			}
			try {

				viewHolder.title.setText(Html.fromHtml(_userInfo.get(position)
						.getFOOD_NAME()));

				viewHolder.title1.setText(Html.fromHtml(_userInfo.get(position)
						.getFOOD_STATE()));
				viewHolder.title1.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub

						String name, desc, direction, state, url, id;

						name = detistring[position];
						state = titlestring[position];
						desc = descstring[position];
						direction = directionstring[position];
						url = imgstring[position];
						id = foodid[position];

						System.out.println("direction>>>" + direction);

						Intent i = new Intent(getParent(), Details.class);
						Bundle bundle = new Bundle();
						bundle.putString(TAG_ID, id);
						bundle.putString(TAG_NAME, name);
						bundle.putString(TAG_DESC, desc);

						bundle.putString(TAG_DIRECTION, direction);
						bundle.putString(TAG_STATE, state);
						bundle.putString(TAG_URL, url);

						i.putExtras(bundle);
						// startActivity(i);

						// Intent inn = new Intent(getParent(),
						// ListItemName.class);
						/*
						 * Bundle bundle = new Bundle(); bundle.putString("id",
						 * id); bundle.putString("catname", catname);
						 * inn.putExtras(bundle);
						 */
						WorkoutTabGroupActivity parantActivity = (WorkoutTabGroupActivity) getParent();
						parantActivity.startChildActivity("Details", i);

					}
				});

				// convertView.setOnClickListener(new
				// OnItemClickListener(position));
			} catch (Exception e) {
				// TODO: handle exception
			}
			return convertView;

		}

		private class ViewHolder 
		{
			TextView title, title1;
		}
	}

}
