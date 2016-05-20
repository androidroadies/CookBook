package com.c4n.cookbook;
/*package com.example.cookbook;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	
	Button btnCal;
	EditText rating1, name1, comment1;
	private ProgressDialog pDialog;

	mxicoders.jsonparsar.JSONParser jsonParser = new mxicoders.jsonparsar.JSONParser();
	private static String url_mseurments = "http://care4nature.org/food/add_maseurments.php";
	private static final String TAG_SUCCESS = "success";

	String srating;
	String sname;
	String scomment;
	String Schest;
	String Swaist;
	String Sthighs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		Sdeviceid = Settings.Secure.getString(getContentResolver(),
				Settings.Secure.ANDROID_ID);
		
		

		rating1 = (EditText) findViewById(R.id.edit_mz_wight);
		name1 = (EditText) findViewById(R.id.edit_mz_hight);
		comment1 = (EditText) findViewById(R.id.edit_mz_rhr);
		waist = (EditText) findViewById(R.id.edit_mz_wiast);
		hip = (EditText) findViewById(R.id.edit_mz_hip);

		btnCal = (Button) findViewById(R.id.btn_calculate);
		btnCal.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			
					new CreateNewMesurments().execute();

				}

			
		});
	}
	
	class CreateNewMesurments extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(MainActivity.this);
			pDialog.setMessage("Measurements Calculating...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		protected String doInBackground(String... args) {

			srating = rating1.getText().toString();
			sname = name1.getText().toString();
			scomment = comment1.getText().toString();
			Swaist = waist.getText().toString();
			Sthighs = hip.getText().toString();

			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("rating", srating));
			params.add(new BasicNameValuePair("name", sname));
			params.add(new BasicNameValuePair("comment", scomment));
			params.add(new BasicNameValuePair("chest", Schest));
			params.add(new BasicNameValuePair("waist", Swaist));
			params.add(new BasicNameValuePair("thighs", Sthighs));

			// getting JSON Object
			// Note that create product url accepts POST method
			JSONObject json = jsonParser.makeHttpRequest(url_mseurments,
					"POST", params);

			;
			// check log cat fro response
			Log.d("Create Response", json.toString());

			// check for success tag
			try {

				int success = json.getInt(TAG_SUCCESS);

				if (success == 1) {

					// successfully created product
					Intent i=new Intent(getApplicationContext(), SuccessScreen.class);
					startActivity(i);
					// closing this screen
					finish();
				} else {
					// failed to create product
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;
		}

		protected void onPostExecute(String file_url) {
			// dismiss the dialog once done
			pDialog.dismiss();
		}

	}

	

}
*/