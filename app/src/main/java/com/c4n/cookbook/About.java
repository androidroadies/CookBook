package com.c4n.cookbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActivityGroup;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.mayank.pojo.ImageLoader;

public class About extends ActivityGroup {

	// Albumartloader imaAlbumartloader;
	ImageLoader imgImageLoader;
	//TextView aboutdesc;
	WebView aboutdesc;
	ImageView aboutimg;
	BitmapDrawable background;

	private ProgressDialog pDialog;
	ArrayList<HashMap<String, String>> contactList;

	private static final String TAG_DESC = "a_desc";
	private static final String TAG_URL = "a_image_url";

	// private static final String urlanimation = "animation_url";

	private static String url = "http://thecompleteafricanrecipe.com/about.php";
	JSONArray contacts = null;

	String description, imgurl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		// getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		// WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.about);

		aboutdesc = (WebView) findViewById(R.id.aboutdesc1);
		aboutdesc.setBackgroundColor(0x00000000);
		new task().execute();
	}

	class task extends AsyncTask<String, String, Void> {
		int success;

		protected void onPreExecute() {
			super.onPreExecute();

			/*
			 * pDialog = ProgressDialog.show(getParent(), "",
			 * "Please wait...",true); // pDialog = new
			 * ProgressDialog(CookJson.this); //
			 * pDialog.setIndeterminate(false); // pDialog.setCancelable(true);
			 * pDialog.show();
			 */
		}

		@Override
		protected Void doInBackground(String... params) {

			Bitmap mIcon11 = null;
			try {
				InputStream in = new java.net.URL(TAG_URL).openStream();
				mIcon11 = BitmapFactory.decodeStream(in);
				background = new BitmapDrawable(mIcon11);

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			

			// TODO Auto-generated method stub
			imgImageLoader = new ImageLoader(About.this);
			JSOnParser jParser = new JSOnParser();
			contactList = new ArrayList<HashMap<String, String>>();
			JSONArray json = jParser.getJsonFromURL(url);
			try {
				for (int i = 0; i < json.length(); i++) {

					JSONObject c = json.getJSONObject(i);

					description = c.getString(TAG_DESC);
					imgurl = c.getString(TAG_URL);

					//System.out.println("desc >>>>>" + description);
					//System.out.println("url >>>>>" + imgurl);

				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		protected void onPostExecute(Void v) {
			// list.setAdapter(new ListAdapter(About.this));

			System.out.println("desc >>>>>" + description.trim());
			// aboutdesc.setText(Html.fromHtml(description));
			// aboutdesc.setText(description);
			 String summarydesc = description;
			 aboutdesc.loadData(summarydesc, "text/html", "utf-8");
			aboutimg = (ImageView) findViewById(R.id.imgabout);
			// aboutimg.setBackgroundDrawable(background);
			imgImageLoader.DisplayImage(imgurl, About.this, aboutimg);
			// pDialog.dismiss();
		}
	}

}
