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
 
public class JSOnParser {
 
        static InputStream _is = null;
        static JSONArray jObj = null;
        static String json = null;
 
        public JSOnParser() {
 
        }
 
        public JSONArray getJsonFromURL(String url) {
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
 
                try {
                        jObj = new JSONArray(json);
 
                } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
 
                return jObj;
 
        }
}