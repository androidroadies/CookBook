package com.c4n.cookbook;

import java.io.IOException;
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

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;

public class MyAndroidAppActivity extends Activity {

	private RatingBar ratingBar;
	private TextView txtRatingValue;
	private Button btnSubmit;
	
	EditText txtusername,txtcomment;
	
	
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rating);
		
		txtusername =(EditText) findViewById(R.id.txtusername);
		txtcomment=(EditText) findViewById(R.id.txtcomment);

		
		//String email = youedittext3.getText().toString();
		
		addListenerOnRatingBar();
		addListenerOnButton();
		
	}

	public void postData() {
	    // Create a new HttpClient and Post Header
		
		String username = txtusername.getText().toString();
		String comment = txtcomment.getText().toString();
		String rating = txtRatingValue.getText().toString();
		
	    HttpClient httpclient = new DefaultHttpClient();
	    HttpPost httppost = new HttpPost("http://care4nature.org/food/test1.php");

	    try {
	        // Add your data
	        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
	        
	        nameValuePairs.add(new BasicNameValuePair("user_name",username));
	        nameValuePairs.add(new BasicNameValuePair("rating",rating));
	        nameValuePairs.add(new BasicNameValuePair("comment",comment));
	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

	        // Execute HTTP Post Request
	        HttpResponse response = httpclient.execute(httppost);

	    } catch (ClientProtocolException e) {
	        // TODO Auto-generated catch block
	    } 
	    catch (IOException e) 
	    {
	        // TODO Auto-generated catch block
	    }
	}
	public void addListenerOnRatingBar() {

		ratingBar = (RatingBar) findViewById(R.id.ratingBar);
		txtRatingValue = (TextView) findViewById(R.id.txtRatingValue);

		//if rating is changed,
		//display the current rating value in the result (textview) automatically
		ratingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
			public void onRatingChanged(RatingBar ratingBar, float rating,
					boolean fromUser) {

				txtRatingValue.setText(String.valueOf(rating));
			}
		});
	}

	public void addListenerOnButton() {

		ratingBar = (RatingBar) findViewById(R.id.ratingBar1);
		btnSubmit = (Button) findViewById(R.id.btnSubmit);

		//if click on me, then display the current rating value.
		btnSubmit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				postData();

				/*Toast.makeText(MyAndroidAppActivity.this,
						String.valueOf(ratingBar.getRating()),
						Toast.LENGTH_SHORT).show();*/
				System.out.println(ratingBar);
			}

		});

	}
}