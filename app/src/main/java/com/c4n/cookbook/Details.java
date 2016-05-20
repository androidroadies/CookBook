package com.c4n.cookbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActivityGroup;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;

import com.c4n.cookbook.tabsample.Albumartloader;

@SuppressLint("ResourceAsColor")
public class Details extends ActivityGroup {

	TextView txtname,  header;
	WebView txtdesc,txtdirection;
	EditText username, comment;
	private RatingBar ratingBar;
	private TextView txtRatingValue;
	private Button btnSubmit, viewreview;

	ProgressDialog pd;
	DatabaseHelper db;
	ImageView imgview;
	Albumartloader albumartloader;
	String id, name, state, desc, direction, url;
	BitmapDrawable background;
	Button fav;

	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@SuppressLint({ "NewApi", "ResourceAsColor" })
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);

		setContentView(R.layout.details);

		txtname = (TextView) findViewById(R.id.txtitemname);
		txtdesc = (WebView) findViewById(R.id.txtdetaildesc);
		
		txtdirection = (WebView) findViewById(R.id.txtdetaildirection);
		//txtdirection.setBackgroundResource(R.drawable.cc);

//		txtdirection.setBackgroundColor(0x00000000);

		txtdirection.setBackgroundColor(0000000000);
		txtdesc.setBackgroundColor(0000000000);
	//	web.setBackgroundResource(R.drawable.backgroundmain);
		
		header = (TextView) findViewById(R.id.headerdetail);

		Intent getdata = getIntent();

		fav = (Button) findViewById(R.id.favbutton);
		
		viewreview = (Button) findViewById(R.id.btnviewreview);
		viewreview.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent review = new Intent(getApplicationContext(),
						ViewReview.class);
				

				//System.out.println("in click >>>"+ name);
				
				/*Intent inn = new Intent(getParent(), ViewReview.class);
				Bundle bundle = new Bundle();
				
				bundle.putString("catname", name);
				inn.putExtras(bundle);
				System.out.println("in click >>>"+ name);
				startActivity(inn);*/
				
				startActivity(review);
			}
		});

		fav.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				
				
				fav.setBackgroundResource(R.drawable.addbut_hover);
				db = new DatabaseHelper(Details.this);
				Item item = new Item();
				id = getIntent().getStringExtra("food_id");
				item.id = id;

				id = getIntent().getStringExtra("food_id");
				name = getIntent().getStringExtra("food_title");
				desc = getIntent().getExtras().getString("food_desc");
				direction = getIntent().getExtras().getString("direction");
				url = getIntent().getExtras().getString("food_image_url");
				// state= getIntent().getExtras().getString("food_state");

				item.id = id;
				item.name = name;
				item.desc = desc;
				item.direction = direction;
				item.url = url;
				item.state = state;
				
				db.addProductToCart(item);
				db.close();

			}
		});
		id=getIntent().getExtras().getString("food_id");
		name = getIntent().getExtras().getString("food_title");
		desc = getIntent().getExtras().getString("food_desc");
		direction = getIntent().getExtras().getString("direction");

		url = getIntent().getExtras().getString("food_image_url");

		// imgview = getIntent().getExtras().getString("url");
		txtname.setText(name);
		//Spanned s = Html.fromHtml(desc); 
		//txtdesc.setText(s);
		//txtdesc.setTag(Html.fromHtml(desc));
		
		//WebView webview = (WebView) findViewById(R.id.MyWebview);
		 String summary = direction;
		 txtdirection.loadData(summary, "text/html", "utf-8");
		 
		 String summarydesc = desc;
		 txtdesc.loadData(summarydesc, "text/html", "utf-8");

		
		header.setText(name);
		new task().execute();
		addListenerOnRatingBar();
		// addListenerOnButton();

	}

	class task extends AsyncTask<String, String, Void> {
		int success;

		protected void onPreExecute() {
			super.onPreExecute();
			
		}

		@Override
		protected Void doInBackground(String... params) {

			addListenerOnButton();
			Bitmap mIcon11 = null;

			try {
				InputStream in = new java.net.URL(url).openStream();
				mIcon11 = BitmapFactory.decodeStream(in);
				background = new BitmapDrawable(mIcon11);

			} catch (Exception e) {

				e.printStackTrace();
			}

			return null;
		}

		protected void onPostExecute(Void result) {
			imgview = (ImageView) findViewById(R.id.imgdetail);
			imgview.setBackgroundDrawable(background);
			// pd.dismiss();

		}

		public void addListenerOnButton() {

			ratingBar = (RatingBar) findViewById(R.id.ratingBar);
			btnSubmit = (Button) findViewById(R.id.btnpost);

			username = (EditText) findViewById(R.id.edtname);
			comment = (EditText) findViewById(R.id.edtcomment);
			// if click on me, then display the current rating value.
			btnSubmit.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
				
					/*if (username.getText().toString().length() == 0) {
						Toast error = Toast.makeText(getApplicationContext(),
								"Oooops! Enter Username", Toast.LENGTH_LONG);
						error.show();

					} else if (comment.getText().toString().length() == 0) {
						Toast error = Toast.makeText(getApplicationContext(),
								"Oooops! Enter Hight", Toast.LENGTH_LONG);
						error.show();
					}
					else {
						execute(params);
					}*/
					
					float rating = ratingBar.getRating();
					String srating = Float.toString(rating);
					// Create a new HttpClient and Post Header
					
					//String id = username.getText().toString();
					
					String name = username.getText().toString();
					String usercomment = comment.getText().toString();

					HttpClient httpclient = new DefaultHttpClient();
					HttpPost httppost = new HttpPost(
							"http://thecompleteafricanrecipe.com/add_maseurments.php");

					try {
						// Add your data
						List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(
								2);
						
						System.out.println("in id>>>>"+id);

						nameValuePairs
						.add(new BasicNameValuePair("food_id", id));
						nameValuePairs
								.add(new BasicNameValuePair("name", name));
						nameValuePairs.add(new BasicNameValuePair("rating",
								srating));
						nameValuePairs.add(new BasicNameValuePair("comment",
								usercomment));
						httppost.setEntity(new UrlEncodedFormEntity(
								nameValuePairs));

						// Execute HTTP Post Request
						HttpResponse response = httpclient.execute(httppost);

						
//						try {
//							HttpResponse response = httpClient.execute(httpPost);
//
//							// writing response to log
						Log.d("Http Response:", response.toString());
//						} catch (ClientProtocolException e) {
//							// writing exception to log
//							e.printStackTrace();
//						} catch (IOException e) {
//							// writing exception to log
//							e.printStackTrace();
//
//						}
//					}
						
					} catch (ClientProtocolException e) {
						// TODO Auto-generated catch block
					} catch (IOException e) {
						// TODO Auto-generated catch block
					}
					myoncreate();
				}

			});
		}

	}

	public void addListenerOnRatingBar() {

		ratingBar = (RatingBar) findViewById(R.id.ratingBar);
		// txtRatingValue = (TextView) findViewById(R.id.txtRatingValue);

		// if rating is changed,
		// display the current rating value in the result (textview)
		// automatically
		ratingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
			public void onRatingChanged(RatingBar ratingBar, float rating,
					boolean fromUser) {

				// txtRatingValue.setText(String.valueOf(rating));
			}
		});
	}

	public void myoncreate() {
		// TODO Auto-generated method stub

		/*StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);*/

		setContentView(R.layout.details);

		txtname = (TextView) findViewById(R.id.txtitemname);
		txtdesc = (WebView) findViewById(R.id.txtdetaildesc);
		
		txtdirection = (WebView) findViewById(R.id.txtdetaildirection);
		//txtdirection.setBackgroundResource(R.drawable.cc);

		txtdirection.setBackgroundColor(0x00000000);
		txtdesc.setBackgroundColor(0x00000000);
	//	web.setBackgroundResource(R.drawable.backgroundmain);
		
		header = (TextView) findViewById(R.id.headerdetail);

		Intent getdata = getIntent();

		fav = (Button) findViewById(R.id.favbutton);
		viewreview = (Button) findViewById(R.id.btnviewreview);
		viewreview.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent review = new Intent(getApplicationContext(),
						ViewReview.class);
				startActivity(review);
			}
		});

		fav.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				fav.setBackgroundResource(R.drawable.addbut_hover);
				db = new DatabaseHelper(Details.this);
				Item item = new Item();
				id = getIntent().getStringExtra("food_id");
				item.id = id;

				id = getIntent().getStringExtra("food_id");
				name = getIntent().getStringExtra("food_title");
				desc = getIntent().getExtras().getString("food_desc");
				direction = getIntent().getExtras().getString("direction");
				url = getIntent().getExtras().getString("food_image_url");
				// state= getIntent().getExtras().getString("food_state");

				item.id = id;
				item.name = name;
				item.desc = desc;
				item.direction = direction;
				item.url = url;
				item.state = state;

				/*Log.i(">>name>>", ">>" + item.name);

				Log.i(">>desc>>", ">>" + item.desc);

				Log.i(">>direction>>", ">>" + item.direction);

				Log.i(">>url>>", ">>" + item.url);*/

				db.addProductToCart(item);
				db.close();

			}
		});
		name = getIntent().getExtras().getString("food_title");
		desc = getIntent().getExtras().getString("food_desc");
		direction = getIntent().getExtras().getString("direction");

		url = getIntent().getExtras().getString("food_image_url");

		// imgview = getIntent().getExtras().getString("url");
		txtname.setText(name);
		//Spanned s = Html.fromHtml(desc); 
		//txtdesc.setText(s);
		//txtdesc.setTag(Html.fromHtml(desc));
		
		//WebView webview = (WebView) findViewById(R.id.MyWebview);
		 String summary = direction;
		 txtdirection.loadData(summary, "text/html", "utf-8");
		 
		 String summarydesc = desc;
		 txtdesc.loadData(summarydesc, "text/html", "utf-8");

	//	txtdesc.setText(Html.fromHtml(desc));
		//txtdirection.setText(direction);
//		txtdirection.setTag(Html.fromHtml(direction));

//		txtdirection.setTag(direction);
		//String html	= direction;
//		Spanned marked_up = Html.fromHtml(direction);
//		((TextView) findViewById(R.id.txtdetaildirection)).setText(marked_up.toString());
		
	    //txtdirection.setText(Html.fromHtml(html));
		
		header.setText(name);
		new task().execute();
		addListenerOnRatingBar();
		// addListenerOnButton();

	}

	/*@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		Intent i = new Intent(getParent(), WorkoutTabGroup.class);
		WorkoutTabGroupActivity parantActivity = (WorkoutTabGroupActivity) getParent();
		parantActivity.startChildActivity("WorkoutTabGroup", i);
		//finishActivityFromChild(getParent(), 1);
		
		//finish();
		super.onBackPressed();
//		Intent backIntent = new Intent();
//	    backIntent.setClass(this, CookJson.class);
	    //startActivity(backIntent);
	}*/
}
