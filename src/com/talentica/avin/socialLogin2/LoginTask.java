package com.talentica.avin.socialLogin2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.content.Entity;
import android.os.AsyncTask;
import android.util.Log;

public class LoginTask extends AsyncTask<String, Integer, String>  {
	
	
	private final String TAG= "Login Task";

	
	
	@Override
	protected String doInBackground(String... params) {
		DefaultHttpClient client= new DefaultHttpClient();
		
		HttpPost post= new HttpPost(params[0]);
		List<NameValuePair> nameValuePairs= new ArrayList<NameValuePair>(2);
		nameValuePairs.add(0, new BasicNameValuePair("user", "avin"));
		nameValuePairs.add(1, new BasicNameValuePair("password", "1234"));
		HttpResponse response=null;
		try {
			
			Log.d("%%%", "Insided back  ground");
			post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			response=client.execute(post);
			
		} catch (UnsupportedEncodingException e) {
			Log.d(TAG, "Unab le to create form encoded entity");
			e.printStackTrace();
			return null;
		} catch (ClientProtocolException e) {
			Log.d(TAG, "Failed Network Request");
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			Log.d(TAG, "Failed Network Request");
			e.printStackTrace();
			return null;
		}
		if(response!=null){
			try {
				String str;
				StringBuilder builder= new StringBuilder();
				InputStream stream= response.getEntity().getContent();
				InputStreamReader reader= new InputStreamReader(stream);
				BufferedReader bReader= new BufferedReader(reader);
				while((str=bReader.readLine())!=null){
					builder.append(str);
				}
				return builder.toString();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Log.d("NULLLLLLLLLLLL", "Response is nullll");
		return null;
	}
	
	@Override
	protected void onPostExecute(String result) {
		Log.d("The response is ", "$$$$$$$$$$ "+result);
		super.onPostExecute(result);
	}
	

}
