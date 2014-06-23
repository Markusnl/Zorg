package com.example.zorg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

class connectdtb extends AsyncTask<String, Void, String> {

	private Context context;
	private String action;
	
	connectdtb(Context callerclass,String action){
		context = callerclass;
		this.action = action;
		
	}
    @Override
    protected String doInBackground(String... params) {
    	String username = params[0];
        String password = params[1];
        BufferedReader in = null;
        String baseUrl = "http://145.24.222.230/index.php/";

        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost request = new HttpPost(baseUrl);

            List<NameValuePair> postParameters = new ArrayList<NameValuePair>();
            if (action.equals("login"))
            {
            	postParameters.add(new BasicNameValuePair("action", "validate_user"));
            	postParameters.add(new BasicNameValuePair("username", username));
            	postParameters.add(new BasicNameValuePair("password", password));
            } else if (action.equals("geterrors"))
            {
            	postParameters.add(new BasicNameValuePair("action", "get_dispensererrors"));
            } else 
            {
            	Log.e("error", "No database function called");
            }
            
            UrlEncodedFormEntity form = new UrlEncodedFormEntity(postParameters);
            request.setEntity(form);

            Log.v("log", "making POST request to: " + baseUrl);

            HttpResponse response = httpClient.execute(request);

            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuffer sb = new StringBuffer("");
            String line = "";
            String NL = System.getProperty("line.separator");
            while ((line = in.readLine()) != null) {
                sb.append(line + NL);
            }
            in.close();

            return sb.toString();
        } catch (Exception e) {
            return "Exception happened: " + e.getMessage();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void onPostExecute(String result) {
        // this refers to a TextView defined as a private field in the parent Activity
    	Log.e("result",result.toString());
    	
    	 if (action.equals("login"))
         {
    		 ((MainActivity) context).txt.setText(result);
    	    	
    	    	if (result.contains("Good"))
    	    	{
    	    		Intent wissel = new Intent("com.example.zorg.ZORGTERMINAL");
    	    		context.startActivity(wissel);
    	    		((MainActivity) context).finish();
    	    	}
         } else if (action.equals("geterrors"))
         {
        	 ((ZorgTerminal) context).txt.setText(result);
         } else 
         {
         	Log.e("error", "No database function called");
         }
    	
    	
    	
    }

}