package com.c4n.cookbook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
 
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
 
public class JsonObject {
 
        static InputStream _is = null;
        static JsonObject jObj = null;
        static String json = null;
 
        public JsonObject() {
 
        }
 
        public JsonObject(String json2) {
			// TODO Auto-generated constructor stub
		}

		public JsonObject getJsonFromURL(String url) {
                try {
                        DefaultHttpClient httpclient = new DefaultHttpClient();
 
                        HttpPost post = new HttpPost(url);
 
                        HttpResponse response = httpclient.execute(post);
 
                        HttpEntity entity = response.getEntity();
 
                        _is = entity.getContent();
 
                } catch (ClientProtocolException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
 
                try {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(
                                        _is, "iso-8859-1"), 8);
 
                        StringBuilder sb = new StringBuilder();
                        String line = null;
 
                        while ((line = reader.readLine()) != null) {
                                sb.append(line + "\n");
 
                        }
                        _is.close();
                        json = sb.toString();
 
                } catch (UnsupportedEncodingException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
 
                jObj = new JsonObject(json);
 
                return jObj;
 
        }
}